package com.employerContibution.EM;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
			test = report.createTest("Logged into EC Application");
			commonStepDefinitions stepDef = new commonStepDefinitions();
			SREG_434 sreg434Page=new SREG_434(driver);
			SREG_435 sreg435Page=new SREG_435(driver);
			SUC_002 suc002Page=new SUC_002(driver);
			
			HomePage home = new HomePage(driver);
			stepDef.login("NDFJP3", "Admin@12345678");
			home.navigateToAccountMaintenance();
			sreg434Page.checkRequiredText();
			sreg434Page.enterEANNumber(EAN);
			
			sreg435Page.enterRequiredFields();
			sreg435Page.clickSubmit();
			suc002Page.validateSucessMessage();
					
		}
	}
}
