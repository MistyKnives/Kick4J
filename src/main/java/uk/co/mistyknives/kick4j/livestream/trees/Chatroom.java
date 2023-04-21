package uk.co.mistyknives.kick4j.livestream.trees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
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
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Chatroom {

    public Chatroom() {
        super();
    }

    private Integer id;

    @JsonProperty("chatable_type")
    private String chatableType;

    @JsonProperty("channelId")
    private Integer channelId;

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
    private Integer chatableId;

    @JsonProperty("followers_mode")
    private boolean followerMode;

    @JsonProperty("subscribers_mode")
    private boolean subscriberMode;

    @JsonProperty("emotes_mode")
    private boolean emoteMode;

    @JsonProperty("message_interval")
    private Integer messageInterval;

    @JsonProperty("following_min_duration")
    private Integer followingMinDuration;
}
