package com.employerContribution.ERM;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_4_001_Verify_CSR_can_view_the_Employers_account_informatio_nand_th_epreviou_sand_current_year_rate_history_information extends TestBase {
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Correction'", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_4_001() throws Exception {

		test = report.createTest(
				"Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Correction'");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		HomePage homePage = new HomePage(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		// Valid Rate Year
		Map<String, String> databaseRateYearResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '"
						+ eanValue + "')",
				"RATE_YEAR");
		String rateYearValue = databaseRateYearResult.get("RATE_YEAR");
		System.out.println(rateYearValue);

		// --- Login --- Refresh --- PopUp OK ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_3.toUpperCase(), COMMON_CONSTANT.CSR_USER_3_PASSWORD);
		sleep(2000);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		sleep();

		// --- Menu Click ---
		sleep(2000);
		commonFunction.screenShot("InsuranceServiePage", "Pass", "Successfully landed on Home Page");
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Inquiry");
		commonFunction.clickMenu("Contribution Inquiry");
		sleep(2000);
		commonFunction.screenShot("InsuranceServiePage", "Pass", "Successfully clicked on menu's");
		commonFunction.clickMenu("Inquiry Employer Account");
		sleep(2000);
		//-------SREG 050
		// Enter Valid ERN
		commonFunction.screenShot("Inquiry Employer AccountPage", "Pass", "Successfully landed on SREG 050 page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		commonFunction.screenShot("Inquiry Employer AccountPage", "Pass", "Successfully entered deatils on 050 and  click on continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-051 --- //
		sleep(2000);
		commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "Successfully launched to SREG-051");
		sleep(2000);
		commonFunction.clickButton("Previous ");
		
		//---------SREG 050-----//
		commonFunction.screenShot("Inquiry Employer AccountPage", "Pass", "Successfully landed on SREG 050 page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		commonFunction.screenShot("Inquiry Employer AccountPage", "Pass", "Successfully entered deatils on 050 and  click on continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-051 --- //
		sleep(2000);
	    commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "Successfully launched to SREG-051");
		sleep(2000);
		commonFunction.selectTableWithoutId("2023", 8, 1, "Rate History");
		commonFunction.waitForLoadingIconToDisappear();
		
		// --- SREG-052 --- //
		commonFunction.screenShot("Employer Account Rate Transaction History ", "Pass",
				"Successfully launched to SREG-052");
		sleep(4000);
		commonFunction.selectTableWithoutId("Total Transfers - Straight-Line", 2, 2,
				"Employer Account Rate Transaction History ");
		commonFunction.waitForLoadingIconToDisappear();
	// --- ERM-014 --- //
		commonFunction.screenShot("Transaction Ingredients", "Pass", "Successfully launched to ERM-014");
		sleep(2000);
		commonFunction.clickButton("Previous ");
		sleep(4000);
		// --- SREG-052 --- //
		commonFunction.screenShot("Employer Account Rate Transaction History ", "Pass",
				"Successfully launched to SREG-052");
		sleep(2000);
		commonFunction.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
		commonFunction.waitForLoadingIconToDisappear();
		//----------SREG -063
					sleep(2000);
					commonFunction.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");
					commonFunction.clickButton("Previous ");
					commonFunction.clickButton(" Previous ");
					
					//----------SREG -051
				    sleep(2000);
					commonFunction.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
					commonFunction.selectTableWithoutId("2023", 1, 1, "Rate History");
					commonFunction.waitForLoadingIconToDisappear();
					
					//----------ERM -013
					commonFunction.screenShot("CurrentRatingAccountStatusHistoryPage", "Pass", "Successfully landed on SREG 013 page");
					sleep(2000);
					commonFunction.clickButton("Previous ");
								
					//step 19 & 20 not applicable
						
					//--------SREG 051
					
					commonFunction.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
					sleep(2000);
					commonFunction.selectTableWithoutId("2023", 8, 1, "Rate History");
					commonFunction.waitForLoadingIconToDisappear();
					// --- SREG-052 --- //
					commonFunction.screenShot("Employer Account Rate Transaction History ", "Pass",
							"Successfully launched to SREG-052");
					////commonFunction.selectTableWithoutId("2023", 1, 1, "Rate History");
					
					commonFunction.selectTableWithoutId("2023", 1, 1, "Employer Account Rate Transaction History ");
					
					commonFunction.waitForLoadingIconToDisappear();
					
					//-------INQ 200
					commonFunction.screenShot("Inquiry Employer Account Experience for Annual Rate Calculation", "Pass",
							"Successfully launched to INQ 200");
					sleep(2000);
					
					commonFunction.clickButton(" Previous ");
					
				    //---------SREG-052 ---
					sleep(2000);
					commonFunction.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
					sleep(2000);
					commonFunction.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
					commonFunction.waitForLoadingIconToDisappear();
					
					//--------SREG 063
					commonFunction.screenShot("EstimatedWgaesPage", "Pass", "Successfully landed on ERM 063 Page");
					
					commonFunction.clickOnLink("0");
					//commonFunction.selectTableWithoutId("Current Year Wages", 2, 3, "Ledger after Transaction");
					sleep(6000);
					commonFunction.screenShot("EstimatedWgaesPage", "Pass", "Successfully landed on ERM 030 Page");
					
					//----------Home
					commonFunction.clickButton(" Home ");
					sleep(2000);
					commonFunction.screenShot("Home page", "Pass", "Successfully landed on Home Page TC: ERM.4.001 Completed");
					System.out.print("Done");
					//Completed by palak
	
	}
}
