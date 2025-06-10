package souchan.a2a.models;

import lombok.Builder;

import java.util.Optional;

/**
 * Configuration for setting up push notifications for task updates.
 */
@Builder
public record PushNotificationConfig(
        /* Push Notification ID - created by server to support multiple callbacks */
        Optional<String> id,

        /* URL for sending the push notifications. */
        String url,

        /* Token unique to this task/session. */
        Optional<String> token,

        Optional<PushNotificationAuthenticationInfo> authentication
) {
}

