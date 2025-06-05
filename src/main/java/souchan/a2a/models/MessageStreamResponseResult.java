package souchan.a2a.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "kind"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Task.class, name = "task"),
        @JsonSubTypes.Type(value = Message.class, name = "message"),
        @JsonSubTypes.Type(value = TaskStatusUpdateEvent.class, name="status-update"),
        @JsonSubTypes.Type(value = TaskArtifactUpdateEvent.class, name="artifact-update")
})
public sealed interface MessageStreamResponseResult permits
    Task, Message, TaskStatusUpdateEvent, TaskArtifactUpdateEvent
{
}
