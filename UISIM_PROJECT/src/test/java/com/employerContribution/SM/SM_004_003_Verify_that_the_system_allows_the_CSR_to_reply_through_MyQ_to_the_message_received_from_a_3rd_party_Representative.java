package com.employerContribution.SM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.pages.PEOPage;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_004_003_Verify_that_the_system_allows_the_CSR_to_reply_through_MyQ_to_the_message_received_from_a_3rd_party_Representative extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "SM_004_003_Verify_that_the_system_allows_the_CSR_to_reply_through_MyQ_to_the_message_received_from_a_3rd_party_Representative", groups = "Regression")
	public void SM_004_003() throws Exception {

		test = report.createTest(
				"SM_004_003_Verify_that_the_system_allows_the_CSR_to_reply_through_MyQ_to_the_message_received_from_a_3rd_party_Representative");
		
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SMPage smlocators = new SMPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		
		String ernNum = "9888277";

		/*-------------- Work Item 1  --------------*/
		sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();

		/*--------------WF-001   --------------*/
		sleep(3000);
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Clicked on Work Item - WF-001 ");
		sleep(3000);
		commonFuntions.clearTextboxContains("Employer Registration Number");
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "Explanation Of Charges Requested");
		sleep(3000);
		commonFuntions.screenShot("Work Item Description Free Text", "Pass", "Search for Task");
		sleep(3000);
		commonFuntions.clickButtonContains(" Search ");
		sleep(3000);
		commonFuntions.ScrollMenu("Explanation Of Charges Requested");
		sleep(3000);
		commonFuntions.screenShot("WIClick", "Pass", "Clicked on Work Item - Explanation Of Charges Requested");
		sleep(3000);
		commonFuntions.clickOnLink("Explanation Of Charges Requested");

		// --- WF-091 ---
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
		sleep(3000);
		commonFuntions.clickButtonContains("Open Work Item ");

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
		 //-----Home
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.screenShot("SM_004_003", "Pass", "Test Case got Pass SM_004_003");
		// ---Executed & completed by Palak
	}
}