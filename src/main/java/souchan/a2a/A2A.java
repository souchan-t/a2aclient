package souchan.a2a;

import souchan.a2a.client.A2AAgentCardClientImpl;
import souchan.a2a.client.A2AAgentClient;
import souchan.a2a.client.A2AAgentClientImpl;
import souchan.a2a.client.APIKeyCredential;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Optional;

public class A2A {
    protected final static HttpClient httpClient = HttpClient
            .newBuilder()
            .connectTimeout(Duration.ofSeconds(60))
            .build();

    public static A2AAgentClient getClient(String serverDomain, String securitySchemeName, String credential, HttpClient httpClient) {
        final var agentCard = new A2AAgentCardClientImpl(httpClient).getAgentCard(serverDomain);
        final var securityScheme = agentCard
                .securitySchemes()
                .flatMap(schemes -> Optional.ofNullable(schemes.get(securitySchemeName)))
                .orElseThrow();

        final var apiKeyCredential = new APIKeyCredential(credential);

        return new A2AAgentClientImpl(agentCard, securityScheme, apiKeyCredential, httpClient);
    }

    public static A2AAgentClient getClient(String serverDomain, String securitySchemeName, String credential) {
        return A2A.getClient(serverDomain,securitySchemeName,credential,A2A.httpClient);
    }
    public static A2AAgentClient getClient(String serverDomain, String credential) {
        final var agentCard = new A2AAgentCardClientImpl(httpClient).getAgentCard(serverDomain);
        final var securityScheme = agentCard
                .securitySchemes()
                .flatMap(schemes -> schemes.values().stream().findFirst())
                .orElseThrow();

        final var apiKeyCredential = new APIKeyCredential(credential);

        return new A2AAgentClientImpl(agentCard, securityScheme, apiKeyCredential, httpClient);

    }
}
