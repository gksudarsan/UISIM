package com.employerContibution.EE;



	import java.util.Map;

	import org.openqa.selenium.support.PageFactory;
	import org.testng.annotations.Test;

	import com.ui.base.TestBase;
	import com.ui.pages.EmployerRegisterPage;
	import com.ui.pages.PEOPage;
	import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;

	public class EE_01_005_Verify_CSR_can_submit_employer_registration_for_employer_type_Business_and_legal_entity_type_Limited_Liability_Partnership_and_work_items_will_be_created_for_CSR_to_review extends TestBase {

		@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EE_01_005_Verify_CSR_can_submit_employer_registration_for_employer_type_Business_and_legal_entity_type_Limited_Liability_Partnership_and_work_items_will_be_created_for_CSR_to_review,", groups = {
				COMMON_CONSTANT.REGRESSION })
		public void TC_ERM_48_001() throws Exception {
			
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

			test = report.createTest("EE_01_005_Verify_CSR_can_submit_employer_registration_for_employer_type_Business_and_legal_entity_type_Limited_Liability_Partnership_and_work_items_will_be_created_for_CSR_to_review");

			commonStepDefinitions commonFunction = new commonStepDefinitions();
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
			PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
			
			  
		/*
		 * Map<String, String> databaseEanResult =
		 * commonFunction.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')"
		 * , "EAN"); String eanValue = databaseEanResult.get("EAN");
		 * System.out.println("The EAN is " + eanValue);
		 * 
		 * 
		 * Map<String, String> databaseRateYear =
		 * commonFunction.database_SelectQuerySingleColumn(
		 * "SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '"
		 * +eanValue+ "')" , "RATE_YEAR"); String rateYr =
		 * databaseRateYear.get("RATE_YEAR"); System.out.println("The Rate Year is " +
		 * rateYr);
		 */
			  
			  
			 
			// --- Login ---
			commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			sleep(2000);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
			sleep(2000);

			// ---Menu Click---
			commonFunction.clickMenu("Menu");
			//commonFunction.clickButtonContains("Home");
			peoPage.queue.click();
			sleep(3000);
			//commonFunction.screenShot("", status, message);
	}

}
