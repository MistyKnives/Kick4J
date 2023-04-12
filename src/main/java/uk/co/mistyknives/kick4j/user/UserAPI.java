package uk.co.mistyknives.kick4j.user;

import uk.co.mistyknives.kick4j.util.HttpConnection;
import uk.co.mistyknives.kick4j.util.KickEndpoints;

import java.util.ArrayList;
import java.util.List;

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
public class UserAPI {

    /**
     * Get the JSON Data from the Kick API then transfer it into "User" class for easy access
     * @param s User's Username
     * @return User
     * @see uk.co.mistyknives.kick4j.user.User
     */
    public User getUser(String s) {
        return (User) HttpConnection.getRawResponse(HttpConnection.getConnection(KickEndpoints.USERS.url + "/" + s), User.class);
    }

    /**
     * Get the JSON Data for Multiple Users from the Kick API then transfer it into an array using UserList to separate each "User" class for easy access
     * @param s Array of Usernames
     * @return UserList
     * @see uk.co.mistyknives.kick4j.user.UserList
     */
    public UserList getUsers(String... s) {
        List<User> users = new ArrayList<>();
        for (String username : s) {
            users.add(this.getUser(username));
        }

        return (UserList) users;
    }
}
