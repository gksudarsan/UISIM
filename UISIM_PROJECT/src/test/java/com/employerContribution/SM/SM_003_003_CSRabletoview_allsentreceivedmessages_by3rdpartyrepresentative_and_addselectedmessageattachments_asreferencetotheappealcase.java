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

public class SM_003_003_CSRabletoview_allsentreceivedmessages_by3rdpartyrepresentative_and_addselectedmessageattachments_asreferencetotheappealcase extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the CSR is able to view all the sent and received messages by a 3rd party representative and add the selected message and any attachments as a reference to the appeal case", groups = "Regression")
	public void SM_003_003() throws Exception {

		test = report.createTest("SM.003.003-Verify that the CSR is able to view all the sent and received messages by a 3rd party representative and add the selected message and any attachments as a reference to the appeal case");

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
		commonFuntions.clickMenu("View Message");
		commonFuntions.screenShot("Menu", "Pass", "Click on View Message");
		smlocators.clickViewMessage.click();

		// ---View Message-SM–105---//

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("View Message", "Pass", "Successful launch View Message - SM–105");
		
		commonFuntions.enterPastDate("From Date", 150);
		commonFuntions.enterCurrentDate("To Date");
		commonFuntions.selectDropdown("Category", "50% Fraud Penalty");
		sleep(2000);
		commonFuntions.selectDropdown("Subcategory", "Request Explanation of 50 % Fraud Penalty");
		commonFuntions.enterTextbox("Subject", "Explanation of 50 % Fraud Penalty");
		commonFuntions.screenShot("View Message1", "Pass", "View Message detail filled- SM–105");
		commonFuntions.clickButton(" Search Sender ");
		
		// ---Search Sender-SM–123---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search Sender", "Pass", "Successful launch Search Sender - SM–123");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Search Sender1", "Pass", "Error Message - SM–123");
		
		
		
		commonFuntions.screenShot("View Message2", "Pass", "View Message details in table- SM–105");

	}
}