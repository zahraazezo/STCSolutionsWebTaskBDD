
Feature: Open Account for Existing Customer

  Scenario: Bank manager opens a new account for an existing customer
    Given the manager is logged into the website
    When the manager clicks on OPEN_ACCOUNT button in the manager actions section
    And the bank manager selects the customer Harry Potter
    And the bank manager selects the currency Dollar
    And the bank manager clicks on the Process button
    Then the account should be created successfully with an account number
    And the bank manager should go back to home
    Given the manager is logged into the website
    When the manager clicks on VIEW_CUSTOMER_LIST button in the manager actions section
    And the Bank Manager types the first name Harry in the search field
    Then the account number of the last added account matches the generated account number
