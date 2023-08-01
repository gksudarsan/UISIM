package com.employerContribution.ERM;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_RPT_01_Generate_and_validate_the_Report_EA1159_Rate_Changes extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "ERM.RPT.01_Generate and validate the Report_EA1159_Rate Changes", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_RPT_21() throws Exception {

		test = report.createTest("ERM.RPT.01_Generate and validate the Report_EA1159_Rate Changes");

		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// --- Login ---
		cf.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		cf.clickMenu("menu");
		sleep();
		cf.clickMenu("Inquiry");
		cf.clickMenu("Reports");
		cf.clickMenu("Search Reports");
		sleep();
		cf.screenShot("Search Reports", "Pass", "Launched to DMS-002");
		cf.selectDropdown("Report", "EA1159 - RATE CHANGES");
		cf.enterTextbox("Start Date", "6/7/2023");
		cf.enterTextboxContains("End Date", "7/10/2023");
		cf.clickButton(" Search ");

		cf.screenShot("Search Reports", "Pass", "Launched to DMS-002");
		sleep();
		cf.clickOnLink1("RATE CHANGES");
		sleep(10000);
		cf.verifyContentInPDf("NEW YORK STATE DEPARTMENT OF LABOR");
		System.out.println("Worked");

	}

}
