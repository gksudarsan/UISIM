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

public class BSP_540_02_001_Verify_the_Claimant_is_allowed_to_successfully_file_a_TRA extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify the Claimant is allowed to successfully file a TRA claim and gets approved based on business rules evaluation with reason of separation as Lack of Work/Laid off.", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void BSP_540_02_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		BSPPage bspPage = new BSPPage(driver);
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
				"BSP-540-02-001 - Verify the Claimant is allowed to successfully file a TRA claim and gets approved based on business rules evaluation with reason of separation as Lack of Work/Laid off.");
		sleep(2000);

		test.info("Step: 1 -- ");
		commonFuntions.benefitsLogin(COMMON_CONSTANT.TRASPECIALIST, COMMON_CONSTANT.TRASPECIALIST_PASSWORD);
		test.log(Status.PASS, "Login with Basic Benefits Inquiry is successful");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 2 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("TRA Entitlement");
		commonFuntions.clickMenu("TRA Entitlement");
		commonFuntions.ScrollMenu("ATAA/RTAA Claim");
		commonFuntions.clickMenu("ATAA/RTAA Claim");
		commonFuntions.screenShot("Homepage", "Pass", "TRA Entitlement in nav bar");
		sleep(1000);
		commonFuntions.ScrollMenu("File ATAA/RTAA Claim");
		commonFuntions.clickMenu("File ATAA/RTAA Claim");
		sleep(1000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("File ATAA RTAA Claim", "Pass", "ATA-033 screen is displayed");

		test.info("Step: 3 -- ");

	}

}
