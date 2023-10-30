package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class ERM_408_09_001 extends TestBase {

	@Test(priority=1, description = "ERM.408.09.001.Verify system calculated rate when CSR process 'Partial Transfers (100%) - Concurrent Transfer' and validate Partial Transfer business rules (no task created) and the transfer is processed (Have Own experience  before the transfer)",groups = {"Regression"})
	public void ERM_408_09_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
	  Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT TEA.EMPLOYER_ACCOUNT_ID,* FROM T_EMPLOYER te JOIN T_EMPLOYER_ACCOUNT tea ON TEA.EAN = TE.EAN WHERE TEA.REGISTRATION_STATUS = 'C' AND TE. CHARGEABILITY_TYPE = 'RATD' AND TEA.ACCOUNT_STATUS = 'LIAB' ORDER BY TEA.CREATED_TS DESC","EAN");
        String eanvalue = databaseEanResult.get("EAN");
        System.out.println(eanvalue);
        
		test = report.createTest("ERM.408.09.001.Verify system calculated rate when CSR process 'Partial Transfers (100%) - Concurrent Transfer' and validate Partial Transfer business rules (no task created) and the transfer is processed (Have Own experience  before the transfer)");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.Registration_Specialist, COMMON_CONSTANT.Registration_Specialist_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		
        //precondition
        commonFuntions.clickMenu("Menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Business Acquisition");
		commonFuntions.clickMenu("Business Acquisition");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---EM-011---
		commonFuntions.screenShot("Business Acquisition – Enter ERN", "Pass", "Business Acquisition – Enter ERN (EM-011)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanvalue);
		commonFuntions.screenShot("Business Acquisition – Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-011---
		commonFuntions.screenShot("Business Acquisition", "Pass", "Business Acquisition (SREG-011)screen launched");
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?","Yes ");
		empPage.eanBeanId.sendKeys(eanvalue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?","PART");
		commonFuntions.enterTextboxContains("Acquisition Date", "4/1/2023");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep(1000);
		commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		sleep(1000);
		commonFuntions.screenShot("Details Entered", "Pass", "Details Entered (SREG-011)screen launched");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("POP UP", "Pass", "POP UP launched 'Please Validate Transfer Rules'");
		commonFuntions.clickButtonContains(" Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---EM-006---
		commonFuntions.screenShot("Business Acquisition Details", "Pass", "Business Acquisition Details (EM-006)screen launched");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SUC-002---
		commonFuntions.screenShot("Business Acquisition Confirmation", "Pass", "Business Acquisition Confirmation (SUC-002)screen launched");
		commonFuntions.clickButtonContains("Home ");
		sleep(2000); 
		
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.Employer_Account_Specialist, COMMON_CONSTANT.Employer_Account_Specialist_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		//ERM-Testcase
        commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Partial Transfer");
		commonFuntions.clickMenu("Partial Transfer");
		commonFuntions.ScrollMenu("Process Partial Transfer");
		commonFuntions.clickMenu("Process Partial Transfer");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-017---
		commonFuntions.screenShot("Process Partial Transfer - Enter ERN", "Pass", "Process Partial Transfer - Enter ERN (SREG-017)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanvalue);
		commonFuntions.screenShot("Process Partial Transfer - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-018---
		commonFuntions.screenShot("Process Partial Transfer", "Pass", "Process Partial Transfer (SREG-018)screen Launched");
		empPage.transferDetailsTable_checkbox.click();
		empPage.transferDetailsTable_Dateoftransfer_hyperlink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-019---
		commonFuntions.screenShot("Transfer Details", "Pass", "Transfer Details (SREG-019)screen Launched");	
		commonFuntions.enterTextboxContains(" 5. Percentage of Transfer ", "0.500");
		commonFuntions.enterTextboxContains(" a. The average number of employees working in the transferred part of business? ", "50");
		commonFuntions.enterTextboxContains(" b. The average number of employees working in the entire business? ", "100");
		commonFuntions.enterTextboxContains(" 9. From the first day of the calendar quarter in which the transfer occurred to the date of", "65420");
		commonFuntions.selectRadioQuestions(" 10. Transfer is Timely or Untimely ","Timely");
		commonFuntions.enterTextareaContains("Comments ", "XYS");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
				
		//---ERM-018---
		commonFuntions.screenShot("Process Partial Transfer", "Pass", "Process Partial Transfer (SREG-018)screen Launched");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
				
		//---SUC-002---
		commonFuntions.screenShot("Process Partial Transfer Confirmation", "Pass", "Process Partial Transfer Confirmation (SUC-002)screen Launched");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.ScrollMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.ScrollMenu("Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-050---
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Inquiry Employer Account - Enter ERN (SREG-050)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanvalue);
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
				
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		commonFuntions.waitForLoadingIconToDisappear();
			
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
		commonFuntions.clickOnLinkAnchorTag("Partial Transfers - Concurrent");
		commonFuntions.waitForLoadingIconToDisappear();
					
		//---ERM-014---
		commonFuntions.screenShot("Transaction Ingredients", "Pass", "Transaction Ingredients (ERM-014)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		commonFuntions.waitForLoadingIconToDisappear();
						
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		commonFuntions.waitForLoadingIconToDisappear();
				
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		commonFuntions.waitForLoadingIconToDisappear();
			
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
		commonFuntions.clickOnLinkAnchorTag("Ledger");
		sleep(2000);
						
		//---SREG-063---
		commonFuntions.screenShot("Ledger after Transaction", "Pass", "Ledger after Transaction (SREG-063)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		commonFuntions.waitForLoadingIconToDisappear();
						
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		commonFuntions.waitForLoadingIconToDisappear();
			
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("2023");
		commonFuntions.waitForLoadingIconToDisappear();
				
		//---ERM-013---
		commonFuntions.screenShot("Current Rating Account Status History", "Pass", "Current Rating Account Status History (ERM-013)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-051---
	    commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLinkAnchorTag(" Bank Accounts Inquiry ");
		commonFuntions.waitForLoadingIconToDisappear();
					
		//---TWR-269---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Bank Accounts Inquiry (SREG-051)screen Launched");
		commonFuntions.clickButtonContains("Previous ");	
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("2023");
		commonFuntions.waitForLoadingIconToDisappear();
			
		//---ERM-013---
		commonFuntions.screenShot("Current Rating Account Status History", "Pass", "Current Rating Account Status History (ERM-013)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		commonFuntions.waitForLoadingIconToDisappear();
				
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		commonFuntions.waitForLoadingIconToDisappear();
						
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
		commonFuntions.clickOnLinkAnchorTag("Ledger");
		commonFuntions.waitForLoadingIconToDisappear();
		
		//---SREG-063---
		commonFuntions.screenShot("Ledger after Transaction", "Pass", "Ledger after Transaction (SREG-063)screen Launched");
		commonFuntions.clickOnLink("0");
		commonFuntions.waitForLoadingIconToDisappear();
				
		//---ERM-030---
		commonFuntions.screenShot("Estimated Wages", "Pass", "Estimated Wages (SREG-063)screen Launched");
		commonFuntions.clickButton(" Home ");
		commonFuntions.waitForLoadingIconToDisappear();
				
		commonFuntions.screenShot("Home", "Pass", "Home");
		commonFuntions.clickMenu("Menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.ScrollMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.ScrollMenu("Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		commonFuntions.ScrollMenu("View Correspondence");
		commonFuntions.clickMenu("View Correspondence");
			
		//---DMS-001---
		commonFuntions.screenShot("Search Document", "Pass", "Search Document (DMS-001)screen Launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanvalue);

		//scripting is done correspondence validation we can not automate
				
				
		
	}
	
	
	
	
}
