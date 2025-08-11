package reqres_api_tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    protected static final String API_KEY = "reqres-free-v1";
    protected static final String REGISTER_PATH = "/register";
    protected static final String LOGIN_PATH = "/login";
    protected static final String USERS_PATH = "/users";
    protected static final String USER_BY_ID_PATH = "/users/{id}";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "/api";
    }
}
