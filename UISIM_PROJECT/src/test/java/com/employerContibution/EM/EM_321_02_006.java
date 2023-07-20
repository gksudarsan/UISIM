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
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_321_02_006 extends TestBase
{


	
	@Test(priority=1, description = "EM.321.02.006 - Verify Employer is able to Edit Member/Managing Member Details.",groups = {"Regression"})
	public void EM_321_02_006() throws Exception
	{
		 
		 test = report.createTest("EM.321.02.006 - Verify Employer is able to Edit Member/Managing Member Details");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_4.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("menu");	
		 sleep(2000);
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.screenShot("Menu","Pass","Maintain Business Ownership");
		 commonFuntions.clickMenu("Maintain Business Ownership");			 
		// commonFuntions.ScrollMenu("Add Member/Managing Member Details");
		 //commonFuntions.screenShot("Add Member/Managing Member Details","Pass","Add Member/Managing Member Details");
		 //commonFuntions.clickMenu("Add Member/Managing Member Details");	
		 sleep(2000);
		 String feinValue = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,11))),9);	 
		 commonFuntions.clickOnLink("Add Member/Managing Member Details");
		 sleep(2000);
	     populateFields(feinValue);
	     commonFuntions.clickButtonContains("Submit");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Success","Pass","Successfully added Member/Managing Member Details");
	     addCorporatePage.successLink.click();
		 Thread.sleep(2000);
	     verifyFields(feinValue);	
	     
	     String feinValueInitial=feinValue;
	     feinValue = StringUtils.left(feinValue, 2)+"-"+StringUtils.right(feinValue, 7);
	     commonFuntions.selectTable(feinValue,7,1,"Business as Member/Managing Member ");
	     Thread.sleep(2000);
	     commonFuntions.enterTextboxContains("Address Line 1", "Updated address line 1");
	     commonFuntions.enterTextboxContains("Address Line 2", "Updated address line 2");
	     commonFuntions.clickButtonContains("Submit");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Success2","Pass","Successfully amended Member/Managing Member Details");
	     addCorporatePage.successLink.click();
	     Thread.sleep(2000);		
	     addCorporatePage.businessEntityFilter.click();
	     addCorporatePage.businessEntityFilter.sendKeys(feinValueInitial);
	     Thread.sleep(2000);
	     String address = commonFuntions.retrieveValueFromTable(feinValue,4,1,"Business as Member/Managing Member ");	   
	     System.out.println("Expected:"+"ADDED ADDRESS LINE 1 but actual is "+address);
	     Assert.assertEquals(address.contains("UPDATED ADDRESS LINE 1"), true,"Expected:"+"UPDATED ADDRESS LINE 1 but actual is "+address);
	    Assert.assertEquals(address.contains("UPDATED ADDRESS LINE 2"), true,"Expected:"+"UPDATED ADDRESS LINE 2 but actual is "+address);
	     
	   	   
	}
	public void populateFields(String feinValue) throws Exception {
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		Thread.sleep(2000);
		commonFuntions.selectRadio("Business Entity");
		addCorporatePage.businessEntityRadio.click();
	     Random random = new Random();
	     commonFuntions.enterTextboxContains("Entity Name", "AutomationFirstName"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),5));	    
	     commonFuntions.enterTextboxContains("Federal Identification Number (FEIN)", feinValue);
	     commonFuntions.selectDropdown("Title", "Managing Member");
	     commonFuntions.screenShot("Populate","Pass","populate corporate officer/ownerdetais");
	     commonFuntions.enterTextboxContains("Address Line 1", "Added address line 1");
	     commonFuntions.enterTextboxContains("Address Line 2", "Added address line 2");
	     commonFuntions.enterTextboxContains("City", "Added City");	    
	     commonFuntions.enterTextboxContains("Zip", StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),5));
	     commonFuntions.enterTextboxContains("Contact Number", StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,12))),10));
	     commonFuntions.screenShot("Submit","Pass","Submit corporate officer/ownerdetais");	
	}
	public void verifyFields(String feinValue) throws InterruptedException {
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		addCorporatePage.businessEntityFilter.click();
	     addCorporatePage.businessEntityFilter.sendKeys(feinValue);
		feinValue = StringUtils.left(feinValue, 2)+"-"+StringUtils.right(feinValue, 7);
		
		Thread.sleep(3000);
		String  address = commonFuntions.retrieveValueFromTable(feinValue,4,1,"Business as Member/Managing Member ");
		System.out.println(address);
		System.out.println("Expected:"+"ADDED ADDRESS LINE 1 but actual is "+address);
		 Assert.assertEquals(address.contains("ADDED ADDRESS LINE 1"), true,"Expected:"+"ADDED ADDRESS LINE 1 but actual is "+address);
		    Assert.assertEquals(address.contains("ADDED ADDRESS LINE 2"), true,"Expected:"+"ADDED ADDRESS LINE 1 but actual is "+address);
	}
}