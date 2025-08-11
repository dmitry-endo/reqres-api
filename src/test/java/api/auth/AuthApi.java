package api.auth;

import api.models.AuthRequestModel;
import api.models.AuthResponseModel;
import api.specs.DefaultSpecs;

import static io.restassured.RestAssured.given;

public class AuthApi extends DefaultSpecs {

    public static AuthResponseModel successfulAuth(String email, String password, String endpointPath) {
        AuthRequestModel authData = new AuthRequestModel();
        authData.setEmail(email);
        authData.setPassword(password);

        return given(defaultRequestSpec)
                .body(authData)

                .when()
                .post(endpointPath)

                .then()
                .spec(responseSpec200)
                .extract()
                .as(AuthResponseModel.class);
    }

    public static AuthResponseModel unsuccessfulAuth(String email, String password, String endpointPath) {
        AuthRequestModel authData = new AuthRequestModel();
        authData.setEmail(email);
        authData.setPassword(password);

        return given(defaultRequestSpec)
                .body(authData)

                .when()
                .post(endpointPath)

                .then()
                .spec(responseSpec400)
                .extract()
                .as(AuthResponseModel.class);
    }
}
