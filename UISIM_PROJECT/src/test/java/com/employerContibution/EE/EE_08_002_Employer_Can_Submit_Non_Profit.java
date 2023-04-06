package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_08_002_Employer_Can_Submit_Non_Profit extends TestBase {
	
	@Test
	public void EE_08_002() throws Exception {
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report
				.createTest("EE.08.002 Verify employer can submit employer registration for employer type 'Non-Profit' and legal entity type 'Corporation (All Types, includes Sub-Chapter S)' and work items will be created for CSR to review.");

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
		commonFuntions.screenShot("EmpRegister11", "Pass", "Entered the details and click on continue button");
		commonFuntions.clickButton("Continue ");
		
		/****************SREG-025************************/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Non-Profit ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		String feinValue = "401101502";
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		
		/*----------------SREG-003----------------*/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("-FUL STUDIO INCs");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFuntions.enterTextbox("Enter date of first operations in New York State", "02282021");
		
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value.click();
		sleep();
		commonFuntions.screenShot("EmpRegister5", "Pass", "Entering the details");
		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value.click();
		empPage.What_firstCalender_Year.click();
		empPage.firstCalender_Year_Value.click();
		
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_radio);
		commonFuntions.safeJavaScriptClick(empPage.If_Not_Lible_Yes_Radio);
		commonFuntions.safeJavaScriptClick(empPage.DOes_Org_Have_Yes_Radio);
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.Is_Reimbursable_Radio);
		sleep();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Entered he details and click continue");
		commonFuntions.clickButton("Continue ");
		sleep();
		
		
	}
}
