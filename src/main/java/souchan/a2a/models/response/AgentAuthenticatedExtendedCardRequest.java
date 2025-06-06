package souchan.a2a.models.response;

public record AgentAuthenticatedExtendedCardRequest(
        String jsonrpc,
        String method,
        String id) implements JSONRPCRequest {

    public AgentAuthenticatedExtendedCardRequest(String id) {
        this("2.0", "agent/authenticatedExtendedCard", id);
    }
}
