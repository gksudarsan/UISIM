package com.employerContibution.EL;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_039_001 extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EL.039.001 - Verify CSR can view and create a non-liable employer UI account.", groups = {COMMON_CONSTANT.REGRESSION})
	public void Test_EL_039_001() throws Exception {
		
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		test = report.createTest("EL.039.001 - Verify CSR can view and create a non-liable employer UI account.");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_3.toUpperCase(), COMMON_CONSTANT.CSR_USER_3_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);
		
		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFunctions.screenShot("Menu", "Pass", "Manage PEO");
		
		
	}
}
