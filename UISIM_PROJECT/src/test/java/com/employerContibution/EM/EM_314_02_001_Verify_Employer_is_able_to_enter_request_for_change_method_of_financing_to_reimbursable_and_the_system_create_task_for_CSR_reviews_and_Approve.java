package com.employerContibution.EM;

import java.util.Map;

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

public class EM_314_02_001_Verify_Employer_is_able_to_enter_request_for_change_method_of_financing_to_reimbursable_and_the_system_create_task_for_CSR_reviews_and_Approve
		extends TestBase {

	@Test
	public void Tc_EM_314_02_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);

		test = report.createTest(
				"Verify_CSR_is_able_to_search_ERN_and_add_Related_Business_Details_of_a_Management_Agreement");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info(
				"Step: 3 -- Navigate to  Home page > Account Maintenance > Employer Account Maintenance > Change in Method of Financing – Enter ERN");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		Thread.sleep(1000);
		commonFuntions.ScrollMenu("Change in Method of Financing");
		commonFuntions.clickMenu("Change in Method of Financing");
		Thread.sleep(2000);
		commonFuntions.screenShot("Change in Method of Financing", "Pass", "SREG-520 screen is visible");
		
		test.info(
				"Step: 4 -- ");
		Map<String, String> databaseResults = commonFuntions.database_SelectQuery(
				"SELECT EMPLOYER_TYPE,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'GOVT';");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		//String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + eanValue);
		//System.out.println("legalName " + legalName);
		

	}
}
