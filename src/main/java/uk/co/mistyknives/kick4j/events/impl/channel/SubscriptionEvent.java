package uk.co.mistyknives.kick4j.events.impl.channel;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@JsonClass(exposeAll = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionEvent extends Event {

    @JsonProperty("chatroom_id")
    private int chatroomId;

    private String username;

    private int months;

    public SubscriptionEvent(Kick4J client, int chatroomId, String username, int months) {
        super(client, UUID.randomUUID(), Instant.now());

        this.chatroomId = chatroomId;
        this.username = username;
        this.months = months;
    }
}
