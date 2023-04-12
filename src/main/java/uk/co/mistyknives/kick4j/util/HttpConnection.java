package uk.co.mistyknives.kick4j.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInput;
import java.net.HttpURLConnection;
import java.net.URL;

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
public class HttpConnection {

    public static HttpURLConnection getConnection(String s) {
        try {
            URL url = new URL(s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            con.connect();

            if(con.getResponseCode() != 200) {
                System.out.println("[Error] Could not connect to the HttpClient, please try again in a few minutes :)");
                System.out.println("[Error] Most likely being rate limited, screw you Cloudflare >:(");
                return null;
            }

            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getRawResponse(HttpURLConnection connection, Class clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            return mapper.readValue(connection.getInputStream(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getRawResponseFromNode(HttpURLConnection connection, Class clazz, String node) {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            JsonNode jsonNode = mapper.readTree(connection.getInputStream()).get(node);
            JsonParser parser = mapper.treeAsTokens(jsonNode);

            return mapper.readValue(parser, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
