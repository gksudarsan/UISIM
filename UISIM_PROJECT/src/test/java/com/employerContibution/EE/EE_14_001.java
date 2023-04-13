//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_14_001 extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100A sent to NYBE.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EE_14_001() throws Exception {
		
		test = report.createTest("Verify CSR is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100A sent to NYBE.");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		
		//--- DB SELECT Query ---
		/*
		 * Map<String, String> databaseFeinResult = commonFunction.
		 * database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 ORDER BY UPDATED_TS DESC"
		 * ,"FEIN"); String feinValue = databaseFeinResult.get("FEIN");
		 * System.out.println("Thw FEIN is " + feinValue);
		 */
		
		/*
		 * Map<String, String> databaseEanResult = commonFunction.
		 * database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC"
		 * ,"EAN"); String eanValue = databaseEanResult.get("EAN");
		 * System.out.println("The EAN is " + eanValue);
		 */
		
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//--- Menu Click ---
		commonFunction.clickMenu("Menu");
		commonFunction.clickMenu("Inquiry");
		commonFunction.clickMenu("Contribution Inquiry");
		commonFunction.screenShot("Menu", "Pass", "Menu - Contribution Inquiry - PEO Client Non-liable Employer information");
		commonFunction.clickMenu("Employer Search");
		commonFunction.screenShot("EmployerSearchPage", "Pass", "Launched SREG-077 page");
		sleep();
		
		//--- SREG-077 --- empty search
		commonFunction.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage1", "Pass", "Clicked on Advanced Search button and searched");
		
		//--- SREG-077 --- EAN search
		driver.navigate().refresh();
		commonFunction.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		commonFunction.screenShot("EmployerSearchPage1", "Pass", "Clicked on Advanced Search button and searched with ERN");
		commonFunction.enterTextboxContains("Employer Registration Number", "0450052");
		//commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		//commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage2", "Pass", "Searched criteria is EAN");
		sleep(3000);
		
		//--- SREG-077 --- Legal name contains search
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.enterTextboxContains("Legal Name of Business", "l");
		commonFunction.screenShot("EmployerSearchPage3", "Pass", "Searched criteria is 'l' contained in Legal business name");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage3", "Pass", "Searched result for 'l' contained in Legal business name");
		sleep(3000);
		
		//--- SREG-077 --- Searched with FEIN and Non Liable
		commonFunction.enterTextboxContains("Legal Name of Business", "");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "141783554");
		commonFunction.selectDropdown("Liability", "Non Liable");
		commonFunction.screenShot("EmployerSearchPage4", "Pass", "Searched criteria is FEIN and Liability is Non Liable");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage4", "Pass", "Searched result for FEIN and Non Liable");
		sleep(3000);
		
		//--- SREG-077 --- Searched with AddressLin1 and Liable
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "");
		commonFunction.selectDropdown("Liability", " Liable ");
		commonFunction.enterTextboxContains("Address Line 1", "1232 READING ROAD");
		commonFunction.screenShot("EmployerSearchPage5", "Pass", "Searched criteria is AddressLin1 and Liability is Liable");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage5", "Pass", "Searched result for AddressLin1 and Liable");
		sleep(3000);
		
		//--- SREG-077 --- Searched with AddressLin1, City and Liable
		commonFunction.enterTextboxContains("City", "Albany");
		commonFunction.screenShot("EmployerSearchPage6", "Pass", "Searched criteria is AddressLin1, City and Liability is Liable");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage6", "Pass", "Searched result for AddressLin1, City and Liable");
		sleep(3000);
		
		//--- SREG-077 --- Searched with OwnerName and Liable
		commonFunction.enterTextboxContains("Address Line 1", "");
		commonFunction.enterTextboxContains("City", "");
		commonFunction.enterTextboxContains("Owner Name", "John");
		commonFunction.screenShot("EmployerSearchPage7", "Pass", "Searched criteria is OwnerName and Liability is Liable");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage7", "Pass", "Searched result for OwnerName and Liable");
		sleep(3000);
		
		//--- SREG-077 --- Searched with TradeName/DBA and Liable
		commonFunction.enterTextboxContains("Owner Name", "");
		commonFunction.enterTextboxContains("Trade Name/DBA", "Agriculture Test");
		commonFunction.screenShot("EmployerSearchPage8", "Pass", "Searched criteria is TradeName/DBA and Liability is Liable");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage8", "Pass", "Searched result for TradeName/DBA and Liable");
		sleep(3000);
		
		//--- SREG-077 --- Searched with ERN, FEIN, LegalBusinessName and Liable
		commonFunction.enterTextboxContains("Trade Name/DBA", "");
		commonFunction.enterTextboxContains("Employer Registration Number", "0450240");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "520591623");
		commonFunction.enterTextboxContains("Legal Name of Business", "LOYOLA UNIVERSITY");
		commonFunction.screenShot("EmployerSearchPage9", "Pass", "Searched criteria is ERN, FEIN, LegalBusinessName and Liability is Liable");
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage9", "Pass", "Searched result for ERN, FEIN, LegalBusinessName and Liable");
		sleep(3000);
		
		//--- SREG-077 --- EAN search
		commonFunction.enterTextboxContains("Employer Registration Number", "0450052");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "");
		commonFunction.enterTextboxContains("Legal Name of Business", "");
		commonFunction.screenShot("EmployerSearchPage10", "Pass", "Searched with ERN");
		//commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.clickButtonContains("search");
		commonFunction.screenShot("EmployerSearchPage10", "Pass", "Searched criteria is EAN");
		sleep(3000);
		commonFunction.clickOnLinkBasisClass("ng-tns");
		
		//--- SREG-051 ---
		commonFunction.screenShot("EmployerSearchPage", "Pass", "Launched to Inquiry Employer Account Information(SREG-051) page");
		commonFunction.clickButtonContains("Previous ");
		commonFunction.screenShot("EmployerSearchPage", "Pass", "Launched to Inquiry Employer Account - Enter ERN(SREG-050) page");
		
		//--- SREG-050 ---
		commonFunction.clickButtonContains(" Home ");
		commonFunction.screenShot("EmployerSearchPage", "Pass", "Clicked on Home button on SREG-050 page");
		
		
		commonFunction.screenShot("SuccessPage", "Pass", "Home page reached");
				
	}
}
