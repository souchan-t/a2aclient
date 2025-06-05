package souchan.a2a.models;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents the possible states of a Task.
 */
public enum TaskState {
    Submitted("submitted"),
    Working("working"),
    InputRequired("input-required"),
    Completed("completed"),
    Canceled("canceled"),
    Failed("failed"),
    Rejected("rejected"),
    AuthRequired("auth-required"),
    Unknown("unknown");

    private final String value;

    TaskState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
