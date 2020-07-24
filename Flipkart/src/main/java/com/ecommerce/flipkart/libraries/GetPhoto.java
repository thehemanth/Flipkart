package com.ecommerce.flipkart.libraries;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetPhoto
{
	public static String takePhoto(WebDriver driver, String testMethodName)
	{
		String date=LocalDateTime.now().toString().replace(':', '-');
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		String photoPath = "./errorshots/"+date+testMethodName+".png";
		File destFile=new File(photoPath);
		try 
		{
			FileUtils.copyFile(srcFile, destFile);
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return photoPath;
	}
}
