package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_433_002_CSR_ViewFTF_PenaltyClosed
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can view the  Failure to File  (FTF) penalty details when the return is filed and the Employer’s prior filing history, the assessed penalty is adjusted and Status of the Penalty is closed", groups = "Regression")
	public void FI_169_05_003() throws Exception {

		test = report.createTest(
				"FI.433.002 : Verify CSR can view the  Failure to File (FTF) penalty details when the return is filed and the Employer’s prior filing history, the assessed penalty is adjusted and Status of the Penalty is closed");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		FraudInvestigationLocators filocators = new FraudInvestigationLocators(driver);
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID", "EAN");
		String ernNo = databaseEanResult.get("EAN");

		if ((ernNo == null) || (ernNo.isEmpty())) {
			System.out.println("ERN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNo + ".");
		}

 		//---Login---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

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
		commonFunction.screenShot("FI433002", "Pass", "Successfully launched Penalty Menu - Enter ERN(FIP–001) page");
		commonFunction.enterTextboxContains("Employer Registration Number", ernNo); //0463826
		sleep(2000);
		commonFunction.screenShot("FI433002", "Pass", "Entered data in FIP-001 page");
		commonFunction.clickButtonContains("Continue ");
		
		// --- FIP-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("FI433002", "Pass", "Successfully launched Select Penalty(FIP–002) page");
		commonFunction.selectRadioInTable("BCP", 1, 1, "");
		sleep(2000);
		commonFunction.screenShot("FI433002", "Pass", "Entered data in FIP-002 page");
		commonFunction.clickButtonContains("Continue ");
		
		// --- FIP-005 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("FI433002", "Pass", "Successfully launched Benefit Claim Penalty Summary(FIP–005) page");
		commonFunction.selectRadioInTable("001-00-0939", 1, 1, "");
		sleep(2000);
		commonFunction.screenShot("FI433002", "Pass", "Entered data in FIP-005 page");
		commonFunction.clickButtonContains("Continue ");
		
		
		
		commonFunction.waitForLoadingIconToDisappear();
		sleep();
		commonFunction.screenShot("FI433002 pass", "Pass", "Test Case got Pass FI.433.002");
	}
}
