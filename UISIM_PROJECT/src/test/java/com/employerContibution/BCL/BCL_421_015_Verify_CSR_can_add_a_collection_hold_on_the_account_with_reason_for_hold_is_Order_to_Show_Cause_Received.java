package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_421_015_Verify_CSR_can_add_a_collection_hold_on_the_account_with_reason_for_hold_is_Order_to_Show_Cause_Received
		extends TestBase {

	@Test(priority = 1, description = "BCL.421.015 - Verify_CSR_can_add_a_collection_hold_on_the_account_with_reason_for_hold_is_Order_to_Show_Cause_Received", groups = {
			"Regression" })
	public void BCL_421_015() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		BclPage BclPage = PageFactory.initElements(driver, BclPage.class);
		

		test = report.createTest(
				"BCL_421_015 - Verify_CSR_can_add_a_collection_hold_on_the_account_with_reason_for_hold_is_Order_to_Show_Cause_Received");
		// -----Login
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		// -----DB---
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='ACTV' AND EAN LIKE '9%'", "EAN");

		String eanValue = databaseResults.get("EAN");//
		  if ((eanValue == null) || eanValue.isEmpty()) {
	            System.out.println("EAN value is null");
	    } else {
	            test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanValue + ".");
	    }
	/*
		  Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
					"SELECT TEA.EMPLOYER_ACCOUNT_ID,* FROM T_EMPLOYER te JOIN T_EMPLOYER_ACCOUNT tea ON TEA.EAN = TE.EAN WHERE TEA.REGISTRATION_STATUS = 'C' AND TE. CHARGEABILITY_TYPE = 'RATD' AND TEA.ACCOUNT_STATUS = 'LIAB' ORDER BY TEA.CREATED_TS DESC",
					"EAN");
			String eanValue = databaseEanResult.get("EAN");
			System.out.println(eanValue);
			*/

		// -----Menu
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		commonFuntions.ScrollMenu("Maintain Collection Hold");
		commonFuntions.clickMenu("Maintain Collection Hold");
		sleep();

		// ---COL-527---
		commonFuntions.screenShot("Maintain Collection Hold", "Pass",
				"Maintain Collection Hold COL-527 screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		// commonFuntions.enterTextboxContains("Employer Registration Number",
		// "04-64364");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		String getEAN = commonFuntions.retrieveTextboxContains("Employer Registration Number");
		System.out.println("getEAN" + getEAN);

		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "Message 'hyperlink visible for ADD ");
		sleep(2000);
		commonFuntions.clickOnLinkAnchorTag("Add Collection Hold");
		sleep(2000);

		// --COL-528---
		commonFuntions.screenShot("Add Collection Hold", "Pass", "Add Collection Hold (COL-528)screen launched");
		sleep(2000);
		commonFuntions.enterCurrentDate("Hold Start Date");
		sleep(2000);
		commonFuntions.selectDropdown("Reason For Hold", " Order to Show Cause Received ");
		sleep(2000);
		BclPage.otherreason_MaintainCollectionHold.sendKeys("Automation Testing");
		sleep(2000);
		commonFuntions.screenShot("Add Collection Hold", "Pass", "Details Entered");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");

		// ------COL-529--
		commonFuntions.screenShot("Add Collection Hold Verification ", "Pass", "Suceesfully landed on COL-529");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");

		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold Confirmation", "Pass",
				"Successfully launched to Add Collection Hold Confirmation(SUC -002) screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		sleep(4000);
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");

		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCL_421_015", "Pass", "Successfully passed TC BCL_421_015");
	}

}
