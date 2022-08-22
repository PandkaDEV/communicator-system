package io.messenger.nats.handler;

import io.pieszku.messenger.api.Messenger;
import io.pieszku.messenger.api.TestPacketOne;
import io.pieszku.messenger.api.stereotype.*;

@MessengerHandler(channelName = "test_messenger", receivedPackets = {TestPacketOne.class}, async = true)
public class TestOneHandler {
    @MessengerPacketHandler(type = TestPacketOne.class)
    public void onHandleOne(@MessengerPacketSender Messenger messenger, @MessengerPacketReceived(callback = false) TestPacketOne packet){
        System.out.println(packet.getText());
    }
}
