package com.employerContibution.EM;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.SRGE_543;
import com.ui.pages.SRGE_544;
import com.ui.pages.SUC_002;

import stepDefinitions.commonStepDefinitions;

public class EM_310_002 {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase {

		String EAN = prop.getProperty("EAN");

		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			
			test = report.createTest("Logged into EC Application");
			commonStepDefinitions stepDef = new commonStepDefinitions();
			HomePage home = new HomePage(driver);
			
			test.log(Status.INFO, "Logging to the application");
			stepDef.login("ndfjp3", "Admin@12345678");
			home.navigateToSaleOfBussiness();
			

		}
	}
}
