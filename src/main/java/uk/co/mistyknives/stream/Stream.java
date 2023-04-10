package uk.co.mistyknives.stream;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Stream {

    private String id, slug, sessionTitle, isLive, channelId, createdAt, duration, language, isMature, viewerCount, thumbnail;

    private StreamCategory category;

    private JsonNode rawResponse;
}
