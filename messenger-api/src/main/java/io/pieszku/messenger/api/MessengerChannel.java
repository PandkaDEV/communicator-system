package io.pieszku.messenger.api;

public class MessengerChannel {

    private final String name;
    private long latestReceivedPacketTime;
    private int receivedCount;
    private int sendCount;

    public MessengerChannel(String name) {
        this.name = name;
        this.latestReceivedPacketTime = 0L;
        this.sendCount = 0;
        this.receivedCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getReceivedCount() {
        return receivedCount;
    }

    public int getSendCount() {
        return sendCount;
    }

    public long getLatestReceivedPacketTime() {
        return latestReceivedPacketTime;
    }

    public void setLatestReceivedPacketTime(long latestReceivedPacketTime) {
        this.latestReceivedPacketTime = latestReceivedPacketTime;
    }

    public void setReceivedCount(int receivedCount) {
        this.receivedCount = receivedCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }
    public void incrementReceivedCount(){
        this.receivedCount++;
    }
    public void incrementSendCount(){
        this.sendCount++;
    }
}
