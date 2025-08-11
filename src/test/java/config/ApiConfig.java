package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties"
})
public interface ApiConfig extends Config {

    @Key("baseUri")
    @DefaultValue("https://reqres.in/")
    String getBaseUri();

    @Key("basePath")
    @DefaultValue("/api")
    String getBasePath();

    @Key("apiKey")
    String getApiKey();
}
