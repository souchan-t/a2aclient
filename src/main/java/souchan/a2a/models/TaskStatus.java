package souchan.a2a.models;

import lombok.Builder;

import java.util.Optional;

/**
 * TaskState and accompanying message.
 */
@Builder
public record TaskStatus(
        /* Current state of the task. */
        TaskState state,

        /* Additional status updates for client */
        Optional<Message> message,

        /*
         * ISO 8601 datetime string when the status was recorded.
         * @example "2023-10-27T10:00:00Z"
         */
        Optional<String> timestamp
) {
}
