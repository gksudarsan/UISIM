package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_13_003_CSR_Can_Submit_Agriculture extends TestBase {

	@Test()
	public void EE_13_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"EE.08.001-Verify employer can submit employer registration for employer type 'Non-Profit' and legal entity type 'Limited Liability Company (All Types)' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.selectRadioQuestions("Short Form Registration", "Yes ");
		sleep();
		commonFuntions.screenShot("EmpRegister11", "Pass", "Entered the details and click on continue button");
		commonFuntions.clickButton("Continue ");

		/**************** SREG-025 ************************/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Liability Company (All Types) ");
		String feinValue =StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),5);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		commonFuntions.selectDropdown("Source", " IA602 ");
		commonFuntions.selectDropdown("Source Type", " Cash Wages ");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		
		/**************** SREG-003 ************************/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Required text on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("Random Test OP LLC");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Random Test OP LLC");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFuntions.enterTextboxContains("Total number of covered employees", "50");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		
		sleep();
		empPage.yearDropDown.click();
		empPage.yearValue_2022.click();
		
		sleep();
		
		/**************** SREG-004 ************************/
		
		sleep(3000);
		
		empPage.addressLine1_Form1_SREG_004.sendKeys("2434 WS Road");
		empPage.city_Form1.sendKeys("Albany");
		empPage.zipCode_Form1.sendKeys("43424");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form1);
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		
		
		
		
		
	}
}
