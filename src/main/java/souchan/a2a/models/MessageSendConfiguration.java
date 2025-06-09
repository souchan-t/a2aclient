package souchan.a2a.models;

import java.util.List;
import java.util.Optional;

/**
 * Configuration for the send message request.
 */
public record MessageSendConfiguration(
        /* Accepted output modalities by the client. */
        List<String> acceptedOutputModes,

        /* Number of recent messages to be retrieved. */
        Optional<Integer> historyLength,

        /* Where the server should send notifications when disconnected. */
        Optional<PushNotificationConfig> pushNotificationConfig,

        /* If the server should treat the client as a blocking request. */
        Optional<Boolean> blocking
) {
}
