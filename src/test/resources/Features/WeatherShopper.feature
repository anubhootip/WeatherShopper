#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Weather Shopper

  Scenario: To test current temperature page successfully
    Given Current temperature page loads successfully
    When Curren temperature value is displayed to the user
    Then user validates the Moisturizers and Sunscreen description should be displayed along with its title
    And user validates that the buy moisturizer and sunscreen button should be displayed on the screen

  Scenario: To Test the purchase button of Moisteruizer and Sunscreen on the basis of temperature
    Given Current temperature page loads successfully
    When Curren temperature value is displayed to the user
    Then user should click on the respective button to purchase moisturizers and sunscreen on the basis of current temperature value
    
    
