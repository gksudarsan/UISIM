package com.employerContibution.EM;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.pages.HomePage;
import com.ui.pages.SRGE_543;
import com.ui.pages.SRGE_544;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;

public class EM_449_001 extends TestBase {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase {

		String EAN = prop.getProperty("EAN");

		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			String EAN = "0000160";
			test = report.createTest("Logged into EC Application");
			commonStepDefinitions stepDef = new commonStepDefinitions();
			SRGE_543 srge543Page = new SRGE_543(driver);
			SRGE_544 srge544Page = new SRGE_544(driver);
			SUC_002 suc002Page = new SUC_002(driver);
			HomePage home = new HomePage(driver);
			
			test.log(Status.INFO, "Logging to the application");
			stepDef.login("ndfjp3", "Admin@12345678");
			test.log(Status.PASS, "Sucessfully login to the application");
			test.log(Status.INFO, "Navigating to the account maintenance info tab");
			home.navigateToAccountMaintenance();
			srge543Page.checkRequiredText();
			srge543Page.enterEANNumber(EAN);
			srge544Page.submitWithoutDetails();
			srge544Page.enterDetails();
			srge544Page.checkFilter();
			srge544Page.clickSubmit();
			suc002Page.validateSucessMessage();

		}
	}
}
