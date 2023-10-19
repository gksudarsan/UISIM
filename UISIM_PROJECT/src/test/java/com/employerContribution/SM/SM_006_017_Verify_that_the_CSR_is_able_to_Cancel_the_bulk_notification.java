package com.employerContribution.SM;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SMPage;
import com.ui.pages.SM_101;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_006_017_Verify_that_the_CSR_is_able_to_Cancel_the_bulk_notification extends TestBase {

	@Test
	public void SM_006_017() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		SM_101 sm101 = new SM_101(driver);
		SMPage sm =  new SMPage(driver);

		test = report.createTest(
				"SM_006_017_Verify_that_the_CSR_is_able_to_Cancel_the_bulk_notification");

		cf.login(COMMON_CONSTANT.CSR_Secure_Messaging_Specialist_Second.toUpperCase(),
				COMMON_CONSTANT.CSR_Secure_Messaging_Specialist_Second_PASSWORD);
		Thread.sleep(2000);
		cf.waitForLoadingIconToDisappear();

		cf.clickMenu("Menu");
		sleep(1000);
		cf.ScrollMenu("Secure Messaging");
		cf.clickMenu("Secure Messaging");
		sleep(2000);
		cf.screenShot("Menu Navigation", "Pass", "View Bulk Notification Status");
		cf.clickMenu("View Bulk Notification Status");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("View Bulk Notification Status", "Pass", "Launched to SM-125");
		cf.enterTextboxContains("From Date", "1/1/2022");
		cf.enterTextboxContains("To Date", "1/1/2022");
		sm.emailCheckBox.click();
		cf.clickButtonContains(" Search ");
		cf.smRadioSelect(2);
		cf.clickButton("Cancel ");
		cf.screenShot("Filled - View Bulk Notification Status", "Pass", "Launched to SM-125");
		cf.clickButtonContains(" Yes ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("View Bulk Notification Status Confirmation", "Pass", "Launched to SUC-002");
		cf.Label("The message was successfully cancelled.");
	}
}
