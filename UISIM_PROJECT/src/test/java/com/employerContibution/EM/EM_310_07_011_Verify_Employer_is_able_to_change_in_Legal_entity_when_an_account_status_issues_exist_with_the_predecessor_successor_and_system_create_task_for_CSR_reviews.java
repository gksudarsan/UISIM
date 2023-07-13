package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.SREG_503;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_07_011_Verify_Employer_is_able_to_change_in_Legal_entity_when_an_account_status_issues_exist_with_the_predecessor_successor_and_system_create_task_for_CSR_reviews
		extends TestBase {

	@Test
	public void Tc_EM_310_07_011() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);
		SREG_503 sreg503 = new SREG_503(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_043 sreg043 = new SREG_043(driver);

		test = report.createTest(
				"EM_310_07_011_Verify_Employer_is_able_to_change_in_Legal_entity_when_an_account_status_issues_exist_with_the_predecessor_successor_and_system_create_task_for_CSR_reviews");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info(
				"Step: 3 -- Navigate to  Home page > Account Maintenance > Employer Account Maintenance > Change in Method of Financing – Enter ERN");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		Thread.sleep(1000);
		commonFuntions.ScrollMenu("Change in Legal Entity");
		commonFuntions.clickMenu("Change in Legal Entity");
		Thread.sleep(2000);
		commonFuntions.screenShot("Change in Method of Financing", "Pass", "SREG-520 screen is visible");

		test.info("Step: 4 --");
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		sleep();

		// Map<String, String> databaseResults = sreg503.database_SelectQuery_Successor(
		// "SELECT * FROM T_EMPLOYER_TRANSFER_SUCCESSOR WHERE SUCCESSOR_ERN IN (SELECT
		// EAN FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS IN
		// ('SUDP','CNEX','CNDP','SUSN','SUUC','SUSB') AND EAN IS NOT NULL)");

		Map<String, String> databaseResults = PEOPage.database_SelectQuery(
				"SELECT ACCOUNT_STATUS status, * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='SUSC'");

		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String legalNameValue = databaseResults.get("legalName");

		System.out.println(feinValue);
		System.out.println(eanValue);
		System.out.println(legalNameValue);

		commonFuntions.screenShot("Change in Method of Financing", "Pass", "SREG-520 screen is visible");
		// Retrive FEIN
		String FeinValueRetrive = commonFuntions.retrieveValue("FEIN");
		System.out.println(FeinValueRetrive);
		//
		commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", feinValue);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", feinValue);
		Thread.sleep(1000);
		commonFuntions.enterCurrentDate("Date of Legal Entity change");
		Thread.sleep(1000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(1000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Change in Legal Entity Details", "Pass", "EM-007 screen is visible");

		test.info("Step: 5 --");
		Thread.sleep(1000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(1000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Change in Legal Entity Confirmation", "Pass", "SUC-002 screen is visible");

		test.info("Step: 6 --");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ FeinValueRetrive + "' ORDER BY UPDATED_TS desc)");

		test.info("Step: 7&8 --");
		//commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		test.info("Step: 9 --");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 10 --");
		commonFuntions.enterTextboxContains("FEIN", FeinValueRetrive);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		commonFuntions.screenShot("", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 11 --");
		sreg084.verifyTransferpelink.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Validate Total/Partial Transfer Failed Rules Task", "Pass", "EEWI-014 screen is visible");

		test.info("Step: 12 --");
		sreg043.EEWI014CommentFeild.sendKeys("Review em comment");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 13 --");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		
	}
}
