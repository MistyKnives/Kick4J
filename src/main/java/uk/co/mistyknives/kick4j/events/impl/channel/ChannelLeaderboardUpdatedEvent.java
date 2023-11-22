package uk.co.mistyknives.kick4j.events.impl.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
public class ChannelLeaderboardUpdatedEvent extends Event {

    private final EventChannel channel;

    private final EventChannel gifter;

    private final Leaderboard[] leaderboard;

    private final Leaderboard[] weeklyLeaderboard;

    private final Leaderboard[] monthlyLeaderboard;

    private final int giftedQuantity;

    @Getter
    @AllArgsConstructor
    public static class Leaderboard {

        @JsonProperty("user_id")
        private int userId;

        private String username;

        private int quantity;
    }

    public ChannelLeaderboardUpdatedEvent(Kick4J client, EventChannel channel, EventChannel gifter, Leaderboard[] leaderboard, Leaderboard[] weeklyLeaderboard, Leaderboard[] monthlyLeaderboard, int giftedQuantity) {
        super(client, UUID.randomUUID(), Instant.now());

        this.channel = channel;
        this.gifter = gifter;
        this.leaderboard = leaderboard;
        this.weeklyLeaderboard = weeklyLeaderboard;
        this.monthlyLeaderboard = monthlyLeaderboard;
        this.giftedQuantity = giftedQuantity;

    }
}
