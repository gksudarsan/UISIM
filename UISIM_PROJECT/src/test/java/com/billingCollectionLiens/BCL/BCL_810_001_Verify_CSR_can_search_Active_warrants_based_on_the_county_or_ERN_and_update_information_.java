package com.billingCollectionLiens.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


		public class  BCL_810_001_Verify_CSR_can_search_Active_warrants_based_on_the_county_or_ERN_and_update_information_ extends TestBase {
			
			@Test
			public void  BCL_810_001()throws Exception {

				test = report.createTest("BCL_810_001_Verify_CSR_can_search_Active_warrants_based_on_the_county_or_ERN_and_update_information_");

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
				commonFunction.clickMenu("Enter Warrant Filed Date");
	sleep(3000);
	commonFunction.screenShot("COL-495", "Pass", "Enter Warrant Filed Date page is displayed");
	commonFunction.enterTextboxContains("Employer Registration Number", "0464364");
	commonFunction.clickButton("Continue ");
	commonFunction.screenShot("COL-496", "Pass", "Update Warrant Details is displayed");
	sleep(2000);
	commonFunction.selectCheckbox("Select");
	commonFunction.clickButton("Submit ");
	sleep(3000);
	commonFunction.screenShot("SUC-002", "Pass", "details updated successfully");
	sleep(3000);
	commonFunction.clickButton("Home");
commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");	
	}
	
	
}



