package souchan.a2a.models;

import java.util.Map;
import java.util.Optional;

/**
 * Sent by server during sendStream or subscribe requests.
 */
public record TaskStatusUpdateEvent(
        /** Task id */
        String taskId,

        /** The context the task is associated with */
        String contextId,

        /** Event type */
        String kind,

        /** Current status of the task */
        TaskStatus status,

        /** Indicates the end of the event stream */
        boolean isFinal,

        /** Extension metadata. */
        Optional<Map<String, Object>> metadata
) implements MessageStreamResponseResult {}
