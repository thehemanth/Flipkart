package com.ecommerce.flipkart.testscripts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ecommerce.flipkart.libraries.ExcelLibrary;
import com.ecommerce.flipkart.pages.HomePage;
import com.ecommerce.flipkart.pages.OrderPage;
import com.ecommerce.flipkart.pages.ProductDetailPage;

public class TC003 extends BaseTest
{
	@DataProvider
	public Object[][] getData()
	{
		return ExcelLibrary.getData(XL_PATH, "TC003");
	}
	
	
	@Test(description="To Verify product is displayed in Order Page", dataProvider="getData")
	public void verifyProductWithExcelData(String menuItem, String productId,
										   String dressSize, String colorId)
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = hp.selectProduct(productId.substring(0,1));
		pdp.quantity(3, true);
		pdp.quantity(1, false);
		pdp.selectSize(dressSize);
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(pdp.selectColor(colorId));
		OrderPage orderPage = (OrderPage) pdp.addItemToCart(true);
		Assert.assertTrue(orderPage.isProductDisplayed(productId.substring(0,1)));
		sf.assertAll();
	}
}
