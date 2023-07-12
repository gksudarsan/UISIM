package com.employerContribution.ERM;



	import java.util.Map;

	import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

	import com.ui.base.TestBase;
	import com.ui.pages.EmployerRegisterPage;
	import com.ui.pages.PEOPage;
	import com.ui.utilities.COMMON_CONSTANT;

	import stepDefinitions.commonStepDefinitions;

	public class ERM_481_02_001_Verify_CSR_can_enter_ERN_and_manually_update_desired_information_and_verify_WhatIf_rate_based_on_new_desired_information  extends TestBase {

		@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "ERM_02_001_Verify_CSR_can_enter_ERN_and_manually_update_desired_information_and_verify_WhatIf_rate_based_on_new_desired_information ,", groups = {
				COMMON_CONSTANT.REGRESSION })
		public void TC_ERM_48_001() throws Exception {

			test = report.createTest(
					"ERM_02_001_Verify_CSR_can_enter_ERN_and_manually_update_desired_information_and_verify_WhatIf_rate_based_on_new_desired_information ");

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
			commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			sleep(2000);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

			// ---Menu Click---
			commonFunction.clickMenu("menu");
			// commonFuntions.clickMenu("Employer Registration");
			commonFunction.ScrollMenu("Account Maintenance");
			commonFunction.clickMenu("Account Maintenance");
			// empRegPage.Account_Maintenance.click();
			commonFunction.clickMenu("What-If Rate");
			commonFunction.screenShot("MenuPage", "Pass",
					"Navigate to Menu ->Account Maintenance ->What-If Rate");
			sleep(6000);
			commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
			commonFunction.enterTextboxContains("Rate Year", rateYr);
			commonFunction.screenShot("wahtif rate calcu", "Pass", "rate calcu page");
			commonFunction.clickButtonContains("Continue");
			sleep(15000);
			commonFunction.screenShot("ERM-009", "Pass", "What-If Rate Calculator page is displayed");
			commonFunction.enterTextboxContains("Opening Balance ($)", "925300");
			commonFunction.enterTextboxContains("Contributions ($)", "9,088.88");
			commonFunction.enterTextboxContains("Charges ($)", "9,998.99");
			commonFunction.enterTextboxContains("Current Year Wages", "4,560.00");
			commonFunction.enterTextboxContains("General Account Transfer ($)", "8,42.89");
			commonFunction.enterTextboxContains("1st Prior Year Wages", "3,456.99");
			commonFunction.enterTextboxContains("2nd Prior Year Wages", "3,487.00");
			commonFunction.enterTextboxContains("3rd Prior Year Wages", "7,600.00");
			commonFunction.enterTextboxContains("4th Prior Year Wages", "6,789.00");
			
			sleep(2000);
			
			commonFunction.clickButtonContains("Calculate Rate ");
	sleep(5000);
	
	boolean actualResult;
    String actualValue=commonFunction.retrieveLabelContains("Average Wages ($)");
    if(actualValue == null || actualValue.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Average Wages ($) is populated");
    actualValue=commonFunction.retrieveLabelContains("Prior Year GA Transfers");
    if(actualValue == null || actualValue.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Prior Year GA Transfers is populated");
    
    String actualValue1=commonFunction.retrieveLabelContains("Account Percentage (%)");
    if(actualValue1 == null || actualValue1.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Account Percentage (%) is populated");
		/*
		 * actualValue1=commonFunction.
		 * retrieveLabelContains("80% Rule for G-Rate Account"); if(actualValue1 == null
		 * || actualValue1.length() == 0) {actualResult = false;} else
		 * {actualResult=true;} Assert.assertTrue(actualResult, "80% Rule for G-Rate Account is populated");
		 */
    
    
    String actualValue2=commonFunction.retrieveLabelContains("Subsidiary Rate (%)");
    if(actualValue2 == null || actualValue2.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Subsidiary Rate (%) is populated");
    actualValue2=commonFunction.retrieveLabelContains("Re-employement Service Fund(RSF) (%)");
    if(actualValue2 == null || actualValue2.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Re-employement Service Fund(RSF) (%) is populated");
    actualValue2=commonFunction.retrieveLabelContains("Normal Rate (%)");
    if(actualValue2 == null || actualValue2.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Normal Rate (%) is populated");
    actualValue2=commonFunction.retrieveLabelContains("Rate Code");
    if(actualValue2 == null || actualValue2.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Rate Code is populated");  
    actualValue2=commonFunction.retrieveLabelContains("Total UI Rate (%)");
    if(actualValue2 == null || actualValue2.length() == 0) {actualResult = false;}
    else {actualResult=true;}
    Assert.assertTrue(actualResult, "Total UI Rate (%)is populated"); 
    
    
    
    
	
	commonFunction.screenShot("page", "Pass", "Rate calculation done");
	
		}

}
