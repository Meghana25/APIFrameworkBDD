package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class CommonUtils {
    public RequestSpecification requestSpecification() throws FileNotFoundException {
        PrintStream log = new PrintStream(new File("Request-Response.txt"));
        return new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build();
    }
}
