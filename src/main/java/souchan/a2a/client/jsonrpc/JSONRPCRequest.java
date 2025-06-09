package souchan.a2a.client.jsonrpc;

public interface JSONRPCRequest<T> {
    String id();

    String method();

    String jsonrpc();

    T params();

}
