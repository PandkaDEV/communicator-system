package io.pieszku.messenger.api;

import java.util.Arrays;

public class MessengerController extends Controller {

    private static MessengerController instance;
    private final ChannelCache channelCache;

    public static MessengerController getInstance() {
        return instance;
    }

    public MessengerController(Messenger messenger, MessengerType type, String handlerPackageName, Channel... channels) {
        super(messenger, type);
        instance = this;
        this.channelCache = new ChannelCache();
        Arrays.stream(channels).forEach(this.channelCache::add);
        this.load(handlerPackageName);
    }
    public ChannelCache getChannelCache() {
        return channelCache;
    }

}
