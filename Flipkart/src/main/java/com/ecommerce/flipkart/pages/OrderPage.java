package com.ecommerce.flipkart.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.flipkart.libraries.Utilities;
import com.ecommerce.flipkart.libraries.WebActionUtil;
public class OrderPage extends BasePage
{
	
	@FindBy(xpath="//tbody//td[@class='cart_product']/a")
	private List<WebElement> productsList;
	
	@FindBy(xpath="//tbody//td[@class='cart_delete text-center']/div/a")
	private WebElement deleteBtn;
	
	@FindBy(xpath = "//div//p[@class='alert alert-warning']")
	private WebElement alertWarning;

	
	public OrderPage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
	
	public boolean isProductDisplayed(String productId)
	{
		for(WebElement product:productsList)
		{
			String av=product.getAttribute("href");
			if(av.contains(productId))
			{
				return true;
			}
		}
		return false;
	}
	
	public void deleteProduct(String productId) 
	{
			String av =deleteBtn.getAttribute("href");
			if(av.contains(productId))
			{
				Utilities.sleepInSeconds(3);
				deleteBtn.click();
			}	
	}
	
	public boolean alertMessage()
	{
			
			String alertText = alertWarning.getText();
			String expectedText = "Your shopping cart is empty.";
			if(expectedText.equals(alertText))
			{
				Utilities.sleepInSeconds(3);
				return true;
			}	
			return false;
	}

	
}
