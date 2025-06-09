package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.TaskIdParams;

public record CancelTasksRequest(
        String jsonrpc,
        String method,
        TaskIdParams params,
        String id) implements JSONRPCRequest<TaskIdParams> {

    public CancelTasksRequest(TaskIdParams params, String id) {
        this("2.0", "asks/cancel", params, id);
    }
}
