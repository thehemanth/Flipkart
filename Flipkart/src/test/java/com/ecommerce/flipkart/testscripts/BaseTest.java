package com.ecommerce.flipkart.testscripts;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ecommerce.flipkart.libraries.AutoConstants;
import com.ecommerce.flipkart.libraries.GetPhoto;
import com.ecommerce.flipkart.libraries.WebActionUtil;
import com.ecommerce.flipkart.pages.HomePage;
import com.ecommerce.flipkart.pages.LoginPage;

public class BaseTest implements AutoConstants
{
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	
	@Parameters({"browserName","appUrl","ito","eto"})
	@BeforeClass
	public void openApp(@Optional(DEFAULT_BROWSER)String browserName,
						@Optional(DEFAULT_APP_URL)String url,
						@Optional(ITO)String implicitTimeOut,
						@Optional(ETO)String explicitTimeOut)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty(CHROME_KEY, CHROME_DRIVER_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty(GECKO_KEY, GECKO_DRIVER_PATH);
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		}
		else
		{
			throw new IllegalArgumentException("Browser is not Supported");
		}
		
		driver.manage().window().maximize();
		long impTimeout = Long.parseLong(implicitTimeOut);
		driver.manage().timeouts().implicitlyWait(impTimeout, TimeUnit.SECONDS);
		driver.get(DEFAULT_APP_URL);
		long expTimeout = Long.parseLong(explicitTimeOut);
		webActionUtil = new WebActionUtil(driver, expTimeout);
	}
	
	@BeforeMethod
	public void loginToApp(@Optional(DEFAULT_USERNAME)String un,
						   @Optional(DEFAULT_PASSWORD)String pwd)
	{
		LoginPage lp = new LoginPage(driver, webActionUtil);
		lp.login(un, pwd);
	}
	
	@AfterMethod
	public void logoutFromApp(ITestResult result)
	{
		String testMethodName=result.getName();
		int status=result.getStatus();
		if(status!=1)
		{
			GetPhoto.takePhoto(driver, testMethodName);
		}
		HomePage hp = new HomePage(driver, webActionUtil);
		try
		{
			hp.signOut();
			driver.navigate().to(DEFAULT_APP_URL);
		}
		catch (Exception e) 
		{
			
		}
	}
	
	@AfterClass
	public void closeApp()
	{
		driver.quit();
	}
}
