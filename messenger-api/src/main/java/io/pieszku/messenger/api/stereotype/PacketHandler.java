package io.pieszku.messenger.api.stereotype;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PacketHandler {
    Class<?> type();
}
