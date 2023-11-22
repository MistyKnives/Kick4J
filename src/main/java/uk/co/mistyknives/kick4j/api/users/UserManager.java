package uk.co.mistyknives.kick4j.api.users;

import uk.co.mistyknives.kick4j.Kick4J;

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
public class UserManager {

    public final UserCache cache;

    public UserManager(Kick4J client) {
        this.cache = new UserCache(client);
    }
}
