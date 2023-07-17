//------------------------Palak---------------------------------------

package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_541;
import com.ui.pages.SREG_EM_mod;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_408_11_001_Verify_system_calculated_rate_when_CSR_process_void_transfer_with_reason_of_voiding_transfer_is_Effective_Date_Change extends TestBase {
	@Test
	public void ERM_408_11_001() throws Exception
	{
test = report.createTest("ERM_408_11_00_Verify system calculated rate when CSR process void transfer with reason of voiding transfer is \"Effective Date Change\"");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement();
		SREG_541 sreg = new SREG_541(driver);		
		// DB Query
				// Valid ERN
				Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
						"SELECT EAN FROM T_EMPLOYER te join T_EMPLOYER_TRANSFER tet ON TET.FROM_EMPLOYER_ID = te.EMPLOYER_ID AND te.ean ='8026207' ORDER BY te.UPDATED_TS DESC",
						"EAN");
				String eanValue = databaseEanResult.get("EAN");
				System.out.println(eanValue);

				// Valid Rate Year
				Map<String, String> databaseRateYearResult = commonFunction.database_SelectQuerySingleColumn(
						"SELECT * FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '" + eanValue + "')AND RATE_YEAR = '2023'",
						"RATE_YEAR");
				String rateYearValue = databaseRateYearResult.get("RATE_YEAR");
				System.out.println(rateYearValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		//--------TC: ERM.414.001
		//---Menu Click---
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		sleep(2000);
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Maintain Accounts");
		commonFunction.ScrollMenu("Void Transfer");
		commonFunction.clickMenu("Void Transfer");
		sleep(2000);
		
        //SREG 541
		commonFunction.screenShot("Void Transfer", "Pass", "Successfully landed on SREG 541");
		commonFunction.enterTextboxContains("Employer Registration Number",eanValue);
		//sreg.employerRegistrationNoText.sendKeys(eanValue);
		commonFunction.clickButtonContains(" Search ");
		
		sleep(2000);
		commonFunction.screenShot("Void Transfer", "Pass", "entered ERN on SREG 541");
		
		commonFunction.selectRadioInTable("Select", 1, 1, "Void Transfer");
		sreg.selectRadioButton.click();
		//commonFunction.selectRadio("Select");
		sleep(2000);
		commonFunction.selectDropdown("Reason For Voiding Transfer", " Effective Date Change ");
		sleep(2000);
		sreg.enterValidComment.sendKeys("Testing");
		/*sreg.sourceDropdown.isSelected();
		sreg.sourceDropdownValue.click();
		sleep(2000);
		sreg.sourceTypeDropdown.isSelected();
		sreg.sourceTypeDropdownValue.click();*/
		commonFunction.selectDropdown("Source", " Correspondence/Email ");
		sleep(2000);
		commonFunction.selectDropdown("Source Type", " Correspondence/Email ");
		
		commonFunction.clickButtonContains("Void Transfer ");
		
		// --- SUC-002 ---
		sleep(2000);
		commonFunction.screenShot("Employer Account Maintenance Confirmation", "Pass", "Successful launch to SUC-002 page");
		commonFunction.clickButton("Home ");
		//----- Home -----
		sleep(2000);
		commonFunction.screenShot("SuccessPage", "Pass", "Launched to Home page");
		
		
		//--------Menu----
		
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Inquiry");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Contribution Inquiry");
		sleep(2000);
		commonFunction.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Inquiry Employer Account");
		
		//----------SREG 050
		sleep(2000);
		commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully landed on SREG 050 page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully entered deatils and  click on continue");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		
	//data issue rate data not showing after submitting void tranasfer EM tc 
		//blocked
		
		
		//----------SREG 051
		commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "Successfully landed on SREG 051 page");
		sleep(2000);
		commonFunction.selectTableWithoutId("2023", 8, 1, "Rate History");
		commonFunction.waitForLoadingIconToDisappear();
		//sleep(2000);

		//----------SREG -052
		sleep(2000);
		commonFunction.screenShot("Employer Account Rate Transaction History", "Pass", "Successfully landed on SREG 052 page");
		sleep(2000);
		commonFunction.selectTableWithoutId("Subject Date Changes", 2, 2, "Employer Account Rate Transaction History ");
		commonFunction.waitForLoadingIconToDisappear();
		//----------ERM - 014
		sleep(2000);
		commonFunction.screenShot("TransactionIngredientstPage", "Pass", "Successfully landed on SREG 014 page");
		commonFunction.clickButton("Previous ");
		commonFunction.waitForLoadingIconToDisappear();
		//sleep(2000);
		commonFunction.clickButton(" Previous ");
		commonFunction.waitForLoadingIconToDisappear();
		//sleep(2000);
		//------SREG 051
		commonFunction.selectTableWithoutId("2023", 8, 1, "Rate History");
		//sleep(2000);
		commonFunction.waitForLoadingIconToDisappear();
		//----------SREG -052
	    sleep(2000);
	    commonFunction.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
	    commonFunction.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
	    commonFunction.waitForLoadingIconToDisappear();
	    
	  //----------SREG -063
	    commonFunction.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");
	    commonFunction.clickButton("Previous ");
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.clickButton(" Previous ");
	    commonFunction.waitForLoadingIconToDisappear();
	   
		//----------SREG -051
	    sleep(2000);
	    commonFunction.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		//commfun.selectTableParameterizedId("2022", 1, 1, "Rate History", "employerRateHistory");
	    commonFunction.selectTableWithoutId("2023", 1, 1, "Rate History");
	    commonFunction.waitForLoadingIconToDisappear();
		
		//----------ERM -013
		commonFunction.screenShot("CurrentRatingAccountStatusHistoryPage", "Pass", "Successfully landed on SREG 013 page");
		sleep(2000);
		commonFunction.clickButton("Previous ");
		commonFunction.waitForLoadingIconToDisappear();
		
		//--------SREG 051
		sleep(2000);
		commonFunction.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		sleep(2000);
		commonFunction.clickOnLinkAnchorTag(" Bank Accounts Inquiry ");
		commonFunction.waitForLoadingIconToDisappear();
		//--------TWR-269
		sleep(2000);
		commonFunction.screenShot("Bank Accounts Inquiry", "Pass", "Successfully landed on SREG TWR-269");
		sleep(2000);
		commonFunction.clickButton("Previous ");
		commonFunction.waitForLoadingIconToDisappear();
	
		//--------SREG 051
		
		commonFunction.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		sleep(2000);
		commonFunction.selectTableWithoutId("2023", 1, 1, "Rate History");
		commonFunction.waitForLoadingIconToDisappear();
		
		//----------ERM -013
		commonFunction.screenShot("CurrentRatingAccountStatusHistoryPage", "Pass", "Successfully landed on SREG 013 page");
		sleep(2000);
		commonFunction.clickButton("Previous ");
		
         //--------SREG 051
		
		commonFunction.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		sleep(2000);
		commonFunction.selectTableWithoutId("2023", 8, 1, "Rate History");
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
		commonFunction.screenShot("Home page", "Pass", "Successfully landed on Home Page");
		
		//done till correspondence
		
System.out.println("pass");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		
		commonStepDefinitions commfun = new commonStepDefinitions();
		HomePage homepage = new HomePage(driver);
		PEOPage peopage = PageFactory.initElements(driver,PEOPage.class);
		test = report.createTest("ERM_408_03_002_ERM.408.03.002.Verify CSR can update 'Beginning Liability Date, and verify the rate which will recalculated into a previous quarter.(rate increase)");
		
		//--------EAN
		//Map<String, String> databaseEanResult = commfun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2022')",
				//"EAN");
			//String eanValue = databaseEanResult.get("EAN");
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
		commfun.ScrollMenu("Inquiry");
		commfun.clickMenu("Inquiry");
		commfun.ScrollMenu("Inquiry");
		commfun.clickMenu("Contribution Inquiry");
		commfun.ScrollMenu("Inquiry");
		commfun.clickMenu("Inquiry Employer Account");
		sleep(2000);
		commfun.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
		homepage.MaintainRateUpdateContributionRate.click();
		
		//----------SREG 050
		sleep(2000);
		commfun.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully landed on SREG 050 page");
		commfun.enterTextboxContains("Employer Registration Number", eanValue);
	    //commfun.enterTextboxContains("Rate Year*", rateYearValue);
	    //commfun.enterTextbox("Rate Year*", rateYearValue);
		sleep(2000);
		commfun.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully entered deatils and  click on continue");
		commfun.clickButton("Continue ");
		sleep(2000);
		commfun.screenShot("UpdateConrtibutionPage ", "Pass", "Successfully landed on SREG 060 page");
		
		//----------SREG 060
		commfun.enterTextboxContains("Lapsed Balance Revision", "2023");
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
		//commfun.selectTableParameterizedId("2022", 8 , 1 , "Rate History", "employerRateHistory");
		commfun.selectTableWithoutId("2023", 8, 1, "Rate History");
		//----------SREG -052
		sleep(2000);
		commfun.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
		commfun.selectTableWithoutId("Administrative Decision", 2, 2, "Employer Account Rate Transaction History ");
		
		//----------ERM - 014
		sleep(2000);
		commfun.screenShot("TransactionIngredientstPage", "Pass", "Successfully landed on SREG 014 page");
		commfun.clickButton("Previous ");
		
		//----------SREG -052
	    sleep(2000);
		commfun.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
		commfun.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
		
		//----------SREG -063
		sleep(2000);
		commonFu.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");
		commfun.clickButton("Previous ");
		commfun.clickButton(" Previous ");
		
		//----------SREG -051
	    sleep(2000);
		commfun.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		//commfun.selectTableParameterizedId("2022", 1, 1, "Rate History", "employerRateHistory");
		commfun.selectTableWithoutId("2023", 1, 1, "Rate History");
		sleep(2000);
		
		//----------ERM -013
		commfun.screenShot("CurrentRatingAccountStatusHistoryPage", "Pass", "Successfully landed on SREG 013 page");
		sleep(2000);
		commfun.clickButton("Previous ");
		;
		//--------SREG 051
		sleep(2000);
		commfun.screenShot("InquiryEmployerAccountInformationPage", "Pass", "Successfully landed on SREG 051 page");
		sleep(2000);
		commfun.selectTableWithoutId("2023", 8, 1, "Rate History");
		sleep(2000);
		commfun.screenShot("inquiryEmployerAccountPage", "Pass", "Successfully landed on SREG 052 page");
		sleep(2000);
		commfun.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
		sleep(2000);
		commfun.screenShot("LedgerfAterTransactionPage", "Pass", "Successfully landed on SREG 063 page");

		sleep(2000);
		commfun.selectTableWithoutId("Current Year Wages", 2, 3, "Ledger after Transaction");
		sleep(2000);
		commfun.screenShot("EstimatedWgaesPage", "Pass", "Successfully landed on ERM 030 Page");
		
		//----------Home
		commfun.clickButton(" Home ");
		sleep(2000);
		commfun.screenShot("UnemploymentInsuranceServiePage", "Pass", "Successfully landed on Home Page");
	*/
	}

}
