package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_011_CSR_Registration extends TestBase{

	@Test
	public void EE_01_011() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.01.011:Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Sole Proprietorship (Individual)' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		//commonFuntions.clickMenu("Menu");
		AddPage.menu.click();sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");sleep(2000);
		commonFuntions.screenShot("EmployerRegistrationPage", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Business");
		String feinValue = prop.getProperty("FeinPresentInDolNotInDtf");
		//Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSM' AND REGISTRATION_STATUS = 'C'", "FEIN");
		//String feinValue = databaseResults.get("FEIN");
		System.out.println("FEIN Value is: " + feinValue);
		test.log(Status.INFO, "ErnValue::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Sole Proprietorship (Individual)");sleep();
//		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSM' AND REGISTRATION_STATUS = 'C'", "EAN");
//		String ernValue = databaseResults1.get("EAN");
		String ernValue = prop.getProperty("ErnFoundInDolNotInDtf");
		System.out.println("ERN Value is: " + ernValue);
		test.log(Status.INFO, "ErnValue::" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Sole Proprietorship (Individual)");
		commonFuntions.selectDropdown("Source", "NYS-100 (paper)");sleep();
		commonFuntions.selectDropdown("Source Type", "NYS-100");sleep();
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
//		Map<String, String> databaseResults3 = commonFuntions.database_SelectQuerySingleColumn(
//				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSM' AND REGISTRATION_STATUS = 'C'","ENTITY_NAME");
//		String legalName = databaseResults3.get("ENTITY_NAME");
		String legalName = prop.getProperty("LegalNameFoundInDolNotInDtf");
		System.out.println("LegalName is: "+ legalName );
		AddPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Trade Name", "TESTTENDOR");
		commonFuntions.enterPastDate("Enter date of first operations in New York State", 365);
		commonFuntions.enterPastDate("What is the date of the first payroll", 335);
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		commonFuntions.selectDropdown("Quarter", "3");sleep();
		commonFuntions.selectDropdown("Year", "2023");sleep();
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "Yes");
		commonFuntions.enterTextboxContains("Explain services that are performed", "TESTAOTU");
		commonFuntions.selectRadioQuestions("Are you registering to remit withholding tax only?", "No");
		commonFuntions.screenShot("EmployerEntityInformationPage", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*------------------SREG-008 page primary business physical address -------*/
		try {
			commonFuntions.safeJavaScriptClick(AddPage.liability_error_Yes);
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.selectDropdown("Principal Business Activity", "Manufacturing");sleep();
		AddPage.productsName.sendKeys("AutomationTesting");
		commonFuntions.enterTextboxContains("Percent of Total Sales Value", "50");
		AddPage.rawMaterialName.sendKeys("SteelManufacturingTest");
		commonFuntions.screenShot("AddPrimaryBussinessPhysicalAddressPage", "Pass", "Add Primary Bussiness Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);	
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----------------------SREG-004 employer contact details --------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("John");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("Terry");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.screenShot("EmployerContactDetailsPage", "Pass", "Employer Contact Details:SREG-004");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton1);
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton2);
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton3);
			commonFuntions.screenShot("VerifyAddress", "Pass", "Verify Address Pop-Up");
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Employer Contact Details Addres Pop Up");
		}
		sleep(2000);

		/*-------------------SREG-521:Employer Verify Contact Details ---------*/
		commonFuntions.screenShot("EmployerVerificationDetails", "Pass", "Employer Verify Contact Details: SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*-------------------Business Acquisition (SREG-011)---------*/
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		sleep();
		//String FEIN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		//System.out.println(FEIN);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "002611287");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*--- Business Acquisition Details :SREG-12----*/
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details :SREG-012");
		sleep(2000);
		AddPage.clickOnLink("Add Another Acquisition");	
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		//String ERN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		//System.out.println(ERN);
		commonFuntions.enterTextboxContains("Employer Registration Number", "4508571");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.screenShot("BusinessAcquisitionPage2", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.screenShot("BusinessAcquisitionDetailsPage2", "Pass", "Business Acquisition Details :SREG-012");
		sleep(2000);
		AddPage.clickOnLink("Add Another Acquisition");
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		//String FEIN1 = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		//System.out.println(FEIN1);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "262569942");sleep();
		Map<String, String> databaseResultBAErn = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CAND'", "EAN");
		String EAN = databaseResultBAErn.get("EAN");
		System.out.println("EAN Value is: " + EAN);
		commonFuntions.enterTextboxContains("Employer Registration Number", EAN);sleep();
		AddPage.legalNameOfBussiness.sendKeys("TestAutomation");sleep();
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterPastDate("Acquisition Date", 90);
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.screenShot("BusinessAcquisitionPages3", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionDetailsPages3", "Pass", "Business Acquisition Details :SREG-012");
		sleep();
		AddPage.clickOnLink(" Add Another Acquisition");	
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		Map<String, String> databaseResults4 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC", "FEIN");
		String feinValue1 = databaseResults4.get("FEIN");
		System.out.println("FEIN Value is: " + feinValue1);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue1);
		sleep(2000);
		Map<String, String> databaseResults5 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC", "EAN");
		String ernValue1 = databaseResults5.get("EAN");
		System.out.println("ERN Value is: " + ernValue1);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue1);sleep();
		AddPage.legalNameOfBussiness.sendKeys("TestAutomation");sleep();
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.screenShot("BusinessAcquisitionPages4", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionDetailsPages4", "Pass", "Business Acquisition Details :SREG-012");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("ChangeInLegalEntity", "Pass", "Change in Legal Entity (SREG-012)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*---------------------SREG:006 -Add Sole Proprietorship Details*/
		commonFuntions.enterTextboxContains("SSN", "347739000");
		commonFuntions.enterTextboxContains("First Name", "TestAuto");
		commonFuntions.enterTextboxContains("Last Name", "Automation");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddSoleProprietorshipDetails", "Pass", "Add Sole Proprietorship Details:SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);
		commonFuntions.screenShot("SoleProprietorshipDetails", "Pass", "Sole Proprietorship Details:SREG-005");sleep();
		commonFuntions.clickButtonContains("Previous");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");sleep(2000);
		AddPage.clickOnLink("Add Sole Proprietorship Details");
		commonFuntions.enterTextboxContains("SSN", "006380002");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Mike");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","87588");
		commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddSoleProprietorshipDetails1", "Pass", "Add Sole Proprietorship Details:SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(4000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);
		AddPage.clickOnLink("Add Sole Proprietorship Details");
		commonFuntions.enterTextboxContains("SSN", "077501281");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Mike");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","84357");
		commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddSoleProprietorshipDetails2", "Pass", "Add Sole Proprietorship Details:SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(4000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		//Edit the existing address
		AddPage.editLink.click();
		sleep();
		commonFuntions.enterTextboxContains("SSN", "077501281");
		commonFuntions.enterTextboxContains("First Name", "John");
		commonFuntions.enterTextboxContains("Last Name", "Terry");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","65457");
		commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddSoleProprietorshipDetails3", "Pass", "Add Sole Proprietorship Details:SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(4000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);
		commonFuntions.screenShot("SoleProprietorshipDetailsFinal", "Pass", "Sole Proprietorship Details");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*-----Upload Documents -SREG-683------*/
		AddPage.browserLink.click();
		commonFuntions.uploadDoc("TESTINGEL.docx");
		sleep(4000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep(4000);
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		commonFuntions.clickButtonContains("Submit");
		sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation:SREG-013");
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to WI DOL-DTF Work item..............
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
		}catch(Exception e) {}

		//Assigning user to WI DOL-DTF Work item..............
		//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("Work Item Description Free Text","DOL DTF");
		commonFuntions.clickButtonContains("Search");
		sleep(3000);
		commonFuntions.screenShot("DOLDTFDiscrepancytasksearch","Pass","DOL-DTF Discrepancy task search");
		//commonFuntions.clickOnLink("DOL DTF Discrepancy");
		//cf.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytask","Pass","DOL-DTF Discrepancy task");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(3000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytaskPage","Pass","DOL/DTF Discrepancy task Page");sleep();
		commonFuntions.selectDropdown("Account Status", " Liable ");sleep();
		commonFuntions.selectRadioQuestions("Suppress Correspondence?", "No ");
		AddPage.comment.sendKeys("doldtf");
		commonFuntions.screenShot("DOL/DTFTask","Pass","DOL/DTF Task");sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep(3000);
		commonFuntions.screenShot("workitemCompletedDolDtf","Pass","DolDtf work item completed");
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to WI Resolve Incomplete Work item..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(2000);
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","FeinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(3000);
		commonFuntions.screenShot("IndividualWorkQueueIncompleteData","Pass","Individual Work Queue Incomplete Data");
		sleep(3000);
		commonFuntions.clickOnLinkAnchorTag("Resolve Incomplete Data");
		sleep(2000); 
		commonFuntions.screenShot("IncompleteDataWorkItemDetails","Pass","Incomplete Data Work Item Details");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("ResolveIncompleteDataTransfer Task","Pass","Resolve Incomplete Data Transfer Task");
		commonFuntions.enterTextboxContains("Employer Registration Number", "6435350");
		commonFuntions.enterTextboxContains("Legal Name Of Business", "ABCTEST");
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		AddPage.comment.sendKeys("incomplete closing");
		commonFuntions.clickButtonContains("Submit");
		sleep(3000);
		commonFuntions.screenShot("workitemCompletedIncompleteData","Pass","Incomplete Data work item completed");
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to WI Resolve Incomplete Work item..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(2000);
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","FeinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(3000);
		commonFuntions.screenShot("IndividualWorkQueuePredecessorData","Pass","Individual Work Queue Predecessor Data");
		sleep(3000);
		commonFuntions.clickOnLinkAnchorTag("Verify predecessor Data");
		sleep(2000); 
		commonFuntions.screenShot("PredecessorDataWorkItemDetails","Pass","Predecessor Data Work Item Details");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("PredecessorDataTransfer Task","Pass","Predecessor Data Transfer Task");
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		AddPage.comment.sendKeys("incomplete closing");
		commonFuntions.clickButtonContains("Submit");
		sleep(3000);
		commonFuntions.screenShot("workitemCompletedIncompleteData","Pass","Incomplete Data work item completed");
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

	}
}
