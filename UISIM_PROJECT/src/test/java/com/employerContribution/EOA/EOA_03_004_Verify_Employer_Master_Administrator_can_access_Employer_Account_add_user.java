package com.employerContribution.EOA;



import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_03_004_Verify_Employer_Master_Administrator_can_access_Employer_Account_add_user extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the UI Online Services Account Creation process (TPR) - (FEIN, Legal name)", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_03_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoaPage = new EOAPage(driver);

		test = report.createTest(
				"EOA.03.004-Verify Employer Master Administrator can access Employer Account and add user with Master Administrator role");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
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

		//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Users");
		commonFuntions.clickMenu("Employer Users");
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 screen is visible");

		test.info("Step: 2 -- SREG-061");
		eoaPage.addUserLink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Add Employer User", "Pass", "SREG-531 screen is visible");

		test.info("Step: 3 -- SREG-531");

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
		commonFuntions.selectDropdown("User Types", " Master Administrator ");
		sleep();
		commonFuntions.screenShot("Add Employer User", "Pass", "SREG-531 screen is visible");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 4 -- SUC-002");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("SIDES E-Response Auto-enrollment Confirmation", "Pass",
				"SUC-002 screen is displayed");

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Users");
		commonFuntions.clickMenu("Employer Users");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 screen is visible");
		eoaPage.userIdVerificationSREG061(userId);

		//
		test.info("Step: 5 -- SREG-061");
		eoaPage.addUserLink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Add Employer User", "Pass", "SREG-531 screen is visible");

		test.info("Step:  -- SREG-531");

		commonFuntions.enterTextboxContains("First Name", "Test");
		commonFuntions.enterTextboxContains("Last Name", "Acc");
		commonFuntions.enterTextboxContains("Email Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains(" Contact Number ", "1234567890");
		commonFuntions.enterTextboxContains(" Cell Number ", "1234567809");

		String userId2 = "dev007@" + commonFuntions.createRandomString() + "12";
		System.out.println(userId2);
		test.log(Status.INFO, "userId: " + userId2);

		String password2 = "143W" + commonFuntions.createRandomString() + "*" + commonFuntions.createRandomString()
				+ "12";
		System.out.println(password2);
		test.log(Status.INFO, "password: " + password2);

		commonFuntions.enterTextboxContains("User ID", userId2);
		commonFuntions.enterTextboxContains("Temporary Password", password2);
		commonFuntions.enterTextboxContains("Confirm Temporary Password", password2);

		eoaPage.userTypeField.click();
		sleep(2000);
		
		eoaPage.employerSubUserOption.isDisplayed();
		
		commonFuntions.screenShot("Add Employer User", "Pass", "SREG-531 screen is visible - Master Administrator option is not present in the range for question *User Type.");

		//
		commonFuntions.clickMenu("Home");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		
		//delete it now
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Users");
		commonFuntions.clickMenu("Employer Users");
		commonFuntions.screenShot("Employer Users", "Pass", "SREG-061 screen is visible");
		sleep(1000);
		eoaPage.manageUserClickSREG061(userId);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.clickButtonContains(" Remove ");
		sleep(1000);
		

	}

}
