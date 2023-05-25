//------------------------Palak---------------------------------------

package com.employerContibution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_481_003 extends TestBase {
	@Test
	public void ERM_481_003() throws Exception
	{
		
		commonStepDefinitions commfun = new commonStepDefinitions();
		HomePage homepage = new HomePage(driver);
		PEOPage peopage = PageFactory.initElements(driver,PEOPage.class);
		test = report.createTest("ERM_481_003_Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Other'");
		//--------EAN
		Map<String, String> databaseEanResult = commfun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')",
				"EAN");
			String eanValue = databaseEanResult.get("EAN");
			System.out.println(eanValue);
		//-------Rate
		Map<String, String> databaseRateResult=commfun.database_SelectQuerySingleColumn("SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '"+ eanValue +"')", "RATE_YEAR");
			String rateYearValue = databaseRateResult.get("RATE_YEAR");
			System.out.println(rateYearValue);
			
		//-----------Login
		commfun.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commfun.screenShot("ApplicationLogonPage", "Pass", "Application login successfully");
		sleep(2000);
		commfun.clickMenu("Menu");
		commfun.ScrollMenu("Account Maintenance");
		commfun.clickMenu("Account Maintenance");
		commfun.clickMenu("Maintain Rate");
		sleep(2000);
		commfun.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
		homepage.MaintainRateUpdateContributionRate.click();
		
		//----------SREG 059
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Successfully landed on SREG 059 page");
		commfun.enterTextboxContains("Employer Registration Number", eanValue);
	    commfun.enterTextboxContains("Rate Year*", rateYearValue);
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Successfully entered deatils and  click on continue");
		commfun.clickButton("Continue ");
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Successfully landed on SREG 060 page");
		
		//----------SREG 060
		commfun.selectDropdown("Lapsed Balance Revision", " 2021 ");
		commfun.clearTextboxContains("Opening Balance ($)");
		commfun.enterTextboxContains("Opening Balance ($)", "567893");
		commfun.enterTextboxContains("Contributions ($)", "673889");
		commfun.enterTextboxContains("Charges ($)", "345678");
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Entered details on SREG 060 page");
		commfun.enterTextboxContains("General Account Transfer ($)", "456789");
		commfun.enterTextboxContains("Current Year Wages", "762023");
		commfun.enterTextboxContains("1st Prior Year Wages", "654321");
		commfun.enterTextboxContains("2nd Prior Year Wages", "567894");
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Entered details on SREG 060 page");
		commfun.enterTextboxContains("3rd Prior Year Wages", "654321");
		commfun.enterTextboxContains("4th Prior Year Wages", "543215");
		commfun.selectDropdown("Override Reasons"," Other ");
		homepage.reasonId_SREG060.sendKeys("Testing");
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Entered details on SREG 060 page and click on continue");
		commfun.clickButton("Continue ");
		
		//----------SREG 061
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Successfully Landed on 061 Page");
		commfun.selectRadioQuestions("Generate Rate Statement","No ");
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Entered details on 061 Page and clicked on submit");
		commfun.clickButton("Submit ");
		
		//----------SUG 002
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Successfully landed on SUC 002 page");
		commfun.clickButton("Home ");
		
		//----------Home
		sleep(2000);
		commfun.screenShot("UnemploymentInsuranceServiePage", "Pass", "Successfully landed on Home Page");
		commfun.clickMenu("Menu");
		commfun.ScrollMenu("Inquiry");
		commfun.clickMenu("Inquiry");
		commfun.clickMenu("Contribution Inquiry");
		sleep(2000);
		commfun.screenShot("UnemploymentInsuranceServiePage", "Pass", "Successfully clicked on menu's");
		commfun.clickMenu("Inquiry Employer Account");
		sleep(2000);
		commfun.screenShot("UnemploymentInsuranceServiePage", "Pass", "Successfully landed on SREG 050 page");
		commfun.enterTextboxContains("Employer Registration Number", eanValue);
	
		sleep(2000);
		commfun.screenShot("UnemploymentInsuranceServiePage", "Pass", "Successfully entered deatils on 050 and  click on continue");
		commfun.clickButton("Continue ");
		
		//----------SREG - 051
		sleep(2000);
		commfun.screenShot("inquiryEmploymentPage", "Pass", "Successfully landed on SREG 051 page");
		commfun.selectTableParameterizedId("2022", 8 , 1 , "Rate History", "employerRateHistory");
		
		//----------SREG -052
		sleep(2000);
		commfun.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
		commfun.selectTableWithoutId("Administrative Decision", 2, 2, "Employer Account Rate Transaction History ");
		
		//----------SREG - 014
		sleep(2000);
		commfun.screenShot("TransactionIngredientstPage", "Pass", "Successfully landed on SREG 014 page");
		commfun.clickButton("Previous ");
		
		//----------SREG -052
	    sleep(2000);
		commfun.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
		commfun.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
		
		//----------SREG -063
		sleep(2000);
		commfun.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");
		commfun.clickButton("Previous ");
		commfun.clickButton(" Previous ");
		
		//----------SREG -051
	    sleep(2000);
		commfun.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		commfun.selectTableParameterizedId("2022", 1, 1, "Rate History", "employerRateHistory");
		
		sleep(2000);
		
		//----------SREG -013
		commfun.screenShot("CurrentRatingAccountStatusHistoryPage", "Pass", "Successfully landed on SREG 013 page");
		sleep(2000);
		
		//--------ERM -013
		sleep(2000);
		commfun.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		commfun.selectTableParameterizedId("2022", 8 , 1 , "Rate History", "employerRateHistory");
		sleep(2000);
		commfun.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
		commfun.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
		sleep(2000);
		commfun.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");
		commfun.selectTableWithoutId("Current Year Wages", 2, 3, "Ledger after Transaction");
		sleep(2000);
		commfun.screenShot("EstimatedWgaesPage", "Pass", "Successfully landed on ERM 030 Page");
		
		//----------Home
		commfun.clickButton(" Home ");
		sleep(2000);
		commfun.screenShot("UnemploymentInsuranceServiePage", "Pass", "Successfully landed on Home Page");
		
	}

}
