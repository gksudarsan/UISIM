package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_11_002_TPR_Can_Register_Non_Profit_Coropration extends TestBase{

	@Test
	public void EE_11_002() throws Exception {

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);	
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		test = 
				report.createTest("EE.11.002:Verify TPR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Corporation (All Types, includes Sub-Chapter S)' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		commonFuntions.ScrollMenu("Employer Registration");sleep();
		commonFuntions.screenShot("Menu", "Pass", "Employer Registration");
		commonFuntions.clickMenu("Employer Registration");sleep();
		commonFuntions.screenShot("Menu", "Pass", "Employer Registration");
		commonFuntions.clickMenu("Register Employer"); sleep();
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Willam");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Email Address", "test@Test.com");
		commonFuntions.enterTextboxContains(" Contact Telephone Number ", "4534567645");sleep();
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButton("Continue ");
        sleep(3000);

		/*---------------SREG-025--------------*/
        commonFuntions.screenShot("TPRRegister", "Pass", "Navigated to SREG-025 page");
		test.log(Status.INFO, "Selecting drop down and filling the form");
		commonFuntions.selectDropdown("Employer Type", " Non-Profit ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		test.log(Status.INFO, "Fein::" +feinValue);
		Map<String, String> ernValue = 	commonFuntions.database_SelectQuerySingleColumn("SELECT EAN FROM T_EMPLOYER_ACCOUNT tea EXCEPT SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd", "EAN");
		String ERN = ernValue.get("EAN");
		System.out.println("ERN ::" +ERN);
		test.log(Status.INFO, "ERN :: "+ERN);	
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButton("Continue ");
		sleep(3000);

		/*---------------SREG-003--------------*/
		Map<String, String> legalNameValue = commonFuntions.database_SelectQuerySingleColumn("SELECT LEGAL_NAME, COUNT(LEGAL_NAME) FROM T_EMPLOYER_DOL_DTF tedd GROUP BY LEGAL_NAME HAVING COUNT(LEGAL_NAME) > 1", "LEGAL_NAME");	
		String legalName= legalNameValue.get("LEGAL_NAME");	
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterPastDate("Enter date of first", 630);sleep();
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
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Contri_Radio);
		commonFuntions.screenShot("'EmployerEntityInformation", "Pass", "'Employer Entity Information  (SREG-003)");
		commonFuntions.clickButton("Continue ");
		sleep(3000);


		/*---------------SREG-008--------------*/
		commonFuntions.screenShot("TPRRegister6", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+"FakeAddress");
		commonFuntions.enterTextboxContains("City", "New York");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"67");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "429");
		commonFuntions.enterTextboxContains("Name of Government Agency from which you receive funds", "test automation");
		commonFuntions.screenShot("AddPrimaryBusinessPhysicalAddress", "Pass", "Add Primary Business Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*---------------SREG-007--------------*/
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Bussiness Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*---------------SREG-004--------------*/
		commonFuntions.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		commonFuntions.enterTextboxContains("Address Line 1 ", "23 cooper square");
		commonFuntions.enterTextboxContains("City ", "New York");
		commonFuntions.enterTextboxContains("Zip Code", "34276");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form1);sleep();
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);sleep();
		commonFuntions.screenShot("TPRRegister8", "Pass", "Entered the address for Business Mailing Address");

		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		empPage.addressLine1_Form2.sendKeys("25 cooper square");
		empPage.city_Form2.sendKeys("New York");
		empPage.zipCode_Form2.sendKeys("34567");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form2);sleep();
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form2);sleep();
		commonFuntions.enterTextbox("First Name", "Abhi");
		commonFuntions.enterTextbox("Last Name", "Jan");sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Entered the address for Location of Books and Records");

		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		empPage.notice_potential_AddressLine_1.sendKeys("27 cooper square");
		empPage.notice_potential_City.sendKeys("New York");
		empPage.notice_potential_Zipcode.sendKeys("34784");
		empPage.notice_potential_firstName.sendKeys("Abhi1");
		empPage.notice_potential_LastName.sendKeys("JanNew");sleep();
		commonFuntions.screenShot("TPRRegister10", "Pass", "Entered the address for Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress1);
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress2);
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress3);sleep();
			commonFuntions.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Employer Contact Details Address pop up");
		}
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		/*---------------SREG-521--------------*/
		commonFuntions.screenShot("TPRRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		sleep(3000);

		/*---------------SREG-011--------------*/
		commonFuntions.screenShot("TPRRegister12", "Pass", "Navigated to SREG-011 page");sleep();
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");sleep();

		String feinValue1 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue1);
		test.log(Status.INFO, "Fein::" + feinValue1);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue1);


		/*---------------Suspended ERN--------------*/
		Map<String, String> susERNValue = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSM'", "EAN");
		String SusERN = susERNValue.get("EAN");
		System.out.println(SusERN);
		test.log(Status.INFO, "Suspended ERN::" + SusERN);
		commonFuntions.enterTextboxContains("Employer Registration Number", SusERN);

		commonFuntions.screenShot("TPRRegister13", "Pass", "Entering the details");
		empPage.legal_Name_Business_Input.sendKeys("abcautocompany");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.enterTextboxContains("Address Line 1 ", "30 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "34246");
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition");
		commonFuntions.clickButton("Continue ");
		sleep(3000);

		/*----------------SREG-012--------------------*/
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details");
		commonFuntions.clickOnLinkAnchorTag(" Add Another Acquisition");sleep(2000);


		/*---------------SREG-011--------------*/
		commonFuntions.screenShot("TPRRegister14", "Pass", "Navigated to SREG-011 page");sleep();
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");sleep();
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue1);

		/*---------------Suspended ERN--------------*/
		Map<String, String> futureERNValue = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='FUTR'", "EAN");
		String futureERN = futureERNValue.get("EAN");
		System.out.println(futureERN);
		test.log(Status.INFO, "Suspended ERN::" + futureERN);
		commonFuntions.enterTextboxContains("Employer Registration Number", futureERN);

		commonFuntions.screenShot("TPRRegister15", "Pass", "Entering the details");
		empPage.legal_Name_Business_Input.sendKeys("Neutron2");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterPastDate("Acquisition Date", 30);
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "43654");
		commonFuntions.screenShot("BusinessAcquisition1", "Pass", "Business Acquisition1");
		commonFuntions.clickButton("Continue ");
		sleep(3000);

		/*----------------SREG-012--------------------*/
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details");
		commonFuntions.clickButton("Continue ");
		sleep(3000);

		/*----------------SREG-006--------------------*/
		commonFuntions.screenShot("TPRRegister16", "Pass", "Entering the details");
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		String SSN = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(SSN);
		test.log(Status.INFO, "SSN Value::" + SSN);
		commonFuntions.enterTextboxContains("SSN", SSN);
		commonFuntions.enterTextboxContains("First Name", "john");
		commonFuntions.enterTextboxContains("Last Name", "test");
		commonFuntions.selectDropdown("Title", " President ");
		commonFuntions.enterTextboxContains("Address Line 1 ", "35 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10023");
		commonFuntions.enterTextboxContains(" Residential Telephone Number ", "(345)-436-5465");
		commonFuntions.screenShot("AddCorporateOfficer/Owner", "Pass", "Add Corporate Officer/Owner");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);sleep();
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*----------------SREG-005--------------------*/
		commonFuntions.screenShot("CorporateOfficer/OwnerDetails", "Pass", "Corporate Officer/Owner Details");
		commonFuntions.clickButton("Continue ");
		sleep(2000);

		/*----------------SREG-683--------------------*/
		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(2000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents(SREG-683)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details(SREG-800)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit");
		sleep(5000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to DOL_DTF WI..................
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
		}catch(Exception e) {}
		commonFuntions.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");

		//Resolving DOL_DTF WI................
		PEOPage.queue.click(); sleep();
		commonFuntions.waitForLoadingIconToDisappear();

	}
}
