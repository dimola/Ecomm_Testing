Feature: Login 
Scenario: Login with valid credentials
Given Login page is loaded
When I type valid username
And I type valid password
And I click on Login button
Then I should be successfully logged in
