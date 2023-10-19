package com.employerContribution.EOA;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_06_002_Verif_TPR_Administrator_can_access_TPR_account_and_add_edit_user extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision,", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void EOA_06_002() throws Exception {

		test = report.createTest("EOA_06_002_Verif_TPR_Administrator_can_access_TPR_account_and_add_edit_user");

		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// --- Login ---
		cf.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		sleep(2000);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// SREG-612
		cf.clickMenu("menu");
		cf.screenShot("Menu", "Pass", "Launched to Menu");
		sleep();
		cf.clickMenu("Users");
		sleep();
		cf.clickMenu("TPR Users");

		cf.screenShot("Third Party Representative User", "Pass", "Help Screen - SREG-061");
		cf.clickOnLinkAnchorTag(" ADD USER");

		test.log(Status.PASS, "Launched to SREG-531");
		cf.screenShot("Add TPR User", "Pass", "Launched to SREG-531");
		cf.enterRandomString("First Name");
		cf.enterTextboxContains("Email Address", "shreya@gmail.com");
		cf.enterTextboxContains(" Contact Number ", "(672)-438-7654");
		sleep(2000);
		String userIdNum = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		String userId = "user@" + userIdNum;
		System.out.println(userId);
		cf.enterTextboxContains("User ID", userId);
		cf.enterTextboxContains("Temporary Password", "TprUser@123456");
		cf.enterTextboxContains("Confirm Temporary Password", "TprUser@123456");
		cf.selectDropdown("User Types", " TPR Sub-User ");
		cf.selectRadioQuestions("Select Roles", " TPR - All UI Matters");
		cf.clickButtonContains("Submit ");

		test.log(Status.PASS, "Launched to SUC-002");
		cf.Label("The user account(s) has been added successfully.");
		cf.screenShot("Add User Confirmation", "Pass", "Launched to SUC-002");

		cf.clickMenu("menu");
		cf.screenShot("Menu", "Pass", "Launched to Menu");
		sleep();
		cf.clickMenu("Users");
		sleep();
		cf.clickMenu("TPR Users");

		cf.screenShot("Third Party Representative User", "Pass", "Help Screen - SREG-061");
		cf.clickOnManageLinkEOA(3);
	}
}
