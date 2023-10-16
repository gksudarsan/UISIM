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

public class SM_006_007_001_CSRabletoCancel_BulkMessage_when_batchjobstatus_Modified extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the CSR is able to Cancel the Bulk Message when the batch job status is Modified", groups = "Regression")
	public void SM_006_007_001() throws Exception {

		test = report.createTest("SM.006.007.001 Verify that the CSR is able to Cancel the Bulk Message when the batch job status is Modified");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		// Pre - SecureMessagingSpecialistSecond - write bulk notification
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.SecureMessagingSpecialist2_User, COMMON_CONSTANT.SecureMessagingSpecialist2_User_pwd);
		test.log(Status.PASS, "Login with Secure Messaging Specialist 2 is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Secure Messaging Menu option");
		commonFuntions.clickMenu("View Bulk Secure Message Summary");

		// ---View Bulk Secure Message Summary-SM–114---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "Successful launch view Bulk_SM Summary - SM–114");
		commonFuntions.enterPastDate("From Date", 300);
		commonFuntions.enterCurrentDate("To Date");
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "Date range selected-SM–114");
		commonFuntions.clickButton(" Search ");
		
		commonFuntions.selectRadioInTable("Modified", 1, 1, "");
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "record selected BSMS-SM–114");
		commonFuntions.clickButton("Cancel ");
		commonFuntions.screenShot("Click on Cancel", "Pass", "Clicked on Cancel, now click on Yes");
		commonFuntions.clickButton(" Yes ");
		
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "The message was successfully cancelled.");

	}
}