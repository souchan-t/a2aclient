package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.Task;

import java.util.Optional;

public record CancelTasksResponse(
        String jsonrpc,
        String id,
        Optional<Task> result,
        Optional<JSONRPCError> error) implements JSONRPCResponse<Task> {
}
