package uk.co.mistyknives.kick4j.api.chatrooms.payload;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class Banned {

    @JsonProperty("banned_user")
    private User bannedUser;

    @JsonProperty("banned_by")
    private User bannedBy;

    private Ban ban;

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {

        private int id;

        private String username;
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ban {

        private String reason;

        @JsonProperty("banned_at")
        private String bannedAt;

        private boolean permanent;

        @JsonProperty("expires_at")
        private String expiresAt;
    }
}
