Feature: Search 

Scenario Outline: Search in Books by Author 
	Given Books page is loaded 
	When I search for a certain author "<author>" 
	Then All displayed books are written by this author "<author>" 
	Examples: 
		|author       |
		|lilly        |  
		|Lilly Pen    |
		|Jenny Hoskins|
		
Scenario Outline: Search in Books by Title 
	Given Books page is loaded 
	When I search for a certain Book by its title "<title>" 
	Then The book is displayed "<title>" 
	Examples: 
		|title     |
		|Bird      |
		|zen       |
		|Green Food|
		
Scenario Outline: Search in Books by Publisher 
	Given Books page is loaded 
	When I search for a certain publisher "<publisher>" 
	Then All books from this publisher are displayed "<publisher>" 
	Examples: 
		|publisher         |
		|Zen Publishing Ltd|
		|Soundsys          |
		|Dollar Inc.       |
		
Scenario Outline: Search in Books by ISBN 
	Given Books page is loaded 
	When I search for a certain ISBN "<ISBN>" 
	Then The book with that number is displayed "<ISBN>" 
	Examples: 
		|ISBN    |
		|55134124|
		|5       |
		|34      |
		
Scenario Outline: Combined searching in Books 
	Given Books page is loaded 
	When I search for more than one of the search criteria at the same time "<author>" and "<title>" and "<publisher>" and "<ISBN>" 
	Then The book answering to the respective criteria is displayed "<author>" and "<title>" and "<publisher>" and "<ISBN>" 
	Examples: 
		|author        |title        |publisher        |ISBN    |
		|Diana Jones   |Autumn Colors|Nature Books Ltd.|55134124| 
		|Anne Wilkinson|             |                 |55432334|
		|Lilly         |             |World            |        |
		
Scenario Outline: 
	Check if error message is displayed when searching with invalid data 
	Given Books page is loaded 
	When I search with invalid criteria "<invalidAuthor>" and "<invalidTitle>" and "<invalidPublisher>" and "<invalidISBN>" 
	Then An error message is displayed, stating that there are no such books in the system 
	Examples: 
		|invalidAuthor|invalidTitle|invalidPublisher|invalidISBN|
		|hdkjahdabhfs |555555      |34jh24bb        |skadnj     |
		|hdgabas      |            |                |           |
		|             |ffffff      |                |           |
		|             |            |aa3333          |           |
		|             |            |                |sabg3      |
		
		
		
	