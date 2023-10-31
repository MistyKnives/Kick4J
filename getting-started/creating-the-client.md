# Creating the Client

Below will contain information on how to use the Kick4JBuilder class to help create a new Kick4J client.

## Creating

Here is a full example on creating the Kick4J instance.

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

If you would like to interact with the Kick API e.g. sending messages, deleting messages, voting on polls, etc. Then you must be logged into a [**kick.com**](https://kick.com/) account.

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

## Events

You can register multiple events via the Kick4JBuilder before creating the Kick4J instance.

**Note:** You can also register these events from the Kick4J instance before using `Kick4J#login()` (You must always register listeners before logging into the client):

```java
Kick4J client = ...
client.on(EventType, EventListener);
client.login();
```

There are 3 different ways of registering events as seen below:

```java
Kick4J client = Kick4JBuilder.builder()
        ...
        .on(EventType, EventListener)
        // OR
        .on(EventType, Lambda Expression)
        // OR
        .on(Event, Lambda Expression)
        .build();
```

**Example 1:**&#x20;

If you would like to register a specific Event to a specific Listener then this is for you. View the Example below on how to use it.

<pre class="language-java"><code class="lang-java"><strong>Kick4J client = Kick4JBuilder.builder()
</strong>        ...
        .on(EventType.READY, new Kick4JListener)
        .build();
</code></pre>

#### **Example 2:**&#x20;

If you would like to specify the EventType but use a lambda expression then this method is for you.

```java
Kick4J client = Kick4JBuilder.builder()
        ...
        .on(EventType.READY, (ReadyEvent event) -> {
           System.out.printf("Hello %s%n!", event.getClient().user.getUsername())
        }
        .build();
```

#### **Example 3:**&#x20;

If you would like to specify the Event and directly use a lambda then here is an example:

```java
Kick4J client = Kick4JBuilder.builder()
        ...
        .on(ReadyEvent.class, (event) -> {
           System.out.printf("Hello %s%n!", event.getClient().user.getUsername())
        }
        .build();
```

Example 2 and 3 are very similar but we wanted to give the user different ways of registering the events.
