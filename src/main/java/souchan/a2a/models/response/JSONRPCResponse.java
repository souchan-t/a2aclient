package souchan.a2a.models.response;

import java.util.Optional;

public interface JSONRPCResponse{

    String jsonrpc();
    String id();
    Optional<JSONRPCError> error();
    Optional<?> result();

    default boolean isError() {
        return error().isPresent();
    }
}
