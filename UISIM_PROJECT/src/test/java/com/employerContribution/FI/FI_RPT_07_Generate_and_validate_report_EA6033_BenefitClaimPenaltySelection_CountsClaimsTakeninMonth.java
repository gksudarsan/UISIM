package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_RPT_07_Generate_and_validate_report_EA6033_BenefitClaimPenaltySelection_CountsClaimsTakeninMonth
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "FI.RPT.07 - Generate and validate the report_EA6033_Benefit Claim Penalty Selection Counts Claims Taken in Month", groups = "Regression")
	public void FI_RPT_07() throws Exception {

		test = report.createTest(
				"FI.RPT.07 - Generate and validate the report_EA6033_Benefit Claim Penalty Selection Counts Claims Taken in Month");
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home", "Pass", "Home Page launched");
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.clickMenu("Inquiry");
		sleep();
		commonFuntions.clickMenu("Reports");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Reports->Generate Reports");
		commonFuntions.clickMenu("Generate Reports");
		sleep();

		// ---Generate Reports-ODS-001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Generate Reports-ODS-001", "Pass", "Generate Reports-ODS-001");
		commonFuntions.selectDropdown("Report Name",
				"EA6033 - BENEFIT CLAIM PENALTY SECTION COUNTS CLAIMS TAKEN IN A MONTH");
		commonFuntions.selectDropdown("Report Format", "PDF");
		commonFuntions.enterPastDate("Start Date", 67);
		commonFuntions.enterPastDate("End Date", 37);
		commonFuntions.screenShot("Generate Reports-ODS-001", "Pass", "Details filled to Generate Reports-ODS-001");
		commonFuntions.clickButton(" Generate Report ");
		//sleep(15000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Generate Reports-ODS-001", "Pass", "Reports Generated-ODS-001");
		commonFuntions.clickButton("Home ");
		commonFuntions.screenShot("HOme", "Pass", "Back to Home Page");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.clickMenu("Inquiry");
		sleep();
		commonFuntions.clickMenu("Reports");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Reports->Search Reports");
		commonFuntions.clickMenu("Search Reports");
		sleep();

		// ---Search Reports-DMS-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search Reports-DMS-002", "Pass", "Search Reports launched-DMS-002");
		commonFuntions.selectDropdownUsingSearch("Report", "EA6033 - BENEFIT CLAIM PENALTY SECTION COUNTS CLAIMS TAKEN IN A MONTH");
		commonFuntions.enterCurrentDate("Start Date");
		commonFuntions.enterCurrentDate("End Date");
		commonFuntions.screenShot("Search Reports-DMS-002", "Pass", "Details filled to search the Reports-DMS-002");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Search Reports-DMS-002", "Pass", "Reports Searched-DMS-002");
		sleep();
		commonFuntions.clickHyperlink("BENEFIT CLAIM PENALTY SECTION COUNTS CLAIMS TAKEN IN A MONTH");
		sleep();
		commonFuntions.switchTab();
		sleep();
		commonFuntions.screenShot("Report downloaded-DMS-002", "Pass", "Reports downloaded TC FI_RPT_07 got pass");
		commonFuntions.verifyContentInPDf("BENEFIT CLAIM PENALTY SECTION COUNTS CLAIMS TAKEN IN A MONTH");
		sleep();
		/*-Test Case Completed-*/

	}
}