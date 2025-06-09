package souchan.a2a.models;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a single message exchanged between user and agent.
 */
public record Message(
        /* Message sender's role */
        Role role,

        /* Message content */
        List<Part> parts,

        /* Extension metadata. */
        Optional<Map<String, Object>> metadata,

        /* The URIs of extensions that are present or contributed to this Message. */
        Optional<List<String>> extensions,

        /* List of tasks referenced as context by this message. */
        Optional<List<String>> referenceTaskIds,

        /* Identifier created by the message creator */
        String messageId,

        /* Identifier of task the message is related to */
        Optional<String> taskId,

        /* The context the message is associated with */
        Optional<String> contextId,

        /* Event type */
        String kind
) implements SendMessageResponseResult, MessageStreamResponseResult {
    public Message {
        if (!"message".equals(kind)) {
            throw new IllegalArgumentException("kind must be 'message'");
        }
    }

    /**
     * Enum representing sender roles in a message.
     */
    public enum Role {
        USER("user"),
        AGENT("agent");

        private final String value;

        Role(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
