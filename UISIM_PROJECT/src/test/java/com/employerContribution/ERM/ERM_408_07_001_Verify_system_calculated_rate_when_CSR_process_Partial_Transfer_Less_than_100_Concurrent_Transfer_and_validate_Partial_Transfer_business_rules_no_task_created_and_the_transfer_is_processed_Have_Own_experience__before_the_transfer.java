package com.employerContribution.ERM;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


		public class ERM_408_07_001_Verify_system_calculated_rate_when_CSR_process_Partial_Transfer_Less_than_100_Concurrent_Transfer_and_validate_Partial_Transfer_business_rules_no_task_created_and_the_transfer_is_processed_Have_Own_experience__before_the_transfer extends TestBase {
			
			@Test
			public void ERM_408_07_001() throws Exception {

				test = report.createTest("ERM_408_07_001_Verify_system_calculated_rate_when_CSR_process_Partial_Transfer_Less_than_100_Concurrent_Transfer_and_validate_Partial_Transfer_business_rules_no_task_created_and_the_transfer_is_processed_Have_Own_experience__before_the_transfer");

				commonStepDefinitions commonFunction = new commonStepDefinitions();
				EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
				PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
				
				 Map<String, String> databaseEanResult =
							commonFunction.database_SelectQuerySingleColumn( "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023') ORDER BY UPDATED_TS DESC"
									,"EAN");
								  
								  String eanValue = databaseEanResult.get("EAN");
								  System.out.println("The EAN is " + eanValue);
								  
								  Map<String, String> databaseRateYear =
								  commonFunction.database_SelectQuerySingleColumn(
								  "SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '" +eanValue+ "')"
								  , "RATE_YEAR");
								  String rateYr = databaseRateYear.get("RATE_YEAR");
								  System.out.println("The Rate Year is " + rateYr);
				// --- Login ---
				commonFunction.login(COMMON_CONSTANT.CSR_USER_3.toUpperCase(), COMMON_CONSTANT.CSR_USER_3_PASSWORD);
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
	commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
	commonFunction.clickButton("Continue ");
	commonFunction.screenShot("ERM019", "Pass", "Successful launch to TRansfer deatils");
	sleep(2000);
	commonFunction.clickButton("Continue ");
	sleep(3000);
	commonFunction.screenShot("ERM018", "Pass", "Successful launch to Process partial TRansfer deatils");
	commonFunction.clickButton("Continue ");
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
	commonFunction.enterTextboxContains("Employer Registration Number", "");
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
