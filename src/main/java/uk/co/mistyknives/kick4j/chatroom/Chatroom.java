package uk.co.mistyknives.kick4j.chatroom;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Chatroom {

    private String id, chatableType, channelId, createdAt, updatedAt, chat_mode_old, chat_mode, slowMode, chatableId;

    private JsonNode rawResponse;
}
