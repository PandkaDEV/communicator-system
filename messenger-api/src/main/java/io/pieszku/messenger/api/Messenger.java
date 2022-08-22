package io.pieszku.messenger.api;

public interface Messenger {
    void subscribe(String channelName, MessengerChannelHandlerExecutor executor);
    void reply(MessengerPacket packet);
    void send(String channelName, MessengerPacket packet);
    <T> void sendRequestPacket(String channelName, MessengerRequestPacket requestPacket, MessengerResponsePacket<T> responsePacket);
}
