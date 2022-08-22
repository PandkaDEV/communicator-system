package io.pieszku.messenger.api;

public abstract class MessengerRequestPacket extends MessengerPacket<MessengerRequestPacket> {

    private boolean done;
    private String errorMessage;

    public boolean isDone() {
        return done;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
