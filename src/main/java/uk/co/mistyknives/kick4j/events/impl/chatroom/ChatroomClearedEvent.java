package uk.co.mistyknives.kick4j.events.impl.chatroom;

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
public class ChatroomClearedEvent extends Event {

    private final int id;

    public ChatroomClearedEvent(Kick4J client, int id) {
        super(client, UUID.randomUUID(), Instant.now());

        this.id = id;
    }
}
