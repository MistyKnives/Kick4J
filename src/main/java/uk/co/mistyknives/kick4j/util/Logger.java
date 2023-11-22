package uk.co.mistyknives.kick4j.util;

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
public enum Logger {
    DEFAULT, DEBUG;

    public static String logDebug(boolean isDebug, String s) {
        if(!isDebug) return s;

        System.out.println(s);
        return s;
    }

    public static String log(String s) {
        System.out.println(s);
        return s;
    }
}
