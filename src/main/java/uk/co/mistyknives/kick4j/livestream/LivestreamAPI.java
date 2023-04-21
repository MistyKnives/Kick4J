package uk.co.mistyknives.kick4j.livestream;

import uk.co.mistyknives.kick4j.util.HttpConnection;
import uk.co.mistyknives.kick4j.util.KickEndpoints;

import java.util.ArrayList;
import java.util.List;

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
public class LivestreamAPI {

    /**
     * Get the JSON Data from the Kick API then transfer it into "Livestream" class for easy access
     * @param s Streamer's Username
     * @return Livestream
     * @see uk.co.mistyknives.kick4j.livestream.Livestream
     */
    public Livestream getLivestream(String s) {
        return (Livestream) HttpConnection.getRawResponseFromNode(KickEndpoints.CHANNELS.url + "/%s".formatted(s), "livestream", Livestream.class);
    }

    /**
     * Get the JSON Data for Multiple Streamers from the Kick API then transfer it into an array using LivestreamList to separate each "Livestream" class for easy access
     * @param s Array of Usernames
     * @return LivestreamList
     * @see uk.co.mistyknives.kick4j.livestream.LivestreamList
     */
    public LivestreamList getLivestreams(String... s) {
        List<Livestream> livestreams = new ArrayList<>();
        for (String username : s) {
            livestreams.add(this.getLivestream(username));
        }

        return (LivestreamList) livestreams;
    }
}
