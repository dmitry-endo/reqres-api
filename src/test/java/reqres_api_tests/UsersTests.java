package reqres_api_tests;

import io.qameta.allure.Owner;
import models.UserRequestModel;
import models.UserResponseModel;
import models.UsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DefaultSpecs.*;

@Tag("users_tests")
@Owner("dmitry_endo")
@DisplayName("Test for API users interactions")
public class UsersTests extends TestBase {

    @Test
    @DisplayName("Check creating user")
    void createUserTest() {
        UserRequestModel userData = new UserRequestModel();
        userData.setName("helen");
        userData.setJob("refugee");

        UserResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                        .body(userData)

                .when()
                        .post(USERS_PATH)

                .then()
                        .spec(responseSpec201)
                        .extract().as(UserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getId())
                    .as("User ID must not be null").isNotNull();
            assertThat(response.getCreatedAt())
                    .as("Creation date must not be null").isNotNull();
            assertThat(response.getName()).isEqualTo(userData.getName());
            assertThat(response.getJob()).isEqualTo(userData.getJob());
        });
    }

    @Test
    @DisplayName("Check updating user with PUT method")
    void updateUserPutMethodTest() {
        UserRequestModel userData = new UserRequestModel();
        userData.setName("alex");
        userData.setJob("manager");

        UserResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                        .pathParam("id", "2")
                        .body(userData)

                .when()
                        .put(USER_BY_ID_PATH)

                .then()
                        .spec(responseSpec200)
                        .extract().as(UserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getUpdatedAt()).isNotNull();
            assertThat(response.getName()).isEqualTo(userData.getName());
            assertThat(response.getJob()).isEqualTo(userData.getJob());
        });
    }

    @Test
    @DisplayName("Check updating user with PATCH method")
    void updateUserPatchMethodTest() {
        UserRequestModel userData = new UserRequestModel();
        userData.setName("max");
        userData.setJob("driver");

        UserResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                        .pathParam("id", "3")
                        .body(userData)

                .when()
                        .patch(USER_BY_ID_PATH)

                .then()
                        .spec(responseSpec200)
                        .extract().as(UserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getUpdatedAt()).isNotNull();
            assertThat(response.getName()).isEqualTo(userData.getName());
            assertThat(response.getJob()).isEqualTo(userData.getJob());
        });
    }

    @Test
    @DisplayName("Check deleting user")
    void deleteUserTest() {
        step("Make request", () ->
                given(defaultRequestSpec)
                        .pathParam("id", "2")

                .when()
                        .delete(USER_BY_ID_PATH)

                .then()
                        .spec(responseSpec204));
    }

    @Test
    @DisplayName("Check data field array size in users list")
    void checkUsersDataSizeTest() {
        UsersResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                        .queryParam("page", "2")
                        .get(USERS_PATH)

                .then()
                        .spec(responseSpec200)
                        .extract().as(UsersResponseModel.class));

        step("Check response", () ->
                assertThat(response.getData().size()).isEqualTo(6));
    }

    @Test
    @DisplayName("Check support field content in users list")
    void checkUsersSupportContentTest() {
        String supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral";
        String supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";

        UsersResponseModel response = step("Make request", () ->
                given(defaultRequestSpec)
                        .queryParam("page", "2")
                        .get(USERS_PATH)

                .then()
                        .spec(responseSpec200)
                        .extract().as(UsersResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getSupport().getUrl()).isEqualTo(supportUrl);
            assertThat(response.getSupport().getText()).isEqualTo(supportText);
        });
    }
}
