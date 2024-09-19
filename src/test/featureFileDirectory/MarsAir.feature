@MarsAir
Feature: To test the MarsAir page and other functionalities


  @MarsAir_TC1
  Scenario Outline: Basic Search flow-To test whether the dropdowns have valid values.
    Given User opens the "<browser>" and loads the "<URL>"
    When the page loads, check for all basic components loaded on the home page
    Then the user clicks the departure dropdown and validates its values "<departure dropdown>"


    Examples:
      | browser | URL        | departure dropdown | return dropdown |
      | browser | MarsAppURL | departure_dropdown | return_dropdown |


#  Select...,July,December,July (next year),December (next year),July (two years from now),December (two years from now)

#  @MarsAir_TC2
#  Scenario Outline: Promotional Codes-To test whether the dropdowns have valid values.
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the page loads, a user checks for all components on the home page
#    Then the user clicks the departure dropdown and validates its values "<departure dropdown>"
#    And the user clicks the return dropdown and validates its values "<return dropdown>"
#
#    Examples:
#      | browser | URL        | departure dropdown                                                                                                    | return dropdown                                                                                                       |
#      | chrome  | MarsAirURL | Select...,July,December,July (next year),December (next year),July (two years from now),December (two years from now) | Select...,July,December,July (next year),December (next year),July (two years from now),December (two years from now) |
#
#
#  @MarsAir_TC2
#  Scenario: Search results - Seats availability message
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user selects the departure as "July (next year)" and return as "December (next year)"
#    Then the system checks for seat availability
#    And the system displays "Seats available! Call 0800 MARSAIR to book!" if seats are available
#    And the system displays "Sorry, there are no more seats available" if no seats are available
#
#
#  @MarsAir_TC3
#  Scenario Outline: Promotional Codes - Validating code
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user searches for flights using departure "<departure>" and return "<return>"
#    Then the user applies the promotional code "<promo code>"
#    And the system validates the promo code "<promo code>" and displays appropriate message "<message>"
#
#    Examples:
#      | browser | URL        | departure          | return           | promo code      | message                                                         |
#      | chrome  | MarsAirURL | July (next year)   | December (next year) | AF3-FJK-418     | Promotional code AF3-FJK-418 used: 30% discount!                |
#      | chrome  | MarsAirURL | July (two years from now) | December (two years from now) | JJ5-OPQ-320 | Promotional code JJ5-OPQ-320 used: 50% discount!                |
#      | chrome  | MarsAirURL | July (next year)   | December (next year) | XX9-XXX-999     | Sorry, code XX9-XXX-999 is not valid                            |
#
#
#  @MarsAir_TC4
#  Scenario: Invalid return dates - Prevent search when return date is less than 1 year from departure
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user selects the departure as "July (next year)" and return as "December (same year)"
#    Then the system displays "Unfortunately, this schedule is not possible. Please try again."
#
#
#  @MarsAir_TC5
#  Scenario: Navigation - Return to flight search from any page
#    Given User is on any page of the website
#    When the user clicks on the "Book a ticket to the red planet now!" link
#    Then the user should be redirected to the flight search page
#
#  @MarsAir_TC6
#  Scenario: Navigation - Clicking on MarsAir logo takes the user to the homepage
#    Given User is on any page of the website
#    When the user clicks the "MarsAir" logo on the top left
#    Then the user should be redirected to the homepage
#
#
#  @MarsAir_TC7
#  Scenario Outline: Promotional code format validation
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user enters a promotional code "<promo code>"
#    Then the system should validate the code and display an appropriate message "<message>"
#
#    Examples:
#      | browser | URL        | promo code      | message                                                              |
#      | chrome  | MarsAirURL | S39-KeJ-009     | Sorry, code S39-KeJ-009 is not valid                                 |
#      | chrome  | MarsAirURL | AF3-FJK-418     | Promotional code AF3-FJK-418 used: 30% discount!                     |
#      | chrome  | MarsAirURL | S+9-K+J-009     | Sorry, code S+9-K+J-009 is not valid (special characters not allowed) |
#
#
#  @MarsAir_TC8
#  Scenario: UI Misalignment after inactivity
#    Given User opens the "<browser>" and loads the "<URL>"
#    And the user leaves the screen idle for 10 minutes
#    When the user attempts to perform any action on the page
#    Then the page layout should not get misaligned
#
#
#  @MarsAir_TC9
#  Scenario Outline: Invalid Promo Code Handling
#    Given User applies an invalid promo code "<promo code>"
#    Then the system should display "Sorry, code <promo code> is not valid"
#
#    Examples:
#      | promo code  |
#      | XX9-KKK-000 |
#      | AF0-XYZ-415 |
#
#  @MarsAir_TC10
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user clicks the departure dropdown
#    Then the user should not be able to select any past months
#
#  @MarsAir_TC11
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user clicks the departure dropdown
#    Then the option 'July' should not be available if it has passed
#
#  @MarsAir_TC13
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user does not select both dropdown values
#    Then the user should not be allowed to click the 'Search' button
#
#    @MarsAir_TC14
#    Given User opens the "<browser>" and loads the "<URL>" on a desktop browser with resolution "<resolution>"
#    Then the user should see the credit text without zooming out
#
#    @MarsAir_TC15
#    Given User opens the "<browser>" and loads the "<URL>" on a mobile device "<device>"
#    Then the background image should not be distorted or duplicated
#
#    @MarsAir_TC16
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user enters a promotional code with special characters "<promocode>"
#    Then the system should display an invalid code message
#
#
#    @MarsAir_TC17
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user enters a long promotional code "<promocode>"
#    Then the code and error message should be properly wrapped within the form
#
#    @MarsAir_TC18
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user enters a 0% discount code "<promocode>"
#    Then the system should display an invalid discount message
#
#    @MarsAir_TC19
#    Given User opens the "<browser>" and loads the "<URL>"
#    When the user enters a valid promotional code with leading or trailing spaces "<promocode>"
#    Then the system should trim spaces and accept the code as valid
