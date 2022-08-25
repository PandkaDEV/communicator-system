package io.pieszku.messenger.api;

import com.scalified.tree.TreeNode;
import org.javatuples.Pair;

public class ChannelHandlerInjector {

    private final SubscriberCache subscriberCache = ControllerAPI.getInstance().getSubscriberCache();

    public void inject(Messenger messenger, ChannelHandlerMapper mapper) {
        mapper.getHandlerMap().subtrees().stream().map(TreeNode::data).map(Pair::getValue1).forEach(info -> {this.registerHandlerExecutor(messenger, info, mapper);});
    }
    private void registerHandlerExecutor(Messenger messenger, HandlerInfo info, ChannelHandlerMapper mapper){
        ChannelHandlerExecutor executor = new ChannelHandlerExecutor(messenger, info, null, mapper);
        messenger.subscribe(info.getChannel(), executor);
        this.subscriberCache.add(executor);
    }
}
