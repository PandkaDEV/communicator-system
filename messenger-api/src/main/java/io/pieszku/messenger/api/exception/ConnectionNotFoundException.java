package io.pieszku.messenger.api.exception;

public class ConnectionNotFoundException extends RuntimeException{
    public ConnectionNotFoundException(String message) {
        super(message);
    }
}
