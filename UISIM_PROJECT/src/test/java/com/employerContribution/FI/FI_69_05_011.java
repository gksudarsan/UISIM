package com.employerContribution.FI;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.COL_474;
import com.ui.pages.FIP_006;
import com.ui.pages.FIS_002;
import com.ui.pages.FIS_008;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_69_05_011 extends TestBase {

	@Test
	public void FI169_07_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);
		COL_474 col474 = new COL_474(driver);
		FIP_006 fip006 = new FIP_006(driver);
		FIS_008 fis008 = new FIS_008(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = report.createTest(
				"FI.169.07.002- 'Verify TPR can submit a IA198.P Failure to File Protest form online, task 'Failure to File Penalty Protest' will create for CSR to review and close task with action taken");

		commonFuntions.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 3 -- ");
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("Write Message - Enter ERN");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Write Message menu side bar is displayed");
		commonFuntions.clickMenu("Write Message - Enter ERN");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message - Enter ERN", "Pass", "SM-100 screen is displayed");

		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Write Message - Enter ERN", "0847711"); // 0847711 , 5454645 , 5454645
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 screen is displayed");
		
	}

}
