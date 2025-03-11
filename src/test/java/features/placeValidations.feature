Feature: Validation Place APIs

  Scenario: Verify if place is Succesfully added using AddPlaceAPI
    Given Add Place Payload
    When user calls "AddPlaceAPI" with POST http request
    Then the API call is success with status code 200
    And "status" in response body "OK"