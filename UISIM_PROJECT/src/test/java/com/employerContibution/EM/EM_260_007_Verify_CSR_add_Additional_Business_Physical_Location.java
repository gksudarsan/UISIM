package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_007_Verify_CSR_add_Additional_Business_Physical_Location extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.007.Verify CSR is able to add Additional Business Physical Location for an employer existing account.", groups = {
			"Regression" })
	public void EM_260_007() throws Exception {
		test = report.createTest(
				"EM.260.007.Verify CSR is able to add Additional Business Physical Location for an employer existing account.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				//"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '93%' AND EMPLOYER_TYPE = 'BUSI' IS NULL ORDER BY UPDATED_TS",
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI' IS NULL",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		// Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Address");
		sleep();

		// SREG-070
		cf.screenShot("Maintain Address – Enter ERN", "Pass", "Launched to SREG-070");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep();
		cf.clickButton("Continue ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		// cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes
		// ");
		cf.clickOnLinkAnchorTag("Add Additional Business Physical Location(s)");

		// SREG-008
		cf.screenShot("Additional Business Physical Location(s)", "Pass", "Launched to SREG-008");
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		cf.clickButtonContains("Submit ");

		// Verify Required toast messages
		cf.errorLabel(" Address 1 Required");
		cf.errorLabel(" City Required");
		cf.errorLabel(" ZIP Code Required");

		// Address Line 1 - invalid character
		cf.enterTextboxContains("Address Line 1", "Test$^^&");
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.errorLabelContains("only Alphabets, Numbers .-", " Additional Business Physical Location(s)  ");
		cf.screenShot("Address Line 1 - invalid character", "Pass", "Launched to SREG-008");

		// Address Line 2 - cannot contain 1 character
		cf.clearTextboxContains("Address Line 1 ");
		cf.enterTextboxContains("Address Line 1 ", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2 ", "T");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.errorLabel(" Address line 2 cannot contain 1 character.");
		cf.screenShot("Address Line 2 - cannot contain 1 character", "Pass", "Launched to SREG-008");
		cf.clearTextboxContains("Address Line 2 ");

		// City - contains an invalid character
		cf.clearTextboxContains("City ");
		cf.enterTextboxContains("City ", "Albany@123");
		cf.clickButtonContains("Submit ");
		cf.errorLabelContains("only Alphabets, Numbers .-", " Additional Business Physical Location(s)  ");
		sleep();
		cf.screenShot("City - contains an invalid character", "Pass", "Launched to SREG-008");
		cf.clearTextboxContains("City ");
		cf.enterTextboxContains("City ", "Albany");

		// Zip Code is invalid
		cf.clearTextboxContains("Zip Code");
		cf.enterTextboxContains("Zip Code", "1231");
		cf.clickButtonContains("Submit ");
		cf.errorLabelContains("Zip Code must have 5 numbers only.", " Additional Business Physical Location(s)  ");
		cf.screenShot("Zip Code is invalid", "Pass", "Launched to SREG-008");
		sleep();

		// Zip Code is invalid
		cf.clearTextboxContains("Zip Code");
		cf.enterTextboxContains("Zip Code", "11111");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.errorLabel(" Zip Code is invalid.");
		cf.clearTextboxContains("Zip Code");
		cf.screenShot("Zip Code is invalid", "Pass", "Launched to SREG-008");
		System.out.println("Worked");

		// Other steps are deprecated from the screen SREG-008

	}
}
