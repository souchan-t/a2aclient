package souchan.a2a.models;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a task executed or tracked by the agent.
 */
public record Task(
        /** Unique identifier for the task */
        String id,

        /** Server-generated id for contextual alignment across interactions */
        String contextId,

        /** Current status of the task */
        TaskStatus status,

        /** History of messages associated with the task */
        Optional<List<Message>> history,

        /** Collection of artifacts created by the agent. */
        Optional<List<Artifact>> artifacts,

        /** Extension metadata. */
        Optional<Map<String, Object>> metadata,

        /** Event type */
        String kind
) implements SendMessageResponseResult, MessageStreamResponseResult {
    public Task {
        if (!"task".equals(kind)) {
            throw new IllegalArgumentException("kind must be 'task'");
        }
    }
}
