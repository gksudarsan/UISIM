package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.createRandomString;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_12_002_TPR_UserType extends TestBase{

	@Test
	public void EE_12_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.12.002 - Verify TPR can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Business' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.enterTextboxContains("First Name", "TestAuto");
		commonFuntions.enterTextboxContains("Last Name", "AutoTest");
		commonFuntions.enterTextboxContains("Job Title", "TPRUsertest");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("EmployerRegistrationDetailsPage", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectDropdown("Employer Type", "Indian Tribe");
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The FEIN Value is:"+ feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Business");
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea join T_EMPLOYER_DOL_DTF ted ON tea.EAN = ted.ERN WHERE tea.ACCOUNT_STATUS = 'SUSD'", 
				"EAN");
		String ernValue = databaseResults.get("EAN");
		System.out.println("The EAN Value is:"+ ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		AddPage.legalNameTextBox.sendKeys("AUTOCOMPANY LLC");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterPastDate("What is the date of the first payroll", 365);
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly owned by a federally recognized Indian Tribe?", "Yes");
		sleep(2000);
		commonFuntions.enterTextboxContains("Enter the name of the federally recognized Indian Tribe.", "TPRUSER");
		commonFuntions.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Contributory");
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals", "653");
		commonFuntions.enterPastDate("Date covered employment began?", 275);
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*--------------SREG-008:Add Primary Business Physical Address ----*/
		
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+"Cooper Square");
		commonFuntions.enterTextboxContains("City", "New York");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"67");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.screenShot("AddPrimaryBusinessPhysicalAddress", "Pass", "Add Primary Business Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		try {
			//PEOPage.uspsAdd.click();
			commonFuntions.safeJavaScriptClick(PEOPage.uspsAdd);
			//AddPage.continueButton_popUp.click();
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("The popup is displyed");
		}
		sleep(2000);
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Bussiness Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		AddPage.addressLine1_Form1.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Ave");
		AddPage.city_Form1.sendKeys("NY");
		AddPage.zipCode_Form1.sendKeys("37634");
		AddPage.countyDropDown_Form1.click();
		AddPage.countyValue1.click();
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("john");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("Tery");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		AddPage.addressLine1_Form3.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Ave");
		AddPage.city_Form3.sendKeys("NY");
		AddPage.zipCode_Form3.sendKeys("76545");
		AddPage.countyDropDown_Form3.click();
		AddPage.countyValue3.click();
		sleep();
		AddPage.firstName_noticeOfPotentialCharges.sendKeys("Hens");
		AddPage.lastName_noticeOfPotentialCharges.sendKeys("Micheal");
		sleep(2000);
		commonFuntions.ScrollMenu("Business Mailing Address");
		sleep(2000);
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details:SREG-004");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		try {
			 AddPage.adderessRadioButton1.click();
			 AddPage.adderessRadioButton2.click();
			 AddPage.adderessRadioButton3.click();
	         commonFuntions.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
		     commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		     commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No");
		     commonFuntions.clickButtonContains("Continue");
		}catch(Exception e) {
			System.out.println("Th popup addres details not populated");
		}
		sleep(2000);
		
		/*--SREG-521---*/
		
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*--SREG-683 : Upload Documents -----*/
		
		AddPage.browserLink.click();
		sleep(3000);
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep(10000);
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementOfAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		commonFuntions.clickButtonContains("Submit");
		sleep(20000);
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation:SREG-013");
		sleep(2000);
		commonFuntions.clickButtonContains("Exit");
		sleep(4000);
		
		/* ------- update userid in the database -----*/
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	    sleep(2000);
		commonFuntions.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		PEOPage.queue.click();
		sleep(15000);
	    commonFuntions.enterTextboxContains("FEIN",feinValue);
	    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
	    commonFuntions.clickButtonContains("Search");
	    sleep(2000);
	    commonFuntions.screenShot("ReviewEmployerType","Pass","Review Employer Type");
	    commonFuntions.clickOnLink("Review Employer Type");
	    sleep(2000);
	    commonFuntions.clickButtonContains("Open Work Item");
	    sleep(2000);
	    commonFuntions.selectDropdown("Account Status", "Future");
	    AddPage.commentField.sendKeys("Test");
	    commonFuntions.clickButtonContains("Submit");
	    sleep(3000);
	    commonFuntions.screenShot("ReviewEmployerTypeTaskCreatedSuccessfully", "Pass", "WorkItem Completed:SUC-002");
	    sleep(2000);
	    commonFuntions.clickButtonContains("Home");
	    sleep(3000);

		
		
		
	}
}
