package uk.co.mistyknives.kick4j.events.impl.chatroom;

import lombok.Getter;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.Event;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ChatroomUpdatedEvent extends Event {

    private final int id;

    @JsonProperty("slow_mode")
    private final SlowMode slowMode;

    @JsonProperty("subscribers_mode")
    private final SubscribersMode subscribersMode;

    @JsonProperty("followers_mode")
    private final FollowersMode followersMode;

    @JsonProperty("emotes_mode")
    private final EmotesMode emotesMode;

    @JsonProperty("advanced_bot_protection")
    private final AdvancedBotProtection advancedBotProtection;

    @Getter
    public static class SlowMode {

        private boolean enabled;

        @JsonProperty("message_interval")
        private int messageInterval;
    }

    @Getter
    public static class SubscribersMode {

        private boolean enabled;
    }

    @Getter
    public static class FollowersMode {

        private boolean enabled;

        @JsonProperty("min_duration")
        private int minDuration;
    }

    @Getter
    public static class EmotesMode {

        private boolean enabled;
    }

    @Getter
    public static class AdvancedBotProtection {

        private boolean enabled;

        @JsonProperty("remaining_time")
        private int remainingTime;
    }

    public ChatroomUpdatedEvent(Kick4J client, int id, SlowMode slowMode, SubscribersMode subscribersMode, FollowersMode followersMode, EmotesMode emotesMode, AdvancedBotProtection advancedBotProtection) {
        super(client, UUID.randomUUID(), Instant.now());
        this.id = id;
        this.slowMode = slowMode;
        this.subscribersMode = subscribersMode;
        this.followersMode = followersMode;
        this.emotesMode = emotesMode;
        this.advancedBotProtection = advancedBotProtection;
    }
}
