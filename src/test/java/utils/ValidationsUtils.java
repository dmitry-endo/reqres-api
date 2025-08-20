package utils;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class ValidationsUtils {

    public static void assertValidToken(String token) {
        assertThat(token)
                .describedAs("Токен должен иметь длину >= 16 и содержать только символы латинского алфавита")
                .isNotBlank()
                .hasSizeBetween(16, 256)
                .matches("^[a-zA-Z0-9]+$");
    }

    public static void assertValidIsoDateTime(String dateTime) {
        assertThatCode(() -> Instant.parse(dateTime))
                .describedAs("Дата должна быть в формате ISO 8601")
                .doesNotThrowAnyException();

        assertThat(dateTime)
                .describedAs("Дата не должна быть пустой и отвечала заданной структуре")
                .isNotBlank();
    }
}
