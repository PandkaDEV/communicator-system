package io.pieszku.messenger.api;

public interface MessengerResponsePacket<T>{

    /**
     * @deprecated Method use if handler from request packet will set responsePacket$setDone(true);
     * @param packet
     */
    void accept(T packet);

    /**
     * @deprecated Method use if handler from request packet will set responsePacket$setDone(false); and responsePacket#setErrorMessage("Error");
     * @param message
     */
    void error(String message);
}
