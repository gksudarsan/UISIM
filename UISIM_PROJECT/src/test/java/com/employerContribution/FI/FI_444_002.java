package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_444_002 extends TestBase {

	@Test(priority = 1, description = "FI.444.002. Verify CSR can view the Fraud penalty details and take a decision to abate the Fraud penalty. (When CSR Deny task '“Review Penalty Abatement Request”)", groups = {
			"Regression" })
	public void FI_444_002() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"FI.444.002. Verify CSR can view the Fraud penalty details and take a decision to abate the Fraud penalty. (When CSR Deny task '“Review Penalty Abatement Request”)");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("Menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Penalty");
		commonFunctions.clickMenu("Penalty");
		commonFunctions.ScrollMenu("Penalty Menu");
		commonFunctions.clickMenu("Penalty Menu");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIP–001---
		commonFunctions.screenShot("Penalty Menu - Enter ERN", "Pass",
				"Penalty Menu - Enter ERN (FIP–001)screen launched");
		commonFunctions.enterTextboxContains("Employer Registration Number", "4763312");
		commonFunctions.screenShot("Penalty Menu - Enter ERN", "Pass", "ERN Entered");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIP-002---
		commonFunctions.screenShot("Select Penalty", "Pass", "Select Penalty (FIP-002)screen launched");
		try {

			BCL.select_Penalty.click();
		} catch (Exception e) {
			e.printStackTrace();

		}
		commonFunctions.screenShot("Select Penalty", "Pass", "Record selected on (FIP-002)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIP-014---
		commonFunctions.screenShot("Fraud Penalty Summary", "Pass", "Fraud Penalty Summary(FIP-014)screen launched");
		System.out.println();
		try {
			BCL.select_Penalty.click();
		} catch (Exception e) {
			e.printStackTrace();

		}
		commonFunctions.selectCheckbox("Abate Penalty?");
		BCL.ResolutionDetails_FraudPenaltySummary.sendKeys("For Testing");
		commonFunctions.screenShot("Fraud Penalty Summary", "Pass", "Record selected on (FIP-014)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIP-009---
		commonFunctions.screenShot("Fraud Penalty Verification", "Pass",
				"Fraud Penalty Verification (FIP-009)screen launched");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Fraud Penalty Confirmation", "Pass",
				"Fraud Penalty Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		commonFunctions.clickButton(" Go to My Q ");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- WF-001 ---
		commonFunctions.screenShot("Individual Work Queue", "Pass", "Successfully launched to WF-001 page");
		commonFunctions.enterTextboxContains("Employer Registration Number", "4763312");
		commonFunctions.clickButton(" Search ");
		commonFunctions.clickOnLink("Review Penalty Abatement Request Task");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- WF-001 ---
		commonFunctions.screenShot("Work Item Details", "Pass", "Successfully launched to WF-001 page");
		commonFunctions.clickButtonContains("Open Work Item ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---PFP-007---
		commonFunctions.screenShot("Review Penalty Abatement Request", "Pass", "Successfully launched to PFP-007 page");
		commonFunctions.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		commonFunctions.selectRadioQuestions("Select Action", "Deny");
		BCL.ResolutionDetails_ReviewPenaltyAbatementRequest.sendKeys("FOR TESTING");
		commonFunctions.screenShot("Review Penalty Abatement Request", "Pass", "Details Entered on PFP-007 page");
		commonFunctions.clickButton("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Task Confirmation", "Pass", "Task Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");

		// Done
	}

}
