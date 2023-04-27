package uk.co.mistyknives.kick4j.streamer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import uk.co.mistyknives.kick4j.livestream.Livestream;
import uk.co.mistyknives.kick4j.streamer.trees.categories.*;
import uk.co.mistyknives.kick4j.streamer.trees.profile.*;
import uk.co.mistyknives.kick4j.streamer.trees.subscriptions.*;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Streamer {

    public Streamer() {
        super();
    }

    private Integer id;

    @JsonProperty("user_id")
    private Integer userId;

    private String slug;

    @JsonProperty("is_banned")
    private boolean isBanned;

    @JsonProperty("playback_url")
    private String playbackUrl;

    @JsonProperty("name_updated_at")
    private String nameUpdatedAt;

    @JsonProperty("vod_enabled")
    private boolean vodEnabled;

    @JsonProperty("subscription_enabled")
    private boolean subscriptionEnabled;

    @JsonProperty("cf_rate_limiter")
    private String cfRateLimiter;

    @JsonProperty("followersCount")
    private Integer followCount;

    private Livestream livestream;

    @JsonProperty("subscriber_badges")
    private Set<SubscriberBadge> subscriberBadges;

    @JsonProperty("banner_image")
    private Set<BannerImage> bannerImage;

    @JsonProperty("recent_categories")
    private Set<RecentCategories> recentCategories;

    private String role;

    private boolean muted;

    @JsonProperty("offline_banner_image")
    private Set<OfflineBannerImage> offlineBannerImage;

    private String verified;
}
