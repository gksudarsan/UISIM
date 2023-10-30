package com.employerContribution.EOA;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.FIpage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_07_005 extends TestBase {

	@Test(priority = 1, description = "EOA.07.005-Verify CSR can access PEO account and add, edit user(s)", groups = {
			"Regression" })
	public void EOA_07_005() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIpage FI = new FIpage(driver);

		test = report.createTest("EOA.07.005-Verify CSR can access PEO account and add, edit user(s)");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		commonFuntions.clickMenu("Menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		commonFuntions.ScrollMenu("Employer Users");
		commonFuntions.clickMenu("Employer Users");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SREG-533---
		commonFuntions.screenShot("User Search", "Pass", "User Search (SREG-533)screen launched");
		commonFuntions.selectRadioQuestions("Account Type: ", "Employer or Professional Employer Organization");
		commonFuntions.enterTextboxContains("Employer Registration Number", "5294821");
		commonFuntions.screenShot("User Search", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SREG-061---
		commonFuntions.screenShot("Employer Users", "Pass", "Employer Users (SREG-061)screen launched");
		commonFuntions.clickOnLinkAnchorTag(" ADD USER");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SREG-531---
		commonFuntions.screenShot("Add Employer User", "Pass", "Add Employer User (SREG-531)screen Launched");
		commonFuntions.enterTextboxContains("First Name", "shubhanshi");
		commonFuntions.enterTextboxContains("Last Name", "sahu");
		commonFuntions.enterTextboxContains("Email Address", "shubhanshi@labor.ny.gov");
		commonFuntions.enterTextboxContains(" Contact Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("User ID",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Temporary Password", "Subh@1234567890");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Subh@1234567890");
		commonFuntions.selectDropdown("User Types", " Employer Sub-User ");
		FI.employerbenefits.click();
		commonFuntions.screenShot("Add Employer User", "Pass",
				"Details Entered on Add Employer User (SREG-531)screen Launched");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFuntions.screenShot("Add User Confirmation", "Pass", "Add User Confirmation (SUC-002)screen Launched");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFuntions.screenShot("Home", "Pass", "Home screen launched");
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.clickMenu("Menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Users");
		commonFuntions.clickMenu("Users");
		commonFuntions.ScrollMenu("Employer Users");
		commonFuntions.clickMenu("Employer Users");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SREG-533---
		commonFuntions.screenShot("User Search", "Pass", "User Search (SREG-533)screen launched");
		commonFuntions.selectRadioQuestions("Account Type: ", "Employer or Professional Employer Organization");
		commonFuntions.enterTextboxContains("Employer Registration Number", "5294821");
		commonFuntions.screenShot("User Search", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SREG-061---
		commonFuntions.screenShot("Employer Users", "Pass", "Employer Users (SREG-061)screen launched");
		commonFuntions.clickOnLinkAnchorTag("Manage User");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SREG-062---
		commonFuntions.screenShot("Update Employer User", "Pass", "Update Employer User (SREG-062)screen Launched");
		commonFuntions.selectDropdown("Suffix", " Jr. ");
		commonFuntions.enterTextboxContains("User ID",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Temporary Password", "Subh@1234567890");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Subh@1234567890");
		commonFuntions.selectDropdown("User Types", " Employer Sub-User ");
		commonFuntions.screenShot("Update Employer User", "Pass",
				"Details Entered on Update Employer User (SREG-062)screen Launched");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFuntions.screenShot("Update User Confirmation", "Pass",
				"Update User Confirmation (SUC-002)screen Launched");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		// Done
	}
}
