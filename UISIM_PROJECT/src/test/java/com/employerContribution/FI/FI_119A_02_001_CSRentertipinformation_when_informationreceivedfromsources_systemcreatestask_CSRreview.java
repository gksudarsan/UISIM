package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_119A_02_001_CSRentertipinformation_when_informationreceivedfromsources_systemcreatestask_CSRreview
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "FI.119A.02.001 .Verify CSR can enter tip information when information received from sources and system creates task for CSR review.", groups = "Regression")
	public void FI_119A_02_001() throws Exception {

		test = report.createTest(
				"FI.119A.02.001 .Verify CSR can enter tip information when information received from sources and system creates task for CSR review.");
		//String ernNum = "5454645";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		//FIpage filocators	= new FIpage(driver);

		// ---Login---
		//commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.login(COMMON_CONSTANT.LnDSpecialist_User, COMMON_CONSTANT.LnDSpecialist_User_Pwd);
		test.log(Status.PASS, "Login with CSR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Report UI Employer/Claimant Fraud");
		commonFuntions.clickMenu("Report UI Employer/Claimant Fraud");
		sleep();

		// ---Report UI Employer/Claimant Fraud-FTM-100---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Employer_Claimant_Fraud", "Pass", "Employer_Claimant_Fraud_FTM_100");
		commonFuntions.selectDropdown("Source", "Shared Mailbox");
		sleep();
		commonFuntions.screenShot("REmployer_Claimant_FraudFTM100", "Pass", "Details Entered");
		commonFuntions.clickButton("Submit ");sleep(2);

		// ---Report UI Employer/Claimant Fraud - Manual Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Employer_Claimant_FraudConfirmation", "Pass", "Employer_Claimant_Fraud_Manual_Confirmation-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("Redirected to Home Screen", "Pass", "Test Case FI_119A_02_001 got Passed back to Home ");

		/*-Test Case Completed-*/
		
		
	}
}