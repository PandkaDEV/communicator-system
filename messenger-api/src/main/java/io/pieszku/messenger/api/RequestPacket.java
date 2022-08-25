package io.pieszku.messenger.api;

import java.util.concurrent.ThreadLocalRandom;

public abstract class RequestPacket extends Packet<RequestPacket> {

    private long callbackId = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
    private String replyChannel;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(long callbackId) {
        this.callbackId = callbackId;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReplyChannel() {
        return replyChannel;
    }

    public void setReplyChannel(String replyChannel) {
        this.replyChannel = replyChannel;
    }
}
