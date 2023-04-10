package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_010_CSR_Registration extends TestBase{

	@Test
	public void EE_02_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.01.009 - Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Estate' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Business");
		sleep();
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_DOL_DTF tedd ORDER BY UPDATED_TS DESC", "FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("FeinValue is: " + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "TRUST");
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		System.out.println(ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		sleep(2000);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFuntions.screenShot("General Information", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		Map<String, String> databaseResults2 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC","ENTITY_NAME");
		String legalName = databaseResults2.get("ENTITY_NAME");
		System.out.println("LegalName is: "+ legalName );
		AddPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Trade Name", "TestAutoCompany");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "5/1/2023");
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "5/1/2023");
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		commonFuntions.selectDropdown("Quarter", "2");
		sleep(2000);
		commonFuntions.selectDropdown("Year", "2023");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "Yes");
		commonFuntions.enterTextboxContains("Explain services that are performed", "OthersTest");
		commonFuntions.screenShot("Employer Entity Information", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		
		

	}
}
