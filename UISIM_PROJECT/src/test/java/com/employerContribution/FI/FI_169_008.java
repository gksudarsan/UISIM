package com.employerContribution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.COL_474;
import com.ui.pages.FIP_006;
import com.ui.pages.FIS_002;
import com.ui.pages.FIS_008;
import com.ui.pages.FIS_009;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_008 extends TestBase {

	@Test
	public void FI169_008() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);
		COL_474 col474 = new COL_474(driver);
		FIP_006 fip006 = new FIP_006(driver);
		FIS_008 fis008 = new FIS_008(driver);
		FIS_009 fis009 = new FIS_009(driver);

		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = report.createTest(
				"FI.169.008 - 'Verify CSR can submit an FI Issue by selecting a work item 'Protest UI Warrant Filing Task'  (Issue Category - Protest, Issue Subcategory - 'UI Warrant Filing')");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		// -------DB---
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;",
				"EAN");

		String eanNumber = databaseResults.get("EAN");

		if ((eanNumber == null) || eanNumber.isEmpty()) {
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanNumber + ".");
		}

		test.info("Step: 3 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Issues");
		commonFuntions.clickMenu("Employer Issues");
		sleep(2000);
		commonFuntions.ScrollMenu("Create Work Item");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Create Work Item menu side bar is displayed");
		commonFuntions.clickMenu("Create Work Item");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Create Work Item - Enter ERN", "Pass", "FIS-001 screen is displayed");

		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
		sleep(2000);
		commonFuntions.screenShot("Create Work Item", "Pass", "Entered valid ERN on FIS_001 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Select Work Item", "Pass", "FIS-010 screen is displayed");

		test.info("Step: 5 -- ");
		commonFuntions.selectDropdown("Select Unit", " Collections ");
		sleep(2000);
		commonFuntions.selectDropdown("Select Work Item", " Protest UI Warrant Filing Task ");
		sleep(2000);
		commonFuntions.screenShot("Select Work Item", "Pass", "Entered Information on FIS_001 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Protest UI Warrant Filing Task", "Pass", "FIS-003 screen is displayed");
		
		test.info("Step: 6 -- ");
		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL.docx");
		sleep(3000);
		
		test.info("Step: 7 -- ");
		commonFuntions.clickButtonContains(" Associate Documents ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Search and Associate Documents", "Pass", "WF-101 screen is displayed");
		
		Map<String, String> SSNdatabaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_SUBMIT_ISSUE_BENEFIT_CLAIM_PENALTY_ASSESSMENT ttsibcpa JOIN T_EMPLOYER_ACCOUNT tea ON TTSIBCPA.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID;",
				"CLAIMANT_SSN");

		String ssnNumber = SSNdatabaseResults.get("CLAIMANT_SSN");
		
		test.info("Step: 8 -- ");
		commonFuntions.enterTextboxContains("SSN ", ssnNumber);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Search and Associate Documents", "Pass", "WF-101 screen search result is displayed");
		
		test.info("Step: 9 -- ");
		fis002.wf001checkboxFirst.click();
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Protest UI Warrant Filing Task", "Pass", "FIS-003 screen is displayed");
		
		test.info("Step: 10 -- ");
		commonFuntions.selectDropdown("Work Basket", " Gen Corr-599 CRU ");
		sleep(2000);
		commonFuntions.selectDropdown("Source", " Department Request ");
		sleep(2000);
		//commonFuntions.enterTextboxContains("Name", "Dev");
		fis002.fis003NameField.sendKeys("Rocky");
		commonFuntions.enterTextboxContains("Title", "Mr");
		commonFuntions.enterTextboxContains(" Telephone Number ", "1234567890");
		commonFuntions.enterTextboxContains("Mailing Address", "test2@gmail.com");
		commonFuntions.enterPastDate("Warrant File Date", 7);
		commonFuntions.enterTextboxContains("Warrant Amount", "200000");
		commonFuntions.enterTextboxContains("Reason/basis for Warrant Protest (must not exceed 2000 characters)", "test");
		commonFuntions.screenShot("Protest UI Warrant Filing Task", "Pass", "FIS-003 screen is displayed");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Verification - Protest UI Warrant Filing Task", "Pass", "FIS-005 screen is displayed");
		
		test.info("Step: 11 -- ");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Work Item Submission Confirmation", "Pass", "SUC-002 screen is displayed");
		
		test.info("Step: 12 -- ");
		commonFuntions.clickButtonContains("Home");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home page", "Pass", "Home screen is displayed");

	}

}
