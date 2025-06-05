package souchan.a2a.models.response;

import souchan.a2a.models.MessageStreamResponseResult;

import java.util.Optional;

public record MessageStreamResponse(
        String jsonrpc,
        String id,
        Optional<MessageStreamResponseResult> result,
        Optional<JSONRPCError> error
)  {
}
