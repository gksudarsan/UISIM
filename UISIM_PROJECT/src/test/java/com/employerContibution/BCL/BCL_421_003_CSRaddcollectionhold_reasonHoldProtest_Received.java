package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_421_003_CSRaddcollectionhold_reasonHoldProtest_Received extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can add a collection hold on the account with reason for hold is Protest Received", groups = "Regression")
	public void BCL_421_003() throws Exception {

		test = report.createTest(
				"BCL_421_003_Verify CSR can add a collection hold on the account with reason for hold is Protest Received");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		BclPage bcllocators = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='ACTV' AND EAN LIKE '9%'", "EAN");
		String ERN_Num = databaseEanResult.get("EAN");
		
		if((ERN_Num==null)||(ERN_Num.isEmpty()))
        {System.out.println("ERN Value is null");}
        else {test.log(Status.PASS, "DB connected successfully and fetched ERN is: "+ ERN_Num +".");}

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		// sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.screenShot("Menu1", "Pass", "Click on Menu page");
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		sleep(1000);
		commonFuntions.screenShot("Menu2", "Pass", "Click on Contribution Collection");
		sleep(2000);
		commonFuntions.clickMenu("Maintain Collection Hold");
		commonFuntions.screenShot("Menu3", "Pass", "Enter ERN to Search Hold");

		// ---Maintain Collection Hold---COL-527//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN_Num);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "Click on Add Collection Hold COL-527");
		commonFuntions.clickOnLink("Add Collection Hold");

		// ---Add Collection Hold---COL-528//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold", "Pass", "Fill Collection Hold details COL-528");
		commonFuntions.enterFutureDate("Hold Start Date", 7);
		commonFuntions.selectDropdown("Reason For Hold", " Protest Received");
		bcllocators.otherreason_MaintainCollectionHold.sendKeys("reason for hold is Protest Received");
		commonFuntions.screenShot("Add Collection Hold details", "Pass", "Filled Collection Hold details COL-528");
		commonFuntions.clickButton("Continue ");

		// ---Add Collection Hold Verification---COL-529//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold Verification", "Pass", "Collection Hold Verification COL-529");
		commonFuntions.clickButton("Submit ");

		// ---Add Collection Hold Confirmation---SUC-002//
		commonFuntions.screenShot("Add Collection Hold Confirmation", "Pass", "Collection Hold Confirmation SUC-002");
		commonFuntions.clickButton("Home ");
		commonFuntions.screenShot("Home page", "Pass", "welcome");
	}
}