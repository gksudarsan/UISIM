package com.employerContibution.BCL;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_04_001_Verify_CSR_can_generate_the_bankruptcy_report_petition_date extends TestBase {
	@Test
	public void BCL_802_04_001() throws Exception {
		test = report.createTest(
				"BCL.802.04.001 - Verify CSR can generate the bankruptcy follow-up reports based on the petition date");

		commonStepDefinitions cf = new commonStepDefinitions();
		SUC_002 suc002 = new SUC_002(driver);

		// Query
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn("", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// -----Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// -----Menu
		cf.clickMenu("menu");
		sleep(2000);
		cf.screenShot("MenuPage", "Pass", "Launched to Menu");
		cf.ScrollMenu("Contribution Collection");
		cf.clickMenu("Contribution Collection");
		sleep(1000);
		cf.clickMenu("Bankruptcy");
		cf.clickMenu("Bankruptcy Balances without a Claim Filed");
		cf.screenShot("Bankruptcy Balances without a Claim Filed", "Pass", "Launched to INQ-561");
		cf.waitForLoadingIconToDisappear();

		// Past-date
		cf.enterTextboxContains("Petition Date", "12/30/2023");
		sleep(2000);
		cf.clickButtonContains("Next ");
		cf.errorLabelContains("Petition Date", " Must be a past date");

		// Valid Date
		cf.enterTextboxContains("Petition Date", "6/30/2023");
		sleep(2000);
		cf.clickButtonContains("Next ");

		cf.screenShot("Bankruptcy Balances without a Claim Filed Confirmation", "Pass", "Launched to SUC-002");
		cf.clickOnLinkAnchorTag("Generate Report ");
		suc002.pOASucessText.isDisplayed();
		cf.clickButtonContains("Home");
		cf.screenShot("Home", "Pass", "Home Page");
		System.out.println("Worked");
	}
}
