Feature: Login 
Scenario: Login with valid credentials
Given Login page is loaded
When I type valid username "student1"
And I type valid password "stpass1"
And I click on Login button
Then I should be successfully logged in


Scenario: Login with invalid credentials
Given Login page is loaded
When I type invalid username "Test123"
And I type invalid password "Test123"
And I click on Login button
Then I am not logged in the system