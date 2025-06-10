package souchan.a2a.models;

import lombok.Builder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An AgentCard conveys key information:
 * - Overall details (version, name, description, uses)
 * - Skills: A set of capabilities the agent can perform
 * - Default modalities/content types supported by the agent.
 * - Authentication requirements
 */
@Builder
public record AgentCard(
        /*
         * Human readable name of the agent.
         * @example "Recipe Agent"
         */
        String name,

        /*
         * A human-readable description of the agent. Used to assist users and
         * other agents in understanding what the agent can do.
         * @example "Agent that helps users with recipes and cooking."
         */
        String description,

        /* A URL to the address the agent is hosted at. */
        String url,

        /* A URL to an icon for the agent. */
        Optional<String> iconUrl,

        /* The service provider of the agent */
        Optional<AgentProvider> provider,

        /*
         * The version of the agent - format is up to the provider.
         * @example "1.0.0"
         */
        String version,

        /* A URL to documentation for the agent. */
        Optional<String> documentationUrl,

        /* Optional capabilities supported by the agent. */
        AgentCapabilities capabilities,

        /* Security scheme details used for authenticating with this agent. */
        Optional<Map<String, SecurityScheme>> securitySchemes,

        /* Security requirements for contacting the agent. */
        Optional<List<Map<String, List<String>>>> security,

        /*
         * The set of interaction modes that the agent supports across all skills. This can be overridden per-skill.
         * Supported media types for input.
         */
        List<String> defaultInputModes,

        /* Supported media types for output. */
        List<String> defaultOutputModes,

        /* Skills are a unit of capability that an agent can perform. */
        List<AgentSkill> skills,

        /*
         * true if the agent supports providing an extended agent card when the user is authenticated.
         * Defaults to false if not specified.
         */
        Optional<Boolean> supportsAuthenticatedExtendedCard
) {
}
