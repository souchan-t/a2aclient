package souchan.a2a.client.jsonrpc;

import souchan.a2a.client.A2AEventListener;
import souchan.a2a.client.Credential;
import souchan.a2a.models.SecurityScheme;

import java.util.concurrent.CompletableFuture;

public interface JsonRPCClient {
    <REQ, RES extends JSONRPCResponse<T>, T> T request(String url,
                                                       JSONRPCRequest<REQ> request,
                                                       Class<RES> responseClass) throws JSONRPCException;


    <REQ, RES extends JSONRPCResponse<T>, T> CompletableFuture<Void> requestSse(String url,
                                                                                JSONRPCRequest<REQ> request,
                                                                                Class<RES> responseClass,
                                                                                A2AEventListener eventListener);
}
