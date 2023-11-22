package uk.co.mistyknives.kick4j.events.impl.channel;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class ChannelSentRaidEvent extends Event {

    private EventChannel channel;

    private String slug;

    private Hosted hosted;

    public static class Hosted {
        @JsonProperty("id")
        private int id;

        private String username;

        private String slug;

        @JsonProperty("viewers_count")
        private int viewerCount;

        @JsonProperty("is_live")
        private boolean live;

        @JsonProperty("profile_pic")
        private String profilePic;

        private String category;

        @JsonProperty("preview_thumbnail")
        private Hosted.PreviewThumbnail previewThumbnail;

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PreviewThumbnail {
            private String src;

            @JsonProperty("srcset")
            private String srcSet;
        }
    }

    public ChannelSentRaidEvent(Kick4J client, EventChannel channel, String slug, Hosted hosted) {
        super(client, UUID.randomUUID(), Instant.now());

        this.channel = channel;
        this.slug = slug;
        this.hosted = hosted;
    }
}
