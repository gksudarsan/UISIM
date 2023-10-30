package com.employerContribution.FI;

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
import com.ui.pages.FIpage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_RPT_09 extends TestBase {

	@Test(priority = 1, description = "FI.RPT.09 - Generate and validate the report_EA6039_Benefit Claim Penalty with UI Delinquent Employers", groups = {
			"Regression" })
	public void FI_RPT_09() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		FIpage FI = new FIpage(driver);

		test = report.createTest(
				"FI.RPT.09 - Generate and validate the report_EA6039_Benefit Claim Penalty with UI Delinquent Employers");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("Menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Inquiry");
		commonFunctions.clickMenu("Inquiry");
		commonFunctions.ScrollMenu("Reports");
		commonFunctions.clickMenu("Reports");
		commonFunctions.ScrollMenu("Generate Reports");
		commonFunctions.clickMenu("Generate Reports");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---ODS-001---
		commonFunctions.screenShot("Generate Reports", "Pass", "Generate Reports (ODS-001)screen launched");
		commonFunctions.selectDropdown("Report Name",
				" EA6039 - BENEFIT CLAIM PENALTY WITH UI DELINQUENT EMPLOYERS ");
		commonFunctions.selectDropdown("Report Format", " PDF ");
		commonFunctions.enterTextboxContains("Start Date", "1/1/2020");
		commonFunctions.enterTextboxContains("End Date", "4/30/2020");
		commonFunctions.screenShot("Generate Reports", "Pass", "Details Entered On ODS-001");
		commonFunctions.clickButtonContains(" Generate Report ");
		commonFunctions.waitForLoadingIconToDisappear();

		commonFunctions.clickMenu("Menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Inquiry");
		commonFunctions.clickMenu("Inquiry");
		commonFunctions.ScrollMenu("Reports");
		commonFunctions.clickMenu("Reports");
		commonFunctions.ScrollMenu("Search Reports");
		commonFunctions.clickMenu("Search Reports");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---DMS-002---
		commonFunctions.screenShot("Search Reports", "Pass", "Search Reports (DMS-002)screen launched");
		commonFunctions.selectDropdown("Report",
				" EA6039 - BENEFIT CLAIM PENALTY WITH UI DELINQUENT EMPLOYERS ");
		commonFunctions.enterTextboxContains("Start Date", "7/13/2023");
		commonFunctions.enterTextboxContains("End Date", "7/13/2023");
		commonFunctions.screenShot("Search Reports", "Pass", "Details Entered On DMS-002");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();

		commonFunctions.clickOnLink("BENEFIT CLAIM PENALTY WITH UI DELINQUENT EMPLOYERS");

		// Done
	}

}
