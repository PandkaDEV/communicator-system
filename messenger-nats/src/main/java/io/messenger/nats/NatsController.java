package io.messenger.nats;

import io.pieszku.messenger.api.Channel;
import io.pieszku.messenger.api.ControllerAPI;
import io.pieszku.messenger.api.ResponsePacket;
import io.pieszku.messenger.api.MessengerType;
import io.pieszku.messenger.api.test.TestPacket;
import io.pieszku.messenger.api.test.TestPacketResponse;

public class NatsController {

    private final Nats messenger;

    public NatsController() {
        this.messenger = new Nats();
        this.messenger.connect(MessengerType.NATS, "localhost", 4222, "");
        var api = new ControllerAPI(this.messenger, MessengerType.NATS, "io.messenger.nats.handler", new Channel("test_messenger"), new Channel("response_messenger"));
        this.messenger.sendRequestPacket("test_messenger",
                new TestPacket(),
                new ResponsePacket<TestPacketResponse>() {
                    @Override
                    public void accept(TestPacketResponse packet) {
                        System.out.println(packet.getText());
                    }
                });
    }
    public static void main(String[] args) {
        new NatsController();
    }
}
