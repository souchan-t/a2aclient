package souchan.a2a.models;

import java.util.Map;
import java.util.Optional;

public record TaskQueryParams(
        String id,
        Optional<Integer> historyLength,
        Optional<Map<String, Object>> metadata
) implements RequestParams {
}
