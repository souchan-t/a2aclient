package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.TaskIdParams;

public record ResubscribeTasksRequest(
        String jsonrpc,
        String method,
        TaskIdParams params,
        String id) implements JSONRPCRequest<TaskIdParams> {

    public ResubscribeTasksRequest(TaskIdParams params, String id) {
        this("2.0", "tasks/resubscribe", params, id);
    }
}
