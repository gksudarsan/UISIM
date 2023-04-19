package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_11_002_TPR_Can_Register_Non_Profit_Coropration extends TestBase{
	
	@Test
	public void EE_09_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		test = report
				.createTest("EE.11.002 -Verify TPR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Corporation (All Types, includes Sub-Chapter S)' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
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
		commonFuntions.enterTextboxContains(" Contact Telephone Number ", "4534567645");
		sleep();
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		/*---------------SREG-025--------------*/
		commonFuntions.screenShot("TPRRegister", "Pass", "Navigated to SREG-025 page");
		test.log(Status.INFO, "Selecting drop down and filling the form");
		commonFuntions.selectDropdown("Employer Type", " Non-Profit ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		/*---------------FEIN--------------*/
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		
		/*---------------FEIN--------------*/
		
		/*---------------ERN--------------*/
		Map<String, String> ernValue = 	commonFuntions.database_SelectQuerySingleColumn("SELECT EAN FROM T_EMPLOYER_ACCOUNT tea EXCEPT SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd", "EAN");
		//SELECT EAN FROM T_EMPLOYER_ACCOUNT tea EXCEPT SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd
		String ERN = ernValue.get("EAN");
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		
		/*---------------ERN--------------*/
		
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		
		/*---------------SREG-003--------------*/
		
		commonFuntions.screenShot("TPRRegister3", "Pass", "Navigated to SREG-003 page");
		test.log(Status.INFO, "Selecting drop down and filling the form");
		/*---------------Legal Name--------------*/
		Map<String, String> legalNameValue = commonFuntions.database_SelectQuerySingleColumn("SELECT LEGAL_NAME, COUNT(LEGAL_NAME) FROM T_EMPLOYER_DOL_DTF tedd GROUP BY LEGAL_NAME HAVING COUNT(LEGAL_NAME) > 1", "LEGAL_NAME");
//		
		String legalName= legalNameValue.get("LEGAL_NAME");
		/*---------------Legal Name--------------*/
		
		
		
		empPage.legalNameTextBox.clear();
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterPastDate("Enter date of first", 630);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_Value);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_Value_2022);
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_radio);
		commonFuntions.screenShot("TPRRegister5", "Pass", "Filling the form continue....");
		
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_employed_4);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_employed_4_value);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_employed_4);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_employed_4_value_2023);
		commonFuntions.safeJavaScriptClick(empPage.If_Not_Lible_Yes_Radio);
		commonFuntions.safeJavaScriptClick(empPage.DOes_Org_Have_Yes_Radio);
//		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Radio);
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Contri_Radio);
		
		
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-008--------------*/
		sleep(4000);
		commonFuntions.screenShot("TPRRegister6", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.enterTextboxContains("Number of employees at this location", "429");
		commonFuntions.enterTextboxContains("Name of Government Agency from which you receive funds", "Tester ACC");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		
		/*---------------SREG-007--------------*/
		commonFuntions.screenShot("Register111", "PASS", "Navigated to SREG-007 page after adding the address");
		commonFuntions.clickButton("Continue ");
		/*---------------SREG-004--------------*/
		
		sleep(3000);
		commonFuntions.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		commonFuntions.enterTextboxContains("Address Line 1 ", "New Address 1");
		commonFuntions.enterTextboxContains("City ", "New York");
		commonFuntions.enterTextboxContains("Zip Code", "34276");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form1);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		sleep();
		commonFuntions.screenShot("TPRRegister8", "Pass", "Entered the address for Business Mailing Address");
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		empPage.addressLine1_Form2.sendKeys("New Address 2");
		empPage.city_Form2.sendKeys("New York");
		empPage.zipCode_Form2.sendKeys("34567");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form2);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		commonFuntions.enterTextbox("First Name", "Abhi");
		commonFuntions.enterTextbox("Last Name", "Jan");
		sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Entered the address for Location of Books and Records");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		
		empPage.notice_potential_AddressLine_1.sendKeys("New Address 3");
		empPage.notice_potential_City.sendKeys("New York");
		empPage.notice_potential_Zipcode.sendKeys("34784");
		commonFuntions.safeJavaScriptClick(empPage.notice_potential_county);
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		
		empPage.notice_potential_firstName.sendKeys("Abhi1");
		empPage.notice_potential_LastName.sendKeys("JanNew");
		sleep();
		commonFuntions.screenShot("TPRRegister10", "Pass", "Entered the address for Notice of Potential Charges (LO400) Address");
//		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O", "Yes ");
//		sleep();
//		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O", "No ");
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-521--------------*/
		sleep();
		commonFuntions.screenShot("TPRRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		/*---------------SREG-011--------------*/
		commonFuntions.screenShot("TPRRegister12", "Pass", "Navigated to SREG-011 page");
		sleep();
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		sleep();
		String feinValue1 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue1);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue1);
		/*---------------Suspended ERN--------------*/
		Map<String, String> susERNValue = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='SUSD' AND EMPLOYER_REGISTRATION_NUMBER IS NOT NULL", "EMPLOYER_REGISTRATION_NUMBER");
		//
		String SusERN = susERNValue.get("EMPLOYER_REGISTRATION_NUMBER");
		/*---------------Suspended ERN--------------*/
		commonFuntions.screenShot("TPRRegister13", "Pass", "Entering the details");
		empPage.legal_Name_Business_Input.sendKeys("Naruto 11");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
//		commonFuntions.enterTextboxContains("Acquisition Date", "Create the method");
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "New Address 5");
		commonFuntions.enterTextboxContains("City ", "New York");
		commonFuntions.enterTextboxContains("Zip Code", "34246");
		
	}
}
