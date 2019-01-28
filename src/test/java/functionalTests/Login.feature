Feature: Login 
Scenario: Login with valid credentials
Given Login page is loaded
When I type valid username "student1"
And I type valid password "stpass1"
And I click on Login button
Then I should be successfully logged in
