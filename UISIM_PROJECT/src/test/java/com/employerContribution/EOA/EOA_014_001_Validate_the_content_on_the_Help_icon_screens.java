package com.employerContribution.EOA;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_014_001_Validate_the_content_on_the_Help_icon_screens extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision,", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void EOA_014_001() throws Exception {

		test = report.createTest(
				"EOA_014_001_Validate_the_content_on_the_Help_icon_screens");

		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// --- Login ---
		cf.login(COMMON_CONSTANT.EMPLOYER_USER_9.toUpperCase(),COMMON_CONSTANT.EMPLOYER_USER_9_PASSWORD);
		sleep(2000);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//SREG-612
		cf.screenShot("UI Services Employer Account Creation", "Pass", "Launched to SREG-612");
		cf.clickOnHelpIcon();
		cf.screenShot("UI Services Employer Account Creation", "Pass", "Help Screen - SREG-612");
		
	}
}
