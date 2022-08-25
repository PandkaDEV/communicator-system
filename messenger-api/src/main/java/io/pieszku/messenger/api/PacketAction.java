package io.pieszku.messenger.api;

@FunctionalInterface
public interface PacketAction<T extends Packet<T>> {
    void execute(T packet);
}
