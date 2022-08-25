package io.pieszku.messenger.api;

import com.scalified.tree.TreeNode;
import io.pieszku.messenger.api.exception.HandlerNotFoundException;
import io.pieszku.messenger.api.exception.HandlerParamsNotFoundException;
import io.pieszku.messenger.api.stereotype.PacketArgument;
import io.pieszku.messenger.api.stereotype.PacketHandler;
import io.pieszku.messenger.api.stereotype.PacketReceived;
import io.pieszku.messenger.api.stereotype.PacketSender;
import org.javatuples.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ChannelHandlerExecutor extends ChannelHandlerDispatcher implements ChannelSubscriber {

    private final ChannelHandlerMapper mapper;
    private final Messenger messenger;

    public ChannelHandlerExecutor(Messenger messenger, HandlerInfo info, PacketHandler handler, ChannelHandlerMapper mapper) {
        super(info.getDescription(), info, handler);
        this.messenger = messenger;
        this.mapper = mapper;
    }

    @Override
    public boolean onMessage(Channel channel, Packet packet) {
        HandlerInfo handlerInfo = this.findHandler(channel, packet);
        if (handlerInfo == null) {
            return false;
        }
        Object[] params = this.guessParams(handlerInfo, packet);
        if (handlerInfo.getMethod().getParameterCount() == 0) {
            return false;
        }
        if (params.length == 0) {
            System.out.println("PARAMS LENGTH ==0");
            return false;
        }
        if (params.length == 1 && params[0] == null) {
            return false;
        }
        try {
            if(handlerInfo.isAsync()){
                CompletableFuture.runAsync(() -> {
                    try {
                        handlerInfo.getMethod().invoke(handlerInfo.getInstance(), params);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new HandlerParamsNotFoundException(String.format("Messenger handler not found paramas in class(%s) error %s", packet.getClass().getSimpleName(), e.getMessage()));
                    }
                });
                return false;
            }
            handlerInfo.getMethod().invoke(handlerInfo.getInstance(), params);
        } catch (Exception e) {
            throw new HandlerParamsNotFoundException(String.format("Messenger handler not found paramas in class(%s) error %s", packet.getClass().getSimpleName(), e.getMessage()));
        }
        return false;
    }

    private HandlerInfo findHandler(Channel channel, Packet packet) {
        TreeNode<Pair<Channel, HandlerInfo>> handlerTree = this.mapper.getHandlerMap().subtrees().stream()
                .filter(handler -> handler.data().getValue0().getName().equals(channel.getName()))
                .filter(handler -> Arrays.stream(handler.data().getValue1().getReceivedPackets()).collect(Collectors.toList())
                        .stream()
                        .anyMatch(clazz -> clazz.equals(packet.getClass())))
                .findFirst()
                .orElseThrow(() -> {
                    throw new HandlerNotFoundException(String.format("Not found %s in TreeNode", packet.getClass().getSimpleName()));
                });
        return handlerTree.data().getValue1();
    }

    private Object[] guessParams(HandlerInfo handler, Packet packet) {
        List<Object> params = new ArrayList<>();
        Arrays.stream(handler.getMethod().getParameterAnnotations()).flatMap(Arrays::stream).forEach(annotation -> {
            if (annotation.annotationType().equals(PacketSender.class)) {
                params.add(this.messenger);
            } else if (annotation.annotationType().equals(PacketReceived.class)) {
                PacketReceived received = (PacketReceived) annotation;
                if (received.callback() && packet instanceof RequestPacket) {
                    RequestPacket requestPacket = (RequestPacket) packet;
                    params.add(requestPacket);
                } else {
                    params.add(packet);
                }
            }else if(annotation.annotationType().equals(PacketArgument.class)) {
                PacketArgument argument = (PacketArgument) annotation;
                if (packet instanceof RequestPacket) {
                    RequestPacket requestPacket = (RequestPacket) packet;
                    if (argument.name().equalsIgnoreCase("callbackId")) {
                        params.add(requestPacket.getCallbackId());
                    }
                    if (argument.name().equalsIgnoreCase("channelReply")) {
                        params.add(requestPacket.getReplyChannel());
                    }
                }
            }
        });
        return params.toArray();
    }

    public ChannelHandlerMapper getMapper() {
        return mapper;
    }
}
