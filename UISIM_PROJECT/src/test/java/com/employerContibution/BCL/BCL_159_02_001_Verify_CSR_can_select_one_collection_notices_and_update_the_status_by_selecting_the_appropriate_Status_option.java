package com.employerContibution.BCL;


import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_159_02_001_Verify_CSR_can_select_one_collection_notices_and_update_the_status_by_selecting_the_appropriate_Status_option extends TestBase {
	@Test
	public void  BCL_159_02_001()throws Exception {

		test = report.createTest("BCL_159_02_001_Verify_CSR_can_select_one_collection_notices_and_update_the_status_by_selecting_the_appropriate_Status_option");

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
		commonFunction.clickMenu("Collections");
		
		commonFunction.screenShot("Menu", "Pass", "Warrant page is displayed");
		commonFunction.clickMenu("Update Collection Notices");
sleep(3000);
commonFunction.screenShot("COL-589", "Pass", "Update Collection Notices page is displayed");
commonFunction.enterTextboxContains("Employer Registration Number", "0464364");
commonFunction.clickButton("Continue ");
commonFunction.screenShot("COL-700", "Pass", "Update Collection Notice Status is displayed");
sleep(2000);
//commonFunction.selectCheckbox("Select");
commonFunction.clickButton("Submit ");
sleep(3000);
commonFunction.screenShot("SUC-002", "Pass", "details updated successfully");
sleep(3000);
commonFunction.clickButton("Home");
commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");
}
}