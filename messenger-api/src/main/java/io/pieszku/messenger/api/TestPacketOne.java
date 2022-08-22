package io.pieszku.messenger.api;

public class TestPacketOne extends MessengerPacket{
    private final String text;

    public TestPacketOne(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
