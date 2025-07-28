/*
 * package TestComponents;
 * 
 * import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.chrome.ChromeDriver; import
 * org.testng.annotations.AfterMethod;
 * 
 * import PageObject.HomePage; import io.cucumber.java.After; import
 * io.cucumber.java.Before;
 * 
 * public class Hooks { public WebDriver driver; public HomePage homePage;
 * 
 * @Before public WebDriver initializeDriver() {
 * System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+
 * "//src//main//java//Resources//BrowserDrivers//chromedriver-win64//chromedriver.exe"
 * ); WebDriver driver = new ChromeDriver();
 * driver.manage().window().maximize(); return driver; }
 * 
 * 
 * 
 * @After public void tearDown() { driver.close(); if (driver != null) {
 * driver.quit(); // closes browser after all tests are done } } }
 */