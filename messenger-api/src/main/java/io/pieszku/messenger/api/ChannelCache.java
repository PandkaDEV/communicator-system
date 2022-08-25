package io.pieszku.messenger.api;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelCache {

    private final Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    public Optional<Channel> findChannelByName(String name){
        return Optional.ofNullable(this.channelMap.get(name));
    }

    public void add(Channel channel){
        this.channelMap.put(channel.getName(), channel);
    }

    public Map<String, Channel> getChannelMap() {
        return channelMap;
    }
}
