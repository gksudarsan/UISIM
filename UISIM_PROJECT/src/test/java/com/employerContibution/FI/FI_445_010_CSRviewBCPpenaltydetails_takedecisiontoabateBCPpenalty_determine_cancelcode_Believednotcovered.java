package com.employerContibution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_445_010_CSRviewBCPpenaltydetails_takedecisiontoabateBCPpenalty_determine_cancelcode_Believednotcovered
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is cancelled when cancel code is 'Believed not covered'", groups = "Regression")
	public void FI_445_010() throws Exception {

		test = report.createTest(
				"FI_445_010 Verify CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is cancelled when cancel code is 'Believed not covered'");

		String ernNum = "8114358";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIpage filocators = new FIpage(driver);

		// Map<String, String> databaseEanResult =
		// commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM
		// T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%';",
		// "EAN");
		// String ernNum = databaseEanResult.get("EAN");

		// if ((ernNum == null) || (ernNum.isEmpty())) {
		// System.out.println("ERN Value is null");
		// } else {
		// test.log(Status.PASS, "DB connected successfully and fetched ERN is: " +
		// ernNum + ".");}

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		//filocators.ClickMenu.click();
		sleep(2000);
		commonFuntions.ScrollMenu("Penalty");
		commonFuntions.clickMenu("Penalty");sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Write Message - Enter ERN");
		commonFuntions.clickMenu("Penalty Menu");

		// ---Penalty Menu - Enter ERN-FIP–001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Penalty Menu - Enter ERN", "Pass", "Successful launch Penalty Menu FIP–001");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);sleep();
		commonFuntions.screenShot("Penalty Menu - Enter ERN2", "Pass", "ERN Entered");sleep(15000);
		commonFuntions.clickButton("Continue ");

		// ---Select Penalty-FIP-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Select Penalty", "Pass", "Select Penalty page launched-FIP-002");
		commonFuntions.selectRadioInTable("", 1, 1, "Select Penalty");sleep();
		commonFuntions.screenShot("Select Penalty_2", "Pass", "Penalty page details selected-FIP-002");sleep(15000);
		commonFuntions.clickButton("Continue ");sleep();

		// ---Benefit Claim Penalty Summary - FIP–005---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Benefit Claim Penalty Summary", "Pass", "BCP Summary launched - FIP–005");
		commonFuntions.selectRadioInTable("", 1, 1, "Benefit Claim Penalty Summary");
		commonFuntions.screenShot("BCP_1", "Pass", "Benifit claim Penalty details filled - FIP–005");sleep(15000);
		commonFuntions.clickButton("Continue ");sleep();
		
		// ---Benefit Claim Penalty Details - FIP–006---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCP details", "Pass", "BCP Details launched - FIP–006");
		commonFuntions.selectRadioQuestions("Protest Quarter Year/Debt Level?    ", " Cancel BCP ");
		commonFuntions.selectRadioQuestions("If Cancel a BCP, Select cancel code ", "Believed Not Covered");
		filocators.Textarea.sendKeys("CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is cancelled when cancel code is 'Believed not covered'");
		commonFuntions.screenShot("BCP details", "Pass", "BCP Details selected - FIP–006");sleep(15000);
	
		commonFuntions.clickButton("Continue ");sleep();
		
		
		/// --------------------------------------------------------

		filocators.Quarter_start.click();
		filocators.Value_Quarter_start.click();
		filocators.Year_start.click();
		filocators.Value_Year.click();
		filocators.Quarter_end.click();
		filocators.Value_Quarter_end.click();
		filocators.Year_end.click();
		filocators.Value_Year.click();

		commonFuntions.enterTextbox("Amount Protesting ($)", "10000");
		commonFuntions.selectCheckbox("Were Books and records previously provided?");
		commonFuntions.selectCheckbox("If 50% penalty was assessed are you protesting 50% fraud penalty?");
		commonFuntions.selectCheckbox("Is this protest a hearing request ?");
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("Select Work Item", "Pass", "Selected the required details-FIS-002");
		commonFuntions.clickButton("Continue ");

		// ---Submit issue verification---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Submit issue verification", "Pass", "Submit issue verification screen launched");
		commonFuntions.clickButton("Submit ");

		// ---Issue Submission Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Issue Submission COnfirmation", "Pass", "Issue Submission COnfirmation-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("Redirected to Home Screen", "Pass", "Back to Home Screen");

		// --- CSR review Flow Started ---//
		// ---Login---
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		// commonFuntions.login(COMMON_CONSTANT.CSR_USER_1,
		// COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		commonFuntions.database_UpdateQuery(
				"UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
						+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"
						+ ernNum + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User logged in Successfully");
		commonFuntions.clickButton(" Go to My Q ");
		sleep(10000);

		// ---Individual Work Queue-WF-001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Individual Work Queue launched-WF-001");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		commonFuntions.selectDropdown("Work Item Description", "Audit Protest Task");
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

		filocators.Entercomments.sendKeys("Audit Protest Task-PFP-020");

		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("AUDIT PROTEST TASK-PFP-0201", "Pass", "APT page details filled-PFP-020");
		commonFuntions.clickButton("Submit ");

		// ---Task Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Task Confirmation", "Pass", "Task Confirmation page launched Successfully-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("FI.169.05.003 pass", "Pass", "Test Case got Pass FI.169.05.003");
	}
}