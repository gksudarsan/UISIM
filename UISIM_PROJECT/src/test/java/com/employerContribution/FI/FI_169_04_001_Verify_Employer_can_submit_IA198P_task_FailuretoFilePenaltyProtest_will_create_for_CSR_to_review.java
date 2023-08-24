package com.employerContribution.FI;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_04_001_Verify_Employer_can_submit_IA198P_task_FailuretoFilePenaltyProtest_will_create_for_CSR_to_review
		extends TestBase {
	@Test
	public void FI_169_04_001() throws Exception {
		test = report.createTest(
				"FI.169.04.001- 'Verify Employer can submit a IA198.P Failure to File Protest form online, task 'Failure to File Penalty Protest' will create for CSR to review and close task with no action taken");

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
		cf.enterTextboxContains("Address Line 1", "Brittany Hall 55 East");
		cf.enterTextboxContains("Address Line 2", "Street 5");
		cf.enterTextboxContains("City", "Albany");
		cf.selectDropdown("State", " New York ");
		cf.enterTextboxContains("Zip Code", "21356");
		cf.enterTextboxContains("ERN", "6835087");
		cf.enterTextboxContains(" FEIN ", "261484819");
		cf.enterTextboxContains("Assessment ID", "124323412111");
		cf.selectCheckboxSection1("Section I", 3);
		cf.enterTextboxContains("and/or NYS-45 ATT was filed:", "8/15/2023");
		cf.selectCheckboxSection1("Section I", 2);
		sleep();
		cf.enterTextboxContains("Name", "Shanice");
		cf.enterTextboxContains("ERN", "2345234");
		cf.enterTextboxContains(" FEIN ", "234253453");
		cf.selectCheckboxSection1("Section I", 1);
		cf.selectCheckboxSection2("The business ceased paying wages.", 1);
		cf.enterTextboxContains("Enter the last payroll date", "8/2/2023");
		sleep(2000);
		suc002.iDoNotCommentField.sendKeys("Test Content");

		cf.enterTextboxContains("Print Name", "Sam");

		cf.enterTextboxContains(" Daytime Phone", "(768)-128-9768");
		cf.enterTextboxContains("Date", "8/2/2023");
		cf.clickButtonContains("Submit ");

		// Required
		cf.errorLabel(" Required");

		cf.selectCheckbox("I Accept");
		cf.clickButtonContains("Submit ");

		// ERN Mismatched
		cf.errorContent("Invalid Employer Registration Number. No ERN matched.");

		// EAN
		cf.enterTextboxContains("ERN", eanValue);
		cf.clickButtonContains("Submit ");

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

		cf.selectRadioQuestions("Close Task with No Action Taken", "No ");
		suc002.enterProtestCommentField.sendKeys("Testing This Field");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Task Confirmation", "Pass", "Message from Webpage popup is visible");

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
