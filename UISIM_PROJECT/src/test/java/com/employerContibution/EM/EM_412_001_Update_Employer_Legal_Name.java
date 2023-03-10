package com.employerContibution.EM;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_027;
import com.ui.pages.SREG_030;
import com.ui.pages.SREG_503;
import com.ui.pages.SREG_504;
import com.ui.pages.SUC_002;

import stepDefinitions.commonStepDefinitions;

public class EM_412_001_Update_Employer_Legal_Name extends TestBase{


	@Test(priority = 1, description = "Test sample", groups = { "Regression" })
	public void Testing123() throws Exception {
		
		test = report.createTest("EM.412.001- Verify CSR is able to update employer legal name of business for employer type 'Business'");
		commonStepDefinitions stepDef = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		SREG_030 sreg030 = new SREG_030(driver);
		SREG_027 sreg27 = new SREG_027(driver);
		SUC_002 suc002 = new SUC_002(driver);
		
		
		test.log(Status.INFO, "Logging to the application");
		stepDef.login(prop.getProperty("CSR_UserID"), prop.getProperty("SCR_Pass"));
		test.log(Status.PASS, "Logged in to the application");
		test.log(Status.INFO, "Navigating to Maintain Accounts");
		home.navigateToMaintainAccounts();
		test.log(Status.PASS, "Navigated to Maintain Accounts");
		test.log(Status.INFO, "Entering ERN number");
		sreg27.enterERNNumber();
		test.log(Status.PASS, "Entered the ERN number successfully");
		test.log(Status.INFO, "Validating page title");
		sreg030.validatePageTitle();
		test.log(Status.PASS, "Validated page title");
		test.log(Status.INFO, "Entering form data and validating pre populated");
		sreg030.fillFormAndValidate();
		test.log(Status.PASS, "Entered form data and validated pre populated");
		sreg030.clickSubmit();
		test.log(Status.PASS, "Submit button clicked");
		suc002.validateEmployerAccountMSG();
		test.log(Status.PASS, "Success message validated");
		test.log(Status.PASS, "Test case completed successfully");
		
	}
}
