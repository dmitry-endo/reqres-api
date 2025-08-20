package tests;

import api.users.UsersApi;
import io.qameta.allure.*;
import api.models.UserResponseModel;
import api.models.UsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("users_tests")
@Owner("dmitry_endo")
@Feature("Взаимодействие с пользователями")
@DisplayName("Тесты на взаимодействие с пользователями")
public class UsersTests extends BaseTest {

    String name = "helen";
    String job = "dancer";
    String userId = "2";
    String usersListPageNum = "2";

    @Test
    @Story("CRUD операции")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка создания пользователя")
    void createUserTest() {
        UserResponseModel response =
                step("Выполняем API запрос на создание", () ->
                                UsersApi.createUser(name, job));

        step("Проверяем ответ от сервера", () -> {
            assertThat(response.getId())
                    .as("У пользователя всегда должен присутствовать ID").isNotNull();
            assertThat(response.getCreatedAt())
                    .as("Дата создания не должна равняться null").isNotNull();
            assertThat(response.getName()).isEqualTo(name);
            assertThat(response.getJob()).isEqualTo(job);
        });
    }

    @Test
    @Story("CRUD операции")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка обновления записи пользователя методом PUT")
    void updateUserPutMethodTest() {

        UserResponseModel response =
                step("Выполняем API запрос методом PUT", () ->
                        UsersApi.updateUserWithPut(name, job, userId));

        step("Проверяем ответ от сервера", () -> {
            assertThat(response.getUpdatedAt())
                    .as("Дата обновления не должна равняться null").isNotNull();
            assertThat(response.getName()).isEqualTo(name);
            assertThat(response.getJob()).isEqualTo(job);
        });
    }

    @Test
    @Story("CRUD операции")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка обновления записи пользователя методом PATCH")
    void updateUserPatchMethodTest() {

        UserResponseModel response =
                step("Выполняем API запрос методом PATCH", () ->
                        UsersApi.updateUserWithPatch(name, job, userId));

        step("Проверяем ответ от сервера", () -> {
            assertThat(response.getUpdatedAt())
                    .as("Дата обновления не должна равняться null").isNotNull();
            assertThat(response.getName()).isEqualTo(name);
            assertThat(response.getJob()).isEqualTo(job);
        });
    }

    @Test
    @Story("CRUD операции")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка удаления пользователя")
    void deleteUserTest() {
        step("Выполняем API запрос на удаление", () -> UsersApi.deleteUser(userId));
    }

    @Test
    @Story("Список пользователей")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка размера массива data в списке пользователей")
    void checkUsersDataSizeTest() {
        UsersResponseModel response =
                step("Выполняем API запрос на получение списка пользователей", () ->
                        UsersApi.getUsersListByPage(usersListPageNum));

        step("Проверяем размера массива data в ответе от сервера", () ->
                assertThat(response.getData().size()).isEqualTo(6));
    }

    @Test
    @Story("Список пользователей")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка содержимого массива support в списке пользователей")
    void checkUsersSupportContentTest() {
        String supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral";
        String supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";

        UsersResponseModel response =
                step("Выполняем API запрос на получение списка пользователей", () ->
                        UsersApi.getUsersListByPage(usersListPageNum));

        step("Проверяем содержимое массива support в ответе от сервера", () -> {
            assertThat(response.getSupport().getUrl()).isEqualTo(supportUrl);
            assertThat(response.getSupport().getText()).isEqualTo(supportText);
        });
    }
}
