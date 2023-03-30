package com.employerContibution.EM;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_541;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_414_001 extends TestBase{
	
	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase {
		String EAN = prop.getProperty("EAN");
		
		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			String EAN = "0000176";
			test = report.createTest("Logged into EC Application");
			commonStepDefinitions stepDef = new commonStepDefinitions();
			SREG_541 sreg541Page=new SREG_541(driver);
			//SUC_002 suc002Page=new SUC_002(driver);
			
			HomePage home = new HomePage(driver);
			stepDef.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			home.navigateToVoidTransfer();
			sreg541Page.validateRequiredText();
			sreg541Page.registerEANNumber(EAN);
			sreg541Page.checkEANData();
			sreg541Page.validateErrorMessagesPopUp();
			sreg541Page.validateCommentErrorMessagePop();
			sreg541Page.enterRequiredFields();
			sreg541Page.clickVoidTransfer();
			
		}
	}

}
