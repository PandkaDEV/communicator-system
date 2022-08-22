package io.messenger.nats;

import io.pieszku.messenger.api.MessengerControllerAPI;
import io.pieszku.messenger.api.MessengerType;
import io.pieszku.messenger.api.test.TestPacket;

import java.util.stream.IntStream;

public class NatsMessengerController {

    private final NatsMessenger messenger;

    public NatsMessengerController() {
        this.messenger = new NatsMessenger();
        this.messenger.connect(MessengerType.NATS, "localhost", 4222, "");
        var api = new MessengerControllerAPI(this.messenger, MessengerType.NATS, "io.messenger.nats.handler");
        IntStream.range(1, 50000).forEach(i -> {
            System.out.println(i);
            this.messenger.send("test_messenger", new TestPacket());
      //      this.messenger.send("test_messenger", new TestPacketOne("GOWNO"));
        });
        System.out.println("PRZESLANO");
    }

    public static void main(String[] args) {
        new NatsMessengerController();
    }
}
