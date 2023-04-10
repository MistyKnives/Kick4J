package uk.co.mistyknives.kick4j;

import uk.co.mistyknives.kick4j.chatroom.Chatroom;
import uk.co.mistyknives.kick4j.chatroom.ChatroomAPI;
import uk.co.mistyknives.kick4j.chatroom.ChatroomList;
import uk.co.mistyknives.kick4j.stream.Stream;
import uk.co.mistyknives.kick4j.stream.StreamAPI;
import uk.co.mistyknives.kick4j.stream.StreamList;
import uk.co.mistyknives.kick4j.streamer.Streamer;
import uk.co.mistyknives.kick4j.streamer.StreamerAPI;
import uk.co.mistyknives.kick4j.streamer.StreamerList;
import uk.co.mistyknives.kick4j.user.User;
import uk.co.mistyknives.kick4j.user.UserAPI;
import uk.co.mistyknives.kick4j.user.UserList;

/**
 * Copyright MistyKnives © 2022-2023
 */
public class Kick4J {

    private static final Kick4J api = new Kick4J();

    public static Kick4J getAPI() {
        return api;
    }
    
    public User getUser(String s) {
        return new UserAPI().getUser(s);
    }
    public UserList getUsers(String... s) {
        return new UserAPI().getUsers(s);
    }

    public Streamer getStreamer(String s) {
        return new StreamerAPI().getStreamer(s);
    }
    public StreamerList getStreamers(String... s) {
        return new StreamerAPI().getStreamers(s);
    }

    public Stream getStream(String s) {
        return new StreamAPI().getStream(s);
    }
    public StreamList getStreams(String... s) {
        return new StreamAPI().getStreams(s);
    }

    public Chatroom getChatroom(String s) {
        return new ChatroomAPI().getChatroom(s);
    }
    public ChatroomList getChatrooms(String... s) {
        return new ChatroomAPI().getChatrooms(s);
    }
}
