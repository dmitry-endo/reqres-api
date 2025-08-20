package utils;

import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;

public class TokenAssertions {

    public static void assertValidToken(String token) {
        assertThat(token)
                .isNotBlank()
                .hasSizeGreaterThanOrEqualTo(16)
                .matches("^[a-zA-Z0-9]+$");
    }

    private static AbstractStringAssert<?> assertThat(String token) {
        return Assertions.assertThat(token)
                .describedAs("Токен должен иметь длину >= 16 и содержать только символы латинского алфавита");
    }
}
