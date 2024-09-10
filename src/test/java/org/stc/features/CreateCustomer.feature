Feature: Create Customer

  Scenario Outline: Verify adding a customer as a bank manager
    Given the manager is logged into the website
    When the manager clicks on ADD_CUSTOMER button in the manager actions section
    And  the manager fills in the customer details first name <FirstName>, last name <LastName>, and post code <PostCode>
    And  clicks on the Add Customer from button
    Then the success message Customer added successfully should be displayed

    Examples:
      | FirstName | LastName | PostCode |
      | zahra1    | gamal1   | 123213   |
      | zahra2    | gamal2   | 324324   |

