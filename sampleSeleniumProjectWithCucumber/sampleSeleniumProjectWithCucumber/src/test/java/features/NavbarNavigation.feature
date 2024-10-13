@navbarNavigation
Feature: Baykar Kariyer Navbar Navigation

  Scenario: Visitor can click on all navbar elements and the pages load successfully
    Given the user is on the Baykar Tech home page
    When the user retrieves all navbar elements
    Then the user clicks on each navbar element
    And the corresponding page should load without errors
    And the user should return to the home page after each click
    And the browser should close
