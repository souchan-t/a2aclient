package souchan.a2a.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import souchan.a2a.models.AgentCard;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class A2AAgentCardClientImpl implements A2AAgentCardClient{
    protected final HttpClient httpClient;
    protected final ObjectMapper objectMapper;
    public A2AAgentCardClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new Jdk8Module());
    }

    @Override
    public AgentCard getAgentCard(String serverDomain) {
        final var httpRequest = HttpRequest.
                newBuilder(URI.create(serverDomain + ".well-known/agent.json"))
                .header("Accept","application/json")
                .GET()
                .build();

        try {

            final var rawResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (rawResponse.statusCode() < 200 | rawResponse.statusCode() >= 300) {
                throw new A2AClientException("スターツコード:" + rawResponse.statusCode());
            } else {
                return objectMapper.readValue(rawResponse.body(), AgentCard.class);
            }



        } catch (IOException | InterruptedException e) {
            throw new A2AClientException(e);
        }
    }
}
