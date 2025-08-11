package reqres_api_tests;

import io.qameta.allure.Owner;
import models.AuthRequestModel;
import models.AuthResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DefaultSpecs.*;

@Tag("registration_tests")
@Owner("dmitry_endo")
@DisplayName("Test for API registration")
public class RegistrationTests extends TestBase {

    @Test
    @DisplayName("Check successful registration")
    void successfulRegisterTest() {
        AuthRequestModel authData = new AuthRequestModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        AuthResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                    .body(authData)

                .when()
                    .post(REGISTER_PATH)

                .then()
                    .spec(responseSpec200)
                    .extract().as(AuthResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
            assertThat(response.getId())
                    .as("User ID must not be null").isNotNull();
        });
    }

    @Test
    @DisplayName("Check unsuccessful registration with missing password")
    void missingPasswordRegisterTest() {
        AuthRequestModel authData = new AuthRequestModel();
        authData.setEmail("eve.holt@reqres.in");

        AuthResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                    .body(authData)

                .when()
                    .post(REGISTER_PATH)

                .then()
                    .spec(responseSpec400)
                    .extract().as(AuthResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getError()).isEqualTo("Missing password");
        });
    }

    @Test
    @DisplayName("Check unsuccessful registration with missing email")
    void missingEmailRegisterTest() {
        AuthRequestModel authData = new AuthRequestModel();
        authData.setPassword("pistol");

        AuthResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                    .body(authData)

                .when()
                    .post(REGISTER_PATH)

                .then()
                    .spec(responseSpec400)
                    .extract().as(AuthResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getError()).isEqualTo("Missing email or username");
        });
    }
}
