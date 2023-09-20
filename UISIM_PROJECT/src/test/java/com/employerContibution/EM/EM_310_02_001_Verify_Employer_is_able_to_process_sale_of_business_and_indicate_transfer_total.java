package com.employerContibution.EM;

import java.sql.SQLException;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EM_005;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.pages.SREG_503;
import com.ui.pages.SUC_002;

import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_02_001_Verify_Employer_is_able_to_process_sale_of_business_and_indicate_transfer_total
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify EMP is able to process sale of business and indicate transfer 'total'", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void Tc_310_02_001() throws Exception {

		test = report.createTest("Verify employer is able to process sale of business and indicate transfer 'total'");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);
		SREG_503 sreg503 = new SREG_503(driver);
		SUC_002 suc002 = new SUC_002(driver);

		Map<String, String> dataBaseResults1 = commonFuntions.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ean IN (SELECT EAN FROM T_EMPLOYER te WHERE EMPLOYER_ID IN (SELECT TO_EMPLOYER_ID FROM T_EMPLOYER_TRANSFER_SUCCESSOR WHERE NOT_100_PERCENT_TRANSFER_EXPLAIN = 'Total Transfer'))");
		String feinValue = dataBaseResults1.get("Fein");
		String eanValue = dataBaseResults1.get("Ean");

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ean IN (SELECT EAN FROM T_EMPLOYER te WHERE EMPLOYER_ID IN (SELECT TO_EMPLOYER_ID FROM T_EMPLOYER_TRANSFER_SUCCESSOR WHERE NOT_100_PERCENT_TRANSFER_EXPLAIN = 'Total Transfer'))",
				"ENTITY_NAME");

		String legalName = databaseResults.get("ENTITY_NAME");
		// System.out.println("feinValue " + feinValue);
		// System.out.println("ernValue " + eanValue);
		System.out.println("ENTITY_NAME" + legalName);

		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_4.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// ---Menu
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.screenShot("Menu", "Pass", "Menu page");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Sale of Business");
		sleep(2000);
		commonFuntions.screenShot("Menu", "Pass", "Menu page");
		commonFuntions.clickMenu("Sale of Business");
		commonFuntions.screenShot("Menu", "Pass", "Menu selected");

		// ----SREG -503
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Sales of bussiness", "Pass", "Successfully launched to(SREG-503) page");
		commonFuntions.selectRadioQuestions("Have you sold your business?", "Yes");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadioQuestions("Did you sell all or part of your business?", "Full");
		Thread.sleep(2000);
		commonFuntions.enterCurrentDate("Effective Date of Transfer");
		Thread.sleep(1000);
		commonFuntions.screenShot("Sales of bussiness", "Pass", "Entered Information (SREG-503) page");
		commonFuntions.enterTextboxContains("Successor`s Name", legalName.trim());
		commonFuntions.enterTextboxContains("Successor`s FEIN", feinValue);
		commonFuntions.enterTextboxContains("Successor`s ERN", eanValue);
		Thread.sleep(1000);
		commonFuntions.screenShot("Sales of bussiness", "Pass", "Entered Information (SREG-503) page");
		commonFuntions.enterTextboxContains("Attention/Care Of", "Einser Com");
		commonFuntions.enterTextboxContains("Address Line 1 ", "testadd");
		commonFuntions.enterTextboxContains("Address Line 2 ", "james");
		commonFuntions.enterTextboxContains("City ", "Boston");
		Thread.sleep(1000);
		commonFuntions.screenShot("Sales of bussiness", "Pass", "Entered Information (SREG-503) page");
		// commonFuntions.selectDropdown("Country", " United States ");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("State", " New York ");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Zip Code", "02110");
		sreg503.successorTelePhoneInput.sendKeys("6174577513");
		Thread.sleep(1000);
		commonFuntions.screenShot("Sales of bussiness", "Pass", "Entered Information (SREG-503) page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SREG 504
		commonFuntions.screenShot("Sale of Business", "Pass", "SREG-504 screen is visible");
		test.info("Step: 5&6");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Submit ");

		// ----SUC 002
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		commonFuntions.Label("The request is under review.");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		sleep(4000);
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");

		// ---Executed & completed by Palak

	}
}
