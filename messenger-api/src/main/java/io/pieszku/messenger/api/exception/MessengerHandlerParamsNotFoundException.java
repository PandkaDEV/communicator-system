package io.pieszku.messenger.api.exception;

public class MessengerHandlerParamsNotFoundException extends RuntimeException{
    public MessengerHandlerParamsNotFoundException(String message) {
        super(message);
    }
}
