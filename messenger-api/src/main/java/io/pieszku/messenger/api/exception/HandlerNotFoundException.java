package io.pieszku.messenger.api.exception;

public class HandlerNotFoundException extends RuntimeException{
    public HandlerNotFoundException(String message) {
        super(message);
    }
}
