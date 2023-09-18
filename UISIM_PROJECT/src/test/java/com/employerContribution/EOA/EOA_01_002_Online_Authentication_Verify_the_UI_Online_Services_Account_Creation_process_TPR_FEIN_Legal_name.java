package com.employerContribution.EOA;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_01_002_Online_Authentication_Verify_the_UI_Online_Services_Account_Creation_process_TPR_FEIN_Legal_name
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the UI Online Services Account Creation process (TPR) - (FEIN, Legal name)", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_01_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		employerManagement empmanagementPage = new employerManagement(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		EOAPage eoaPage = new EOAPage(driver);
		

		test = report.createTest(
				"EOA_01_002_Online_Authentication_Verify_the_UI_Online_Services_Account_Creation_process_TPR_FEIN_Legal_name");

		commonFuntions.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		test.info("Step: 1 -- ");
		commonFuntions.screenShot("UI Services Account Creation", "Pass", "UI Services Account Creation Popup is displayed");
		sleep(1000);
		
		test.info("Step: 2 -- ");
		eoaPage.tprRepresentativeRadio.click();
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("UI Services Third Party Representative Account Creation", "Pass", "SREG-612 screen is displayed");
		
		test.info("Step: 3 -- ");
		
		
	}

}
