package uk.co.mistyknives.kick4j.socket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import lombok.SneakyThrows;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.api.channels.payload.Channel;
import uk.co.mistyknives.kick4j.api.users.payload.User;
import uk.co.mistyknives.kick4j.events.EventType;
import uk.co.mistyknives.kick4j.events.impl.channel.*;
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelSubscriptionEvent;
import uk.co.mistyknives.kick4j.events.impl.chatroom.*;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;
import uk.co.mistyknives.kick4j.events.impl.data.EventLivestream;
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelSentRaidEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project: Kick4J
 * <br>
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
public class KickSocket {

    private boolean isConnected = false;

    private final Kick4J client;

    private final ExecutorService pusherExecutor = Executors.newSingleThreadExecutor();

    public final Pusher pusherClient;

    public KickSocket(Kick4J client) {
        this.client = client;

        String PUSHER_CLUSTER = "us2", PUSHER_KEY = "eb1d5f283081a78b932c";
        PusherOptions options = new PusherOptions().setCluster(PUSHER_CLUSTER);
        pusherClient = new Pusher(PUSHER_KEY, options);
    }

    public void connect() {
        pusherExecutor.execute(() -> {

            pusherClient.connect(new ConnectionEventListener() {
                @Override public void onConnectionStateChange(ConnectionStateChange change) {
                    if(change.getCurrentState().equals(ConnectionState.CONNECTED)) isConnected = true;
                }
                @Override public void onError(String s, String s1, Exception e) {
                    isConnected = false;
                }
            }, ConnectionState.ALL);

            client.channels.cache.subscribed.forEach((id, channel) -> {
                this.subscribe("channel.%s".formatted(channel.getId()));
                this.subscribe("chatrooms.%s.v2".formatted(channel.getChatroom().getId()));

                //client.emit(EventType.CLIENT_JOINED_CHANNEL, new ClientJoinEvent(client, channel));
            });

            Runtime.getRuntime().addShutdownHook(new Thread(this::disconnect));
        });
    }

    public void disconnect() {
        pusherClient.disconnect();
        pusherExecutor.shutdownNow();
    }

    public void subscribe(String s) {
        pusherClient.subscribe(s).bindGlobal(this::onEvent);
    }

    public void unsubscribe(String s) {
        pusherClient.unsubscribe(s);
    }

    public boolean isActive() {
        return this.isConnected;
    }

    @SneakyThrows
    public void onEvent(PusherEvent pusherEvent) {
        ObjectMapper mapper = new ObjectMapper();

        String type = pusherEvent.getEventName();
        String data = pusherEvent.getData();

        switch(type) {
            // Channel Updates

            // ChannelWentLiveEvent
            case "App\\Events\\StreamerIsLive" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("livestream").get("channel_id").asInt());

                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                EventLivestream livestream = mapper.treeToValue(result.get("livestream"), EventLivestream.class);
                client.emit(EventType.CHANNEL_WENT_LIVE, new ChannelWentLiveEvent(client, channel, livestream));
            }

            // ChannelWentOfflineEvent
            case "App\\Events\\StopStreamBroadcast" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("livestream").get("channel").get("id").asInt());

                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                EventLivestream livestream = new EventLivestream(cachedChannel.getLivestream().getId(), cachedChannel.getId(), cachedChannel.getLivestream().getSessionTitle(), cachedChannel.getLivestream().getSource(), cachedChannel.getLivestream().getCreatedAt());
                client.emit(EventType.CHANNEL_WENT_OFFLINE, new ChannelWentLiveEvent(client, channel, livestream));
            }

            // ChannelSubscriptionEvent
            case "App\\Events\\ChannelSubscriptionEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("channel_id").asInt());
                Channel.V2 cachedGifter = client.channels.cache.find(result.get("username").asText());
                JsonNode idNode = mapper.readTree(result.get("user_ids").asText());

                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                EventChannel gifter = new EventChannel(cachedGifter.getId(), cachedGifter.getUserId(), cachedGifter.getFollowerCount(), cachedGifter.getSlug(), cachedGifter.isVerified());
                client.emit(EventType.CHANNEL_SUBSCRIPTION, new ChannelSubscriptionEvent(client, mapper.treeToValue(idNode, int[].class), gifter, channel));
            }

            // ChannelFollowEvent
            case "App\\Events\\FollowersUpdatedEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("channel_id").asInt());
                Channel.V2 cachedFollower = client.channels.cache.find(result.get("username").asText());

                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                EventChannel follower = new EventChannel(cachedFollower.getId(), cachedFollower.getUserId(), cachedFollower.getFollowerCount(), cachedFollower.getSlug(), cachedFollower.isVerified());
                client.emit(EventType.CHANNEL_FOLLOW, new ChannelFollowEvent(client, channel, follower, result.get("followersCount").asInt(), result.get("followed").asBoolean()));
            }

            // ChannelGiftedSubscriptionsEvent
            case "App\\Events\\LuckyUsersWhoGotGiftSubscriptionsEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("channel").get("id").asInt());
                Channel.V2 cachedGifter = client.channels.cache.find(result.get("gifter_username").asText());

                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                EventChannel gifter = new EventChannel(cachedGifter.getId(), cachedGifter.getUserId(), cachedGifter.getFollowerCount(), cachedGifter.getSlug(), cachedGifter.isVerified());
                client.emit(EventType.CHANNEL_GIFTED_SUBSCRIPTIONS, new ChannelGiftedSubscriptionsEvent(client, channel, gifter, mapper.treeToValue(mapper.readTree(result.get("usernames").asText()), String[].class)));
            }

            // ChannelLeaderboardUpdatedEvent
            case "App\\Events\\GiftsLeaderboardUpdated" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("channel").get("id").asInt());
                Channel.V2 cachedGifter = client.channels.cache.find(result.get("gifterId").asInt());

                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                EventChannel gifter = new EventChannel(cachedGifter.getId(), cachedGifter.getUserId(), cachedGifter.getFollowerCount(), cachedGifter.getSlug(), cachedGifter.isVerified());
                ChannelLeaderboardUpdatedEvent.Leaderboard[] leaderboard = mapper.readValue(result.get("leaderboard").asText(), ChannelLeaderboardUpdatedEvent.Leaderboard[].class);
                ChannelLeaderboardUpdatedEvent.Leaderboard[] weekly = mapper.readValue(result.get("weekly_leaderboard").asText(), ChannelLeaderboardUpdatedEvent.Leaderboard[].class);
                ChannelLeaderboardUpdatedEvent.Leaderboard[] monthly = mapper.readValue(result.get("monthly_leaderboard").asText(), ChannelLeaderboardUpdatedEvent.Leaderboard[].class);

                client.emit(EventType.CHANNEL_LEADERBOARD_UPDATED, new ChannelLeaderboardUpdatedEvent(client, channel, gifter, leaderboard, weekly, monthly, result.get("gifted_quantity").asInt()));
            }

            // ChannelSentRaidEvent
            case "App\\Events\\ChatMoveToSupportedChannelEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("channel").get("id").asInt());
                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                ChannelSentRaidEvent.Hosted hosted = mapper.readValue(result.get("hosted").asText(), ChannelSentRaidEvent.Hosted.class);

                client.emit(EventType.CHANNEL_SENT_RAID, new ChannelSentRaidEvent(client, channel, result.get("slug").asText(), hosted));
            }

            // ChannelWasRaidedEvent
            case "App\\Events\\StreamHostEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 raiderCache = client.channels.cache.find(result.get("host_username").asText());
                EventChannel raider = new EventChannel(raiderCache.getId(), raiderCache.getUserId(), raiderCache.getFollowerCount(), raiderCache.getSlug(), raiderCache.isVerified());

                client.emit(EventType.CHANNEL_WAS_RAIDED, new ChannelWasRaidedEvent(client, raider, result.get("chatroom_id").asInt(), result.get("number_viewers").asInt(), result.get("optional_message").asText()));
            }

            // ChannelPollUpdatedEvent
            case "App\\Events\\PollUpdateEvent" -> {
                ChannelPollUpdatedEvent event = mapper.readValue(data, ChannelPollUpdatedEvent.class);
                client.emit(EventType.CHANNEL_POLL_UPDATE, event);
            }

            // SubscriptionEvent
            case "App\\Events\\SubscriptionEvent" -> {
                SubscriptionEvent event = mapper.readValue(data, SubscriptionEvent.class);
                client.emit(EventType.SUBSCRIPTION, event);
            }

            // Chatroom Updates

            // ChatMessageEvent
            case "App\\Events\\ChatMessageEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.subscribed.values().stream().filter(filter -> filter.getChatroom().getId() == result.get("chatroom_id").asInt()).findFirst().get();
                User user = client.users.cache.find(result.get("sender").get("slug").asText());

                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());
                ChatMessageEvent.Sender sender = new ChatMessageEvent.Sender(user.getId(), result.get("sender").get("username").asText(), result.get("sender").get("slug").asText(), mapper.readValue(result.get("sender").get("identity").toString(), ChatMessageEvent.Sender.Identity.class));
                ChatMessageEvent.Metadata metadata = result.get("metadata") == null ? null : mapper.readValue(result.get("metadata").toString(), ChatMessageEvent.Metadata.class);

                client.emit(EventType.CHAT_MESSAGE, new ChatMessageEvent(client, channel, result.get("content").asText(), result.get("id").asInt(), result.get("chatroom_id").asInt(), sender, metadata));
            }

            // ChatterBanEvent
            case "App\\Events\\UserBannedEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("id").asInt());
                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());

                ChatterBanEvent.User banned = mapper.readValue(result.get("user").asText(), ChatterBanEvent.User.class);
                ChatterBanEvent.User moderator = mapper.readValue(result.get("banned_by").asText(), ChatterBanEvent.User.class);

                client.emit(EventType.CHATTER_BANNED, new ChatterBanEvent(client, channel, result.get("id").asInt(), banned, moderator, result.get("expires_at").asText()));
            }

            // ChatterUnbanEvent
            case "App\\Events\\UserUnbannedEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("id").asInt());
                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());

                ChatterUnbanEvent.User banned = mapper.readValue(result.get("user").asText(), ChatterUnbanEvent.User.class);
                ChatterUnbanEvent.User moderator = mapper.readValue(result.get("banned_by").asText(), ChatterUnbanEvent.User.class);

                client.emit(EventType.CHATTER_UNBANNED, new ChatterUnbanEvent(client, channel, result.get("id").asInt(), banned, moderator));
            }

            // DeleteMessageEvent
            case "App\\Events\\MessageDeletedEvent" -> {
                JsonNode result = mapper.readTree(data);
                Channel.V2 cachedChannel = client.channels.cache.find(result.get("id").asInt());
                EventChannel channel = new EventChannel(cachedChannel.getId(), cachedChannel.getUserId(), cachedChannel.getFollowerCount(), cachedChannel.getSlug(), cachedChannel.isVerified());

                client.emit(EventType.DELETED_MESSAGE, new DeleteMessageEvent(client, channel, result.get("message").get("id").asInt()));
            }

            // PinnedMessageEvent
            case "App\\Events\\PinnedMessageCreatedEvent" -> {
                JsonNode result = mapper.readTree(data);
                PinnedMessageEvent.Message message = mapper.readValue(result.get("message").asText(), PinnedMessageEvent.Message.class);

                client.emit(EventType.PINNED_MESSAGE, new PinnedMessageEvent(client, message, result.get("duration").asText()));
            }

            // GiftedSubscriptionsEvent
            case "App\\Events\\GiftedSubscriptionsEvent" -> {
                GiftedSubscriptionsEvent event = mapper.readValue(data, GiftedSubscriptionsEvent.class);
                client.emit(EventType.GIFTED_SUBSCRIPTIONS, event);
            }

            // ChatroomUpdatedEvent
            case "App\\Events\\ChatroomUpdatedEvent" -> {
                ChatroomUpdatedEvent event = mapper.readValue(data, ChatroomUpdatedEvent.class);
                client.emit(EventType.CHATROOM_UPDATED, event);
            }

            // ChatroomClearedEvent
            case "App\\Events\\ChatroomClearEvent" -> {
                ChatroomClearedEvent event = mapper.readValue(data, ChatroomClearedEvent.class);
                client.emit(EventType.CHATROOM_CLEARED, event);
            }
        }
    }
}