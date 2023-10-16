package com.employerContribution.EOA;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EOA_07_001_Verify_CSR_can_access_Employer_Users_screen_and_add_edit_user extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the UI Online Services Account Creation process (TPR) - (FEIN, Legal name)", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_07_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoaPage = new EOAPage(driver);

		test = report.createTest("EOA.07.001-Verify CSR can access Employer Users screen and add, edit user(s)");
		sleep(3000);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		test.info("Step: 1 -- ");
		try {
			commonFuntions.clickButtonContains(" I agree with the Terms and Conditions ");
			test.log(Status.PASS, "Accepted 'Terms and Conditions for PEO'");
		} catch (Exception exception) {
			test.log(Status.PASS, "Accepted 'Terms and Conditions for Professional Employer Organizations(PEO)'");
		}

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		// commonFuntions.ScrollMenu("Employer Users");
		// commonFuntions.clickMenu("Employer Users");
		eoaPage.employerUserOption.click();
		commonFuntions.screenShot("User Search", "Pass", "SREG-533 screen is visible");

		test.info("Step: 2 -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.errorLabel("Required");
		commonFuntions.screenShot("User Search", "Pass", "SREG-533 screen required error displayed");

		test.info("Step: 3 & 4 & 5 -- ");
		sleep(1000);
		commonFuntions.selectRadioQuestions("Account Type: ", "Employer or Professional Employer Organization");
		sleep(2000);
		commonFuntions.enterTextboxContains("Employer Registration Number", "0000000");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		eoaPage.ernErrorlabel.isDisplayed();
		sleep(1000);
		commonFuntions.screenShot("User Search", "Pass", "SREG-533 screen ERN error displayed");

		Map<String, String> EAN_Value = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;",
				"EAN");
		String ean = EAN_Value.get("EAN");
		System.out.println(ean);
		test.log(Status.INFO, "EAN used : " + ean);

		test.info("Step: 6 -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("Employer Registration Number", ean);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 Screen is displayed");

		test.info("Step: 7 -- ");
		eoaPage.addUserLink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Add Employer User", "Pass", "SREG-531 screen is visible");

		test.info("Step: 8 -- ");
		commonFuntions.enterTextboxContains("First Name", "Test");
		commonFuntions.enterTextboxContains("Last Name", "Acc");
		commonFuntions.enterTextboxContains("Email Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains(" Contact Number ", "1234567890");
		commonFuntions.enterTextboxContains(" Cell Number ", "1234567809");

		String userId = "dev007@" + commonFuntions.createRandomString() + "12";
		System.out.println(userId);
		test.log(Status.INFO, "userId: " + userId);

		String password = "143W" + commonFuntions.createRandomString() + "*" + commonFuntions.createRandomString()
				+ "12";
		System.out.println(password);
		test.log(Status.INFO, "password: " + password);

		commonFuntions.enterTextboxContains("User ID", userId);
		commonFuntions.enterTextboxContains("Temporary Password", password);
		commonFuntions.enterTextboxContains("Confirm Temporary Password", password);
		commonFuntions.selectDropdown("User Types", " Employer Sub-User ");
		sleep();
		eoaPage.userAdminCheckbox.click();
		sleep();
		commonFuntions.screenShot("Add Employer User", "Pass", "SREG-531 screen is visible");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Add User Confirmation", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 9 -- SUC-002");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		//commonFuntions.ScrollMenu("Employer Users");
		//commonFuntions.clickMenu("Employer Users");
		eoaPage.employerUserOption2.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 screen is visible");
		eoaPage.userIdVerificationSREG061(userId);

		test.info("Step: 10 -- ");
		sleep(1000);
		eoaPage.manageUserClickSREG061(userId);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Update Employer User", "Pass", "SREG-062 screen is visible");

		test.info("Step: 11 -- ");
		commonFuntions.forceClearTextWithElement("Middle Initial");
		commonFuntions.forceClearTextWithElement("Email Address");
		sleep(1000);
		commonFuntions.enterTextboxContains("Middle Initial", "d");
		commonFuntions.enterTextboxContains("Email Address", "test12@gmail.com");

		
		String password2 = "143W" + commonFuntions.createRandomString() + "*" + commonFuntions.createRandomString()
				+ "16";
		System.out.println(password2);
		test.log(Status.INFO, "password: " + password2);
		
		commonFuntions.enterTextboxContains("Temporary Password", password);
		commonFuntions.enterTextboxContains("Confirm Temporary Password", password);
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		eoaPage.updatedSuccesMsg.isDisplayed();
		commonFuntions.screenShot("Updated User Confirmation", "Pass", "SUC-002 screen is displayed");

	}

}
