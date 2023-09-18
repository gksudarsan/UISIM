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

public class SM_005_009_CSRabletowrite_sendbulkSecureMessage_TPR extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that CSR is able to write and send a bulk Secure Message to TPR", groups = "Regression")
	public void SM_005_009() throws Exception {

		test = report.createTest("SM.005.009-Verify that CSR is able to write and send a bulk Secure Message to TPR");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SMPage smlocators = new SMPage(driver);

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Write Message - Enter ERN");
		commonFuntions.clickMenu("Write Bulk Secure Message");

		// ---Write Bulk Secure Message-SM–112---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Write Bulk Secure Message", "Pass", "Successful launch Write Bulk_SM - SM–112");
		// commonFuntions.selectRadioQuestions(" Recipient Type", "Employer");
		smlocators.selectEmployer.click();
		commonFuntions.selectDropdown("Message Language", "English");
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Recipient_Worklist.xls");
		sleep(2000);

		commonFuntions.selectDropdown("Category", "Collection Notices");
		sleep();
		commonFuntions.selectDropdown("Subcategory", "Other");
		commonFuntions.enterTextbox("Subject", "Category 'Collection Notices' with Other as 'Subcategory'");
		smlocators.write_SecureMessage.sendKeys("Message sending with Collection Notices' with Other as 'Subcategory'");
		sleep();
		commonFuntions.selectRadioQuestions("Send Notification Via", "Email ");
		commonFuntions.enterFutureDate("Send Message On", 2);
		commonFuntions.screenShot("Write Bulk Secure Message", "Pass", "BSM details filled - SM–112");

		commonFuntions.clickButton("Submit ");
		commonFuntions.clickButton("Yes ");

		// ---Bulk Secure Message Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Bulk Secure Message Confirmation", "Pass", "BSM Confirmation page launched Successfully-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("SM_005_009", "Pass", "Test Case got Pass SM_005_009");
		
		/*Execute the batch required 'BatchProcessSMBulkRequest'*/

	}
}