package io.pieszku.messenger.api.exception;

public class SendPacketException extends RuntimeException{
    public SendPacketException(String message) {
        super(message);
    }
}
