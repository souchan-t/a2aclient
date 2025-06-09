package souchan.a2a.client;

import souchan.a2a.client.jsonrpc.*;
import souchan.a2a.models.*;

import java.net.http.HttpClient;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class A2AAgentClientImpl implements A2AAgentClient {
    protected final AgentCard agentCard;
    protected final HttpClient httpClient;
    protected final Credential credential;
    protected final JsonRPCClient jsonRPCClient;
    protected final SecurityScheme securityScheme;

    public A2AAgentClientImpl(AgentCard agentCard, SecurityScheme securityScheme, Credential credential, HttpClient httpClient) {
        this.securityScheme = securityScheme;
        this.credential = credential;
        this.httpClient = httpClient;
        this.jsonRPCClient = new JsonRPCClientImpl(httpClient);
        if (agentCard.supportsAuthenticatedExtendedCard().orElse(false)) {
            this.agentCard = authenticatedExtendedCard(agentCard);
        } else {
            this.agentCard = agentCard;
        }
    }

    public A2AAgentClientImpl(AgentCard agentCard, Credential credential, HttpClient httpClient) {
        this(agentCard,
                agentCard
                        .securitySchemes()
                        .map(Map::values)
                        .flatMap(schemes -> schemes
                                .stream()
                                .filter(scheme -> scheme instanceof SecurityScheme.APIKeySecurityScheme)
                                .findFirst()
                        )
                        .orElse(null),
                credential,
                httpClient);

    }

    @Override
    public AgentCard authenticatedExtendedCard(AgentCard agentCard) {

        return this.jsonRPCClient.request(
                this.agentCard.url(),
                new AgentAuthenticatedExtendedCardRequest(generateId()),
                AgentAuthenticatedExtendedCardResponse.class,
                this.securityScheme,
                this.credential);

    }

    @Override
    public SendMessageResponseResult sendMessage(MessageSendParams messageSendParams) {

        return this.jsonRPCClient.request(
                this.agentCard.url(),
                new SendMessageRequest(messageSendParams, generateId()),
                SendMessageResponse.class,
                this.securityScheme,
                this.credential
        );
    }

    @Override
    public CompletableFuture<Void> sendMessageStreaming(MessageSendParams messageSendParams, A2AEventListener eventListener) {
        return this.jsonRPCClient.requestSse(
                this.agentCard.url(),
                new StreamMessageRequest(messageSendParams, generateId()),
                StreamMessageResponse.class,
                eventListener,
                this.securityScheme,
                this.credential
        );
    }

    @Override
    public Task getTask(TaskQueryParams taskQueryParams) {
        return this.jsonRPCClient.request(
                this.agentCard.url(),
                new GetTasksRequest(taskQueryParams, generateId()),
                GetTasksResponse.class,
                this.securityScheme,
                this.credential);
    }

    @Override
    public Task cancelTask(TaskIdParams taskIdParams) {
        return this.jsonRPCClient.request(
                this.agentCard.url(),
                new CancelTasksRequest(taskIdParams, generateId()),
                CancelTasksResponse.class,
                this.securityScheme,
                this.credential);
    }

    @Override
    public TaskPushNotificationConfig setPushNotification(TaskPushNotificationConfig taskPushNotificationConfig) {
        return this.jsonRPCClient.request(
                this.agentCard.url(),
                new SetPushNotificationConfigRequest(taskPushNotificationConfig, generateId()),
                SetPushNotificationConfigResponse.class,
                this.securityScheme,
                this.credential);
    }

    @Override
    public TaskPushNotificationConfig getPushNotification(TaskIdParams taskIdParams) {
        return this.jsonRPCClient.request(
                this.agentCard.url(),
                new GetPushNotificationConfigRequest(taskIdParams, generateId()),
                GetPushNotificationConfigResponse.class,
                this.securityScheme,
                this.credential
        );
    }

    @Override
    public CompletableFuture<Void> resubscribeTasks(TaskIdParams taskIdParams, A2AEventListener eventListener) {
        return this.jsonRPCClient.requestSse(
                this.agentCard.url(),
                new ResubscribeTasksRequest(taskIdParams, generateId()),
                ResubscribeTasksResponse.class,
                eventListener,
                this.securityScheme,
                this.credential
        );
    }

    protected static String generateId() {
        return UUID.randomUUID().toString();
    }
}
