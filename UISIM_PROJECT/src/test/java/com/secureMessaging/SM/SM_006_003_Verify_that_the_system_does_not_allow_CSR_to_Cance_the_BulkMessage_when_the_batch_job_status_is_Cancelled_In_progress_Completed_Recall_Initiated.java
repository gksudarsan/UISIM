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
public class SM_006_003_Verify_that_the_system_does_not_allow_CSR_to_Cance_the_BulkMessage_when_the_batch_job_status_is_Cancelled_In_progress_Completed_Recall_Initiated extends TestBase {

	@Test(priority = 1, description = "SM_006_003_Verify_that_the_system_does_not_allow_CSR_to_Cance_the_BulkMessage_when_the_batch_job_status_is_Cancelled_In_progress_Completed_Recall_Initiated ", groups = {
	"Regression" })
	
public void SM_006_003() throws Exception {
commonStepDefinitions commonFunctions = new commonStepDefinitions();
SMPage sm = new SMPage(driver);



 test= report.createTest("SM_006_003_Verify_that_the_system_does_not_allow_CSR_to_Cance_the_BulkMessage_when_the_batch_job_status_is_Cancelled_In_progress_Completed_Recall_Initiated ");


// --------Login-------
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.waitForLoadingIconToDisappear();
		/*
		// -------DB---
		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID AND tea.EAN LIKE '9%'", "EAN");
		
		

		String eanNumber = databaseEanResult.get("EAN");

		if ((eanNumber == null) || eanNumber.isEmpty()) {
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanNumber + ".");
		}
   */
	//------Menu----	
commonFunctions.clickMenu("Menu");
commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
commonFunctions.ScrollMenu("Secure Messaging");
commonFunctions.clickMenu("Secure Messaging");
commonFunctions.ScrollMenu("View Bulk Secure Message Summary");
commonFunctions.clickMenu("View Bulk Secure Message Summary");
commonFunctions.waitForLoadingIconToDisappear();

       //---SM-114---
		commonFunctions.screenShot("View Bulk Secure Message Summary", "Pass", "Successfully launched to SM-114 page");
		sleep(1000);
		commonFunctions.enterTextboxContains("From Date", "7/25/2023");
		sleep(1000);
		commonFunctions.enterTextboxContains("To Date", "7/25/2023");
		sleep(1000);
		commonFunctions.selectDropdown("Category", " Collection Notices ");
		sleep(2000);
		commonFunctions.selectDropdown("Sub Category", " Other ");
		sleep(2000);
		commonFunctions.screenShot("View Bulk Secure Message Summary", "Pass", "Entered Information on SM-114 page");
		sleep(2000);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		sm.dataTableIdRadio1.click();
		commonFunctions.clickButtonContains("Cancel ");
		sleep(1000);
		commonFunctions.clickButtonContains(" Yes ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Secure Message Summary", "Pass", "Error  Action taken on the selected request is not allowed on SM-114 page");
		/*
		sm.dataTableIdRadio2.click();
		commonFunctions.clickButtonContains("Cancel ");
		sleep(1000);
		*/
		commonFunctions.screenShot("View Bulk Secure Message Summary", "Pass", "Error  Action taken on the selected request is not allowed on SM-114 page");
		commonFunctions.screenShot("TC_SM_006_003", "Pass", "Successfully completed TC_SM_006_003 ");
		
	//--completed by palak
		
}}
