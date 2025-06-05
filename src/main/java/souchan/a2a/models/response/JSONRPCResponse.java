package souchan.a2a.models.response;

import souchan.a2a.models.ResponseResult;

import java.util.Optional;

public record JSONRPCResponse(
        String jsonrpc,
        String id,
        Optional<ResponseResult> result,
        Optional<JSONRPCError> error
) {
}
