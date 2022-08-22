package io.pieszku.messenger.api;

import com.scalified.tree.TreeNode;
import io.pieszku.messenger.api.exception.MessengerHandlerNotFoundException;
import io.pieszku.messenger.api.exception.MessengerHandlerParamsNotFoundException;
import io.pieszku.messenger.api.stereotype.*;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.stream.Collectors;

public class MessengerChannelHandlerExecutor extends MessengerChannelHandlerDispatcher implements MessengerChannelSubscriber {

    private final MessengerChannelHandlerMapper mapper;
    private final Messenger messenger;

    public MessengerChannelHandlerExecutor(Messenger messenger, MessengerHandlerInfo info, MessengerPacketHandler handler, MessengerChannelHandlerMapper mapper) {
        super(info.getDescription(), info, handler);
        this.messenger = messenger;
        this.mapper = mapper;
    }

    @Override
    public boolean onMessage(MessengerChannel channel, MessengerPacket packet) {
        MessengerHandlerInfo handlerInfo = this.findHandler(channel, packet);
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
                //code
       //         new ScheduledThreadPoolExecutor(1).schedule(())
            }
            handlerInfo.getMethod().invoke(handlerInfo.getInstance(), params);
        } catch (Exception e) {
            throw new MessengerHandlerParamsNotFoundException(String.format("Messenger handler not found paramas in class(%s) error %s", packet.getClass().getSimpleName(), e.getMessage()));
        }
        return false;
    }

    private MessengerHandlerInfo findHandler(MessengerChannel channel, MessengerPacket packet) {
        TreeNode<Pair<MessengerChannel, MessengerHandlerInfo>> handlerTree = this.mapper.getHandlerMap().subtrees().stream()
                .filter(handler -> handler.data().getValue0().getName().equals(channel.getName()))
                .filter(handler -> Arrays.stream(handler.data().getValue1().getReceivedPackets())
                        .collect(Collectors.toList())
                        .stream()
                        .anyMatch(clazz -> clazz.equals(packet.getClass())))
                .findFirst()
                .orElseThrow(() -> {
                    throw new MessengerHandlerNotFoundException(String.format("Not found %s in TreeNode", packet.getClass().getSimpleName()));
                });
        return handlerTree.data().getValue1();
    }

    private Object[] guessParams(MessengerHandlerInfo handler, MessengerPacket packet) {
        List<Object> params = new ArrayList<>();
        Arrays.stream(handler.getMethod().getParameterAnnotations()).flatMap(Arrays::stream).forEach(annotation -> {
            if (annotation.annotationType().equals(MessengerPacketSender.class)) {
                params.add(this.messenger);
            } else if (annotation.annotationType().equals(MessengerPacketReceived.class)) {
                MessengerPacketReceived received = (MessengerPacketReceived) annotation;
                if (received.callback() && packet instanceof MessengerRequestPacket) {
                    MessengerRequestPacket requestPacket = (MessengerRequestPacket) packet;
                    params.add(requestPacket);
                } else {
                    params.add(packet);
                }
            }else if(annotation.annotationType().equals(MessengerPacketArgument.class)){
                MessengerPacketArgument argument = (MessengerPacketArgument) annotation;
                if(argument.name().equalsIgnoreCase("callbackId")){
                    if (packet instanceof MessengerRequestPacket) {
                        MessengerRequestPacket requestPacket = (MessengerRequestPacket) packet;
                        params.add(requestPacket.getCallbackId());
                    }
                }
            }
        });
        return params.toArray();
    }

    public MessengerChannelHandlerMapper getMapper() {
        return mapper;
    }
}
