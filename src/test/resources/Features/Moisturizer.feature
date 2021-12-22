#Author: your.email@your.domain.com
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
Feature: Moisturizer page

  Scenario: To Test the payment of the Moisturizer purchased
    Given Current temperature page loads successfully
    When Curren temperature value is displayed to the user
    Then user should be able to click on the purchase button for Moisturizer
    Then user should be able to click on the Add button for lowest price of Aloe
    Then user should be able to click on the Add button for lowest price of Almond
    Then user should be able to click on the Cart button to navigate to check out page
    And user should validate the sum of the items with the total sum
    When user click on the Pay with card button
    Then user validates that the payment pop up is displayed
    Then user should input the values for email,card number,monthyear and cvv
    Then user should click on the payment button and payment success message should be displayed
