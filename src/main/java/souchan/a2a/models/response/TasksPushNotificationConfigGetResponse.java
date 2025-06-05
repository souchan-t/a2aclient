package souchan.a2a.models.response;

import souchan.a2a.models.TaskPushNotificationConfig;

import java.util.Optional;

public record TasksPushNotificationConfigGetResponse(
    String jsonrpc,
    String id,
    Optional<TaskPushNotificationConfig> result,
    Optional<JSONRPCError> error){
}
