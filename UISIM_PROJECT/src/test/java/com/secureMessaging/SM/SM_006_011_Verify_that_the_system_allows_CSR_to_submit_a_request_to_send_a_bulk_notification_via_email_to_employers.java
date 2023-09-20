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
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FraudAndInvestigationPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_006_011_Verify_that_the_system_allows_CSR_to_submit_a_request_to_send_a_bulk_notification_via_email_to_employers extends TestBase {

	@Test(priority = 1, description = "SM_006_003_Verify_that_the_system_does_not_allow_CSR_to_Cance_the_BulkMessage_when_the_batch_job_status_is_Cancelled_In_progress_Completed_Recall_Initiated ", groups = {
	"Regression" })
	
public void SM_006_003() throws Exception {
commonStepDefinitions commonFunctions = new commonStepDefinitions();
//PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
//AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
SMPage sm = new SMPage(driver);
//FraudAndInvestigationPage fiPage = new FraudAndInvestigationPage(driver);


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
commonFunctions.ScrollMenu("Send Bulk Notification");
commonFunctions.clickMenu("Send Bulk Notification");
commonFunctions.waitForLoadingIconToDisappear();

//--------SM-124-----

commonFunctions.screenShot("Send Bulk Notification", "Pass","Send Bulk Notification page launched-SM-124");
//commonFunctions.selectRadioQuestions(xpathQuestions, xpathParameter);
sm.appealCaseRadioButton1.click();
sm.notificationViaEmail.click();
sleep(1000);
commonFunctions.selectLink("Document", "Browse");
sleep(1000);
commonFunctions.uploadDoc("ERN.xls");
sleep(1000);
sm.write_SecureMessage.sendKeys("Message Send");
commonFunctions.enterTextboxContains("Send Notification On", "9/25/2023");
sleep(1000);
commonFunctions.clickButtonContains("Submit ");
commonFunctions.waitForLoadingIconToDisappear();
//----SUC-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Task Confirmation", "Pass", "Task Confirmation page launched Successfully-SUC-002");
		 //-----Home
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		commonFunctions.waitForLoadingIconToDisappear();

		//------Menu----	
		commonFunctions.clickMenu("Menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("Send Bulk Notification");
		commonFunctions.clickMenu("Send Bulk Notification");
		commonFunctions.waitForLoadingIconToDisappear();
		
		
       //---SM-126---
		commonFunctions.screenShot("View Bulk Notification Summary", "Pass", "Successfully launched to SM-126 page");
		sleep(1000);
		commonFunctions.enterTextboxContains(" Period From", "7/25/2023");
		sleep(1000);
		commonFunctions.enterTextboxContains(" Period To", "7/25/2023");
		sleep(1000);
		sm.appealCaseRadioButton1.click();
		commonFunctions.selectCheckbox("Email");
		sleep(2000);
		commonFunctions.screenShot("View Bulk Notification Summary", "Pass", "Entered Information on SM-126 page");
		sleep(2000);
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		commonFunctions.screenShot("View Bulk Notification Summary", "Pass", "Entered Information on SM-126 page");
		
	//---Batch issue Blocked not for automation
		
}}
