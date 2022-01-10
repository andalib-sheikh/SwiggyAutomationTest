package com.swiggy.pages;

import java.sql.Timestamp;
import java.util.ArrayList;

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

	@FindBy(xpath="//a[@href='https://play.google.com/store/apps/details?id=in.swiggy.android']")
	WebElement linkPlayStore;

	@FindBy(xpath="//a[@href='https://itunes.apple.com/in/app/swiggy-food-order-delivery/id989540920']")
	WebElement linkAppStore;



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

	public String launchPage(String DeliveryLocation)
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
			inputDeliveryLocation.sendKeys(DeliveryLocation);
			if(inputDeliveryLocation.getAttribute("value").equals(DeliveryLocation))
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
			else
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 5
		baseHome.tempIndex++;
		try
		{
			js.executeScript("arguments[0].scrollIntoView();", linkPlayStore);
			if(linkPlayStore.isDisplayed())
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
			else
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 6
		baseHome.tempIndex++;
		try
		{
			String windowHandle = baseHome.driver.getWindowHandle();
			String playStoreURL=linkPlayStore.getAttribute("href");
			linkPlayStore.click();
			ArrayList tabs = new ArrayList (baseHome.driver.getWindowHandles());
			baseHome.driver.switchTo().window(tabs.get(tabs.size()-1)+"");
			if(baseHome.driver.getCurrentUrl().equals(playStoreURL) && baseHome.driver.getTitle().contains("Swiggy"))
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
			else
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
			baseHome.driver.close();
			baseHome.driver.switchTo().window(windowHandle);
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 7
		baseHome.tempIndex++;
		try
		{
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView();", linkAppStore);
			if(linkAppStore.isDisplayed())
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
			else
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		//Step 8
		baseHome.tempIndex++;
		try
		{
			String windowHandle = baseHome.driver.getWindowHandle();
			linkAppStore.click();
			ArrayList tabs = new ArrayList (baseHome.driver.getWindowHandles());
			baseHome.driver.switchTo().window(tabs.get(tabs.size()-1)+"");
			if(baseHome.driver.getTitle().contains("Swiggy"))
				baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);	
			else
				return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
			baseHome.driver.switchTo().window(windowHandle);
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
