@languageSwitching
Feature: Language Switching on Baykar Tech Website

  Scenario: Visitor can switch between available languages correctly
    Given the user is on the Baykar Tech website
    When the user switches the language to "English"
    Then the website should display the correct content in "English"
    When the user switches the language to "Turkish"
    Then the website should display the correct content in "Turkish"

