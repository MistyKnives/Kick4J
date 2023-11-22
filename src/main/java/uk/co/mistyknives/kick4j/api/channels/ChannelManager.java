package uk.co.mistyknives.kick4j.api.channels;

import uk.co.mistyknives.kick4j.api.channels.payload.Channel;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;
import uk.co.mistyknives.kick4j.socket.KickSocket;
import uk.co.mistyknives.kick4j.util.Logger;
import uk.co.mistyknives.kick4j.Kick4J;

import uk.co.mistyknives.kick4j.events.impl.client.*;
import uk.co.mistyknives.kick4j.events.*;

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
public class ChannelManager {

    public final ChannelCache cache;

    private final Kick4J client;

    public ChannelManager(Kick4J client) {
        this.client = client;
        this.cache = new ChannelCache(client);
    }

    /**
     * Join a channel and watch events, etc.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * // Join the channel.
     * client.channels.join(12353);
     * }</pre>
     * @param id Channel ID.
     * @return Channel.V2
     */
    public Channel.V2 join(int id) {
        if(this.cache.isPublicAPI) return null;

        Channel.V2 channel = this.cache.find(id);
        EventChannel eventChannel = new EventChannel(channel.getId(), channel.getUserId(), channel.getFollowerCount(), channel.getSlug(), channel.isVerified());

        this.cache.subscribed.putIfAbsent(channel.getId(), channel);

        this.client.emit(EventType.CLIENT_JOINED_CHANNEL, new ClientJoinEvent(this.client, eventChannel));
        Logger.logDebug(this.client.isDebug(), "[Channel] Now Watching %s! (%s)".formatted(channel.getUser().getUsername(), channel.getId()));
        return channel;
    }

    /**
     * Part from a channel to no-longer watch events, etc.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * // Part the channel.
     * client.channels.part(12353);
     * }</pre>
     * @param id Channel ID.
     */
    public void part(int id) {
        Channel.V2 channel = this.cache.subscribed.get(id);
        if(channel == null) return;

        EventChannel eventChannel = new EventChannel(channel.getId(), channel.getUserId(), channel.getFollowerCount(), channel.getSlug(), channel.isVerified());

        this.cache.subscribed.remove(id);
        this.client.emit(EventType.CLIENT_PART_CHANNEL, new ClientPartEvent(this.client, eventChannel));

        KickSocket socket = this.client.socket;
        socket.unsubscribe("channel.%s".formatted(channel.getId()));
        socket.unsubscribe("chatrooms.%s.v2".formatted(channel.getChatroom().getId()));
        Logger.logDebug(this.client.isDebug(), "[Channel] No Longer Watching %s! (%s)".formatted(channel.getUser().getUsername(), channel.getId()));
    }
}
