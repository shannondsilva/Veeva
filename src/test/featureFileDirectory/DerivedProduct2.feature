@DerivedProductRegression
Feature: Testcases for checks on the Derived Product functionality

  @DP2_1
  Scenario Outline: TC1-Validate the footer hyperlink links and highlight if any duplicates
    Given User opens the "<browser>" and loads the "<URL>"
    When I am on the DP Home Page and do some quick element loaded validations
    And I scroll to the bottom of the page to the footer section
    And I count the total hyperlinks in the DP footer section save these links into a csv file highlighting duplicates
    And I attach the generated csv file to the test report

    Examples:
      | browser | URL     |
      | chrome  | DP2_URL |




