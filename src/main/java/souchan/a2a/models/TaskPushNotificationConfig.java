package souchan.a2a.models;

import lombok.Builder;

/**
 * Parameters for setting or getting push notification configuration for a task.
 */
@Builder
public record TaskPushNotificationConfig(
        /* Task id. */
        String taskId,

        /* Push notification configuration. */
        PushNotificationConfig pushNotificationConfig
) implements RequestParams {
}
