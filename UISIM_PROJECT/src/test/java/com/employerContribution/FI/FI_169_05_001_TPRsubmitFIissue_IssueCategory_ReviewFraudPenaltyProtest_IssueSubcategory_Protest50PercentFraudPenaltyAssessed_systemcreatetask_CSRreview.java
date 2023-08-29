package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_05_001_TPRsubmitFIissue_IssueCategory_ReviewFraudPenaltyProtest_IssueSubcategory_Protest50PercentFraudPenaltyAssessed_systemcreatetask_CSRreview
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit an FI Issue when Issue Category is Review Fraud Penalty Protest and Issue Subcategory is 'How do I Protest the fifty percent fraud penalty assessed' and system create task for CSR review ", groups = "Regression")
	public void FI_169_05_001() throws Exception {

		test = report.createTest(
				"FI.169.05.003 : Verify TPR can submit an FI Issue when Issue Category is Review Fraud Penalty Protest and Issue Subcategory is 'How do I Protest the fifty percent fraud penalty assessed' and system create task for CSR review ");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FraudInvestigationLocators fiLocators = new FraudInvestigationLocators(driver);
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%';", "EAN");
		String ernNum = databaseEanResult.get("EAN");

		if ((ernNum == null) || (ernNum.isEmpty())) {
			System.out.println("ERN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNum + ".");
		}

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_3, COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		fiLocators.menu.click();
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		commonFuntions.ScrollMenu("Write Message - Enter ERN");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Secure Messaging -> Write Message - Enter ERN");
		commonFuntions.clickMenu("Write Message - Enter ERN");

		// --- SM-100 ---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successful launch Write Message - Enter ERN(SM-1000 screen");
		commonFuntions.enterTextbox("Employer Registration Number", "5454645");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "ERN entered and clicked on Continue");
		commonFuntions.clickButton("Continue ");

		// --- SM-101 ---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launched Write Message (SM-101) screen");
		commonFuntions.selectDropdown("Category", " Protest ");
		sleep();
		commonFuntions.selectDropdown("Subcategory", " How do I protest the fifty percent fraud penalty assessed? ");
		sleep();
		commonFuntions.screenShot("FI16905001", "Pass", "Data enetered in SM-101 screen");
		
		commonFuntions.clickOnLink("click here");
		commonFuntions.switchTab();
		
		commonFuntions.screenShot("FI16905001", "Pass", "Data enetered in SM-101 screen");
		
		// --- FIS-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launched Submit Issue(FIS-002) screen");
		fiLocators.remarksTextarea.sendKeys("Testing");
		
		
		
		
		

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("Select Work Item", "Pass", "Selected the required details-SM-101");
		commonFuntions.clickButton("Send ");

		// ---Secure Message Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Secure Message Confirmation", "Pass", "The SM has been sent Successfully-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("Redirected to Home Screen", "Pass", "Redirected to Home Screen");
		commonFuntions.clickButton("power_settings_new");
		

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User logged in Successfully");
		commonFuntions.clickButton(" Go to My Q ");
		sleep(10000);

		// ---Individual Work Queue-WF-001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Individual Work Queue launched-WF-001");
		commonFuntions.enterTextbox("Employer Registration Number", "5454645");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Data Searched for ERN-WF-001");
		commonFuntions.clickHyperlink("Audit Protest Task");

		// ---Work Item Details AUDIT PROTEST TASK-WF-091---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("AUDIT PROTEST TASK-WF-091", "Pass", "AUDIT PROTEST TASK page launched-WF-091");
		commonFuntions.clickButton("Open Work Item ");

		// ---Audit Protest Task-PFP-020---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("AUDIT PROTEST TASK-PFP-0201", "Pass", "AUDIT PROTEST TASK page launched-PFP-020");
		commonFuntions.selectRadioQuestions("Do you want to reroute this task to another work basket/User?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to create a Field Audit Request?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		commonFuntions.selectRadioQuestions("Is this Employer Protesting 50% Fraud Penalty?", "Yes ");
		commonFuntions.selectRadioQuestions("Do you want to close this work item with no action taken?", "Yes ");

		//bcllocators.Entercomments.sendKeys("Audit Protest Task-PFP-020");
		commonFuntions.screenShot("AUDIT PROTEST TASK-PFP-0201", "Pass", "APT page details filled-PFP-020");
		commonFuntions.clickButton("Submit ");

		// ---Task Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Task Confirmation", "Pass", "Task Confirmation page launched Successfully-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("FI.169.05.003 pass", "Pass", "Test Case got Pass FI.169.05.001");
	}
}