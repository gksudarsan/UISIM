package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class ERM_408_29_001_Verify_system_calculated_rate_when_CSR_reviews_and_Approve_Automated_Registrations_Task
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision,", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_408_29_001() throws Exception {

		test = report.createTest(
				"ERM_408_29_001_Verify_system_calculated_rate_when_CSR_reviews_and_Approve_Automated_Registrations_Task");

		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		Map<String, String> databaseRateYear = cf.database_SelectQuerySingleColumn(
				"SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '"
						+ eanValue + "')",
				"RATE_YEAR");
		String rateYear = databaseRateYear.get("RATE_YEAR");
		System.out.println("The Rate Year is " + rateYear);

		// --- Login ---
		cf.login(COMMON_CONSTANT.CSR_General_Inquiry.toUpperCase(),
				COMMON_CONSTANT.CSR_General_Inquiry_PASSWORD);
		sleep(2000);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		cf.clickMenu("Menu");
		sleep();
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Rate");
		sleep();
		cf.screenShot("MenuPage", "Pass",
				"Navigate to Menu ->Account Maintenance ->Maintain Rate ->Update Contribution Rate");
		cf.clickMenu("Update Contribution Rate");
		sleep(2000);
	}
}
