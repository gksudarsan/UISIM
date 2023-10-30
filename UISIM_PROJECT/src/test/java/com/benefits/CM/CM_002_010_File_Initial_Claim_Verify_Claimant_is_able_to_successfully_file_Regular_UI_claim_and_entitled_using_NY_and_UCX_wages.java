package com.benefits.CM;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.pages.ClaimantManagementLocators;

import stepDefinitions.commonStepDefinitions;

public class CM_002_010_File_Initial_Claim_Verify_Claimant_is_able_to_successfully_file_Regular_UI_claim_and_entitled_using_NY_and_UCX_wages
		extends TestBase {
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify Claimant is able to successfully file Regular UI claim and entitled using NY and UCX wages", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void CM_002_010() throws Exception {

		test = report.createTest(
				"CM.002.010 - File Initial Claim - Verify Claimant is able to successfully file Regular UI claim and entitled using NY and UCX wages");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		ClaimantManagementLocators cmlPage = new ClaimantManagementLocators(driver);

		test.info("Step: 1 -- ");
		commonFuntions.benefitsLogin(COMMON_CONSTANT.CLAIMANT_REPRESENTATIVE_1, COMMON_CONSTANT.CLAIMANT_REPRESENTATIVE_1);
		test.log(Status.PASS, "Login with Basic Benefits Inquiry is successful");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		
		test.info("Step: 2 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("File New Unemployment Insurance Claim");
		sleep(2000);
		commonFuntions.screenShot("Nav Bar", "Pass", "File New Unemployment Insurance Claim navigation bar");
		commonFuntions.clickMenu("File New Unemployment Insurance Claim");
		
		commonFuntions.ScrollMenu("File for Unemployment Insurance");
		commonFuntions.clickMenu("File for Unemployment Insurance");
		
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Claim Filing Notification", "Pass", "CIN-044 Screen Is Displayed");
		
		test.info("Step: 3 -- ");
		sleep(1000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("EQUAL EMPLOYMENT OPPORTUNITY(EEO) INFORMATION", "Pass", "CIN-250 Screen Is Displayed");
		
		test.info("Step: 4 -- ");
		commonFuntions.clickButtonContains("Agree & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Secure Your UI Account Screen", "Pass", "CMS-001 Screen Is Displayed");
		
		test.info("Step: 5 -- ");
		sleep(1000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Secure Your UI Account Screen", "Pass", "Required Error On CMS-001 Screen Is Displayed");
		//
		sleep(1000);
		String secretPhrase = "ph"+commonFuntions.createRandomString();
		sleep(2000);
		commonFuntions.enterTextboxContains("Please enter a secret phrase that will be used to help keep your account secure", secretPhrase);
		commonFuntions.enterTextboxContains("Confirm the secret phrase that will be used to help keep your account secure", secretPhrase);
		commonFuntions.enterTextboxContains("Please enter a secret phrase hint that will be displayed to help you remember your secret phrase", "ph");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Eligibility Review", "Pass", "CIN-200 Screen Is Displayed");
		
		test.info("Step: 6 -- ");
		sleep(1000);
		commonFuntions.selectRadioQuestions("Do you want to change the Claim Effective Date/Start Date?", "No ");
		cmlPage.amountEarnedFilingWeekIdFieldCIN200.sendKeys("3");
		
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("", "Pass", "");

	}

}
