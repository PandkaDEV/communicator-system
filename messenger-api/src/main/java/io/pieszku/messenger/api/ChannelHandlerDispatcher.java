package io.pieszku.messenger.api;

import io.pieszku.messenger.api.stereotype.PacketHandler;

public class ChannelHandlerDispatcher {

    private final String description;
    private final HandlerInfo info;
    private final PacketHandler handler;

    public ChannelHandlerDispatcher(String description, HandlerInfo info, PacketHandler handler) {
        this.description = description;
        this.info = info;
        this.handler = handler;
    }

    public String getDescription() {
        return description;
    }

    public HandlerInfo getInfo() {
        return info;
    }

    public PacketHandler getHandler() {
        return handler;
    }
}
