package uk.co.mistyknives.kick4j.util;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor
public enum KickEndpoints {

    BASE("https://kick.com/api/v1"), USERS("%s/users".formatted(BASE.url)), CHANNELS("%s/channels".formatted(BASE.url));

    public final String url;

}
