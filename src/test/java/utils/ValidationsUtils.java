package utils;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class ValidationsUtils {

    public static void assertValidToken(String token) {
        assertThat(token)
                .as("Токен должен иметь длину >= 16 и содержать только символы латинского алфавита")
                .isNotBlank()
                .hasSizeGreaterThanOrEqualTo(16)
                .matches("^[a-zA-Z0-9]+$");
    }

    public static void assertMissingPasswordError(String error, String description) {
        assertThat(error)
                .as(description)
                .isEqualTo("Missing password");
    }

    public static void assertMissingEmailError(String error, String description) {
        assertThat(error)
                .as(description)
                .isEqualTo("Missing email or username");
    }

    public static void assertValidIsoDateTime(String dateTime) {
        assertThatCode(() -> Instant.parse(dateTime))
                .as("Дата должна быть в формате ISO 8601")
                .doesNotThrowAnyException();

        assertThat(dateTime)
                .as("Дата не должна быть пустой")
                .isNotBlank();
    }
}
