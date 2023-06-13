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
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        commonFuntions.clickMenu("Menu");sleep();
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Maintain Rate");
		commonFuntions.clickMenu("Maintain Rate");
		commonFuntions.ScrollMenu("Update Contribution Rate");
		commonFuntions.clickMenu("Update Contribution Rate");
		commonFuntions.screenShot("Update Contribution Rate", "Pass", "Update Contribution Rate screen launched");
		sleep();
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
		commonFuntions.enterTextboxContains("Rate Year" , Yearratevalue); 
		commonFuntions.screenShot("Employer Registration Number and Rate Year", "Pass", "Employer Registration Number and Rate Year entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep();
		commonFuntions.screenShot("Employer Rate Maintenance - Update Contribution Rate", "Pass", "Employer Rate Maintenance - Update Contribution Rate screen launched");
		commonFuntions.selectDropdown("Year " , " 2022 ");
		commonFuntions.selectDropdown("Lapsed Balance Revision" , " 2021 ");
		commonFuntions.enterTextboxContains("Opening Balance ($)" , "77,850.22");
		commonFuntions.enterTextboxContains("Contributions ($)" , "8,560.54");
		commonFuntions.enterTextboxContains("Charges ($)" , "5,600.00");
		commonFuntions.enterTextboxContains("Account Balance ($)" , "80,810.76");
		commonFuntions.enterTextboxContains("General Account Transfer ($)" , "9.006.58");
		commonFuntions.enterTextboxContains("Current Year Wages" , "36,510.00");
		commonFuntions.enterTextboxContains("1st Prior Year Wages" , "36,584.20");
		commonFuntions.enterTextboxContains("2nd Prior Year Wages" , "38,562.40");
		commonFuntions.enterTextboxContains("3rd Prior Year Wages" , "35,789.02");
		commonFuntions.enterTextboxContains("4th Prior Year Wages" , "74,894.14");
		commonFuntions.selectDropdown("Override Reasons", " Account Balance Adjustment ");
		commonFuntions.screenShot("Update Contribution Rate", "Pass", "Data is Entered for Update Contribution Rate");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Verification", "Pass", "Verify update rate");
		commonFuntions.selectRadio("Yes ");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000); 
		commonFuntions.screenShot("Confirmation", "Pass", "Confirmation update rate");
		commonFuntions.clickButtonContains("Home ");
		sleep();
		commonFuntions.screenShot("Home", "Pass", "Home Page");
		sleep(2000);

		

		
		
		

	}
}
