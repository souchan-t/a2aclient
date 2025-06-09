package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.TaskPushNotificationConfig;

import java.util.Optional;

public record SetPushNotificationConfigResponse(
        String jsonrpc,
        String id,
        Optional<TaskPushNotificationConfig> result,
        Optional<JSONRPCError> error) implements JSONRPCResponse<TaskPushNotificationConfig> {
}
