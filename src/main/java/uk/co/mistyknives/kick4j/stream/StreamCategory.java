package uk.co.mistyknives.kick4j.stream;

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
public class StreamCategory {

    private String id, categoryId, name, slug, description;

    private JsonNode rawResponse;
}
