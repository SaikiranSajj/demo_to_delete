Feature: Validating Place API's

Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
  Given Add place PayLoad "<address>" "<language>" "<name>"
  When user calls "AddPlaceAPI" with "POST" HTTP request
  Then response code is 200
    And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_id created maps to "<name>" using "getPlaceAPI"
  
  Examples:
  | address | language | name  |
  | wakad   | hindi    | sai   |
  | aundh   | English  | kiran |
 

 