package com.employerContibution.BCL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.COL_474;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_05_001 extends TestBase {

	@Test
	public void BCL802_05_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_474 col474 = new COL_474(driver);

		test = report.createTest(
				"BCL.802.05.001 - Verify CSR can Search ERN with select bankruptcy case status 'Active' and add Bankruptcy Case Activity for activity type incoming and activity name 'Telephone Call'");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		sleep(2000);
		//commonFuntions.ScrollMenu("Bankruptcy");
		//commonFuntions.clickMenu("Bankruptcy");
		col474.bankruptcynavBtn.click();
		sleep(1000);
		commonFuntions.ScrollMenu("Review/Update Bankruptcy Case Activity");
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass",
				"Review/Update Bankruptcy Case Activity");
		commonFuntions.clickMenu("Review/Update Bankruptcy Case Activity");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "COL-474 screen is displayed");

		test.info("Step: 4 -- ");
		String invalidFein = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		String invalidErn = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		String invalidBankruptcyCaseNumber = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))),
				7);

		commonFuntions.enterTextboxContains("a. Bankruptcy Case Number", invalidBankruptcyCaseNumber);
		commonFuntions.enterTextboxContains("b. Employer Registration Number", invalidErn);
		commonFuntions.enterTextboxContains(" d. FEIN ", invalidFein);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		Assert.assertTrue(col474.ERNOROwnerSSNORFEINErrorMessage.isDisplayed());
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "Error Message is displayed");

		test.info("Step: 5 -- ");
		commonFuntions.forceClearTextWithElement("b. Employer Registration Number");
		sleep(2000);
		commonFuntions.forceClearTextWithElement(" d. FEIN ");
		sleep(2000);
		commonFuntions.selectDropdown("2. Bankruptcy Status", " Active ");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		Assert.assertTrue(col474.noRecordFound.isDisplayed());
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "Error Message is displayed");

		test.info("Step: 6 -- ");
		commonFuntions.forceClearTextWithElement("a. Bankruptcy Case Number");
		sleep(1000);
		commonFuntions.selectDropdown("2. Bankruptcy Status", "--SELECT--");
		sleep(1000);
		commonFuntions.enterTextboxContains("a. Bankruptcy Case Number", "&64($!^(9");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		Assert.assertTrue(col474.noRecordFound.isDisplayed());
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "Error Message is displayed");

		test.info("Step: 7 -- ");
		commonFuntions.forceClearTextWithElement("a. Bankruptcy Case Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("c. Owner SSN", "111111111");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.errorLabel(" The entry for the SSN is invalid.");
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "Error Message is displayed");

		test.info("Step: 8 -- ");
		commonFuntions.forceClearTextWithElement("c. Owner SSN");
		sleep(1000);
		commonFuntions.enterTextboxContains("b. Employer Registration Number", "1111111");
		sleep(1000);
		commonFuntions.enterTextboxContains(" d. FEIN ", "123456789");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		Assert.assertTrue(col474.ERNOROwnerSSNORFEINErrorMessage.isDisplayed());
		commonFuntions.errorLabel(" The entry for the Federal Identification Number (FEIN) is invalid.");
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "Error Message is displayed");

		test.info("Step: 9&10 -- ");
		commonFuntions.forceClearTextWithElement("b. Employer Registration Number");
		sleep(1000);
		commonFuntions.forceClearTextWithElement(" d. FEIN ");
		sleep(1000);

		Map<String, String> databaseResults = col474.database_SelectQuery(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID");

		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String caseNumber = databaseResults.get("CaseNumber");
		System.out.println("The EAN Value is:" + feinValue);
		test.log(Status.INFO, "Ean::" + feinValue);
		System.out.println("The EAN Value is:" + eanValue);
		test.log(Status.INFO, "Ean::" + eanValue);
		System.out.println("The EAN Value is:" + caseNumber);
		test.log(Status.INFO, "Ean::" + caseNumber);

		commonFuntions.enterTextboxContains("a. Bankruptcy Case Number", caseNumber);
		sleep(2000);
		commonFuntions.selectDropdown("2. Bankruptcy Status", " Active ");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		Assert.assertTrue(col474.selectARecordErrorMsg.isDisplayed());
		commonFuntions.screenShot("Review/Update Bankruptcy Case Activity", "Pass", "Error Message is displayed");

		test.info("Step: 11 -- ");
		col474.selectFirstRadioBtn.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("View Bankruptcy Case Details/History", "Pass", "COL-475 screen is displayed");

		test.info("Step: 12 -- ");
		commonFuntions.clickButtonContains("Add New Activity ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add/Edit Activity - Bankruptcy Case Activity", "Pass",
				"COL-402 screen is displayed");

		test.info("Step: 13 -- ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("View Bankruptcy Case Details/History", "Pass", "Error Message is displayed");

		test.info("Step: 14 -- ");
		commonFuntions.selectRadioQuestions("1. Activity Type", "Incoming activity");
		commonFuntions.selectRadioQuestions("2. Is a response expected ?", "Yes ");
		commonFuntions.selectDropdown("3. Activity Name", " Other ");
		commonFuntions.enterTextboxContains("a. If Other,specify name", "Test");
		commonFuntions.enterTextboxContains("4. Remarks", "remarks");
		commonFuntions.enterPastDate("5. Activity Date",1);
		commonFuntions.selectRadioQuestions("6. Is this a Response to existing activity?", "No ");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add/Edit - Activity Verification", "Pass", "COL-513 screen is displayed");
		
		test.info("Step: 15 -- ");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add/Edit Activity Confirmation", "Pass", "SUC-002 screen is displayed");
		
		test.info("Step: 16 -- ");
		commonFuntions.clickButton("Home ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Homepage", "Pass", "Home screen is displayed");
		
	}

}
