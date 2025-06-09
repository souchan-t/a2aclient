package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.MessageSendParams;

public record SendMessageRequest(
        String jsonrpc,
        String method,
        MessageSendParams params,
        String id) implements JSONRPCRequest<MessageSendParams> {

    public SendMessageRequest(MessageSendParams params, String id) {
        this("2.0", "message/send", params, id);
    }
}
