package com.benefits.USDOL;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.USDepartmentOfLaborLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class USDOL_059_CsrAddBtqQuarter_ReviewSeedNo_SubmitSampleDetails extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can add BTQ Quarterly Review Seed Number and submit sample details (no data exists in the system).", groups = COMMON_CONSTANT.REGRESSION)
	public void TC_USDOL_059() throws Exception {
	
	test = report.createTest("USDOL.059 - Verify CSR can add BTQ Quarterly Review Seed Number and submit sample details (no data exists in the system).");
	
	test.log(Status.INFO, "TC Script developed by Ankan Das");
	
	commonStepDefinitions commonFunctions = new commonStepDefinitions();
	USDepartmentOfLaborLocators usdolLocators = new USDepartmentOfLaborLocators(driver);
	
	commonFunctions.benefitsLogin(COMMON_CONSTANT.BTQ_SUPERVISOR.toUpperCase(), COMMON_CONSTANT.BTQ_SUPERVISOR_PASSWORD);
	test.log(Status.PASS, "Login with Basic Benefits Access role is successful.");
	
	// -- navigation --
	commonFunctions.waitForLoadingIconToDisappear();
	usdolLocators.menu.click();
	commonFunctions.clickMenu("Review");
	sleep();
	commonFunctions.screenShot("MenuPage", "Pass", "Navigation : Menu -> Review -> Add BTQ Seed Numbers");
	commonFunctions.clickMenu("Add BTQ Seed Numbers");
	
	// --- AUD-101 ---
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("USDOL059", "Pass", "Successfully launched to Add BTQ Quarterly Review Seed Number(AUD-101) screen");
	commonFunctions.clickButton("Continue ");
	sleep(2000);
	commonFunctions.screenShot("USDOL059", "Pass", "Error for mandatory fields, on clicking Continue without data on AUD-101 screen");
	
	usdolLocators.nonMonNonSepDropdown.click(); sleep();
	usdolLocators.value_2.click(); sleep(2000);
	usdolLocators.nonSepYearId.sendKeys("2023");  
	usdolLocators.nonSepSeedNo.sendKeys("7567"); sleep(2000);
	
	usdolLocators.nonMonSepDropdown.click(); sleep();
	usdolLocators.value_2.click(); sleep(2000);
	usdolLocators.sepYearId.sendKeys("2023"); sleep(2000);
	usdolLocators.sepSeedNo.sendKeys("8767"); sleep(2000);
	
	commonFunctions.screenShot("USDOL059", "Pass", "Data entered on AUD-101 screen");
	commonFunctions.clickButton("Continue ");
	
	// --- AUD-003 ---
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.screenShot("USDOL059", "Pass", "Successfully launched to Add BTQ Quarterly Review Seed Number(AUD-003) screen");
	commonFunctions.clickButton("Submit ");
	
	// --- SUC-002 ---
	commonFunctions.waitForLoadingIconToDisappear();
	commonFunctions.Label("SUC-002");
	commonFunctions.screenShot("USDOL059", "Pass", "Successfully launched to Cancel Claim Confirmation(SUC-002) screen");
	commonFunctions.clickButton("Home ");
	
	commonFunctions.screenShot("USDOL059", "Pass", "TC USDOL.059 passed successfully.");
	}

}
