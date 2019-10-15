package helpers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import com.typesafe.config.Config;
import utilities.ConfigLoader;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jayway.restassured.RestAssured.given;

public class ApiHelper {
    private static final Logger log = LoggerFactory.getLogger(ApiHelper.class);
    static Config conf = ConfigLoader.load();

    static String baseapiurl = conf.getString("baseURL");

    public static Gson gson;


    protected static RequestSpecification getApiUrl() {
        RestAssured.baseURI = URI.create(baseapiurl).toString();
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    //Custom Gson config to override Default Gson configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }
}
