package com.swiggy.testcases;

import java.sql.Timestamp;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swiggy.base.BasePage;
import com.swiggy.pages.LoginPage;
import com.swiggy.pages.LoginPage;
import com.swiggy.utils.ReadExcel;

public class LoginPageTests 
{
	LoginPage loginPage;
	String text="";
	BasePage baseLogin;

	public LoginPageTests()
	{
		baseLogin=new BasePage("Login");
	}

	@BeforeClass
	public void setup() throws Exception
	{
		try
		{
			baseLogin.initialization("Login");
			baseLogin.scriptStartTime=new Timestamp(System.currentTimeMillis());
			loginPage=new LoginPage(baseLogin);
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@AfterClass
	public void tearDown()
	{
		baseLogin.scriptEndTime=new Timestamp(System.currentTimeMillis());
		try
		{
			Thread.sleep(1000);
			baseLogin.docER.close();
			baseLogin.writerER.close();
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		baseLogin.docTSR.add(baseLogin.tableTSR);
		baseLogin.pdfCreate.createPDF(baseLogin);
		baseLogin.docTSR.close();
		baseLogin.writerTSR.close();
		baseLogin.driver.quit();
	}

	@AfterSuite
	public void mergePdfs()
	{
		baseLogin.pdfMerge();
	}

	public void performAfter()
	{
		if(text.equals("Success"))
		{
			baseLogin.scriptsPassed++;
			System.out.println(loginPage.testName+" test case passed!");
		}
		else
		{
			baseLogin.scriptFailed++;
			System.out.println(loginPage.testName+" test case failed after step "+(baseLogin.tempIndex+1));	
		}
		baseLogin.pdfCreate.createPDF(loginPage.testName,loginPage.startTime+"",loginPage.timestamp+"",baseLogin,"Career");
	}
	
	@Test(priority=1,dataProvider="getLoginTestData")
	public void login(String PhoneNo, String RandomOTP)
	{
		baseLogin.totalScripts++;
		try
		{
			//baseLogin.driver.navigate().refresh();
			text=loginPage.login(PhoneNo, RandomOTP);
			performAfter();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@DataProvider
	public static Object[][] getLoginTestData()
	{
		Object data[][]=ReadExcel.getTestData("Login");
		return data;
	}
}