package souchan.a2a.models.response;

import souchan.a2a.models.SendMessageResponseResult;

import java.util.Optional;

public record SendMessageResponse(
        String jsonrpc,
        String id,
        Optional<SendMessageResponseResult> result,
        Optional<JSONRPCError> error ) implements JSONRPCResponse {
}
