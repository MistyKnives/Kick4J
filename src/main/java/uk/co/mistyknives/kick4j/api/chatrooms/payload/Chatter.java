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
public class Chatter {

    @Setter
    private int id;

    private String username;

    private String slug;

    @JsonProperty("profile_pic")
    private String profilePic;

    @JsonProperty("is_staff")
    private boolean isStaff;

    @JsonProperty("is_channel_owner")
    private boolean isChannelOwner;

    @JsonProperty("is_moderator")
    private boolean isModerator;

    @JsonProperty("following_since")
    private String followingSince;

    @JsonProperty("subscribed_for")
    private int subscribedFor;

    private Object banned;

    private Badge[] badges;

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Badge {
        private String type;

        private String text;

        private boolean active;
    }
}
