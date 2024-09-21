@CoreProductRegression
Feature: Testcases for checks on the Core Product functionality

  @CP_1
  Scenario Outline: TC1-Extract Jacket details from the Shop Menu
    Given User opens the "<browser>" and loads the "<URL>"
#    When I am on the CP Home Page and do some quick element loaded validations
#    And I navigate to the Shop Menu and select "<ShopMenuCategory>" category
#    And I find all "<ProductType>" from all paginated pages
#    Then I store each Price, Title, and Top Seller message of the "<ShopMenuCategory>" and "<ProductType>" in a text file
#    And I attach the generated text file to the test report

    Examples:
      | browser | URL    | ShopMenuCategory | ProductType |
      | browser | CP_URL | Men's            | Jackets     |

  @CP_2
  Scenario Outline: TC2-Extract Jacket details from the Shop Menu
    Given User opens the "<browser>" and loads the "<URL>"
#    When I am on the CP Home Page and do some quick element loaded validations
#    And I navigate to the Shop Menu and select "<ShopMenuCategory>" category
#    And I find all "<ProductType>" from all paginated pages
#    Then I store each Price, Title, and Top Seller message of the "<ShopMenuCategory>" and "<ProductType>" in a text file
#    And I attach the generated text file to the test report

    Examples:
      | browser | URL    | ShopMenuCategory | ProductType |
      | browser | CP_URL | Men's            | Jackets     |

