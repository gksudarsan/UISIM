
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

public class BCL_483_001_Verify_CSR_can_search_for_warrants_in_active_status_based_on_ERN_and_Satisfy_warrant_details
		extends TestBase {
	@Test
	public void BCL_483_001() throws Exception {

		test = report.createTest(
				"483_001 - Verify CSR can search for warrants in active status based on ERN and Satisfy warrant details");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		BclPage BclPage = PageFactory.initElements(driver, BclPage.class);
		// BillingCollectionLiensLocators bclLocators = new
		// BillingCollectionLiensLocators(driver);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		// -----Login
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		// -----Menu
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.screenShot("Menu", "Pass", "Menu page");
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		sleep(1000);
		commonFuntions.clickMenu("Warrant");
		sleep(1000);
		commonFuntions.screenShot("Menu", "Pass", "Menu selected");
		commonFuntions.clickMenu("Satisfy Warrant");
		sleep(1000);
		// ----COL 501
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Successfully launched to COL 501 page");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered nothing on ERN:  COL 501 page");
		sleep(2000);
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "0400027");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered  ERN with no record on  COL 501 page");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "1111111");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered invalid ERN on  COL 501 page");
		sleep(2000);
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered  ERN with no record on  COL 501 page");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "0464364");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered valid ERN on  COL 501 page");

		// --- COL-490 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Warrants", "Pass",
				"Successfully launched to List of Warrants(COL-490) screen");
		BclPage.dataTableIdRadio.click();
		sleep(2000);
		commonFuntions.screenShot("List of Warrants", "Pass", "Successfully selected data on COL-490 screen");
		commonFuntions.clickButtonContains("Continue ");

		// --- COL 501---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Satisfy Warrant Details", "Pass",
				"Successfully launched to Satisfy Warrant Details COL 501 screen");
		try {
			BclPage.yesRadioInCircle.click();
		} catch (Exception exception) {
			BclPage.yesRadioOutCircle.click();
		}
		BclPage.reasonExplanation.sendKeys("Yes want to statisy ");
		commonFuntions.screenShot("Satisfy Warrant Details", "Pass", "Entered details COL 501 screen");
		commonFuntions.clickButtonContains("Continue ");
		// --- COL 502 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Satisfy Warrant Verification", "Pass",
				"Successfully launched to Warrant Satisfy Verification COL 502 screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Warrant Satisfy Confirmation", "Pass",
				"Successfully launched to Warrant Satisfy Confirmation(SUC -002) screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		sleep(4000);
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCL483001", "Pass", "Successfully passed TC BCL.483.001");

		System.out.println("Pass :)");

		///

	}

}
