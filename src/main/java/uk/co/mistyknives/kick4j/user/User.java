package uk.co.mistyknives.kick4j.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id;

    private String username;

    @JsonProperty("agreed_to_terms")
    private Boolean agreedToTerms;

    @JsonProperty("email_verified_at")
    private String emailVerifiedAt;

    private String bio, twitter, facebook, instagram, youtube, discord, tiktok;

    @JsonProperty("profile_pic")
    private String profilePicture;

    public User() {
        super();
    }
}
