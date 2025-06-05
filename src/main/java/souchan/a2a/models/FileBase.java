package souchan.a2a.models;

import java.util.Optional;

public record FileBase(
        Optional<String> name,
        Optional<String> mimeType,
        Optional<String> bytes,
        Optional<String> uri
) {
}