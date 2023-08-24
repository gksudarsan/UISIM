package com.billingCollectionLiens.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


		public class  BCL_484_006_Verify_CSR_can_search_for_warrants_in_an_active_status_based_on_the_ERN_and_unable_to_process_warrant_vacate extends TestBase {
			
			@Test
			public void  BCL_001()throws Exception {

				test = report.createTest("BCL_484_006_Verify_CSR_can_search_for_warrants_in_an_active_status_based_on_the_ERN_and_unable_to_process_warrant_vacate");

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
				commonFunction.ScrollMenu("Contribution Collection");
				commonFunction.clickMenu("Contribution Collection");
				commonFunction.clickMenu("Warrant");
				commonFunction.screenShot("Menu", "Pass", "Warrant page is displayed");
				commonFunction.clickMenu("Request Warrant");
				
	sleep(3000);
	commonFunction.screenShot("COL-570", "Pass", "Process Partial Transfer page is displayed");
	commonFunction.enterTextboxContains("Employer Registration Number", "0464364");
	commonFunction.screenShot("COL-570", "Pass", "Process Partial Transfer page is displayed");
	commonFunction.clickButton("Continue ");
	commonFunction.screenShot("COL-571", "Pass", "List of warrent is displayed");
	sleep(2000);
	peoPage.selectRadiobutton.click();
	peoPage.textBox.sendKeys("ok");
	peoPage.dateEnter.sendKeys("8/2/2023");
	
	
	commonFunction.screenShot("COL-571", "Pass", "List of warrent is displayed");
	commonFunction.clickButton("Submit ");
	sleep(3000);
	commonFunction.screenShot("COL-499", "Pass", "Request to vacate warrent page is displayed");
	sleep(3000);
	commonFunction.clickButton("Home");
	sleep(3000);
	commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");
	
	
	
	}
	
	
	
}



