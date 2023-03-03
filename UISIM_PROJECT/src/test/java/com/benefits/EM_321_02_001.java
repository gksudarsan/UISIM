package com.benefits;



import java.sql.SQLException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//import com.relevantcodes.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.pages.LoginPage;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_321_02_001 extends TestBase
{


	
	@Test(priority=1, description = "EM.321.02.001 - Verify Employer is able to Edit Corporate Officer/Owner Details.",groups = {"Regression"})
	public void EM_321_02_001() throws Exception
	{
		 test = report.createTest("EM.321.02.001 - Verify Employer is able to Edit Corporate Officer/Owner Details.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login("tst_empr","Nysdol@0210");
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.screenShot("Menu","Pass","Maintain Business Ownership");
		 commonFuntions.clickMenu("Maintain Business Ownership");
		 commonFuntions.ScrollMenu("Add Corporate Officer/Owner Details");
		 commonFuntions.screenShot("Add Corporate Officer/Owner Details","Pass","Add Corporate Officer/Owner Details");
		 commonFuntions.clickMenu("Add Corporate Officer/Owner Details");
	       
	    
	}
	
}