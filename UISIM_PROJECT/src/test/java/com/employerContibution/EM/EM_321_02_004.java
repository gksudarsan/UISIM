package com.employerContibution.EM;



import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
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
public class EM_321_02_004 extends TestBase
{


	
	@Test(priority=1, description = "EM.321.02.004 - Verify Employer is able to Edit Partner Details.",groups = {"Regression"})
	public void EM_321_02_004() throws Exception
	{
		 
		 test = report.createTest("EM.321.02.004 - Verify Employer is able to Edit Partner Details.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login("Laxmi@12","Hachiku7533991779");
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.screenShot("Menu","Pass","Maintain Business Ownership");
		 commonFuntions.clickMenu("Maintain Business Ownership");			 
		 commonFuntions.ScrollMenu("Add Partner Details");
		 commonFuntions.screenShot("Add Partner Details","Pass","Add Partner Details");
		 commonFuntions.clickMenu("Add Partner Details");		 
		 long number = commonFuntions.createRandomInteger(100000000,999999999);
		 String ssnValue=Long.toString(number);
	     populateFields(ssnValue);	     
	     commonFuntions.clickButtonContains("Submit");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Success","Pass","Successfully added Partner Details");
	     addCorporatePage.successLink.click();
		 Thread.sleep(2000);
	     verifyFields(ssnValue);	
	     ssnValue = StringUtils.left(ssnValue, 3)+"-"+StringUtils.right(StringUtils.left(ssnValue, 5),2)+"-"+StringUtils.right(ssnValue, 4);
	     
	     commonFuntions.selectTable(ssnValue,10,1,"Individual as Partner ");
	     Thread.sleep(2000);
	     commonFuntions.enterTextboxContains("Address Line 1", "Updated address line 1");
	     commonFuntions.enterTextboxContains("Address Line 2", "Updated address line 2");
	     commonFuntions.clickButtonContains("Submit");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Success2","Pass","Successfully amended Partner Details");
	     addCorporatePage.successLink.click();
	     Thread.sleep(2000);		
	     
	     String address = commonFuntions.retrieveValueFromTable(ssnValue,4,1,"Individual as Partner ");	   
	    Assert.assertEquals(address.contains("UPDATED ADDRESS LINE 1"), true);
	    Assert.assertEquals(address.contains("UPDATED ADDRESS LINE 2"), true);
	     
	   	   
	}
	public void populateFields(String ssnValue) throws Exception {
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		Thread.sleep(2000);
		commonFuntions.selectRadio("Individual");
	     Random random = new Random();
	     commonFuntions.enterTextbox("SSN ",ssnValue );
	     commonFuntions.enterTextboxContains("First Name", "AutomationFirstName"+random.nextInt(10000));	    
	     commonFuntions.enterTextboxContains("Last Name", "AutomationLastName"+random.nextInt(10000));
	     commonFuntions.selectDropdown("Title", "Partner");
	     commonFuntions.screenShot("Populate","Pass","populate Partner Details");	     
	     commonFuntions.enterTextboxContains("Address Line 1", "Added address line 1");
	     commonFuntions.enterTextboxContains("Address Line 2", "Added address line 2");
	     commonFuntions.enterTextboxContains("City", "Added City");	    
	     commonFuntions.enterTextboxContains("Zip", String.valueOf((long) (Math.random()*Math.pow(10,6))));
	     commonFuntions.enterTextboxContains("Contact Number", String.valueOf((long) (Math.random()*Math.pow(10,11))));
	     commonFuntions.screenShot("Submit","Pass","Submit Partner Details");	
	}
	public void verifyFields(String ssnValue) throws InterruptedException {
		ssnValue = ssnValue = StringUtils.left(ssnValue, 3)+"-"+StringUtils.right(StringUtils.left(ssnValue, 5),2)+"-"+StringUtils.right(ssnValue, 4);
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		Thread.sleep(3000);
		String  address = commonFuntions.retrieveValueFromTable(ssnValue,4,1,"Individual as Partner ");
		System.out.println(address);
		 Assert.assertEquals(address.contains("ADDED ADDRESS LINE 1"), true);
		    Assert.assertEquals(address.contains("ADDED ADDRESS LINE 2"), true);
	}
}