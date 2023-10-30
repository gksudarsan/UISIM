package com.employerContribution.SM;

import java.util.Map;

import org.apache.commons.exec.LogOutputStream;
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
public class SM_005_003 extends TestBase {

	@Test(priority = 1, description = "SM.005.003-Verify that CSR is able to write and send a bulk Secure Message to Employer", groups = {
			"Regression" })
	public void SM_005_003() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		BclPage BCL = new BclPage(driver);

		test = report
				.createTest("SM.005.003-Verify that CSR is able to write and send a bulk Secure Message to Employer");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("Write Bulk Secure Message");
		commonFunctions.clickMenu("Write Bulk Secure Message");
		commonFunctions.screenShot("Menu", "Pass", "Write Bulk Secure Message");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- SM-112 ---
		commonFunctions.screenShot("Write Bulk Secure Message", "Pass", "Successfully launched to SM-112 page");
		commonFunctions.selectRadio("Employer");
		commonFunctions.selectDropdown("Message Language", " English ");
		sleep(2000);
		commonFunctions.selectLink("Document", "Browse");
		sleep(2000);
		commonFunctions.uploadDoc("Recipient_Worklist.xls");
		sleep(2000);
		commonFunctions.selectDropdown("Category", " Audits ");
		sleep(2000);
		commonFunctions.selectDropdown("Subcategory",
				" How do I designate a representative for the Review of my  Books and Records? ");
		sleep(2000);
		commonFunctions.enterTextboxContains("Subject", "Audit of this day");
		BCL.write_SecureMessage.sendKeys("Audit of this day 28/08/2023");
		commonFunctions.selectRadioQuestions("Send Notification Via", "Email ");
		commonFunctions.enterTextboxContains("Send Message On", "8/29/2023");
		commonFunctions.screenShot("Write Bulk Secure Message", "Pass", "Bulk Secure Message Entered on SM-112 page");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Write Bulk Secure Message", "Pass", "Message popup on SM-112 page");
		commonFunctions.clickButtonContains("Yes ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Write Bulk Secure Message Confirmation", "Pass",
				"Write Bulk Secure Message Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");

		commonFunctions.logoutAndLogin(COMMON_CONSTANT.EMPLOYER_USER_4, COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("View Message");
		commonFunctions.clickMenu("View Message");
		commonFunctions.ScrollMenu("View Message");
		commonFunctions.clickMenu("View Message");
		commonFunctions.screenShot("Menu", "Pass", "View Message");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- SM-105 ---
		commonFunctions.screenShot("View Message", "Pass", "Successfully launched to SM-105 page");
		commonFunctions.enterTextboxContains("From Date", "8/1/2023");
		commonFunctions.enterTextboxContains("To Date", "8/1/2023");
		commonFunctions.selectDropdown("Category", " Audits ");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickOnLink("Audit for this day");

		// --- SM-108 ---
		commonFunctions.screenShot("Show Received Messages", "Pass", "Successfully launched to SM-108 page");

		// Done
	}

}
