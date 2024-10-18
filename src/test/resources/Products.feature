@products
Feature: Product scenarios

  Background:
    Given I'm logged in

  Scenario: Validate product info on Products page
    Given the product is listed with title "Sauce Labs Backpack" and price "$29.99"
    And I logout

  Scenario: Validate product info inside Products page
    Given I click product title "Sauce Labs Backpack"
    Then I should be on product details page with title "Sauce Labs Backpack", price "$29.99" and description "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."
    And I logout
