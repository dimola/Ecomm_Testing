# new feature
# Tags: optional
    
Feature: Browse books by categories
    
Scenario Outline: Verify links are redirecting to correct places
    Given Books page is loaded
    When I click a certain <category>
    Then The books for the respective category are displayed

    Examples: