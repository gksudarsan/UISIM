package com.employerContibution.EM;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_503;
import com.ui.pages.SREG_504;
import com.ui.pages.SRGE_543;
import com.ui.pages.SRGE_544;
import com.ui.pages.SUC_002;
import com.ui.utilities.ConstantData;

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
			EM_005 em005 = new EM_005(driver);
			SREG_503 sreg503 = new SREG_503(driver);
			SREG_504 sreg504 = new SREG_504(driver);
			SUC_002 suc002 = new SUC_002(driver);
			
			test.log(Status.INFO, "Logging to the application");
			stepDef.login("ndfjp3", "Admin@12345678");
			home.navigateToSaleOfBussiness();
			em005.enterDetailInERNField(prop.getProperty("EAN_EM_310_02"));
			sreg503.fillFormDetails();
			sreg504.verifyPageName(ConstantData.SALE_BUSINESS_DETAIL_PAGE_NAME);
			sreg504.verifyFilterValues();
			sreg504.clickSubmitButton();
			Assert.assertEquals(ConstantData.SALE_BUSINESS_SUCCESS_TEXT, suc002.validateSaleOfBusinessText());
			suc002.validateHomeButton();
		}
	}
}
