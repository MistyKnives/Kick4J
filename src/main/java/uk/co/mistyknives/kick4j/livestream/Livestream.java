package uk.co.mistyknives.kick4j.livestream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import uk.co.mistyknives.kick4j.livestream.trees.LivestreamCategory;
import uk.co.mistyknives.kick4j.streamer.trees.categories.RecentCategories;
import uk.co.mistyknives.kick4j.streamer.trees.profile.BannerImage;

import java.util.List;
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
public class Livestream {

    public Livestream() {
        super();
    }

    private Integer id;

    private String slug;

    @JsonProperty("channel_id")
    private Integer channelId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("session_title")
    private String sessionTitle;

    @JsonProperty("is_live")
    private Boolean isLive;

    @JsonProperty("risk_level_id")
    private String riskLevelId;

    private String source;

    @JsonProperty("twitch_channel")
    private String twitchChannel;

    private Integer duration;

    private String language;

    @JsonProperty("is_mature")
    private Boolean isMature;

    private Set<BannerImage> thumbnail;

    private Integer viewers;

    private Set<LivestreamCategory> categories;

    private List<String> tags;

    public boolean isLive() {
        return isLive;
    }
}
