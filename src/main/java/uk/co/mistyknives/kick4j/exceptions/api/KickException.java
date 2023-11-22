package uk.co.mistyknives.kick4j.exceptions.api;

import lombok.Getter;

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
public class KickException extends Exception {

    private final Exception exception;

    private final int code;

    private final String responseBody;

    public KickException(Exception exception, int code, String responseBody) {
        super("An error occurred.");

        this.exception = exception;
        this.code = code;
        this.responseBody = responseBody;
    }
}
