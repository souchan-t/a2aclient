package souchan.a2a.client.jsonrpc;

public class JSONRPCException extends RuntimeException {
    public final JSONRPCError jsonrpcError;

    public JSONRPCException(JSONRPCError jsonrpcError) {
        this.jsonrpcError = jsonrpcError;
    }

    @Override
    public String getMessage() {
        return this.jsonrpcError.message();
    }
}
