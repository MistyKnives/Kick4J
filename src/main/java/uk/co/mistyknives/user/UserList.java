package uk.co.mistyknives.user;

import lombok.*;

import java.util.List;

/**
 * Copyright MistyKnives © 2022-2023
 */
@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserList {

    private List<User> users;
}
