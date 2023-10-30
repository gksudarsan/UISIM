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
public class FI_169_005 extends TestBase {

	@Test(priority = 1, description = "FI.169.005. 'Verify CSR can submit an FI Issue by selecting a work item 'Transfer Protest '  (Issue Category - Protest, Issue Subcategory - 'Transfer Protest' )", groups = {
			"Regression" })
	public void FI_169_005() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		FIpage FI = new FIpage(driver);

		 Map<String, String> databaseEanResult =
				 commonFunctions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
				 String eanValue = databaseEanResult.get("EAN");
				 System.out.println(eanValue);


		test = report.createTest(
				"FI.169.005. 'Verify CSR can submit an FI Issue by selecting a work item 'Transfer Protest '  (Issue Category - Protest, Issue Subcategory - 'Transfer Protest' )");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Employer Issues");
		commonFunctions.clickMenu("Employer Issues");
		commonFunctions.ScrollMenu("Create Work Item");
		commonFunctions.clickMenu("Create Work Item");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-001---
		commonFunctions.screenShot("Create Work Item - Enter ERN", "Pass",
				"Create Work Item - Enter ERN (FIS-001)screen launched");
		commonFunctions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunctions.screenShot("Generate Collection Notice - Enter ERN", "Pass", "ERN Entered");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-010---
		commonFunctions.screenShot("Select Work Item", "Pass", "Select Work Item (FIS-010)screen launched");
		commonFunctions.selectDropdown("Select Unit", " L&D Generalist ");
		sleep(1000);
		commonFunctions.selectDropdown("Select Work Item", " Transfer Protest ");
		sleep(1000);
		commonFunctions.screenShot("Select Work Item", "Pass", "Details selected on (FIS-010)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-003---
		commonFunctions.screenShot("Transfer Protest", "Pass", "Transfer Protest (FIS-003)screen launched");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample.docx");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickButtonContains(" Associate Documents ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---WF-101---
		commonFunctions.screenShot("Search and Associate Documents", "Pass",
				"Search and Associate Documents (WF-101)screen launched");
		commonFunctions.enterTextboxContains("ERN", "4701235");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		FI.selectcheckbox_SearchAssociateDocuments.click();
		commonFunctions.screenShot("Search and Associate Documents", "Pass", "Record Selected on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		sleep(2000);

		// ---FIS-003---
		commonFunctions.screenShot("Transfer Protest", "Pass", "Transfer Protest (FIS-003)screen launched");
		commonFunctions.selectDropdown("Work Basket", " Default Work Basket ");
		sleep(1000);
		commonFunctions.selectDropdown("Source", " Department Request ");
		sleep(1000);
		FI.Reasons_AuditProtest.sendKeys("For Testing");
		commonFunctions.enterTextboxContains("Effective Date of Transfer", "4/7/2023");
		commonFunctions.enterTextboxContains("Predecessor ERN", "0123422");
		commonFunctions.enterTextboxContains("Predecessor Name", "Tester");
		commonFunctions.enterTextboxContains("Successor ERN", "0123422");
		commonFunctions.enterTextboxContains("Successor Name", "Tester");
		commonFunctions.selectCheckbox("Is this protest a hearing request?");
		commonFunctions.selectCheckbox("Add HA to Account?");
		commonFunctions.screenShot("Transfer Protest", "Pass", "Details Entered on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		// ---FIS-005---
		commonFunctions.screenShot("Verification - Audit Protest", "Pass",
				"Verification - Audit Protest (FIS-005)screen launched");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Work Item Submission - Confirmation", "Pass",
				"Work Item Submission - Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		commonFunctions.clickButton(" Go to My Q ");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- WF-001 ---
		commonFunctions.screenShot("Individual Work Queue", "Pass", "Successfully launched to WF-001 page");
		commonFunctions.enterTextboxContains("Employer Registration Number", "6263084");
		commonFunctions.clickButton(" Search ");
		commonFunctions.clickOnLink("Transfer Protest Task");
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
		FI.comment_AuditProtestTask.sendKeys("FOR TESTING");
		commonFunctions.screenShot("Transfer Protest", "Pass", "Details Entered on PFP-050 page");
		commonFunctions.clickButton("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Task Confirmation", "Pass",
				"Task Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		// Done
	}

}
