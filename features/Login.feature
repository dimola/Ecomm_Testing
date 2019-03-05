Feature: Login
  Imperative VS Declarative

  Background:
    Given Login page is loaded


  Scenario Outline: Login with invalid credentials
    When I login with credentials "<username>" and "<password>"
    Then I am not logged in the system

    Examples:
      | username | password |
      | student1 | stpass2  |
      | student1 |          |
      |          | stpass2  |
      |          |          |

  Scenario: Try to login three times with invalid credentials
    When I login with credentials "student1" and "pass1"
    And I login with credentials "student1" and "asd"
    And I login with credentials "student1" and "stpass"
    Then An error message is displayed

  Scenario: Verify that user is able to logout
    Given I am logged in with credentials "student1" and "stpass1"
    When I logout
    Then I am successfully logged out
