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
public class ERM_408_03_001 extends TestBase {

	@Test(priority=1, description = "ERM.408.03.001 - Verify CSR can review and verify system calculated rate when a Beginning Liability Date change future date.(rate increase or decrease)",groups = {"Regression"})
	public void ERM_408_03_00() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("ERM.408.03.001 - Verify CSR can review and verify system calculated rate when a Beginning Liability Date change future date.(rate increase or decrease)");
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT WHERE EAN IS NOT NULL","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);
        
		

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        commonFuntions.clickMenu("Menu");sleep();
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Inquiry Employer Account");
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Inquiry Employer Account - Enter ERN screen launched");
		sleep();
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information screen Launched");
		commonFuntions.clickOnLink("Rate History");
		sleep(2000);
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History screen Launched");
//		commonFuntions.clickOnLinkAnchorTag("Subject Date Changes");
		sleep(2000);
		commonFuntions.screenShot("Transaction Ingredients", "Pass", "Transaction Ingredients screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History screen Launched");
		commonFuntions.clickOnLink("Ledger");
		sleep(2000);
		commonFuntions.screenShot("Ledger after Transaction", "Pass", "Ledger after Transaction screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);
		commonFuntions.screenShot("Employer Account Rate Transaction History", "Pass", "Employer Account Rate Transaction History screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep(2000);
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information screen Launched");
		commonFuntions.clickOnLink("2023");
		sleep();
		commonFuntions.screenShot("Current Rating Account Status History", "Pass", "Current Rating Account Status History screen Launched");
		commonFuntions.clickButtonContains("Previous ");
		sleep();
		
}
}
