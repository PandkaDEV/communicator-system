package io.pieszku.messenger.api;

import com.google.inject.Injector;
import com.scalified.tree.TreeNode;
import com.scalified.tree.multinode.ArrayMultiTreeNode;
import io.pieszku.messenger.api.exception.MessengerHandlerNotFoundException;
import io.pieszku.messenger.api.stereotype.MessengerHandler;
import io.pieszku.messenger.api.stereotype.MessengerPacketHandler;
import org.apache.commons.lang.NotImplementedException;
import org.javatuples.Pair;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MessengerChannelHandlerMapper {

    private final MessengerChannelHandlerScanner scanner;
    private final MessengerChannelCache channelCache = MessengerControllerAPI.getInstance().getChannelCache();
    private final TreeNode<Pair<MessengerChannel, MessengerHandlerInfo>> handlerMap = new ArrayMultiTreeNode<>(null);
    private final Injector injector;

    public MessengerChannelHandlerMapper(MessengerChannelHandlerScanner scanner, Injector injector) {
        this.scanner = scanner;
        this.injector = injector;
        this.mapHandlersByClassName();
    }
    private void mapHandlersByClassName(){
        this.scanner.getClasses().forEach(clazz -> {
            TreeNode<Pair<MessengerChannel, MessengerHandlerInfo>> handlerTree = new ArrayMultiTreeNode<>(null);
            MessengerHandler handler = clazz.getAnnotation(MessengerHandler.class);
            this.channelCache.findChannelByName(handler.channelName()).ifPresent(channel -> {
                MessengerHandlerInfo handlerInfo = new MessengerHandlerInfo(channel, "", handler.async());
                Object instance = this.injector.getInstance(clazz);
                Pair<MessengerChannel, MessengerHandlerInfo> rootHandler = new Pair<>(channel, handlerInfo);
                handlerInfo.setInstance(instance);

                Method handlerMethod = Arrays.stream(clazz.getMethods())
                        .filter(method -> method.isAnnotationPresent(MessengerPacketHandler.class))
                        .findFirst()
                        .orElseThrow(() -> {
                            throw new MessengerHandlerNotFoundException(String.format("No messenger handler found in handler %s (class %s)", channel.getName(), clazz.getName()));
                        });
                handlerInfo.setMethod(handlerMethod);
                handlerInfo.setReceivedPackets(handler.receivedPackets());
                handlerTree.setData(rootHandler);
                this.handlerMap.add(handlerTree);
            });
        });
    }

    public Injector getInjector() {
        return injector;
    }

    public MessengerChannelHandlerScanner getScanner() {
        return scanner;
    }

    public TreeNode<Pair<MessengerChannel, MessengerHandlerInfo>> getHandlerMap() {
        return handlerMap;
    }
}
