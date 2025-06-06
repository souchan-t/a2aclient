package souchan.a2a.client;

import souchan.a2a.models.*;
import souchan.a2a.models.response.*;

import java.net.http.HttpClient;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class A2AAgentClientImpl implements A2AAgentClient {
    protected AgentCard agentCard;
    protected final HttpClient httpClient;
    protected final Credential credential;
    protected final JsonRPCClient jsonRPCClient;
    protected final SecurityScheme securityScheme;

    public A2AAgentClientImpl(AgentCard agentCard, Credential credential, HttpClient httpClient) {
        this.agentCard = agentCard;
        this.httpClient = httpClient;
        this.credential = credential;
        this.jsonRPCClient = new JsonRPCClientImpl(httpClient);
        this.securityScheme = agentCard
                .securitySchemes()
                .map(Map::values)
                .flatMap(schemes -> schemes
                        .stream()
                        .filter(scheme -> scheme instanceof SecurityScheme.APIKeySecurityScheme)
                        .findFirst()
                )
                .orElse(null);

    }

    @Override
    public AgentCard authenticatedExtendedCard() {

        final var agentCard = (AgentCard) this.jsonRPCClient.request(
                this.agentCard.url(),
                new AgentAuthenticatedExtendedCardRequest(generateId()),
                AgentAuthenticatedExtendedCardResponse.class,
                this.securityScheme,
                this.credential);
        this.agentCard = agentCard;
        return agentCard;


    }

    @Override
    public SendMessageResponseResult sendMessage(MessageSendParams messageSendParams) {

        return (SendMessageResponseResult) this.jsonRPCClient.request(
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
                new StreamMessageRequest(messageSendParams,generateId()),
                StreamMessageResponse.class,
                this.securityScheme,
                this.credential,
                eventListener
        );
    }

    @Override
    public Task getTask(TaskQueryParams taskQueryParams) {
        return (Task) this.jsonRPCClient.request(
                this.agentCard.url(),
                new GetTasksRequest(taskQueryParams, generateId()),
                GetTasksResponse.class,
                this.securityScheme,
                this.credential);
    }

    @Override
    public Task cancelTask(TaskIdParams taskIdParams) {
        return (Task) this.jsonRPCClient.request(
                this.agentCard.url(),
                new CancelTasksRequest(taskIdParams, generateId()),
                CancelTasksResponse.class,
                this.securityScheme,
                this.credential);
    }

    @Override
    public TaskPushNotificationConfig setPushNotification(TaskPushNotificationConfig taskPushNotificationConfig) {
        return (TaskPushNotificationConfig) this.jsonRPCClient.request(
                this.agentCard.url(),
                new SetPushNotificationConfigRequest(taskPushNotificationConfig, generateId()),
                SetPushNotificationConfigResponse.class,
                this.securityScheme,
                this.credential);
    }

    @Override
    public TaskPushNotificationConfig getPushNotification(TaskIdParams taskIdParams) {
        return (TaskPushNotificationConfig) this.jsonRPCClient.request(
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
                new ResubscribeTasksRequest(taskIdParams,generateId()),
                ResubscribeTasksResponse.class,
                this.securityScheme,
                this.credential,
                eventListener
        );
    }

    protected String generateId() {
        return UUID.randomUUID().toString();
    }
}
