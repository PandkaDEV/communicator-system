package io.pieszku.messenger.api.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketArgument {

    /**
     * @deprecated Options choice: "channelReply" - used for example to nats-messenger, "callbackId" - used for example to redis-messenger
     */
    String name();
}
