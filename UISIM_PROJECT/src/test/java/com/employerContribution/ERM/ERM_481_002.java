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

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class ERM_481_002 extends TestBase {

	@Test(priority=1, description = " ERM.481.002-Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Account Balance Adjustment'",groups = {"Regression"})
	public void ERM_481_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("ERM.481.002 - Verify CSR is able to add POA/TPR details for additional address Form Type 'Notice of Potential Charges (LO400)'");
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);
        Map<String, String> databaseRateYearResult = commonFuntions.database_SelectQuerySingleColumn("SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '" + eanValue + "')","RATE_YEAR");
        String Yearratevalue = databaseRateYearResult.get("RATE_YEAR");		
        System.out.println(Yearratevalue);
		

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		
		
	    commonFuntions.clickMenu("Menu");sleep();
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.clickMenu("Account Maintenance");sleep();
		commonFuntions.screenShot("Account Maintenance", "Pass", "Click Account Maintenance");
		commonFuntions.clickMenu("Maintain Rate");sleep();
		commonFuntions.clickMenu("Update Contribution Rate");sleep();
		commonFuntions.screenShot("Update Contribution Rate", "Pass", "Click Update Contribution Rate");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);sleep();
		commonFuntions.enterTextboxContains("Rate Year" , Yearratevalue); 
		commonFuntions.clickButtonContains("Continue ");sleep();
		commonFuntions.selectDropdown("Year " , " 2022 ");sleep();
		commonFuntions.selectDropdown("Lapsed Balance Revision" , " 2021 ");sleep();
		commonFuntions.enterTextboxContains("Opening Balance ($)" , "77,850.22");sleep();
		commonFuntions.enterTextboxContains("Contributions ($)" , "8,560.54");sleep();
		commonFuntions.enterTextboxContains("Charges ($)" , "5,600.00");sleep();
		commonFuntions.enterTextboxContains("Account Balance ($)" , "80,810.76");sleep();
		commonFuntions.enterTextboxContains("General Account Transfer ($)" , "9.006.58");sleep();
		commonFuntions.enterTextboxContains("Current Year Wages" , "36,510.00");sleep();
		commonFuntions.enterTextboxContains("1st Prior Year Wages" , "36,584.20");sleep();
		commonFuntions.enterTextboxContains("2nd Prior Year Wages" , "38,562.40");sleep();
		commonFuntions.enterTextboxContains("3rd Prior Year Wages" , "35,789.02");sleep();
		commonFuntions.enterTextboxContains("4th Prior Year Wages" , "74,894.14");sleep();
		commonFuntions.selectDropdown("Override Reasons", " Account Balance Adjustment ");sleep();
		commonFuntions.screenShot("Update Contribution Rate", "Pass", "Enter Update Contribution Rate");
		commonFuntions.clickButtonContains("Continue ");sleep();
		commonFuntions.screenShot("Verification", "Pass", "Verify update rate");
		commonFuntions.selectRadio("Yes ");sleep();
		commonFuntions.clickButtonContains("Submit ");sleep(); 
		commonFuntions.screenShot("Confirmation", "Pass", "Confirmation update rate");
		commonFuntions.clickButtonContains("Home ");sleep();
		commonFuntions.screenShot("Home", "Pass", "Home Page");

		

		
		
		

	}
}
