package uk.co.mistyknives.kick4j.auth;

import org.jetbrains.annotations.NotNull;

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
public record AuthCredential(@NotNull String username, @NotNull String password, String oneTimePassword) {
    public AuthCredential(@NotNull String username, @NotNull String password) {
        this(username, password, "");
    }
}
