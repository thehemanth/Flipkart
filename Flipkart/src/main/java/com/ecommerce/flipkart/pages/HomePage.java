package com.ecommerce.flipkart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.flipkart.libraries.WebActionUtil;

public class HomePage extends BasePage
{
	@FindBy(className="logout")
	private WebElement signOutBtn;
	
	@FindBy(linkText="Women")
	private WebElement womenLink;
	
	@FindBy(xpath="(//a[text()='Dresses'])[2]")
	private WebElement dressesLink;
	
	@FindBy(xpath="(//a[text()='T-shirts'])[2]")
	private WebElement tShirtsLink;
	
	@FindBy(id="search_query_top")
	private WebElement searchTB;
	
	@FindBy(id="selectProductSort")
	private WebElement sortByListBox;
	
	@FindBy(id="Grid")
	private WebElement gridView;
	
	@FindBy(id="List")
	private WebElement listView;
	
	@FindBy(xpath="//a[@class='product_img_link']")
	private List<WebElement> productsList;
	
	public HomePage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver,webActionUtil);
	}
	
	public void signOut()
	{
		webActionUtil.elementClick(signOutBtn);
	}
	
	public void clickOnMenu(String option)
	{
		if(option.equalsIgnoreCase("women"))
		{
			webActionUtil.elementClick(womenLink);
		}
		else if(option.equalsIgnoreCase("dresses"))
		{
			webActionUtil.elementClick(dressesLink);
		}
		else if(option.equalsIgnoreCase("tshirts"))
		{
			webActionUtil.elementClick(tShirtsLink);
		}
	}
	
	public void togleView(String view)
	{
		if(view.equalsIgnoreCase("grid"))
		{
			webActionUtil.elementClick(gridView);
		}
		else if(view.equalsIgnoreCase("list"))
		{
			webActionUtil.elementClick(listView);
		}
	}
	
	public ProductDetailPage selectProduct(String productId)
	{
		productId="id_product="+productId;
		for(WebElement product:productsList)
		{
			String href=product.getAttribute("href");
			if(href.contains(productId))
			{
				webActionUtil.scrollToElement(product);
				webActionUtil.javaScriptClick(product);
				break;
			}
		}
		webActionUtil.switchToFrame("0");
		return new ProductDetailPage(driver, webActionUtil);
	}
}
 