package tests;

import api.auth.AuthApi;
import io.qameta.allure.*;
import api.models.AuthResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.ApiEndpoints.LOGIN_PATH;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("login_tests")
@Owner("dmitry_endo")
@Feature("Аутентификация")
@DisplayName("Тесты API входа в профиль")
public class LoginTests extends BaseTest {

    String email = "eve.holt@reqres.in";
    String password = "cityslicka";
    String token = "QpwL5tke4Pnpja7X4";

    @Test
    @Story("Вход в профиль")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка успешного входа в профиль")
    void successfulLoginTest() {

        AuthResponseModel response =
                step("Выполняем API запрос на вход", () ->
                        AuthApi.successfulAuth(email, password, LOGIN_PATH));

        step("Проверяем ответ от сервера на наличие валидного токена", () -> {
            assertThat(response.getToken()).isEqualTo(token); // fix token check
        });
    }

    @Test
    @Story("Вход в профиль")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка входа в профиль без пароля")
    void missingPasswordLoginTest() {

        AuthResponseModel response =
                step("Выполняем API запрос на вход", () ->
                        AuthApi.unsuccessfulAuth(email, null, LOGIN_PATH));

        step("Проверяем ответ от сервера на наличие ожидаемой ошибки", () -> {
            assertThat(response.getError())
                    .as("Для входа в профиль необходим пароль")
                    .isEqualTo("Missing password");
        });
    }

    @Test
    @Story("Вход в профиль")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка входа в профиль без email'а")
    void missingEmailLoginTest() {

        AuthResponseModel response =
                step("Выполняем API запрос на вход", () ->
                        AuthApi.unsuccessfulAuth(null, password, LOGIN_PATH));

        step("Проверяем ответ от сервера на наличие ожидаемой ошибки", () -> {
            assertThat(response.getError())
                    .as("Для входа в профиль необходим email")
                    .isEqualTo("Missing email or username");
        });
    }
}
