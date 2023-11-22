package uk.co.mistyknives.kick4j;

import lombok.*;

import uk.co.mistyknives.kick4j.auth.AuthCredential;

import dev.failsafe.internal.util.Assert;
import uk.co.mistyknives.kick4j.auth.Token;
import uk.co.mistyknives.kick4j.events.Event;
import uk.co.mistyknives.kick4j.events.EventListener;
import uk.co.mistyknives.kick4j.events.EventType;
import uk.co.mistyknives.kick4j.util.Logger;

import java.util.*;
import java.util.function.Consumer;

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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Kick4JBuilder {

    private AuthCredential credential;

    private Logger loggerType = Logger.DEFAULT;

    private ArrayList<Integer> watching;

    private Map<EventType, List<Object>> listeners = new HashMap<>();

    /**
     * Start the process of helping in building Kick4J class.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        .credentials(new AuthCredential(username, password, OTP))
     *        .logType(Logger.DEBUG)
     *        .join(23455, 542334, 455345, 3312)
     *        .on(EventType.READY, new ReadyEvent())
     *        .build();
     * }</pre>
     * @return Kick4JBuilder
     */
    public static Kick4JBuilder builder() {
        return new Kick4JBuilder();
    }

    /**
     * Configure the Auth Credentials to make the Read-Only Kick4J client into a Read/Write Client with sending messages, sending events, etc.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        ...
     *        .credentials(new AuthCredential(username, password, OTP))
     *        .build();
     * }</pre>
     * @param credential AuthCredential
     * @return Kick4JBuilder
     */
    public Kick4JBuilder credentials(AuthCredential credential) {
        this.credential = credential;
        return this;
    }

    /**
     * Configure the Logger to either print Normal Messages or Normal & Debug Messages.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        ...
     *        .logType(Logger.DEBUG)
     *        .build();
     * }</pre>
     * @param type Logger Type
     * @return Kick4JBuilder
     */
    public Kick4JBuilder logType(Logger type) {
        this.loggerType = type;
        return this;
    }

    /**
     * Join channels directly from the Builder.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        ...
     *        .join(23455, 542334, 455345, 3312)
     *        .build();
     * }</pre>
     * @param ids List of Channel Ids.
     * @return Kick4JBuilder
     */
    public Kick4JBuilder join(Integer... ids) {
        this.watching = new ArrayList<>();
        Collections.addAll(this.watching, ids);
        return this;
    }

    /**
     * Add an event to Kick4J directly from the Builder.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        ...
     *        .on(EventType.READY, new ReadyListener())
     *        .build();
     * }</pre>
     * @param type EventType e.g. READY
     * @param listener EventListener class that contains all the methods from the Events
     * @return Kick4JBuilder
     */
    @SneakyThrows
    public Kick4JBuilder on(EventType type, Class<? extends EventListener> listener) {
        this.listeners.computeIfAbsent(type, k -> new LinkedList<>()).add(listener.newInstance());
        return this;
    }

    /**
     * Add an event to Kick4J directly from the Builder.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        ...
     *        .on(EventType.READY, (ReadyEvent event) -> console.log("Hello from Kick4J!"));
     *        .build();
     * }</pre>
     * @param type EventType e.g. READY
     * @param lambda Lambda Expression
     * @return Kick4JBuilder
     */
    public <T extends Event> Kick4JBuilder on(EventType type, Consumer<T> lambda) {
        this.listeners.computeIfAbsent(type, k -> new LinkedList<>()).add(lambda);
        return this;
    }

    /**
     * Add an event to Kick4J directly from the Builder.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        ...
     *        .on(ReadyEvent.class, (event) -> console.log("Hello from Kick4J!"));
     *        .build();
     * }</pre>
     * @param eventClass EventType e.g. READY
     * @param consumer Lambda Expression
     * @return Kick4JBuilder
     */
    public <T extends Event> Kick4JBuilder on(Class<T> eventClass, Consumer<T> consumer) {
        this.listeners.computeIfAbsent(EventType.getEventType(eventClass), k -> new LinkedList<>()).add(consumer);
        return this;
    }

    /**
     * Build a new Kick4J instance.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = Kick4JBuilder.builder()
     *        .credentials(new AuthCredential(username, password, OTP))
     *        .logType(Logger.DEBUG)
     *        .join(23455, 542334, 455345, 3312)
     *        .on(EventType.READY, new ReadyEvent())
     *        // Build the Kick4J client.
     *        .build();
     * }</pre>
     * @return Kick4J
     */
    @SneakyThrows
    public Kick4J build() {
        Token token;

        // If user provides credential information for the Client.
        if (this.credential != null) {
            Assert.isTrue(this.credential.username() != null, "[Auth] You must specify an email/username to login with.");
            Assert.isTrue(this.credential.password() != null, "[Auth] You must specify a password to login with.");

            Logger.logDebug(this.loggerType.equals(Logger.DEBUG), "[Auth] Generating token...");

            // Kick4J will become a client with read/write events, chat, etc.
            token = new Token(this.credential);
            String generatedToken = token.generate();

            if (generatedToken == null || generatedToken.length() < 0 || generatedToken.equalsIgnoreCase("")) {
                // Kick4J will only become read-only. not a client.
                Logger.logDebug(this.loggerType.equals(Logger.DEBUG), "[Auth] Generated Token is invalid.");
                token = null;
            }
        // Kick4J will only become read-only. not a client.
        } else token = null;

        Kick4J client = new Kick4J(token, this.loggerType != null && this.loggerType.equals(Logger.DEBUG), this.watching);
        this.listeners.forEach((type, listenersList) -> {
            List<Object> listeners = this.listeners.get(type);
            if(listeners != null) {
                for(Object listener : listeners) {
                    if (listener instanceof EventListener) client.on(type, ((EventListener) listener).getClass());
                    else if (listener instanceof Consumer) client.on(type, (Consumer<? extends Event>) listener);
                }
            }
        });
        return client;
    }


}
