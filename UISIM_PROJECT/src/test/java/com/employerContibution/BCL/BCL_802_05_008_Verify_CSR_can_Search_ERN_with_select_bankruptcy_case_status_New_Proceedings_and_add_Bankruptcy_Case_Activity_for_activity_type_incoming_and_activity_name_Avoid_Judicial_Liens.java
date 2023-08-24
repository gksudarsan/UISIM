
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

public class BCL_802_05_008_Verify_CSR_can_Search_ERN_with_select_bankruptcy_case_status_New_Proceedings_and_add_Bankruptcy_Case_Activity_for_activity_type_incoming_and_activity_name_Avoid_Judicial_Liens
		extends TestBase {
	@Test
	public void BCL_802_05_008() throws Exception {

		test = report.createTest(
				"BCL_802_05_008 - Verify CSR can search for warrants in active status based on ERN and Satisfy warrant details");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		BclPage BclPage = PageFactory.initElements(driver, BclPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE STATUS ='ACTV'",
				"EAN");

		String eanNumber = databaseResults.get("EAN");// 0524100

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
		BclPage.clickMenu_Bankruptcy.click();
		sleep(1000);
		commonFuntions.screenShot("Menu", "Pass", "Menu selected");
		commonFuntions.ScrollMenu("Review/Update Bankruptcy Case Activity");
		commonFuntions.clickMenu("Review/Update Bankruptcy Case Activity");
		sleep(1000);
		// ----COL 474
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass",
				"Successfully launched to COL 474 page");
		sleep(2000);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass",
				"Entered nothing on ERN:  COL 474 page");
		sleep(2000);
		commonFuntions.enterTextboxContains("b. Employer Registration Number", eanNumber);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "selected value on  COL 474 page");
		sleep(2000);
		// commonFuntions.selectRadioInTable(" * ",1, 1, "Review/Update Bankruptcy Case
		// Activity");
		BclPage.select_Review_UpdateBankruptcyCaseActivity.click();
		sleep(3000);
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		// ---------COL 475
		commonFuntions.screenShot("View Bankruptcy Case Details/History", "Pass",
				"Successfully launched on  COL 475 page");
		sleep(2000);
		commonFuntions.clickButtonContains("Add New Activity ");
		// BclPage.dataTableId_Activities_Radio.click();
		sleep(2000);
		// ---------COL 402
		commonFuntions.screenShot("Add/Edit Activity - Bankruptcy Case Activity", "Pass",
				"Successfully launched on  COL 402 page");
		sleep(2000);

		commonFuntions.selectRadioQuestions("1. Activity Type", "Incoming activity");
		commonFuntions.selectRadioQuestions("2. Is a response expected ?", "Yes ");
		commonFuntions.selectDropdown("3. Activity Name", " Avoid Judicial Liens ");
		commonFuntions.clearTextboxContains("4. Remarks");
		commonFuntions.enterTextboxContains("4. Remarks", "Testing");
		commonFuntions.enterTextboxContains("5. Activity Date", "6/30/2023");
		commonFuntions.selectRadioQuestions("6. Is this a Response to existing activity?", "No ");
		sleep(1000);
		commonFuntions.screenShot("Add/Edit Activity- Bankruptcy Case Activity", "Pass",
				"Details Entered on Add/Edit Activity- Bankruptcy Case Activity (COL-402)screen launched");
		commonFuntions.clickButton("Continue ");
		sleep(2000);

		// --- COL 513---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add/Edit Activity Verification", "Pass", "Successfully launched  COL 513 screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add/Edit Activity Confirmation", "Pass",
				"Successfully launched to Add/Edit ActivityConfirmation(SUC -002) screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		sleep(4000);
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCL_802_05_008", "Pass", "Successfully passed TC BCL_802_05_008");

		System.out.println("Pass :)");
		// Completed Palak

	}

}
