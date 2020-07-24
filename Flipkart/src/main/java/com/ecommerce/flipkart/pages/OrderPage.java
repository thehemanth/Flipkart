package com.ecommerce.flipkart.pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.flipkart.libraries.WebActionUtil;
public class OrderPage extends BasePage
{
	
	@FindBy(xpath="//tbody//td[@class='cart_product']/a")
	private List<WebElement> productsList;
	
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
	
}
