package souchan.a2a.models.response;

import souchan.a2a.models.MessageSendParams;

public record StreamMessageRequest(
        String jsonrpc,
        String method,
        MessageSendParams params,
        String id) implements JSONRPCRequest{

    public StreamMessageRequest(MessageSendParams params, String id) {
        this("2.0","message/send",params,id);
    }
}
