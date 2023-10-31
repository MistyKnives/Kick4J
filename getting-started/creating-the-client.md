---
description: >-
  Below will contain information on how to use the Kick4JBuilder class to help
  create a new Kick4J client.
---

# Creating the Client

## Creating

```java
Kick4J client = Kick4JBuilder.builder()
        .credentials(AuthCredential)
        .logType(Logger)
        .join(Integer...)
        .on(EventType, EventListener)
        .on(Event, Lambda Expression)
        .build();
```

## Credentials

If you would like to interact with the Kick API e.g. sending messages, deleting messages, voting on polls, etc. Then you must be logged into a **kick.com** account.

**Note:** Most Kick accounts require 2FA now which is why OTP is optional. Here is the process on using 2FA with Kick4J.

```java
Kick4J client = Kick4JBuilder.builder()
        ...
        .credentials(new AuthCredential(username, password, OTP))
        // OR
        .credentials(new AuthCredential(username, password))
        .build();
```

## Log Type

Kick4J prints various different logs into the System Console. We have two different types of logs `DEFAULT` and `DEBUG`. Depending on which one you pick, those will be what prints into the console.

```java
Kick4J client = Kick4JBuilder.builder()
        ...
        .logType(Logger.DEFAULT)
        // OR
        .logType(Logger.DEBUG)
        .build();
```

## Joining a Kick Channel

Joining a Channel will start firing events to our EventSub which you can listen for and then interact with the data you receive to create some cool things!

You can either join via the KicK4JBuilder or the Kick4J client itself.

```java
Kick4J client = Kick4JBuilder.builder()
        ...
        .join(Integer...)
        .build();
        
// OR
Kick4J client = ...
client.channels.join(Integer);
```
