package com.employerContribution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.COL_474;
import com.ui.pages.FIP_006;
import com.ui.pages.FIS_002;
import com.ui.pages.FIS_008;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.pages.FIS_009;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_405_004 extends TestBase {

	@Test
	public void FI405_004() throws Exception {
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
				"FI.405.004.Verify Employer can submit IA13.8 online form to file a protest for a BCP penalty and system create task for CSR review and remove the BCP Penalty for the quarter and store the cancel code.");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 3 -- ");
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("Write Message");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Write Message menu side bar is displayed");
		commonFuntions.clickMenu("Write Message");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 screen is displayed");

		test.info("Step: 4 -- ");
		commonFuntions.selectDropdown("Category", " Protest ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.selectDropdown("Subcategory", " How Do I Protest Benefit Claim Penalty? ");
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 with details filled screen is displayed");
		sleep(2000);
		commonFuntions.clickOnLinkAnchorTag("Click Here");
		commonFuntions.waitForLoadingIconToDisappear();
		Set<String> allHandles = driver.getWindowHandles();
		Iterator<String> l1 = allHandles.iterator();
		String parent = l1.next();
		System.out.println(parent);
		String Child = l1.next();
		System.out.println(Child);
		driver.switchTo().window(Child);
		sleep(2000);
		commonFuntions.screenShot("Benefit Claim Penalty Assessment Protest", "Pass", "FIS-009 screen is displayed");

		test.info("Step: 5 -- ");
		// CLAIMANT_NAME
		Map<String, String> claimantNameOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_SUBMIT_ISSUE_BENEFIT_CLAIM_PENALTY_ASSESSMENT ORDER BY UPDATED_TS DESC;",
				"CLAIMANT_NAME");
		String claimantName = claimantNameOutput.get("CLAIMANT_NAME");
		System.out.println(claimantName);
		//

		// CLAIMANT_SSN
		Map<String, String> claimantSnnOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_SUBMIT_ISSUE_BENEFIT_CLAIM_PENALTY_ASSESSMENT ORDER BY UPDATED_TS DESC;",
				"CLAIMANT_SSN");
		String claimantSnn = claimantSnnOutput.get("CLAIMANT_SSN");
		System.out.println(claimantSnn);
		//

		commonFuntions.enterTextboxContains("Claimant Name", claimantName);
		commonFuntions.enterTextboxContains("SSN", claimantSnn);
		commonFuntions.selectDropdown("Quarter ", " 2 ");
		commonFuntions.selectDropdown("Year ", " 2022 ");
		fis009.selectCheckboxfis009(
				"The wage information was reported correctly on the Quarterly Combined Withholding, Wage Reporting and Unemployment Insurance Return.");
		commonFuntions.selectCheckbox("I Accept");

		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Issue Submission Confirmation", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 6 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("HomePage", "Pass", "Home Page screen is displayed");

		String eanvalue = "0000368";

		//
		commonFuntions.database_UpdateQuery(
				"UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
						+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"
						+ eanvalue + "' ORDER BY UPDATED_TS desc);");
		//

		test.info("Step: 7 -- ");
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);

		test.info("Step: 8 -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanvalue);
		commonFuntions.screenShot("EanSearch", "Pass", "EanSearch");
		commonFuntions.clickButtonContains("Search");

		test.info("Step: 9 -- ");
		// commonFuntions.clickOnLink("Review Benefit Claim Penalty Protest");
		fip006.reviewBenefitClaimPenaltyProtestWorkItem.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Benefit Claim Penalty Protest", "Pass", "PFP–004 screen is visible");

		test.info("Step: 10 -- ");
		fip006.selectRadioWithValue(" Cancel BCP ");
		commonFuntions.selectDropdown("If Cancel a BCP,choose the cancel code", " ADME ");
		fis009.pfp4_IA13RadioBtn.click();

		try {
			fis009.pfp4_findingRequiredRadioBtn.click();
		} catch (Exception e) {
			System.out.println("radio is not there");
		}

		fis009.commentfieldResolution.sendKeys("testing comment");

		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");

		test.info("Step: 11 -- ");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

	}

}
