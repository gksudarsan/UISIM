package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_497_002_CSRviewSUTApenaltydetails_takedecisiontoupbateSUTApenalty_CSRDenytask_ReviewPenaltyAbatementRequest
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can view the SUTA penalty details and take a decision to abate the SUTA penalty. (When CSR Deny task '“Review Penalty Abatement Request”)", groups = "Regression")
	public void FI_497_002() throws Exception {

		test = report.createTest(
				"FI.497.002. Verify CSR can view the SUTA penalty details and take a decision to abate the SUTA penalty. (When CSR Deny task '“Review Penalty Abatement Request”)");
		String ernNum = "5664905";
		//5664905, 5641047, 5649848, 5150304, dev 9888277
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIpage filocators = new FIpage(driver);

		/*
		 * Map<String, String> databaseEanResult =
		 * commonFuntions.database_SelectQuerySingleColumn("select\r\n" +
		 * "        count(otherpayme0_.OTHER_PAYMENT_DISTRIBUTION_ID) as COUNT,\r\n" +
		 * "        otherpayme0_.PAYMENT_CATEGORY as PAYMENT_CATEGORY ,\r\n" +
		 * "        E.EAN\r\n" + "    from\r\n" +
		 * "        T_TX_OTHER_PAYMENT_DISTRIBUTION otherpayme0_,\r\n" +
		 * "        T_TX_OTHER_DUE_TRANSACTION otherduetr3_ ,\r\n" +
		 * "        T_EMPLOYER E \r\n" + "    where\r\n" +
		 * "        otherpayme0_.OTHER_DUE_TRANSACTION_ID=otherduetr3_.OTHER_DUE_TRANSACTION_ID \r\n"
		 * +
		 * "        and otherduetr3_.EMPLOYER_ID= E.EMPLOYER_ID AND PAYMENT_CATEGORY='SUTA'\r\n"
		 * + "    group by\r\n" + "        otherpayme0_.PAYMENT_CATEGORY,E.EAN","EAN");
		 * String eanNum = databaseEanResult.get("EAN");
		 */
		  
		  if ((ernNum == null) || (ernNum.isEmpty())) {
		  System.out.println("ERN Value is null"); } else { test.log(Status.PASS,
		  "DB connected successfully and fetched ERN is: " + ernNum + ".");}
		 

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		// filocators.ClickMenu.click();
		sleep(2000);
		commonFuntions.ScrollMenu("Penalty");
		commonFuntions.clickMenu("Penalty");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Write Message - Enter ERN");
		commonFuntions.clickMenu("Penalty Menu");

		// ---Penalty Menu - Enter ERN-FIP–001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Penalty Menu - Enter ERN", "Pass", "Successful launch Penalty Menu FIP–001");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		sleep();
		commonFuntions.screenShot("Penalty Menu - Enter ERN2", "Pass", "ERN Entered");
		commonFuntions.clickButton("Continue ");

		// ---Select Penalty-FIP-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Select Penalty", "Pass", "Select Penalty page launched-FIP-002");
		commonFuntions.selectRadioInTable("SUTA", 1, 1, "Select Penalty");
		sleep();
		commonFuntions.screenShot("Select Penalty_2", "Pass", "Penalty page details selected-FIP-002");
		commonFuntions.clickButton("Continue ");
		sleep();

		// ---SUTA Penalty Summary - FIP–010---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SUTA Penalty Summary", "Pass", "SUTA Penalty Summary launched - FIP–010");
		commonFuntions.selectRadioInTable("Due", 1, 1, "SUTA Penalty Summary");
		filocators.Textarea.sendKeys("SUTA Penalty record has been selected");
		commonFuntions.screenShot("SUTA Penalty Summary 2", "Pass", "SUTA Penalty details filled - FIP–010");
		commonFuntions.clickButton("Continue ");
		sleep();

		// ---SUTA Penalty Verification - FIP–012---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SUTA Penalty Verification", "Pass", "SUTA Penalty Verification launched - FIP–012");
		//commonFuntions.clickButton("Submit ");
		commonFuntions.clickButton(" Home "); sleep();
		sleep();

		/*
		 * // ---SUTA Penalty Confirmation - SUC–002---//
		 * commonFuntions.waitForLoadingIconToDisappear();
		 * commonFuntions.screenShot("SUTA Penalty Confirmation", "Pass",
		 * "SUTA Penalty Confirmation launched - SUC–002");
		 * commonFuntions.clickButton(" Home "); sleep();
		 * commonFuntions.screenShot("Redirected to Home Screen", "Pass",
		 * "Back to Home Screen");
		 */

		// --- CSR review Flow Started ---//

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User Review Process Started");
		commonFuntions.clickButton(" Go to My Q ");
		sleep(5000);

		
		  commonFuntions.database_UpdateQuery(
		  "UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" +
		  COMMON_CONSTANT.CSR_USER_1 +
		  "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"
		  + ernNum + "' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);
		 

		// ---Individual Work Queue-WF-001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Individual Work Queue launched-WF-001");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		commonFuntions.enterTextbox("Work Item Description Free Text", "Review Penalty Abatement Request Task");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Data Searched for ERN-WF-001");
		commonFuntions.clickHyperlink("Review Penalty Abatement Request Task");

		// ---Work Item Details REVIEW PENALTY ABATEMENT REQUEST TASK-WF-091---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Work Item Details-WF-091", "Pass", "Work Item Details RPART page launched-WF-091");
		commonFuntions.clickButton("Open Work Item ");

		// ---Review Penalty Abatement Request-PFP-007---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Review Penalty Abatement Request", "Pass",
				"Review Penalty Abatement Request page launched-PFP-007");
		commonFuntions.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		commonFuntions.selectRadioQuestions("Select Action", "Deny");
		filocators.enter_resolutionDetails.sendKeys("Review Penalty Abatement Request-PFP-007");

		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("Review Penalty Abatement Request", "Pass", "RPAR page details filled-PFP-007");
		commonFuntions.clickButton("Submit ");

		// ---Task Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Task Confirmation", "Pass", "Task Confirmation page launched Successfully-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("FI_497_002", "Pass", "Test Case got Pass FI_497_002");
	}
}