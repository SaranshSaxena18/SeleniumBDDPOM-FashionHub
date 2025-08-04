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
@tag
Feature: To test the shopping feature for the FashionHub Website
  
  #@tag1
	Background:
	Given User visit the FashionHub Website

  
  Scenario Outline: Search Product
   Given user scroll through the featured products
   When user click on the view all products
   And search a <product>
   Then <product> should be searched
   Examples:
   | product							 |
   | Classic White T-Shirt |

	@CurrentDev
  Scenario Outline: Filter Products
   Given user scroll through the featured products
   When user click on the view all products
   Then products should be searched as per the filter "<Company1>" "<Company2>" "<Company3>"
	 Examples:
	|Company1|Company2|Company3|
	|Nike		 |Gap		  |Zara		 |
