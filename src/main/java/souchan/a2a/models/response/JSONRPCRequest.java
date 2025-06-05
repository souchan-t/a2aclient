package souchan.a2a.models.response;

import souchan.a2a.models.RequestParams;

import java.util.Optional;

public record JSONRPCRequest(
        String jsonrpc,
        String method,
        Optional<RequestParams> params,
        String id
) {

}
