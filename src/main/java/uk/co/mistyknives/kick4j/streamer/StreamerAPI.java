package uk.co.mistyknives.kick4j.streamer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.mistyknives.kick4j.util.Endpoints;
import uk.co.mistyknives.kick4j.util.SocialLinks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright MistyKnives © 2022-2023
 */
public class StreamerAPI {

    public StreamerList getStreamers(String... s) {
        List<Streamer> streamers = new ArrayList<>();
        for (String username : s) {
            streamers.add(this.getStreamer(username));
        }

        return (StreamerList) streamers;
    }
    public Streamer getStreamer(String s) {
        try {

            URL url = new URL(Endpoints.CHANNELS_ENDPOINT + s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            if(con.getResponseCode() != 200) {
                System.out.println(con.getResponseMessage());
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(con.getInputStream());

            JsonNode userNode = node.get("user");

            SocialLinks links = new SocialLinks(
                    userNode.get("instagram").asText(),
                    userNode.get("twitter").asText(),
                    userNode.get("youtube").asText(),
                    userNode.get("discord").asText(),
                    userNode.get("tiktok").asText(),
                    userNode.get("facebook").asText());
            Streamer response = new Streamer(
                    node.get("id").asText(),
                    node.get("user_id").asText(),
                    userNode.get("username").asText(),
                    node.get("slug").asText(),
                    node.get("followersCount").asText(),
                    userNode.get("bio").asText(),
                    userNode.get("profile_pic").asText(),
                    node.get("banner_image").get("url").asText(),
                    node.get("chatroom").get("created_at").asText(),
                    node.get("livestream").get("is_live").asText(),
                    node,
                    links);

            con.disconnect();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
