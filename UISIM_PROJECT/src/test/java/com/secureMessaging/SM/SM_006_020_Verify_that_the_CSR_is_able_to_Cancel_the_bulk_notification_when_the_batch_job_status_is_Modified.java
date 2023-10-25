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
public class SM_006_020_Verify_that_the_CSR_is_able_to_Cancel_the_bulk_notification_when_the_batch_job_status_is_Modified extends TestBase {

	@Test(priority = 1, description = "SM_006_020_Verify_that_the_CSR_is_able_to_Cancel_the_bulk_notification_when_the_batch_job_status_is_Modified", groups = {
	"Regression" })
	
public void SM_006_020() throws Exception {
commonStepDefinitions commonFunctions = new commonStepDefinitions();
SMPage sm = new SMPage(driver);



 test= report.createTest("SM_006_020_Verify_that_the_CSR_is_able_to_Cancel_the_bulk_notification_when_the_batch_job_status_is_Modified");


// --------Login-------
 commonFunctions.login(COMMON_CONSTANT.SecureMessagingSpecialist2_User.toUpperCase(), COMMON_CONSTANT.SecureMessagingSpecialist2_User_pwd);
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
commonFunctions.clickMenu("menu");
commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
commonFunctions.ScrollMenu("Secure Messaging");
commonFunctions.clickMenu("Secure Messaging");
commonFunctions.ScrollMenu("View Bulk Notification Status");
commonFunctions.clickMenu("View Bulk Notification Status");
commonFunctions.waitForLoadingIconToDisappear();

       //---SM-125---
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Successfully launched to SM-125 page");
		sleep(1000);
		commonFunctions.enterTextboxContains("From Date", "10/06/2023");
		sleep(1000);
		commonFunctions.enterTextboxContains("To Date", "10/06/2023");
		sleep(1000);
		//commonFunctions.selectCheckbox("Email");
		sm.checkboxEmail.click();
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Entered Information on SM-125 page");
		sleep(1000);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Modified Row visible on SM-125 page");
		
		sm.datatabRadio1.click();
		commonFunctions.clickButtonContains("Cancel ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "After Clicked on cancel on SM-125 page");
		sleep(1000);
		commonFunctions.clickButtonContains(" Yes ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Error  Action taken on the selected request is not allowed on SM-125 page");
		/*
		sm.dataTableIdRadio2.click();
		commonFunctions.clickButtonContains("Cancel ");
		sleep(1000);
		commonFunctions.screenShot("View Bulk Notification Status", "Pass", "Error  Action taken on the selected request is not allowed on SM-114 page");
		*/
		commonFunctions.screenShot("TC_SM_006_020", "Pass", "Successfully completed TC_SM_006_020 ");
	
		
}}
