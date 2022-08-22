package io.pieszku.messenger.api.exception;

public class MessengerConnectionNotFoundException extends RuntimeException{
    public MessengerConnectionNotFoundException(String message) {
        super(message);
    }
}
