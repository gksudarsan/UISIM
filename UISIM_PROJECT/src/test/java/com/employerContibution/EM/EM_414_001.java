package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_434;
import com.ui.pages.SREG_435;
import com.ui.pages.SREG_541;
import com.ui.pages.SUC_002;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_414_001 extends TestBase {


	@Test(priority=1, description = "EM.414.001. - Verify CSR is able to process void transfer and reason of voiding transfer \"No Transfer Occurred\".",groups = {"Regression"})
	public void EM_414_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EM.414.001. - Verify CSR is able to process void transfer and reason of voiding transfer \"No Transfer Occurred\".");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.ScrollMenu("Void Transfer");
		commonFuntions.clickMenu("Void Transfer");
		commonFuntions.screenShot("Employer Account Maintenance", "Pass", "Void Transfer");
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Void Transfer", "Pass", "Required Error - Void Transfer");
		commonFuntions.errorLabel(" Required");
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC","EAN");
		Thread.sleep(10000);
		String ernNumber=databaseResults.get("EAN");
		System.out.println("The selected ERN is: "+ernNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number",ernNumber);
		commonFuntions.screenShot("Registered ERN", "Pass", "Entered ERN");
		
		// not able find the record with ern number
		

	}

}
