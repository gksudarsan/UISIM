package com.employerContibution.EE;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_09_002_Employer_Can_Submit_Indian_Tribe extends TestBase{
	
	@Test
	public void EE_09_002() throws Exception {
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
		commonFuntions.selectDropdown("Type of Legal Entity", " Business ");
		/*---------------FEIN--------------*/
		//"SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd EXCEPT SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea"
		String FEIN = "101308177";
		System.out.println(FEIN);
		/*---------------FEIN--------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-003--------------*/
		
		
		
		/*---------------Legal Name--------------*/
		//SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT  IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
		String legalName="GLOBAL CONCRETE CORP";
		/*---------------Legal Name--------------*/
		
		
		
		
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?", "08012023");
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly", "Yes ");
		sleep();
		commonFuntions.enterTextbox("Enter the name of the federally recognized Indian Tribe.", "Naruto Uzumaki");
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals", "25");
		commonFuntions.enterTextbox("Date covered employment began? ", "01012018");
		commonFuntions.clickButton("Continue ");
		
		
	}
}
