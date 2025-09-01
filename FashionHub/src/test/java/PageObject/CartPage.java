package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.Waits;

public class CartPage extends Waits{
	
	private WebDriver driver;
	private CheckoutPage checkoutPage;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//button[text()='Proceed to Checkout']")
	WebElement ProceedToCheckoutButton_xpath;

	@FindBy(tagName="h2")
	WebElement EmptyCartPageHeading_xpath;
	
	public CheckoutPage proceedToCheckout() {
		waitToBeClickableThenClick(ProceedToCheckoutButton_xpath);
		checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	public String getEmptyCartPageHeading() {
		waitToBeVisible(EmptyCartPageHeading_xpath);
		return EmptyCartPageHeading_xpath.getText();
	}

}
