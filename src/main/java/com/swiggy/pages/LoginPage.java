package com.swiggy.pages;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swiggy.base.BasePage;
import com.swiggy.utils.ElementUtil;

public class LoginPage 
{

	@FindBy(xpath="//*[name()='svg' and @class='_1envo']")
	WebElement logoSwiggy;

	@FindBy(xpath="//a[text()='Login']")
	WebElement linkLogin;

	@FindBy(xpath="//div[@id='overlay-sidebar-root']//form")
	WebElement formLogin;

	@FindBy(xpath="//input[@id='mobile']")
	WebElement inputPhoneNo;

	@FindBy(xpath="//div[@id='overlay-sidebar-root']//form//a[text()='Login']")
	WebElement btnLogin;

	@FindBy(xpath="//input[@id='otp']")
	WebElement inputOTP;
	
	@FindBy(xpath="//div[@id='overlay-sidebar-root']//form//a[text()='VERIFY OTP']")
	WebElement btnVerifyOTP;



	ElementUtil elementUtil;
	public String text;
	public Timestamp timestamp, startTime, endTime;
	public String testName="";
	JavascriptExecutor js;
	BasePage baseHome;

	public LoginPage(BasePage baseHome) 
	{
		this.baseHome=baseHome;
		PageFactory.initElements(baseHome.driver, this);
		elementUtil=new ElementUtil(baseHome.driver);
		js=(JavascriptExecutor)baseHome.driver;
	}

	public String login(String PhoneNo, String RandomOTP)
	{
		baseHome.login++;
		testName="Login";
		baseHome.initVars(testName);
		//Step 1
		try
		{
			elementUtil.waitForElementToBeVisible(logoSwiggy);
			timestamp=new Timestamp(System.currentTimeMillis());
			startTime=timestamp;
			if(baseHome.driver.getCurrentUrl().equals(baseHome.prop.getProperty("url")) && logoSwiggy.isDisplayed())
			{
				baseHome.pass(testName, baseHome.login, baseHome.docER, baseHome.fontER);
			}
			else
			{
				return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			timestamp=new Timestamp(System.currentTimeMillis());
			startTime=timestamp;
			return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
		}
		//Step 2
		baseHome.tempIndex++;
		try
		{
			js.executeScript("arguments[0].scrollIntoView();", linkLogin);
			if(linkLogin.isDisplayed())
			{
				baseHome.pass(testName, baseHome.login, baseHome.docER, baseHome.fontER);	
			}
			else
			{
				return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
		}
		//Step 3
		baseHome.tempIndex++;
		try
		{
			linkLogin.click();
			elementUtil.waitForElementToBeVisible(formLogin);
			if(formLogin.isDisplayed())
			{
				baseHome.pass(testName, baseHome.login, baseHome.docER, baseHome.fontER);	
			}
			else
			{
				return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
		}
		//Step 4
		baseHome.tempIndex++;
		try
		{
			PhoneNo=PhoneNo.substring(0,PhoneNo.indexOf("."))+PhoneNo.substring(PhoneNo.indexOf(".")+1,PhoneNo.indexOf("E"));
			inputPhoneNo.sendKeys(PhoneNo);
			Thread.sleep(1000);
			if(inputPhoneNo.getAttribute("value").equals(PhoneNo))
			{
				baseHome.pass(testName, baseHome.login, baseHome.docER, baseHome.fontER);	
			}
			else
			{
				return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
		}
		//Step 5
		baseHome.tempIndex++;
		try
		{
			btnLogin.click();
			Thread.sleep(2000);
			elementUtil.waitForElementToBeVisible(inputOTP);
			if(inputOTP.isDisplayed())
			{
				baseHome.pass(testName, baseHome.login, baseHome.docER, baseHome.fontER);	
			}
			else
			{
				return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
		}
		//Step 6
		baseHome.tempIndex++;
		try
		{
			inputOTP.sendKeys(RandomOTP);
			btnVerifyOTP.click();
			if(baseHome.driver.findElement(By.xpath("//label[@for='otp']")).getText().equals("Enter valid OTP"))
			{
				baseHome.pass(testName, baseHome.login, baseHome.docER, baseHome.fontER);	
			}
			else
			{
				return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.login, baseHome.docER, baseHome.fontER);
		}
		text="Success";
		return text;
	}
}
