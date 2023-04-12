package uk.co.mistyknives.kick4j.streamer.trees.profile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

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
public class BannerImage {

    public BannerImage() {
        super();
    }

    private String responsive, url;
}
