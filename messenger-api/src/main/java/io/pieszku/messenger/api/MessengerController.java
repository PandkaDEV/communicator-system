package io.pieszku.messenger.api;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.pieszku.messenger.api.di.DependencyInjectionModule;

public abstract class MessengerController {

    private final MessengerType type;
    private Injector injector;

    public MessengerController(MessengerType type) {
        this.type = type;
    }
    public void load(String handlersPackageName){
        this.injector = Guice.createInjector(new DependencyInjectionModule());
        MessengerChannelHandlerScanner scanner = new MessengerChannelHandlerScanner(handlersPackageName);
        MessengerChannelHandlerMapper mapper = new MessengerChannelHandlerMapper(scanner, this.injector);
        MessengerChannelHandlerInjector injector = new MessengerChannelHandlerInjector();
        injector.inject(new Messenger() {
            @Override
            public void subscribe(String channelName, MessengerChannelHandlerExecutor executor) {

            }

            @Override
            public void reply(MessengerPacket packet) {

            }

            @Override
            public void send(String channelName, MessengerPacket packet) {

            }

            @Override
            public <T> void sendRequestPacket(String channelName, MessengerRequestPacket requestPacket, MessengerResponsePacket<T> responsePacket) {

            }
        }, mapper);
    }
    public MessengerType getType() {
        return type;
    }
}
