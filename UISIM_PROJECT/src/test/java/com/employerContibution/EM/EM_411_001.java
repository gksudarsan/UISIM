package com.employerContibution.EM;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_434;
import com.ui.pages.SREG_435;
import com.ui.pages.SUC_002;

import stepDefinitions.commonStepDefinitions;

public class EM_411_001 extends TestBase{

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase {
		String EAN = prop.getProperty("EAN");
		
		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			String EAN = "0000022";
			test = report.createTest("EM.411.001.Verify CSR is able to update account status of employer account 'Liable'");
			commonStepDefinitions stepDef = new commonStepDefinitions();
			SREG_434 sreg434Page=new SREG_434(driver);
			SREG_435 sreg435Page=new SREG_435(driver);
			SUC_002 suc002Page=new SUC_002(driver);
			
			HomePage home = new HomePage(driver);
			stepDef.login(prop.getProperty("CSR_UserID"), prop.getProperty("CSR_Pass"));
			test.log(Status.PASS, "User logged in successfully");
			test.log(Status.INFO, "Navigating to Maintenance account Status");
			home.navigateToAccountMaintenance();
			test.log(Status.PASS, "Navigated to Maintenance account Status");
			sreg434Page.checkRequiredText();
			test.log(Status.PASS, "Required text verified");
			test.log(Status.INFO, "Enter ERN number");
			sreg434Page.enterEANNumber(EAN);
			test.log(Status.PASS, "Entered ERN number");
			test.log(Status.INFO, "Entering required data");
			sreg435Page.enterRequiredFields();
			test.log(Status.PASS, "Entered required data");
			sreg435Page.clickSubmit();
			test.log(Status.PASS, "Submit button clicked");
			suc002Page.validateSucessMessage();
			test.log(Status.PASS, "Success message verified");
			test.log(Status.PASS, "Test case completed successfully");
					
		}
	}
}
