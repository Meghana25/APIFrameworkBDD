package resources;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class CommonUtils {
    private static RequestSpecification requestSpecification;
    public RequestSpecification requestSpecification() throws IOException {
        if(requestSpecification == null) {
            PrintStream log = new PrintStream(new File("Request-Response.txt"));
            requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalProperties().getProperty("baseUrl")).addQueryParam("key","qaclick123").setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
        }
        return requestSpecification;
    }
    public Properties getGlobalProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/global.properties");
        properties.load(fileInputStream);
        return properties;
    }

    public String getJsonPath(Response response, String key)
    {
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.get(key).toString();
    }
}
