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
import com.ui.pages.EmployerRegisterPage;
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
		 EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		// Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("","EAN");
	   //  String eanValue = databaseEanResult.get("EAN");
	    // System.out.println(eanValue);
	     LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	     commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_1,COMMON_CONSTANT.EMPLOYER_USER_1_PASSWORD );
	   
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickMenu("menu");
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.ScrollMenu("Change in Legal Entity");
		 commonFuntions.clickMenu("Change in Legal Entity");	
		 commonFuntions.screenShot("Change in Legal Entity","Pass","Change in Legal Entity Screen launched");
		 sleep();
		 
		 //---SREG-012---
		 commonFuntions.selectRadio("Yes ");
		 commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(9,99)));
		 commonFuntions.enterTextboxContains("Prior Employer Registration Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(7,99)));
		 commonFuntions.enterTextboxContains("Date of Legal Entity change", "10/1/2023");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep(2000);
		 
		 //---EM-007---
		 commonFuntions.screenShot("Change in Legal Entity Details","Pass","Change in Legal Entity Details Screen launched");
		 commonFuntions.clickButtonContains("Submit ");
		 sleep(2000);
		 
		 //---SUC-002---
		 commonFuntions.screenShot("Change in Legal Entity Confirmation","Pass","Change in Legal Entity Confirmation Screen launched");
		 commonFuntions.clickButtonContains("Home ");
		 
		 //---Home---
		 commonFuntions.screenShot("Home Page", "Pass", "Home Page Launched");
	     sleep(2000);
	     
	     commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
	     sleep(3000);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickButtonContains(" Go to My Q ");
		// --- WF-001 ---
		 sleep(10000);
		 commonFuntions.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		 commonFuntions.enterTextboxContains("Work Item Description Free Text", "Verify predecessor Data");
		 commonFuntions.clickButton(" Search ");
	     commonFuntions.clickHyperlink("Verify predecessor Data");
       
	     // --- WF-091 ---
	     commonFuntions.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
	     commonFuntions.clickButton("Open Work Item ");
       
	     // --- EEWI-012 ---
	     commonFuntions.enterTextboxContains("First date of operation", "6/4/2023");
	     commonFuntions.selectDropdown("Account Status", " Liable ");
	     commonFuntions.enterTextboxContains("Legal Name Of Business", "TCS CORPORATION");
	     commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
	     empRegPage.commentBox_MyQ.sendKeys("for testing");
	     commonFuntions.clickButton("Submit ");
	     
	     //---SUC-002---
	     commonFuntions.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
	     commonFuntions.clickButtonContains("Home ");
		 
		 //---Home---
	     commonFuntions.screenShot("Home Page", "Pass", "Home Page Launched");
	     sleep(2000);
	}
}