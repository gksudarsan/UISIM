package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.pages.SM_101;

import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_002_006 extends TestBase {

	@Test
	public void SM002_006() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);
		SM_101 sm101 = new SM_101(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		

		test = report.createTest(
				"SM.002.006-Verify that the system does not display a suggestion and the employer will be able to write and send a message.(Suggestion Type N)");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		test.info("Step: 3 -- ");
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
		
		test.info("Step: 4 -- ");
		commonFuntions.selectDropdown("Category", " Account Balance and UI Rate Issues ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.selectDropdown("Subcategory", " Other ");
		sleep(1000);
		
		sm101.sm101TextMessageField.click();
		sm101.sm101TextMessageField.sendKeys("This test case is for employer");
		
		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL.docx");
		
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 with details filled screen is displayed");
		sleep(2000);
		
		commonFuntions.clickButtonContains("Send ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Secure Message Confirmation", "Pass", "SUC-002 screen is visible");
		sleep(2000);
		
		test.info("Step: 5 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
	}

}
