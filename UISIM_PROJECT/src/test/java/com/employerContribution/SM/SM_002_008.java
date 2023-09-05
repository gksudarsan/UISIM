package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SM_101;
import com.ui.pages.COL_468;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class SM_002_008 extends TestBase {

	@Test
	public void SM002_008() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		COL_468 col468 = new COL_468(driver);
		PEOPage peopage = new PEOPage(driver);
		SM_101 sm101 = new SM_101(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = report.createTest(
				"SM.002.008-Verify that the system displays a suggestion, but the third party representative will not be able to write and send a message. (Suggestion Type H)");

		commonFuntions.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 3 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("Write Message - Enter ERN");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Write Message - Enter ERN menu side bar is displayed");
		commonFuntions.clickMenu("Write Message - Enter ERN");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message - Enter ERN", "Pass", "SM-100 screen is displayed");

		String eanNumber = "5454645";
		
		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
		sleep(2000);
		commonFuntions.screenShot("Write Message - Enter ERN", "Pass", "Entered valid ERN on SM-100 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 screen is displayed");
		commonFuntions.selectDropdown("Category", " Protest ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.selectDropdown("Subcategory", " How do I protest a Wage Garnishment? ");
		sleep(1000);
		
		try {
			sm101.sm101TextMessageField.click();
			sm101.sm101TextMessageField.sendKeys("This test case is for employer");
			}
			catch(Exception e) { 
				System.out.println("Write your message is disabled");
			}

		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL.docx");

		commonFuntions.screenShot("Write Message", "Pass", "SM-101 with details filled screen is displayed");
		sleep(2000);

		commonFuntions.clickButtonContains("Send ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		col468.errorLabelInLi("User is not allowed to send message for the given selected subcategory.");
		commonFuntions.screenShot("Write Message", "Pass", "SM-101- error with details filled screen is displayed");
		sleep(2000);

	}

}
