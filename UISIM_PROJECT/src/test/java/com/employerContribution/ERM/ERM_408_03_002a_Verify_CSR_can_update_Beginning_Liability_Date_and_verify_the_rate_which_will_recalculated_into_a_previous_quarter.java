package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_408_03_002a_Verify_CSR_can_update_Beginning_Liability_Date_and_verify_the_rate_which_will_recalculated_into_a_previous_quarter extends TestBase {
	
		@Test
		public void TC_ERM_48_001() throws Exception {

			test = report.createTest("ERM_408_03_002a_Verify_CSR_can_update_Beginning_Liability_Date_and_verify_the_rate_which_will_recalculated_into_a_previous_quarter");

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
				commonFunction.ScrollMenu("Account Maintenance");
				commonFunction.clickMenu("Account Maintenance");
				commonFunction.ScrollMenu("Employer Account Maintenance");
				commonFunction.clickMenu("Employer Account Maintenance");
				sleep();
				commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance");
				
				empRegPage.employerAccountMaintanceMenu.click();
				commonFunction.clickMenu("Employer Account Maintenance");
				commonFunction.clickMenu("Maintain Accounts");
				sleep(2000);
				commonFunction.screenShot("Employer Account Maintenance", "Pass", "Employer Account Maintenance page is displayed successfully");
				//00-00387
				
				// --- SREG-030 ---
				sleep(2000);		
				commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
				commonFunction.clickButton("Continue ");
				commonFunction.screenShot("SREG30", "Pass", "Successful launch to Modify Employer Account Details");
				sleep(2000);
				commonFunction.selectDropdown("Type of Legal Entity", "Corporation (All Types, includes Sub-Chapter S)");
				commonFunction.selectDropdown("Employer Type", "Business Employer");
				commonFunction.selectDropdown("Source", "IA602");
				commonFunction.selectDropdown("Source Type", "Coverage Exception");
				commonFunction.clickButton("Submit ");
				sleep(2000);
				commonFunction.screenShot("Employer Account Maintenance Confirmation", "Pass", "Employer Account Maintenance Confirmation page is displayed");
				commonFunction.clickButton("Home ");
				
				sleep(3000);
				commonFunction.clickButtonContains("Home");
				sleep(2000);
				commonFunction.clickMenu("menu");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Inquiry");
				commonFunction.clickMenu("Contribution Inquiry");
				sleep(2000);
				commonFunction.screenShot("ERM4", "Pass", "Inquiry Employer Account Path");
				commonFunction.clickMenu("Inquiry Employer Account");
				sleep(2000);
				commonFunction.screenShot("ERM5", "Pass", "Inquiry Employer Account Page");

				//commonFunction.enterTextboxContains("Employer Registration Number", "0000269");
				commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
				sleep(2000);
				commonFunction.screenShot("ERM6", "Pass", "Inquiry Employer Account Page Details Entered");
				commonFunction.clickButtonContains("Continue");
				sleep(2000);
				commonFunction.screenShot("ERM7", "Pass", "Inquiry Employer Account Information Page");
		        commonFunction.selectTableParameterizedId(rateYr, 8 , 1 , "Rate History", "employerRateHistory");
		        sleep(5000);
		        commonFunction.screenShot("SREG-052", "Pass", "Employer Account Rate Transaction History page displayed");
		        peoPage.traxnTYPE.click();
		        sleep(2000);
		        commonFunction.screenShot("ERM-014", "Pass", "Transaction Ingredients page displayed");
		        commonFunction.clickButton("Previous ");
		        
				
			 sleep(2000);
				commonFunction.screenShot("ERM8", "Pass", "Employer Account Rate Transaction History Page");
				commonFunction.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");		
				commonFunction.screenShot("ERM8", "Pass", "Ledger Page");
				  
				  sleep(2000); 			  
							
				commonFunction.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");
			
				 peoPage.currentYearwages.click();						
				  commonFunction.screenShot("EstimatedWgaesPage", "Pass","Successfully landed on ERM 030 Page");
				  commonFunction.clickButton(" Home "); sleep(2000);
				  commonFunction.screenShot("UnemploymentInsuranceServiePage", "Pass","Successfully landed on Home Page");
				
			
	}
	

}
