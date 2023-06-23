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
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		test = report
				.createTest("EE.11.002 -Verify TPR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Corporation (All Types, includes Sub-Chapter S)' and work items will be created for CSR to review.");

		cf.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		sleep();
		cf.waitForLoadingIconToDisappear();
//		test.log(Status.FAIL, "Exception");
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
//		cf.clickMenu("Menu");
		cf.safeJavaScriptClick(empPage.menuButtonHomepage);
		sleep(2000);
		cf.clickMenu("Employer Registration");
		sleep();
		cf.clickMenu("Register Employer");
		sleep(3000);
		cf.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		cf.enterTextboxContains("First Name", "Tom");
		cf.enterTextboxContains("Last Name", "Willam");
		cf.enterTextboxContains("Job Title", "Tester");
		cf.enterTextboxContains("Email Address", "test@Test.com");
		cf.enterTextboxContains(" Contact Telephone Number ", "4534567645");
		sleep();
		cf.clickButton("Continue ");
		sleep(3000);
		/*---------------SREG-025--------------*/
		cf.screenShot("TPRRegister", "Pass", "Navigated to SREG-025 page");
		test.log(Status.INFO, "Selecting drop down and filling the form");
		cf.selectDropdown("Employer Type", " Non-Profit ");
		cf.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		/*---------------FEIN--------------*/
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		
		/*---------------FEIN--------------*/
		
		/*---------------ERN--------------*/
		Map<String, String> ernValue = 	cf.database_SelectQuerySingleColumn("SELECT EAN FROM T_EMPLOYER_ACCOUNT tea EXCEPT SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd", "EAN");
		//SELECT EAN FROM T_EMPLOYER_ACCOUNT tea EXCEPT SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd
		String ERN = ernValue.get("EAN");
		System.out.println("ERN : : " +ERN);
		test.log(Status.INFO, "ERN : : "+ERN);
		
		cf.enterTextboxContains("Employer Registration Number", ERN);
		
		/*---------------ERN--------------*/
		
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		cf.clickButton("Continue ");
		sleep(4000);
		
		/*---------------SREG-003--------------*/
		
		cf.screenShot("TPRRegister3", "Pass", "Navigated to SREG-003 page");
		test.log(Status.INFO, "Selecting drop down and filling the form");
		/*---------------Legal Name--------------*/
//		Map<String, String> legalNameValue = cf.database_SelectQuerySingleColumn("SELECT LEGAL_NAME, COUNT(LEGAL_NAME) FROM T_EMPLOYER_DOL_DTF tedd GROUP BY LEGAL_NAME HAVING COUNT(LEGAL_NAME) > 1", "LEGAL_NAME");
//		
//		String legalName= legalNameValue.get("LEGAL_NAME");
		String legalName= "SPILLANE ELECTRIC INC";
		/*---------------Legal Name--------------*/
		
		
		
		cf.forceClearText(empPage.legalNameTextBox);
		empPage.legalNameTextBox.sendKeys(legalName);
		cf.enterTextboxContains(" Business Phone Number  ", "7687765665");
		cf.enterPastDate("Enter date of first", 630);
		sleep();
		cf.safeJavaScriptClick(empPage.firstCalender_Quater);
		cf.safeJavaScriptClick(empPage.firstCalender_Quater_Value);
		cf.safeJavaScriptClick(empPage.firstCalender_Year);
		cf.safeJavaScriptClick(empPage.firstCalender_Year_Value_2022);
		cf.safeJavaScriptClick(empPage.DO_Person_Work_radio);
		cf.screenShot("TPRRegister5", "Pass", "Filling the form continue....");
		
		cf.safeJavaScriptClick(empPage.firstCalender_Quater_employed_4);
		cf.safeJavaScriptClick(empPage.firstCalender_Quater_employed_4_value);
		cf.safeJavaScriptClick(empPage.firstCalender_Year_employed_4);
		cf.safeJavaScriptClick(empPage.firstCalender_Year_employed_4_value_2023);
		cf.safeJavaScriptClick(empPage.If_Not_Lible_Yes_Radio);
		cf.safeJavaScriptClick(empPage.DOes_Org_Have_Yes_Radio);
//		cf.safeJavaScriptClick(empPage.Choose_Option_Radio);
		cf.safeJavaScriptClick(empPage.Choose_Option_Contri_Radio);
		
		
		cf.clickButton("Continue ");
		
		/*---------------SREG-008--------------*/
		sleep(4000);
		cf.screenShot("TPRRegister6", "Pass", "Navigated to SREG-008 page and entering the details");
		cf.enterTextboxContains("Address Line 1 ", "Fake Address");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", "10002");
		cf.selectDropdown("County", " Albany ");
		cf.enterTextboxContains("Number of employees at this location", "429");
		cf.enterTextboxContains("Name of Government Agency from which you receive funds", "Tester new ACC");
		cf.clickButton("Continue ");
		sleep(4000);
		cf.waitForLoadingIconToDisappear();
		
		/*---------------SREG-007--------------*/
		cf.screenShot("Register111", "PASS", "Navigated to SREG-007 page after adding the address");
		cf.clickButton("Continue ");
		/*---------------SREG-004--------------*/
		
		sleep(3000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		cf.enterTextboxContains("Address Line 1 ","10 Maiden Street");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34276");
		cf.safeJavaScriptClick(empPage.countyDropDown_Form1);
		sleep();
		cf.safeJavaScriptClick(empPage.countyValue_Form1);
		sleep();
		cf.screenShot("TPRRegister8", "Pass", "Entered the address for Business Mailing Address");
		cf.selectRadioQuestions("Location of Books and Records", "Other");
		empPage.addressLine1_Form2.sendKeys("20 Maiden Street");
		empPage.city_Form2.sendKeys("Albany");
		empPage.zipCode_Form2.sendKeys("34567");
		cf.safeJavaScriptClick(empPage.countyDropDown_Form2);
		sleep();
		cf.safeJavaScriptClick(empPage.countyValue_Form1);
		cf.enterTextbox("First Name", "Abhi");
		cf.enterTextbox("Last Name", "Jan");
		sleep();
		cf.screenShot("TPRRegister9", "Pass", "Entered the address for Location of Books and Records");
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		
		empPage.notice_potential_AddressLine_1.sendKeys("30 Maiden Street");
		empPage.notice_potential_City.sendKeys("Albany");
		empPage.notice_potential_Zipcode.sendKeys("34784");
		cf.safeJavaScriptClick(empPage.notice_potential_county);
		cf.safeJavaScriptClick(empPage.countyValue_Form1);
		
		empPage.notice_potential_firstName.sendKeys("Abhi1");
		empPage.notice_potential_LastName.sendKeys("JanNew");
		sleep();
		cf.screenShot("TPRRegister10", "Pass", "Entered the address for Notice of Potential Charges (LO400) Address");
//		cf.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O", "Yes ");
//		sleep();
//		cf.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O", "No ");
		cf.clickButton("Continue ");
		
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.uspsCommonButton2);
			sleep();
			cf.safeJavaScriptClick(empPage.uspsCommonButton3);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("pop up not displayed");
		}
		sleep(4000);
		cf.waitForLoadingIconToDisappear();
		try {
			cf.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			sleep();
			cf.clickButton("Continue ");
			sleep(2000);
			cf.waitForLoadingIconToDisappear();
		}catch(Exception e) {
			System.out.println("No error in on page");
		}
		
		/*---------------SREG-521--------------*/
		
		cf.screenShot("TPRRegister11", "Pass", "Navigated to SREG-521 page");
		cf.clickButton("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		/*---------------SREG-011--------------*/
		cf.screenShot("TPRRegister12", "Pass", "Navigated to SREG-011 page");
		sleep();
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		String feinValue1 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue1);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue1);
		/*---------------Suspended ERN--------------*/
//		Map<String, String> susERNValue = cf.database_SelectQuerySingleColumn("SELECT ACCOUNT_STATUS,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'FUTR'", "EAN");
		//
//		String SusERN = susERNValue.get("EAN");
		String SusERN = "9876303";
		/*---------------Suspended ERN--------------*/
		cf.screenShot("TPRRegister13", "Pass", "Entering the details");
		cf.enterTextboxContains("Employer Registration Number", SusERN);
		cf.enterTextboxContains("Address Line 1 ", "221 Madison AVE");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34246");
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		cf.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		cf.enterDateOfCurrentQuaterFirstMonth("Notification date of Transfer");
		cf.clickButton("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}
		catch(Exception e) {
			System.out.println("No Address Pop up");
		}
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*---------------SREG-012--------------*/
		cf.screenShot("TPRRegister14", "Pass", "Navigated to SREG-012 and click on Add Another Aquisition");
		sleep();
		cf.clickOnLinkAnchorTag(" Add Another Acquisition");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*---------------SREG-011------------------*/
		cf.screenShot("TPRRegister15", "Pass", "Navigated to SREG-011 page");
		sleep();
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue1);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		sleep();
		empPage.legal_Name_Business_Input.sendKeys("a haadjh ada gd");
		/*---------------Suspended ERN--------------*/
//		Map<String, String> susERNValue = cf.database_SelectQuerySingleColumn("SELECT ACCOUNT_STATUS,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'FUTR'", "EAN");
		//
//		String SusERN = susERNValue.get("EAN");
		String SusERN2 = "0000652";
		/*---------------Suspended ERN--------------*/
		cf.screenShot("TPRRegister13", "Pass", "Entering the details");
		cf.enterTextboxContains("Employer Registration Number", SusERN2);
		cf.enterTextboxContains("Address Line 1 ", "222 Madison AVE");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34226");
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		cf.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		cf.enterDateOfCurrentQuaterFirstMonth("Notification date of Transfer");
		cf.clickButton("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}
		catch(Exception e) {
			System.out.println("No Address Pop up");
		}
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		
		/*---------------SREG-012------------------*/
		cf.screenShot("TPRRegister15", "Pass", "Navigated to SREG-012 page");
		cf.validateNextPageNumber("SREG-012");
		cf.clickButton("Continue ");
		
		/*---------------SREG-012------------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("TPRRegister19", "Pass", "Navigated to SREG-006 page");
		cf.validateNextPageNumber("SREG-006");
		cf.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("SSN", "453647568");
		cf.enterTextboxContains("First Name", "dsbjh");
		cf.enterTextboxContains("Last Name", "dfhsd");
		cf.selectDropdown("Title", " Board Member ");
		
		cf.enterTextboxContains("Address Line 1 ", "222 Madison AVE");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34226");
		sleep();
		cf.enterTextboxContains(" Residential Telephone Number ", "4563746576");
		cf.clickButton("Continue ");
		sleep(4000);
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*---------------SREG-005------------------*/
		
		cf.screenShot("TPRRegister21", "Pass", "Navigated to SREG-005 page");
		cf.validateNextPageNumber("SREG-005");
		cf.clickButton("Continue ");
		
		
		/*---------------SREG-683------------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("TPRRegister22", "Pass", "Navigated to SREG-683 page");
		cf.validateNextPageNumber("SREG-683");
		cf.clickButton("Continue ");
		/*---------------SREG-800------------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("TPRRegister22", "Pass", "Navigated to SREG-800 page");
		cf.validateNextPageNumber("SREG-800");
		cf.clickButton("Continue ");
		/*---------------SREG-043------------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("TPRRegister23", "Pass", "Navigated to SREG-043 page");
		cf.validateNextPageNumber(" SREG-043");
		cf.selectCheckbox("I accept");
		cf.clickButton("Submit ");
		/*---------------SREG-043------------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("TPRRegister24", "Pass", "Navigated to SREG-013 page");
		cf.validateNextPageNumber(" SREG-013");
		cf.clickButton("Home ");
		
		cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		/*-----------------Home Page----------------*/
		cf.waitForLoadingIconToDisappear();
		sleep(20000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		cf.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextbox("FEIN", feinValue);
		cf.clickButton(" Search ");
		cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		empPage.review_employer_My_Q.click();
		sleep(3000);
		
		/*-----------------WF-091----------------*/
		cf.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		cf.clickButton("Open Work Item ");
		sleep(3000);
		cf.waitForLoadingIconToDisappear();
		/*-----------------EEWl-002----------------*/
		sleep();
		cf.safeJavaScriptClick(empPage.firstCalender_Quater);
		cf.safeJavaScriptClick(empPage.firstCalender_Quater_employed_4_value);
		cf.safeJavaScriptClick(empPage.firstCalender_Year);
		cf.safeJavaScriptClick(empPage.firstCalender_Year_Value_2022);
		cf.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		empPage.commentBox_MyQ.sendKeys("Test");
		cf.clickButton("Submit ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		/*-----------------SUC-002----------------*/
	
		cf.validateNextPageNumber("SUC-002");
		cf.screenShot("EmpRegister19", "Pass", "Navigated to Success page");
		cf.clickButtonContains("Home ");
		
		/*-----------------Searching DOL/DTF task----------------*/
		
		cf.clickOnLinkAnchorTag("DOL DTF Discrepancy");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister30", "Pass", "Navigated WF-091 page");
		cf.clickButton("Open Work Item ");
		cf.validateNextPageNumber("EEWI-005");
		empPage.commentBox_MyQ.sendKeys("sdfsdjhf");
		cf.clickButton("Submit ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		/*-----------------SUC-002----------------*/
	
		cf.validateNextPageNumber("SUC-002");
		cf.screenShot("EmpRegister19", "Pass", "Navigated to Success page");
		cf.clickButtonContains("Home ");
		
		
		
		
	}
}
