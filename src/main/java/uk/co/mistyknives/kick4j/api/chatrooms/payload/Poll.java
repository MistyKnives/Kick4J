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
public class Poll {

    private String time;

    private int duration;

    @JsonProperty("result_display_duration")
    private int resultDisplayDuration;

    @JsonProperty("created_at")
    private String createdAt;

    private int remaining;

    @JsonProperty("has_voted")
    private boolean hasVoted;

    private Option[] options;

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Option {

        private int id;

        private String label;

        private int votes;
    }

}
