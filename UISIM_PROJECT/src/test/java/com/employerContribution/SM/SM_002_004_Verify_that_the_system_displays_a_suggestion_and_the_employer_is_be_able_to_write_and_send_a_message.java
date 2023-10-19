package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SM_101;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_002_004_Verify_that_the_system_displays_a_suggestion_and_the_employer_is_be_able_to_write_and_send_a_message
		extends TestBase {


	@Test
	public void SM_002_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SM_101 sm101 = new SM_101(driver);

		test = report.createTest(
				"SM_002_004_Verify_that_the_system_displays_a_suggestion_and_the_employer_is_be_able_to_write_and_send_a_message");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_MA_ROLE.toUpperCase(), COMMON_CONSTANT.EMPLOYER_MA_ROLE_PASSWORD);
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("Write Message");
		sleep(1000);
		commonFuntions.screenShot("Menu Navigation", "Pass", "Write Message menu side bar is displayed");
		commonFuntions.clickMenu("Write Message");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "Launched to SM-101");
		//commonFuntions.selectDropdown("Message Language", " English ");
		commonFuntions.selectDropdown("Category", " Collection Notices ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectDropdown("Subcategory", " How do I file a Quarterly Combined Withholding, Wage Reporting and Unemployment Insurance Return (Form NYS-45)? ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		sm101.sm101TextMessageField.click();
		sm101.sm101TextMessageField.sendKeys("This test case is for employer");
		commonFuntions.clickButtonContains("Send ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Secure Message Confirmation", "Pass", "SUC-002 screen is displayed");

		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		// Check WorkItem creation in database

		// we can test in next cycle

	}

}
