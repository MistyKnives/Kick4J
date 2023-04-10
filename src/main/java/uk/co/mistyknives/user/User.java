package uk.co.mistyknives.user;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import uk.co.mistyknives.util.SocialLinks;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class User {

    private String id, username, bio, profilePicture;

    private JsonNode rawResponse;

    private SocialLinks socialLinks;
}
