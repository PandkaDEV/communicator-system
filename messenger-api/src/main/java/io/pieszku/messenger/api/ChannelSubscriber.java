package io.pieszku.messenger.api;

public interface ChannelSubscriber {
    boolean onMessage(Channel channel, Packet messenger);
}
