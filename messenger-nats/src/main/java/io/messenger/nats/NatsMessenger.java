package io.messenger.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.pieszku.messenger.api.*;
import io.pieszku.messenger.api.exception.MessengerConnectionNotFoundException;

import java.time.Duration;
import java.util.Arrays;

public class NatsMessenger implements Messenger {

    private Connection connection;

    @Override
    public void connect(MessengerType type, String hostName, int port, String password) {
        try {
            Options.Builder builder = new Options.Builder().server(type.getHostUrl() + hostName + ":" + port)
                    .reconnectWait(Duration.ofSeconds(10L)).maxReconnects(1000)
                    .connectionTimeout(Duration.ofSeconds(2L));
            this.connection = Nats.connect(builder.build());
        } catch (Exception e) {
            throw new MessengerConnectionNotFoundException(String.format("Nats connection not found host: %s", e.getMessage()));
        }
    }

    @Override
    public void subscribe(MessengerChannel channel, MessengerChannelHandlerExecutor executor) {
        this.connection.createDispatcher().subscribe(channel.getName(), message -> {
            MessengerPacket packet = MessengerPacketSerializer.decodePacket(message.getData());
             if(!Arrays.stream(executor.getInfo().getReceivedPackets()).anyMatch(clazz -> packet.getClass().isAssignableFrom(clazz))) return;
            executor.onMessage(channel, packet);
        });
    }

    @Override
    public void reply(MessengerPacket packet) {
        this.send("response_messenger", packet);
    }

    @Override
    public void send(String channelName, MessengerPacket packet) {
        this.connection.publish(channelName, MessengerPacketSerializer.encodePacket(packet));
    }

    @Override
    public <T> void sendRequestPacket(String channelName, MessengerRequestPacket requestPacket, MessengerResponsePacket<T> responsePacket) {
        this.send(channelName, requestPacket);
    }
}
