package com.benefits.BSP;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BSPPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BSP_700_01_001_Verify_that_the_CSR_is_allowed_to_successfully_enter_a_DUA_declaration_in_the_system extends TestBase{
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify_that_the_CSR_is_allowed_to_successfully_enter_a_DUA_declaration_in_the_system", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void BSP_700_01_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		BSPPage bcpPage = new BSPPage(driver);
		employerManagement empmanagementPage = new employerManagement(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);

		test = report.createTest(
				"BSP-700-01-001 - Verify that the CSR is allowed to successfully enter a DUA declaration in the system.");
		sleep(2000);

		test.info("Step: 1 -- ");
		commonFuntions.benefitsLogin(COMMON_CONSTANT.DUA, COMMON_CONSTANT.DUA_PASSWORD);
		test.log(Status.PASS, "Login with Basic Benefits Inquiry is successful");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 2 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Benefit Maintenance");
		commonFuntions.clickMenu("Benefit Maintenance");
		commonFuntions.ScrollMenu("DUA Declaration");
		commonFuntions.clickMenu("DUA Declaration");
		commonFuntions.screenShot("Homepage", "Pass", "Enter DUA Declaration in nav bar");
		sleep(1000);
		commonFuntions.ScrollMenu("Enter DUA Declaration");
		commonFuntions.clickMenu("Enter DUA Declaration");
		sleep(1000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Enter DUA Declaration", "Pass", "CIN-601 screen is displayed");
		
		test.info("Step: 3 -- ");
		
	}

}
