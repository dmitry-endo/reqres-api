package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import tests.BaseTest;

import static io.restassured.filter.log.LogDetail.ALL;

public class BaseSpec extends BaseTest {

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL)
                .build();
    }
}
