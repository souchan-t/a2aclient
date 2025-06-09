package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.TaskQueryParams;

public record GetTasksRequest(
        String jsonrpc,
        String method,
        TaskQueryParams params,
        String id) implements JSONRPCRequest<TaskQueryParams> {

    public GetTasksRequest(TaskQueryParams params, String id) {
        this("2.0", "tasks/get", params, id);
    }
}
