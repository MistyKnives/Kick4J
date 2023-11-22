package uk.co.mistyknives.kick4j.events.impl.chatroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.Event;

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
public class ChannelPollUpdatedEvent extends Event {

    private Poll poll;

    @Getter
    public static class Poll {

        private String title;

        private Option[] options;

        private int duration;

        private int remaining;

        @JsonProperty("result_display_duration")
        private int resultDisplayDuration;

        @Getter
        public static class Option {

            private int id;

            private String label;

            private int votes;
        }
    }

    public ChannelPollUpdatedEvent(Kick4J client, Poll poll) {
        super(client, UUID.randomUUID(), Instant.now());

        this.poll = poll;
    }
}
