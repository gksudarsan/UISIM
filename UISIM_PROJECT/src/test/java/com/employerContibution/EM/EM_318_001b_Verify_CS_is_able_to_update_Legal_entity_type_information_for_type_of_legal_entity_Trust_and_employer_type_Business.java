package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_318_001b_Verify_CS_is_able_to_update_Legal_entity_type_information_for_type_of_legal_entity_Trust_and_employer_type_Business
		extends TestBase {

	@Test
	public void Tc_EM_418_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);

		String ern = "0478465";

		test = report.createTest(
				"EM_318_001b_Verify_CS_is_able_to_update_Legal_entity_type_information_for_type_of_legal_entity_Trust_and_employer_type_Business");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 3 -- Navigate to Main Menu -> Account Maintenance -> Maintain Address – Enter ERN");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu("Maintain Accounts");
		commonFuntions.clickMenu("Maintain Accounts");
		Thread.sleep(2000);
		commonFuntions.screenShot("Employer Account Maintenance – Enter ERN", "Pass", "SREG-027 screen is visible");

		test.info("Step: 4 --");
		commonFuntions.enterTextboxContains("Employer Registration Number", ern);
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Modify Employer Account Details", "Pass", "SREG-030 screen is visible");

		test.info("Step: 5 --");
		commonFuntions.selectDropdown("Type of Legal Entity", " Trust ");
		sleep(2000);
		commonFuntions.selectDropdown("Source", " IA602 ");
		sleep(2000);
		commonFuntions.selectDropdown("Source Type", " Cash Wages ");
		sleep(2000);
		commonFuntions.clickButton("Submit ");
		sleep(4000);
		commonFuntions.screenShot("Employer Account Maintenance Confirmation", "Pass", "SUC-002 screen is visible");

		test.info("Step: 6 --");
		commonFuntions.clickButton("Home ");
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homapage page displayed");

		test.info("Step: 7 --");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Inquiry Employer Account Profile Changes");
		commonFuntions.clickMenu("Inquiry Employer Account Profile Changes");
		sleep(2000);
		commonFuntions.screenShot("Inquiry Employer Account Profile Changes - Enter ERN", "Pass",
				"SREG-050 screen is displayed");
		sleep(2000);
		
		test.info("Step: 8 --");
		commonFuntions.enterTextboxContains("Employer Registration Number", ern);
		commonFuntions.clickButton("Continue ");
		sleep(5000);
		commonFuntions.screenShot("Employer Account Profile Changes", "Pass", "SREG-053 screen is visible");
		
		
		
		

	}
}
