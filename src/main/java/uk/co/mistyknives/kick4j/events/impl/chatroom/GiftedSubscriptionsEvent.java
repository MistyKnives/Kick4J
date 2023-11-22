package uk.co.mistyknives.kick4j.events.impl.chatroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.Event;

import java.time.Instant;
import java.util.List;
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
public class GiftedSubscriptionsEvent extends Event {

    @JsonProperty("chatroom_id")
    private int chatroomId;

    @JsonProperty("gifted_usernames")
    private List<String> giftedUsernames;

    @JsonProperty("gifter_username")
    private String gifterUsername;

    public GiftedSubscriptionsEvent(Kick4J client, int chatroomId, List<String> giftedUsernames, String gifterUsername) {
        super(client, UUID.randomUUID(), Instant.now());

        this.chatroomId = chatroomId;
        this.giftedUsernames = giftedUsernames;
        this.gifterUsername = gifterUsername;
    }
}
