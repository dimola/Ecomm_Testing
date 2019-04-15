Feature: Checkout Page

#TC-59
Scenario: Confirm purchase
    Given I am logged in
    And One product is already added in the basket
    And I am on the Checkout page
    When I confirm purchase
    Then Confirmation for purchase message is displayed

#TC-60
Scenario: Cancel purchase
    Given I am logged in
    And One product is already added in the basket
    And I am on the Checkout page
    When I cancel purchase
    Then Message for canceld purchase is displayed

#TC-62
Scenario: Automatic add of shipping charge to the total of the order
    Given I am logged in
    And One product is already added in the basket
    And I am on the Checkout page
    Then The shipping cost is displayed and it is added to the total sum

#TC-63
Scenario: Automatic add tax to the total of the order
    Given I am logged in
    And One product is already added in the basket
    And I am on the Checkout page
    Then The tax field is displayed and it is added to the total sum