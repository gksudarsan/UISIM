package com.employerContribution.FI;


import org.testng.annotations.Test;

import com.ui.base.TestBase;

import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_RPT_13 extends TestBase {

	@Test
	public void FIRPT_13() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		
		test = report.createTest(
				"FI.RPT.13 - Generate and validate the report_EA6089_Notice of Penalty Assessment (Original Notice Not Mailed)");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 2 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Reports");
		commonFuntions.clickMenu("Reports");
		sleep(2000);
		commonFuntions.ScrollMenu("Search Reports");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Search Reports menu side bar is displayed");
		commonFuntions.clickMenu("Search Reports");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Search Reports", "Pass", "DMS-002 screen is displayed");
		
		test.info("Step: 4 -- ");
		commonFuntions.selectDropdown("Report", " EA6089 - NOTICE OF PENALTY ASSESSMENT ORIGINAL NOTICE NOT MAILED ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.enterTextboxContains("Start Date", "6/1/2023");
		commonFuntions.enterCurrentDate("End Date");
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Search Reports", "Pass", "DMS-002 - Results screen is displayed");
		commonFuntions.clickOnLinkfirstItem("NOTICE OF PENALTY ASSESSMENT ORIGINAL NOTICE NOT MAILED");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.switchTab();
		
		test.info("Step: 5 -- ");
		sleep(2000);
		commonFuntions.verifyContentInPDf("NOTICE OF PENALTY ASSESSMENT");
		commonFuntions.screenShot("PDF", "Pass", "PDF-Verification screen is displayed");
		
	}

}