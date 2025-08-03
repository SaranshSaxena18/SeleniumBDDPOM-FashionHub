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
import org.testng.annotations.DataProvider;

public class Utility {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	public Utility(WebDriver driver)
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
				}catch(Exception e)
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

	DataFormatter formatter = new DataFormatter();
	@DataProvider(name="TestData")
	public Object[][] getDataFromExcel()throws IOException
	{	
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//TestData//TestData.xlsx");//path of the test data file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);//creating the object of XSSFWorkbook to load the xlsx sheet so that it can be interacted with.
		int numberOfSheets = workbook.getNumberOfSheets();//to get the total number of sheets in the excel file
		for(int i=0;i<numberOfSheets;i++)//run the for loop till the total number of sheets
		{
			if(workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("testdata"))
			{
				XSSFSheet sheet = workbook.getSheetAt(i);//load the sheet
				int rowCount = sheet.getPhysicalNumberOfRows();//get total number of rows
				XSSFRow row = sheet.getRow(0);//get the first row
				int colCount = row.getLastCellNum();//number of total columns
				Object[][] data = new Object[rowCount-1][colCount];//rowCount-1 as first row is the heading
				for(int j=1;j<rowCount;j++)//run the loop till total number of rows,  j=1 as first row is heading
				{
					XSSFRow row1 = sheet.getRow(j);
					if(row1 == null) continue;
					for(int k=0;k<colCount;k++)
					{
						XSSFCell cell = row1.getCell(k);
						data[j-1][k] = formatter.formatCellValue(cell);
					}
				}
				return data;
			}
		}
		return null;
	}
}
