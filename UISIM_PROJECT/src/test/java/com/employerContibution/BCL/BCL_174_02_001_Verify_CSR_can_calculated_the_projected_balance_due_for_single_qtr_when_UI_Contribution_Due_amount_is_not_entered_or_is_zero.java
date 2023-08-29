package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_174_02_001_Verify_CSR_can_calculated_the_projected_balance_due_for_single_qtr_when_UI_Contribution_Due_amount_is_not_entered_or_is_zero extends TestBase {
	@Test

	public void  BCL_174_02_001()throws Exception {

		test = report.createTest("BCL_174_02_001_Verify_CSR_can_calculated_the_projected_balance_due_for_single_qtr_when_UI_Contribution_Due_amount_is_not_entered_or_is_zero");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
/*
 * Map<String, String> databaseEanResult =
 * commonFunction.database_SelectQuerySingleColumn(
 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023') ORDER BY UPDATED_TS DESC"
 * ,"EAN");
 * 
 * String eanValue = databaseEanResult.get("EAN");
 * System.out.println("The EAN is " + eanValue);
 * 
 * Map<String, String> databaseRateYear =
 * commonFunction.database_SelectQuerySingleColumn(
 * "SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '"
 * +eanValue+ "')" , "RATE_YEAR"); String rateYr =
 * databaseRateYear.get("RATE_YEAR"); System.out.println("The Rate Year is " +
 * rateYr);
 */
		
		
		
		
		
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	
		//---Menu Click---
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Employer Collection");
		commonFunction.clickMenu("Employer Collection");
		commonFunction.clickMenu("Pay Agreement");
		commonFunction.clickMenu("Calculate Payoff");
		commonFunction.screenShot("Menu", "Pass", "Warrant page is displayed");
sleep(3000);

commonFunction.screenShot("COL-489", "Pass", "Calculate Payoff is displayed");
commonFunction.enterTextboxContains("Employer Registration Number", "04-64364");
commonFunction.enterTextboxContains("Payoff Date", "8/17/2023");
commonFunction.screenShot("COL-489", "Pass", "Calculate Payoff is displayed");
commonFunction.clickButton("Continue ");


sleep(3000);
commonFunction.screenShot("COL-488", "Pass", "Pay Off Letter Details");
sleep(3000);
commonFunction.clickButton(" Calculate ");
sleep(3000);
commonFunction.screenShot("COL-488", "Pass", "Pay Off Letter Details");
commonFunction.clickButton("Home");
sleep(3000);
commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");



}



}



