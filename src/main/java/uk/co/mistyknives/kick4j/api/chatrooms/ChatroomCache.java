package uk.co.mistyknives.kick4j.api.chatrooms;

import co.casterlabs.rakurai.json.element.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.api.Cache;
import uk.co.mistyknives.kick4j.api.channels.payload.Channel;
import uk.co.mistyknives.kick4j.api.chatrooms.interfaces.IChatroomCache;
import uk.co.mistyknives.kick4j.api.chatrooms.payload.Banned;
import uk.co.mistyknives.kick4j.api.chatrooms.payload.Chatroom;
import uk.co.mistyknives.kick4j.api.chatrooms.payload.Chatter;
import uk.co.mistyknives.kick4j.api.chatrooms.payload.Poll;
import uk.co.mistyknives.kick4j.exceptions.api.impl.NotFoundException;
import uk.co.mistyknives.kick4j.exceptions.api.impl.ForbiddenException;
import uk.co.mistyknives.kick4j.util.HttpRequest;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;

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
public class ChatroomCache extends Cache implements IChatroomCache {

    private final Kick4J client;

    public ChatroomCache(Kick4J client) {
        super(client);

        this.client = client;
    }

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
    @Override
    @SneakyThrows
    public Chatroom find(Object value) {
        if(this.isPublicAPI) return null;
        return this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/chatroom", VersionType.V2.toString(),
                    (value instanceof Number ? this.client.channels.cache.search((Integer) value).getSlug() : value))), Chatroom.class);
    }

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
    @Override
    @SneakyThrows
    public Chatter getChatter(Object channel, Object user) {
        if(this.isPublicAPI) return null;
        return this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/users/{2}", VersionType.V2.toString(),
                    (channel instanceof Number ? this.client.channels.cache.search((Integer) channel).getSlug() : channel),
                    (user instanceof Number ? this.client.channels.cache.search((Integer) user).getSlug() : user))), Chatter.class);
    }


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
    @Override
    @SneakyThrows
    public List<Banned> getBans(Object value) {
        if(this.isPublicAPI) return null;
        return (List<Banned>) this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/bans", VersionType.V2.toString(),
                        (value instanceof Number ? this.client.channels.cache.search((Integer) value).getSlug() : value))), Banned.class);
    }

    /**
     * Fetches a Full List of Banned Words from a Channel.
     * <br>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * List<String> bannedWords = client.chatrooms.cache.getBannedWords(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return List of banned words.
     */
    @Override
    @SneakyThrows
    public List<String> getBannedWords(Object value) {
        if (this.isPublicAPI) return null;
        String response = this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/chatroom/banned-words", VersionType.V2.toString(),
                    (value instanceof Number ? this.client.channels.cache.search((Integer) value).getSlug() : value))), String.class);

        JsonNode node = this.self.objectMapper.readTree(response);
        JsonNode words = node.get("data").get("words");
        List<String> wordsList = new ArrayList<>();

        for (JsonNode word : words) wordsList.add(word.asText());

        return wordsList;
    }

    /**
     * Fetches the Chatroom Rules from a Channel.
     * <br>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * String rules = client.chatrooms.cache.getChatroomRules(slug/id);
     * }</pre>
     * @param value Slug or ID.
     * @return Chatroom Rules.
     */
    @Override
    @SneakyThrows
    public String getChatroomRules(Object value) {
        if (this.isPublicAPI) return null;
        String response = this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/chatroom/rules", VersionType.INTERNAL_V1.toString(),
                    (value instanceof Number ? this.client.channels.cache.search((Integer) value).getSlug() : value))), String.class);

        JsonNode node = this.self.objectMapper.readTree(response);
        JsonNode rules = node.get("data").get("rules");

        return rules.asText();
    }

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
    @Override
    @SneakyThrows
    public Poll getPoll(Object value) {
        if (this.isPublicAPI) return null;

        Channel.V2 channel = this.client.channels.cache.find(value);

        String response = this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/polls",
                        VersionType.V2.toString(), channel.getSlug())), String.class);

        JsonNode node = this.self.objectMapper.readTree(response);
        JsonNode data = node.get("data");
        JsonNode poll = data.get("poll");

        if(poll == null) throw new NotFoundException("Poll Not Found For Channel '%s'".formatted(channel.getUser().getUsername()));
        return this.self.objectMapper.readValue(poll.asText(), Poll.class);
    }

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
    @Override
    @SneakyThrows
    public String voteForPoll(Object value, int option){
        if (this.isPublicAPI) return null;
        if (this.client.user == null) return "[Client] You are not logged into a client. You cannot use this.";

        Channel.V2 channel = this.client.channels.cache.find(value);
        JsonObject payload = new JsonObject().put("id", option);

        String response = this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/polls/vote", VersionType.V2.toString(), channel.getSlug()))
                .post(RequestBody.create(payload.toString().getBytes(StandardCharsets.UTF_8), MediaType.parse("application/json"))), String.class);
        JsonNode node = this.self.objectMapper.readTree(response);
        JsonNode statusNode = node.get("status");

        if(statusNode.get("code").asLong() == 404 && statusNode.get("message").asText().equalsIgnoreCase("Poll not found")) throw new NotFoundException("Poll Not Found For Channel '%s'".formatted(channel.getUser().getUsername()));
        if(statusNode.get("code").asLong() == 404) throw new NotFoundException(statusNode.get("message").asText());

        return response;
    }

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
    @Override
    @SneakyThrows
    public String ban(Object channel, Object user, String reason) {
        if (this.isPublicAPI) return null;
        if (this.client.user == null) return "[Client] You are not logged into a client. You cannot use this.";

        Channel.V2 searchedChannel = this.client.channels.cache.find(channel);
        Channel.V2 searchedUser = this.client.channels.cache.find(user);

        JsonObject payload = new JsonObject()
                .put("banned_username", searchedUser.getSlug())
                .put("permanent", true)
                .put("reason", reason);

        String response = this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/bans", VersionType.V2.toString(), searchedChannel.getSlug()))
                .post(RequestBody.create(payload.toString().getBytes(StandardCharsets.UTF_8), MediaType.parse("application/json"))), String.class);

        if (response.contains("<title>Forbidden</title>"))
            throw new ForbiddenException("%s is not modded in %s's chat. Please make sure %s is modded before attempting to ban a user.".formatted(this.client.user.getUsername(), searchedChannel.getUser().getUsername(), this.client.user.getUsername()));
        return response;
    }

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
    @Override
    @SneakyThrows
    public String unban(Object channel, Object user) {
        if (this.isPublicAPI) return null;
        if (this.client.user == null) return "[Client] You are not logged into a client. You cannot use this.";

        Channel.V2 searchedChannel = this.client.channels.cache.find(channel);
        Channel.V2 searchedUser = this.client.channels.cache.find(user);

        String response = this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/bans/{2}", VersionType.V2.toString(), searchedChannel.getSlug(), searchedUser.getSlug()))
                .delete(), String.class);

        if(response.contains("<title>Forbidden</title>")) throw new ForbiddenException("%s is not modded in %s's chat. Please make sure %s is modded before attempting to unbanning a user.".formatted(this.client.user.getUsername(), searchedChannel.getUser().getUsername(), this.client.user.getUsername()));
        return response;
    }

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
    @Override
    @SneakyThrows
    public String timeout(Object channel, Object user, String reason, int duration) {
        if (this.isPublicAPI) return null;
        if (this.client.user == null) return "[Client] You are not logged into a client. You cannot use this.";

        Channel.V2 searchedChannel = this.client.channels.cache.find(channel);
        Channel.V2 searchedUser = this.client.channels.cache.find(user);

        JsonObject payload = new JsonObject()
                .put("banned_username", searchedUser.getSlug())
                .put("permanent", false)
                .put("reason", reason)
                .put("duration", duration);

        String response = this.self.request(new Request.Builder()
                .url(MessageFormat.format("{0}/channels/{1}/bans", VersionType.V2.toString(), searchedChannel.getSlug()))
                .post(RequestBody.create(payload.toString().getBytes(StandardCharsets.UTF_8), MediaType.parse("application/json"))), String.class);

        if(response.contains("<title>Forbidden</title>")) throw new ForbiddenException("%s is not modded in %s's chat. Please make sure %s is modded before attempting to timeout a user.".formatted(this.client.user.getUsername(), searchedChannel.getUser().getUsername(), this.client.user.getUsername()));
        return response;
    }

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
    @Override
    @SneakyThrows
    public String untimeout(Object channel, Object user) {
        if (this.isPublicAPI) return null;
        return this.unban(channel, user);
    }

    /**
     * Delete a message in chat.
     * <br>
     * <strong>The Client logged in must be a moderator in the chat to do this.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.chatrooms.cache.untimeout(slug/id, slug/id);
     * }</pre>
     * @param channel Slug or ID.
     * @param messageId The ID of the message.
     * @return String (response)
     */
    @Override
    @SneakyThrows
    public String deleteMessage(Object channel, int messageId) {
        Channel.V2 searchedChannel = this.client.channels.cache.find(channel);

        return this.self.request(new Request.Builder()
                .url("https://kick.com/api/v2/chatrooms/" + searchedChannel.getChatroom().getId() + "/messages/" + messageId)
                .delete()
                .header("Accept", "application/json"), String.class);
    }
}