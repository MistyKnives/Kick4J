# Kick4J
This is a simple and small project aimed at accessing Kick.com's API easily through Java to create projects from it.

## Getting Started
Just add this library to your project and our dependencies [lombok](https://projectlombok.org/) and its as simple as that.

## Features

# User
Directly access the User API from Kick.com's User Endpoint.
This method will return the User's ID, Username, Bio, Profile Picture, and Social Media Links.

```java
UserAPI api = new UserAPI();
User user = api.getUser("MistyKnives");
```

If you recieve an error when testing the User API then it's most likely an Invalid Username you have entered.

# Streamer
Directly access the Streamer API from Kick.com's Channel Endpoint.
This method will return the Streamers's ID, User ID, Username, Slug (Lowercase Username, Follower Count, Bio, Profile Picture, Banner URL, Created At Timestamp, Is Live, and Social Media Links.

```java
StreamerAPI api = new StreamerAPI();
Streamer streamer = api.getStreamer("MistyKnives");
```

If you recieve an error when testing the Streamer API then it's most likely an Invalid Username you have entered.

# Stream
Directly access the Stream API from Kick.com's Channel Endpoint.
This method will return the Stream's ID, Slug (Lowercase Session Title), Session Title, Is Live, Channel ID, Duration (Always returns 0 until Ended), Language, Is Mature, Viewer Count, Thumbnail, and Category.

```java
StreamAPI api = new StreamerAPI();
Stream stream = api.getStream("MistyKnives");
```

If you recieve an error when testing the Streamer API then it's most likely the Streamer is no longer live or you have entered an Invalid Username

# Chatroom
Directly access the Chatroom API from Kick.com's Channel Endpoint.
This method will return the Chatroom's ID, Chatable Type, Created At Timestamp, Updated At Timestamp, Chat Mode Old, Chat Mode, Slow Mode, Chatable ID.

```java
ChatroomAPI api = new ChatroomAPI();
Chatroom stream = api.getChatroom("MistyKnives");
```

# Errors
If you recieve any errors while testing my project, please dont be afraid to put them in the [Issues Section](https://github.com/MistyKnives/Kick4J/issues) as this will help me detect issues easier!
