package souchan.a2a.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;

import java.util.Map;
import java.util.Optional;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "kind",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Part.TextPart.class, name = "text"),
        @JsonSubTypes.Type(value = Part.FilePart.class, name = "file"),
        @JsonSubTypes.Type(value = Part.DataPart.class, name = "data"),

})
public sealed interface Part permits Part.TextPart, Part.FilePart, Part.DataPart {
    @Builder
    record TextPart(
            String kind,
            String text,
            Optional<Map<String, Object>> metadata
    ) implements Part {
        public TextPart {
            if (!kind.equals("text")) {
                throw new IllegalArgumentException("kind must be 'text'");
            }
        }
    }

    @Builder
    record FilePart(
            String kind,
            FileBase file,
            Optional<Map<String, Object>> metadata
    ) implements Part {
        public FilePart {
            if (!kind.equals("file")) {
                throw new IllegalArgumentException("kind must be 'file'");
            }
        }
    }

    @Builder
    record DataPart(
            String kind,
            Map<String, Object> data,
            Optional<Map<String, Object>> metadata
    ) implements Part {
        public DataPart {
            if (!kind.equals("data")) {
                throw new IllegalArgumentException("kind must be 'data");
            }
        }
    }
}
