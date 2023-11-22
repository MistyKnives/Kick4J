package uk.co.mistyknives.kick4j.events.impl.data;

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
public class EventLivestream {

    private int id;

    @JsonProperty("channel_id")
    private int channelId;

    @JsonProperty("session_title")
    private String sessionTitle;

    private Object source;

    @JsonProperty("created_at")
    private String createdAt;
}
