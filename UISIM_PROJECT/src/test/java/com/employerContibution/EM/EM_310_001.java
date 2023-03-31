package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_503;
import com.ui.pages.SRGE_543;
import com.ui.pages.SRGE_544;
import com.ui.pages.SUC_002;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_310_001 extends TestBase {

	@Test(priority=1, description = "EM.310.001 - Verify CSR is able to process sale of business and indicate transfer 'Total' ",groups = {"Regression"})
	public void EM_310_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EM.310.001 - Verify CSR is able to process sale of business and indicate transfer 'Total' ");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.screenShot("Account Maintenance", "Pass", "Sale of Bussiness");
		commonFuntions.clickMenu("Sale of Business");
		Thread.sleep(2000);
		commonFuntions.screenShot("SOB", "Pass", "Sale of Bussiness - Enter REN");
		commonFuntions.clickButtonContains("Continue ");sleep();
		commonFuntions.screenShot("Sale of Bussiness", "Pass", "Required_Sale of Bussiness_Enter ERN");
		commonFuntions.errorLabel("Required");
		Thread.sleep(2000);
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC","EAN");
		Thread.sleep(10000);
		String ernNumber=databaseResults.get("EAN");
		System.out.println("The selected ERN is: "+ernNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number",ernNumber);
		commonFuntions.screenShot("Registered ERN", "Pass", "Entered ERN");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.selectRadioQuestions("Have you sold your business?", "Yes");
		commonFuntions.enterTextboxContains("Successor", "0087");
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Sale of Bussiness", "Pass", "Sale of Bussiness");
		commonFuntions.errorLabel("Length of this response must be at least 7 characters.");
		commonFuntions.enterTextboxContains("Successor", ernNumber);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Required", "Pass", "Required Message");
		
		//Blocked at step-9
		//FEIN and ERN combination are not valid.
		
	}
}
