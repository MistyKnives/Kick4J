package uk.co.mistyknives.kick4j.api.channels.payload;

import co.casterlabs.rakurai.json.annotating.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
import lombok.*;

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
public class Livestream {

    private int id;

    private String slug;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("session_title")
    private String sessionTitle;

    private String language;

    @JsonProperty("is_mature")
    private boolean isMature;

    private int viewers;

    private Thumbnail thumbnail;

    private List<Category> categories;

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnail {
        private String url;
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Category {

        private int id;

        @JsonProperty("category_id")
        private int categoryId;

        private String name;

        private String slug;

        private List<String> tags;

        private String description;

        @JsonProperty("deleted_at")
        private String deletedAt;

        private int viewers;

        private CategoryInfo category;
    }

    @Getter
    public static class CategoryInfo {
        private int id;

        private String name, slug, icon;
    }
}