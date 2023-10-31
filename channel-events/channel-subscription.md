# Channel Subscription

## Description

This Event is triggered when someone subscribes to the Kick Channel.

## Example

```java
Kick4J client = ...
client.on(EventType.CHANNEL_SUBSCRIPTION, new YourEventListener());
// OR
client.on(ChannelSubscriptionEvent.class, event -> {
    // Your code
});
```

## Class

```java
public class ChannelSubscriptionEvent extends Event {

    private int[] userIds;

    private String username;

    private int channelId;
}
```

