package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

public class EM_319_001_CSR_Can_Edit_Location_Charge extends TestBase {
	
	@Test
	public void EM_319_001() throws Exception {
		commonStepDefinitions cf= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		test = report.createTest("EM.319.001 - Verify CSR is able to view and Edit Additional Charging Location Address");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		cf.login("ndfjp3", "Admin@12345678");
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		sleep();
		cf.ScrollMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Address");
		sleep(40000);
		
		/*----------------SREG-020------------------*/
		cf.clickButtonContains("Continue ");
		sleep();
		cf.errorLabel("Required");
		cf.enterTextboxContains("Employer Registration Number", "4772");
		cf.errorLabel("Length of this response must be at least 7 characters.");
		cf.enterTextboxContains("Employer Registration Number", "1238684");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		
		Map<String, String> ernOutput = cf.database_SelectQuerySingleColumn("SELECT EAN  FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS", "EAN");
		String ernValue = ernOutput.get("EAN");
		
		cf.enterTextboxContains("Employer Registration Number", ernValue);
		cf.clickButtonContains("Continue ");
		sleep(4000);
		
		/*----------------SREG-486------------------*/
		
		cf.clickOnLinkAnchorTag("View Additional Charging Location(s)");
		sleep();
		
		/*----------------EM-008------------------*/
		
		cf.clickOnLinkAnchorTag("Edit");
		cf.waitForLoadingIconToDisappear();
		
		/*----------------EM-009------------------*/
		
		cf.enterTextboxContains("Address Line 1 ", "New Address 1");
		cf.enterTextboxContains("City ", "yt");
		cf.enterTextboxContains("Zip Code", "34234");
		cf.selectDropdown("Status", " Active ");
		cf.selectDropdown("Source", " IA602 ");
		cf.selectDropdown("Source Type", " Correspondence ");
		cf.clickButton("Submit ");
		
		/*----------------EM-009------------------*/
		
		cf.clickButton("Previous ");
		
		/*----------------SREG-486------------------*/
		
		cf.clickButton("Submit ");
		sleep();
		cf.errorLabel("Required");
		sleep();
		cf.selectDropdown("Source", " IA602 ");
		cf.selectDropdown("Source Type", " Correspondence/Email ");
		cf.enterCurrentDate("SIDES E-Response Effective End Date");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
