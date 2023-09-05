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

public class SM_006_002 extends TestBase {

	@Test
	public void SM006_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);
		SM_101 sm101 = new SM_101(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		

		test = report.createTest(
				"SM.006.002-Verify that the CSR is able to modify the Bulk Message when the batch job status is Submitted");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		test.info("Step: 2 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("View Bulk Secure Message Summary");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Write Message menu side bar is displayed");
		commonFuntions.clickMenu("View Bulk Secure Message Summary");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "SM-114 screen is displayed");
		
		test.info("Step: 3 -- ");
		commonFuntions.enterPastDate("From Date", 365);
		commonFuntions.enterCurrentDate("To Date");
		commonFuntions.selectDropdown("Category", " Collection Notices ");
		commonFuntions.selectDropdown("Sub Category", " Other ");
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		
		test.info("Step: 4 -- ");
		sm101.sm114SubmittedRadioBtn.click();
		sleep(3000);
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "SM-114 screen is displayed");
		commonFuntions.clickButtonContains("modify ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "Popup in SM-114 screen is displayed");
		
		test.info("Step: 5 -- ");
		commonFuntions.clickButtonContains(" Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("Write Bulk Secure Message", "Pass", "SM-112 screen is displayed");
		
		test.info("Step: 6 -- ");
		sm101.sm101TextMessageField.click();
		sm101.sm101TextMessageField.clear();
		sm101.sm101TextMessageField.sendKeys("This message is send to the employers message is to modified");
		sm101.sm112TextRadioBtn.click();
		commonFuntions.forceClearTextWithElement("Send Message On");
		commonFuntions.enterFutureDate("Send Message On", 7);
		commonFuntions.screenShot("Write Bulk Secure Message", "Pass", "SM-112 screen is displayed");
		
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("View Bulk Secure Message Summary", "Pass", "Popup in SM-114 screen is displayed");
	
		sm101.sm112PopupYesBtn.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("View Bulk Secure Message Status Confirmation", "Pass", "SUC-002 screen is displayed");
	
		test.info("Step: 7 -- ");
		//batch execution required
		
	}

}
