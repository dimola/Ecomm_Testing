Feature: Shopping basket

#TC-42
Scenario Outline: Books can be added to basket
    Given Book category "<book_category>" page is loaded
    And Book category page is not empty
    When I click on "Add to basket" of book number <number_book>
    Then Book number <number_book> is added in the basket
    And To basket product count is added 1
    Examples:
    |book_category|number_book|
    |Computers    |1          |

#TC-43
Scenario Outline: Cds can be added to basket
    Given Cds category "<cd_category>" page is loaded
    And CD category page is not empty
    When I click on "Add to basket" of cds number <number_cd>
    Then CD number <number_cd> is added in the basket
    And To basket product count is added 1
    Examples:
    |cd_category|number_cd|
    |Classical  |1        |

#TC-54
Scenario Outline: Increasing product count
    Given One product is already added in the basket
    And I am on the shopping basket page
    When I tap <tapTimes> times on add one button in counter
    Then The number of copies for product number <productNumberRow> is increased by <counter>
    And The basket icon in header is displaying the same number of products as in the shopping basket
    Examples:
    |productNumberRow|counter|tapTimes|
    |1               |1      |1       |

#TC-55
Scenario Outline: Decrease products count from the basket
    Given One product is already added in the basket
    And I am on the shopping basket page
    When I tap <tapTimes> times on remove one button in counter
    Then Product is removed and following text is displayed "<errorMsg>"
    And <number> should be displayed in shopping basket icon
    Examples: Main Categories
    |errorMsg                     |tapTimes|number|
    |The Shopping Basket is empty!|1       |0     |

#TC-56
Scenario Outline: Removing products from the basket
    Given One product is already added in the basket
    And I am on the shopping basket page
    When I click on remove button for product number <productNumberRow>
    Then <productNumberRow> product is removed
    And The basket icon in header is displaying the same number of products as in the shopping basket
    Examples: Main Categories
    |productNumberRow|
    |1               |

#TC-57
Scenario: User is able to proceed to checkout page
    Given I am logged in
    And One product is already added in the basket
    And I am on the shopping basket page
    When I click on checkout button
    Then I am redirected on Checkout page

#TC-58
Scenario: Verify that user is able to proceed to checkout page
    Given I am not logged in
    And One product is already added in the basket
    And I am on the shopping basket page
    When I click on checkout button
    Then I am redirectied to checkout login menu
    When I log in
    Then I am redirected on Checkout page