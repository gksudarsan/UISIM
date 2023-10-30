package com.employerContribution.SM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_004_005_Verify_that_the_CSR_is_able_to_reply_to_received_message_and_then_send_and_close_task_SendCloseTaskButton extends TestBase {

	@Test(priority = 1, description = "SM.004.005-Verify that the CSR is able to reply to a received message and then send and close the task (Send & Close Task Button)", groups = {
			"Regression" })
	public void SM_004_005() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		BclPage BCL = new BclPage(driver);

		test = report.createTest(
				"SM.004.005-Verify that the CSR is able to reply to a received message and then send and close the task (Send & Close Task Button)");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.EMPLOYER_USER_9, COMMON_CONSTANT.EMPLOYER_USER_9_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("Write Message");
		commonFunctions.clickMenu("Write Message");
		commonFunctions.screenShot("Menu", "Pass", "Write Message");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- SM-101 ---
		commonFunctions.screenShot("Write Message", "Pass", "Successfully launched to SM-101 page");
		commonFunctions.selectDropdown("Category", " Audits ");
		sleep(2000);
		commonFunctions.selectDropdown("Subcategory", " Other ");
		sleep(2000);
		BCL.write_SecureMessage.sendKeys("This testcase is to verify TPR is able to send message 28/08/2023");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample.docx");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Write Message", "Pass", "Write Message Message Entered on SM-101 page");
		commonFunctions.clickButtonContains("Send ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Secure Message Confirmation", "Pass",
				"Secure Message Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");

		commonFunctions.logoutAndLogin(COMMON_CONSTANT.Collections_Specialist_1, COMMON_CONSTANT.Collections_Specialist_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		
		commonFunctions.clickButton(" Go to My Q ");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- WF-001 ---
		commonFunctions.screenShot("Individual Work Queue", "Pass", "Successfully launched to WF-001 page");
		commonFunctions.enterTextboxContains("Work Item Description Free Text", "Current");
		commonFunctions.clickButton(" Search ");
		commonFunctions.clickOnLink("Current UI Rates");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- WF-091 ---
		commonFunctions.screenShot("Work Item Details", "Pass", "Successfully launched to WF-091 page");
		commonFunctions.clickButtonContains("Open Work Item ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SM-103---
		commonFunctions.screenShot("Reply Message", "Pass", "Successfully launched to SM-103 page");
		BCL.write_SecureMessage.sendKeys("This message is received successfully");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample.docx");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Reply Message", "Pass", "Message Entered on SM-103 page");
		commonFunctions.clickButton(" Send and Close Task ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Secure Message Confirmation", "Pass", "Secure Message Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		// Done
	}

}
