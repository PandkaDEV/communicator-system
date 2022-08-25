package io.messenger.nats.handler;

import io.pieszku.messenger.api.Messenger;
import io.pieszku.messenger.api.stereotype.*;
import io.pieszku.messenger.api.test.TestPacket;
import io.pieszku.messenger.api.test.TestPacketResponse;

@Handler(channelName = "test_messenger", receivedPackets = {TestPacket.class}, async = false)
public class TestHandler {
    @PacketHandler(type = TestPacket.class)
    public void onHandle(@PacketSender Messenger messenger, @PacketReceived(callback = true) TestPacket packet, @PacketArgument(name = "channelReply") String channelReply){
        System.out.println("HANDLE");
        TestPacketResponse responsePacket = new TestPacketResponse("KUPA");
        responsePacket.setReplyChannel(channelReply);
        messenger.reply(responsePacket);
    }
}
