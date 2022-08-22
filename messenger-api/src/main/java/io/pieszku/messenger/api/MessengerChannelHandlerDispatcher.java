package io.pieszku.messenger.api;

import io.pieszku.messenger.api.stereotype.MessengerPacketHandler;

public class MessengerChannelHandlerDispatcher {

    private final String description;
    private final MessengerHandlerInfo info;
    private final MessengerPacketHandler handler;

    public MessengerChannelHandlerDispatcher(String description, MessengerHandlerInfo info, MessengerPacketHandler handler) {
        this.description = description;
        this.info = info;
        this.handler = handler;
    }

    public String getDescription() {
        return description;
    }

    public MessengerHandlerInfo getInfo() {
        return info;
    }

    public MessengerPacketHandler getHandler() {
        return handler;
    }
}
