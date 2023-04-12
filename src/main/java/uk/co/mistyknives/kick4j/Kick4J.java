package uk.co.mistyknives.kick4j;

import lombok.Getter;
import uk.co.mistyknives.kick4j.livestream.Livestream;
import uk.co.mistyknives.kick4j.livestream.LivestreamAPI;
import uk.co.mistyknives.kick4j.livestream.LivestreamList;
import uk.co.mistyknives.kick4j.streamer.Streamer;
import uk.co.mistyknives.kick4j.streamer.StreamerAPI;
import uk.co.mistyknives.kick4j.streamer.StreamerList;
import uk.co.mistyknives.kick4j.user.UserAPI;
import uk.co.mistyknives.kick4j.user.User;
import uk.co.mistyknives.kick4j.user.UserList;
import uk.co.mistyknives.kick4j.util.HttpConnection;
import uk.co.mistyknives.kick4j.util.KickEndpoints;


/**
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
public class Kick4J {

    @Getter
    private static final Kick4J instance = new Kick4J();

    /**
     * Get information about a User e.g. ID, Socials, etc.
     * @param name The User's Username
     * @return User
     * @see uk.co.mistyknives.kick4j.user.UserAPI
     */
    public User getUser(String name) {
        return new UserAPI().getUser(name);
    }

    /**
     * Get information about Multiple Users e.g. ID, Socials, etc.
     * @param names Array of Usernames
     * @return UserList
     * @see uk.co.mistyknives.kick4j.user.UserAPI
     */
    public UserList getUser(String... names) {
        return new UserAPI().getUsers(names);
    }

    /**
     * Get information about a Streamer e.g. ID, Follower Count, Subscription Badges, and more.
     * @param name The User's Username
     * @return Streamer
     * @see uk.co.mistyknives.kick4j.streamer.StreamerAPI
     */
    public Streamer getStreamer(String name) {
        return new StreamerAPI().getStreamer(name);
    }

    /**
     * Get information about Multiple Streamers e.g. ID, Follower Count, Subscription Badges, and more.
     * @param names Array of Usernames
     * @return StreamerList
     * @see uk.co.mistyknives.kick4j.streamer.StreamerAPI
     */
    public StreamerList getStreamers(String... names) {
        return new StreamerAPI().getStreamers(names);
    }

    /**
     * Get information about Multiple Livestreams e.g. ID, Title, Viewer Count, and more.
     * @param name The User's Username
     * @return Livestream
     * @see uk.co.mistyknives.kick4j.livestream.LivestreamAPI
     */
    public Livestream getLivestream(String name) {
        return new LivestreamAPI().getLivestream(name);
    }

    /**
     * Get information about Multiple Livestreams e.g. ID, Title, Viewer Count, and more.
     * @param names Array of Usernames
     * @return LivestreamList
     * @see uk.co.mistyknives.kick4j.livestream.LivestreamAPI
     */
    public LivestreamList getLivestreams(String... names) {
        return new LivestreamAPI().getLivestreams(names);
    }
}
