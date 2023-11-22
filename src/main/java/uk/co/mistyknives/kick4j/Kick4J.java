package uk.co.mistyknives.kick4j;

import lombok.*;

import uk.co.mistyknives.kick4j.api.channels.*;
import uk.co.mistyknives.kick4j.api.chatrooms.*;
import uk.co.mistyknives.kick4j.api.users.*;
import uk.co.mistyknives.kick4j.api.users.payload.*;
import uk.co.mistyknives.kick4j.auth.*;
import uk.co.mistyknives.kick4j.events.*;
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelSubscriptionEvent;
import uk.co.mistyknives.kick4j.events.impl.client.ReadyEvent;
import uk.co.mistyknives.kick4j.socket.KickSocket;
import uk.co.mistyknives.kick4j.util.*;

import java.util.ArrayList;


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
public class Kick4J implements EventSub {

    public UserManager users;
    public ChannelManager channels;
    public ChatroomManager chatrooms;

    public Me user;

    public KickSocket socket;

    @Getter
    private final Token token;

    @Getter
    private final boolean debug;

    private final ArrayList<Integer> watchingIds;

    private Thread clientThread;

    /**
     * Create a new Instance of Kick4J.
     * <p>
     * <strong>WARNING: Do not attempt to spam the Kick API if you are not logged into a Client, it will spam you with errors from too many requests.</strong>
     * @param token Client's Token (null if Read-Only).
     * @param debug If logger is debug.
     * @param watchingIds The channels the Client will watch events from.
     */
    public Kick4J(Token token, boolean debug, ArrayList<Integer> watchingIds) {
        this.token = token;
        this.debug = debug;
        this.watchingIds = watchingIds;
    }

    /**
     * Login to Kick4J Client to start listening to events, etc.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * // Login to the Kick4J client.
     * client.login();
     * }</pre>
     */
    public void login() {
        this.clientThread = new Thread();
        this.clientThread.start();

        this.users = new UserManager(this);
        this.user = this.users.cache.getMe();

        Logger.logDebug(this.isDebug(), "[Auth] Now Logged in as %s!".formatted(this.user.getUsername()));

        this.channels = new ChannelManager(this);
        if(this.watchingIds != null && this.watchingIds.size() > -1) this.watchingIds.forEach(id -> this.channels.join(id));

        this.chatrooms = new ChatroomManager(this);

        this.socket = new KickSocket(this);
        this.socket.connect();

        this.emit(EventType.READY, new ReadyEvent(this));
    }

    /**
     * Logout from the Kick4J Client to stop listening to events, etc.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * // Logout from the Kick4J client.
     * client.close();
     * }</pre>
     * @throws InterruptedException Interrupting Threads.
     */
    public void close() throws InterruptedException {
        this.clientThread.interrupt();
        this.clientThread.join();

        this.socket.disconnect();
    }
}