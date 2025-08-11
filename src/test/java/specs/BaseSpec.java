package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import reqres_api_tests.TestBase;

import static io.restassured.filter.log.LogDetail.ALL;

public class BaseSpec extends TestBase {

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL)
                .build();
    }
}
