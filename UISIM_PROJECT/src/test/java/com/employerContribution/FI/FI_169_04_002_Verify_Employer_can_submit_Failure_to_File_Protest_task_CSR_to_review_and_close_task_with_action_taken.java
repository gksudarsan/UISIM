package com.employerContribution.FI;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_04_002_Verify_Employer_can_submit_Failure_to_File_Protest_task_CSR_to_review_and_close_task_with_action_taken
		extends TestBase {
	/**
	 * @throws Exception
	 */
	@Test
	public void FI_169_04_002() throws Exception {
		test = report.createTest(
				"FI_169_04_002_Verify_Employer_can_submit_Failure_to_File_Protest_task_CSR_to_review_and_close_task_with_action_taken");

		commonStepDefinitions cf = new commonStepDefinitions();
		SUC_002 suc002 = new SUC_002(driver);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = new PEOPage(driver);

		// Query
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// Login
		cf.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// Menu
		cf.clickMenu("menu");
		sleep(2000);
		cf.screenShot("MenuPage", "Pass", "Launched to Menu");
		cf.ScrollMenu("Secure Messaging");
		cf.clickMenu("Secure Messaging");
		sleep(1000);
		cf.clickMenu("Write Message");
		cf.screenShot("Write Message", "Pass", "Launched to SM-101");
		cf.selectDropdown("Category", " Protest ");
		cf.selectDropdown("Subcategory", " How do I protest Failure to File Penalties? ");
		cf.clickOnLinkAnchorTag("Protest Document for Failure to File Penalties");
		cf.switchTab();

		// FIS-008
		cf.screenShot("Protest Document for Failure to File Penalties", "Pass", "Launched to FIS-008");
		cf.enterTextboxContains("Employer Name", "Sam Hunt");
		cf.enterTextboxContains("ERN", eanValue);
		cf.selectCheckbox("I Accept");
		cf.clickButtonContains("Submit ");
		cf.Label("An Issue has been successfully created and will be assigned to the Internal Staff.");
		cf.screenShot("Issue Submission Confirmation", "Pass", "Launched to SUC-002 ");

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='0000368' ORDER BY UPDATED_TS desc)");
		Thread.sleep(4000);

		cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		peoPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		cf.enterTextboxContains("Employer Registration Number", "0000368");
		cf.screenShot("EAN Search", "Pass", "EAN Search");
		cf.clickButtonContains("Search");

		cf.clickOnLink("Failure To File Penalty Protest");
		cf.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		cf.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		cf.screenShot("Failure to File Penalty Protest", "Pass", "PFP-002 screen is visible");

		
		  cf.selectRadioQuestions("Close Task with No Action Taken", "Yes ");
		  suc002.enterProtestCommentField.sendKeys("Testing This Field");
		  cf.clickButtonContains("Submit "); cf.waitForLoadingIconToDisappear();
		  sleep(1000); cf.screenShot("Task Confirmation", "Pass",
		  "Message from Webpage popup is visible");
		 

		// DTF Pop-Up
		cf.clickButtonContains(" Yes ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.Label("The task details have been successfully saved");
		cf.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");
		cf.clickButtonContains("Home");
		cf.screenShot("Home", "Pass", "Home Page");
		System.out.println("Worked");
	}
}
