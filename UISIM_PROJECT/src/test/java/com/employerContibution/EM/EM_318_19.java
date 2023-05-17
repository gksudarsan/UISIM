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
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_318_19 extends TestBase
{


	
	@Test(priority=1, description = "EM.318.019 Verify CSR is able to update Legal entity type information for type of legal entity 'Business'  and employer type 'Indian Tribe'",groups = {"Regression"})
	public void EM_318_012() throws Exception
	{
		 
		 test = report.createTest("EM.318.019 Verify CSR is able to update Legal entity type information for type of legal entity 'Business'  and employer type 'Indian Tribe'.");
		 commonStepDefinitions commonFuntions = new commonStepDefinitions();
		 Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'HOUA' AND EAN LIKE '9%'","EAN");
	     String eanValue = databaseEanResult.get("EAN");
	     System.out.println(eanValue);
	     LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 commonFuntions.login("ndfjp3","Admin@12345678");
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep();
		 commonFuntions.clickMenu("Menu");
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.ScrollMenu("Employer Account Maintenance");
		 commonFuntions.clickMenu("Employer Account Maintenance");
		 commonFuntions.clickMenu("Maintain Accounts");	
		 commonFuntions.screenShot("Employer Account Maintenance – Enter ERN","Pass","Employer Account Maintenance – Enter ERN is launched");
		 sleep();	 	 
		 commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		 commonFuntions.screenShot("Entered Employer Registration Number","Pass","Entered Employer Registration Number");
		 commonFuntions.clickButtonContains("Continue ");
		 commonFuntions.screenShot("Modify Employer Account Page Launched","Pass","Modify Employer Account Page Launched");
		 sleep();
	     commonFuntions.enterTextboxContains(" Business Phone Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		 commonFuntions.enterTextboxContains(" Business Fax Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		 commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@labor.ny.gov");
		 commonFuntions.selectDropdownUsingSearch("Type of Legal Entity"," Business "); sleep();
		 commonFuntions.selectRadio(" Send LDD097 SDC No Report Due letter");
		 commonFuntions.selectDropdown("Employer Type", " Indian Tribe Employer ");
		 commonFuntions.selectDropdown("Source", " NYS-45 (Quarterly Report) ");
		 commonFuntions.selectDropdown("Source Type", " NYS-45 Without Remittance ");
		 commonFuntions.screenShot("Modify Employer Account Details Entered","Pass","Modify Employer Account Details Entered");
		 commonFuntions.clickButtonContains("Submit ");
		 sleep();
		 commonFuntions.screenShot("Employer Account Maintenance Confirmation Launched","Pass","Employer Account Maintenance Confirmation Launched");
		 commonFuntions.Label("The Account Information has been succesfully saved");
		 sleep(2000);
		  
	}
}