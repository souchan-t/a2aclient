package souchan.a2a.client;

import souchan.a2a.models.*;

import java.util.concurrent.CompletableFuture;

public interface A2AAgentClient {

    AgentCard authenticatedExtendedCard();
    SendMessageResponseResult sendMessage(MessageSendParams messageSendParams);
    CompletableFuture<Void> sendMessageStreaming(MessageSendParams messageSendParams, A2AEventListener eventListener);
    Task getTask(TaskQueryParams taskQueryParams);
    Task cancelTask(TaskIdParams taskIdParams);
    TaskPushNotificationConfig setPushNotification(TaskPushNotificationConfig taskPushNotificationConfig);
    TaskPushNotificationConfig getPushNotification(TaskIdParams taskIdParams);
    CompletableFuture<Void> resubscribeTasks(TaskIdParams taskIdParams, A2AEventListener eventListener);

}
