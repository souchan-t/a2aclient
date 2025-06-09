package souchan.a2a.client.jsonrpc;

import java.util.Optional;

public record JSONRPCError(
        int code,
        String message,
        Optional<Object> data
) {
}
