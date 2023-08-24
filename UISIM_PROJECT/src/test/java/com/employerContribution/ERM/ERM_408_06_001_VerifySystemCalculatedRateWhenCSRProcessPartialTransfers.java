package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_408_06_001_VerifySystemCalculatedRateWhenCSRProcessPartialTransfers extends TestBase {

			// TODO Auto-generated constructor stub
	
	
		@Test
		public void ERM_408_06_001 () throws Exception {

			test = report.createTest("ERM_408_06_001_VerifySystemCalculatedRateWhenCSRProcessPartialTransfers");

			commonStepDefinitions commonFunction = new commonStepDefinitions();
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
			PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
	
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT. CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		//---Menu Click---
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Partial Transfer");
		commonFunction.clickMenu("Partial Transfer");
		commonFunction.screenShot("Menu", "Pass", "Process Partial Transfer");
		commonFunction.clickMenu("Process Partial Transfer");
sleep(3000);
commonFunction.screenShot("ERM-017", "Pass", "Process Partial Transfer page is displayed");
commonFunction.enterTextboxContains("Employer Registration Number", "4517766");
commonFunction.clickButton("Continue ");
commonFunction.screenShot("ERM019", "Pass", "Successful launch to TRansfer deatils");
sleep(2000);
commonFunction.selectCheckbox("Select");
commonFunction.screenShot("ERM019", "Pass", "Successful launch to TRansfer deatils");
commonFunction.clickButton("Submit ");
sleep(3000);
commonFunction.screenShot("SUC002", "Pass", "The partial transfer has been processed");
commonFunction.clickButtonContains("Home");
sleep(10000);

commonFunction.clickMenu("menu");
commonFunction.ScrollMenu("Inquiry");
commonFunction.clickMenu("Inquiry");
commonFunction.clickMenu("Contribution Inquiry");
commonFunction.screenShot("Menu", "Pass", "Inquiry Employer Account");
sleep(2000);
commonFunction.screenShot("ERM4", "Pass", "Inquiry Employer Account Profile Changes page is displayed");
commonFunction.enterTextboxContains("Employer Registration Number", "4517766");
commonFunction.clickButton("Continue ");
sleep(5000);
commonFunction.screenShot("SREG-051", "Pass", "Inquiry Employer Account Information");
sleep(3000);
commonFunction.clickButtonContains("Previous ");
sleep(3000);
commonFunction.clickButtonContains(" Home ");
commonFunction.screenShot("Home", "Pass", "Home page is displayed");

	}

}
