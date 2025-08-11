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

@Tag("login_tests")
@Owner("dmitry_endo")
@DisplayName("Test for API login")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Check successful login")
    void successfulLoginTest() {
        AuthRequestModel authData = new AuthRequestModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        AuthResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                        .body(authData)

                        .when()
                        .post(LOGIN_PATH)

                        .then()
                        .spec(responseSpec200)
                        .extract().as(AuthResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
        });
    }

    @Test
    @DisplayName("Check unsuccessful login with missing password")
    void missingPasswordLoginWithSpecsTest() {
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
    @DisplayName("Check unsuccessful login with missing email")
    void missingEmailRegisterWithSpecsTest() {
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
