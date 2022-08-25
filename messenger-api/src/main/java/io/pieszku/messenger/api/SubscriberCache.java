package io.pieszku.messenger.api;

import java.util.ArrayList;
import java.util.List;

public class SubscriberCache {

    private final List<ChannelHandlerExecutor> executors = new ArrayList<>();

    public void add(ChannelHandlerExecutor handlerInfo){
        this.executors.add(handlerInfo);
    }

    public List<ChannelHandlerExecutor> getExecutors() {
        return executors;
    }
}
