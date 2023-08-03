
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

public class BCL_485_001_Verify_CSR_can_search_for_warrants_in_active_status_based_on_ERN_and_destroyed_warrant_details
		extends TestBase {
	@Test
	public void BCL_485_001() throws Exception {

		test = report.createTest(
				"485_001 - Verify CSR can search for warrants in active status based on ERN and destroyed warrant details");
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
		commonFuntions.clickMenu("Destroy Warrant");
		sleep(1000);
		// ----COL-497
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Destroy Warrant", "Pass", "Successfully launched to COL-497 page");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Destroy Warrant", "Pass", "Entered nothing on ERN:  COL-497 page");
		sleep(2000);
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "0400027");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Destroy Warrant", "Pass", "Entered  ERN with no record on  COL-497 page");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "1111111");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Destroy Warrant", "Pass", "Entered invalid ERN on  COL-497 page");
		sleep(2000);
		commonFuntions.screenShot("Destroy Warrant", "Pass", "Entered  ERN with no record on  COL-497 page");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "0464364");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Destroy Warrant", "Pass", "Entered valid ERN on  COL-497 page");

		// --- COL-490 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Warrants", "Pass",
				"Successfully launched to List of Warrants(COL-490) screen");
		BclPage.dataTableIdRadio.click();
		sleep(2000);
		commonFuntions.screenShot("List of Warrants", "Pass", "Successfully selected data on COL-490 screen");
		commonFuntions.clickButtonContains("Continue ");

		// --- COL-491 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Destroy Warrant Details", "Pass",
				"Successfully launched to Destroy Warrant Details(COL-491) screen");
		try {
			BclPage.yesRadioInCircle.click();
		} catch (Exception exception) {
			BclPage.yesRadioOutCircle.click();
		}
		BclPage.reasonExplanation.sendKeys("Yes want to destory");
		commonFuntions.screenShot("Destroy Warrant Details", "Pass", "Entered details COL-491 screen");
		commonFuntions.clickButtonContains("Continue ");
		// --- COL-492 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Warrant Destroyed Verification", "Pass",
				"Successfully launched to Warrant Destroyed Verification(COL-492) screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Warrant Destroyed Confirmation", "Pass",
				"Successfully launched to Warrant Destroyed Confirmation(SUC -002) screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		sleep(4000);
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCL485001", "Pass", "Successfully passed TC BCL.485.001");

		System.out.println("Pass :)");

		///

	}

}
