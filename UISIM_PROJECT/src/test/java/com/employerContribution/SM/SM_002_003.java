package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SM_101;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_002_003 extends TestBase {

	@Test
	public void SM002_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);
		SM_101 sm101 = new SM_101(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = report.createTest(
				"SM.002.003-Verify that the system does not display a suggestion and the claimant will be able to write and send a message.(Suggestion Type N)");

		commonFuntions.login(COMMON_CONSTANT.CLAIMENT_USER1.toUpperCase(), COMMON_CONSTANT.CLAIMENT_USER_PASSWORD1);
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 1 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("Write Message");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Write Message menu side bar is displayed");
		commonFuntions.clickMenu("Write Message");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 screen is displayed");

		test.info("Step: 2 -- ");
		commonFuntions.selectDropdown("Message Language", " English ");
		commonFuntions.selectDropdown("Category", " Skills Training Resources ");
		commonFuntions.selectDropdown("Subcategory",
				" Can I attend training and still get Unemployment Insurance benefits? ");

		sleep(3000);

		sm101.sm101TextMessageField.click();
		sm101.sm101TextMessageField.sendKeys("This test case is for employer");
		commonFuntions.clickButtonContains("Send ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Secure Message Confirmation", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 3 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		test.info("Step: 4 -- ");
		// we can test in next cycle

	}

}
