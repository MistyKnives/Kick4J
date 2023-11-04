# Kick4J

# WIP NEW UPDATE. WILL HAVE A MUCH MORE FUNCTIONAL WAY OF ACCESSING DATA WITH EVENTS AND MUCH MORE.
https://docs.kick4j.com (New Update) - Will be released soon.
# Home

This is a simple and small project aimed at accessing Kick.com's API easily through Java to create projects from it.

# Getting Started
Just add this library to your project and our dependencies [lombok](https://projectlombok.org/), [Jackson API](https://github.com/FasterXML/jackson), [Selenium](https://www.selenium.dev/), and [JSoup](https://jsoup.org/) and its as simple as that.

## Features

# User
Directly access the User API from Kick.com's User Endpoint.
This method will return the User's ID, Username, Bio, Profile Picture, and a few more variables. 

## Get One User
```java
UserAPI api = new UserAPI();
User user = api.getUser("MistyKnives");
```

```java
User user = Kick4J.getInstance().getUser("MistyKnives");
```

## Getting Mulitple Users
```java
UserAPI api = new UserAPI();
UserList users = api.getUsers("MistyKnives", "YOUR NAME");
```

```java
UserList users = Kick4J.getInstance().getUsers("MistyKnives", "YOUR NAME");
```

If you recieve an error when testing the User API then it's most likely an Invalid Username you have entered.

# Streamer
Directly access the Streamer API from Kick.com's Channel Endpoint.
This method will return the Streamer's ID, Playback URL, Follow Count, Subscriber Badges, Banner, and more.

## Getting One Streamer
```java
StreamerAPI api = new StreamerAPI();
Streamer streamer = api.getStreamer("MistyKnives");
```

```java
Streamer streamer = Kick4J.getInstance().getStreamer("MistyKnives");
```

## Getting Multiple Streamers
```java
StreamerAPI api = new StreamerAPI();
StreamerList streamers = api.getStreamers("MistyKnives", "YOUR NAME");
```

```java
StreamerList streamers = Kick4J.getInstance().getStreamers("MistyKnives", "YOUR NAME");
```

If you recieve an error when testing the Streamer API then it's most likely an Invalid Username you have entered.

# Livestream
Directly access the Livestream API from Kick.com's Channel Endpoint.
This method will return the Livestream's ID, Slug (Lowercase Session Title), Session Title, Is Live, Channel ID, Duration (Always returns 0 until Ended), Language, Is Mature, Viewer Count, Thumbnail, Category, and many more variables.

## Getting One Livestream
```java
LivestreamAPI api = new LivestreamAPI();
Livestream livestream = api.getLivestream("MistyKnives");
```

```java
Livestream livestream = Kick4J.getInstance().getLivestream("MistyKnives");
```

## Getting Multiple Livestreams
```java
LivestreamAPI api = new LivestreamAPI();
LivestreamList livestreams = api.getLivestreams("MistyKnives", "YOUR NAME");
```

```java
LivestreamList livestreams = Kick4J.getInstance().getLivestreams("MistyKnives", "YOUR NAME");
```

If you recieve an error when testing the Livestream API then it's most likely the Streamer is no longer live or you have entered an Invalid Username.

# Errors
If you recieve any errors while testing my project, please dont be afraid to put them in the [Issues Section](https://github.com/MistyKnives/Kick4J/issues) as this will help me detect issues easier!
