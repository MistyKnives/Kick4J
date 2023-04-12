package uk.co.mistyknives.kick4j.livestream.trees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import uk.co.mistyknives.kick4j.streamer.trees.categories.Category;
import uk.co.mistyknives.kick4j.streamer.trees.profile.BannerImage;

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
public class LivestreamCategory {

    public LivestreamCategory() {
        super();
    }

    private Integer id;

    @JsonProperty("category_id")
    private Integer categoryId;

    private String name, slug;

    private String[] tags;

    private String description;

    @JsonProperty("deleted_at")
    private String deletedAt;

    private Set<BannerImage> banner;

    private Set<Category> category;
}
