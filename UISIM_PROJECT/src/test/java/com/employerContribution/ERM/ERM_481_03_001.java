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
public class ERM_481_03_001 extends TestBase {

    @Test(priority=1, description = "ERM.481.03.001 - Verify CSR can enter ERN for purging a rate",groups = {"Regression"})
	public void ERM_481_03_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("ERM.481.03.001-Verify CSR can enter ERN for purging a rate");
	
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        commonFuntions.clickMenu("Menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Maintain Rate");
		commonFuntions.clickMenu("Maintain Rate");
		commonFuntions.ScrollMenu("Purge Rate");
		commonFuntions.clickMenu("Purge Rate");
		commonFuntions.screenShot("Purge Employer Rate - Enter ERN", "Pass", "Purge Employer Rate - Enter ERN screen launched");
		sleep();
		commonFuntions.enterTextboxContains("Employer Registration Number" ,"1111111");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("invalid ERN", "Pass", "Message popup invalid ERN");
		sleep();
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Purge Employer Rate", "Pass", "Purge Employer Rate screen Launched");
		sleep(2000);
		commonFuntions.clickButtonContains("Purge ");
		commonFuntions.screenShot("Purge Employer Rate Confirmation", "Pass", "Purge Employer Rate Confirmation screen Launched");
        sleep();
        commonFuntions.clickButtonContains("Home ");
        commonFuntions.screenShot("Home Page", "Pass", "Home Page Launched");
        sleep(2000);
        
        
		

		
		
		

	}
}
