package com.employerContribution.BCL;

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
public class BCL_421_001 extends TestBase {

	@Test(priority=1, description = "BCL.421.001 - Verify CSR can add a collection hold on the account with reason for hold is Hearing Pending",groups = {"Regression"})
	public void BCL_421_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
        Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT TEA.EMPLOYER_ACCOUNT_ID,* FROM T_EMPLOYER te JOIN T_EMPLOYER_ACCOUNT tea ON TEA.EAN = TE.EAN WHERE TEA.REGISTRATION_STATUS = 'C' AND TE. CHARGEABILITY_TYPE = 'RATD' AND TEA.ACCOUNT_STATUS = 'LIAB' ORDER BY TEA.CREATED_TS DESC","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);
        
		test = report.createTest("BCL.421.001 - Verify CSR can add a collection hold on the account with reason for hold is Hearing Pending");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndsbb3","Brijen@1234567");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        
        commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		commonFuntions.ScrollMenu("Maintain Collection Hold");
		commonFuntions.clickMenu("Maintain Collection Hold");
		sleep();
		
		//---EM-011---
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "Maintain Collection Hold (EM-011)screen launched");
		commonFuntions.clickButtonContains("Continue ");
		sleep();
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "Message 'Required' on EM-011");
		commonFuntions.enterTextboxContains("Employer Registration Number", "1111111");
		commonFuntions.clickButton("Continue ");
		sleep();
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "Message 'The Employer Registration Number is not valid");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,"9000007");
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		
		//---COL-531---
		commonFuntions.screenShot("Remove Collection Hold", "Pass", "Remove Collection Hold (COL-531)screen launched");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Remove Collection Hold", "Pass", "Message 'Required' on COL-531");
		
	}
	
	
	
	
}
