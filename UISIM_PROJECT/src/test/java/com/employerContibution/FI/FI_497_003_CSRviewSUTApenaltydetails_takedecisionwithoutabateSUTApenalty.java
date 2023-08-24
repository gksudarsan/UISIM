package com.employerContibution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_497_003_CSRviewSUTApenaltydetails_takedecisionwithoutabateSUTApenalty extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can view the SUTA penalty details and take a decision without abate the SUTA penalty", groups = "Regression")
	public void FI_497_003_() throws Exception {

		test = report.createTest(
				"FI_497_003_Verify CSR can view the SUTA penalty details and take a decision without abate the SUTA penalty");
		String ernNum = "9888277";
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
		commonFuntions.selectRadioInTable("", 1, 1, "Select Penalty");
		sleep();
		commonFuntions.screenShot("Select Penalty_2", "Pass", "Penalty page details selected-FIP-002");
		commonFuntions.clickButton("Continue ");
		sleep();

		// ---SUTA Penalty Summary - FIP–010---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SUTA Penalty Summary", "Pass", "SUTA Penalty Summary launched - FIP–010");
		commonFuntions.selectRadioInTable("", 1, 1, "SUTA Penalty Summary");
		filocators.Textarea.sendKeys("SUTA Penalty record has been selected");
		commonFuntions.screenShot("SUTA Penalty Summary 2", "Pass", "SUTA Penalty details filled - FIP–010");
		commonFuntions.clickButton("Continue ");
		sleep();

		// ---SUTA Penalty Verification - FIP–012---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SUTA Penalty Verification", "Pass", "SUTA Penalty Verification launched - FIP–012");
		commonFuntions.clickButton("Submit ");
		sleep();

		// ---SUTA Penalty Confirmation - SUC–002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("SUTA Penalty Confirmation", "Pass", "SUTA Penalty Confirmation launched - SUC–002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("FI_497_003", "Pass", "Test Case got Pass FI_497_003");

	}
}