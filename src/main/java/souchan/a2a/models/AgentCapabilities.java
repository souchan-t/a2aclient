package souchan.a2a.models;

import lombok.Builder;

import java.util.List;
import java.util.Optional;

/**
 * Defines optional capabilities supported by an agent.
 */
@Builder
public record AgentCapabilities(
        /* true if the agent supports SSE. */
        Optional<Boolean> streaming,

        /* true if the agent can notify updates to client. */
        Optional<Boolean> pushNotifications,

        /* true if the agent exposes status change history for tasks. */
        Optional<Boolean> stateTransitionHistory,

        /* extensions supported by this agent. */
        Optional<List<AgentExtension>> extensions
) {
}
