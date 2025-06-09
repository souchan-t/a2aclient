package souchan.a2a.models;

/**
 * Parameters for setting or getting push notification configuration for a task.
 */
public record TaskPushNotificationConfig(
        /** Task id. */
        String taskId,

        /** Push notification configuration. */
        PushNotificationConfig pushNotificationConfig
) implements RequestParams {
}
