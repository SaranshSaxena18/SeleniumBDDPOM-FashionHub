package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReader {
	private Map<String, Object[]> allData = new HashMap<String,Object[]>();
	public TestDataReader() throws IOException {
        this.allData = Collections.unmodifiableMap(allDataGetter());
    }
	
	public Map<String, Object[]> allDataGetter()
	{
		Map<String, Object[]> tempMap = new HashMap<String, Object[]>();//to make it thread safe
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream("C:\\Users\\S.NandaKumar\\Selenium UI Automation\\FashionHub\\src\\main\\java\\Resources\\TestData\\TestData.xlsx");
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Test Data file not found"+e);
		}
		catch (IOException e) {
			System.out.println("Test Data file not found"+e);
		}
		int sheets = workbook.getNumberOfSheets();//get the total number of sheets
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("TestData"))
			{
				XSSFSheet sheet = workbook.getSheetAt(i);//get the sheet at index
				Iterator<Row> rows = sheet.iterator();//sheet is collection of rows
				Row firstRow = rows.next();//row is collection of cells
				//Iterator<Cell> cell = firstRow.cellIterator();//row is collection of cells
				int columns = sheet.getRow(0).getLastCellNum();//get the total number of columns
				while(rows.hasNext())
				{
					Row currentRow = rows.next();
					Iterator<Cell> currentCell = currentRow.cellIterator();
					int j=0;
					String scenarioName = currentRow.getCell(0).getStringCellValue().trim();//get the scenario name
					 Object[] obj = new Object[columns - 1]; // exclude first column
					    
					    for (int colIdx = 1; colIdx < columns; colIdx++) // start from col 1, skip col 0
					    {
					        Cell cellValue = currentRow.getCell(colIdx);
					        if (cellValue != null) {
					            switch(cellValue.getCellType())
					            {
					                case STRING:
					                    obj[colIdx - 1] = cellValue.getStringCellValue();
					                    break;
					                case NUMERIC:
					                    obj[colIdx - 1] = cellValue.getNumericCellValue();
					                    break;
					                case BOOLEAN:
					                    obj[colIdx - 1] = cellValue.getBooleanCellValue();
					                    break;
					                default:
					                    obj[colIdx - 1] = null;
					            }
					        } else {
					            obj[colIdx - 1] = null;
					        }
					    }
					    tempMap.put(scenarioName, obj);
				}
//				System.out.println(map.keySet());
//				for(String s:map.keySet())
//				{
//					Object[] obj = map.get(s);
//					for(Object o:obj)
//					{
//						System.out.print(o+" ");
//					}
//					System.out.println();
//				}
				break;
			}
		}
		return tempMap;
	}
	
//	public Object[] getData(String sceanrioName)
//	{
//		Map<String,Object[]> allData = allDataGetter();
//		Object[] data = allData.get(sceanrioName);
//		return data;
//	}
	public Object[] getData(String scenarioName) {
	    return allData.get(scenarioName);
	}
	
	public static void main(String[] args) {
		TestDataReader tdr = null;
		try {
			tdr = new TestDataReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[] data = tdr.getData("Search Product");
		for(Object o:data)
		{
			System.out.print(o+" ");
		}
		//tdr.allDataGetter();
	}

}
