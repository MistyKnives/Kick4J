package uk.co.mistyknives.kick4j.events.impl.chatroom;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.Event;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

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
public class ChatterBanEvent extends Event {

    private final EventChannel channel;

    private final int id;

    private final User banned;

    private final User moderator;

    private final String expiry;

    @Getter
    public static class User {

        private int id;

        private String username;

        private String slug;
    }

    public ChatterBanEvent(Kick4J client, EventChannel channel, int id, User banned, User moderator, String expiry) {
        super(client, UUID.randomUUID(), Instant.now());

        this.channel = channel;
        this.id = id;
        this.banned = banned;
        this.moderator = moderator;
        this.expiry = expiry;
    }
}
