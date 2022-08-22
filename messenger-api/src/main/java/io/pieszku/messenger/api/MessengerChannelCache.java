package io.pieszku.messenger.api;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MessengerChannelCache {

    private final Map<String, MessengerChannel> channelMap = new ConcurrentHashMap<>();

    public Optional<MessengerChannel> findChannelByName(String name){
        return Optional.ofNullable(this.channelMap.get(name));
    }

    public void add(MessengerChannel channel){
        this.channelMap.put(channel.getName(), channel);
    }

    public Map<String, MessengerChannel> getChannelMap() {
        return channelMap;
    }
}
