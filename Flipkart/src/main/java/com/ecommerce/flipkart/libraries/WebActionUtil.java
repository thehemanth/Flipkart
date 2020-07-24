package com.ecommerce.flipkart.libraries;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActionUtil
{
	WebDriver driver;
	long ETO;
	JavascriptExecutor js;
	Actions actions;
	WebDriverWait wait;
	public WebActionUtil(WebDriver driver, long ETO) 
	{
		this.driver=driver;
		this.ETO=ETO;
		js = (JavascriptExecutor)driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, ETO);
	}
	
	public void javaScriptClick(WebElement target)
	{ 
		js.executeScript("arguments[0].click();", target);
	}
	
	public void scrollDown(int pixels)
	{
		js.executeScript("window.scrollBy(0,"+pixels+");");
	}
	
	public void scrollUp(int pixels)
	{
		js.executeScript("window.scrollBy(0,-"+pixels+");");
	}
	
	public void scrollToElement(WebElement target)
	{
		js.executeScript("arguments[0].scrollIntoView()", target);
	}
	
	public void enterKeysUsingJSE(WebElement target, String keysToSend)
	{
		js.executeScript("arguments[0].value="+keysToSend+";", target);
	}
	
	public void enterKeys(WebElement target, String keysToSend)
	{
		target.clear();
		target.sendKeys(keysToSend);
	}
	
	public void dragAndDrop(WebElement source, WebElement target)
	{
		actions.dragAndDrop(source, target).perform();
	}
	
	public void elementRightClick(WebElement target)
	{
		actions.contextClick(target).perform();
	}
	
	public void moveToElement(WebElement target)
	{
		actions.moveToElement(target).perform();
	}
	
	public void moveToElementClick(WebElement target, int xAxisvalue, int yAxisValue)
	{
		actions.moveToElement(target,xAxisvalue,yAxisValue).click().perform();
	}
	
	public void selectVisibleText(WebElement listBox,String visibleTextOrIndex)
	{
		Select select = new Select(listBox);
		try
		{
			int index = Integer.parseInt(visibleTextOrIndex);
			select.selectByIndex(index);
		}
		catch(NumberFormatException e)
		{
			select.selectByVisibleText(visibleTextOrIndex);
		}		
	}
	
	public void elementClick(WebElement target)
	{
		wait.until(ExpectedConditions.elementToBeClickable(target));
		target.click();
	}
	
	public String getExpectedTitle(String expectedTitle)
	{
		wait.until(ExpectedConditions.titleIs(expectedTitle));
		return driver.getTitle();
	}
	
	public void waitForImageExist(WebElement target)
	{
		wait.until(ExpectedConditions.visibilityOf(target));
	}

	public void switchToFrame(String indexNameId)
	{
		try
		{
			Utilities.sleepInSeconds(10);
			try
			{
				int index = Integer.parseInt(indexNameId);
				driver.switchTo().frame(index);
			}
			catch(NumberFormatException e)
			{
				driver.switchTo().frame(indexNameId);
			}
		}
		catch(NoSuchFrameException e)
		{
			System.out.println("Opening PDP Page");
		}
	}
	
}


