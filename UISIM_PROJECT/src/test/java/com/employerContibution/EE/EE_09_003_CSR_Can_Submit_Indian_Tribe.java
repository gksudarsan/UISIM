package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_09_003_CSR_Can_Submit_Indian_Tribe extends TestBase{
	
	@Test
	public void EE_09_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		test = report
				.createTest("EE.09.002 Verify employer can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Business' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_MANJU.toUpperCase(), COMMON_CONSTANT.EMPLOYER_PASS_MANJU);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Willam");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Email Address", "test@Test.com");
		sleep();
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-025--------------*/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Indian Tribe ");
		commonFuntions.selectDropdown("Type of Legal Entity", " School ");
		/*---------------FEIN--------------*/
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		
		/*---------------FEIN--------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-003--------------*/
		
		
		
		/*---------------Legal Name--------------*/
		//SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT  IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
		String legalName="METROPOLITAN WINDOW WASHING LLC";
		/*---------------Legal Name--------------*/
		
		
		
		empPage.legalNameTextBox.clear();
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterPastDate("What is the date of the first payroll which", 365);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.are_You_Subsidiary_Yes);
//		commonFuntions.enterTextboxContains("Are you a subdivision, subsidiary or business", "Yes ");
		commonFuntions.enterTextboxContains("Enter the name of the federally recogni", "Test Tribe");
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals working", "20");
		commonFuntions.enterPastDate("Date covered employment began", 455);
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-004--------------*/
		sleep(4000);
		commonFuntions.enterTextboxContains(legalName, feinValue);
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.selectDropdown("County", " Albany ");
		driver.quit();
		commonFuntions.selectRadioQuestions("", legalName);
		
		
	}
}
