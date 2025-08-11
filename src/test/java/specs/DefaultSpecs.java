package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DefaultSpecs extends BaseSpec {

    public static final RequestSpecification defaultRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("x-api-key", API_KEY)
            .contentType(JSON);

    public static final ResponseSpecification responseSpec200 = responseSpec(200);
    public static final ResponseSpecification responseSpec201 = responseSpec(201);
    public static final ResponseSpecification responseSpec204 = responseSpec(204);
    public static final ResponseSpecification responseSpec400 = responseSpec(400);
}
