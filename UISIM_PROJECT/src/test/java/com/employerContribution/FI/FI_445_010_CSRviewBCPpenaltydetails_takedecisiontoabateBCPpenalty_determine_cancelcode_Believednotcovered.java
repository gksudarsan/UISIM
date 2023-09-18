

package com.employerContribution.FI;

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

		String ernNum = "4868478";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIpage filocators = new FIpage(driver);

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");sleep();
		commonFuntions.ScrollMenu("Penalty");
		commonFuntions.clickMenu("Penalty");sleep();
		commonFuntions.screenShot("Menu", "Pass", "Penalty Menu - Enter ERN");
		commonFuntions.clickMenu("Penalty Menu");

		// ---Penalty Menu - Enter ERN-FIP–001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Penalty Menu - Enter ERN", "Pass", " Penalty Menu launch FIP–001");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);sleep();
		commonFuntions.screenShot("Penalty Menu - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButton("Continue ");

		// ---Select Penalty-FIP-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Select Penalty", "Pass", "Select Penalty page launched-FIP-002");
		commonFuntions.selectRadioInTable("BCP", 1, 1, "Select Penalty");sleep();
		commonFuntions.screenShot("Select Penalty", "Pass", "Penalty page details selected-FIP-002");
		commonFuntions.clickButton("Continue ");sleep();

		// ---Benefit Claim Penalty Summary - FIP–005---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Benefit Claim Penalty Summary", "Pass", "BCP Summary launched - FIP–005");
		commonFuntions.selectRadioInTable("Due", 1, 1, "Benefit Claim Penalty Summary");
		commonFuntions.screenShot("BCP Summary", "Pass", "BCP details selected - FIP–005");
		commonFuntions.clickButton("Continue ");sleep();

		// ---Benefit Claim Penalty Details - FIP–006---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCP details", "Pass", "BCP Details launched - FIP–006");
		commonFuntions.selectRadioQuestionsContains("Protest Quarter Year/Debt Level?", "Cancel BCP ");
		//commonFuntions.selectDropdown("If Cancel a BCP, Select cancel code ", "Believed Not Covered ");
		filocators.cancelBcpDropdownClick.click();sleep();
		filocators.cancelBcpDropdownSelect_Bnc.click();sleep();

		filocators.Textarea.sendKeys("CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is cancelled when cancel code is 'Believed not covered'");
		commonFuntions.screenShot("BCP details", "Pass", "BCP Details selected - FIP–006");
		commonFuntions.clickButton("Continue ");sleep();

		// ---Benefit Claim Penalty Verification - FIP–007---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCP Verification", "Pass", "BCP Verification launched - FIP–007");
		//commonFuntions.clickButton("Submit ");sleep();

		// ---Benefit Claim Penalty Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCP Confirmation", "Pass", "BCP Confirmation page launched Successfully-SUC-002");
		commonFuntions.clickButton(" Home ");
		sleep();
		commonFuntions.screenShot("FI.445.010 pass", "Pass", "Test Case got Pass FI.445.010");

		//----------Test Case Completed-------------//

	}
}