package souchan.a2a.models;


import java.util.List;
import java.util.Optional;

/**
 * Represents a unit of capability that an agent can perform.
 */
public record AgentSkill(
        /** Unique identifier for the agent's skill. */
        String id,

        /** Human readable name of the skill. */
        String name,

        /**
         * Description of the skill - will be used by the client or a human
         * as a hint to understand what the skill does.
         */
        String description,

        /**
         * Set of tagwords describing classes of capabilities for this specific skill.
         * @example ["cooking", "customer support", "billing"]
         */
        List<String> tags,

        /**
         * The set of example scenarios that the skill can perform.
         * Will be used by the client as a hint to understand how the skill can be used.
         * @example ["I need a recipe for bread"]
         */
        Optional<List<String>> examples,

        /**
         * The set of interaction modes that the skill supports
         * (if different than the default).
         * Supported media types for input.
         */
        Optional<List<String>> inputModes,

        /** Supported media types for output. */
        Optional<List<String>> outputModes
) {
}
