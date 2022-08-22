package io.pieszku.messenger.api;

@FunctionalInterface
public interface MessengerPacketAction<T extends MessengerPacket<T>> {
    void execute(T packet);
}
