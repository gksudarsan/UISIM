package com.employerContibution.EE;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_13_004_CSR_Can_Submit_HouseHold extends TestBase {

	@Test()
	public void EE_13_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"EE.13.004 - Verify CSR can submit employer registration for employer type 'Household/Domestic'Short Form flow");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		
		/*--------------------SREG-001------------------*/
		
		commonFuntions.selectRadioQuestions("Short Form Registration", "Yes ");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		/*--------------------SREG-025------------------*/
		
		commonFuntions.selectDropdown("Employer Type", " Household/Domestic ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Sole Proprietorship (Individual) ");
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("FEIN : : "+feinValue);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Navigated to SREG-025 page and filling the form");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Source", " IA602 ");
		commonFuntions.selectDropdown("Source Type", " Fraud ");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*--------------------SREG-025------------------*/
		empPage.legalTextInput.sendKeys("d usygyufudsgf df");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "4536456787");
		
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_1.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_employed_4_value_2023.click();
		
		commonFuntions.enterTextboxContains("Total number of persons employed in your home? ", "4");
		
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		
		/*--------------------SREG-004------------------*/
		commonFuntions.screenShot("EmpRegister13", "Pass", "Navigated to SREG-004 page and filling the form");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23432");
		commonFuntions.selectDropdown("County", " Albany ");
		sleep();
		commonFuntions.screenShot("EmpRegister14", "Pass", "Form Filled");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		
		/*-------------------SREG-521-------------------*/
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-521");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		
		/*-------------------SREG-012-------------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-012");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-683-------------------*/
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-683");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-800-------------------*/
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-800");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-043-------------------*/
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-043 page click on submit");
		commonFuntions.clickButton("Submit ");
		sleep(30000);
		/*-------------------Home Page-------------------*/
		commonFuntions.screenShot("EmpRegister23", "Pass", "Navigated to Home Page page click on Inquiry Employer Account");
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.inquiry_dropDown_Menu);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.Contribution_dropDown_Menu);
		sleep();
		commonFuntions.screenShot("EmpRegister23", "Pass", "Clicking on Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		sleep(4000);
		/*-------------------SREG-050-------------------*/
		commonFuntions.screenShot("EmpRegister24", "Pass", "Navigated to SREG-050 page and validating the FEIN");
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		sleep();
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		String fein = empPage.FEIN_Value_Text_SREG_051.getText();
		System.out.println("FEIN from SREG-051 : : "+fein);
		Assert.assertEquals(feinValue, fein.replace("-", ""));
		test.log(Status.PASS,"FEIN used : : "+feinValue+" Fein found on SREG-051 : : "+fein);
		commonFuntions.screenShot("EmpRegister25", "Pass", "Click on previous button");
		commonFuntions.clickButton("Previous ");
		sleep();
		commonFuntions.screenShot("EmpRegister26", "Pass", "Click on Home button");
		commonFuntions.clickButton(" Home ");
	}
}
