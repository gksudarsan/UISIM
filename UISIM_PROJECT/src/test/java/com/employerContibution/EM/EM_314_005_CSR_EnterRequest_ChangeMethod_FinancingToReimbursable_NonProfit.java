
//------------------------------------- not done yet, data unavailable to meet pre-requisite, Employer Type -> Non-Profit --------------------------

package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_043;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_314_005_CSR_EnterRequest_ChangeMethod_FinancingToReimbursable_NonProfit extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter request for change method of financing  to reimbursable with select option No.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EM_314_004() throws Exception {

		test = report.createTest(
				"Verify CSR is able to enter request for change method of financing  to reimbursable with select option No.");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement empManagement = new employerManagement(driver);
		PEOPage peoPage = new PEOPage(driver);
		SREG_043 sreg043 = new SREG_043(driver);

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		test.info("Step: 3 -- ");
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.ScrollMenu("Change in Method of Financing");
		commonFunction.screenShot("MenuNavigation", "Pass",
				"Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Change in Method of Financing");
		commonFunction.clickMenu("Change in Method of Financing");

		Map<String, String> databaseResults = peoPage.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + eanValue);
		System.out.println("legalName " + legalName);

		// --- ETR-228 ---
		test.info("Step: 4 -- ");
		sleep(2000);
		commonFunction.screenShot("EM314004", "Pass",
				"Successful launch to Change in Method of Financing(ETR-228) page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		commonFunction.screenShot("Change in Method of Financing", "Pass", "ETR-229 screen is displayed");

		test.info("Step: 5 -- ");
		commonFunction.selectRadioQuestions("Do you want to change the method of financing to contributory?", "Yes ");
		sleep(1000);
		commonFunction.enterPastDate("Enter the receipt date of written request.", 7);
		sleep(1000);
		commonFunction.enterFutureDate("Requested Effective Date", 7);
		sleep(1000);
		sreg043.EEWI002CommentsField.sendKeys("test ok");
		sleep(1000);
		
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep(2000);
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		sleep(2000);
		commonFunction.clickButton("Continue ");
		sleep(4000);
		commonFunction.screenShot("Change in Method of Financing Verification", "Pass", "ETR-230 screen is visible");
	
		test.info("Step: 6 -- ");
		commonFunction.selectRadioQuestions("Suppress Correspondence", "Yes ");
		sleep(2000);
		commonFunction.clickButton("Submit ");
		sleep(4000);
		commonFunction.screenShot("Change Method of Financing Confirmation", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 7 -- ");
		commonFunction.clickButton("Home ");
		Thread.sleep(5000);
		commonFunction.screenShot("Homepage", "Pass", "Homapage page displayed");
		
	}

}
