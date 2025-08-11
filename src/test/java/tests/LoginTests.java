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
@Feature("Auth")
@DisplayName("Test for API login")
public class LoginTests extends BaseTest {

    String email = "eve.holt@reqres.in";
    String password = "cityslicka";
    String token = "QpwL5tke4Pnpja7X4";

    @Test
    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check successful login")
    void successfulLoginTest() {

        AuthResponseModel response =
                step("Make API login request", () ->
                        AuthApi.successfulAuth(email, password, LOGIN_PATH));

        step("Check login response token", () -> {
            assertThat(response.getToken()).isEqualTo(token);
        });
    }

    @Test
    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check unsuccessful login with missing password")
    void missingPasswordLoginTest() {

        AuthResponseModel response =
                step("Make API login request", () ->
                        AuthApi.unsuccessfulAuth(email, null, LOGIN_PATH));

        step("Check login response error", () -> {
            assertThat(response.getError())
                    .as("There must be a password provided to login")
                    .isEqualTo("Missing password");
        });
    }

    @Test
    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check unsuccessful login with missing email")
    void missingEmailLoginTest() {

        AuthResponseModel response =
                step("Make API login request", () ->
                        AuthApi.unsuccessfulAuth(null, password, LOGIN_PATH));

        step("Check login response error", () -> {
            assertThat(response.getError())
                    .as("There must be an email provided to login")
                    .isEqualTo("Missing email or username");
        });
    }
}
