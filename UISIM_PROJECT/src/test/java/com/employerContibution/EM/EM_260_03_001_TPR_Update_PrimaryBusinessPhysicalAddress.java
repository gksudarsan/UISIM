package com.employerContibution.EM;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_03_001_TPR_Update_PrimaryBusinessPhysicalAddress extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR is able to update TPR(Own) address for address type 'Primary business physical address'", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_260_02_002() throws Exception{
		
		test = report.createTest("Verify TPR is able to update TPR(Own) address for address type 'Primary business physical address'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement empManagement = new employerManagement();
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_2.toUpperCase(), COMMON_CONSTANT.TPR_USER_2_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Maintain Address");
		commonFunction.clickMenu("Maintain Address");
		
		// --- SREG-070 ---
		sleep(2000);
		commonFunction.screenShot("MenuNavigation", "Pass", "Successful launch to Maintain Address – Enter ERN(SREG-070) page");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("EM26003001", "Pass", "Continue blocked due to blank ERN");
		commonFunction.enterTextbox("Employer Registration Number", "A#@$RF12");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("EM26003001", "Pass", "Continue blocked due to invalid ERN");
		commonFunction.enterTextbox("Employer Registration Number", "");
		sleep(2000);
		commonFunction.enterTextbox("Employer Registration Number", "4772");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("EM26003001", "Pass", "Continue blocked due to invalid ERN-minimum 7 characters");
		commonFunction.enterTextbox("Employer Registration Number", "");
		sleep(2000);
		commonFunction.enterTextbox("Employer Registration Number", "4456777");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.enterTextbox("Employer Registration Number", "");
		sleep(2000);
		
		commonFunction.enterTextbox("Employer Registration Number", "0000129");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("EM26003001", "Pass", "Continue blocked due to no power of attorney");
		
		
		System.out.println("Pass");
	}
}
