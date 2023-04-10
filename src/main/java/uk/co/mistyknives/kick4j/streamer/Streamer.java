package uk.co.mistyknives.kick4j.streamer;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import uk.co.mistyknives.kick4j.util.SocialLinks;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Streamer {

    private String id, userId, username, slug, followerCount, bio, profilePicture, bannerUrl, createdAt, isLive;

    private JsonNode rawResponse;

    private SocialLinks socialLinks;
}
