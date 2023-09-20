package com.secureMessaging.SM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_006_022_Verify_that_the_CSR_is_able_to_modify_the_bulk_notification_when_the_batch_job_status_is_Cancelled_Completed_RecallInitiated_Inprogress
		extends TestBase {

	@Test(priority = 1, description = "SM_006_022_Verify_that_the_CSR_is_able_to_modify_the_bulk_notification_when_the_batch_job_status_is_Cancelled_Completed_RecallInitiated_Inprogress", groups = {
			"Regression" })

	public void SM_006_022() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		SMPage sm = new SMPage(driver);

		test = report.createTest(
				"SM_006_022_Verify_that_the_CSR_is_able_to_modify_the_bulk_notification_when_the_batch_job_status_is_Cancelled_Completed_RecallInitiated_Inprogress");

		// --------Login-------
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.waitForLoadingIconToDisappear();
		/*
		 * // -------DB--- Map<String, String> databaseEanResult =
		 * commonFunctions.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID AND tea.EAN LIKE '9%'"
		 * , "EAN");
		 * 
		 * 
		 * 
		 * String eanNumber = databaseEanResult.get("EAN");
		 * 
		 * if ((eanNumber == null) || eanNumber.isEmpty()) {
		 * System.out.println("EAN value is null"); } else { test.log(Status.PASS,
		 * "DB Connected successfully & fetched ERN is " + eanNumber + "."); }
		 */
		// ------Menu----
		commonFunctions.clickMenu("Menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("View Bulk Notification Status");
		commonFunctions.clickMenu("View Bulk Notification Status");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SM-125---
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Successfully launched to SM-125 page");
		sleep(1000);
		commonFunctions.enterTextboxContains("From Date", "3/02/2022");
		sleep(1000);
		commonFunctions.enterTextboxContains("To Date", "7/20/2023");
		sleep(1000);
		// commonFunctions.selectCheckbox("Email");

		sm.checkboxEmail.click();
		sleep(2000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Entered Information on SM-125 page");
		sleep(2000);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();

		sm.dataTableIdRadio1.click();
		commonFunctions.clickButtonContains("modify ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "After Clicked on modify on SM-125 page");
		sleep(1000);
		commonFunctions.clickButtonContains(" Yes ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass",
				"Error  Action taken on the selected request is not allowed on SM-125 page");
		// ---- 2nd completed
		sleep(2000);
		commonFunctions.enterTextboxContains("From Date", "7/26/2022");
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Successfully launched to SM-125 page");
		sleep(1000);
		sleep(1000);
		commonFunctions.enterTextboxContains("To Date", "7/26/2023");
		sleep(1000);
		// sm.checkboxEmail.click();
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Entered Information on SM-125 page");
		sleep(1000);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		sm.dataTableIdRadio2.click();
		commonFunctions.clickButtonContains("modify ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "After Clicked on modify on SM-125 page");
		sleep(1000);
		commonFunctions.clickButtonContains(" Yes ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass",
				"Error  Action taken on the selected request is not allowed on SM-125 page");

		// As per mention in Design Doc Inprogress status is out of scope so we are
		// excluding.
		/*
		 * //------3rd inprogress commonFunctions.enterTextboxContains("From Date",
		 * "3/02/2022"); commonFunctions.screenShot("View Bulk Notification Status",
		 * "Pass", "Successfully launched to SM-125 page"); sleep(1000); sleep(1000);
		 * commonFunctions.enterTextboxContains("To Date", "7/20/2023"); sleep(1000);
		 * sm.checkboxEmail.click(); sleep(1000);
		 * commonFunctions.screenShot("View Bulk Notification Status", "Pass",
		 * "Entered Information on SM-125 page"); sleep(1000);
		 * commonFunctions.clickButtonContains(" Search ");
		 * commonFunctions.waitForLoadingIconToDisappear();
		 * sm.dataTableIdRadio1.click(); commonFunctions.clickButtonContains("modify ");
		 * sleep(1000); commonFunctions.screenShot("View Bulk Notification Status",
		 * "Pass", "After Clicked on modify on SM-125 page"); sleep(1000);
		 * commonFunctions.clickButtonContains(" Yes "); sleep(1000);
		 * commonFunctions.screenShot("View Bulk Notification Status", "Pass",
		 * "Error  Action taken on the selected request is not allowed on SM-125 page");
		 */

		commonFunctions.screenShot("TC:  SM_006_022", "Pass", "test completed  ");
		// ---Executed & completed by Palak

	}
}
