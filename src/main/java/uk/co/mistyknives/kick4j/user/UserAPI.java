package uk.co.mistyknives.kick4j.user;

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
public class UserAPI {

    public UserList getUsers(String... s) {
        List<User> users = new ArrayList<>();
        for (String username : s) {
            users.add(this.getUser(username));
        }

        return (UserList) users;
    }
    public User getUser(String s) {
        try {

            URL url = new URL(Endpoints.USERS_ENDPOINT + s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            if(con.getResponseCode() != 200) {
                System.out.println(con.getResponseMessage());
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(con.getInputStream());

            SocialLinks links = new SocialLinks(
                    node.get("instagram").asText(),
                    node.get("twitter").asText(),
                    node.get("youtube").asText(),
                    node.get("discord").asText(),
                    node.get("tiktok").asText(),
                    node.get("facebook").asText());
            User response = new User(
                    node.get("id").asText(),
                    node.get("username").asText(),
                    node.get("bio").asText(),
                    node.get("profilepic").asText(),
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
