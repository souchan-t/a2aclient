package souchan.a2a.models.response;

import java.util.Optional;

public record JSONRPCError(
        int code,
        String message,
        Optional<Object> data
) {
}
