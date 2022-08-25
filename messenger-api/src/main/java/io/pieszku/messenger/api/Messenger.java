package io.pieszku.messenger.api;

import java.util.concurrent.CompletableFuture;

public interface Messenger {

    /**
     * @deprecated Make connecting to the select messenger with the help of @param/s
     * @param type
     * @param hostName
     * @param port
     * @param password
     */
    void connect(MessengerType type, String hostName, int port, String password);
    /**
     * @deprecated Handling of subscriptions to which information will arrive
     * @param channel
     * @param executor
     */
    void subscribe(Channel channel, ChannelHandlerExecutor executor);

    /**
     * @deprecated Sends a packet to the response channel
     * @param packet
     */
    void reply(RequestPacket packet);

    /**
     * @deprecated Sends a packet to the select channel
     * @param channelName
     * @param packet
     */
    void send(String channelName, Packet packet);

    /**
     * @deprecated Sends a packet to the select channel and have ability to response with the help of messenger#reply(responsePacket);
     * @param channelName
     * @param requestPacket
     * @param responsePacket
     * @param <T>
     */
    <T> CompletableFuture<T> sendRequestPacket(String channelName, RequestPacket requestPacket, ResponsePacket<T> responsePacket);
}
