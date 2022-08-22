package io.pieszku.messenger.api;

import java.util.concurrent.ThreadLocalRandom;

public abstract class MessengerRequestPacket extends MessengerPacket<MessengerRequestPacket> {

    private long callbackId = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
    private boolean done;
    private String errorMessage;

    public boolean isDone() {
        return done;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(long callbackId) {
        this.callbackId = callbackId;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
