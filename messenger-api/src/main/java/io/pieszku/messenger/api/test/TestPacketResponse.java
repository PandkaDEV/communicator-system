package io.pieszku.messenger.api.test;

import io.pieszku.messenger.api.RequestPacket;

public class TestPacketResponse extends RequestPacket {

    private final String text;

    public TestPacketResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
