package io.pieszku.messenger.api;

import java.io.Serializable;
import java.util.function.Consumer;

public class Packet<T extends Packet<T>> implements Serializable {

    private Consumer<PacketAction<T>> action;

    public Consumer<PacketAction<T>> getAction() {
        return action;
    }
    public void setAction(Consumer<PacketAction<T>> action) {
        this.action = action;
    }
    public void apply(PacketAction<T> packet){
        this.action.accept(packet);
    }
}
