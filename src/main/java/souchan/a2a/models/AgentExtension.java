package souchan.a2a.models;

import lombok.Builder;

import java.util.Map;
import java.util.Optional;

/**
 * A declaration of an extension supported by an Agent.
 */
@Builder
public record AgentExtension(
        /* The URI of the extension. */
        String uri,

        /* A description of how this agent uses this extension. */
        Optional<String> description,

        /* Whether the client must follow specific requirements of the extension. */
        Optional<Boolean> required,

        /* Optional configuration for the extension. */
        Optional<Map<String, Object>> params
) {
}
