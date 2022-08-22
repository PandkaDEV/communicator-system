package io.pieszku.messenger.api;

import java.lang.reflect.Method;

public class MessengerHandlerInfo {

    private final MessengerChannel channel;
    private final String description;
    private final boolean async;
    private Class<?>[] receivedPackets;
    private Method method;
    private Object instance;

    public MessengerHandlerInfo(MessengerChannel channel, String description, boolean async) {
        this.channel = channel;
        this.description = description;
        this.async = async;
    }

    public MessengerChannel getChannel() {
        return channel;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAsync() {
        return async;
    }

    public Method getMethod() {
        return method;
    }

    public Object getInstance() {
        return instance;
    }

    public Class<?>[] getReceivedPackets() {
        return receivedPackets;
    }

    public void setReceivedPackets(Class<?>[] receivedPackets) {
        this.receivedPackets = receivedPackets;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
