package tests;

import api.users.UsersApi;
import io.qameta.allure.Owner;
import api.models.UserResponseModel;
import api.models.UsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("users_tests")
@Owner("dmitry_endo")
@DisplayName("Test for API users interactions")
public class UsersTests extends BaseTest {

    String name = "helen";
    String job = "dancer";
    String userId = "2";
    String usersPageNum = "2";

    @Test
    @DisplayName("Check creating user")
    void createUserTest() {
        UserResponseModel response =
                step("Make API create user request", () ->
                                UsersApi.createUser(name, job));

        step("Check create user response", () -> {
            assertThat(response.getId())
                    .as("User ID must not be null").isNotNull();
            assertThat(response.getCreatedAt())
                    .as("Creation date must not be null").isNotNull();
            assertThat(response.getName()).isEqualTo(name);
            assertThat(response.getJob()).isEqualTo(job);
        });
    }

    @Test
    @DisplayName("Check updating user with PUT method")
    void updateUserPutMethodTest() {

        UserResponseModel response =
                step("Make API update user request with PUT", () ->
                        UsersApi.updateUserWithPut(name, job, userId));

        step("Check update user response", () -> {
            assertThat(response.getUpdatedAt()).isNotNull();
            assertThat(response.getName()).isEqualTo(name);
            assertThat(response.getJob()).isEqualTo(job);
        });
    }

    @Test
    @DisplayName("Check updating user with PATCH method")
    void updateUserPatchMethodTest() {

        UserResponseModel response =
                step("Make API update user request with PATCH", () ->
                        UsersApi.updateUserWithPatch(name, job, userId));

        step("Check update user response", () -> {
            assertThat(response.getUpdatedAt()).isNotNull();
            assertThat(response.getName()).isEqualTo(name);
            assertThat(response.getJob()).isEqualTo(job);
        });
    }

    @Test
    @DisplayName("Check deleting user")
    void deleteUserTest() {
        step("Make request", () -> UsersApi.deleteUser(userId));
    }

    @Test
    @DisplayName("Check data field array size in users list")
    void checkUsersDataSizeTest() {
        UsersResponseModel response =
                step("Make API get users list request", () ->
                        UsersApi.getUsersListByPage(usersPageNum));

        step("Check response for data field size", () ->
                assertThat(response.getData().size()).isEqualTo(6));
    }

    @Test
    @DisplayName("Check support field content in users list")
    void checkUsersSupportContentTest() {
        String supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral";
        String supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";

        UsersResponseModel response =
                step("Make API get users list request", () ->
                        UsersApi.getUsersListByPage(usersPageNum));

        step("Check response for support field content", () -> {
            assertThat(response.getSupport().getUrl()).isEqualTo(supportUrl);
            assertThat(response.getSupport().getText()).isEqualTo(supportText);
        });
    }
}
