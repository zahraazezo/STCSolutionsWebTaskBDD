Feature: Customer List

  Scenario: Sort customers by postcode in ascending and descending order
    Given the manager is logged into the website
    When the manager clicks on VIEW_CUSTOMER_LIST button in the manager actions section
    And the Bank Manager clicks on the Postcode column to sort in ASCENDING order
    Then the customers should be sorted by postcode in ASCENDING order
    When the Bank Manager clicks on the Postcode column to sort in DESCENDING order
    Then the customers should be sorted by postcode in DESCENDING order
