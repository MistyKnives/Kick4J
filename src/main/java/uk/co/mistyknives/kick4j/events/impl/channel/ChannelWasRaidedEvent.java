package uk.co.mistyknives.kick4j.events.impl.channel;

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
public class ChannelWasRaidedEvent extends Event {

    private final EventChannel raider;

    /**
     * Event only has the "chatroomId" for the channel that has been raided.
     * <br>
     * Until Kick provides a way to search for chatrooms via chatroomId, i will not be able to provide information on the raided channel.
     */
    //private final EventChannel channel;

    private final int chatroomId;

    private final int transferredViewerCount;

    private final String optionalMessage;

    public ChannelWasRaidedEvent(Kick4J client, EventChannel raider, int chatroomId, int transferredViewerCount, String optionalMessage) {
        super(client, UUID.randomUUID(), Instant.now());

        this.raider = raider;
        this.chatroomId = chatroomId;
        this.transferredViewerCount = transferredViewerCount;
        this.optionalMessage = optionalMessage;
    }
}
