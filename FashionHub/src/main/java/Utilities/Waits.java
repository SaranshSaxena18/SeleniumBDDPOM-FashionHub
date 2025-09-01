package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;

public class Waits {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	public Waits(WebDriver driver)
	{
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
	}
	
	public void scrollTo(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public void waitToBeVisible(WebElement element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException e)
		{
			System.out.println("Element is not visible after waiting for 30 seconds - "+e.getMessage());
		}
	}
	
	public void waitToBeClickableThenClick(WebElement element)
	{
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(TimeoutException e)
		{
			System.out.println("Element is not clickable after waiting for 50 seconds - "+e.getMessage());
		}
		
		try 
		{
			int attempts = 0;
			while(attempts<2)
			{
				try {
					js.executeScript("arguments[0].click()",element);
					break;
				}
				catch(Exception e)
				{
					attempts++;
				}
			}
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("Unable to the click the element after waiting for 50 seconds - "+e.getMessage());
		}
		
	}
}
