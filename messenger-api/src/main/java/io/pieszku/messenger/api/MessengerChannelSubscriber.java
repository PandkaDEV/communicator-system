package io.pieszku.messenger.api;

public interface MessengerChannelSubscriber {
    boolean onMessage(MessengerChannel channel, MessengerPacket messenger);
}
