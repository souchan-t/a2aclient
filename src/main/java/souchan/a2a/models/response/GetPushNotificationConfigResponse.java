package souchan.a2a.models.response;

import souchan.a2a.models.TaskPushNotificationConfig;

import java.util.Optional;

public record GetPushNotificationConfigResponse(
    String jsonrpc,
    String id,
    Optional<TaskPushNotificationConfig> result,
    Optional<JSONRPCError> error) implements JSONRPCResponse{
}
