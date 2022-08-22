package io.messenger.nats;

import io.pieszku.messenger.api.MessengerControllerAPI;
import io.pieszku.messenger.api.MessengerType;

public class NatsMessengerController {

    public NatsMessengerController() {
        var api = new MessengerControllerAPI(MessengerType.NATS, "io.messenger.nats.handler");
    }
}
