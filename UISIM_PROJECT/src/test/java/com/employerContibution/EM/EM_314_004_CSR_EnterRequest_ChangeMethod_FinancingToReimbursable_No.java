package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_314_004_CSR_EnterRequest_ChangeMethod_FinancingToReimbursable_No extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter request for change method of financing  to reimbursable with select option No.", groups = { COMMON_CONSTANT.REGRESSION })
	public void TC_EM_314_004() throws Exception {
		
		test = report.createTest("Verify CSR is able to enter request for change method of financing  to reimbursable with select option No.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement empManagement = new employerManagement(driver);
		

		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.ScrollMenu("Change in Method of Financing");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Change in Method of Financing");
		commonFunction.clickMenu("Change in Method of Financing");
		
		// --- ETR-228 ---
		sleep(2000);
		commonFunction.screenShot("EM314004", "Pass", "Successful launch to Change in Method of Financing(ETR-228) page");
		commonFunction.enterTextboxContains("Employer Registration Number", "1371365");
		commonFunction.clickButtonContains("Continue ");
		
		System.out.println("xxx");
		
		
	}

}
