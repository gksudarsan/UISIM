package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.pages.HomePage;
import com.ui.pages.SRGE_543;
import com.ui.pages.SRGE_544;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;

public class EM_013_001_Add_Account_Flag_First_Return_Mail extends TestBase {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase {

		String EAN = prop.getProperty("EAN");

		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			String EAN = "0000257";
			test = report.createTest("EM.013.001 - Verify CSR is able to add account flags information for flag type  'First Return Mail'.");
			commonStepDefinitions stepDef = new commonStepDefinitions();
			SRGE_543 srge543Page = new SRGE_543(driver);
			SRGE_544 srge544Page = new SRGE_544(driver);
			SUC_002 suc002Page = new SUC_002(driver);
			HomePage home = new HomePage(driver);
			
			test.log(Status.INFO, "Logging to the application");
			stepDef.login(prop.getProperty("CSR_UserID"),"Admin@123456789");
			test.log(Status.PASS, "Sucessfully login to the application");
			test.log(Status.INFO, "Navigating to the maintenance account status tab");
			home.navigateToAccountMaintenance();
			test.log(Status.PASS, "Navigated to the maintenance account status tab");
			srge543Page.checkRequiredText();
			test.log(Status.PASS, "Required is displaying if user don't enter the data and click continue");
			Map<String, String> ernOutput = stepDef.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea  WHERE ORGANIZATION_TYPE='INDO 'AND  ACCOUNT_STATUS='LIAB' ORDER BY UPDATED_TS", "EAN");
			String ernValue = ernOutput.get("EAN");
			srge543Page.enterEANNumber(ernValue);
			test.log(Status.PASS, "Entered the ERN number");
			test.log(Status.INFO, "Submit the form without entering the details");
			Boolean flag = srge544Page.submitWithoutDetails();
			Assert.assertTrue(flag);
			test.log(Status.PASS, "Required is displaying if user don't enter the data and click Submit");
			test.log(Status.INFO, "Entering the Data in form");
			srge544Page.enterDetails();
			test.log(Status.PASS, "Form is filled");
			test.log(Status.INFO, "Checking if the filters are displaying");
			srge544Page.checkFilter();
			test.log(Status.PASS, "Filter verified");
			test.log(Status.INFO, "Click Submit button");
			srge544Page.clickSubmit();
			test.log(Status.PASS, "Submit button clicked");
			suc002Page.validateSucessMessage();

		}
	}
}
