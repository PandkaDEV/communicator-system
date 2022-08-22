package io.messenger.nats;

import io.pieszku.messenger.api.MessengerControllerAPI;
import io.pieszku.messenger.api.MessengerType;
import io.pieszku.messenger.api.TestPacketOne;
import io.pieszku.messenger.api.test.TestPacket;

public class NatsMessengerController {

    private final NatsMessenger messenger;

    public NatsMessengerController() {
        this.messenger = new NatsMessenger();
        this.messenger.connect(MessengerType.NATS, "localhost", 4222, "");
        var api = new MessengerControllerAPI(this.messenger, MessengerType.NATS, "io.messenger.nats.handler");
        this.messenger.send("test_messenger", new TestPacket());
        this.messenger.send("test_messenger", new TestPacketOne("GOWNO"));
    }

    public static void main(String[] args) {
        new NatsMessengerController();
    }
}
