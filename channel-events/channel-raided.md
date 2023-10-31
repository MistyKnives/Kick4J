# Channel Raided

## Description

This Event is triggered when a Kick Channel raids another Kick Channel.

## Example

```java
Kick4J client = ...
client.on(EventType.STREAMER_RAIDED, new YourEventListener());
// OR
client.on(ChatMovedToSupportedChannelEvent.class, event -> {
    // Your code
});
```

## Class

```java
public class ChatMovedToSupportedChannelEvent extends Event {

    private Channel channel;

    private String slug;

    private Hosted hosted;
}
```



| Paramater | Type   |        Description |
| --------- | ------ | -----------------: |
| channel   | Class  | The Raider Channel |
| slug      | String |  The Raider's Slug |
|           |        |                    |
