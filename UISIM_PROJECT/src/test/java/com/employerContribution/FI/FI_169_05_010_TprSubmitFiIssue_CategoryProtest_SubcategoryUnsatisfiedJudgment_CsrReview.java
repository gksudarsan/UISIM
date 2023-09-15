package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.FIpage;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_05_010_TprSubmitFiIssue_CategoryProtest_SubcategoryUnsatisfiedJudgment_CsrReview extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit an FI Issue when Issue Category - 'Protest', Issue Subcategory - 'Unsatisfied Judgment' and system create task for CSR review ", groups = "Regression")
	public void FI_169_05_010() throws Exception {

		test = report.createTest(
				"FI.169.05.010 : Verify TPR can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Unsatisfied Judgment' and system create task for CSR review ");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FraudInvestigationLocators fiLocators = new FraudInvestigationLocators(driver);
		FIpage filocators	= new FIpage(driver);
		
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_4, COMMON_CONSTANT.TPR_USER_4_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		fiLocators.menu.click();
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		commonFuntions.ScrollMenu("Write Message");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Secure Messaging -> Write Message - Enter ERN");
		commonFuntions.clickMenu("Write Message");

		// --- SM-101 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905010", "Pass", "Successfully launched Write Message (SM-101) screen");
		commonFuntions.selectDropdown("Category", " Protest ");
		sleep();
		commonFuntions.selectDropdown("Subcategory", " How do I protest (an) Unsatisfied Judgment(s)? ");
		sleep();
		commonFuntions.screenShot("FI16905010", "Pass", "Data enetered in SM-101 screen");
		
		commonFuntions.clickOnLinkAnchorTag("click here");
		sleep(3000);
		commonFuntions.switchTab();
		
		commonFuntions.screenShot("FI16905010", "Pass", "Data enetered in SM-101 screen");
		
		// --- FIS-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905010", "Pass", "Successfully launched Submit Issue(FIS-002) screen");
		
		commonFuntions.enterTextboxContains("Warrant/Judgment balance due ($))", "10000");
		fiLocators.reasonBasisUnsatisfiedJudgProtestId.sendKeys("Testing");
		sleep(2000);
		commonFuntions.screenShot("FI16905010", "Pass", "Data entered in FIS-002 screen");
		
		sleep();
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);
		commonFuntions.screenShot("FI16905010", "Pass", "Document entered in FIS-002 screen");
		
		commonFuntions.clickButton("Continue ");

		// ---Submit issue verification---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905010", "Pass", "Submit issue verification screen launched");
		commonFuntions.clickButton("Submit ");

		// ---SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905010", "Pass", "Successfully launched Issue Submission COnfirmation(SUC-002) screen");
		commonFuntions.clickButton("Home ");
				
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905010", "Pass", "Successfully passed TC FI.169.05.010");
	}

}
