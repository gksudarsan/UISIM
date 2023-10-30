package com.benefits.PMT;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BSPPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class PA_005_415_ED_single_week_cert_DUA_Yes_to_Worked_During_Week_Verify_System_allow_CSR extends TestBase {
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "ED single week cert DUA - Yes to Worked During Week- Verify System allow CSR to file a weekly certification for DUA  ED claim when the  claimant worked during the week", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void PA_005_415() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		BSPPage bspPage = new BSPPage(driver);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);

		test = report.createTest(
				"PA.005.415 - ED single week cert DUA - Yes to Worked During Week- Verify System allow CSR to file a weekly certification for DUA  ED claim when the  claimant worked during the week");
		sleep(2000);

		test.info("Step: 1 -- ");
		commonFuntions.benefitsLogin(COMMON_CONSTANT.BASIC_BENEFITS_ACCESS, COMMON_CONSTANT.BASIC_BENEFITS_ACCESS_PASSWORD);
		test.log(Status.PASS, "Login with Basic Benefits Inquiry is successful");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 2 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Weekly Activities");
		commonFuntions.clickMenu("Weekly Activities");
		commonFuntions.screenShot("Homepage", "Pass", "Weekly Activities in nav bar");
		sleep(1000);
		commonFuntions.ScrollMenu("File Weekly Certification for Benefits");
		commonFuntions.clickMenu("File Weekly Certification for Benefits");
		sleep(1000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("File Weekly Certification(s) - Enter SSN", "Pass", "WC-001 screen is displayed");

		test.info("Step: 3 -- ");
		commonFuntions.enterTextboxContains("Claimant ID", "");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("", "Pass", " screen is displayed");
		
		test.info("Step: 4 -- ");
		
		
	}

}
