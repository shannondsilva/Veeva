@CoreProductRegression
Feature: Testcases for checks on the Core Product functionality

  @CP_1
  Scenario Outline: TC1-Extract Jacket details from the Shop Menu
    Given User opens the "<browser>" and loads the "<URL>"
    When I am on the CP Home Page and do some quick element loaded validations
    And I navigate to the Shop Menu and select "<MenuCategory>" category
    And I find all "<ProductType>" from all paginated pages
    Then I store each Price, Title, and Top Seller message of the "<MenuCategory>" and "<ProductType>" in a text file
    And I attach the generated text file to the test report

    Examples:
      | browser | URL    | MenuCategory | ProductType |
      | chrome  | CP_URL | Men's        | Jackets     |
      | firefox | CP_URL | Women's      | Footwear    |
      | edge    | CP_URL | Kids         | Hats        |


  @CP_2
  Scenario Outline: TC2-To count the overall video feeds and the feeds less than 3d
    Given User opens the "<browser>" and loads the "<URL>"
    When I am on the CP Home Page and do some quick element loaded validations
    And I navigate to the three dotted Menu and select "<MenuCategory>" category
    Then I navigate to the News page and count all video feeds "<operator>" "<days>"
    And I attach the generated text file to the test report

    Examples:
      | browser | URL    | MenuCategory    | operator | days |
      | chrome  | CP_URL | News & Features | >=       | 3    |
      | firefox | CP_URL | News & Features | >        | 4    |
      | chrome  | CP_URL | News & Features | =        | 1    |
      | edge    | CP_URL | News & Features | >        | 30   |
      | firefox | CP_URL | News & Features | !=       | 4    |

