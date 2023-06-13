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
public class EM_310_06_001 extends TestBase
{


	
	@Test(priority=1, description = "EM_310_06_001 - Verify CSR is able to report a change in Legal entity.",groups = {"Regression"})
	public void EM_310_06_001() throws Exception
	{
		 
		 test = report.createTest("EM_310_06_001 - Verify CSR is able to report a change in Legal entity.");
		 commonStepDefinitions commonFuntions = new commonStepDefinitions();
		 //Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'HOUD' AND EAN LIKE '8%'","EAN");
		 Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'HOUD' AND EAN IS NOT NULL","EAN");
	     String eanValue = databaseEanResult.get("EAN");
	     System.out.println(eanValue);
	     LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	     commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickMenu("Menu");
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.ScrollMenu("Change in Legal Entity");
		 commonFuntions.clickMenu("Change in Legal Entity");	
		 commonFuntions.screenShot("Change in Legal Entity – Enter ERN","Pass","Change in Legal Entity – Enter ERN is launched");
		 sleep();
		 commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
		 commonFuntions.screenShot("Employer Registration Number","Pass","Employer Registration Number entered");
		 commonFuntions.clickButtonContains("Continue ");
		 commonFuntions.screenShot("Change in Legal Entity","Pass","Change in Legal Entity page launched");
		 sleep();
		 commonFuntions.selectRadio("Yes ");
		 commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) " ,"815318734");
		 commonFuntions.enterTextboxContains("Prior Employer Registration Number","5426996");
		 commonFuntions.enterTextboxContains("Date of Legal Entity change", "10/1/1799");
		 commonFuntions.enterTextboxContains("Date of Notification", "10/1/1799");
		 commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		 commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		 commonFuntions.screenShot("Change in Legal Entity ","Pass","Change in Legal Entity Details entered");
		 commonFuntions.clickButtonContains("Continue ");
		 commonFuntions.screenShot("Popup","Pass","Popup is coming");
		 sleep();
		 commonFuntions.clickButtonContains(" Yes ");
		 commonFuntions.screenShot("Change in Legal Entity Details","Pass","Change in Legal Entity Details Table is launched");
		 commonFuntions.clickButtonContains("Submit ");
		 sleep();
		 commonFuntions.screenShot("Change in Legal Entity Confirmation","Pass","Change in Legal Entity Confirmation Page is launched");
		 sleep(2000);

	}
}