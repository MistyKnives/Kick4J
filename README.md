# Kick4J
This is a simple and small project aimed at accessing Kick.com's API easily through Java to create projects from it.

# Getting Started
Just add this library to your project and our dependencies [lombok](https://projectlombok.org/) and its as simple as that.

## Features

# User
Directly access the User API from Kick.com's User Endpoint.
This method will return the User's ID, Username, Bio, Profile Picture, and Social Media Links.

## Get One User
```java
UserAPI api = new UserAPI();
User user = api.getUser("MistyKnives");
```

```java
User user = Kick4J.getAPI().getUser("MistyKnives");
```

## Getting Mulitple Users
```java
UserAPI api = new UserAPI();
UserList users = api.getUsers("MistyKnives", "YOUR NAME");
```

```java
UserList users = Kick4J.getAPI().getUsers("MistyKnives", "YOUR NAME");
```

If you recieve an error when testing the User API then it's most likely an Invalid Username you have entered.

# Streamer
Directly access the Streamer API from Kick.com's Channel Endpoint.
This method will return the Streamers's ID, User ID, Username, Slug (Lowercase Username), Follower Count, Bio, Profile Picture, Banner URL, Created At Timestamp, Is Live, and Social Media Links.

## Getting One Streamer
```java
StreamerAPI api = new StreamerAPI();
Streamer streamer = api.getStreamer("MistyKnives");
```

```java
Streamer streamer = Kick4J.getAPI().getStreamer("MistyKnives");
```

## Getting Multiple Streamers
```java
StreamerAPI api = new StreamerAPI();
StreamerList streamers = api.getStreamers("MistyKnives", "YOUR NAME");
```

```java
StreamerList streamers = Kick4J.getAPI().getStreamers("MistyKnives", "YOUR NAME");
```

If you recieve an error when testing the Streamer API then it's most likely an Invalid Username you have entered.

# Stream
Directly access the Stream API from Kick.com's Channel Endpoint.
This method will return the Stream's ID, Slug (Lowercase Session Title), Session Title, Is Live, Channel ID, Duration (Always returns 0 until Ended), Language, Is Mature, Viewer Count, Thumbnail, and Category.

## Getting One Stream
```java
StreamAPI api = new StreamAPI();
Stream stream = api.getStream("MistyKnives");
```

```java
Stream stream = Kick4J.getAPI().getStream("MistyKnives");
```

## Getting Multiple Streams
```java
StreamAPI api = new StreamAPI();
StreamList streams = api.getStreams("MistyKnives", "YOUR NAME");
```

```java
StreamList streams = Kick4J.getAPI().getStreams("MistyKnives", "YOUR NAME");
```

If you recieve an error when testing the Streamer API then it's most likely the Streamer is no longer live or you have entered an Invalid Username.

# Chatroom
Directly access the Chatroom API from Kick.com's Channel Endpoint.
This method will return the Chatroom's ID, Chatable Type, Created At Timestamp, Updated At Timestamp, Chat Mode Old, Chat Mode, Slow Mode, Chatable ID.

## Getting One Chatroom
```java
ChatroomAPI api = new ChatroomAPI();
Chatroom chatroom = api.getChatroom("MistyKnives");
```

```java
ChatroomList chatroom = Kick4J.getAPI().getChatroom("MistyKnives");
```

## Getting Multiple Chatrooms
```java
ChatroomAPI api = new ChatroomAPI();
ChatroomList chatrooms = api.getChatrooms("MistyKnives", "YOUR NAME");
```

```java
ChatroomList chatrooms = Kick4J.getAPI().getChatrooms("MistyKnives", "YOUR NAME");
```

If you recieve an error while testing the Chatroom API then it's most likely the user does not exist.

# Errors
If you recieve any errors while testing my project, please dont be afraid to put them in the [Issues Section](https://github.com/MistyKnives/Kick4J/issues) as this will help me detect issues easier!
