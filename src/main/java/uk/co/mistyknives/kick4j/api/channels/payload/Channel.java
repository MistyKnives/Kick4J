package uk.co.mistyknives.kick4j.api.channels.payload;

import co.casterlabs.rakurai.json.annotating.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import uk.co.mistyknives.kick4j.api.users.payload.User;

import java.util.List;

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
@Getter
@JsonClass(exposeAll = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class V1 {

        @JsonProperty("id")
        private int id;

        @JsonProperty("user_id")
        private int userId;

        private String slug;

        @JsonProperty("is_banned")
        private boolean isBanned;

        @JsonProperty("playback_url")
        private String playbackUrl;

        @JsonProperty("name_updated_at")
        private String nameUpdatedAt;

        @JsonProperty("vod_enabled")
        private boolean vodEnabled;

        @JsonProperty("subscription_enabled")
        private boolean subscriptionEnabled;

        @JsonProperty("followersCount")
        private int followerCount;

        @JsonProperty("subscriber_badges")
        private List<SubscriberBadge> subscriberBadges;

        @JsonProperty("banner_image")
        private BannerImage bannerImage;

        @JsonProperty("recent_categories")
        private List<RecentCategory> recentCategories;

        private Livestream livestream;

        private Object role;

        private boolean muted;

        @JsonProperty("follower_badges")
        private Object follower_badges;

        @JsonProperty("offline_banner_image")
        private OfflineBanner offlineBannerImage;

        @JsonProperty("can_host")
        private boolean canHost;

        private User user;

        private Chatroom chatroom;

        @JsonProperty("ascending_links")
        private List<AscendingLink> ascendingLinks;

        private Plan plan;

        @JsonProperty("previous_livestreams")
        private List<Livestream> previousLivestreams;

        private Verified verified;

        private Object media;

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class SubscriberBadge {

            private int id;

            @JsonProperty("channel_id")
            private int channelId;

            private int months;

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class BadgeImage {

                @JsonProperty("srcset")
                private String srcSet;

                private String src;
            }
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class BannerImage {

            private String responsive;

            private String url;
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class RecentCategory {

            private int id;

            @JsonProperty("category_id")
            private int categoryId;

            private String name;

            private String slug;

            private List<String> tags;

            private String description;

            @JsonProperty("deleted_at")
            private String deletedAt;

            private int viewers;

            private BannerImage banner;

            private Category category;

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Category {
                private int id;

                private String name, slug, icon;
            }
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Livestream {

            private int id;

            private String slug;

            @JsonProperty("channel_id")
            private int channelId;

            @JsonProperty("created_at")
            private String createdAt;

            @JsonProperty("session_title")
            private String sessionTitle;

            @JsonProperty("is_live")
            private boolean isLive;

            @JsonProperty("risk_level_id")
            private int riskLevelId;

            @JsonProperty("start_time")
            private String startTime;

            private String source;

            @JsonProperty("twitch_channel")
            private String twitchChannel;

            private int duration;

            private String language;

            @JsonProperty("is_mature")
            private boolean isMature;

            @JsonProperty("viewer_count")
            private int viewerCount;

            private Thumbnail thumbnail;

            private List<Category> categories;

            private List<String> tags;

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Thumbnail {
                private String url;
            }

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Category {

                private int id;

                @JsonProperty("category_id")
                private int categoryId;

                private String name;

                private String slug;

                private List<String> tags;

                private String description;

                @JsonProperty("deleted_at")
                private String deletedAt;

                private int viewers;

                private Category category;
            }
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class OfflineBanner {

            @JsonProperty("srcset")
            private String srcSet;

            private String src;
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class AscendingLink {

            private int id;

            @JsonProperty("channel_id")
            private int channelId;

            private String description;

            private String link;

            @JsonProperty("created_at")
            private String createdAt;

            @JsonProperty("updated_at")
            private String updatedAt;

            private long order;

            private String title;
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Plan {

            private long id;

            @JsonProperty("channel_id")
            private long channelId;

            @JsonProperty("stripe_plan_id")
            private String stripePlanId;

            private String amount;

            @JsonProperty("created_at")
            private String createdAt;

            @JsonProperty("updated_at")
            private String updatedAt;
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Verified {

            private long id;

            @JsonProperty("channel_id")
            private long channelId;

            @JsonProperty("created_at")
            private String createdAt;

            @JsonProperty("updated_at")
            private String updatedAt;
        }
    }

    @Getter
    @JsonClass(exposeAll = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class V2 {
        @JsonProperty("id")
        private int id;

        @JsonProperty("user_id")
        private int userId;

        private String slug;

        @JsonProperty("is_banned")
        private boolean isBanned;

        @JsonProperty("playback_url")
        private String playbackUrl;

        @JsonProperty("vod_enabled")
        private boolean vodEnabled;

        @JsonProperty("subscription_enabled")
        private boolean subscriptionEnabled;

        @JsonProperty("followers_count")
        private int followerCount;

        @JsonProperty("subscriber_badges")
        private Object subscriberBadges;

        @JsonProperty("banner_image")
        private Category.Banner bannerImage;

        private Livestream livestream;

        private Object role;

        private boolean muted;

        @JsonProperty("follower_badges")
        private Object followerBadges;

        @JsonProperty("offline_banner_image")
        private OfflineBanner offlineBannerImage;

        private boolean verified;

        @JsonProperty("recent_categories")
        private List<Category> recentCategories;

        @JsonProperty("can_host")
        private boolean canHost;

        private User user;

        private Chatroom chatroom;

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Category {

            @JsonProperty("id")
            private int id;

            @JsonProperty("category_id")
            private int categoryId;

            @JsonProperty("name")
            private String name;

            @JsonProperty("slug")
            private String slug;

            @JsonProperty("tags")
            private String[] tags;

            @JsonProperty("description")
            private String description;

            @JsonProperty("deleted_at")
            private String deletedAt;

            @JsonProperty("viewers")
            private int viewers;

            @JsonProperty("banner")
            private Banner banner;

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Banner {
                @JsonProperty("responsive")
                private String responsive;

                @JsonProperty("url")
                private String url;
            }
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class OfflineBanner {
            private String src;

            @JsonProperty("srcset")
            private String srcSet;
        }

        @Getter
        @JsonClass(exposeAll = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Livestream {

            private int id;

            private String slug;

            @JsonProperty("channel_id")
            private int channelId;

            private String createdAt;

            @JsonProperty("session_title")
            private String sessionTitle;

            @JsonProperty("is_live")
            private boolean isLive;

            @JsonProperty("risk_level_id")
            private int riskLevelId;

            @JsonProperty("start_time")
            private String startTime;

            private String source;

            @JsonProperty("twitch_channel")
            private String twitchChannel;

            private int duration;

            private String language;

            @JsonProperty("is_mature")
            private boolean isMature;

            @JsonProperty("viewer_count")
            private int viewerCount;

            private Thumbnail thumbnail;

            private List<Category> categories;

            private List<String> tags;

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Thumbnail {
                private String url;
            }

            @Getter
            @JsonClass(exposeAll = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Category {

                private int id;

                @JsonProperty("category_id")
                private int categoryId;

                private String name;

                private String slug;

                private List<String> tags;

                private String description;

                @JsonProperty("deleted_at")
                private String deletedAt;

                private int viewers;

                private CategoryInfo category;
            }

            @Getter
            public static class CategoryInfo {
                private int id;

                private String name, slug, icon;
            }
        }
    }
}
