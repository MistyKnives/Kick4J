package uk.co.mistyknives.kick4j.command;

import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.impl.chatroom.ChatMessageEvent;

import java.util.HashMap;
import java.util.Map;

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
public abstract class Command {

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUsage();

    public abstract Map<String, String> getOptions();

    public abstract void onCommand(ChatMessageEvent event, String[] args);
}
