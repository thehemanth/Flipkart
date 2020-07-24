package com.ecommerce.flipkart.pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.flipkart.libraries.Utilities;
import com.ecommerce.flipkart.libraries.WebActionUtil;

public class ProductDetailPage extends BasePage
{
	@FindBy(xpath="//i[contains(@class,'icon-plus')]")
	private WebElement plusBtn;
	
	@FindBy(xpath="//i[contains(@class,'icon-minus')]")
	private WebElement minusBtn;
	
	@FindBy(id="group_1")
	private WebElement sizeListBox;
	
	@FindBy(xpath="//ul[@id='color_to_pick_list']//a")
	private List<WebElement> colorPicker;
	
	@FindBy(name="Submit")
	private WebElement addToCartBtn;
		
	@FindBy(xpath="//a[@title='Proceed to checkout']")
	private WebElement proceedToCheckOutBtn;
	
	@FindBy(xpath="//span[contains(.,'Continue shopping')]")
	private WebElement continueToShoppingBtn;
	
	@FindBy(className="cross")
	private WebElement closeBtn;
	
	public ProductDetailPage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
	
	public void quantity(int count, boolean increase)
	{
		WebElement btn;
		if(increase)
		{
			btn = plusBtn;
		}
		else
		{
			btn = minusBtn;
		}
		for(int i=1;i<=count;i++)
		{
			webActionUtil.javaScriptClick(btn);
			Utilities.sleepInSeconds(1);
		}
	}
	
	public void selectSize(String size)
	{
		webActionUtil.selectVisibleText(sizeListBox, size);
	}
	
	public boolean selectColor(String colorId)
	{
		for(WebElement color:colorPicker)
		{
			String id=color.getAttribute("id");
			if(id.equalsIgnoreCase(colorId))
			{
				webActionUtil.elementClick(color);
				return true;
			}
		}
		return false;
	}
	
	public BasePage addItemToCart(boolean proceedToCheckOut)
	{
		if(proceedToCheckOut)
		{
			webActionUtil.elementClick(addToCartBtn);
			webActionUtil.elementClick(proceedToCheckOutBtn);
			return new OrderPage(driver, webActionUtil);
		}
		else
		{
			webActionUtil.elementClick(continueToShoppingBtn);
			return this;
		}
	}
	
	public void closeAddToCartPopUp()
	{
		webActionUtil.elementClick(closeBtn);
	}
}
