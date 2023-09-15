package com.employerContribution.SM;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_006_012_SystemAllowsCsrToSubmit_SendBulkNotification_ViaTextEmployer extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the system allows CSR to submit a request to send a bulk notification via Text to employers", groups = "Regression")
	public void SM_006_012() throws Exception {

		test = report.createTest(
				"SM.006.012 - Verify that the system allows CSR to submit a request to send a bulk notification via Text to employers");
//		String ernNum = "9888277";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SMPage smlocators = new SMPage(driver);
		
//		if ((ernNum == null) || (ernNum.isEmpty())) {
//			System.out.println("ERN Value is null");
//		} else {
//			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNum + ".");
//		}

		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// --- Menu ----
		commonFuntions.waitForLoadingIconToDisappear();
		smlocators.menu.click();
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		commonFuntions.ScrollMenu("Send Bulk Notification");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Secure Messaging -> Send Bulk Notification");
		commonFuntions.clickMenu("Send Bulk Notification");
		
		// --- SM-124 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SM006012", "Pass", "Successfully launched to Send Bulk Notification(SM-124) page");
		commonFuntions.selectRadioQuestions("Recipient Type", "Employer");
		commonFuntions.selectRadioQuestions("Send Notification Via", "Text");
		
		sleep();
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Recipient_Worklist.xls");
		sleep(2000);
		commonFuntions.screenShot("SM006012", "Pass", "Document entered in SM-124 screen");
		
		smlocators.write_SecureMessage.sendKeys("Hi. \nGood day! \nPlease check the notification.");
		commonFuntions.enterFutureDate("Send Notification On", 10);
		sleep(2000);
		commonFuntions.screenShot("SM006012", "Pass", "Data entered in SM-124 screen");
		
		commonFuntions.clickButton("Submit ");
		
		// ---SUC-002---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SM006012", "Pass", "Successfully launched Bulk Notification Confirmation(SUC-002) screen");
		commonFuntions.clickButton("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SM006012", "Pass", "Successfully launched to Home(WEL-10000) page");
		
		// --- Need to execute batch -- BATCH PROCESS BULK NOTIFICATION REQUEST
		sleep();
		commonFuntions.screenShot("FI_497_002", "Pass", "Test Case got Pass SM.006.012 till batch execution");

	}
}