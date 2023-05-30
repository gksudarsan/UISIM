package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_004_CSR_Can_Register_Agriculture extends TestBase{
	
	@Test
	public void EE_02_004() throws Exception {
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
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButton("Continue ");
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to __ Page");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
//		sleep();
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		/*----------------Enter Query logic here----------------*/
		String ERN = "0449897";
		/*----------------Enter Query logic here----------------*/
		commonFuntions.screenShot("EmpRegister3", "Pass", "Filling the form");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Guardianship  Internal User Only ");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Form filled and click on continue");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-003----------------*/
		
		commonFuntions.screenShot("EmpRegister5", "Pass", "Landed on SREG-003 page");
		
		empPage.legalNameTextBox.sendKeys("-FUL STUDIO INC");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Business Email Address", "test@test.com");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "01122023");
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "04112023");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Filling the form");
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_radio);
		commonFuntions.enterTextboxContains("Total number of covered employees", "67");
		
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-008----------------*/

//		sleep(4000);
		commonFuntions.screenShot("EmpRegister7", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23432");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		empPage.individualPrinciple.click();
		empPage.individualPrinciple_value_other.click();
//		commonFuntions.selectDropdown("Indicate your principal activity or", " Other ");
		PEOPage.otherDetails_New1.sendKeys("other1");
		PEOPage.otherDetails1_New2.sendKeys("other2");
		
		
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-007----------------*/
		
		sleep(3000);
		commonFuntions.screenShot("EmpRegister8", "Pass", "Navigated to SREG-007 page");
		empPage.addAnotherBusinessLink.click();
		/*-----------------SREG-008----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister9", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.selectDropdown("County", " Allegany ");
		commonFuntions.safeJavaScriptClick(empPage.individualPrinciple);
		commonFuntions.safeJavaScriptClick(empPage.individualPrinciple_value_other);
		commonFuntions.enterTextboxContains("Number of employees at this location", "23");
		
//		commonFuntions.selectDropdown("Indicate your principal activity or", " Other ");
		PEOPage.otherDetails_New1.sendKeys("other3");
		PEOPage.otherDetails1_New2.sendKeys("other4");
		commonFuntions.clickButton("Continue ");
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-007----------------*/
		
		
//		sleep(4000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-007 page");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		empPage.addressLine1_Form1.sendKeys("20 cooper square");
		empPage.city_Form1.sendKeys("NY");
		empPage.zipCode_Form1.sendKeys("32424");
		empPage.countyDropDown_Form1_SREG_004.click();
		empPage.countyValue_Form1.click();
		
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		
//		empPage.addresLine1_Form2_SREG_004.sendKeys("20 cooper");
		
		
		commonFuntions.clickButton("Continue ");
		
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_Cooper);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-521----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-011----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-011 page");
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		/*-----------------Find Predecessor----------------*/
		
		/*-----------------Find Predecessor----------------*/
		String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		commonFuntions.enterTextboxContains("Employer Registration Number", "8606734");
		commonFuntions.enterTextboxContains("Legal Name of Business", "Other Enterprise");
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square new");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
//		commonFuntions.selectDropdown("County", " Allegancy ");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterTextboxContains("Acquisition Date", "04012023");
		commonFuntions.enterTextboxContains("Notification date of Transfer", "04022023");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-012----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-012----------------*/
		
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister15", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		commonFuntions.enterTextboxContains("Prior Federal Employer Identification Number (FEIN)", "");
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", "");
		commonFuntions.enterTextboxContains("Date of Legal Entity change", "04032023");
		commonFuntions.enterTextboxContains("Date of Notification", "04042023");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-006----------------*/
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		
		commonFuntions.enterTextboxContains("SSN", "453758964");
		commonFuntions.enterTextboxContains("First Name", "Abhi");
		commonFuntions.enterTextboxContains("Last Name", "Jan");
		commonFuntions.selectDropdown("Title", " Officer ");
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square new 2");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24944");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-005----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		commonFuntions.uploadDoc("Sample");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-800----------------*/
//		sleep(5000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-043----------------*/
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.clickButton("Submit ");
//		sleep(15000);
		/*-----------------SREG-013----------------*/
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-013 success page and click on exit");
		commonFuntions.clickButton("Exit ");
		
		/*-----------------SREG-013----------------*/
		sleep(7000);
		commonFuntions.screenShot("EmpRegister22", "Pass", "Navigated to Home Page");
		PEOPage.queue.click();
		
	}
}
