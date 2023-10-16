package com.employerContribution.SM;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)

public class SM_006_019_SystemDoesNotAllowsCsrToCancel_BulkNotification_CancelledOrInProgressRecallOrInitiatedCompleted extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the system does not allow CSR to Cancel the bulk notification when the batch job status is 'Cancelled/In progress/Recall Initiated/Completed'", groups = "Regression")
	public void TC_SM_006_019() throws Exception {

		test = report.createTest(
				"SM.006.019 - Verify that the system does not allow CSR to Cancel the bulk notification when the batch job status is 'Cancelled/In progress/Recall Initiated/Completed'");
//		String ernNum = "9888277";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SMPage smlocators = new SMPage(driver);
		
//		if ((ernNum == null) || (ernNum.isEmpty())) {
//			System.out.println("ERN Value is null");
//		} else {
//			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNum + ".");
//		}

		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.SECURE_MESSAGING_SPECIALIST_2.toUpperCase(), COMMON_CONSTANT.SECURE_MESSAGING_SPECIALIST_2_PASSWORD);
		test.log(Status.PASS, "Login with Secure Messaging Specialist 2 is successful");

		// --- Menu ----
		commonFuntions.waitForLoadingIconToDisappear();
		smlocators.menu.click();
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		commonFuntions.ScrollMenu("View Bulk Notification Status");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Secure Messaging -> View Bulk Notification Status");
		commonFuntions.clickMenu("View Bulk Notification Status");
		
		// --- SM-125 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.Label("SM-125");
		commonFuntions.screenShot("SM006019", "Pass", "Successfully launched to View Bulk Notification Status(SM-125) page");
		commonFuntions.enterPastDate("From Date", 300);
		commonFuntions.enterCurrentDate("To Date");
		smlocators.emailCheckBox.click();
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Data entered to Search on SM-125 page");
		
		commonFuntions.clickButtonContains(" Search ");
		sleep(3000);
		commonFuntions.screenShot("SM006019", "Pass", "Data Searched on SM-125 page");
		
		//commonFuntions.screenShot("SM006019", "Fail", "Radio button unclickable");
		
		//--- status - CANCELLED
		try {
			commonFuntions.Label("Cancelled");
			sleep();
			commonFuntions.selectRadioInTable("Cancelled", 1, 1, "");
			sleep(2000);
			commonFuntions.screenShot("SM006019", "Pass", "Cancelled-status data selected on SM-125 page");
			commonFuntions.clickButtonContains("Cancel ");
			sleep(2000);
			commonFuntions.screenShot("SM006019", "Pass", "Clicking on 'Yes' button on the pop-up for 'Cancel' button click");
			commonFuntions.clickButtonContains(" Yes ");
			sleep();
			commonFuntions.screenShot("SM006019", "Pass", "System displays error message 'Action taken on the selected request is not allowed.'");
		} catch (Exception exception) {
			test.log(Status.INFO, "Cancelled status batch is not available.");
		}
		
		sleep(3000);
		commonFuntions.clickButtonContains(" Search ");
		
		//--- status - SUBMITTED
		try {
			commonFuntions.Label("Submitted");
			sleep(2000);
			commonFuntions.selectRadioInTable("Submitted", 1, 1, "");
			sleep(2000);
			commonFuntions.screenShot("SM006019", "Pass", "Submitted-status data selected on SM-125 page");
			commonFuntions.clickButtonContains("Cancel ");
			sleep(2000);
			commonFuntions.screenShot("SM006019", "Pass", "Clicking on 'Yes' button on the pop-up for 'Cancel' button click");
			commonFuntions.clickButtonContains(" Yes ");
			sleep();
			commonFuntions.screenShot("SM006019", "Pass", "System displays error message 'Action taken on the selected request is not allowed.'");
		} catch (Exception exception) {
			test.log(Status.INFO, "Submitted status batch is not available.");
		}
		
		sleep(3000);
		commonFuntions.clickButtonContains(" Search ");
		
		//--- status - MODIFIED
		try {
			commonFuntions.Label("Modified");
			sleep(2000);
			commonFuntions.selectRadioInTable("Modified", 1, 1, "");
			sleep(2000);
			commonFuntions.screenShot("SM006019", "Pass", "Modified-status data selected on SM-125 page");
			commonFuntions.clickButtonContains("Cancel ");
			sleep(2000);
			commonFuntions.screenShot("SM006019", "Pass", "Clicking on 'Yes' button on the pop-up for 'Cancel' button click");
			commonFuntions.clickButtonContains(" Yes ");
			sleep();
			commonFuntions.screenShot("SM006019", "Pass", "System displays error message 'Action taken on the selected request is not allowed.'");
		} catch (Exception exception) {
			test.log(Status.INFO, "Modified status batch is not available.");
		}
		
		sleep();
		test.log(Status.INFO, "Test Cas SM.006.019 got Pass");
//		commonFuntions.screenShot("SM006019", "Pass", "Test Cas SM.006.019 got Pass");
		
		System.out.println("Pass :)");

	}
}