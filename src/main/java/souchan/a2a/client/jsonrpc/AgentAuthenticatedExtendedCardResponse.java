package souchan.a2a.client.jsonrpc;

import souchan.a2a.models.AgentCard;

import java.util.Optional;

public record AgentAuthenticatedExtendedCardResponse(
        String jsonrpc,
        String id,
        Optional<AgentCard> result,
        Optional<JSONRPCError> error) implements JSONRPCResponse<AgentCard> {
}
