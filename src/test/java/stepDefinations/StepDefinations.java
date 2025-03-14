package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.CommonUtils;
import resources.MapAPIresources;
import resources.TestDataBuild;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinations extends CommonUtils {
    RequestSpecification addPlaceRequest;
    RequestSpecification getPlaceRequest;
    ResponseSpecification responseSpec;
    Response response;
    static String place_id;
    TestDataBuild dataBuild = new TestDataBuild();
    JsonPath jsonPath;

    @Given("Add Place Payload")
    public void delete_place_payload() throws IOException {
        addPlaceRequest = given().spec(requestSpecification()).body(dataBuild.addPlacePayload("Frontline house","French-IN","29, side layout, cohen 09"));
    }

    @Given("Delete Place Payload")
    public void add_place_payload() throws IOException {
        addPlaceRequest = given().spec(requestSpecification()).body(dataBuild.deletePlacePayLoad(place_id));
    }


    @Given("Add Place Payload with {string}, {string} and {string}")
    public void add_place_with_updated_values(String name,String language,String address) throws IOException {
        addPlaceRequest = given().spec(requestSpecification()).body(dataBuild.addPlacePayload(name,language,address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource,String method) {
        MapAPIresources resourceAPI = MapAPIresources.valueOf(resource);
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("post")) {
            response = addPlaceRequest.when().post(resourceAPI.getAPIresource());
        } else if (method.equalsIgnoreCase("get")) {
            response = getPlaceRequest.when().get(resourceAPI.getAPIresource());
        }

    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        response.then().spec(responseSpec).extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response body {string}")
    public void in_response_body(String keyValue, String ExpectedValue) {
        Assert.assertEquals(getJsonPath(response,keyValue),ExpectedValue);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
        place_id = getJsonPath(response,"place_id");
        getPlaceRequest = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_post_http_request(resource,"GET");
        Assert.assertEquals(getJsonPath(response,"name"),name);
    }

}
