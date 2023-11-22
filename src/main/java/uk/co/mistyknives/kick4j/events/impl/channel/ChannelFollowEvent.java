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
public class ChannelFollowEvent extends Event {

    private final EventChannel channel;

    private final EventChannel follower;

    private final int updatedFollowerCount;

    private final boolean userFollowing;

    public ChannelFollowEvent(Kick4J client, EventChannel channel, EventChannel follower, int updatedFollowerCount, boolean userFollowing) {
        super(client, UUID.randomUUID(), Instant.now());

        this.channel = channel;
        this.follower = follower;
        this.updatedFollowerCount = updatedFollowerCount;
        this.userFollowing = userFollowing;
    }
}
