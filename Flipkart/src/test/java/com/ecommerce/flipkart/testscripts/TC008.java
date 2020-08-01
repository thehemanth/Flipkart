package com.ecommerce.flipkart.testscripts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ecommerce.flipkart.libraries.ExcelLibrary;
import com.ecommerce.flipkart.pages.HomePage;
import com.ecommerce.flipkart.pages.OrderPage;
import com.ecommerce.flipkart.pages.ProductDetailPage;

public class TC008 extends BaseTest
{
	
	@Test(description="To Verify product displayed in Order Page is deleted")
	public void verifyProductIsDeleted()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		String menuItem=ExcelLibrary.getValue(XL_PATH, "TC002", 1, 0);
		String productId=ExcelLibrary.getValue(XL_PATH, "TC002", 1, 1).substring(0,1);
		String dressSize=ExcelLibrary.getValue(XL_PATH, "TC002", 1, 2);
		String colorId=ExcelLibrary.getValue(XL_PATH, "TC002", 1, 3);
		
		hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = hp.selectProduct(productId);
		pdp.quantity(3, true);
		pdp.quantity(1, false);
		pdp.selectSize(dressSize);
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(pdp.selectColor(colorId));
		OrderPage orderPage = (OrderPage) pdp.addItemToCart(true);
		Assert.assertTrue(orderPage.isProductDisplayed(productId));
		orderPage.deleteProduct(productId);
		orderPage.alertMessage();
		sf.assertAll();
	}
}

