package io.pieszku.messenger.api;

public class MessengerControllerAPI extends MessengerController{

    private static MessengerControllerAPI instance;
    private final MessengerChannelCache channelCache;
    private final MessengerSubscriberCache subscriberCache;

    public static MessengerControllerAPI getInstance() {
        return instance;
    }

    public MessengerControllerAPI(Messenger messenger, MessengerType type, String handlerPackageName) {
        super(messenger, type);
        instance = this;
        this.channelCache = new MessengerChannelCache();
        this.subscriberCache = new MessengerSubscriberCache();
        this.channelCache.add(new MessengerChannel("test_messenger"));
        this.channelCache.add(new MessengerChannel("response_messenger"));
        this.load(handlerPackageName);
    }

    public static void main(String[] args) {
      //  new MessengerControllerAPI(MessengerType.NATS, "io.pieszku.messenger.api.test");
    }

    public MessengerChannelCache getChannelCache() {
        return channelCache;
    }

    public MessengerSubscriberCache getSubscriberCache() {
        return subscriberCache;
    }
}
