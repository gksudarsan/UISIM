package com.employerContibution.EM;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_503;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_310_001 extends TestBase {
	String EAN = prop.getProperty("EAN");
	@Test(priority = 1, description = "Test sample", groups = { "Regression" })
	public void Testing123() throws Exception {
		String EAN = "9300014";
		test = report.createTest("Logged into EC Application");
		commonStepDefinitions stepDef = new commonStepDefinitions();
		EM_005 em005Page = new EM_005(driver);
	    SREG_503 sreg503Page = new SREG_503(driver);
	    
		HomePage home = new HomePage(driver);
		test.log(Status.INFO, "Logging to the application");
		stepDef.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Sucessfully login to the application");
		test.log(Status.INFO, "Navigating to the account maintenance info tab");
		home.navigateToSaleOfBussiness();
		//em005Page.checkKeepBlankErrorMessage();
		//em005Page.checkEanNotExit(EAN);
		//em005Page.enterValidEANNumber(EAN);
		sreg503Page.validateScreenIdText();
		sreg503Page.validateEanMessageText();
		sreg503Page.validateEanMessageText2();
		
	}
}
