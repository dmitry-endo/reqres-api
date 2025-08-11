package config;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

public class ProjectConfig {

    private final ApiConfig apiConfig;

    public ProjectConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public ProjectConfig() { // no-args constructor
        this(ConfigFactory.create(ApiConfig.class, System.getProperties()));
    }

    public void setApiConfig() {
        RestAssured.baseURI = apiConfig.getBaseUri();
        RestAssured.basePath = apiConfig.getBasePath();
    }

    public String getApiKey() {
        return apiConfig.getApiKey();
    }
}
