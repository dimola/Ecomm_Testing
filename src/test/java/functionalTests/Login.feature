Feature: Login 
Scenario: Login with valid credentials 
	Given Login page is loaded 
	When I type valid username "student1" 
	And I type valid password "stpass1" 
	And I click on Login button 
	Then I should be successfully logged in 
	
Scenario: Login with invalid credentials 
	Given Login page is loaded 
	When I type invalid username "" 
	And I type invalid password "" 
	And I click on Login button 
	Then I am not logged in the system 
	
Scenario:
Try to loggin three times with invalid credentials results in an error message 
	Given Login page is loaded 
	When I try to login three times with invalid credentials "asd" and "asd" 
	Then An error message is displayed