
package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_481_005_Verify_CSR_can_enter_ERN_and_rate_year_and_manually_update_desired_information_with_select_override_reasons_Previous_rate_purge extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "ERM_481_005_Verify_CSR_can_enter_ERN_and_rate_year_and_manually_update_desired_information_with_select_override_reasons_Previous_rate_purge", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_48_001() throws Exception {

		test = report.createTest(
				"ERM_481_005_Verify_CSR_can_enter_ERN_and_rate_year_and_manually_update_desired_information_with_select_override_reasons_Previous_rate_purge");

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

		// ---Menu Click---
		commonFunction.clickMenu("menu");
		// commonFuntions.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Account Maintenance");
		// empRegPage.Account_Maintenance.click();
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.clickMenu("Maintain Rate");
		commonFunction.screenShot("MenuPage", "Pass",
				"Navigate to Menu ->Account Maintenance ->Maintain Rate ->Update Contribution Rate");
		commonFunction.clickMenu("Update Contribution Rate");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		//commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		//commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.enterTextboxContains("Rate Year", rateYr);
		commonFunction.screenShot("page", "Pass", "Details entered");
		//commonFunction.selectDropdown("Reason", "Administrative Decision");
		commonFunction.clickButtonContains("Continue");
		sleep(5000);
		
		commonFunction.enterTextboxContains("Opening Balance ($)", "25300");
		commonFunction.enterTextboxContains("Contributions ($)", "1,088.88");
		commonFunction.enterTextboxContains("Charges ($)", "3,998.99");
		commonFunction.enterTextboxContains("Current Year Wages", "4,560.00");
		commonFunction.enterTextboxContains("Account Balance ($)", "8,342.89");
		commonFunction.enterTextboxContains("1st Prior Year Wages", "3,456.99");
		commonFunction.enterTextboxContains("2nd Prior Year Wages", "3,487.00");
		commonFunction.enterTextboxContains("3rd Prior Year Wages", "7,600.00");
		commonFunction.enterTextboxContains("4th Prior Year Wages", "6,789.00");
		commonFunction.screenShot("page2", "Pass", "Details updated.");
		sleep(2000);
		commonFunction.selectDropdown("Reason", "Previous rate purged");
		commonFunction.screenShot("page3", "Pass", "reason selected.");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);
		commonFunction.selectRadio("Yes");
		sleep(2000);
		commonFunction.screenShot("ERM2", "Pass", "Details updated.");
		commonFunction.clickButtonContains("Submit");
		sleep(2000);
		commonFunction.screenShot("ERM3", "Pass",
				"The Employer Rate Information has been successfully modified and saved.");
		sleep(2000);
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
		  
		;
		
		///peoPage.txningrident.click();
		
		commonFunction.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");
		
	
		 peoPage.currentYearwages.click();
		
		
		  commonFunction.screenShot("EstimatedWgaesPage", "Pass","Successfully landed on ERM 030 Page");
		  commonFunction.clickButton(" Home "); sleep(2000);
		  commonFunction.screenShot("UnemploymentInsuranceServiePage", "Pass",
		  "Successfully landed on Home Page");
		 
	}



	}
