package souchan.a2a.models;

import lombok.Builder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents an artifact generated for a task.
 */
@Builder
public record Artifact(
        /* Unique identifier for the artifact. */
        String artifactId,

        /* Optional name for the artifact. */
        Optional<String> name,

        /* Optional description for the artifact. */
        Optional<String> description,

        /* Artifact parts. */
        List<Part> parts,

        /* Extension metadata. */
        Optional<Map<String, Object>> metadata,

        /* The URIs of extensions that are present or contributed to this Artifact. */
        Optional<List<String>> extensions
) {
}
