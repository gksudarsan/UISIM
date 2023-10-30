package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class Correspondence extends TestBase {

	@Test(priority = 1, description = "", groups = { COMMON_CONSTANT.REGRESSION })
	public void Correspondence() throws Exception {

		test = report.createTest(
				"correspondence");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		employerManagement EM = new employerManagement();

		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_5, COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFuntions.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// --- Menu ---
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Ad Hoc Correspondence");
		commonFuntions.clickMenu("Ad Hoc Correspondence");
		commonFuntions.ScrollMenu("Generate EC Correspondence");
		commonFuntions.screenShot("Menu", "Pass", "Menu Generate EC Correspondence");
		commonFuntions.clickMenu("Generate EC Correspondence");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---ACOR-001---
		commonFuntions.screenShot("Select Correspondence", "Pass", "Select Correspondence (ACOR-001)Screen launched");
		commonFuntions.selectDropdown("Correspondence Category", " UIT EAA PROOF OF CREDITS ");
		commonFuntions.selectDropdown("Correspondence Name",
				" EAA023EXTR Employer Account Abstract (Proof Of Credit) ");
		commonFuntions.screenShot("Select Correspondence", "Pass",
				"Selected Details on Select Correspondence (ACOR-001)Screen");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---ACOR-002---
		commonFuntions.screenShot("Enter Recipient Details", "Pass",
				"Enter Recipient Details (ACOR-002)Screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number", "6544554");
		commonFuntions.screenShot("Enter Recipient Details", "Pass",
				"ERN Entered on Enter Recipient Details (ACOR-002)Screen");
		commonFuntions.clickButton(" Get Address ");
		commonFuntions.waitForLoadingIconToDisappear();

	//	EM.Attention.sendKeys("fdsgfds");
	//  EM.Address_Line_1.sendKeys("4236");
	//	EM.Address_Line_2.sendKeys("albany");
		commonFuntions.screenShot("Enter Recipient Details", "Pass",
				"Details Entered on Enter Recipient Details (ACOR-002)Screen");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---ACOR-003---
		commonFuntions.screenShot("Add Follow-Up", "Pass", "Add Follow-Up (ACOR-003)Screen launched");
		commonFuntions.selectRadioQuestions("Enable Follow-Up", "No ");
		commonFuntions.screenShot("Add Follow-Up", "Pass", "Details selected on Add Follow-Up (ACOR-003)Screen");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---ACOR-004---
		commonFuntions.screenShot("Add Routing Options and Internal Copies", "Pass",
				"Add Routing Options and Internal Copies (ACOR-004)Screen launched");
		commonFuntions.selectRadioQuestions("Route", "No ");
		commonFuntions.selectRadioQuestions("Add Cross Reference?", "No ");
		commonFuntions.screenShot("Add Routing Options and Internal Copies", "Pass",
				"Details selected on Add Routing Options and Internal Copies (ACOR-004)Screen");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---ACOR-006---
		commonFuntions.screenShot("Review Correspondence Information", "Pass",
				"Review Correspondence Information (ACOR-006)Screen launched");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFuntions.screenShot("Live Editor Instructions", "Pass",
				"Live Editor Instructions (SUC-002)Screen launched");
	}
}
