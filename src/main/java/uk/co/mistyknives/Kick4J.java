package uk.co.mistyknives;
import uk.co.mistyknives.chatroom.ChatroomAPI;
import uk.co.mistyknives.stream.StreamAPI;
import uk.co.mistyknives.streamer.StreamerAPI;
import uk.co.mistyknives.user.UserAPI;

/**
 * Copyright MistyKnives © 2022-2023
 */
public class Kick4J {

    public static void main(String[] args) {
        System.out.println("---------- stream api ----------");
        System.out.println(new StreamAPI().getStream("Loochy"));
        System.out.println("---------- streamer api ----------");
        System.out.println(new StreamerAPI().getStreamer("Loochy"));
        System.out.println("---------- chatroom api ----------");
        System.out.println(new ChatroomAPI().getChatroom("Loochy"));
        System.out.println("---------- user api ----------");
        System.out.println(new UserAPI().getUser("Loochy"));
    }
}
