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

@Tag("registration_tests")
@Owner("dmitry_endo")
@Feature("Auth")
@DisplayName("Test for API registration")
public class RegistrationTests extends BaseTest {

    String email = "eve.holt@reqres.in";
    String password = "cityslicka";
    String token = "QpwL5tke4Pnpja7X4";

    @Test
    @Story("Registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check successful registration")
    void successfulRegisterTest() {

        AuthResponseModel response =
                step("Make API registration request", () ->
                        AuthApi.successfulAuth(email, password, REGISTER_PATH));

        step("Check registration response token and ID", () -> {
            assertThat(response.getToken()).isEqualTo(token);
            assertThat(response.getId())
                    .as("User ID must not be null").isNotNull();
        });
    }

    @Test
    @Story("Registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check unsuccessful registration with missing password")
    void missingPasswordRegisterTest() {

        AuthResponseModel response =
                step("Make API registration request", () ->
                        AuthApi.unsuccessfulAuth(email, null, REGISTER_PATH));

        step("Check registration response error", () -> {
            assertThat(response.getError())
                    .as("There must be a password provided to registration")
                    .isEqualTo("Missing password");
        });
    }

    @Test
    @Story("Registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check unsuccessful registration with missing email")
    void missingEmailRegisterTest() {

        AuthResponseModel response =
                step("Make API registration request", () ->
                        AuthApi.unsuccessfulAuth(null, password, REGISTER_PATH));

        step("Check registration response error", () -> {
            assertThat(response.getError())
                    .as("There must be an email provided to registration")
                    .isEqualTo("Missing email or username");
        });
    }
}
