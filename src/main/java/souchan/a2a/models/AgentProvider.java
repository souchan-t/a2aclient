package souchan.a2a.models;


import lombok.Builder;

/**
 * Represents the service provider of an agent.
 */
@Builder
public record AgentProvider(
        /* Agent provider's organization name. */
        String organization,

        /* Agent provider's URL. */
        String url
) {
}
