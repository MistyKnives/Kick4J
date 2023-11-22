package uk.co.mistyknives.kick4j.api;

import lombok.AllArgsConstructor;

import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.util.HttpRequest;

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
public abstract class Cache {

    public final boolean isPublicAPI = false;

    public HttpRequest self;

    public Cache(Kick4J client) {
        this.self = new HttpRequest(client);
    }

    @AllArgsConstructor
    public enum VersionType {
        V1("https://kick.com/api/v1"),
        V2("https://kick.com/api/v2"),
        SEARCH("https://search.kick.com/collections/channel/documents/search"),
        INTERNAL_V1("https://kick.com/api/internal/v1"),
        INTERNAL_V2("https://kick.com/api/internal/v2");

        private final String url;

        @Override
        public String toString() {
            return this.url;
        }
    }

    public enum FindType { ID, SLUG }
}
