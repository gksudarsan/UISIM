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
public class EE_11_003_TPR_UserType extends TestBase{

	@Test
	public void EE_11_003() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.11.003 -Verify TPR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Unincorporated Association' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.enterRandomString("First Name");
		commonFuntions.enterRandomString("Last Name");
		commonFuntions.enterTextboxContains("Job Title", "TPRUSER");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistrationDetailsPage", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectDropdown("Employer Type", "Non-Profit");
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_BY DESC", 
				"FEIN");
		String feinValue = databaseResults.get("FEIN"); 
		System.out.println("The FEIN Value is:"+ feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Unincorporated Association");
		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_BY DESC", 
				"EAN");
		String eanValue = databaseResults1.get("EAN"); 
		System.out.println("The ERN Value is:"+ eanValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		/*------------------- SREG-003------------------------*/
		commonFuntions.enterRandomStringLegalName("Legal Name");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterPastDate("Enter date of first operations", 2190);
		commonFuntions.selectDropdown("Quarter", "2");
		commonFuntions.selectDropdown("Year", "2018");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No");
		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for nonprofit employment, do you wish to elect voluntary coverage?", "Yes");
		commonFuntions.selectRadioQuestions("Does this organization have, or have they applied for, a Nonprofit 501 (c)(3) exemption with the Internal Revenue Service?", "Yes");
		sleep(2000);
		commonFuntions.ScrollMenu("Employer Entity Information");
		sleep();
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*--------------SREG-008:Add Primary Business Physical Address ----*/
		
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Ave");
		commonFuntions.enterTextboxContains("City", "New York");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"12");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "75");
		commonFuntions.enterRandomString("Name of Government Agency");
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
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("Joe");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("Root");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_noticeOfPotentialCharges.sendKeys("Kane");
		AddPage.lastName_noticeOfPotentialCharges.sendKeys("Man");
		sleep(2000);
		commonFuntions.ScrollMenu("Employer Contact Details");
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
		
		/*--SREG-521:Employer Verify Contact Details---*/
		
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*----Business Acquisition:SREG-011-----*/
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		String FEIN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The FEIN Value is:"+ FEIN);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		
		Map<String, String> databaseResults2 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSC'", 
				"EAN");
		String ERN = databaseResults2.get("EAN"); 
		System.out.println("The FEIN Value is:"+ ERN);
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		commonFuntions.enterRandomStringLegalName("Legal Name of Business");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"01");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterPastDate("Acquisition Date", 365);
		commonFuntions.enterCurrentDate(AddPage.notificationDateTransfer);
		commonFuntions.ScrollMenu("Business Acquisition");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition:SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		/*--------Business Acquisition Details:SREG-012 --*/
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details:SREG-012");
		sleep();
		AddPage.clickOnLink(" Add Another Acquisition");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFuntions.enterTextboxContains("Employer Registration Number", "4562263");
		commonFuntions.enterRandomStringLegalName("Legal Name of Business");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"01");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterPastDate("Acquisition Date", 547);
		commonFuntions.enterCurrentDate(AddPage.notificationDateTransfer);
		commonFuntions.screenShot("BusinessAcquisition1", "Pass", "SecondAddress:Business Acquisition:SREG-011");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.screenShot("BusinessAcquisitionDetails1", "Pass", "AddSecondAddress:Business Acquisition Details:SREG-012");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*------Add Corporate Officer/Owner:SREG-006-----*/
		
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Business Entity");
		commonFuntions.enterRandomString("Entity Name");
		commonFuntions.enterTextboxContains("Federal Identification Number (FEIN)", FEIN);
		commonFuntions.selectDropdown("Title", "President");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Avenue");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"01");
		commonFuntions.enterTextboxContains("Contact Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.ScrollMenu("Add Corporate Officer/Owner");
		sleep(2000);
		commonFuntions.screenShot("AddCorporateOfficer", "Pass", "Add Corporate Officer/Owner: SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*------Add Corporate Officer/Owner Details:SREG-005-----*/
		
		commonFuntions.screenShot("AddCorporateOfficerDetails", "Pass", "Add Corporate Officer/Owner Details: SREG-005");
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
		sleep(25000);
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation:SREG-013");
		sleep(2000);
		commonFuntions.clickButtonContains("Exit");
		sleep(4000);
		
		/* ------- update CSR User ID in the database -----*/
		
		//Assigning user id to WI
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	    sleep(2000);
		commonFuntions.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		PEOPage.queue.click();
		sleep(15000);
		//Review Employer Type Task
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
	    commonFuntions.screenShot("ReviewEmployerTypeTaskCreatedSuccessfully", "Pass", "WorkItem Completed:SUC-002:Review Employer Type");
	    sleep(2000);
	    commonFuntions.clickButtonContains("Home");
	    sleep(2000);
	    
	    //Assigning CSR User ID to Other WI
	    commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	    sleep(2000);
	    PEOPage.queue.click();
		sleep(15000);

	}
}
