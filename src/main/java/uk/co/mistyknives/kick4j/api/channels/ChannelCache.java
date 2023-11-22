package uk.co.mistyknives.kick4j.api.channels;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import okhttp3.Request;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.api.Cache;
import uk.co.mistyknives.kick4j.api.channels.payload.Channel;
import uk.co.mistyknives.kick4j.api.channels.payload.Livestream;
import uk.co.mistyknives.kick4j.api.channels.payload.Searched;
import uk.co.mistyknives.kick4j.api.channels.interfaces.IChannelCache;
import uk.co.mistyknives.kick4j.exceptions.api.impl.NotFoundException;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;

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
public class ChannelCache extends Cache implements IChannelCache {

    public final HashMap<Integer, Channel.V2> subscribed = new HashMap<>();

    public final Kick4J client;

    public ChannelCache(Kick4J client) {
        super(client);

        this.client = client;
    }

    /**
     * Fetches a Full Channel Object from the v2 API.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * Channel.V2 channel = client.channels.cache.find(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return The Full Channel Object.
     */
    @Override
    @SneakyThrows
    public Channel.V2 find(Object value){
        if(this.isPublicAPI) return null;
        return this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}", VersionType.V2.toString(),
                (value instanceof Number ? this.search((Integer) value).getSlug() : value))), Channel.V2.class);
    }

    /**
     * Search Kick for a channel via Channel ID and get a return of a full Searched Object.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * Searched search = client.channels.cache.search(id);
     * }</pre>
     *
     * @param id Channel ID
     * @return The Full Searched Object.
     */
    @Override
    @SneakyThrows
    public Searched search(int id) {
        if(this.isPublicAPI)
            return null;
        return this.self.request(new Request.Builder()
                .url("https://search.kick.com/collections/channel/documents/search?q=*&collections=channels&preset=channel_list&filter_by=id:" + id)
                .header("X-TYPESENSE-API-KEY", "nXIMW0iEN6sMujFYjFuhdrSwVow3pDQu"),
                Searched.class);
    }

    /**
     * Fetches a Full Livestream Object from the v2 API.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * Livestream livestream = client.channels.cache.getLivestream(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return The Full Livestream Object.
     */
    @Override
    @SneakyThrows
    public Livestream getLivestream(Object value) {
        if(this.isPublicAPI) return null;
        String response = this.self.requestEmpty(new Request.Builder().url(MessageFormat.format("{0}/channels/{1}/livestream", VersionType.V2.toString(),
                (value instanceof Number ? this.search((Integer) value).getSlug() : value))));
        JsonNode node = this.self.objectMapper.readTree(response);

        return this.self.objectMapper.readValue(node.get("data").asText(), Livestream.class);
    }
}