package com.employerContribution.FI;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_007_Verify_CSR_can_submit_anFI_issue_by_selecting_a_work_item_MISC_Determination_Protest_Miscellaneous_Protest
		extends TestBase {

	@Test
	public void FI_169_007() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		PEOPage peoPage = new PEOPage(driver);

		test = report.createTest(
				"FI_169_007_Verify_CSR_can_submit_anFI_issue_by_selecting_a_work_item_MISC_Determination_Protest_Miscellaneous_Protest");

		// -------DB---
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;",
				"EAN");

		String eanValue = databaseResults.get("EAN");
		if ((eanValue == null) || eanValue.isEmpty()) {
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanValue + ".");
		}

//		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(),COMMON_CONSTANT.CSR_USER_1_PASSWORD);
//		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu");
		sleep(1000);
		cf.ScrollMenu("Employer Issues");
		cf.clickMenu("Employer Issues");
		sleep(2000);
		cf.ScrollMenu("Create Work Item");
		sleep(1000);
		cf.screenShot("Menu Bar", "Pass", "Create Work Item menu side bar is displayed");
		cf.clickMenu("Create Work Item");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Create Work Item - Enter ERN", "Pass", "FIS-001 screen is displayed");

		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		cf.screenShot("Create Work Item", "Pass", "Entered valid ERN on FIS_001 page");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Select Work Item", "Pass", "FIS-010 screen is displayed");

		cf.selectDropdown("Select Unit", " L&D Generalist ");
		sleep(2000);
		cf.selectDropdown("Select Work Item", " MISC Protest Task ");
		sleep(2000);
		cf.screenShot("Select Work Item", "Pass", "Entered Information on FIS_001 page");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Protest UI Warrant Filing Task", "Pass", "FIS-003 screen is displayed");

		// FIS-003
		AddPage.browserLink.click();
		sleep(2000);
		cf.uploadDoc("TESTINGEL.docx");
		sleep(3000);

		cf.clickButtonContains(" Associate Documents ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Search and Associate Documents", "Pass", "WF-101 screen is displayed");

		Map<String, String> SSNdatabaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_SUBMIT_ISSUE_BENEFIT_CLAIM_PENALTY_ASSESSMENT ttsibcpa JOIN T_EMPLOYER_ACCOUNT tea ON TTSIBCPA.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID;",
				"CLAIMANT_SSN");

		String ssnNumber = SSNdatabaseResults.get("CLAIMANT_SSN");

		cf.enterTextboxContains("SSN ", ssnNumber);
		cf.waitForLoadingIconToDisappear();
		sleep(2000);
		cf.clickButtonContains(" Search ");
		cf.waitForLoadingIconToDisappear();
		sleep(2000);
		cf.screenShot("Search and Associate Documents", "Pass", "WF-101 screen search result is displayed");

		fis002.wf001checkboxFirst.click();
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Protest UI Warrant Filing Task", "Pass", "FIS-003 screen is displayed");

		test.info("Step: 10 -- ");
		cf.selectDropdown("Work Basket", " Default Work Basket ");
		sleep(2000);
		cf.selectDropdown("Source", " Correspondence/ Email/ Efax ");
		sleep(2000);
		fis002.fis003NameField.sendKeys("Rocky");
		cf.enterTextboxContains("Title", "Mr");
		cf.enterTextboxContains(" Telephone Number ", "1234567890");
		cf.enterTextboxContains("Mailing Address", "test@mail.com");
//		cf.enterTextboxContains("Reason/basis for Warrant Protest (must not exceed 2000 characters)", "test");
		cf.screenShot("Protest UI Warrant Filing Task", "Pass", "FIS-003 screen is displayed");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Verification - MISC Protest Task", "Pass", "FIS-005 screen is displayed");

		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.Label("  A Work Item has been successfully created ");
		cf.screenShot("Work Item Submission Confirmation", "Pass", "SUC-002 screen is displayed");

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"
				+ eanValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(4000);

		/*
		 * cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(),
		 * COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 */

		peoPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.screenShot("EAN Search", "Pass", "EAN Search");
		cf.clickButtonContains("Search");

		cf.clickOnLinkfirstItem("MISC Protest Task");
		cf.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		cf.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);

		cf.screenShot("MISC Protest Task", "Pass", "Launched to PFP-005");
		cf.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User", "No ");
		cf.selectRadioQuestions("Do you want to add a Hold Action Flag on this account", "No ");
		cf.populateListbox("Comments", "okay");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();
		cf.Label("The Task details have been successfully saved.");
		cf.screenShot("Task Confirmarion: SUC-002", "Pass", "Work Item Completed");
		cf.clickButtonContains("Home ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Home", "Pass", "Home page is displayed");
	}
}
