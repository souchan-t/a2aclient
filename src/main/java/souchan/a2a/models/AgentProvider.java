package souchan.a2a.models;


/**
 * Represents the service provider of an agent.
 */
public record AgentProvider(
        /* Agent provider's organization name. */
        String organization,

        /* Agent provider's URL. */
        String url
) {
}
