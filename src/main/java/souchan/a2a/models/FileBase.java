package souchan.a2a.models;

import lombok.Builder;

import java.util.Optional;

@Builder
public record FileBase(
        Optional<String> name,
        Optional<String> mimeType,
        Optional<String> bytes,
        Optional<String> uri
) {
}