package uk.co.mistyknives.kick4j.events.impl.channel;

import lombok.Getter;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.Event;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

import java.time.Instant;
import java.util.UUID;

/**
 * Project: Kick4J
 * <br>
 * Copyright MistyKnives © 2022-2023
 * <br>
 * ---------------------------------------
 * <br>
 * All Projects are located on my GitHub
 * <br>
 * Please provide credit where due :)
 * <br>
 * ---------------------------------------
 * <br>
 * https://github.com/MistyKnives
 */
@Getter
public class ChannelSubscriptionEvent extends Event {

    private final int[] userIds;

    private final EventChannel gifter;

    private final EventChannel channel;

    public ChannelSubscriptionEvent(Kick4J client, int[] userIds, EventChannel gifter, EventChannel channel) {
        super(client, UUID.randomUUID(), Instant.now());

        this.userIds = userIds;
        this.gifter = gifter;
        this.channel = channel;
    }
}
