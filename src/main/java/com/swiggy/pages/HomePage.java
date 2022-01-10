package com.swiggy.pages;

import java.sql.Timestamp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swiggy.base.BasePage;
import com.swiggy.utils.ElementUtil;

public class HomePage 
{

	@FindBy(xpath="//*[name()='svg' and @class='_1envo']")
	WebElement logoSwiggy;

	@FindBy(xpath="//a[text()='Login']")
	WebElement linkLogin;

	@FindBy(xpath="//a[text()='Sign up']")
	WebElement linkSignUp;

	@FindBy(xpath="//input[@id='location']")
	WebElement inputDeliveryLocation;

	@FindBy(xpath="//span[text()='Locate Me']")
	WebElement btnLocateMe;

	@FindBy(xpath="//a/span[text()='FIND FOOD']")
	WebElement btnFindFood;

	@FindBy(xpath="//div[text()='Enter your delivery location']")
	WebElement divEnterLocation;



	ElementUtil elementUtil;
	public String text;
	public Timestamp timestamp, startTime, endTime;
	public String testName="";
	JavascriptExecutor js;
	BasePage baseHome;

	public HomePage(BasePage baseHome) 
	{
		this.baseHome=baseHome;
		PageFactory.initElements(baseHome.driver, this);
		elementUtil=new ElementUtil(baseHome.driver);
		js=(JavascriptExecutor)baseHome.driver;
	}

	public String launchPage(String Test)
	{
		baseHome.launchPageCounter++;
		testName="Launch Page";
		baseHome.initVars(testName);
		//Step 1
		try
		{
			elementUtil.waitForElementToBeVisible(logoSwiggy);
			timestamp=new Timestamp(System.currentTimeMillis());
			startTime=timestamp;
			if(baseHome.driver.getCurrentUrl().equals(baseHome.prop.getProperty("url")) && logoSwiggy.isDisplayed())
			{
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
			}
			else
			{
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			timestamp=new Timestamp(System.currentTimeMillis());
			startTime=timestamp;
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 2
		baseHome.tempIndex++;
		try
		{
			js.executeScript("arguments[0].scrollIntoView();", linkLogin);
			if(linkLogin.isDisplayed() && linkSignUp.isDisplayed())
			{
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
			}
			else
			{
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 3
		baseHome.tempIndex++;
		try
		{
			//js.executeScript("arguments[0].scrollIntoView();", inputDeliveryLocation);
			if(inputDeliveryLocation.isDisplayed() && btnLocateMe.isDisplayed() && btnFindFood.isDisplayed())
			{
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
			}
			else
			{
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 4
		baseHome.tempIndex++;
		try
		{
			try
			{
				if(divEnterLocation.isDisplayed())
					return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
			}
			catch(NoSuchElementException e)
			{
				btnFindFood.click();
				elementUtil.waitForElementToBeVisible(divEnterLocation);
				if(divEnterLocation.isDisplayed())
				{
					baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
				}
				else
				{	
					return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
						
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 4
		baseHome.tempIndex++;
		try
		{
			btnLocateMe.click();
			elementUtil.waitForAlertPresent(3000);
			baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		text="Success";
		return text;
	}
}
