//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_039_001 extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EL.039.001 - Verify CSR can view and create a non-liable employer UI account.", groups = {COMMON_CONSTANT.REGRESSION})
	public void Test_EL_039_001() throws Exception {
		
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
//		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
//		System.out.println("feinValue is"+feinValue);
		
		/*
		 * Map<String, String> databaseResults = PEOPage.
		 * database_SelectQuery("SELECT FEIN,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT NULL ORDER BY UPDATED_TS desc"
		 * ); String feinValue = databaseResults.get("Fein");
		 * System.out.println("feinValue is"+feinValue);
		 */
		
		test = report.createTest("EL.039.001 - Verify CSR can view and create a non-liable employer UI account.");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);
		
		commonFunctions.clickMenu("Menu");
		commonFunctions.clickMenu("Inquiry");
		commonFunctions.screenShot("Menu", "Pass", "Menu - Inquery - PEO Client Non-liable Employer information");
		commonFunctions.clickMenu("Inquiry PEO Client Non-liable Employer information");
		commonFunctions.screenShot("PeoNonLiableEmployerSearchPage", "Pass", "Search for PEO Client Non-Liable Employer");
		sleep();
		
		commonFunctions.enterTextboxContains("Legal Name", "Exempt");
		commonFunctions.screenShot("PeoNonLiableEmployerPage", "Pass", "Entered \"Exempt\" in Legal Name");
		commonFunctions.clickButtonContains(" Search ");
		commonFunctions.screenShot("PeoNonLiableEmployerPage", "Pass", "Searched for Exempted Legal Name");
		Thread.sleep(2000);
		commonFunctions.enterTextboxContains("Legal Name", "");
		
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		commonFunctions.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		commonFunctions.screenShot("PeoNonLiableEmployerPage", "Pass", "Click on Advanced Search option");
		Thread.sleep(2000);
		commonFunctions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "123456789");
		commonFunctions.screenShot("PeoNonLiableEmployerPage", "Pass", "Input FEIN to search");
		commonFunctions.clickButtonContains(" Search ");	
		
		commonFunctions.screenShot("FailurePage", "Fail", "Blocked at manual");
	}
}
