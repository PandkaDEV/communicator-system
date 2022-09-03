package io.messenger.nats;

import io.nats.client.Connection;
import io.nats.client.Options;
import io.pieszku.messenger.api.*;
import io.pieszku.messenger.api.exception.ConnectionNotFoundException;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class Nats implements Messenger {

    private Connection connection;

    @Override
    public void connect(MessengerType type, String hostName, int port, String password) {
        try {
            Options.Builder builder = new Options.Builder().server(type.getHostUrl() + hostName + ":" + port).reconnectWait(Duration.ofSeconds(10L)).maxReconnects(1000).connectionTimeout(Duration.ofSeconds(2L));
            this.connection = io.nats.client.Nats.connect(builder.build());
        } catch (Exception e) {
            throw new ConnectionNotFoundException(String.format("Nats connection not found host: %s", e.getMessage()));
        }
    }

    @Override
    public void subscribe(Channel channel, ChannelHandlerExecutor executor) {
        this.connection.createDispatcher().subscribe(channel.getName(), message -> {
            var packet = PacketSerializer.decodePacket(message.getData());
            if (Arrays.stream(executor.getInfo().getReceivedPackets()).noneMatch(clazz -> packet.getClass().isAssignableFrom(clazz))) return;
            if(packet instanceof RequestPacket){
                RequestPacket requestPacket = (RequestPacket) packet;
                requestPacket.setReplyChannel(message.getReplyTo());
            }
            executor.onMessage(channel, packet);
        });
    }

    @Override
    public void reply(RequestPacket requestPacket) {
        this.send(requestPacket.getReplyChannel(), requestPacket);
    }

    @Override
    public void send(String channelName, Packet packet) {
        this.connection.publish(channelName, PacketSerializer.encodePacket(packet));
    }

    @Override
    public <T> CompletableFuture<T> sendRequestPacket(String channelName, RequestPacket requestPacket, ResponsePacket<T> responsePacket) {
        var messagePacket = this.connection.requestWithTimeout(channelName, PacketSerializer.encodePacket(requestPacket), Duration.ofMillis(5L))
                .thenApply(message -> (RequestPacket) PacketSerializer.decodePacket(message.getData()))
                .whenComplete((data, throwable) -> {
                    if (data != null && data.getErrorMessage() != null) {
                        System.out.printf("The packet: %s, returned the wrong message: %s%n", requestPacket.getClass().getSimpleName(), data.getErrorMessage());
                        return;
                    }
                    responsePacket.accept((T) data);
                });
        return (CompletableFuture<T>) messagePacket;
    }
}
