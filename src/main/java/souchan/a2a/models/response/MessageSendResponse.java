package souchan.a2a.models.response;

import souchan.a2a.models.MessageSendResponseResult;

import java.util.Optional;

public record MessageSendResponse(
        String jsonrpc,
        String id,
        Optional<MessageSendResponseResult> result,
        Optional<JSONRPCError> error
) {
}
