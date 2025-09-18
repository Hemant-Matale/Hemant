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

Feature: Bus search functionality on Goibibo

  Scenario Outline: Verify that valid bus routes return available buses
    Given the user is on the Goibibo Bus booking page
    When the user enters "<source>" as source and "<destination>" as destination
    And selects "<date>" as travel date
    And clicks on the search button
    Then the search results page should display available buses with the correct count
    Examples:
    | source    | destination | date              |
    | Mumbai    | Pune        | September 30 2025 |
    #| Delhi     | Jaipur      | December 20 2025  |
    #| Bangalore | Hyderabad   | November 12 2025  |
    
    Scenario Outline: Verify that search with same source and destination Error Validation Message displays
    Given the user is on the Goibibo Bus booking page
    When the user enters "<city>" source and destination
    And selects "<date>" as travel date
    And clicks on the search button
    Then Validation Message should be displayed saying "Source and Destination cannot be the same"
    Examples:
    | city        | date               |
    | Mumbai      | September 30 2025 |
    #| Delhi       | December 20 2025  |
    #| Bangalore   | November 12 2025  |
    
    Scenario Outline: Verify that search with only source entered and destination kept Blank Error Validation Message displays
    Given the user is on the Goibibo Bus booking page
    When the user enters "<source>" as source and destination kept blank
    And selects "<date>" as travel date
    And clicks on the search button
    Then Validation Message should be displayed saying "Please enter the TO location"
    Examples:
    | source    |  date              |
    | Mumbai    |  September 30 2025 |
    #| Delhi     |  December 20 2025  |
    #| Bangalore |  November 12 2025  |
    
    Scenario Outline: Verify that search with source kept blank and only destination entered Error Validation Message displays
    Given the user is on the Goibibo Bus booking page
    When the user keeps source blank and only enters "<destination>" as destination
    And selects "<date>" as travel date
    And clicks on the search button
    Then Validation Message should be displayed saying "Please enter the FROM location"
    Examples:
       | destination | date              |
       | Mumbai      | September 30 2025 |
       #| Delhi       | December 20 2025  |
       #| Pune        | November 12 2025  |


