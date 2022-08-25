package io.pieszku.messenger.api;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.pieszku.messenger.api.di.DependencyInjectionModule;

public abstract class Controller {

    private final MessengerType type;
    private Injector injector;
    private final Messenger messenger;

    public Controller(Messenger messenger, MessengerType type) {
        this.type = type;
        this.messenger = messenger;
    }
    public void load(String handlersPackageName){
        this.injector = Guice.createInjector(new DependencyInjectionModule());
        ChannelHandlerScanner scanner = new ChannelHandlerScanner(handlersPackageName);
        ChannelHandlerMapper mapper = new ChannelHandlerMapper(scanner, this.injector);
        ChannelHandlerInjector injector = new ChannelHandlerInjector();
        injector.inject(this.messenger, mapper);
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
