package config;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    @Getter
    private static final ApiConfig apiConfig =
            ConfigFactory.create(
                    ApiConfig.class,
                    System.getProperties()
            );
}
