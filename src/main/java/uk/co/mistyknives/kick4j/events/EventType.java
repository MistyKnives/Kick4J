package uk.co.mistyknives.kick4j.events;

import lombok.AllArgsConstructor;
import uk.co.mistyknives.kick4j.events.impl.channel.*;
import uk.co.mistyknives.kick4j.events.impl.chatroom.*;
import uk.co.mistyknives.kick4j.events.impl.client.*;

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
@AllArgsConstructor
public enum EventType {

    // Channel Events
    CHANNEL_WENT_LIVE(ChannelWentLiveEvent.class),
    CHANNEL_WENT_OFFLINE(ChannelWentOfflineEvent.class),
    CHANNEL_FOLLOW(ChannelFollowEvent.class),
    CHANNEL_GIFTED_SUBSCRIPTIONS(ChannelGiftedSubscriptionsEvent.class),
    CHANNEL_SUBSCRIPTION(ChannelSubscriptionEvent.class),
    CHANNEL_LEADERBOARD_UPDATED(ChannelLeaderboardUpdatedEvent.class),
    CHANNEL_SENT_RAID(ChannelSentRaidEvent.class),
    CHANNEL_WAS_RAIDED(ChannelWasRaidedEvent.class),
    CHANNEL_POLL_UPDATE(ChannelPollUpdatedEvent.class),
    SUBSCRIPTION(SubscriptionEvent.class),

    // Chatroom Events
    CHAT_MESSAGE(ChatMessageEvent.class),
    DELETED_MESSAGE(DeleteMessageEvent.class),
    PINNED_MESSAGE(PinnedMessageEvent.class),
    CHATTER_BANNED(ChatterBanEvent.class),
    CHATTER_UNBANNED(ChatterUnbanEvent.class),
    GIFTED_SUBSCRIPTIONS(GiftedSubscriptionsEvent.class),
    CHATROOM_UPDATED(ChatroomUpdatedEvent.class),
    CHATROOM_CLEARED(ChatroomClearedEvent.class),

    //Client Events
    READY(ReadyEvent.class),
    CLIENT_JOINED_CHANNEL(ClientJoinEvent.class),
    CLIENT_PART_CHANNEL(ClientPartEvent.class);

    private final Class<? extends Event> eventClass;

    public Class<? extends Event> getEventClass() {
        return eventClass;
    }

    public static EventType getEventType(Class<? extends Event> clazz) {
        for(EventType type : values())
            if(type.getEventClass().equals(clazz))
                return type;
        return null;
    }
}
