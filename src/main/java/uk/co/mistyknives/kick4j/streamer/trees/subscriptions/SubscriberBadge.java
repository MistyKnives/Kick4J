package uk.co.mistyknives.kick4j.streamer.trees.subscriptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.Set;

/**
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
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class SubscriberBadge {

    public SubscriberBadge() {
        super();
    }

    private Integer id;

    @JsonProperty("channel_id")
    private Integer channelId;

    private Integer months;

    @JsonProperty("badge_image")
    private Set<SubscriberBadgeImage> badgeImage;
}
