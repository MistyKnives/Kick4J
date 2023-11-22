package uk.co.mistyknives.kick4j.api.chatrooms.interfaces;

import java.io.*;
import java.util.*;

import lombok.SneakyThrows;
import uk.co.mistyknives.kick4j.api.chatrooms.payload.*;
import uk.co.mistyknives.kick4j.exceptions.api.impl.ForbiddenException;
import uk.co.mistyknives.kick4j.exceptions.api.impl.NotFoundException;

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
public interface IChatroomCache {

    /**
     * Fetches a Full Chatroom Object from the v2 API.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * Chatroom chatroom = client.chatrooms.cache.find(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return The Full Chatroom Object.
     */
    @SneakyThrows
    Chatroom find(Object value);

    /**
     * Fetches a Full Chatter Object from the v2 API.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * Chatter chatter = client.chatrooms.cache.getChatter(slug/id, slug/id);
     * }</pre>
     * @param channel Slug or ID.
     * @param user Slug or ID.
     * @return The Full Chatter Object.
     */
    @SneakyThrows
    Chatter getChatter(Object channel, Object user);

    /**
     * Fetches a Full List of Bans from a Channel.
     * <br>
     * <strong>The Client logged in must be a moderator in the chat to view this.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * List<Banned> banned = client.chatrooms.cache.getBans(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return List of bans.
     */
    @SneakyThrows
    List<Banned> getBans(Object value);

    /**
     * Fetches a Full List of Banned Words from a Channel.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * List<String> bannedWords = client.chatrooms.cache.getBannedWords(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return List of banned words.
     */
    @SneakyThrows
    List<String> getBannedWords(Object value) throws IOException;

    /**
     * Fetches the Chatroom Rules from a Channel.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * String rules = client.chatrooms.cache.getChatroomRules(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return Chatroom Rules.
     */
    @SneakyThrows
    String getChatroomRules(Object value) throws IOException;

    /**
     * Fetches the Poll from a Channel.
     * <br>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * Poll poll = client.chatrooms.cache.getPoll(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return Chatroom Rules.
     */
    @SneakyThrows
    Poll getPoll(Object value) throws IOException, NotFoundException;

    /**
     * Vote for a Poll.
     * <br>
     * <strong>Requires Login.</strong>
     * <br>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.chatrooms.cache.voteForPoll(slug/id, option);
     * }</pre>
     * @param value Slug or ID.
     * @param option Option Number
     * @return Chatroom Rules.
     */
    @SneakyThrows
    String voteForPoll(Object value, int option);

    /**
     * Ban a User from a Channel.
     * <br>
     * <strong>The Client logged in must be a moderator in the chat to do this.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.chatrooms.cache.ban(slug/id slug/id, reason);
     * }</pre>
     * @param channel Slug or ID.
     * @param user Slug or ID.
     * @param reason Reason of the ban.
     * @return String from response.
     */
    @SneakyThrows
    String ban(Object channel, Object user, String reason);

    /**
     * Unban a User from a Channel.
     * <br>
     * <strong>The Client logged in must be a moderator in the chat to do this.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.chatrooms.cache.unban(slug/id, slug/id);
     * }</pre>
     * @param channel Slug or ID.
     * @param user Slug or ID.
     * @return String from response.
     */
    @SneakyThrows
    String unban(Object channel, Object user);

    /**
     * Timeout a User from a Channel.
     * <br>
     * <strong>The Client logged in must be a moderator in the chat to do this.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.chatrooms.cache.timeout(slug/id, slug/id, reason, duration);
     * }</pre>
     * @param channel Slug or ID.
     * @param user Slug or ID.
     * @param reason Reason of the timeout.
     * @param duration How long the timeout is for.
     * @return String from response.
     */
    @SneakyThrows
    String timeout(Object channel, Object user, String reason, int duration);

    /**
     * Untimeout a User from a Channel.
     * <br>
     * <strong>The Client logged in must be a moderator in the chat to do this.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.chatrooms.cache.untimeout(slug/id, slug/id);
     * }</pre>
     * @param channel Slug or ID.
     * @param user Slug or ID.
     * @return String from response.
     */
    @SneakyThrows
    String untimeout(Object channel, Object user);

    /**
     * Delete a message in chat.
     * <br>
     * <strong>The Client logged in must be a moderator in the chat to do this.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.chatrooms.cache.deleteMessage(slug/id, slug/id);
     * }</pre>
     * @param channel Slug or ID.
     * @param messageId The ID of the message.
     * @return String (response)
     */
    @SneakyThrows
    String deleteMessage(Object channel, int messageId);
}
