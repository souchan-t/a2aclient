package souchan.a2a.models.response;

import souchan.a2a.models.TaskIdParams;

public record GetPushNotificationConfigRequest(
        String jsonrpc,
        String method,
        TaskIdParams params,
        String id) implements JSONRPCRequest {

    public GetPushNotificationConfigRequest(TaskIdParams params, String id) {
        this("2.0","tasks/pushNotificationConfig/get",params,id);
    }
}
