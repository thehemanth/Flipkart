package com.ecommerce.flipkart.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecommerce.flipkart.libraries.WebActionUtil;
public class LoginPage extends BasePage
{
	@FindBy(id="email")
	private WebElement emailTB;
	
	@FindBy(id="passwd")
	private WebElement passwordTB;
	
	@FindBy(id="SubmitLogin")
	private WebElement signInBtn;
	
	@FindBy(linkText="Forgot your password?")
	private WebElement forgotPasswordLink;
	
	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
	
	public HomePage login(String un, String pwd)
	{
		webActionUtil.enterKeys(emailTB, un);
		webActionUtil.enterKeys(passwordTB, pwd);
		webActionUtil.elementClick(signInBtn);
		return new HomePage(driver, webActionUtil);
	}
}
