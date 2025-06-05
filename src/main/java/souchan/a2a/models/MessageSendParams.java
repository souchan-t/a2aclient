package souchan.a2a.models;

import java.util.Map;
import java.util.Optional;

/**
 * Sent by the client to the agent as a request. May create, continue or restart a task.
 */
public record MessageSendParams(
        /** The message being sent to the server. */
        Message message,

        /** Send message configuration. */
        Optional<MessageSendConfiguration> configuration,

        /** Extension metadata. */
        Optional<Map<String, Object>> metadata
) implements RequestParams{}
