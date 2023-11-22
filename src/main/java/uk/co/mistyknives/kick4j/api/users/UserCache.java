package uk.co.mistyknives.kick4j.api.users;

import lombok.SneakyThrows;
import okhttp3.Request;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.api.Cache;
import uk.co.mistyknives.kick4j.api.users.interfaces.IUserCache;
import uk.co.mistyknives.kick4j.api.users.payload.*;

import java.text.MessageFormat;
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
public class UserCache extends Cache implements IUserCache {

    public final Kick4J client;

    public UserCache(Kick4J client) {
        super(client);

        this.client = client;
    }

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
    @Override
    @SneakyThrows
    public User find(Object value) {
        if(this.isPublicAPI) return null;
        return this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/users/{1}", VersionType.V1.toString(),
                (value instanceof Number ? this.client.channels.cache.search((Integer) value).getSlug() : value))),
                User.class);
    }

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
    @Override
    @SneakyThrows
    public Me getMe() {
        if(this.isPublicAPI) return null;
        return this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/user", VersionType.V1.toString())),
                Me.class);
    }
}