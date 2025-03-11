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
import resources.TestDataBuild;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinations extends CommonUtils {
    private RequestSpecification addPlaceRequest;
    private ResponseSpecification responseSpec;
    private Response response;
    TestDataBuild dataBuild = new TestDataBuild();

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {
        addPlaceRequest = given().spec(requestSpecification()).body(dataBuild.addPlacePayload());
    }

    @When("user calls {string} with POST http request")
    public void user_calls_with_post_http_request(String string) {
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = addPlaceRequest.queryParam("key","qaclick123").when().post("/maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response body {string}")
    public void in_response_body(String keyValue, String ExpectedValue) {
        JsonPath jsonPath = new JsonPath(response.asString());
        Assert.assertEquals(jsonPath.get(keyValue).toString(),ExpectedValue);

    }

}
