package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.TaskIdParams;

public record GetPushNotificationConfigRequest(
        String jsonrpc,
        String method,
        TaskIdParams params,
        String id) implements JSONRPCRequest<TaskIdParams> {

    public GetPushNotificationConfigRequest(TaskIdParams params, String id) {
        this("2.0", "tasks/pushNotificationConfig/get", params, id);
    }
}
