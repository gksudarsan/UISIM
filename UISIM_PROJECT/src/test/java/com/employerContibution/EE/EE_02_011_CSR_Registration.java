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
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_02_011_CSR_Registration extends TestBase{

	@Test
	public void EE_02_011() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.02.011 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Sole Proprietorship (Individual)' and work items will be created for CSR to review.");
		commonFuntions.login("ndsbb3","Brijen@1234567");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmployerRegistrationPage", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);
		commonFuntions.selectDropdown("Employer Type", "Agricultural");
		sleep(2000);
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd EXCEPT SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea", "FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("FEIN Value is: " + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Sole Proprietorship (Individual)");
		sleep(2000);
		commonFuntions.selectDropdown("Source", "NYS-100 (paper)");
		sleep();
		commonFuntions.selectDropdown("Source Type", "NYS-100AG");
		sleep();
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		//
		Map<String, String> databaseResults3 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd WHERE FEIN NOT IN (SELECT ENTITY_NAME FROM T_EMPLOYER_ACCOUNT tea)ORDER BY UPDATED_TS DESC","LEGAL_NAME");
		String legalName = databaseResults3.get("LEGAL_NAME");
		System.out.println("LegalName is: "+ legalName );
		AddPage.legalNameTextBox.sendKeys(legalName);
		sleep(2000);
		commonFuntions.enterTextboxContains("Trade Name", "TestAutoCompany");
		commonFuntions.enterTextboxContains("Business Phone Number", Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		//commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "4/1/2022");
		//commonFuntions.enterTextboxContains("What is the date of the first payroll", "5/1/2022");
		//commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		//commonFuntions.selectDropdown("Quarter", "2");
		//sleep(2000);
		//commonFuntions.selectDropdown("Year", "2023");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No");
		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		//commonFuntions.enterTextboxContains("Explain services that are performed", "OthersTest");
		//commonFuntions.selectRadioQuestions("Are you registering to remit withholding tax only?", "No");
		commonFuntions.screenShot("EmployerEntityInformationPage", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		/*------------------SREG-008 page primary business physical address -------*/
		try {
             commonFuntions.safeJavaScriptClick(AddPage.liability_error_Yes);
			} catch(Exception e) {
			System.out.println("Pop up not displayed");
			}
		//Additional Business Physical Locations(currently this page appears)
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.selectDropdown("State", "New York");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.selectDropdown("County", "Albany");
		//commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		//commonFuntions.selectDropdown("Principal Business Activity", "Other");
		//PEOPage.otherDetails.sendKeys("Atest");
		//PEOPage.otherDetails1.sendKeys("Btest");
		//sleep(2000);
		//AddPage.productsName.sendKeys("AutomationTesting");
		//commonFuntions.enterTextboxContains("Percent of Total Sales Value", "50");
		//AddPage.rawMaterialName.sendKeys("SteelManufacturingTest");
		commonFuntions.screenShot("AdditionalBusinessPhysicalLocations", "Pass", "Additional Business Physical Locations:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.screenShot("VerifyAddressPopUp", "Pass", "Verify Address Pop Up displayed");
		commonFuntions.safeJavaScriptClick(PEOPage.uspsAdd);
		sleep();
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep(2000);
		commonFuntions.screenShot("PhysicalBussinessAddressPageDetails", "Pass", "Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		//this test case is failing at step 13(defct_4916)
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
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton1);
			sleep();
		}catch(Exception e) {
			System.out.println("bmad address selected");
		}
		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton2);
			sleep();
		}catch(Exception e) {
			System.out.println("ibra address selected");
		}

		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton3);
			sleep();
		}catch(Exception e) {
			System.out.println("npca address selected");
		}
		commonFuntions.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		
		/*-------------------SREG-521:Employer Verify Contact Details ---------*/
		
		commonFuntions.screenShot("EmployerVerificationDetails", "Pass", "Employer Verify Contact Details: SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*-------------------Business Acquisition (SREG-011)---------*/
		
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		sleep();
		String FEIN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(FEIN);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFuntions.enterTextboxContains("Acquisition Date", "4/1/2023");
		commonFuntions.enterTextboxContains("Notification date of Transfer", "4/1/2023");
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		/*--- Business Acquisition Details :SREG-12----*/
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details :SREG-012");
		sleep(2000);
		AddPage.clickOnLink("Add Another Acquisition");	
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		String ERN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		System.out.println(ERN);
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		commonFuntions.enterTextboxContains("Notification date of Transfer", "4/1/2023");
		commonFuntions.screenShot("BusinessAcquisitionPage2", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.screenShot("BusinessAcquisitionDetailsPage2", "Pass", "Business Acquisition Details :SREG-012");
		sleep(2000);
		AddPage.clickOnLink("Add Another Acquisition");
		String FEIN1 = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(FEIN1);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN1);
		sleep(2000);
		Map<String, String> databaseResultBAErn = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CAND'", "EAN");
		String EAN = databaseResultBAErn.get("EAN");
		System.out.println("EAN Value is: " + EAN);
		commonFuntions.enterTextboxContains("Employer Registration Number", EAN);
		sleep(2000);
		AddPage.legalNameOfBussiness.sendKeys("TestAutomation");
		sleep();
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterTextboxContains("Acquisition Date", "1/1/2023");
		commonFuntions.enterTextboxContains("Notification date of Transfer", "4/1/2023");
		commonFuntions.screenShot("BusinessAcquisitionPages3", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionDetailsPages3", "Pass", "Business Acquisition Details :SREG-012");
		sleep();
		AddPage.clickOnLink(" Add Another Acquisition");	
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes");
		Map<String, String> databaseResults4 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "FEIN");
		String feinValue1 = databaseResults4.get("FEIN");
		System.out.println("FEIN Value is: " + feinValue1);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue1);
		sleep(2000);
		Map<String, String> databaseResults15 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "EAN");
		String ernValue1 = databaseResults15.get("EAN");
		System.out.println("ERN Value is: " + ernValue1);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue1);
		sleep(2000);
		AddPage.legalNameOfBussiness.sendKeys("TestAutomation");
		sleep();
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterTextboxContains("Acquisition Date", "4/1/2023");
		commonFuntions.enterTextboxContains("Notification date of Transfer", "4/1/2023");
		commonFuntions.screenShot("BusinessAcquisitionPages4", "Pass", "Business Acquisition Details :SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionDetailsPages4", "Pass", "Business Acquisition Details :SREG-012");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("ChangeInLegalEntity", "Pass", "Change in Legal Entity (SREG-012)");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		/*---------------------SREG:006 -Add Sole Proprietorship Details*/
		commonFuntions.enterTextboxContains("SSN", "985768958");
		commonFuntions.enterTextboxContains("First Name", "TestAuto");
		commonFuntions.enterTextboxContains("Last Name", "Automation");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddSoleProprietorshipDetails", "Pass", "Add Sole Proprietorship Details:SREG-006");
		commonFuntions.clickButtonContains("Continue");
		try {
		AddPage.uspsAddress.click();
		commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
		AddPage.continueButton_popUp.click();
		}catch(Exception e) {
			System.out.println("The popup is displyed");
		}
		sleep(2000);
		commonFuntions.screenShot("SoleProprietorshipDetails", "Pass", "Sole Proprietorship Details:SREG-005");
		commonFuntions.clickButtonContains("Previous");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		AddPage.clickOnLink("Add Sole Proprietorship Details");
		sleep(2000);
		Map<String, String> databaseResults16 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "EAN");
		String SSN = databaseResults15.get("SSN");
		System.out.println("SSN Value is: " + SSN);
		commonFuntions.enterTextboxContains("SSN", SSN);
		sleep(2000);
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Mike");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","87588");
		commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddSoleProprietorshipDetails1", "Pass", "Add Sole Proprietorship Details:SREG-006");
		commonFuntions.clickButtonContains("Continue");
		try {
			AddPage.uspsAddress.click();
			commonFuntions.screenShot("UspsAddressDisplayedAgain","Pass","UspsAddress1");
			AddPage.continueButton_popUp.click();
			}catch(Exception e) {
				System.out.println("The popup is displyed");
				
				//Observation 9: Not able to execute step 29 (error message related to this step) .
			}
			sleep(2000);
			AddPage.clickOnLink("Add Sole Proprietorship Details");
			commonFuntions.enterTextboxContains("SSN", "343453454");
			commonFuntions.enterTextboxContains("First Name", "Tom");
			commonFuntions.enterTextboxContains("Last Name", "Mike");
			commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
			commonFuntions.enterTextboxContains("City","NY");
			commonFuntions.enterTextboxContains("Zip Code","84357");
			commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
			commonFuntions.screenShot("AddSoleProprietorshipDetails2", "Pass", "Add Sole Proprietorship Details:SREG-006");
			commonFuntions.clickButtonContains("Continue");
			try {
			AddPage.uspsAddress.click();
			commonFuntions.screenShot("UspsAddress2","Pass","UspsAddress2");
			AddPage.continueButton_popUp.click();
			}catch(Exception e) {
				System.out.println("The popup is displyed");
			}
			sleep(2000);
			AddPage.editLink.click();
			sleep();
			commonFuntions.enterTextboxContains("SSN", "984375894");
			commonFuntions.enterTextboxContains("First Name", "John");
			commonFuntions.enterTextboxContains("Last Name", "Terry");
			commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
			commonFuntions.enterTextboxContains("City","NY");
			commonFuntions.enterTextboxContains("Zip Code","65457");
			commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
			commonFuntions.screenShot("AddSoleProprietorshipDetails3", "Pass", "Add Sole Proprietorship Details:SREG-006");
			commonFuntions.clickButtonContains("Continue");
			try {
			AddPage.uspsAddress.click();
			commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress3");
			AddPage.continueButton_popUp.click();
			}catch(Exception e) {
				System.out.println("The popup is displyed");
			}
			sleep(2000);
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			/*-----Upload Documents -SREG-683------*/
			PEOPage.browserLinkManagePEOPage.click();
			commonFuntions.uploadDoc("TESTINGEL.docx");
			Thread.sleep(4000);
			commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
			commonFuntions.clickButtonContains("Continue");
			sleep(10000);
			commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.selectCheckbox("I accept");
			commonFuntions.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
			commonFuntions.clickButtonContains("Continue");
			sleep(15000);
			commonFuntions.screenShot("StatementofAcknowledgement1", "Pass", "Statement of Acknowledgement:SREG-043");
		

	}
}
