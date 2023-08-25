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
public class ERM_408_13_001 extends TestBase {

	@Test(priority=1, description = "ERM.408.13.001 - Verify system calculated rate when CSR process the void transfer and reason of voiding transfer is \"Effective Date Change",groups = {"Regression"})
	public void ERM_408_13_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
        Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT TEA.EMPLOYER_ACCOUNT_ID,* FROM T_EMPLOYER te JOIN T_EMPLOYER_ACCOUNT tea ON TEA.EAN = TE.EAN WHERE TEA.REGISTRATION_STATUS = 'C' AND TE. CHARGEABILITY_TYPE = 'RATD' AND TEA.ACCOUNT_STATUS = 'LIAB' ORDER BY TEA.CREATED_TS DESC","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);
        
		test = report.createTest("ERM.408.13.001 - Verify system calculated rate when CSR process the void transfer and reason of voiding transfer is \"Effective Date Change");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndsbb3","Brijen@1234567");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        

        //precondition for EM_414.004 Testcase
        commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Business Acquisition");
		commonFuntions.clickMenu("Business Acquisition");
		sleep();
		
		//---EM-011---
		commonFuntions.screenShot("Business Acquisition – Enter ERN", "Pass", "Business Acquisition – Enter ERN (EM-017)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
		commonFuntions.screenShot("Business Acquisition – Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		
		//---SREG-011---
		commonFuntions.screenShot("Business Acquisition", "Pass", "Business Acquisition (EM-017)screen launched");
		commonFuntions.selectRadio("Yes ");
		empPage.eanBeanId.sendKeys(eanValue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.selectRadio("PART");
		commonFuntions.enterTextboxContains("Acquisition Date", "4/1/2023");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep(1000);
		commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		sleep(1000);
		commonFuntions.screenShot("Details Entered", "Pass", "Details Entered (SREG-011)screen launched");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("POP UP", "Pass", "POP UP launched 'Please Validate Transfer Rules'");
		commonFuntions.clickButtonContains(" Yes ");
		sleep(2000);
		
		//---EM-006---
		commonFuntions.screenShot("Business Acquisition Details", "Pass", "Business Acquisition Details (EM-006)screen launched");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		
		//---SUC-002---
		commonFuntions.screenShot("Business Acquisition Confirmation", "Pass", "Business Acquisition Confirmation (SUC-002)screen launched");
		commonFuntions.clickButtonContains("Home ");
		sleep(2000); 
		
		//EM_414.004-Testcase
		 commonFuntions.clickMenu("menu");
		 commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.ScrollMenu("Employer Account Maintenance");
		 commonFuntions.clickMenu("Employer Account Maintenance");
		 commonFuntions.ScrollMenu("Void Transfer");
		 commonFuntions.clickMenu("Void Transfer");
		
		//---SREG-541---
		 commonFuntions.screenShot("Void Transfer", "Pass", "Void Transfer (SREG-541)screen launched");
	     commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
	     commonFuntions.clickButton(" Search ");
	     sleep(2000);
	     commonFuntions.selectRadio("");
	     commonFuntions.selectDropdown("Reason For Voiding Transfer", " Effective Date Change ");
	     commonFuntions.enterCommentBoxContains("");
	     commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		 sleep(1000);
		 commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		 sleep(1000);
		 commonFuntions.screenShot("Details Entered", "Pass", "Details Entered (SREG-011)screen launched");
		 commonFuntions.clickButton("Void Transfer ");
		 sleep(2000);
		 
		// --- SUC-002 ---
		 commonFuntions.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
		 commonFuntions.clickButton("Home ");
		 sleep(2000);
		 
		//ERM-Testcase
        commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.ScrollMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.ScrollMenu("Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		sleep();
		
		//---SREG-050---
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Inquiry Employer Account - Enter ERN (SREG-050)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		sleep(2000);
		
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
		commonFuntions.clickOnLinkAnchorTag("Subject Date Changes");
		sleep(2000);
				
		//---ERM-014---
		commonFuntions.screenShot("Transaction Ingredients", "Pass", "Transaction Ingredients (ERM-014)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);
				
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		sleep(2000);
		
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
		commonFuntions.clickOnLinkAnchorTag("Ledger");
		sleep(2000);
				
		//---SREG-063---
		commonFuntions.screenShot("Ledger after Transaction", "Pass", "Ledger after Transaction (SREG-063)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);
				
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);	
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("2023");
		sleep();
		
		//---ERM-013---
		commonFuntions.screenShot("Current Rating Account Status History", "Pass", "Current Rating Account Status History (ERM-013)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep();
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLinkAnchorTag(" Bank Accounts Inquiry ");
		sleep();
				
		//---TWR-269---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Bank Accounts Inquiry (SREG-051)screen Launched");
		commonFuntions.clickButtonContains("Previous ");	
				
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("2023");
		sleep();
		
		//---ERM-013---
		commonFuntions.screenShot("Current Rating Account Status History", "Pass", "Current Rating Account Status History (ERM-013)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep();
		
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		sleep(2000);
				
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
		commonFuntions.clickOnLinkAnchorTag("Ledger");
		sleep(2000);
				
		//---SREG-063---
		commonFuntions.screenShot("Ledger after Transaction", "Pass", "Ledger after Transaction (SREG-063)screen Launched");
        commonFuntions.clickOnLink("0");
		
		//---ERM-030---
		commonFuntions.screenShot("Estimated Wages", "Pass", "Estimated Wages (SREG-063)screen Launched");
		commonFuntions.clickButton(" Home ");
		sleep(2000);
		
		commonFuntions.screenShot("Home", "Pass", "Home");
		commonFuntions.clickMenu("menu");
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
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);

		//scripting is done correspondence validation we can not automate
		
		
	}
	
	
	
	
}
