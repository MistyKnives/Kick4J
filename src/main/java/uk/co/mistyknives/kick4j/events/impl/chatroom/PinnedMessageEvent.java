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
public class PinnedMessageEvent extends Event {

    private final Message message;

    private final String duration;

    @Getter
    public static class Message {

        private int id;

        @JsonProperty("chatroom_id")
        private int chatroomId;

        private String content;

        private String type;

        @JsonProperty("created_at")
        private String createdAt;

        private Sender sender;

        @Getter
        public static class Sender {

            private int id;

            private String username;

            private String slug;

            private Identity identity;

            @Getter
            public static class Identity {

                private String color;

                private Badge[] badges;

                @Getter
                public static class Badge {
                    private String type;

                    private String text;

                    private int count;
                }
            }
        }
    }

    public PinnedMessageEvent(Kick4J client, Message message, String duration) {
        super(client, UUID.randomUUID(), Instant.now());

        this.message = message;
        this.duration = duration;
    }
}
