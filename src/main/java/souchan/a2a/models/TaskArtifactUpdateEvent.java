package souchan.a2a.models;

import java.util.Map;
import java.util.Optional;

/**
 * Sent by server during sendStream or subscribe requests.
 */
public record TaskArtifactUpdateEvent(
        /** Task id */
        String taskId,

        /** The context the task is associated with */
        String contextId,

        /** Event type */
        String kind,

        /** Generated artifact */
        Artifact artifact,

        /** Indicates if this artifact appends to a previous one */
        Optional<Boolean> append,

        /** Indicates if this is the last chunk of the artifact */
        Optional<Boolean> lastChunk,

        /** Extension metadata. */
        Optional<Map<String, Object>> metadata
) implements MessageStreamResponseResult {}
