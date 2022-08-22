package io.messenger.nats.handler;

import io.pieszku.messenger.api.Messenger;
import io.pieszku.messenger.api.stereotype.*;
import io.pieszku.messenger.api.test.TestPacket;

@MessengerHandler(channelName = "test_messenger", receivedPackets = {TestPacket.class}, async = true)
public class TestHandler {
    @MessengerPacketHandler(type = TestPacket.class)
    public void onHandle(@MessengerPacketSender Messenger messenger, @MessengerPacketReceived(callback = true) TestPacket packet, @MessengerPacketCallback long callbackId){
        System.out.println("HANDLE");
        System.out.println(callbackId);
    }
}
