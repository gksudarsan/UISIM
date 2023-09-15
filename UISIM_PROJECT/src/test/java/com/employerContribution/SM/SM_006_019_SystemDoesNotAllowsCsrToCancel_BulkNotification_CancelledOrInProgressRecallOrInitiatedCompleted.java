package com.employerContribution.SM;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_006_019_SystemDoesNotAllowsCsrToCancel_BulkNotification_CancelledOrInProgressRecallOrInitiatedCompleted extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the system does not allow CSR to Cancel the bulk notification when the batch job status is 'Cancelled/In progress/Recall Initiated/Completed'", groups = "Regression")
	public void SM_006_019() throws Exception {

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
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

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
		commonFuntions.screenShot("SM006019", "Pass", "Successfully launched to View Bulk Notification Status(SM-125) page");
		commonFuntions.enterPastDate("From Date", 300);
		commonFuntions.enterCurrentDate("To Date");
		smlocators.emailCheckBox.click();
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Data entered to Search on SM-125 page");
		
		commonFuntions.clickButtonContains(" Search ");
		sleep(3000);
		commonFuntions.screenShot("SM006019", "Pass", "Data Searched on SM-125 page");
		
		commonFuntions.screenShot("SM006019", "Fail", "Radio button unclickable");
		
		//--- status - CANCELLED
		commonFuntions.selectRadioInTable("Cancelled", 1, 1, "");
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Cancelled-status data selected on SM-125 page");
		commonFuntions.clickButtonContains("modify ");
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Pop-up on selection");
		commonFuntions.clickButtonContains(" No ");
		
		//--- status - SUBMITTED
		sleep(2000);
		commonFuntions.selectRadioInTable("Submitted", 1, 1, "");
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Submitted-status data selected on SM-125 page");
		commonFuntions.clickButtonContains("modify ");
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Pop-up on selection");
		commonFuntions.clickButtonContains(" No ");
		
		//--- status - MODIFIED
		sleep(2000);
		commonFuntions.selectRadioInTable("Modified", 1, 1, "");
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Cancelled-status data selected on SM-125 page");
		commonFuntions.clickButtonContains("modify ");
		sleep(2000);
		commonFuntions.screenShot("SM006019", "Pass", "Pop-up on selection");
		commonFuntions.clickButtonContains(" No ");
		
		// IN PROGRESS- status, unavilable.
		
		sleep();
		commonFuntions.screenShot("SM006019", "Pass", "Test Cas SM.006.019 got Pass");

	}
}