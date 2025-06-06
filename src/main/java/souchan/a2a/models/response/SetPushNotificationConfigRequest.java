package souchan.a2a.models.response;

import souchan.a2a.models.TaskPushNotificationConfig;

public record SetPushNotificationConfigRequest(
        String jsonrpc,
        String method,
        TaskPushNotificationConfig params,
        String id) implements JSONRPCRequest {

    public SetPushNotificationConfigRequest(TaskPushNotificationConfig params,String id) {
        this("2.0","tasks/pushNotificationConfig/set",params,id);
    }
}
