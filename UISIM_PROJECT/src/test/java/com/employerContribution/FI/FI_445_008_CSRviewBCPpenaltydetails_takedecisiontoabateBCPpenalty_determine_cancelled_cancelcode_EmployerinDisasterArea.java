package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_445_008_CSRviewBCPpenaltydetails_takedecisiontoabateBCPpenalty_determine_cancelled_cancelcode_EmployerinDisasterArea
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is cancelled when cancel code is 'Employer in Disaster Area'", groups = "Regression")
	public void FI_445_008() throws Exception {

		test = report.createTest(
				"FI.445.008- Verify CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is cancelled when cancel code is 'Employer in Disaster Area'");

		String ernNum = "4868478";
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		FIpage filocators = new FIpage(driver);

		// ---Login---
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");sleep();
		commonFunctions.ScrollMenu("Penalty");
		commonFunctions.clickMenu("Penalty");sleep();
		commonFunctions.screenShot("Menu", "Pass", "Penalty Menu - Enter ERN");
		commonFunctions.clickMenu("Penalty Menu");

		// ---Penalty Menu - Enter ERN-FIP–001---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Penalty Menu - Enter ERN", "Pass", " Penalty Menu launch FIP–001");
		commonFunctions.enterTextbox("Employer Registration Number", ernNum);sleep();
		commonFunctions.screenShot("Penalty Menu - Enter ERN", "Pass", "ERN Entered");
		commonFunctions.clickButton("Continue ");

		// ---Select Penalty-FIP-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Select Penalty", "Pass", "Select Penalty page launched-FIP-002");
		commonFunctions.selectRadioInTable("BCP", 1, 1, "Select Penalty");sleep();
		commonFunctions.screenShot("Select Penalty", "Pass", "Penalty page details selected-FIP-002");
		commonFunctions.clickButton("Continue ");sleep();

		// ---Benefit Claim Penalty Summary - FIP–005---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Benefit Claim Penalty Summary", "Pass", "BCP Summary launched - FIP–005");
		commonFunctions.selectRadioInTable("Due", 1, 1, "Benefit Claim Penalty Summary");
		commonFunctions.screenShot("BCP Summary", "Pass", "BCP Summary selected - FIP–005");
		commonFunctions.clickButton("Continue ");sleep();

		// ---Benefit Claim Penalty Details - FIP–006---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("BCP details", "Pass", "BCP Details launched - FIP–006");
		commonFunctions.selectRadioQuestionsContains("Protest Quarter Year/Debt Level?", "Cancel BCP ");
		// commonFuntions.selectDropdown("If Cancel a BCP, Select cancel code ", "Believed Not Covered ");
		filocators.cancelBcpDropdownClick.click();sleep();
		filocators.cancelBcpDropdownSelect_Eda.click();sleep();

		filocators.Textarea.sendKeys("Verify CSR can view the BCP penalty details in order to determine if it is cancelled when cancel code is 'Employer in Disaster Area'");
		commonFunctions.screenShot("BCP details", "Pass", "BCP Details selected - FIP–006");
		commonFunctions.clickButton("Continue ");sleep();

		// ---Benefit Claim Penalty Verification - FIP–007---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("BCP Verification", "Pass", "BCP Verification launched - FIP–007");
		commonFunctions.clickButton("Submit ");sleep();

		// ---Benefit Claim Penalty Confirmation-SUC-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("BCP Confirmation", "Pass", "BCP Confirmation page launched Successfully-SUC-002");
		commonFunctions.clickButton(" Home ");
		sleep();
		commonFunctions.screenShot("FI.445.008 pass", "Pass", "Test Case got Pass FI.445.008");

		//----------Test Case Completed-------------//

	}
}