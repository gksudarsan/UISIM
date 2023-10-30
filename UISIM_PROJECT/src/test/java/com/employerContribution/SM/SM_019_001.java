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
public class SM_019_001 extends TestBase {

	@Test(priority = 1, description = "SM.019.001- Verify the Help content on all the SM screens", groups = {
			"Regression" })
	public void SM_019_001() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		BclPage BCL = new BclPage(driver);

		test = report.createTest("SM.019.001- Verify the Help content on all the SM screens");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.Collections_Specialist_1, COMMON_CONSTANT.Collections_Specialist_1_PASSWORD);
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
		BCL.Help.click();
		commonFunctions.screenShot("Help", "Pass", "Help Details shown");
		BCL.close.click();
		commonFunctions.waitForLoadingIconToDisappear();

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("View Message");
		commonFunctions.clickMenu("View Message");
		BCL.click_ViewMessage.click();
		commonFunctions.screenShot("Menu", "Pass", "View Message");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- SM-105 ---
		commonFunctions.screenShot("View Message", "Pass", "Successfully launched to SM-105 page");
		BCL.Help.click();
		commonFunctions.screenShot("Help", "Pass", "Help Details shown");
		BCL.close.click();
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.enterTextboxContains("From Date", "8/10/2023");
		commonFunctions.enterTextboxContains("To Date", "8/10/2023");
		commonFunctions.screenShot("View Message", "Pass", "Date Entered for Search message");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickOnLink("Request for Additional Information");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- SM-106 ---
		commonFunctions.screenShot("Message Conversation", "Pass", "Successfully launched to SM-106 page");
		BCL.Help.click();
		commonFunctions.screenShot("Help", "Pass", "Help Details shown");
		BCL.close.click();
		commonFunctions.waitForLoadingIconToDisappear();

		// Done
	}

}
