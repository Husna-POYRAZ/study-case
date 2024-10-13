@viewOpenPosition
Feature: View Open Positions on Baykar Tech Homepage

  Scenario: User navigates to the open positions page and views the list of positions
    Given the user opens the Baykar Tech homepage
    When the user clicks on the open positions link in the navbar
    And the user clicks the button to view all open positions
    And the user scrolls down the page
    Then the user should see the list of open positions

