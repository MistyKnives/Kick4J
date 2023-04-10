package uk.co.mistyknives.stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import uk.co.mistyknives.chatroom.Chatroom;

import java.util.List;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class StreamList {

    private List<Stream> streams;
}
