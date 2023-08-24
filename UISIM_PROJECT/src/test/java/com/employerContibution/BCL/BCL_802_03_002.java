package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.COL_540;
import com.ui.pages.COL_474;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_03_002 extends TestBase {

	@Test
	public void BCL802_03_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_540 col540 = new COL_540(driver);
		COL_474 col474 = new COL_474(driver);

		test = report.createTest(
				"BCL.802.03.002 - Verify CSR can Generate Proof of Claim Report and selects option No to Generate Report");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		sleep(2000);
		// commonFuntions.ScrollMenu("Bankruptcy");
		// commonFuntions.clickMenu("Bankruptcy");
		col540.contributionCollectionBankruptcybtn.click();
		sleep(1000);
		commonFuntions.ScrollMenu("Generate Proof of Claim-Enter ERN");
		commonFuntions.screenShot("Generate Proof of Claim-Enter ERN", "Pass", "Generate Proof of Claim-Enter ERN");
		commonFuntions.clickMenu("Generate Proof of Claim-Enter ERN");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Generate Proof of Claim-Enter ERN", "Pass", "COL-540 screen is displayed");

		test.info("Step: 4 -- ");
		Map<String, String> databaseResults = col474.database_SelectQuery(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID");

		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String caseNumber = databaseResults.get("CaseNumber");
		System.out.println("The EAN Value is:" + feinValue);
		test.log(Status.INFO, "Ean::" + feinValue);
		System.out.println("The EAN Value is:" + eanValue);
		test.log(Status.INFO, "Ean::" + eanValue);
		System.out.println("The EAN Value is:" + caseNumber);
		test.log(Status.INFO, "Ean::" + caseNumber);

		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Generate Proof of Claim-Enter ERN", "Pass", "COL-540 screen is displayed");

		test.info("Step: 5 -- ");
		commonFuntions.selectRadioQuestions("Do you want to generate the report?", "No ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Generate Proof of Claim Verification", "Pass", "COL-542 screen is displayed");

		test.info("Step: 6 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Homepage", "Pass", "Home Page screen is displayed");

	}

}
