package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SREG_011;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_05_013_Verify_Employer_is_able_to_process_Business_Acquisition_an_indicate_transfer_Total_when_the_effective_date_of_the_transfer_is_in_the_future_and_system_create_task_for_CSR_reviews
		extends TestBase {

	@Test
	public void Tc_EM_310_05_013() throws Exception {

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_011 sreg011 = new SREG_011(driver);
		SREG_041 sreg041 = new SREG_041(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"Verify_Employer_is_able_to_process_Business_Acquisition_an_indicate_transfer_Total_when_the_effective_date_of_the_transfer_is_in_the_future_and_system_create_task_for_CSR_reviews");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 3 -- Navigate the path Menu > Account Maintenance > Business Acquisition");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Business Acquisition");
		commonFuntions.clickMenu("Business Acquisition");
		Thread.sleep(2000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "SREG-011 - Screen is visible");

		test.info("Step: 4 --Enter/Select the details on  \"Business Acquisition  (SREG-011)\"");
		
		Map<String, String> databaseResults = PEOPage
				.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI' AND EAN IS NOT NULL;");
		String feinValue = databaseResults.get("Fein");
		String ernValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + ernValue);
		System.out.println("legalName " + legalName);
		
		
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?","Yes");
		Thread.sleep(1000);
		//commonFuntions.enterTextboxContains("Employer Registration Number", "00-00093");
		sreg011.eanField.sendKeys(ernValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		//commonFuntions.enterTextboxContains("Legal Name of Business", "ABCLCORP LLC");
		sreg011.legalNameField.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Address Line 1 ", "Test");
		commonFuntions.enterTextboxContains("City ", "Test");
		commonFuntions.selectDropdown("Country", "United States");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("State", " New York ");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Zip Code", "53453");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?","ALL");
		Thread.sleep(1000);
		commonFuntions.enterFutureDate("Acquisition Date", 7); //enterFutureDate
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "SREG-012 - Screen is visible");
		
		test.info("Step: 5 --The request is under review");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "SREG-002 - Screen is visible");
		
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		
		test.info("Step: 6 --Click home button");
		commonFuntions.clickButtonContains("Home ");
		Thread.sleep(5000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "Home - Screen is visible");
		
		
		test.info("Step: 7&8 --Logout and login with csr ");
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("Business Acquisition", "Pass", "logged In");
		
		test.info("Step: 9 --CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 10 --Search fein");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
}
}
