package tests;

import config.ApiConfig;
import config.ConfigReader;
import config.ProjectConfig;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    public static final ApiConfig apiConfig = ConfigReader.getApiConfig();

    @BeforeAll
    static void baseConfigurationSetup() {
        ProjectConfig projectConfig = new ProjectConfig(apiConfig);
        projectConfig.setApiConfig();
    }
}
