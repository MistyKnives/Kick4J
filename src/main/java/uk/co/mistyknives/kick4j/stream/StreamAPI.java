package uk.co.mistyknives.kick4j.stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.mistyknives.kick4j.util.Endpoints;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright MistyKnives © 2022-2023
 */
public class StreamAPI {

    public StreamList getStreams(String... s) {
        List<Stream> streams = new ArrayList<>();
        for (String username : s) {
            streams.add(this.getStream(username));
        }

        return (StreamList) streams;
    }

    public Stream getStream(String s) {
        try {
            URL url = new URL(Endpoints.CHANNELS_ENDPOINT + s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (con.getResponseCode() != 200) {
                System.out.println(con.getResponseMessage());
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(con.getInputStream());

            if(node.get("livestream").asText().equalsIgnoreCase("null")) {
                System.out.println(s + " is not live!");
                return null;
            }

            JsonNode livestreamNode = node.get("livestream");
            JsonNode categoryNode = livestreamNode.get("categories").get(0);

            StreamCategory category = new StreamCategory(
                    categoryNode.get("id").asText(),
                    categoryNode.get("category_id").asText(),
                    categoryNode.get("name").asText(),
                    categoryNode.get("slug").asText(),
                    categoryNode.get("description").asText(),
                    categoryNode
            );

            Stream stream = new Stream(
                    livestreamNode.get("id").asText(),
                    livestreamNode.get("slug").asText(),
                    livestreamNode.get("session_title").asText(),
                    livestreamNode.get("is_live").asText(),
                    livestreamNode.get("channel_id").asText(),
                    livestreamNode.get("created_at").asText(),
                    livestreamNode.get("duration").asText(),
                    livestreamNode.get("language").asText(),
                    livestreamNode.get("is_mature").asText(),
                    livestreamNode.get("viewer_count").asText(),
                    livestreamNode.get("thumbnail").get("url").asText(),
                    category,
                    livestreamNode);

            con.disconnect();

            return stream;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
