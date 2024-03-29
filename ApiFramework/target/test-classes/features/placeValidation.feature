Feature: Validating the Place APIs

@Addplace @Regression
Scenario Outline: Verify  if the place is  being successfully added  using AddPlaceAPI
	Given AddPlace payload with"<name>" "<language>" "<address>"
	When  user calls "addPlaceAPI"  with "POST" http request
	Then the API  call got success with status code  200
	And  "status" in response body is "OK"
	And "scope" in response body is "APP"
	And  verify  place_Id  created maps  to "<name>" using "getPlaceAPI"
	
	
Examples:
	|name   | language  |address			 |
	|BAhouse| French    | Eaton cross center |
	

@Deleteplace @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
 	When  user calls "deletePlaceAPI"  with "GET" http request
	Then the API  call got success with status code  200
	And  "status" in response body is "OK"