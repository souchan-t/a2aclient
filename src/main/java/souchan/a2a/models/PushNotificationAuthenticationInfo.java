package souchan.a2a.models;

import java.util.List;
import java.util.Optional;

/**
 * Defines authentication details for push notifications.
 */
public record PushNotificationAuthenticationInfo(
        /* Supported authentication schemes - e.g. Basic, Bearer */
        List<String> schemes,

        /* Optional credentials */
        Optional<String> credentials
) {
}
