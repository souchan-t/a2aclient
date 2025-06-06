package souchan.a2a.models.response;

public interface JSONRPCRequest {
    String id();
    String method();
    String jsonrpc();

}
