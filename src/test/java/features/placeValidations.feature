Feature: Validation Place APIs

  @AddPlace
  Scenario: Verify if place is Succesfully added using AddPlaceAPI
    Given Add Place Payload
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body "OK"
    Then verify place_id created maps to "Frontline house" using "getPlaceAPI"

  @DeletePlace
 Scenario: Verify if Delete Place functionality is working
    Given Delete Place Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body "OK"

    @ScenarioOutline
  Scenario Outline:  Verify if place is Succesfully added using AddPlaceAPI followed by DeletePlaceAPI for specified examples
    Given Add Place Payload with "<name>", "<language>" and "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body "OK"
    Then verify place_id created maps to "<name>" using "getPlaceAPI"
    Given Delete Place Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body "OK"
    Examples:
    |    name      |   language    |  address    |
    |    BoatHouse |  English      |Telangana    |
    |    BBHouse   |  Tamil        |Chennai      |
