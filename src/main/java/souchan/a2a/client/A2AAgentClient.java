package souchan.a2a.client;

import souchan.a2a.models.*;
import souchan.a2a.models.response.*;

import java.util.function.Consumer;

public interface A2AAgentClient {

    AgentAuthenticatedExtendedCardResponse authenticatedExtendedCard();
    MessageSendResponse messageSend(MessageSendParams messageSendParams);
    void messageStream(MessageSendParams messageSendParams, Consumer<MessageStreamResponse> consumer);
    TasksGetResponse tasksGet(TaskQueryParams taskQueryParams);
    TasksCancelResponse taskCancel(TaskIdParams taskIdParams);
    TasksPushNotificationConfigSetResponse pushNotificationSet(TaskPushNotificationConfig taskPushNotificationConfig);
    TasksPushNotificationConfigGetResponse pushNotificationGet(TaskIdParams taskIdParams);
    void tasksResubscribe(TaskIdParams taskIdParams, Consumer<TasksResubscribeResponse> consumer);






}
