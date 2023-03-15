package com.employerContibution.EM;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_492;
import com.ui.pages.SREG_503;
import com.ui.pages.SREG_504;
import com.ui.pages.SUC_002;

import stepDefinitions.commonStepDefinitions;

public class EM_453_01_001_Update_Joint_Account_Admin_Info extends TestBase {

	
	@Test(priority = 1, description = "Test sample", groups = { "Regression" })
	public void Testing123() throws Exception {
		
		test = report.createTest("EM.453.01.001 - Verify CSR is able to search ERN and update joint account administrator information.");
		commonStepDefinitions stepDef = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		SREG_492 sreg492 = new SREG_492(driver);
		
		test.log(Status.INFO, "Logging to the application");
		stepDef.login(prop.getProperty("CSR_UserID") , prop.getProperty("CSR_Pass"));
		test.log(Status.PASS, "Login Success");
		home.navigateToMaintainJointAccount();
		sreg492.enterERNAndJointAccountNumber();
		
	}
}