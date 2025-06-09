package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.MessageStreamResponseResult;

import java.util.Optional;

public record StreamMessageResponse(
        String jsonrpc,
        String id,
        Optional<MessageStreamResponseResult> result,
        Optional<JSONRPCError> error
) implements JSONRPCResponse<MessageStreamResponseResult> {
}
