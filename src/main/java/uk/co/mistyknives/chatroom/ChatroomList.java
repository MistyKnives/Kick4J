package uk.co.mistyknives.chatroom;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import uk.co.mistyknives.user.User;

import java.util.List;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChatroomList {

    private List<Chatroom> chatrooms;
}
