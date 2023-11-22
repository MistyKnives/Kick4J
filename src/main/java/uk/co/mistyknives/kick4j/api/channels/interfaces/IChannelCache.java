package uk.co.mistyknives.kick4j.api.channels.interfaces;

import lombok.SneakyThrows;
import uk.co.mistyknives.kick4j.api.Cache;
import uk.co.mistyknives.kick4j.api.channels.payload.Channel;
import uk.co.mistyknives.kick4j.api.channels.payload.Livestream;
import uk.co.mistyknives.kick4j.api.channels.payload.Searched;
import uk.co.mistyknives.kick4j.exceptions.api.impl.NotFoundException;

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
public interface IChannelCache {

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
    @SneakyThrows
    Channel.V2 find(Object value);

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
    @SneakyThrows
    Searched search(int id);

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
    @SneakyThrows
    Livestream getLivestream(Object value);
}
