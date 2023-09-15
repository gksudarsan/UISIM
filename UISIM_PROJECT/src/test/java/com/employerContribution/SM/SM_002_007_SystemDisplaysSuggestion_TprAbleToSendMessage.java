package com.employerContribution.SM;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_002_007_SystemDisplaysSuggestion_TprAbleToSendMessage extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify that the system displays a suggestion and the third party representative is be able to write and send a message.", groups = "Regression")
	public void SM_002_007() throws Exception {

		test = report.createTest(
				"SM.002.007 - Verify that the system displays a suggestion and the third party representative is be able to write and send a message.");
//		String ernNum = "9888277";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SMPage smlocators = new SMPage(driver);
		
//		if ((ernNum == null) || (ernNum.isEmpty())) {
//			System.out.println("ERN Value is null");
//		} else {
//			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNum + ".");
//		}

		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_4.toUpperCase(), COMMON_CONSTANT.TPR_USER_4_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// --- Menu ----
		commonFuntions.waitForLoadingIconToDisappear();
		smlocators.menu.click();
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		commonFuntions.ScrollMenu("Write Message");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Secure Messaging -> Write Message");
		commonFuntions.clickMenu("Write Message");
		
		// --- SM-101 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SM002007", "Pass", "Successfully launched to Write Message(SM-101) page");
		commonFuntions.selectDropdownEquals("Category", " Collection Notices ");
		commonFuntions.selectDropdownEquals("Subcategory", " Other ");
		sleep(2000);
		commonFuntions.screenShot("SM002007", "Pass", "Data entered in SM-101 page");
		smlocators.write_SecureMessage.sendKeys("Testing WI from TPR t CSR");
		sleep(2000);
		commonFuntions.screenShot("SM002007", "Pass", "Data entered in SM-101 page");
		
		sleep();
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);
		commonFuntions.screenShot("SM002007", "Pass", "Document entered in SM-101 screen");
		
		commonFuntions.clickButton("Send ");
		
		// ---SUC-002---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SM002007", "Pass", "Successfully launched to Secure Message Confirmation(SUC-002) screen");
		commonFuntions.clickButton("Home ");
		
		//--- CSR review Flow Started ---//
		// ---Login---
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// --- Navigation ----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User logged in Successfully");
		commonFuntions.clickButton(" Go to My Q ");
		sleep(20000);

		// --- WF-001 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SM002007", "Pass", "Successfully launch to Individual Work Queue (WF-001) screen");
		commonFuntions.ScrollMenu("Collections Other");
		commonFuntions.screenShot("SM002007", "Pass", "WI Created for CSR Successful");
		
		
		sleep();
		commonFuntions.screenShot("SM002007", "Pass", "Test Cas SM.002.007 got Pass");
		
		System.out.println("Pass :)");

	}
}