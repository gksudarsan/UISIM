package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_421_011 extends TestBase {

	@Test(priority = 1, description = "BCL.421.011 - Verify CSR can add a collection hold on the account with reason for hold is Pending Special Investigation", groups = {
			"Regression" })
	public void BCL_421_011() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='ACTV' AND EAN LIKE '9%'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"BCL.421.011 - Verify CSR can add a collection hold on the account with reason for hold is Pending Special Investigation");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		commonFuntions.ScrollMenu("Maintain Collection Hold");
		commonFuntions.clickMenu("Maintain Collection Hold");
		sleep();

		// ---EM-011---
		commonFuntions.screenShot("Maintain Collection Hold", "Pass",
				"Maintain Collection Hold (EM-011)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		// ---COL-527---
		commonFuntions.screenShot("Maintain Collection Hold", "Pass",
				"Maintain Collection Hold (COL-527)screen launched");
		sleep(2000);
		commonFuntions.clickOnLink("Add Collection Hold");
		sleep();

		// ---COL-528---
		commonFuntions.screenShot("Add Collection Hold", "Pass", "Add Collection Hold (COL-528)screen launched");
		commonFuntions.enterTextboxContains("Hold Start Date", "8/20/2023");
		commonFuntions.selectDropdown("Reason For Hold", " Pending Special Investigation ");
		sleep(1000);
		BCL.otherreason_MaintainCollectionHold.sendKeys("For Testing");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		// ---COL-529---
		commonFuntions.screenShot("Add Collection Hold Verification", "Pass",
				"Add Collection Hold Verification (COL-529)screen launched");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);

		// ---SUC-002---
		commonFuntions.screenShot("Add Collection Hold Confirmation", "Pass",
				"Add Collection Hold Confirmation (SUC-002)screen launched");
		commonFuntions.clickButtonContains("Home ");
		sleep(2000);

		// ---HOME---
		commonFuntions.screenShot("Home", "Pass", "Home screen launched");
		sleep(2000);

		// Done
	}

}
