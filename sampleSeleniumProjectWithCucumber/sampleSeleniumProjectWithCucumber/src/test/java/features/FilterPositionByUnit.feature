@filterPositionByUnit
Feature: Filter Positions by Unit on Baykar Tech Career Page

  Background:
    Given the user is on the Baykar Tech career page

  Scenario: User filters positions by unit
    When the user navigates to the open positions page
    And the user filters positions by unit "Web"
    Then the user should see positions matching the unit criteria

  Scenario: User searches for a specific position
    When the user navigates to the open positions page
    And the user searches for the position "smart web"
    Then the user should see positions matching the search criteria

  Scenario: User searches for a non-existing position
    When the user navigates to the open positions page
    And the user searches for the position "cyber"
    Then the user should see a message indicating no positions found
