package io.pieszku.messenger.api.test;

import io.pieszku.messenger.api.Messenger;
import io.pieszku.messenger.api.stereotype.MessengerHandler;
import io.pieszku.messenger.api.stereotype.MessengerPacketHandler;
import io.pieszku.messenger.api.stereotype.MessengerPacketReceived;
import io.pieszku.messenger.api.stereotype.MessengerPacketSender;

@MessengerHandler(channelName = "test_messenger", receivedPackets = {TestPacket.class}, async = true)
public class TestHandler {
    @MessengerPacketHandler(type = TestPacket.class)
    public void onHandle(@MessengerPacketSender Messenger messenger, @MessengerPacketReceived(callback = true) TestPacket packet){
        System.out.println("HANDLE");
    }
}
