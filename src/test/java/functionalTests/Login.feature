Feature: Login

  Scenario: Login with valid credentials
    Given Login page is loaded
    When I type username "student1"
    And I type password "stpass1"
    And I click on Login button
    Then I should be successfully logged in

  Scenario: Login with invalid credentials
    Given Login page is loaded
    When I type username "<username>"
    And I type password "<password>"
    And I click on Login button
    Then I am not logged in the system

   
