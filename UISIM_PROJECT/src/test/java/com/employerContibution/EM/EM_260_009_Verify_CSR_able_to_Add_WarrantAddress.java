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

public class EM_260_009_Verify_CSR_able_to_Add_WarrantAddress extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.009.Verify CSR is able to view and Add Warrant  Address information of an employer account", groups = {
			"Regression" })
	public void EM_260_009() throws Exception {
		test = report.createTest(
				"EM.260.009.Verify CSR is able to view and Add Warrant  Address information of an employer account");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				//"SELECT * FROM T_EMPLOYER_ACCOUNT WHERE EMPLOYER_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"SELECT * FROM T_EMPLOYER_ACCOUNT WHERE EMPLOYER_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7",
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
		cf.clickButton("Continue ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		// cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes
		// ");
		cf.selectTableWithoutId("Warrant Address", 6, 1, "Maintain Address Details");
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to EM-007");
		sleep();
		cf.clickButtonContains("Submit ");

		// Verify Required toast messages
		cf.errorLabel(" Required ");

		// Address Line 2 - cannot contain 1 character
		cf.enterTextboxContains("Address Line 1 ", "TestAddress");
		cf.enterTextboxContains("Address Line 2 ", "T");
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.selectDropdown("Address Status", "Active");
		cf.selectDropdown("Source", "NYS-100 (paper)");
		sleep(2000);
		cf.selectDropdown("Source Type", "NYS-100");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.errorLabel(" Address line 2 cannot contain 1 character.");
		cf.screenShot("Address Line 2 - cannot contain 1 character", "Pass", "Launched to SREG-008");

		// Address Line 1 - invalid character
		cf.clearTextboxContains("Address Line 1 ");
		cf.enterTextboxContains("Address Line 1 ", "Test@324$");
		cf.enterTextboxContains("Address Line 2 ", "Address");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.errorLabelContains("only Alphabets, Numbers .-", "Maintain Warrant Address");
		cf.screenShot("Address Line 1 - invalid character", "Pass", "Launched to SREG-008");

		// City - contains an invalid character
		cf.enterTextboxContains("Address Line 1 ", "TestAddress");
		cf.clearTextboxContains("City ");
		cf.enterTextboxContains("City ", "Bany@11#23");
		cf.clickButtonContains("Submit ");
		cf.errorLabelContains("only Alphabets, Numbers .-", "Maintain Warrant Address");
		sleep();
		cf.screenShot("City - contains an invalid character", "Pass", "Launched to SREG-008");
		// cf.clearTextboxContains("City ");
		cf.enterTextboxContains("City ", "Albany");

		// Zip Code is invalid
		cf.clearTextboxContains("Zip Code");
		cf.enterTextboxContains("Zip Code", "1231");
		cf.clickButtonContains("Submit ");
		cf.errorLabelContains("Zip Code must have 5 numbers only.", "Maintain Warrant Address");
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

		// Saving Address
		cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.selectDropdown("State", "New York");
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
//		cf.enterTextboxContains("First Name", "AutoTestFirstName");
//		cf.enterTextboxContains("Last Name", "AutoTestLastName");
		cf.enterTextboxContains("Effective End Date", "10/6/2022");
		cf.clickButtonContains("Submit ");
		cf.screenShot("Address Saved", "Pass", "Launched to SREG-486");

		System.out.println("Worked");
	}
}
