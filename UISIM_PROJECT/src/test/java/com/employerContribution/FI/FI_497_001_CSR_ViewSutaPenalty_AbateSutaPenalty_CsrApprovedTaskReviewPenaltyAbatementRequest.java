package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_497_001_CSR_ViewSutaPenalty_AbateSutaPenalty_CsrApprovedTaskReviewPenaltyAbatementRequest extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can view the SUTA penalty details and take a decision to abate the SUTA penalty. (When CSR approved task 'Review Penalty Abatement Request')", groups = "Regression")
	public void FI_497_001() throws Exception {

		test = report.createTest(
				"FI.497.001 : Verify CSR can view the SUTA penalty details and take a decision to abate the SUTA penalty. (When CSR approved task 'Review Penalty Abatement Request')");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		FraudInvestigationLocators filocators = new FraudInvestigationLocators(driver);
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT\r\n"
				+ "        count (otherpayme0_.OTHER_PAYMENT_DISTRIBUTION_ID) as COUNT,\r\n"
				+ "        otherpayme0_.PAYMENT_CATEGORY as PAYMENT_CATEGORY ,\r\n"
				+ "        E.EAN\r\n"
				+ "    from\r\n"
				+ "        T_TX_OTHER_PAYMENT_DISTRIBUTION otherpayme0_,\r\n"
				+ "        T_TX_OTHER_DUE_TRANSACTION otherduetr3_,\r\n"
				+ "        T_EMPLOYER E \r\n"
				+ "    where\r\n"
				+ "        otherpayme0_.OTHER_DUE_TRANSACTION_ID = otherduetr3_.OTHER_DUE_TRANSACTION_ID \r\n"
				+ "        and otherduetr3_.EMPLOYER_ID = E.EMPLOYER_ID\r\n"
				+ "        AND PAYMENT_CATEGORY = 'SUTA'\r\n"
				+ "    group by\r\n"
				+ "        otherpayme0_.PAYMENT_CATEGORY,E.EAN;", "EAN");
		String ernNo = databaseEanResult.get("EAN");

		if ((ernNo == null) || (ernNo.isEmpty())) {
			System.out.println("ERN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched EAN is: " + ernNo + ".");
		}

 		//---Login---
		commonFunction.login(COMMON_CONSTANT.LND_FRAUD_SPECIALIST, COMMON_CONSTANT.LND_FRAUD_SPECIALIST_PASSWORD);
		test.log(Status.PASS, "Login with L&D Fraud Specialist role is successful.");

		// ---Menu----
		commonFunction.waitForLoadingIconToDisappear();
		filocators.menu.click();
		commonFunction.ScrollMenu("Penalty");
		commonFunction.clickMenu("Penalty");
		commonFunction.ScrollMenu("Penalty Menu");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Penalty -> Penalty Menu");
		commonFunction.clickMenu("Penalty Menu");
		
		// --- FIP–001 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("FI497001", "Pass", "Successfully launched Penalty Menu - Enter ERN(FIP–001) page");
		
		commonFunction.enterTextboxContains("Employer Registration Number", ""); //0463826
		sleep(2000);
		commonFunction.clickButtonContains("Continue ");
		commonFunction.screenShot("FI497001", "Pass", "Error on blank ERN continue");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "5641047"); //0463826
		sleep(2000);
		commonFunction.screenShot("FI497001", "Pass", "Entered data in FIP-001 page");
		commonFunction.clickButtonContains("Continue ");
		
		// --- FIP-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("FI497001", "Pass", "Successfully launched Select Penalty(FIP–002) page");
		commonFunction.selectRadioInTable("SUTA", 1, 1, "");
		sleep(2000);
		commonFunction.screenShot("FI497001", "Pass", "Successfully launched Select Penalty(FIP–002) page");
		commonFunction.clickButtonContains("Continue ");
		
		// --- FIP–010 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("FI497001", "Pass", "Successfully launched SUTA Penalty Summary(FIP–010) page");
		commonFunction.selectRadioInTable("Abated", 1, 1, "");
		filocators.bankArtryId.sendKeys("Test");
		sleep(2000);
		commonFunction.screenShot("FI497001", "Pass", "Entered required data to FIP–010 page");
		
		// --- FIP–010 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("FI497001", "Pass", "Successfully launched SUTA Penalty Summary(FIP–010) page");
		 //penalty abated already
		
		
		
	}
}
