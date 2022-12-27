Dependency:

MAVEN
```java
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.Piechuuu</groupId>
	    <artifactId>communicator-system</artifactId>
	    <version>fd3e2ea880</version>
	</dependency>
```

GRADLE
```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.Piechuuu:communicator-system:Tag'
	}
```

Description:
```This was created to make it easier when creating new messengers```
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
Queue#send:
```java
        List<String> texts = new ArrayList<>();
        IntStream.range(0, 1823).forEach(i -> {
            texts.add("x:" + i);
        });
        var queueData = new PacketQueueData<>(TestQueuePacket.class, controller.getMessenger(),
                "test_01", String.class, texts, 113, 200);
        queueData.start();
```
Queue#receive:
```java
@Handler(channelName = "test_01", receivedPackets = {TestQueuePacket.class}, async = true)
public class TestHandler {

    private int count = 0;

    @PacketHandler(type = TestQueuePacket.class)
    public void onHandle(@PacketSender Messenger messenger, @PacketReceived(callback = false) TestQueuePacket packet) {
        List<String> texts = PacketQueueSerializer.encode(packet);
        this.count += texts.size();
        System.out.println("RECEIVED: " + texts.size() + " entities, actual: " + count);
    }
}
```

