package com.employerContribution.SM;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_002_001_SystemdisplaysSuggestionAndClaimant_abletoWriteandSendMessage extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the system displays a suggestion and the claimant is be able to write and send a message. (Suggestion Type D)", groups = "Regression")
	public void SM_002_001() throws Exception {

		test = report.createTest(
				"SM.002.001-Verify that the system displays a suggestion and the claimant is be able to write and send a message. (Suggestion Type D)");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SMPage smlocators = new SMPage(driver);

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CLAIMANT, COMMON_CONSTANT.CLAIMANT_PASSWORD);
		test.log(Status.PASS, "Login with Claimant is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Write Message");
		commonFuntions.clickMenu("Write Message");

		// ---Write Message-SM–101---//

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Write Message", "Pass", "Successful launch Write Message - SM–101");

		commonFuntions.selectDropdown("Message Language", "English");
		commonFuntions.selectDropdown("Category", "Claims Questions");
		commonFuntions.selectDropdownUsingSearch("Subcategory",
				"Request Backdating of claim/Request to claim benefits for a prior week");
		smlocators.write_SecureMessage.sendKeys("claimant is be able to write and send a message");

		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("Write Message", "Pass", "Details filled WM - SM–101");
		commonFuntions.clickButton("Send ");

		// ---Secure Message Confirmation-SUC–002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Secure Message Confirmation", "Pass",
				"Successful launch Secure Message Confirmation - SUC–002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("SM_002_001", "Pass", "Test Case 1st step done SM_002_001");

		// ---LogoutAndLogin---//
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Write Message");
		commonFuntions.clickMenu("Write Message");

		// ---Write Message-SM–101---//

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Write Message", "Pass", "Successful launch Write Message - SM–101");
		commonFuntions.clickButton(" Search Recipient ");

		// ---Search Recipient-SM–102---//

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search Recipient", "Pass", "Successful launch Search Recipient - SM–102");
		commonFuntions.enterTextbox("ERN", "05-33872");
		commonFuntions.clickButton(" Search ");

		commonFuntions.selectRadioInTable("", 1, 1, "Search Recipient");
		commonFuntions.screenShot("Search Recipient", "Pass", "Details got on Search Recipient - SM–102");
		commonFuntions.clickButton("Continue ");

		// ---Back to -Write Message-SM–101---//

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Write Message", "Pass", "Successful launch WM again - SM–101");sleep();

		commonFuntions.selectDropdown("Category", "Fraud");sleep();
		commonFuntions.selectDropdown("Subcategory", "What Is Claimant Fraud?");
		smlocators.write_SecureMessage.sendKeys("CSR is be able to write and send a message");

		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("Write Message", "Pass", "Details filled in WM - SM–101");
		commonFuntions.clickButton("Send ");

		// ---Secure Message Confirmation-SUC–002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Secure Message Confirmation", "Pass",
				"Successful launched SM Confirmation - SUC–002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("SM_002_001", "Pass", "Test Case got pass SM_002_001");

	}
}