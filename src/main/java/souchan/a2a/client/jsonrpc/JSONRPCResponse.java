package souchan.a2a.client.jsonrpc;

import java.util.Optional;

public interface JSONRPCResponse<R> {

    String jsonrpc();

    String id();

    Optional<JSONRPCError> error();

    Optional<R> result();

    default boolean isError() {
        return error().isPresent();
    }
}
