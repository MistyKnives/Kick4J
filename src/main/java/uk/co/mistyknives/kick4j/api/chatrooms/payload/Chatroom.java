package uk.co.mistyknives.kick4j.api.chatrooms.payload;

import co.casterlabs.rakurai.json.annotating.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

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
@JsonClass(exposeAll = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chatroom {

    private int id;

    @JsonProperty("pinned_message")
    private Object pinnedMessage;

    @JsonProperty("slow_mode")
    private SlowMode slowMode;

    @JsonProperty("subscribers_mode")
    private SubscribersMode subscribersMode;

    @JsonProperty("followers_mode")
    private FollowersMode followersMode;

    @JsonProperty("emotes_mode")
    private EmotesMode emotesMode;

    @JsonProperty("advanced_bot_protection")
    private AdvancedBotProtection advancedBotProtection;

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SlowMode {

        private boolean enabled;

        @JsonProperty("message_interval")
        private int messageInterval;
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubscribersMode {

        private boolean enabled;
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FollowersMode {

        private boolean enabled;

        @JsonProperty("min_duration")
        private int minDuration;
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmotesMode {

        private boolean enabled;
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AdvancedBotProtection {

        private boolean enabled;

        @JsonProperty("remaining_time")
        private int remainingTime;
    }
}
