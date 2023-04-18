package com.employerContibution.EE;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_008_CSR_Can_Register_Agriculture extends TestBase{
	
	@Test
	public void EE_02_008() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);

		
		test = report
				.createTest("EE.02.004 -Verify CSR can submit employer registration for employer type 'Agricultural' and legal entity type 'Guardianship' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButton("Continue ");
		
	/*---------------SREG-025--------------*/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Liability Partnership ");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "325555555");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-003--------------*/
		
		empPage.legalNameTextBox.sendKeys("Test LLC");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Business Email Address", "test@test.com");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "03012021");
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "01012022");
		
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();
		
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_Yes_radio);
		commonFuntions.enterTextboxContains("Explain services that are performed by people you do not consider to be your employee(s)?", "Testing");
		commonFuntions.safeJavaScriptClick(empPage.If_Not_Lible_Radio);
		
		commonFuntions.clickButton("Continue ");
		
		try {
			commonFuntions.safeJavaScriptClick(empPage.liability_error_Yes);
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------DEFECT_4916------------------*/
		
		
	}
}
