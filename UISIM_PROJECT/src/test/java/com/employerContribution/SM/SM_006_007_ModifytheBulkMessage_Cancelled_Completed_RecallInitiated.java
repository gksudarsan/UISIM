package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_006_007_ModifytheBulkMessage_Cancelled_Completed_RecallInitiated extends TestBase {

	@Test
	public void SM_006_007() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		SMPage sm = new SMPage(driver);
		test = report.createTest(
				"SM.006.007-Verify that the system does not allow CSR to Modify the Bulk Message when the batch job status is \"Cancelled/Completed/Recall Initiated\"");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();cf.clickMenu("Secure Messaging");
		sleep();
		cf.screenShot("NavigateToViewBulkSecureMessageSummary", "Pass", "Navigating to View Bulk Secure Message Summary");
		cf.clickMenu("View Bulk Secure Message Summary");
		sleep();

		//View Bulk Secure Message Summary_____________SM-114
		
		cf.screenShot("ViewBulkSecureMessageSummary", "Pass", "View Bulk Secure Message Summary");
		cf.enterPastDate("From Date", 120);sleep();
		cf.enterCurrentDate("To Date");sleep();
		cf.selectDropdown("Category", " Collection Notices ");sleep();
		cf.selectDropdown("Sub Category", " Other ");
		cf.screenShot("searchWithEnteredDetails", "Pass", "Search with entered details");
		cf.clickButtonContains(" Search ");
		sleep(2000);
		//sm.cancelledStatusType_radioButton.click();sleep();
		cf.screenShot("selectRadioButton", "Pass", "Selected Radio Button");
		cf.clickButtonContains("modify ");sleep(2000);
		cf.screenShot("popupWithCancelledStatus", "Pass", "PopUp with Cancelled Status");
		cf.clickButtonContains(" Yes ");sleep(2000);
		cf.screenShot("bulkSecureMessageSummary", "Pass", "Bulk Secure Message Summary");
		
		cf.clickButtonContains(" Search ");
		sleep(2000);
		//sm.completedStatusType_radioButton.click();sleep();
		cf.screenShot("selectRadioButton1", "Pass", "Selected Radio Button1");
		cf.clickButtonContains("modify ");sleep(2000);
		cf.screenShot("popupWithCompletedStatus", "Pass", "PopUp with Completed Status");
		cf.clickButtonContains(" Yes ");sleep(2000);
		cf.screenShot("bulkSecureMessageSummary1", "Pass", "Bulk Secure Message Summary1");
	}

}
