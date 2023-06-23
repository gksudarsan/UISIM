package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
public class EE_07_001_EmployerType extends TestBase{

	@Test
	public void EE_07_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.07.001- Verify employer can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_5.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_5_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("Employer Regitration", "Pass", "Employer Regitration - Error Message");
		commonFuntions.enterTextboxContains("First Name", "AutoTest");
		commonFuntions.enterTextboxContains("Last Name", "AutoSanjay");
		commonFuntions.enterTextboxContains("Job Title", "AutomationEngineer");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001_Error Message");
		sleep();
		commonFuntions.selectDropdown("Employer Type", "Governmental");
		sleep();
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "City");
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		System.out.println(ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("General Information", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("General Information", "Pass", "General Information:SREG-025_Error Message");
		sleep();
		AddPage.legalNameTextBox.sendKeys("TESTAUTOMATION TEST");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "1/1/1799");
		commonFuntions.enterTextboxContains("Estimated or approximate number", "360");
		commonFuntions.enterTextboxContains("Date covered employment began?", "1/1/1799");
		commonFuntions.selectRadioQuestions("Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?", "Yes");
		commonFuntions.clickButtonContains("Continue");
		AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("Employer Entity Information", "Pass", "Employer Entity Information:SREG-003_Error Message");
		sleep();
		commonFuntions.enterTextboxContains("If Yes, enter Legal Name", "TestAutomation");
		
//		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
//		commonFuntions.enterTextboxContains("City", "New York");
//		commonFuntions.selectDropdown("State", "New York");
		
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.ScrollMenu("Business Website (URL)");
		commonFuntions.screenShot("Invalid date Payroll", "Pass", "Date Payroll Withheld is invalid");
		commonFuntions.errorLabel(" Date Payroll Withheld is invalid");
		sleep();
		WebElement payrollDate = driver.findElement(By.xpath("//input[@id='firstPayrollDtId']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='';", payrollDate);
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "7/1/2022");
		sleep();
		WebElement empDate = driver.findElement(By.xpath("//input[@id='covEmploymentBeginDtId']"));
		js.executeScript("arguments[0].value='';", empDate);
		commonFuntions.enterTextboxContains("Date covered employment began?", "7/1/2022");
		commonFuntions.screenShot("No Error Message", "Pass", "Employer Entity Information  (SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.screenShot("Expected : SREG-008", "Pass", "System Failure");
		
		//test case blocked due to system failure error 
		
	}
}
