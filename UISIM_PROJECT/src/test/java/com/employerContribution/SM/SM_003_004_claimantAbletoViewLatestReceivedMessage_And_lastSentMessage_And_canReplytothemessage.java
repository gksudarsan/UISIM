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

public class SM_003_004_claimantAbletoViewLatestReceivedMessage_And_lastSentMessage_And_canReplytothemessage extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the Claimant is able to view the latest received message and the last sent message and can Reply to the message", groups = "Regression")
	public void SM_003_004() throws Exception {

		test = report.createTest(
				"SM.003.004-Verify that the Claimant is able to view the latest received message and the last sent message and can Reply to the message");

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
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("View Message2", "Pass", "View Message details in table- SM–105");
		
		/*Script done till the passed Steps, there is no evidence doc in MC*/	

	}
}