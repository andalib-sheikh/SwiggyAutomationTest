package com.swiggy.testcases;

import java.sql.Timestamp;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swiggy.base.BasePage;
import com.swiggy.pages.HomePage;
import com.swiggy.utils.ReadExcel;

public class HomePageTests 
{
	HomePage homePage;
	String text="";
	BasePage baseHome;

	public HomePageTests()
	{
		baseHome=new BasePage("Home");
	}

	@BeforeClass
	public void setup() throws Exception
	{
		try
		{
			Thread.sleep(3000);
			baseHome.initialization("Home");
			baseHome.scriptStartTime=new Timestamp(System.currentTimeMillis());
			homePage=new HomePage(baseHome);
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@AfterClass
	public void tearDown()
	{
		baseHome.scriptEndTime=new Timestamp(System.currentTimeMillis());
		try
		{
			Thread.sleep(1000);
			baseHome.docER.close();
			baseHome.writerER.close();
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		baseHome.docTSR.add(baseHome.tableTSR);
		baseHome.pdfCreate.createPDF(baseHome);
		baseHome.docTSR.close();
		baseHome.writerTSR.close();
		baseHome.driver.quit();
	}

	@AfterSuite
	public void mergePdfs()
	{
		baseHome.pdfMerge();
	}

	public void performAfter()
	{
		if(text.equals("Success"))
		{
			baseHome.scriptsPassed++;
			System.out.println(homePage.testName+" test case passed!");
		}
		else
		{
			baseHome.scriptFailed++;
			System.out.println(homePage.testName+" test case failed after step "+(baseHome.tempIndex+1));	
		}
		baseHome.pdfCreate.createPDF(homePage.testName,homePage.startTime+"",homePage.timestamp+"",baseHome,"Career");
	}
	
	@Test(priority=1,dataProvider="getLaunchPageTestData")
	public void launchPage(String DeliveryLocation)
	{
		baseHome.totalScripts++;
		try
		{
			//baseHome.driver.navigate().refresh();
			text=homePage.launchPage(DeliveryLocation);
			performAfter();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@DataProvider
	public static Object[][] getLaunchPageTestData()
	{
		Object data[][]=ReadExcel.getTestData("LaunchPage");
		return data;
	}
}