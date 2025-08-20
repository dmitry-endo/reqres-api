package tests;

import api.auth.AuthApi;
import io.qameta.allure.*;
import api.models.AuthResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.ApiEndpoints.REGISTER_PATH;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.TokenAssertions.assertValidToken;

@Tag("registration_tests")
@Owner("dmitry_endo")
@Feature("Аутентификация")
@DisplayName("Тесты API регистрации")
public class RegistrationTests extends BaseTest {

    String email = "eve.holt@reqres.in";
    String password = "cityslicka";

    @Test
    @Story("Регистрация")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка успешной регистрации")
    void successfulRegisterTest() {

        AuthResponseModel response =
                step("Выполняем API запрос на регистрацию", () ->
                        AuthApi.successfulAuth(email, password, REGISTER_PATH));

        step("Проверяем ответ от сервера на наличие валидного токена и ID", () -> {
            assertValidToken(response.getToken());
            assertThat(response.getId())
                    .as("У пользователя всегда должен присутствовать ID").isNotNull();
        });
    }

    @Test
    @Story("Регистрация")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка регистрации без пароля")
    void missingPasswordRegisterTest() {

        AuthResponseModel response =
                step("Выполняем API запрос на регистрацию", () ->
                        AuthApi.unsuccessfulAuth(email, null, REGISTER_PATH));

        step("Проверяем ответ от сервера на наличие ожидаемой ошибки", () -> {
            assertThat(response.getError())
                    .as("Для регистрации необходим пароль")
                    .isEqualTo("Missing password");
        });
    }

    @Test
    @Story("Регистрация")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка регистрации без email'а")
    void missingEmailRegisterTest() {

        AuthResponseModel response =
                step("Выполняем API запрос на регистрацию", () ->
                        AuthApi.unsuccessfulAuth(null, password, REGISTER_PATH));

        step("Проверяем ответ от сервера на наличие ожидаемой ошибки", () -> {
            assertThat(response.getError())
                    .as("Для регистрации необходим email")
                    .isEqualTo("Missing email or username");
        });
    }
}
