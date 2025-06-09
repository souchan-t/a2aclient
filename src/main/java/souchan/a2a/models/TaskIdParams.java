package souchan.a2a.models;

import java.util.Map;
import java.util.Optional;

public record TaskIdParams(
        String id,
        Optional<Map<String, Object>> metadata
) implements RequestParams {
}
