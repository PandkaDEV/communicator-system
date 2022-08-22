package io.pieszku.messenger.api.exception;

public class MessengerHandlerNotFoundException extends RuntimeException{
    public MessengerHandlerNotFoundException(String message) {
        super(message);
    }
}
