package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.Waits;

public class CheckoutPage extends Waits{
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@name='firstName']")
	WebElement FirstNameTextField_xpath;
	
	@FindBy(xpath="//input[@name='lastName']")
	WebElement LastNameTextField_xpath;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement EmailTextField_xpath;
	
	@FindBy(xpath="//button[text()='Next']")
	WebElement NextButton_xpath;
	
	@FindBy(xpath="//input[@name='address']")
	WebElement AddressTextField_xpath;
	
	@FindBy(xpath="//input[@name='city']")
	WebElement CityTextField_xpath;
	
	@FindBy(xpath="//input[@name='state']")
	WebElement StateTextField_xpath;
	
	@FindBy(xpath="//input[@name='zipCode']")
	WebElement ZipCodeTextField_xpath;
	
	@FindBy(xpath="//input[@value='credit-card']")
	WebElement CreditCardRadioButton_xpath;
	
	@FindBy(xpath="//input[@placeholder='1234 5678 9012 3456']")
	WebElement CardNumberTextField_xpath;
	
	@FindBy(xpath="//input[@placeholder='MM/YY']")
	WebElement ExpiryDateTextField_xpath;
	
	@FindBy(xpath="//input[@placeholder='123']")
	WebElement CVVTextField_xpath;
	
	@FindBy(xpath="//button[contains(text(),'Place Order')]")
	WebElement PlaceOrderButton_xpath;
	
	@FindBy(xpath="//button[text()='Confirm Order']")
	WebElement ConfirmOrderButton_xpath;
	
	public CheckoutPage enterCheckoutDetails(String firstName, String lastName, String email)
	{
		waitToBeVisible(FirstNameTextField_xpath);
		FirstNameTextField_xpath.sendKeys(firstName);
		LastNameTextField_xpath.sendKeys(lastName);
		EmailTextField_xpath.sendKeys(email);
		waitToBeClickableThenClick(NextButton_xpath);
		return this;
	}
	
	public CheckoutPage enterShippingDetails(String address, String city, String state, String zipCode)
	{
		waitToBeVisible(AddressTextField_xpath);
		AddressTextField_xpath.sendKeys(address);
		CityTextField_xpath.sendKeys(city);
		StateTextField_xpath.sendKeys(state);
		ZipCodeTextField_xpath.sendKeys(zipCode);	
		waitToBeClickableThenClick(NextButton_xpath);
		return this;
	}
	
	public CheckoutPage enterPaymentDetailsAndPay(String cardNumber, String expiryDate, String cvv)
	{
		waitToBeClickableThenClick(CreditCardRadioButton_xpath);
		CardNumberTextField_xpath.sendKeys(cardNumber);
		ExpiryDateTextField_xpath.sendKeys(expiryDate);
		CVVTextField_xpath.sendKeys(cvv);
		waitToBeClickableThenClick(PlaceOrderButton_xpath);
		return this;
	}
	
	public void confirmOrder()
	{
		waitToBeClickableThenClick(ConfirmOrderButton_xpath);
	}
}