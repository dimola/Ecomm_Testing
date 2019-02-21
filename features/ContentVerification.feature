Feature: Content verification 
Scenario: Observe Books page 
	When I redirect to books page 
	Then I should see the books page 
	And I should see all book filtering options