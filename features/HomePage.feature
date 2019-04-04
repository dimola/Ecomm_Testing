Feature: Home page 

Scenario Outline: Verify that links are redirecting to correct place 
	Given Home page is loaded 
	When I click on a certain category "<category>" 
	Then I am redirected to the respective category "<page>" 
	Examples:
	|category|page     |
	|Books   |BooksPage|
	|Cds     |CdsPage  |


Scenario Outline: Verify that by clicking on the logo the user is redirected to Home page
    Given A "<page>" from the ACME website is loaded
    When I click on the logo
    Then I am redirected to Home page
    Examples:
    |page     |
    |BooksPage|
    |CdsPage  |
    |LoginPage|
