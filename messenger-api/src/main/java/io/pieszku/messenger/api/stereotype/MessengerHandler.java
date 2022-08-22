package io.pieszku.messenger.api.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MessengerHandler {

    String channelName();
    Class<?>[] receivedPackets();
    boolean async() default false;
}
