package io.messenger.nats;

import io.pieszku.messenger.api.*;

public class NatsMessenger implements Messenger {

    @Override
    public void subscribe(String channelName, MessengerChannelHandlerExecutor executor) {

    }

    @Override
    public void reply(MessengerPacket packet) {

    }

    @Override
    public void send(String channelName, MessengerPacket packet) {

    }

    @Override
    public <T> void sendRequestPacket(String channelName, MessengerRequestPacket requestPacket, MessengerResponsePacket<T> responsePacket) {

    }
}
