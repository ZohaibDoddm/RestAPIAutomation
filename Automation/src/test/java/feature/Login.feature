Feature: User is able to Login to the application

Scenario: new user default login
Given User is on landing page
When User login to application  with useername and password
Then Home page is displayed
And Cards are displayed