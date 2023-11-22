package uk.co.mistyknives.kick4j.api.channels.payload;

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

    @JsonProperty("chatable_type")
    private String chatableType;

    @JsonProperty("channel_id")
    private int channelId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("chat_mode_old")
    private String chatModeOld;

    @JsonProperty("chat_mode")
    private String chatMode;

    @JsonProperty("slow_mode")
    private boolean slowMode;

    @JsonProperty("chatable_id")
    private int chatableId;

    @JsonProperty("followers_mode")
    private boolean followersMode;

    @JsonProperty("subscribers_mode")
    private boolean subscribersMode;

    @JsonProperty("emotes_mode")
    private boolean emotesMode;

    @JsonProperty("message_interval")
    private int messageInterval;

    @JsonProperty("following_min_duration")
    private int followingMinDuration;
}