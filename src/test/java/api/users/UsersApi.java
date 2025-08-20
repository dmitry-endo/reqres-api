package api.users;

import api.models.UserRequestModel;
import api.models.UserResponseModel;
import api.models.UsersResponseModel;
import api.specs.DefaultSpecs;

import static api.ApiEndpoints.USERS_PATH;
import static api.ApiEndpoints.USER_BY_ID_PATH;
import static io.restassured.RestAssured.given;

public class UsersApi extends DefaultSpecs {

    public static UserResponseModel createUser(String name, String job) {
        UserRequestModel userData = new UserRequestModel(name, job);

        return given(defaultRequestSpec)
                .body(userData)

                .when()
                .post(USERS_PATH)

                .then()
                .spec(responseSpec201)
                .extract()
                .as(UserResponseModel.class);
    }

    public static UserResponseModel updateUserWithPut(String name, String job, String userId) {
        UserRequestModel userData = new UserRequestModel(name, job);

        return given(defaultRequestSpec)
                .pathParam("id", userId)
                .body(userData)

                .when()
                .put(USER_BY_ID_PATH)

                .then()
                .spec(responseSpec200)
                .extract()
                .as(UserResponseModel.class);
    }

    public static UserResponseModel updateUserWithPatch(String name, String job, String userId) {
        UserRequestModel userData = new UserRequestModel(name, job);

        return given(defaultRequestSpec)
                .pathParam("id", userId)
                .body(userData)

                .when()
                .patch(USER_BY_ID_PATH)

                .then()
                .spec(responseSpec200)
                .extract()
                .as(UserResponseModel.class);
    }

    public static void deleteUser(String userId) {
        given(defaultRequestSpec)
                .pathParam("id", userId)

                .when()
                .delete(USER_BY_ID_PATH)

                .then()
                .spec(responseSpec204);
    }

    public static UsersResponseModel getUsersListByPage(String pageNumber) {
        return given(defaultRequestSpec)
                .queryParam("page", pageNumber)

                .get(USERS_PATH)

                .then()
                .spec(responseSpec200)
                .extract()
                .as(UsersResponseModel.class);
    }
}
