package com.employerContibution.EM;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_328_001 extends TestBase{

	@Test(priority=1, description = "EM.328.001 - Verify CSR is able to search Legal name of business and update POA/TPR profile details without additional address",groups = {"Regression"})
	public void EM_328_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EM.328.001 - Verify CSR is able to search Legal name of business and update POA/TPR profile details without additional address");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Maintain Reporting Service Details");
		commonFuntions.clickMenu("Maintain Reporting Service Details");
		Thread.sleep(2000);
		
		//Required error message
		commonFuntions.clickButton(" Search ");Thread.sleep(2000);
		commonFuntions.errorLabel(" Required");
		commonFuntions.screenShot("Third Party Representative", "Pass", "Required Error - Search POA/Third Party Representative");
		Thread.sleep(2000);
		
		//Third Party Representative Identification Number is invalid. 
		commonFuntions.enterTextboxContains("*POA/TPR Legal Name", "test_user");
		commonFuntions.enterTextboxContains("TPR ID", "yu"+commonFuntions.createRandomInteger(100000, 999999));
		commonFuntions.clickButton(" Search ");Thread.sleep(2000);
		commonFuntions.screenShot("Error Message", "Pass", "TPR Required Error First Character - Search POA");
		commonFuntions.errorLabel(" First character should be A/a followed by numeric characters.");
		driver.navigate().refresh();
		commonFuntions.enterTextboxContains("*POA/TPR Legal Name", "ABCD");
		Thread.sleep(2000);
		
		//Select a record to proceed.
		commonFuntions.clickButtonContains("Search");Thread.sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.errorContent("Please select a record to proceed further.");
		commonFuntions.screenShot("Select Record Message", "Pass", "Select record message pop-up appear");
		commonFuntions.clickButtonContains("Search");Thread.sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		
		//“Trade Name cannot contain ATTN, DBA, C/0, or c/o.”
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As", "C/o Test");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Trade Name Error Message", "Pass", "Third Party Details - Trade Name");
		commonFuntions.errorLabel(" Trade Name cannot contain ATTN, DBA, C/0, or c/o");
		
		//Either PTIN OR FEIN is required.
		driver.navigate().refresh();
		commonFuntions.enterTextboxContains("POA/TPR Name", "ABCD");
		commonFuntions.clearTextboxContains("Trade Name or Doing Business As");
		commonFuntions.clearTextboxContains("FEIN");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(5000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Error message", "Pass", "Either PTIN & FEIN is required");
		commonFuntions.errorContent("Either PTIN OR FEIN is required.");
		
		//Attention/Care Of cannot contain ATTN, C/O, %.
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.enterTextboxContains("Attention", "Attn");
		commonFuntions.enterTextboxContains("Care Of", "test");
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Error message", "Pass", "Attention/Careof cannot contain ATTN, DBA, C/0, c/o - TPR");
		commonFuntions.errorContent("Attention/Careof cannot contain ATTN, DBA, C/0, c/o");
		
		//Required validation
		commonFuntions.clearTextboxContains("Address Line 1 ");
		commonFuntions.clearTextboxContains("City ");
		commonFuntions.clearTextboxContains("Zip Code");Thread.sleep(2000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Required error", "Pass", "Required error message - TPR");
		commonFuntions.errorLabel("Required");
		
		//"Address Line 1 contains an invalid character(s)." 
        commonFuntions.enterTextboxContains("Address Line 1 ", "Test%^5544");		
		commonFuntions.enterTextboxContains("City","ALBANY");
		commonFuntions.enterTextboxContains("Zip Code","13429");Thread.sleep(2000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Address Error Message", "Pass", "Address Error Message- TPR");
		commonFuntions.errorLabel("only Alphabets, Numbers .-',!#_:;&/ allowed");
		
		//"City  contains an invalid character(s).
		commonFuntions.enterTextboxContains("Address Line 1", "Test_Auto"+commonFuntions.createRandomInteger(10,99));	
		commonFuntions.enterTextboxContains("City","test@t$%^^");
		commonFuntions.enterTextboxContains("Zip Code","13409");Thread.sleep(2000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("City Error Message", "Pass", "City Error Message- TPR");
		commonFuntions.errorLabel("only Alphabets, Numbers .-',!#_:;&/ allowed");
		
		// ZIP Code must have 5 numbers only.
		commonFuntions.enterTextboxContains("City","ALBANY");
		commonFuntions.enterTextboxContains("Zip Code","13");Thread.sleep(2000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("ZipCode Error Message", "Pass", "ZipCode Error Message- TPR");
		commonFuntions.errorLabel("Zip Code must have 5 numbers only.");
		
		//ZIP Code is invalid.
		commonFuntions.enterTextboxContains("Zip Code","00000");Thread.sleep(2000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Invalid Error Message", "Pass", "Invalid ZipCode Error Message- TPR");
		commonFuntions.errorLabel(" Zip Code is invalid.");
		
		// Phone number must be numeric and of 10 digits
		commonFuntions.enterTextboxContains("Zip Code","12983");
		commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(100000,999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));Thread.sleep(2000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Invalid", "Pass", "10 digit phone number is required - TPR");
		commonFuntions.errorLabel("Phone Number should be 10 digits minimum");
		
		//"Contact Number is invalid."
		commonFuntions.enterTextboxContains("Phone Number","9999999999");
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Invalid", "Pass", "Invalid Phone Number");
		commonFuntions.errorLabel(" Please enter valid number");
		
		//Email Address contains  invalid character(s).
		commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("E-mail Address", "&%&^^&%^&^");
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Invalid", "Pass", "Invalid Email Address - TPR");
		commonFuntions.errorLabel(" Email Address entered is invalid.Correct format of email address is a@b.com.");
		
		//"Fax Number is invalid.
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterTextboxContains(" Fax Number ", "0000000000");
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Invalid FAX", "Pass", "Enter valid FAX Number - TPR");
		commonFuntions.errorLabel(" Please enter valid number");
		
		//Third Party Representative Identification Number is invalid.
		commonFuntions.clearTextboxContains("Attention");
		commonFuntions.clearTextboxContains("Care Of");
		commonFuntions.enterTextboxContains(" Fax Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("TPR ID", "U000000004");
		commonFuntions.clickButton("Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Invalid FAX", "Pass", "Enter valid FAX Number - TPR");
		commonFuntions.errorLabel("First character should be A/a followed by numeric characters.");
		
		//Effective Date of Closure is required.
		commonFuntions.enterTextboxContains("TPR ID", "A000000004");
		commonFuntions.selectRadioQuestions("Are you requesting to terminate your Third Party Representation which will disassociate all of your clients?", "Yes");
		commonFuntions.clickButton("Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Required Error", "Pass", "Required Date - Closure Date");
		commonFuntions.errorLabel("Required");
		
		//Cannot be a past date
		commonFuntions.enterTextboxContains("If yes, please enter Effective Date of Closure (MM/DD/YYYY)", "02/01/2022");
		commonFuntions.clickButton("Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Required error message", "Pass", "Cannot be a past date");
		commonFuntions.errorLabel(" Cannot be a past date");
		
		//required message - comments 
	    //commonFuntions.enterCurrentDate(ele);
		commonFuntions.enterTextboxContains("If yes, please enter Effective Date of Closure (MM/DD/YYYY)", "04/01/2022");
		commonFuntions.clearTextboxContains("Comments");
		commonFuntions.clickButton("Submit");
		commonFuntions.screenShot("Required error message", "Pass", "Required error message for comments");
		commonFuntions.errorLabel("Required");
		Thread.sleep(2000);
		
		//success confirmation
		commonFuntions.enterTextboxContains("Comments", "test OK");
		commonFuntions.clickButton("Submit");
		commonFuntions.screenShot("Success Messages", "Pass", "Third party representative confirmation details");
		commonFuntions.clickButtonContains("Home ");
	
	}
}
