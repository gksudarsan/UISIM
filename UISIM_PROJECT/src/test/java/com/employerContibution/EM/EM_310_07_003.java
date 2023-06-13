package com.employerContibution.EM;



import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

//import com.relevantcodes.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_310_07_003 extends TestBase
{


	
	@Test(priority=1, description = "EM.310.07.003 - Verify Employer is able to change in Legal entity when predecessor provided information is erroneous and system create task for CSR reviews.",groups = {"Regression"})
	public void EM_310_07_003() throws Exception
	{
		 
		 test = report.createTest("EM.310.07.003 - Verify Employer is able to change in Legal entity when predecessor provided information is erroneous and system create task for CSR reviews.");
		 commonStepDefinitions commonFuntions = new commonStepDefinitions();
		// Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("","EAN");
	   //  String eanValue = databaseEanResult.get("EAN");
	    // System.out.println(eanValue);
	     LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	     commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_5,COMMON_CONSTANT.EMPLOYER_USER_5_PASSWORD );
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickMenu("Menu");
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.ScrollMenu("Change in Legal Entity");
		 commonFuntions.clickMenu("Change in Legal Entity");	
		 commonFuntions.screenShot("Change in Legal Entity","Pass","Change in Legal Entity Screen launched");
		 sleep();
		 commonFuntions.selectRadio("Yes ");
		 commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) " ,"364567009");
		 commonFuntions.enterTextboxContains("Prior Employer Registration Number","11-11111");
		 commonFuntions.enterTextboxContains("Date of Legal Entity change", "10/1/1799");
		 commonFuntions.enterTextboxContains("Date of Notification", "10/1/1799");
		 commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		 commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep(2000);
		 commonFuntions.screenShot("Change in Legal Entity Details","Pass","Change in Legal Entity Details Screen launched");
		 commonFuntions.clickButtonContains("Submit ");
		 sleep(2000);
		 commonFuntions.screenShot("Change in Legal Entity Confirmation","Pass","Change in Legal Entity Confirmation Screen launched");
		 commonFuntions.clickButtonContains("Home ");
	     commonFuntions.screenShot("Home Page", "Pass", "Home Page Launched");
	     sleep(2000);
	     commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickButtonContains(" Go to My Q ");
	//	 commonFuntions.enterTextboxContains("Claimant ID", 

	
	}
}