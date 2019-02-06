Feature: Login 
 
 Scenario: Login with valid credentials 
	Given Login page is loaded
	When I type username "student1"
	And I type password "stpass1"
	And I click on Login button
	Then I should be successfully logged in

 Scenario: 
	Login with invalid credentials
	Given Login page is loaded
	When I type username "<username>" 
	And I type password "<password>"
	And I click on Login button 
	Then I am not logged in the system  
 
Scenario: Login with missing credentials 
	Given Login page is loaded 
	When I type username "" 
	And I type password "" 
	And I click on Login button 
	Then I am not logged in the system 
	
Scenario: Try to loggin three times with invalid credentials results in an error message 
	Given Login page is loaded 
	When I try to login three times with invalid credentials "asd" and "asd" 
	Then An error message is displayed 
 
 Scenario: Verify that user is able to logout 
 	Given Login page is loaded  
	And I am logged in with credentials "student1" and "stpass1" 
	When I click on Logout
	And I confirm the logout
	Then I am succesfully logged out 
