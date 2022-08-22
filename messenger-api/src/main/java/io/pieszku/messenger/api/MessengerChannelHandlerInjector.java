package io.pieszku.messenger.api;

import com.scalified.tree.TreeNode;
import org.javatuples.Pair;

public class MessengerChannelHandlerInjector {

    private final MessengerSubscriberCache subscriberCache = MessengerControllerAPI.getInstance().getSubscriberCache();

    public void inject(Messenger messenger, MessengerChannelHandlerMapper mapper) {
        mapper.getHandlerMap().subtrees().stream().map(TreeNode::data).map(Pair::getValue1).forEach(info -> {this.registerHandlerExecutor(messenger, info, mapper);});
    }
    private void registerHandlerExecutor(Messenger messenger, MessengerHandlerInfo info, MessengerChannelHandlerMapper mapper){
        MessengerChannelHandlerExecutor executor = new MessengerChannelHandlerExecutor(messenger, info, null, mapper);
        messenger.subscribe(info.getChannel(), executor);
        this.subscriberCache.add(executor);
    }
}
