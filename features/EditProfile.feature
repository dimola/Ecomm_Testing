Feature: Edit Profile

#TC-73
Scenario Outline: Edit profile with valid data
    Given I am logged in
    And I am on the Edit Profile page
    When I fill in all form fields with random valid data
    And I submit the edit profile form
    Then I am redirected to page with confirmation message "<successMsg>"
    Examples:
    |successMsg                            |
    |Your profile was updated successfully!|

#TC-74
Scenario Outline: Can not edit profile with missing field
    Given I am logged in
    And I am on the Edit Profile page
    When I fill in all form fields without "<fieldName>" field
    And I submit the edit profile form
    Then An error message with following text is displayed: "<errorMsg>"
    Examples:
    |errorMsg                        |fieldName|
    |Please fill all required fields!|email    |
    |Please fill all required fields!|name     |
    |Please fill all required fields!|phone    |
    |Please fill all required fields!|address  |