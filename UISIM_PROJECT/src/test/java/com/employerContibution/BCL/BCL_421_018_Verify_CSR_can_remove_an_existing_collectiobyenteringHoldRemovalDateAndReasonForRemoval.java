package com.employerContibution.BCL;

import java.util.Map;
import org.testng.annotations.Test;

import org.testng.annotations.Listeners;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_421_018_Verify_CSR_can_remove_an_existing_collectiobyenteringHoldRemovalDateAndReasonForRemoval
		extends TestBase {
	@Test
	public void BCL_421_018() throws Exception {
		test = report.createTest(
				"BCL.421.018 -  Verify CSR can remove an existing collection hold on the account by entering the Hold Removal Date and Reason for Removal.");

		commonStepDefinitions cf = new commonStepDefinitions();
		EM_005 em005 = new EM_005(driver);
		SUC_002 suc002 = new SUC_002(driver);

		// Query
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// -----Login
		cf.login(COMMON_CONSTANT.CSR_Collection_Specialist_One.toUpperCase(), COMMON_CONSTANT.CSR_Collection_Specialist_One_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();

		// -----Menu
		cf.clickMenu("Menu");
		sleep(2000);
		cf.screenShot("MenuPage", "Pass", "Launched to Menu");
		cf.ScrollMenu("Contribution Collection");
		cf.clickMenu("Contribution Collection");
		sleep(1000);
		cf.clickMenu("Maintain Collection Hold");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Maintain Collection Hold", "Pass", "Launched to COL-527");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(3000);
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Remove Collection Hold", "Pass", "Launched to COL-531");
		cf.clickButtonContains("Continue ");
		cf.errorLabel(" Please provide a reason for removal.");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Reason is required", "Pass", "Launched to COL-531");
		cf.populateListbox("a. Reason for Removal", "Reason for Removing");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Remove Collection Hold - Added Removal Reason", "Pass", "Launched to COL-531");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Remove Collection Hold Verification", "Pass", "Launched to COL-532");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();

		cf.screenShot("Remove Collection Hold Confirmation", "Pass", "Launched to SUC-002");
		suc002.pOASucessText.isDisplayed();
		cf.clickButtonContains("Home");
		cf.screenShot("Home", "Pass", "Home Page");
		System.out.println("Worked");
	}
}
