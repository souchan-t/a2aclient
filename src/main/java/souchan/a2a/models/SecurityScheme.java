package souchan.a2a.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;
import java.util.Optional;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SecurityScheme.APIKeySecurityScheme.class, name = "apiKry"),
        @JsonSubTypes.Type(value = SecurityScheme.HTTPAuthSecurityScheme.class, name = "http"),
        @JsonSubTypes.Type(value = SecurityScheme.OAuth2SecurityScheme.class, name = "oauth2"),
        @JsonSubTypes.Type(value = SecurityScheme.OpenIdConnectSecurityScheme.class, name = "openIdConnect")
})
public sealed interface SecurityScheme
        permits
        SecurityScheme.APIKeySecurityScheme,
        SecurityScheme.HTTPAuthSecurityScheme,
        SecurityScheme.OAuth2SecurityScheme,
        SecurityScheme.OpenIdConnectSecurityScheme {
    record APIKeySecurityScheme(
            String type,
            Optional<String> description,
            String name,
            String in
    ) implements SecurityScheme {
    }

    record HTTPAuthSecurityScheme(
            String type,
            Optional<String> description,
            String scheme,
            Optional<String> bearerFormat

    ) implements SecurityScheme {
    }

    record OAuth2SecurityScheme(
            String type,
            Optional<String> description,
            OAuthFlow oauth2

    ) implements SecurityScheme {
    }

    record OpenIdConnectSecurityScheme(
            String type,
            Optional<String> description,
            String openIdConnectUrl

    ) implements SecurityScheme {
    }

    record OAuthFlow(
            String authorizationUrl,
            String tokenUrl,
            Optional<String> refreshUrl,
            Map<String, String> scopes
    ) {
    }
}


