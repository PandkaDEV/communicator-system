Description:
```This was created to make it easier when creating new messengers```
## HOW TO USE?
#### How to send packets or receive?

Default#send:
```java
this.messenger.send("channelNameReceived", new TestPacket());
```
Default#receive:
```java
@Handler(channelName = "channelNameReceived", receivedPackets = {TestPacket.class}, async = true)
public class TestHandler {
    @PacketHandler(type = TestPacket.class)
    public void onHandle(@PacketSender Messenger messenger, @PacketReceived(callback = false) TestPacket packet){
        System.out.println("HANDLE");
    }
}
```
Request#send:
```java
this.messenger.sendRequestPacket("test_messenger",
        new TestPacket(),
        (ResponsePacket<TestPacketResponse>) packet -> {
           System.out.println(packet.getText());
        });
```
Request#receive:
```java
@Handler(channelName = "test_messenger", receivedPackets = {TestPacket.class}, async = true)
public class TestHandler {
    @PacketHandler(type = TestPacket.class)
    public void onHandle(@PacketSender Messenger messenger, @PacketReceived(callback = true) TestPacket packet, @PacketArgument(name = "channelReply") String channelReply){
        System.out.println("HANDLE");
        TestPacketResponse responsePacket = new TestPacketResponse("KUPA");
        responsePacket.setReplyChannel(channelReply);
        messenger.reply(responsePacket);
    }
}
```


