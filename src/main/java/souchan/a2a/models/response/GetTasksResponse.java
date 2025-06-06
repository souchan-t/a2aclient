package souchan.a2a.models.response;

import souchan.a2a.models.Task;

import java.util.Optional;

public record GetTasksResponse(
    String jsonrpc,
    String id,
    Optional<Task> result,
    Optional<JSONRPCError> error) implements JSONRPCResponse{
}
