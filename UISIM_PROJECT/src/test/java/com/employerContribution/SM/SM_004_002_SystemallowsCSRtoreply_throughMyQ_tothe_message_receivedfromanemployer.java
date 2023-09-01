package com.employerContribution.SM;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.pages.smPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_004_002_SystemallowsCSRtoreply_throughMyQ_tothe_message_receivedfromanemployer extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the system allows the CSR to reply (through My Q) to the message received from an employer", groups = "Regression")
	public void SM_004_002() throws Exception {

		test = report.createTest(
				"SM.004.002-Verify that the system allows the CSR to reply (through My Q) to the message received from an employer");
		String ernNum = "9888277";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		smPage smlocators = new smPage(driver);

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User logged in Successfully");
		commonFuntions.clickButton("Go to My Q");
		sleep(10000);
		
		// ---Individual Work Queue-WF-001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Individual Work Queue launched-WF-001");
		commonFuntions.enterTextbox("Work Item Description Free Text", "current ui rates");
	
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Data Searched for 'current ui rates'-WF-001");
		commonFuntions.clickHyperlink("Current UI Rates");

		// ---Work Item Details 'CURRENT UI RATES' -WF-091---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CURRENT UI RATES-WF-091", "Pass", "WI details CURRENTUIRATES page launched-WF-091");
		commonFuntions.clickButton("Open Work Item ");

		// ---Reply Message-SM-103---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Reply Message", "Pass","Reply Message page launched-SM-103");
		smlocators.write_SecureMessage.sendKeys("message recieved, please go ahead as discussed");

		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("Reply Message", "Pass", "Reply Message page details filled-SM-103");
		commonFuntions.clickButton("Send ");

		// ---Task Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Task Confirmation", "Pass", "Task Confirmation page launched Successfully-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("FI_497_002", "Pass", "Test Case got Pass FI_497_002");

	}
}