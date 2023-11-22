package uk.co.mistyknives.kick4j.api.users.payload;

import co.casterlabs.rakurai.json.annotating.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import uk.co.mistyknives.kick4j.api.channels.payload.Channel;

import java.util.Date;

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
@JsonClass(exposeAll = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Me {

    private int id;

    private String username, bio, country, state, city, instagram, tiktok, youtube, twitter, discord, facebook;

    @JsonProperty("agreed_to_terms")
    private boolean agreedToTerms;

    @JsonProperty("email_verified_at")
    private Date emailVerifiedAt;

    @JsonProperty("enable_live_notifications")
    private boolean enableLiveNotifications;

    @JsonProperty("enable_onscreen_live_notifications")
    private boolean enableOnScreenLiveNotifications;

    @JsonProperty("apple_id")
    private Object appleID;

    @JsonProperty("email_updated_at")
    private Date emailUpdatedAt;

    @JsonProperty("country_code")
    private Object countryCode;

    @JsonProperty("profilePic")
    private String profilePicture;

    private Object redirect;

    @JsonProperty("channel_can_be_updated")
    private boolean channelCanBeUpdated;

    @JsonProperty("is_live")
    private boolean live;

    private Object[] roles;

    @JsonProperty("streamer_channel")
    private Channel.V1 streamerChannel;
}
