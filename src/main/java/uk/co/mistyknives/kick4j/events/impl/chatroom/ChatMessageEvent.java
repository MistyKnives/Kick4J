package uk.co.mistyknives.kick4j.events.impl.chatroom;

import co.casterlabs.rakurai.json.annotating.JsonClass;
import co.casterlabs.rakurai.json.element.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.Event;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;
import uk.co.mistyknives.kick4j.util.HttpRequest;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

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
public class ChatMessageEvent extends Event {

    private final EventChannel channel;

    private final String message;

    private final int messageId;

    private final int chatroomId;

    private final Sender sender;

    private final Metadata metadata;

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sender {

        private int id;

        private String username;

        private String slug;

        private Identity identity;

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Identity {

            private String color;

            private Badge[] badges;

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Badge {
                private String type;

                private String text;

                private int count;
            }
        }
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Metadata {

        @JsonProperty("original_sender")
        private OriginalSender originalSender;

        @JsonProperty("original_message")
        private OriginalMessage originalMessage;

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class OriginalSender {

            private int id;

            private String username;
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class OriginalMessage {

            private String id;

            private String content;
        }
    }

    /**
     * Send a message into the same chatroom from the ChatMessageEvent.
     * <br>
     * <strong>Requires login.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * ChatMessageEvent event = ...
     * // lets say event.getContent() is "!hello"
     * event.send("hello!");
     * // Will print: hello!
     * }</pre>
     * @param content The message to want to send.
     * @return String (response)
     */
    @SneakyThrows
    public String send(String content) {
        if(this.client.getToken() == null) return "[Client] Not logged in. Please login before using this feature.";
        if(this.client.user == null) return "[Client] Not logged in. Please login before using this feature.";

        JsonObject payload = new JsonObject()
                .put("chatroom_id", this.chatroomId)
                .put("message", content);

        return new HttpRequest(this.client).request(new Request.Builder()
                .url("https://kick.com/api/v1/chat-messages")
                .post(RequestBody.create(payload.toString().getBytes(StandardCharsets.UTF_8), MediaType.parse("application/json")))
                .header("Accept", "application/json"), String.class);
    }

    /**
     * Reply to a message.
     * <br>
     * <strong>Requires login.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * ChatMessageEvent event = ...
     * // lets say event.getContent() is "!hello"
     * event.reply("hello to you too!");
     * // Will print: @user, hello to you too!
     * }</pre>
     * @param content The message to want to send.
     * @return String (response)
     */
    @SneakyThrows
    public String reply(String content) {
        if(this.client.getToken() == null) return "[Client] Not logged in. Please login before using this feature.";
        if(this.client.user == null) return "[Client] Not logged in. Please login before using this feature.";

        JsonObject payload = new JsonObject()
                .put("chatroom_id", this.chatroomId)
                .put("message", "@%s, %s".formatted(this.sender.getUsername(), content));

        return new HttpRequest(this.client).request(new Request.Builder()
                .url("https://kick.com/api/v1/chat-messages")
                .post(RequestBody.create(payload.toString().getBytes(StandardCharsets.UTF_8), MediaType.parse("application/json")))
                .header("Accept", "application/json"), String.class);
    }

    /**
     * Delete the sent message.
     * <br>
     * <strong>Requires login & Logged in user to be moderator in the chatroom.</strong>
     * <p>
     * Example Usage:
     * <pre>{@code
     * ChatMessageEvent event = ...
     * event.delete();
     * }</pre>
     * @return String (response)
     */
    @SneakyThrows
    public String delete() {
        if(this.client.getToken() == null) return "[Client] Not logged in. Please login before using this feature.";
        if(this.client.user == null) return "[Client] Not logged in. Please login before using this feature.";

        return new HttpRequest(this.client).request(new Request.Builder()
                .url("https://kick.com/api/v2/chatrooms/" + this.chatroomId + "/messages/" + this.messageId)
                .delete()
                .header("Accept", "application/json"), String.class);
    }

    public boolean isClient() {
        return this.sender.id == this.client.user.getId();
    }

    public ChatMessageEvent(Kick4J client, EventChannel channel, String message, int messageId, int chatroomId, Sender sender, Metadata metadata) {
        super(client, UUID.randomUUID(), Instant.now());

        this.channel = channel;
        this.message = message;
        this.messageId = messageId;
        this.chatroomId = chatroomId;
        this.sender = sender;
        this.metadata = metadata;
    }
}
