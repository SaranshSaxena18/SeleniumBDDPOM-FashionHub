#Author: Saransh Saxena
#Author email: saransh.saxena18@gmail.com

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
	
  Scenario Outline: Filter Products
   Given user scroll through the featured products
   When user click on the view all products
   Then products should be searched as per the filter "<Company1>" "<Company2>" "<Company3>"
	 Examples:
	|Company1|Company2|Company3|
	|Nike		 |Gap		  |Zara		 |
	
	@CurrentDev
	Scenario Outline: User shops
		Given user go to shop now from home page
		When Add products to the cart
		And  proceed to checkout
		And add checkout details
		And complete the payment
		Then cart should get empty
		
