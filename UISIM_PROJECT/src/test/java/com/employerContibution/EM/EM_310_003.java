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
import com.ui.pages.SUC_002;
import com.ui.utilities.ConstantData;

import stepDefinitions.commonStepDefinitions;

public class EM_310_003 extends TestBase{
	
	@Listeners(com.ui.utilities.ListenerTest.class)
	public class EM_310_003_ extends TestBase {
		String EAN = prop.getProperty("EAN");
		
		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void EM_310() throws Exception {
			test = report.createTest("EM.310.003 - Verify CSR is able to process sale of business when sale of business is denial.");
			commonStepDefinitions cf= new commonStepDefinitions();
			HomePage home = new HomePage(driver);
			EM_005 em005 = new EM_005(driver);
			SREG_503 sreg503 = new SREG_503(driver);
			SREG_504 sreg504 = new SREG_504(driver);
			SUC_002 suc002 = new SUC_002(driver);
			test.log(Status.INFO, "Logging to the application");
			cf.login(prop.getProperty("CSR_UserID") , prop.getProperty("CSR_Pass"));
			test.log(Status.PASS, "Login Success");
			test.log(Status.INFO, "Navigating to Sales of Business tab");
			home.navigateToSaleOfBussiness();
			test.log(Status.PASS, "Navigated to Sales of Business tab");
			test.log(Status.INFO, "Entering the ERN Number");
			em005.enterDetailInERNField(prop.getProperty("EAN_EM_310_02"));
			test.log(Status.PASS, "Entered the ERN Number");
			test.log(Status.INFO, "Entering the form data");
			cf.selectRadio("No");
			cf.enterTextbox("Successor", "0451789");
			cf.clickButtonContains("Search");
			cf.selectRadio("Part");
			
			cf.selectDropdown("Source", " Correspondence/Email ");sleep(2000);
			cf.selectDropdown("Source Type", " Correspondence/Email ");sleep(2000);
			cf.screenShot("Menu","Pass","");
			cf.clickButtonContains("Continue");sleep();
			cf.screenShot("Menu","Pass","");
			test.log(Status.PASS, "Clicked on close button");
		
		
		}
	}	
}
