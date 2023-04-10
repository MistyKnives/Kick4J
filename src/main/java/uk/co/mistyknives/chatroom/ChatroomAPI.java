package uk.co.mistyknives.chatroom;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.mistyknives.streamer.Streamer;
import uk.co.mistyknives.streamer.StreamerList;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static uk.co.mistyknives.util.Endpoints.CHANNELS_ENDPOINT;

/**
 * Copyright MistyKnives © 2022-2023
 */
public class ChatroomAPI {

    public ChatroomList getChatrooms(String... s) {
        List<Chatroom> chatrooms = new ArrayList<>();
        for (String username : s) {
            chatrooms.add(this.getChatroom(username));
        }

        return (ChatroomList) chatrooms;
    }

    public Chatroom getChatroom(String s) {
        try {
            URL url = new URL(CHANNELS_ENDPOINT + s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (con.getResponseCode() != 200) {
                System.out.println(con.getResponseMessage());
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(con.getInputStream());

            JsonNode chatroomNode = node.get("chatroom");

            Chatroom response = new Chatroom(
                    chatroomNode.get("id").asText(),
                    chatroomNode.get("chatable_type").asText(),
                    chatroomNode.get("channel_id").asText(),
                    chatroomNode.get("created_at").asText(),
                    chatroomNode.get("updated_at").asText(),
                    chatroomNode.get("chat_mode_old").asText(),
                    chatroomNode.get("chat_mode").asText(),
                    chatroomNode.get("slow_mode").asText(),
                    chatroomNode.get("chatable_id").asText(),
                    chatroomNode);

            con.disconnect();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
