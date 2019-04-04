Feature: Home page 

Scenario Outline: Verify that links are redirecting to correct place 
	Given Home page is loaded 
	When I click on a certain category "<category>" 
	Then I am redirected to the respective category "<page>" 
	Examples: Main Categories
	|category|page     |
	|Books   |BooksPage|
	|Cds     |CdsPage  |