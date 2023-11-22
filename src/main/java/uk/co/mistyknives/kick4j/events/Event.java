package uk.co.mistyknives.kick4j.events;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;
import lombok.Getter;

import uk.co.mistyknives.kick4j.Kick4J;

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
public abstract class Event {


    @Getter @JsonIgnore @Setter
    public Kick4J client;

    @Getter
    private UUID eventId;

    @Getter
    private Instant createdAt;

    public Event() {}

    public Event(Kick4J client, UUID eventId, Instant createdAt) {
        this.client = client;
        this.eventId = eventId;
        this.createdAt = createdAt;
    }
}
