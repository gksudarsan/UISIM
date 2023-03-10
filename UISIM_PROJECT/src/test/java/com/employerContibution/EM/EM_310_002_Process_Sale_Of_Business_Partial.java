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

public class EM_310_002_Process_Sale_Of_Business_Partial {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase {

		String EAN = prop.getProperty("EAN");

		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			
			test = report.createTest("EM.310.002 - Verify CSR is able to process sale of business and indicate transfer 'Partial'.");
			commonStepDefinitions stepDef = new commonStepDefinitions();
			HomePage home = new HomePage(driver);
			EM_005 em005 = new EM_005(driver);
			SREG_503 sreg503 = new SREG_503(driver);
			SREG_504 sreg504 = new SREG_504(driver);
			SUC_002 suc002 = new SUC_002(driver);
			
			test.log(Status.INFO, "Logging to the application");
			stepDef.login(prop.getProperty("SCR_UserID") , prop.getProperty("CSR_Pass"));
			test.log(Status.PASS, "Login Success");
			test.log(Status.INFO, "Navigating to Sales of Business tab");
			home.navigateToSaleOfBussiness();
			test.log(Status.PASS, "Navigated to Sales of Business tab");
			test.log(Status.INFO, "Entering the ERN Number");
			em005.enterDetailInERNField(prop.getProperty("EAN_EM_310_02"));
			test.log(Status.PASS, "Entered the ERN Number");
			test.log(Status.INFO, "Entering the form data");
			sreg503.fillFormDetails();
			test.log(Status.PASS, "Form filledand click continue");
			test.log(Status.INFO, "verifying page name");
			sreg504.verifyPageName(ConstantData.SALE_BUSINESS_DETAIL_PAGE_NAME);
			test.log(Status.PASS, "verifyied page name");
			test.log(Status.INFO, "verifying filters");
			sreg504.verifyFilterValues();
			test.log(Status.INFO, "verifyied filters");
			sreg504.clickSubmitButton();
			test.log(Status.PASS, "Submit button clicked");
			test.log(Status.INFO, "verifying success text");
			Assert.assertEquals(ConstantData.SALE_BUSINESS_SUCCESS_TEXT, suc002.validateSaleOfBusinessText());
			test.log(Status.PASS, "verifyied success text");
			suc002.validateHomeButton();
			test.log(Status.PASS, "verifyied home button");
			test.log(Status.PASS, "Test completed sucessfully");
		}
	}
}
