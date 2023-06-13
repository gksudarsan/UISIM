package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_07_001_EmployerType extends TestBase{

	@Test
	public void EE_07_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = 
				report.createTest("EE.07.001:Verify employer can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep();
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep();
		commonFuntions.forceClearText(AddPage.lastName);
		sleep();
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.errorLabel(" Required ");
		commonFuntions.screenShot("EmployerRegitrationPageRequiredError", "Pass", "Employer Regitration:Required Error");
		commonFuntions.enterTextboxContains("First Name", "Jhon");
		commonFuntions.enterTextboxContains("Last Name", "Wick");
		commonFuntions.enterTextboxContains("Job Title", "AutomationEngineer");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.forceClearText(PEOPage.clearFeinFieldSection);
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.errorLabel(" Required ");
		commonFuntions.screenShot("GeneralInformationRequiredError", "Pass", "General Information Required Error");
		sleep();
		commonFuntions.selectDropdown("Employer Type", "Governmental");
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The fein value is::" + feinValue);
		test.log(Status.INFO, "FEIN IS::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "City");
		Map<String, String> databaseResults =
				commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea",
				"EAN");
		String ernValue = databaseResults.get("FEIN");
		test.log(Status.INFO, "EAN IS::" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.errorLabel(" Required");
		commonFuntions.screenShot("EmployerEntityInformationRequiredErorr", "Pass", "Employer Entity Information Required Erorr");
		sleep();
		AddPage.legalNameTextBox.sendKeys("TESTAUTOMATION");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "1/1/1799");
		commonFuntions.enterTextboxContains("Estimated or approximate number", "360");
		commonFuntions.enterTextboxContains("Date covered employment began?", "1/1/1799");
		commonFuntions.selectRadioQuestions("Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?", "Yes");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.errorLabel(" Date Payroll Withheld is invalid");
		sleep();
		commonFuntions.errorLabel(" Date Covered Employment Began is invalid");
		sleep();
		commonFuntions.errorLabel(" Required");
		commonFuntions.screenShot("EmployerEntityInformationEnterValidDate", "Pass", "Employer Entity Information:SREG-003_Error Message");
		sleep();
		commonFuntions.enterPastDate("What is the date of the first payroll", 180);
		commonFuntions.enterPastDate("Date covered employment began?", 180);
		commonFuntions.enterTextboxContains("If Yes, enter Legal Name", "TestAutomation");
		commonFuntions.screenShot("EmployerEntityInformationEnter", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.screenShot("AddPrimaryBusinessPhysicalAddressRequiredError", "Pass", "Add Primary Business Physical Address Required Error");
		
		/*--------Add Primary Business Physical Address------*/
		commonFuntions.enterTextboxContains("Address Line 1",  commonFuntions.createRandomInteger(10,99 )+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		commonFuntions.screenShot("AddPrimaryBussineesdDetails", "Pass", "Add Primary Business Physical Address");
		commonFuntions.clickButtonContains(" Finish Later ");
		sleep();
		commonFuntions.clickButtonContains(" Yes ");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep();
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Incomplete Registration");
		commonFuntions.clickMenu("Incomplete Registration");
		sleep();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		sleep();
		commonFuntions.clickButtonContains("Search");
		
	}
}
