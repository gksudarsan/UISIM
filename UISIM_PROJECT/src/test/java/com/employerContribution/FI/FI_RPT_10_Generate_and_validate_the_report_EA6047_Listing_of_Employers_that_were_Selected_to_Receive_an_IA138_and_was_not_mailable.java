package com.employerContribution.FI;

import java.util.Map;
	import org.testng.annotations.Test;

	import com.ui.base.TestBase;
	import com.ui.pages.EmployerRegisterPage;
	import com.ui.pages.PEOPage;
	import com.ui.pages.SUC_002;
	import com.ui.utilities.COMMON_CONSTANT;

	import stepDefinitions.commonStepDefinitions;

	public class FI_RPT_10_Generate_and_validate_the_report_EA6047_Listing_of_Employers_that_were_Selected_to_Receive_an_IA138_and_was_not_mailable extends TestBase {
		@Test
		public void FI_RPT_10() throws Exception {
			test = report.createTest("FI_RPT_10_Generate_and_validate_the_report_EA6047_Listing_of_Employers_that_were_Selected_to_Receive_an_IA138_and_was_not_mailable");

			commonStepDefinitions cf = new commonStepDefinitions();
			SUC_002 suc002 = new SUC_002(driver);
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
			PEOPage peoPage = new PEOPage(driver);

			// Login
			cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			sleep(2000);
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("ApplicationLogin", "Pass", "Login is Successful");

			// Menu
			cf.clickMenu("Menu");
			sleep(2000);
			cf.screenShot("MenuPage", "Pass", "Launched to Menu");
			cf.ScrollMenu("Inquiry");
			cf.clickMenu("Inquiry");
			sleep(1000);
			cf.clickMenu("Reports");
			cf.clickMenu("Search Reports");
			cf.screenShot("Search Reports", "Pass", "Launched to DMS-002");
			cf.selectDropdown("Report", " EA6047 - LISTING OF EMPLOYERS THAT WERE SELECTED TO RECEIVE AN IA13.8 AND WAS UNMAILABLE ");
			cf.enterTextboxContains("Start Date", "6/1/2023");
			cf.enterTextboxContains("End Date", "9/5/2023");
			cf.clickButtonContains(" Search ");
			cf.screenShot("Filled Details", "Pass", "Launched to DMS-002");
			cf.clickOnLinkAnchorTag("LISTING OF EMPLOYERS THAT WERE SELECTED TO RECEIVE AN IA13.8 AND WAS UNMAILABLE");
			sleep(3000);
			cf.switchTab();
			cf.screenShot("Report", "Pass", "Penalty Cancellation Counts");
			cf.verifyContentInPDf("LISTING OF EMPLOYERS THAT WERE SELECTED TO RECEIVE AN IA13.8 AND WAS UNMAILABLE");
			System.out.println("Worked");
		}
}
