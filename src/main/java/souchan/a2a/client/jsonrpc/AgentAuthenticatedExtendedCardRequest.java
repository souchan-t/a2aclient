package souchan.a2a.client.jsonrpc;

public record AgentAuthenticatedExtendedCardRequest(
        String jsonrpc,
        String method,
        String id,
        Void params

) implements JSONRPCRequest<Void> {

    public AgentAuthenticatedExtendedCardRequest(String id) {
        this("2.0", "agent/authenticatedExtendedCard", id, null);
    }
}
