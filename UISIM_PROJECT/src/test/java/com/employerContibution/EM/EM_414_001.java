package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.ui.utilities.COMMON_CONSTANT;

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
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.ScrollMenu("Void Transfer");
		commonFuntions.clickMenu("Void Transfer");
		commonFuntions.screenShot("EmployerAccountMaintenance", "Pass", "Void Transfer");
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Void Transfer", "Pass", "Required Error - Void Transfer");
		commonFuntions.errorLabel(" Required");
		sleep(2000);
		
//		String ERN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
//		System.out.println("The ERN Value is:"+ ERN);
//		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", ERN);
		
//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC",
//				"EAN");
//		String ernNumber = databaseResults.get("EAN");
//		System.out.println("The selected EAN is:"+ ernNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number","2601754");
		commonFuntions.clickButtonContains(" Search ");
		sleep(5000);
		commonFuntions.ScrollMenu("Void Transfer");
		commonFuntions.screenShot("ValidERN", "Pass", "Valid ERN");
		commonFuntions.clickButtonContains("Void Transfer ");
		sleep(3000);
		commonFuntions.screenShot("SelectAtleatOneRecord", "Pass", "Select One Record: Void Transfer");
		commonFuntions.errorContent("Please select a record to proceed further.");
		sleep(2000);
		commonFuntions.enterTextboxContains("Employer Registration Number","2601754");
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.selectRadio("Select");
		commonFuntions.ScrollMenu("Void Transfer");
		
		

	}

}
