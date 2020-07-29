package com.ecommerce.flipkart.testscripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.flipkart.pages.HomePage;
import com.ecommerce.flipkart.pages.OrderPage;
import com.ecommerce.flipkart.pages.ProductDetailPage;
public class TCB005 extends BaseTest
{
	@Test(description="To Verify product is displayed in Order Page")
	public void verifyProduct()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		hp.clickOnMenu("Dresses");
		ProductDetailPage pdp = hp.selectProduct("7");
		pdp.quantity(3, true);
		pdp.quantity(1, false);
		pdp.selectSize("S");
		pdp.selectColor("color_15");
		OrderPage orderPage = (OrderPage) pdp.addItemToCart(true);
		Assert.assertTrue(orderPage.isProductDisplayed("7"));
	}
}