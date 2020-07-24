package com.ecommerce.flipkart.libraries;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary 
{
	public static String getValue(String filePath, String sheetName,int rowNumber, int cellNumber)
	{
		String data=null;
		try 
		{
			FileInputStream fin = new FileInputStream(filePath);
			Workbook wb=WorkbookFactory.create(fin);
			data = wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).toString();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return data;
	}
	
	public static Object[][] getData(String filePath, String sheetName)
	{
		Object[][] arr = null;
		try 
		{
			FileInputStream fin = new FileInputStream(filePath);
			Workbook wb=WorkbookFactory.create(fin);
			Sheet sheet = wb.getSheet(sheetName);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int column=sheet.getRow(0).getPhysicalNumberOfCells();
			arr = new Object[rowCount-1][column];
			for(int i=1,k=0;i<=rowCount-1;i++,k++)
			{
				int cellcount=sheet.getRow(i).getPhysicalNumberOfCells();
				for(int j=0;j<=cellcount-1;j++)
				{
					arr[k][j]=sheet.getRow(i).getCell(j).toString();
				}
			}
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return arr;
	}
}
