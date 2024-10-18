@login
Feature: Login scenarios

  Scenario Outline: Login with invalid username "<username>"
    Given I enter username as "<username>"
    And I enter password as "<password>"
    When I login
    Then login should fail with an error "<error>"

    Examples:
      | username        | password        | error                                                        |
      | invalidUsername | secret_sauce    | Username and password do not match any user in this service. |
      | standard_user   | invalidPassword | Username and password do not match any user in this service. |

  Scenario: Login with valid username & password
    Given I enter username as "standard_user"
    And I enter password as "secret_sauce"
    When I login
    Then I should see Products page with title "PRODUCTS"
    And I logout
