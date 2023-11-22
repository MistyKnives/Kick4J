package uk.co.mistyknives.kick4j.api.users.interfaces;

import lombok.SneakyThrows;
import uk.co.mistyknives.kick4j.api.Cache;
import uk.co.mistyknives.kick4j.api.users.payload.Me;
import uk.co.mistyknives.kick4j.api.users.payload.User;

import java.util.List;

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
public interface IUserCache {

    /**
     * Fetches a Full User Object from the v1 API.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * User user = client.users.cache.find(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return The Full User Object.
     */
    @SneakyThrows
    User find(Object value);

    /**
     * Fetches a Full User Object from the v1 API with the User logged into the Client.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * Me me = client.users.cache.getMe();
     * }</pre>
     * @return The Full Me Object.
     */
    @SneakyThrows
    Me getMe();
}
