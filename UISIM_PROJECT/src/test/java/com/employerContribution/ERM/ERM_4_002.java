package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class ERM_4_002 extends TestBase {

	@Test(priority=1, description = "ERM.4.002.Verify Employer can view the Employer’s account information and the previous and current year rate history information",groups = {"Regression"})
	public void ERM_4_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("ERM.4.002.Verify Employer can view the Employer’s account information and the previous and current year rate history information");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_1, COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
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
		commonFuntions.enterTextboxContains("Employer Registration Number" ,"4570058");
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		sleep(2000);
		
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		sleep();
		
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
		sleep();		
		
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("2023");
		sleep(2000);
				
		//---ERM-013---
		commonFuntions.screenShot("Current Rating Account Status History", "Pass", "Current Rating Account Status History (ERM-013)screen Launched");
		commonFuntions.clickButtonContains("Home ");
		sleep();
				
		commonFuntions.screenShot("Home", "Pass", "Home");
		commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.ScrollMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.ScrollMenu("Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		
		//---SREG-050---
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Inquiry Employer Account - Enter ERN (SREG-050)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,"4570058");
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
				
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
		commonFuntions.clickOnLink("Rate History");
		sleep(2000);
				
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");
		commonFuntions.clickOnLinkAnchorTag("2023");
		
		//---INQ-200---
		commonFuntions.screenShot("Inquiry Employer Account Experience for Annual Rate Calculation", "Pass", "Inquiry Employer Account Experience for Annual Rate Calculation(INQ-200)screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		
		//---SREG-052---
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History (SREG-052)screen Launched");	
//		commonFuntions.clickButtonContains("Previous ");
//		sleep(2000);
				
		//---SREG-051---
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
	    
		//Done
	}
}
