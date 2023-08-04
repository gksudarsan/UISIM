package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_802_05_003 extends TestBase {

	@Test(priority = 1, description = "BCL.802.05.003 -  Verify CSR can Search case number with select bankruptcy case status 'Dismissed' and Edit Bankruptcy Case Activity for activity type incoming and activity name 'Bankruptcy Claim Acknowledgement'", groups = {
			"Regression" })
	public void BCL_802_05_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFuntions
				.database_SelectQuerySingleColumn("SELECT * FROM T_TX_BANKRUPTCY tea", "CASE_NUMBER");
		String CASE_NUMBER = databaseEanResult.get("CASE_NUMBER");
		System.out.println(CASE_NUMBER);

		test = report.createTest(
				"BCL.802.05.003 -  Verify CSR can Search case number with select bankruptcy case status 'Dismissed' and Edit Bankruptcy Case Activity for activity type incoming and activity name 'Bankruptcy Claim Acknowledgement'");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		BCL.clickMenu_Bankruptcy.click();
		commonFuntions.ScrollMenu("Review/Update Bankruptcy Case Activity");
		commonFuntions.clickMenu("Review/Update Bankruptcy Case Activity");
		sleep();

		// ---COL-474---
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass",
				"Review/Update Bankruptcy Case Activity (COL-474)screen launched");
		commonFuntions.enterTextboxContains("a. Bankruptcy Case Number", CASE_NUMBER);
		commonFuntions.selectDropdown("2. Bankruptcy Status", " Dismissed ");
		commonFuntions.clickButton(" Search ");
		sleep();
		BCL.select_Review_UpdateBankruptcyCaseActivity.click();
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass",
				"Details selected with the respected case (COL-474)screen ");
		commonFuntions.clickButton("Continue ");
		sleep(2000);

		// ---COL-475---
		commonFuntions.screenShot("View Bankruptcy Case Details/History", "Pass",
				"View Bankruptcy Case Details/History (COL-475)screen launched");
		sleep(1000);
		commonFuntions.clickOnLink("Edit");
		sleep(2000);
		
		// ---COL-402---
		commonFuntions.screenShot("Add/Edit Activity- Bankruptcy Case Activity", "Pass",
				"Add/Edit Activity- Bankruptcy Case Activity (COL-402)screen launched");
		commonFuntions.selectRadioQuestions("1. Activity Type", "Incoming activity");
		commonFuntions.selectRadioQuestions("2. Is a response expected ?", "Yes ");
		commonFuntions.selectDropdown("3. Activity Name", " Bankruptcy Claim Acknowledgement ");
		commonFuntions.clearTextboxContains("4. Remarks");
		commonFuntions.enterTextboxContains("4. Remarks", "test");
		commonFuntions.enterTextboxContains("5. Activity Date", "6/30/2023");
		commonFuntions.selectRadioQuestions("6. Is this a Response to existing activity?", "Yes ");
		sleep(1000);
		commonFuntions.screenShot("Add/Edit Activity- Bankruptcy Case Activity", "Pass",
				"Details Entered on Add/Edit Activity- Bankruptcy Case Activity (COL-402)screen launched");
		commonFuntions.clickButton("Continue ");
		sleep(2000);

		// ---COL-513---
		commonFuntions.screenShot("Add/Edit Activity Verification", "Pass",
				"Add/Edit Activity Verification (COL-513)screen launched");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);

		// ---SUC-002---
		commonFuntions.screenShot("Add/Edit Activity Confirmation", "Pass",
				"Add/Edit Activity Confirmation (SUC-002)screen launched");
		commonFuntions.clickButtonContains("Home ");
		sleep(2000);

		// ---HOME---
		commonFuntions.screenShot("Home", "Pass", "Home screen launched");
		sleep(2000);

		// Done
	}

}
