package souchan.a2a.client;

import souchan.a2a.models.AgentCard;

public interface A2AAgentCardClient {
    /** agent.jsonからAgentCardを取得する. */
    AgentCard getAgentCard(String serverDomain);
}
