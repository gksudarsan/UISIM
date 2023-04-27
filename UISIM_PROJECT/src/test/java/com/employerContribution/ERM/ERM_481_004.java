package com.employerContribution.ERM;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_481_004 extends TestBase {
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Correction'", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_481_004() throws Exception {

		test = report.createTest(
				"Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Correction'");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		HomePage homePage = new HomePage(driver);		
		 
		//DB Query
		//Valid ERN
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);
		
		//Valid Rate Year
		Map<String, String> databaseRateYearResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '" + eanValue + "')",
				"RATE_YEAR");
		String rateYearValue = databaseRateYearResult.get("RATE_YEAR");
		System.out.println(rateYearValue);	
				
		// --- Login --- Refresh --- PopUp OK ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		sleep();

		// --- Menu Click ---
		sleep(2000);
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		homePage.maintainRate.click();
		homePage.updateContributionRate.click();
		//Enter Valid ERN
		sleep();
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		//Enter Valid Rate Year
		commonFunction.enterTextboxContains("Rate Year", rateYearValue);
		commonFunction.screenShot("Entered Valid ERN and Rate Year", "Pass", "Valid ERN and Valid Rate Year");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-060 --- //
		commonFunction.screenShot("Employer Rate Maintenance - Update Contribution Rate", "Pass","Successfully launched on SREG-060");
		commonFunction.enterTextboxContains("Effective Date", "4/4/2023");
		commonFunction.enterTextboxContains("Quarter ", "1"); //Experience rating subject date - Adjustments
		commonFunction.enterTextboxContains("Year ", "2021");
		commonFunction.enterTextboxContains("Quarter ", "1"); //Experience rating subject date - Results
		commonFunction.enterTextboxContains("Year ", "2023");
		commonFunction.enterTextboxContains("Lapsed Balance Revision", "2023");
		commonFunction.enterTextboxContains("Opening Balance ($)", "56,738.78");
		commonFunction.enterTextboxContains("Contributions ($)", "6,002.00");
		commonFunction.enterTextboxContains("Charges ($)", "7,450.00");
		//commonFunction.enterTextboxContains("Account Balance ($)", "55,290.78");
		commonFunction.enterTextboxContains("General Account Transfer ($)", "5,200.00");
		commonFunction.enterTextboxContains("Current Year Wages", "35,036.90");
		commonFunction.enterTextboxContains("1st Prior Year Wages", "34,903.69");
		commonFunction.enterTextboxContains("2nd Prior Year Wages", "34,503.69");
		commonFunction.enterTextboxContains("3rd Prior Year Wages", "34,003.69");
		commonFunction.enterTextboxContains("4th Prior Year Wages", "33,403.69");
		commonFunction.selectDropdown("Override Reasons", "Correction");
		homePage.commentBox.sendKeys("Sample Text");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-061 --- //
		commonFunction.screenShot("Employer Rate Maintenance Verification", "Pass", "Successfully launched to SREG-061");
		commonFunction.selectRadioQuestions("Generate Rate Statement", "No ");
		homePage.submitButton.click();
		
		// --- SUC-002 --- //
		commonFunction.screenShot("Employer Rate Maintenance Confirmation", "Pass", "Successfully launched to SUC-002");
		commonFunction.clickButton("Home ");
		sleep(2000);
		commonFunction.clickMenu("Menu");
		commonFunction.clickMenu("Inquiry");
		commonFunction.clickMenu("Contribution Inquiry");
		commonFunction.clickMenu("Inquiry Employer Account");
		
		// --- SREG-050 --- //
		commonFunction.screenShot("Inquiry Employer Account", "Pass", "Successfully launched to SREG-050");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.clickButton("Continue ");
		
		// --- SREG-051 --- //
		commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "Successfully launched to SREG-051");
		//commonFunction.selectTableParameterizedId("4.8", 1, 8, "Rate History", "employerRateHistory");
		commonFunction.selectTableWithoutId("2023", 8, 1, "Rate History");
		sleep(2000);
		
		// --- SREG-052 --- //
		commonFunction.screenShot("Employer Account Rate Transaction History ", "Pass", "Successfully launched to SREG-052");
		commonFunction.selectTableWithoutId("Administrative Decision", 2, 2, "Employer Account Rate Transaction History ");
		sleep(2000);
		// --- ERM-014 --- //
		commonFunction.screenShot("Transaction Ingredients", "Pass", "Successfully launched to ERM-014");
		commonFunction.clickHyperlink("Ledger after Transaction");
		sleep();
		
		// --- SREG-063 --- //
		commonFunction.screenShot("Ledger after Transaction",  "Pass", "Successfully launched to ERM-063");
		commonFunction.clickButton("Previous ");
		
		// Previous to SREG-052
		commonFunction.clickButton(" Previous ");
		commonFunction.screenShot("Back to Inquiry Employer Account Information Page", "Pass", "Successfully launched to SREG-051");
		sleep(2000);
		commonFunction.selectTableParameterizedId("4.8", 1, 1, "Rate History", "employerRateHistory");
		
		// --- ERM-013 --- //
		commonFunction.screenShot("Current Rating Account Status History", "Pass", "Successfully launched to ERM-013");
		commonFunction.clickButton("Previous ");
		
		//Step-17 & Step 18 - Not applicable
		
		// Click on Ledger after Transaction
		commonFunction.selectTableWithoutId("2023", 8, 1, "Rate History");
		commonFunction.selectTableWithoutId("Administrative Decision", 2, 2, "Employer Account Rate Transaction History ");
		
		commonFunction.screenShot("Transaction Ingredients", "Pass", "Current Screen - ERM014");
		commonFunction.selectTableWithoutId("Current Year Wages", 2, 2, "Transaction Ingredients");
		sleep();
		commonFunction.screenShot("Estimated Wages", "Pass", "Successfully landed on ERM-030");
		commonFunction.clickButton(" Home ");
		commonFunction.screenShot("Home", "Pass", "Successfully launched to Home");		
		
		System.out.println("Worked");
	};

}

