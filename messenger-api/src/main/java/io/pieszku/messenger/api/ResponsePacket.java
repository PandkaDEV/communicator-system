package io.pieszku.messenger.api;

public interface ResponsePacket<T>{

    /**
     * @deprecated Method use if handler from request packet will set responsePacket$setDone(true);
     * @param packet
     */
    void accept(T packet);
}
