package io.pieszku.messenger.api;

import java.util.ArrayList;
import java.util.List;

public class MessengerSubscriberCache {

    private final List<MessengerChannelHandlerExecutor> executors = new ArrayList<>();

    public void add(MessengerChannelHandlerExecutor handlerInfo){
        this.executors.add(handlerInfo);
    }

    public List<MessengerChannelHandlerExecutor> getExecutors() {
        return executors;
    }
}
