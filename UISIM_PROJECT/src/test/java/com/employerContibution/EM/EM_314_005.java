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
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_314_005 extends TestBase
{


	
	@Test(priority=1, description = "EM.314.005 - Verify CSR is able to enter request for change method of financing to Contributory and new ERN for employer type non profit.",groups = {"Regression"})
	public void EM_314_005() throws Exception
	{
		 
		 test = report.createTest("EM.314.005 - Verify CSR is able to enter request for change method of financing to Contributory and new ERN for employer type non profit.");
		 commonStepDefinitions commonFuntions = new commonStepDefinitions();
		 Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'NONP' AND EAN IS NOT NULL","EAN");
	     String eanValue = databaseEanResult.get("EAN");
	     System.out.println(eanValue);
	     HomePage HP = new HomePage(driver);
	     LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	     commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickMenu("Menu");
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.ScrollMenu("Employer Account Maintenance");
		 commonFuntions.clickMenu("Employer Account Maintenance");
		 commonFuntions.ScrollMenu("Change in Method of Financing");
		 commonFuntions.clickMenu("Change in Method of Financing");	
		 sleep();
		 commonFuntions.screenShot("Change in Method of Financing - Enter ERN", "Pass","Change in Method of Financing - Enter ERN Screen Launched");
         commonFuntions.enterTextboxContains("Employer Registration Number", eanValue); 
		 commonFuntions.screenShot("Entered Employer Registration Number","Pass","Entered Employer Registration Number");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep();
		 commonFuntions.screenShot("Change in Method of Financing","Pass","Change in Method of Financing Screen Launched");
		 commonFuntions.selectRadio("Yes ");
		 commonFuntions.enterTextboxContains("Requested Effective Date","17/03/2023");
		 HP.commentBox.sendKeys("for testing");
		 commonFuntions.selectDropdown("Source", " Correspondence/Email ");
		 commonFuntions.selectDropdown("Source Type", " Correspondence/Email ");
		 commonFuntions.clickButtonContains("Continue ");
		 sleep();
		 commonFuntions.screenShot("Change in Method of Financing Confirmation","Pass","Change in Method of Financing Verification Screen Launched");
		 commonFuntions.selectRadio("Yes ");
		 commonFuntions.clickButtonContains("Submit ");
		 sleep();
		 commonFuntions.screenShot("Change in Method of Financing Verification","Pass","Change in Method of Financing Confirmation Screen Launched");
		 commonFuntions.clickButtonContains("Home ");
		 sleep(); 
		 commonFuntions.screenShot("Home","Pass","Home Page Launched");
		 sleep(2000);
	}
	
}