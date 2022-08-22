package io.pieszku.messenger.api;

import org.nustaq.serialization.FSTConfiguration;

public class MessengerPacketSerializer {

    private static final FSTConfiguration fstConfiguration = FSTConfiguration.createDefaultConfiguration();

    public static byte[] encodePacket(MessengerPacket packet){
        return fstConfiguration.asByteArray(packet);
    }
    public static MessengerPacket decodePacket(byte[] bytes){
        return (MessengerPacket) fstConfiguration.asObject(bytes);
    }
}
