package io.pieszku.messenger.api;

public enum MessengerType {
    REDIS(3000, "https://redis.io"),
    NATS(2000, "https://nats.io"),
    SOCKET(1000, "unknown");

    private final int timeoutConnection;
    private final String hostUrl;

    MessengerType(int timeoutConnection, String hostUrl) {
        this.timeoutConnection = timeoutConnection;
        this.hostUrl = hostUrl;
    }

    public int getTimeoutConnection() {
        return timeoutConnection;
    }

    public String getHostUrl() {
        return hostUrl;
    }
}
