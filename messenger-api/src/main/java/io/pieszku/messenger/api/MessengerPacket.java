package io.pieszku.messenger.api;

import java.io.Serializable;
import java.util.function.Consumer;

public class MessengerPacket<T extends MessengerPacket<T>> implements Serializable {

    private Consumer<MessengerPacketAction<T>> action;

    public Consumer<MessengerPacketAction<T>> getAction() {
        return action;
    }
    public void setAction(Consumer<MessengerPacketAction<T>> action) {
        this.action = action;
    }
    public void apply(MessengerPacketAction<T> packet){
        this.action.accept(packet);
    }
}
