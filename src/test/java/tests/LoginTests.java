package tests;

import api.auth.AuthApi;
import io.qameta.allure.*;
import api.models.AuthResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.ApiEndpoints.LOGIN_PATH;
import static io.qameta.allure.Allure.step;
import static utils.ValidationsUtils.*;

@Tag("login_tests")
@Owner("dmitry_endo")
@Feature("Аутентификация")
@DisplayName("Тесты API входа в профиль")
public class LoginTests extends BaseTest {

    String email = "eve.holt@reqres.in";
    String password = "cityslicka";

    @Test
    @Story("Вход в профиль")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка успешного входа в профиль")
    void successfulLoginTest() {

        AuthResponseModel response =
                step("Выполняем API запрос на вход", () ->
                        AuthApi.successfulAuth(email, password, LOGIN_PATH));

        step("Проверяем ответ от сервера на наличие валидного токена", () -> {
            assertValidToken(response.getToken());
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
            assertMissingPasswordError(response.getError(), "Для входа в профиль необходим пароль");
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
            assertMissingEmailError(response.getError(), "Для входа в профиль необходим email");
        });
    }
}
