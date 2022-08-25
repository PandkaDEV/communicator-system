package io.pieszku.messenger.api;

import com.google.inject.Injector;
import com.scalified.tree.TreeNode;
import com.scalified.tree.multinode.ArrayMultiTreeNode;
import io.pieszku.messenger.api.exception.HandlerNotFoundException;
import io.pieszku.messenger.api.stereotype.Handler;
import io.pieszku.messenger.api.stereotype.PacketHandler;
import org.javatuples.Pair;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ChannelHandlerMapper {

    private final ChannelHandlerScanner scanner;
    private final ChannelCache channelCache = ControllerAPI.getInstance().getChannelCache();
    private final TreeNode<Pair<Channel, HandlerInfo>> handlerMap = new ArrayMultiTreeNode<>(null);
    private final Injector injector;

    public ChannelHandlerMapper(ChannelHandlerScanner scanner, Injector injector) {
        this.scanner = scanner;
        this.injector = injector;
        this.mapHandlersByClassName();
    }
    private void mapHandlersByClassName(){
        this.scanner.getClasses().forEach(clazz -> {
            TreeNode<Pair<Channel, HandlerInfo>> handlerTree = new ArrayMultiTreeNode<>(null);
            Handler handler = clazz.getAnnotation(Handler.class);
            this.channelCache.findChannelByName(handler.channelName()).ifPresent(channel -> {
                HandlerInfo handlerInfo = new HandlerInfo(channel, "", handler.async());
                Object instance = this.injector.getInstance(clazz);
                Pair<Channel, HandlerInfo> rootHandler = new Pair<>(channel, handlerInfo);
                handlerInfo.setInstance(instance);

                Method handlerMethod = Arrays.stream(clazz.getMethods())
                        .filter(method -> method.isAnnotationPresent(PacketHandler.class))
                        .findFirst()
                        .orElseThrow(() -> {
                            throw new HandlerNotFoundException(String.format("No messenger handler found in handler %s (class %s)", channel.getName(), clazz.getName()));
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

    public ChannelHandlerScanner getScanner() {
        return scanner;
    }

    public TreeNode<Pair<Channel, HandlerInfo>> getHandlerMap() {
        return handlerMap;
    }
}
