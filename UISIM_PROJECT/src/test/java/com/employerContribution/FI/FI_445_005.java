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
public class FI_445_005 extends TestBase {

	@Test(priority = 1, description = "FI.445.005.Verify CSR can view the BCP penalty details in order to determine if it is cancelled when cancel code is 'Administrative Error/Decision'", groups = {
			"Regression" })
	public void FI_445_005() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"FI.445.005.Verify CSR can view the BCP penalty details in order to determine if it is cancelled when cancel code is 'Administrative Error/Decision'");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Penalty");
		commonFunctions.clickMenu("Penalty");
		commonFunctions.ScrollMenu("Penalty Menu");
		commonFunctions.clickMenu("Penalty Menu");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIP–001---
		commonFunctions.screenShot("Penalty Menu - Enter ERN", "Pass",
				"Penalty Menu - Enter ERN (FIP–001)screen launched");
		commonFunctions.enterTextboxContains("Employer Registration Number", eanValue);
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

		// ---FIP-005---
		commonFunctions.screenShot("Benefit Claim Penalty Summary", "Pass",
				"Benefit Claim Penalty Summary (FIP-005)screen launched");
		try {

			BCL.select_Penalty.click();
		} catch (Exception e) {
			e.printStackTrace();

		}
		commonFunctions.screenShot("Benefit Claim Penalty Summary", "Pass", "Record selected on (FIP-005)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIP-006---
		commonFunctions.screenShot("Benefit Claim Penalty Details", "Pass",
				"Benefit Claim Penalty Details (FIP-006)screen launched");
		BCL.select_cancelCode.click();
		BCL.Resolution_Details.sendKeys("For Testing");
		commonFunctions.screenShot("Benefit Claim Penalty Details", "Pass", "Details selected on (FIP-006)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Benefit Claim Penalty Details", "Pass",
				"Message 'Required'for (If Cancel a BCP, Select cancel code) on (FIP-006)screen");
		commonFunctions.selectDropdownUsingSearch("", " Administrative Error/Decision ");
		commonFunctions.screenShot("Benefit Claim Penalty Details", "Pass", "Details selected on (FIP-006)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIP-007---
		commonFunctions.screenShot("Benefit Claim Penalty Verification", "Pass",
				"Benefit Claim Penalty Verification (FIP-007)screen launched");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Benefit Claim Penalty Confirmation", "Pass",
				"Benefit Claim Penalty Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		commonFunctions.waitForLoadingIconToDisappear();
		// Done
	}

}
