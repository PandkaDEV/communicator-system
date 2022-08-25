package io.pieszku.messenger.api.exception;

public class HandlerParamsNotFoundException extends RuntimeException{
    public HandlerParamsNotFoundException(String message) {
        super(message);
    }
}
