package io.pieszku.messenger.api;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.pieszku.messenger.api.di.DependencyInjectionModule;

public abstract class MessengerController {

    private final MessengerType type;
    private Injector injector;
    private final Messenger messenger;

    public MessengerController(Messenger messenger, MessengerType type) {
        this.type = type;
        this.messenger = messenger;
    }
    public void load(String handlersPackageName){
        this.injector = Guice.createInjector(new DependencyInjectionModule());
        MessengerChannelHandlerScanner scanner = new MessengerChannelHandlerScanner(handlersPackageName);
        MessengerChannelHandlerMapper mapper = new MessengerChannelHandlerMapper(scanner, this.injector);
        MessengerChannelHandlerInjector injector = new MessengerChannelHandlerInjector();
        injector.inject(messenger, mapper);
    }

    public Injector getInjector() {
        return injector;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public MessengerType getType() {
        return type;
    }
}
