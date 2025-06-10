package souchan.a2a.models;

import lombok.Builder;

import java.util.Map;
import java.util.Optional;

@Builder
public record TaskIdParams(
        String id,
        Optional<Map<String, Object>> metadata
) implements RequestParams {
}
