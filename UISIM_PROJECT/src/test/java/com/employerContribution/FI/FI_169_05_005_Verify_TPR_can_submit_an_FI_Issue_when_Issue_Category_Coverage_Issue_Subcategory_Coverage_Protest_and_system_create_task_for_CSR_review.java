package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_05_005_Verify_TPR_can_submit_an_FI_Issue_when_Issue_Category_Coverage_Issue_Subcategory_Coverage_Protest_and_system_create_task_for_CSR_review
		extends TestBase {

	@Test
	public void FI_169_05_005() throws Exception {
		test = report.createTest(
				"FI_169_05_005_Verify_TPR_can_submit_an_FI_Issue_when_Issue_Category_Coverage_Issue_Subcategory_Coverage_Protest_and_system_create_task_for_CSR_review");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// Query
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_SUBMIT_ISSUE_BENEFIT_CLAIM_PENALTY_ASSESSMENT ttsibcpa JOIN T_EMPLOYER_ACCOUNT tea ON TTSIBCPA.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"CLAIMANT_NAME");
		String claimaintName = databaseEanResult.get("CLAIMANT_NAME");
		System.out.println("The CLAIMANT NAME is " + claimaintName);

		// -----Login
		//commonFunction.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		sleep(2000);
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// -----Menu
		commonFunction.clickMenu("Menu");
		sleep(2000);
		commonFunction.screenShot("MenuPage", "Pass", "Launched to Menu");
		commonFunction.ScrollMenu("Secure Messaging");
		commonFunction.clickMenu("Secure Messaging");
		sleep(1000);
		commonFunction.clickMenu("Write Message - Enter ERN");
		commonFunction.enterTextboxContains("Employer Registration Number", "0847711"); // eanValue
		commonFunction.screenShot("Write Message - Enter ERN", "Pass", "Launched to SM-100");
		commonFunction.clickButtonContains("Continue ");
		// SM-101
		commonFunction.screenShot("Write Message", "Pass", "Launched to SM-101");
		commonFunction.selectDropdown("Category", " Protest ");
		commonFunction.selectDropdown("Subcategory",
				" How do I protest a determination that an individual is an employee? ");
		commonFunction.clickOnLinkAnchorTag("click here");
		commonFunction.switchTab();

		// FIS-002
		commonFunction.screenShot("Submit Issue", "Pass", "Launched to FIS-002");
		commonFunction.enterTextboxContains("Claimant Name", claimaintName);
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.populateListbox("Remarks/Reasons for submitting Issue", "okay");
		commonFunction.clickButtonContains("Continue ");

		commonFunction.screenShot("Submit Issue Verification", "Pass", "Launched to FIS-008");
		commonFunction.clickButtonContains("Submit ");

		commonFunction.screenShot("Issue Submission Confirmation", "Pass", "Launched to SUC-002");
		commonFunction.Label("An Issue has been successfully created and will be assigned to the Internal Staff.");
		commonFunction.clickButtonContains("Home ");

		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='0847711' ORDER BY UPDATED_TS desc)");
		Thread.sleep(4000);

		//commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		peoPage.queue.click();
		commonFunction.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFunction.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFunction.enterTextboxContains("Employer Registration Number", "0847711");
		commonFunction.screenShot("EAN Search", "Pass", "EAN Search");
		commonFunction.clickButtonContains("Search");

		commonFunction.clickOnLinkfirstItem("Coverage Determination Protest Task");
		commonFunction.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		commonFunction.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);

		// PFP-120
		commonFunction.screenShot("Coverage Determination Protest Task", "Pass", "PFP-120 screen is visible");
		commonFunction.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "Yes ");
		commonFunction.selectRadioQuestions("If Yes, Select Work Basket", " Default Work Basket ");
		commonFunction.enterTextboxContains("If Yes, Enter User ID", "124411");
		commonFunction.populateListbox("Comments", "okay");
		commonFunction.clickButtonContains("Submit ");
		commonFunction.screenShot("Task Confirmation", "Pass", "Launched to SUC-002");
		commonFunction.Label("This work item has been successfully reassigned/rerouted to another Work Basket/User.");
		commonFunction.clickButtonContains("Home ");

	}

}