package io.pieszku.messenger.api;

import java.util.Arrays;

public class ControllerAPI extends Controller {

    private static ControllerAPI instance;
    private final ChannelCache channelCache;
    private final SubscriberCache subscriberCache;

    public static ControllerAPI getInstance() {
        return instance;
    }

    public ControllerAPI(Messenger messenger, MessengerType type, String handlerPackageName, Channel... channels) {
        super(messenger, type);
        instance = this;
        this.channelCache = new ChannelCache();
        this.subscriberCache = new SubscriberCache();
        Arrays.stream(channels).forEach(this.channelCache::add);
        this.load(handlerPackageName);
    }
    public ChannelCache getChannelCache() {
        return channelCache;
    }

    public SubscriberCache getSubscriberCache() {
        return subscriberCache;
    }
}
