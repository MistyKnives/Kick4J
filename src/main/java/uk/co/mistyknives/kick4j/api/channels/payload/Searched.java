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
public class Searched {

    private String slug, username;

    private int id;

    @JsonProperty("followers_count")
    private int followerCount;

    @JsonProperty("is_banned")
    private boolean isBanned;

    @JsonProperty("is_live")
    private boolean isLive;

    private boolean verified;
}
