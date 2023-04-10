package uk.co.mistyknives.streamer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class StreamerList {

    private List<Streamer> streamers;
}
