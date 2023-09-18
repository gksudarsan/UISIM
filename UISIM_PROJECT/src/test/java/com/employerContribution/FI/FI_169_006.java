package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FIpage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_006 extends TestBase {

	@Test(priority = 1, description = "FI.169.006. ''Verify CSR can submit an FI Issue by selecting a work item 'Coverage  Protest Task'  (Issue Category - Coverage, Issue Subcategory - 'Coverage Protest')", groups = {
			"Regression" })
	public void FI_169_006_CSR() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		// PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);
		FIpage fiPage = new FIpage(driver);
		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"FI.169.005. 'Verify CSR can submit an FI Issue by selecting a work item 'Transfer Protest '  (Issue Category - Protest, Issue Subcategory - 'Transfer Protest' )");

		// LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFunctions.clickMenu("Menu");sleep();
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Employer Issues");
		commonFunctions.clickMenu("Employer Issues");
		commonFunctions.ScrollMenu("Create Work Item");
		commonFunctions.clickMenu("Create Work Item");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-001---
		commonFunctions.screenShot("CreateWorkItem-EnterERN", "Pass",
				"Create Work Item - Enter ERN (FIS-001)screen launched");
		commonFunctions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunctions.screenShot("createWorkItem-EnterERN1", "Pass", "ERN Entered");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-010---
		commonFunctions.screenShot("SelectWorkItem", "Pass", "Select Work Item (FIS-010)screen launched");
		commonFunctions.selectDropdown("Select Unit", " L&D Coverage ");
		sleep(1000);
		commonFunctions.selectDropdown("Select Work Item", " Coverage Protest ");
		sleep(1000);
		commonFunctions.screenShot("SelectWorkItem1", "Pass", "Details selected on (FIS-010)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-003---
		commonFunctions.screenShot("CoverageProtest", "Pass", "Coverage Protest (FIS-003)screen launched");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickButtonContains(" Associate Documents ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---WF-101---
		commonFunctions.screenShot("SearchandAssociateDocuments", "Pass",
				"Search and Associate Documents (WF-101)screen launched");
		commonFunctions.enterTextboxContains("ERN", eanValue);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		BCL.selectcheckbox_SearchAssociateDocuments.click();
		commonFunctions.screenShot("SearchandAssociateDocuments", "Pass", "Record Selected on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		sleep(2000);

		// ---WF-101---
		commonFunctions.clickButtonContains(" Add Additional Employer ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("SearchandAssociateDocuments1", "Pass",
				"Search and Associate Documents (WF-101)screen launched");
		commonFunctions.enterTextboxContains("ERN", "4701235");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		fiPage.selectRadioButton.click();sleep();
		commonFunctions.screenShot("SearchandAssociateDocuments", "Pass", "Record Selected on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		sleep(2000);

		// ---FIS-003---
		commonFunctions.screenShot("CoverageProtest1", "Pass", "Coverage Protest (FIS-003)screen launched");
		commonFunctions.selectDropdown("Work Basket", " Bank Correspondence Employer ");
		sleep(1000);
		commonFunctions.selectDropdown("Source", " Department Request ");
		sleep(1000);
		BCL.Reasons_AuditProtest.sendKeys("For Testing");
//		commonFunctions.enterTextboxContains("Effective Date of Transfer", "4/7/2023");
//		commonFunctions.enterTextboxContains("Predecessor ERN", "0123422");
//		commonFunctions.enterTextboxContains("Predecessor Name", "Tester");
//		commonFunctions.enterTextboxContains("Successor ERN", "0123422");
//		commonFunctions.enterTextboxContains("Successor Name", "Tester");
//		commonFunctions.selectCheckbox("Is this protest a hearing request?");
//		commonFunctions.selectCheckbox("Add HA to Account?");
		commonFunctions.screenShot("CoverageProtest2", "Pass", "Details Entered on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("ClaimantNameClaimentSSNJobTitlerequired", "Pass", "Claimant Name and Claiment SSN or Job Title is required");
        commonFunctions.enterTextboxContains("Claimant Name", "autoTest");
        commonFunctions.enterTextboxContains("Claimant SSN", "843659834");
        commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		// ---FIS-005---
		commonFunctions.screenShot("Verification-CoverageProtest", "Pass",
				"Verification - Coverage Protest (FIS-005)screen launched");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("WorkItemSubmission-Confirmation", "Pass",
				"Work Item Submission - Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		commonFunctions.clickButton(" Go to My Q ");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- WF-001 ---
		commonFunctions.screenShot("Individual Work Queue", "Pass", "Successfully launched to WF-001 page");
		commonFunctions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunctions.clickButton(" Search ");
		commonFunctions.clickOnLink("Coverage Protest Task");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- WF-091 ---
		commonFunctions.screenShot("Work Item Details", "Pass", "Successfully launched to WF-091 page");
		commonFunctions.clickButtonContains("Open Work Item ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---PFP-050---
		commonFunctions.screenShot("Transfer Protest", "Pass", "Successfully launched to PFP-050 page");
		commonFunctions.selectRadioQuestions("Do you want to reroute this task to another work basket/User?", "No ");
		commonFunctions.selectRadioQuestions("Do you want to create a Field Audit Request task?", "No ");
		commonFunctions.selectRadioQuestions("Do you want to place a Hold Action Flag on this account?", "No ");
		BCL.comment_AuditProtestTask.sendKeys("FOR TESTING");
		commonFunctions.screenShot("Transfer Protest", "Pass", "Details Entered on PFP-050 page");
		commonFunctions.clickButton("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Task Confirmation", "Pass", "Task Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		// Done
	}

}
