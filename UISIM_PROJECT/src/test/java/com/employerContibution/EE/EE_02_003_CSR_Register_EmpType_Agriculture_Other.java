package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_003_CSR_Register_EmpType_Agriculture_Other extends TestBase{

	@Test
	public void EE_02_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report
				.createTest("EE.02.003 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Other' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		/*-----------------SREG-001------------------*/
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		/*-----------------SREG-025------------------*/
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 Page");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
		sleep();
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
		commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "Others");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		sleep(3000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		/*-----------------SREG-003------------------*/
		empPage.legalTextInput.sendKeys("-FUL STUDIO INC");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "01012020");
		commonFuntions.enterTextboxContains("What is the date of the first payroll which you", "01082020");
//		commonFuntions.enterTextboxContains("Quarter ", " 1 ");
		empPage.quaterDropDown.click();
		sleep();
		empPage.quaterValue.click();
		sleep();
		empPage.yearDropDown.click();
		sleep();
		empPage.yearValue.click();
		
//		commonFuntions.enterTextboxContains("Year ", " 2023 ");
		commonFuntions.enterTextboxContains("Total number of covered employees", "50");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-008------------------*/
		commonFuntions.enterTextboxContains("Address Line 1 ", "123 State");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "44673");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		commonFuntions.safeJavaScriptClick(empPage.uspsAddress);
		commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		/*-----------------SREG-007------------------*/
		sleep(3000);
		
		empPage.addAnotherBusinessLink.click();
		sleep(3000);
		
//		commonFuntions.enterTextboxContains("Address Line 1 ", "60 Ave");
//		empPage.addressLine1SREG_008.click();
//		empPage.addressLine1SREG_008.sendKeys("60 Ave");
//		commonFuntions.jsSendKeys(empPage.addressLine1SREG_008, "60 Ave");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "44673");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-004------------------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		empPage.addressLine1_Form1.sendKeys("50 Ave");
		empPage.city_Form1.sendKeys("Albany");
		empPage.zipCode_Form1.sendKeys("74747");
		empPage.countyDropDown_Form1.click();
		empPage.countyValue_Form1.click();
		sleep();
		
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		empPage.addressLine1_Form2.sendKeys("40 Park View");
		sleep();
		empPage.city_Form2.sendKeys("Albany");
		sleep();
		empPage.zipCode_Form2.sendKeys("28287");
		sleep();
		empPage.countyDropDown_Form2.click();
		empPage.countyValue_Form2.click();
		
		commonFuntions.enterTextboxContains("First Name", "aaa");
		commonFuntions.enterTextboxContains("Last Name", "bbb");
		commonFuntions.enterTextboxContains(" Telephone Number ", "3478365384");
		
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFuntions.clickButton("Continue ");
		
		
		
		
}
}
