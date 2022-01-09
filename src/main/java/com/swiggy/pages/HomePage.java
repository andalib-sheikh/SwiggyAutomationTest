package com.swiggy.pages;

import java.sql.Timestamp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import com.swiggy.base.BasePage;
import com.swiggy.utils.ElementUtil;

public class HomePage 
{
	
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
			timestamp=new Timestamp(System.currentTimeMillis());
			startTime=timestamp;
			baseHome.pass(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+testName+" test case at Step "+(baseHome.tempIndex+1)+". Details: "+e.getMessage());
			e.printStackTrace();
			timestamp=new Timestamp(System.currentTimeMillis());
			startTime=timestamp;
			return baseHome.fail(testName, baseHome.launchPageCounter, baseHome.docER, baseHome.fontER);
		}
		text="Success";
		return text;
	}
}
