
package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_481_001 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision,", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_48_001() throws Exception {

		test = report.createTest(
				"Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision'");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		  
		  Map<String, String> databaseEanResult =
		  commonFunction.database_SelectQuerySingleColumn(
		  "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')"
		  , "EAN"); 
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
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		commonFunction.clickMenu("Menu");
		// commonFuntions.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Account Maintenance");
		// empRegPage.Account_Maintenance.click();
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.clickMenu("Maintain Rate");
		commonFunction.screenShot("MenuPage", "Pass",
				"Navigate to Menu ->Account Maintenance ->Maintain Rate ->Update Contribution Rate");
		commonFunction.clickMenu("Update Contribution Rate");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(2000);
		// commonFunction.enterTextboxContains("Employer Registration Number","0000269");
		commonFunction.enterTextboxContains("Employer Registration Number","1111111");
		//commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		 commonFunction.enterTextboxContains("Rate Year", "2023");
		 commonFunction.clickButtonContains("Continue");
		 commonFunction.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		//commonFunction.enterTextboxContains("Rate Year", rateYr);
		sleep(2000);
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.enterTextboxContains("Rate Year", rateYr);
		commonFunction.clickButtonContains("Continue");
		commonFunction.selectDropdown("Reason", "Other");
		commonFunction.clickButtonContains("Continue");
			
		// commonFunction.screenShot("ERM_Page", "FAIL", "The Employer Registration
		// Number (ERN)provided does not exit in the system");
		sleep(2000);

		commonFunction.screenShot("UpdateContributionpage", "Pass", "Reason is required if Remark is 'Other'.\r\n" + 
				"Nothing has been entered for update.");
		// commonFunction.clickMenu("Account_Maintenance");
		commonFunction.selectDropdown("Reason", "Administrative Decision");
		commonFunction.clickButtonContains("Continue");
		commonFunction.errorContent("Nothing has been entered for update.");
		sleep(5000);
		//commonFunction.clickButtonContains("Continue");
		
		//commonFunction.screenShot("Error", "Pass", "Please select the reason");
		sleep(2000);
		// commonFunction.errorLabelContains(xpathParameter, "ErrorCode001");
		// ommonFunction.selectFromDropdown("Other");
		
		
		// commonFunction.clickMenu("Maintain_Rate");
		// commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu ->Account
		// Maintenance -> Employer_Rate");
		// commonFunction.clickMenu("Employer_Rate");
		/*
		 * sleep(2000); commonFunction.screenShot("ERM1",
		 * "Pass","Reason is required if Remark is 'Other'.Nothing has been entered for update."
		 * ); commonFunction.clickButtonContains("Continue");
		 * commonFunction.errorContent("Nothing has been entered for update.");
		 * commonFunction.screenShot("Error", "Pass",
		 * "Nothing has been changed for update");
		 */
		// commonFunction.enterTextboxContains("Emp_RegiNo", "0000269");
		// commonFunction.enterTextboxContains("Rate_year", "2023");
		// sleep(2000);
		// commonFunction.screenShot("ERM_Page1", "PASS", "Details Entered");
		// commonFunction.clickMenu("Home");
		// Main menu should display.
		
//		commonFunction.enterTextboxContains("Charges ($)", "999,999,999.99");
//		commonFunction.enterTextboxContains("Current Year Wages", "999,999,999.99");
//		sleep(2000);
//
//		commonFunction.clickButtonContains("Continue");
//		commonFunction.screenShot("Error", "Pass", "Limit exceeded");

		sleep(2000);
		commonFunction.enterTextboxContains("Opening Balance ($)", "925300");
		commonFunction.enterTextboxContains("Contributions ($)", "9,088.88");
		commonFunction.enterTextboxContains("Charges ($)", "9,998.99");
		commonFunction.enterTextboxContains("Current Year Wages", "4,560.00");
		commonFunction.enterTextboxContains("Account Balance ($)", "8,342.89");
		commonFunction.enterTextboxContains("1st Prior Year Wages", "3,456.99");
		commonFunction.enterTextboxContains("2nd Prior Year Wages", "3,487.00");
		commonFunction.enterTextboxContains("3rd Prior Year Wages", "7,600.00");
		commonFunction.enterTextboxContains("4th Prior Year Wages", "6,789.00");
		sleep(2000);
		commonFunction.selectDropdown("Reason", "Administrative Decision");
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
		commonFunction.clickMenu("Menu");
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
		
	 sleep(2000);
		commonFunction.screenShot("ERM8", "Pass", "Employer Account Rate Transaction History Page");
		commonFunction.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");		
		commonFunction.screenShot("ERM8", "Pass", "Ledger Page");
		  commonFunction.clickButtonContains(" Home "); 
		  sleep(2000); 
		  commonFunction.screenShot("ERM9","Pass","Employer Account Rate Transaction History Page1");
		 //		

	}
}