
package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.CaPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_04_002 extends TestBase {

	@Test
	public void BCL_855_04_002() throws Exception {

		test = report
				.createTest("BCL.855.04.002 - Verify CSR can search for the list of mailed NPR letters for an ERN");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		BclPage BclPage = PageFactory.initElements(driver, BclPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		// -----Login
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(1000);
		commonFuntions.clickMenu("Confidential Source");
		sleep(1000);
		commonFuntions.clickMenu("Update NPR Referral to Treasury – Enter ERN/SSN/FEIN");
		// ----COL-586
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Update NPR Referral to Treasury – Enter ERN/SSN/FEIN", "Pass",
				"Successfully launched to COL-586 page");
		sleep(2000);
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE REGISTRATION_STATUS = 'C'  and EAN = '0464364'", "EAN");

		String eanNumber = databaseResults.get("EAN");// 0464364

		commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
		commonFuntions.clickButtonContains("Search");
		commonFuntions.waitForLoadingIconToDisappear();

		BclPage.dataTableId_select_0_checkbox.click();
		commonFuntions.screenShot("Update NPR Referral to Treasury", "Pass",
				"Selected NPR Referral to Treasury  COL-586 page");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		// ------COL-587
		commonFuntions.screenShot("Update NPR Referral to Treasury", "Pass", " Referral to Treasury  COL-587 page");
		BclPage.updateReferralCheckBox.click();
		sleep(2000);
		commonFuntions.screenShot("Update NPR Referral to Treasury", "Pass",
				"Clicked Referral to Treasury  COL-587 page");
		commonFuntions.clickButtonContains("Update");
		sleep(2000);
		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("update NPR Referral to Treasury confirmation", "Pass", " Landed on SUC 002");
		commonFuntions.Label("Changes made to NPR have been saved/updated successfully.");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		sleep(4000);
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("855_04_002", "Pass", "Successfully passed TC 855_04_002");
		// ---Developed Palak

	}

}
