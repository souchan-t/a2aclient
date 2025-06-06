package souchan.a2a.client;

import souchan.a2a.models.SecurityScheme;
import souchan.a2a.models.response.JSONRPCRequest;
import souchan.a2a.models.response.JSONRPCResponse;

import java.util.concurrent.CompletableFuture;

public interface JsonRPCClient {
    <T extends JSONRPCResponse> Object request(String url, JSONRPCRequest request, Class<T> responseClass, SecurityScheme securityScheme, Credential credential) throws A2AClientException;
    <T extends JSONRPCResponse> CompletableFuture<Void> requestSse(String url, JSONRPCRequest request, Class<T> responseClass, SecurityScheme securityScheme, Credential credential, A2AEventListener eventListener);
}
