Feature: Content verification

Scenario: Observe Home page
	When I redirect to home page
	Then I should see the home page
	And I should see Books and CDs categories

Scenario: Observe Books page
	When I redirect to books page
	Then I should see the books page
	And I should see all book filtering options

Scenario: Observe CDs page
	When I redirect to cds page
	Then I should see the cds page
	And I should see all cds filtering options

Scenario Outline: Observe Product Catalog page when there are Books in the respective category
	When I redirect to not empty "<bookCategory>" category of Books
	Then I should see the book category page
	And I should see all books' elements in the category page
	Examples: Book Categories
		|bookCategory    |
		|Art             |
		|Biographies     |
		|Children’s books    |
		|Finance             |
		|Cooking, food & wine|
		|Entertainment|
		|Mind & body|
		|Hobbies|
		|Home & garden|
		|Science & nature|
		|Science fiction|

Scenario Outline: Observe Product Catalog page when there are Cds in the respective category
	When I redirect to not empty "<cdsCategory>" category of CDs
	Then I should see the cd category page
	And I should see all cds' elements in the category page
	Examples: CDs Categories
		|cdsCategory|
		|Alternative        |
		|Blues |
		|Children’s music|
		|Classical|
		|Country|
		|Dance & DJ|
		|Folk|
		|New age|
		|Pop|
		|Soul|

Scenario Outline: Observe Product Catalog page when there are no books in the respective category
	When I redirect to empty "<bookCategory>" category of Books
	Then I should see the empty book category page
	Examples: Empty Book Categories
		|bookCategory|
		|History         |
		|Horror |
		|Literature & fiction|
		|Mystery & thrillers|
		|Non-fiction|
		|Professional & technical|
		|Reference|
		|Religion & spirituality|
		|Sports & outdoors|
		|Travel|

Scenario Outline: Observe Product Catalog page when there are no CDs in the respective category
	When I redirect to empty "<cdsCategory>" category of CDs
	Then I should see the empty CDs category page
	Examples: Empty CDs Categories
		|cdsCategory         |
		|Emerging artists    |
		|International       |
		|Jazz                |
		|Miscellaneous|
		|Opera & vocal|
		|Rap & hip-hop|
		|R&B|
		|Soundtracks|
		|Vocalists & Broadway|
		|World|

Scenario Outline: Observe Books details page
	When I am on a product details page with "<url>" url
	Then I can see the product details page for "<product>" book/cd
	And I can see the book details
	Examples: Books Details pages
	|product|url|
	|Garden Zen|/index.php?page=books&category=books-home-garden&product=7|

Scenario Outline: Observe CDs details page
	When I am on a product details page with "<url>" url
	Then I can see the product details page for "<product>" book/cd
	And I can see the CD details
	Examples: CDs Details pages
	|product|url|
	|Green Green Green|/index.php?page=cds&category=cds-alternative&product=1|

Scenario: Observe Shopping basket page when there are no added products
When I'm on empty shopping basket page
Then I can see the shopping basket page
And I can see the following text displayed : "The Shopping Basket is empty!"

Scenario: Observe Shopping basket page when there are added products
When 1 products are added to the basket
And I open the shopping basket
Then I should see the shopping basket page
And I should see all 1 added products
And I should see all shopping basket information and buttons

