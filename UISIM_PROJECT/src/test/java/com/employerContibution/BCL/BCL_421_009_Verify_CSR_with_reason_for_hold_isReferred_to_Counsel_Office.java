package com.employerContibution.BCL;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_421_009_Verify_CSR_with_reason_for_hold_isReferred_to_Counsel_Office extends TestBase {
	@Test
	public void BCL_421_009() throws Exception {
		test = report.createTest(
				"BCL.421.009 -  Verify CSR can add a collection hold on the account with reason for hold is Referred to Counsel's Office");

		commonStepDefinitions cf = new commonStepDefinitions();
		SUC_002 suc002 = new SUC_002(driver);

		// Query
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// -----Login
		cf.login(COMMON_CONSTANT.Collections_Specialist_1_User.toUpperCase(), COMMON_CONSTANT.Collections_Specialist_1_pwd);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// -----Menu
		cf.clickMenu("Menu");
		sleep(2000);
		cf.screenShot("MenuPage", "Pass", "Launched to Menu");
		cf.ScrollMenu("Contribution Collection");
		cf.clickMenu("Contribution Collection");
		sleep(1000);
		cf.clickMenu("Maintain Collection Hold");
		cf.screenShot("Maintain Collection Hold", "Pass", "Launched to COL-527");
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(3000);
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickOnLinkAnchorTag("Add Collection Hold");
		cf.screenShot("Add Collection Hold", "Pass", "Launched to COL-528");
		cf.enterTextboxContains("Hold Start Date", "10/12/2023");
		cf.selectDropdown("Reason For Hold", " Referred to Counsel Office ");
		cf.clickButtonContains("Continue ");
		cf.screenShot("Add Collection Hold Verification", "Pass", "Launched to COL-529");
		cf.clickButtonContains("Submit ");

		cf.screenShot("Add Collection Hold Confirmation", "Pass", "Launched to SUC-002");
		sleep(3000);
		suc002.pOASucessText.isDisplayed();
		cf.clickButtonContains("Home");
		cf.screenShot("Home", "Pass", "Home Page");
		System.out.println("Worked");
	}

}
