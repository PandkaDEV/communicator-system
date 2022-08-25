package io.pieszku.messenger.api;

import org.nustaq.serialization.FSTConfiguration;

public class PacketSerializer {

    private static final FSTConfiguration fstConfiguration = FSTConfiguration.createDefaultConfiguration();

    public static byte[] encodePacket(Packet packet){
        return fstConfiguration.asByteArray(packet);
    }
    public static Packet decodePacket(byte[] bytes){
        return (Packet) fstConfiguration.asObject(bytes);
    }
}
