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
public class BCL_421_001 extends TestBase {

	@Test(priority = 1, description = "BCL.421.001 - Verify CSR can add a collection hold on the account with reason for hold is Hearing Pending", groups = {
			"Regression" })
	public void BCL_421_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT DISTINCT ttech.HOLD_STATUS AS HOLD_STATUS,tea.EAN AS EAN FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON tea.EMPLOYER_ACCOUNT_ID = ttech.EMPLOYER_ACCOUNT_ID WHERE tea.REGISTRATION_STATUS IN ('C')AND tea.ACCOUNT_STATUS IN ('LIAB')GROUP BY ttech.HOLD_STATUS,tea.EAN",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"BCL.421.001 - Verify CSR can add a collection hold on the account with reason for hold is Hearing Pending");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.login(COMMON_CONSTANT.Collections_Specialist_1,
				COMMON_CONSTANT.Collections_Specialist_1_PASSWORD);
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
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "Message 'Required' on EM-011");
		commonFuntions.enterTextboxContains("Employer Registration Number", "1111111");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Maintain Collection Hold", "Pass",
				"Message 'The Employer Registration Number is not valid");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---COL-527---
		commonFuntions.screenShot("Maintain Collection Hold", "Pass",
				"Maintain Collection Hold (COL-527)screen launched");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickOnLink("Add Collection Hold");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---COL-528---
		commonFuntions.screenShot("Add Collection Hold", "Pass", "Add Collection Hold (COL-528)screen launched");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold", "Pass", "Message 'Required' on COL-528");
		commonFuntions.selectDropdown("Reason For Hold", " Hearing Pending ");
		BCL.otherreason_MaintainCollectionHold.sendKeys("For Testing");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold", "Pass",
				"Message 'The response to the Hold Start Date is required.' on COL-528");
		commonFuntions.enterTextboxContains("Hold Start Date", "7/2/2023");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold", "Pass",
				"Message 'The Hold Start Date cannot be a past date.' on COL-528");
		commonFuntions.clearTextboxContains("Hold Start Date");
		commonFuntions.selectDropdown("Reason For Hold", " Other ");
		commonFuntions.waitForLoadingIconToDisappear();
		BCL.otherreason_MaintainCollectionHold.clear();
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold", "Pass",
				"Message 'The other reason description is required when dropdown reason is selected as “other”' on COL-528");
		commonFuntions.enterTextboxContains("Hold Start Date", "10/7/2023");
		sleep(1000);
		commonFuntions.selectDropdown("Reason For Hold", " Hearing Pending ");
		sleep(1000);
		BCL.otherreason_MaintainCollectionHold.sendKeys("For Testing");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---COL-529---
		commonFuntions.screenShot("Add Collection Hold Verification", "Pass",
				"Add Collection Hold Verification (COL-529)screen launched");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFuntions.screenShot("Add Collection Hold Confirmation", "Pass",
				"Add Collection Hold Confirmation (SUC-002)screen launched");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFuntions.screenShot("Home", "Pass", "Home screen launched");
		commonFuntions.waitForLoadingIconToDisappear();

		// Done
	}

}
