package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_418_001_Verify_CSR_is_able_to_remove_SIDES_E_Response_from_an_employer_account extends TestBase{


	@Test
	public void Tc_EM_418_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);

		test = report.createTest(
				"Verify_CSR_is_able_to_search_ERN_and_add_Related_Business_Details_of_a_Management_Agreement");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info(
				"Step: 3 -- Navigate to Main Menu -> Account Maintenance -> Maintain Address – Enter ERN");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Maintain Address");
		commonFuntions.clickMenu("Maintain Address");
		Thread.sleep(2000);
		commonFuntions.screenShot("Maintain Address", "Pass", "SREG-070 screen is visible");
		
		
		
		test.info(
				"Step: 4 -- Enter the Valid Employer Registration Number in \"\"Maintain Address - Enter ERN (SREG-070)\"\" screen");
		Map<String, String> databaseResults = PEOPage.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE CREATED_BY !='LEGACY' AND EAN IS NOT NULL");
		String feinValue =databaseResults.get("Fein");
		String ernValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		//System.out.println("feinValue "+ feinValue);
		//System.out.println("ernValue "+ ernValue);
		//System.out.println("legalName "+ legalName);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		Assert.assertTrue(sreg084.continueButton.isDisplayed());
		sreg084.continueButton.click();
		Thread.sleep(2000);
		
		test.info(
				"Step: 5 -- SIDES e-RESPONSE successfully removed from employer's account.");
		commonFuntions.selectRadio("Yes ");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown( "Source Type", " NYS-100 ");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(4000);
		commonFuntions.enterFutureDate("SIDES E-Response Effective End Date", 7);
		Thread.sleep(1000);
		commonFuntions.clickButtonContains("Submit ");
		
		
		// Blocked after this unable to verify next page
		
		
		
	}
	
}
