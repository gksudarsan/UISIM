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
public class EE_02_011_CSR_Registration extends TestBase{

	@Test
	public void EE_02_011() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.02.011:Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Sole Proprietorship (Individual)' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");sleep();
		commonFuntions.screenShot("EmployerRegistrationPage", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);

		/*-----'General Information (SREG-025)------*/
		commonFuntions.selectDropdown("Employer Type", "Agricultural");
		sleep(2000);
		//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd EXCEPT SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea", "FEIN");
		//		String feinValue = databaseResults.get("FEIN");
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information - Enter Details");
		String feinValue = prop.getProperty("FeinNotPresentInDolButInDtf");
		System.out.println("FEIN Value is: " + feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Sole Proprietorship (Individual)");
		commonFuntions.selectDropdown("Source", "NYS-100 (paper)");sleep();
		commonFuntions.selectDropdown("Source Type", "NYS-100AG");sleep();
		commonFuntions.screenShot("GeneralInformationPage1", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*------Employer Entity Information (SREG-003)------*/
		//		Map<String, String> databaseResults3 = commonFuntions.database_SelectQuerySingleColumn(
		//				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd WHERE FEIN NOT IN (SELECT ENTITY_NAME FROM T_EMPLOYER_ACCOUNT tea)ORDER BY UPDATED_TS DESC","LEGAL_NAME");
		//		String legalName = databaseResults3.get("LEGAL_NAME");
		commonFuntions.screenShot("EmployerEntityInformationPage", "Pass", "Employer Entity Information-Entering Details");
		String legalName = prop.getProperty("LegalFoundInDtfNotInDol");
		System.out.println("LegalName is: "+ legalName );
		AddPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Trade Name", "TestAutoCompany");
		commonFuntions.enterTextboxContains("Business Phone Number", Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No");
		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		commonFuntions.screenShot("EmployerEntityInformationPage1", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*-----Add Primary Business Physical Address (SREG-008)------*/
		commonFuntions.screenShot("BusinessPhysicalLocations", "Pass", "Business Physical Locations - Entering Details");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.selectDropdown("State", "New York");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		commonFuntions.selectDropdown("Principal Business Activity", "Other");sleep();
		commonFuntions.enterTextboxContains("If Other, provide details", "testing");
		commonFuntions.screenShot("BusinessPhysicalLocations", "Pass", "Add Primary Business Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.screenShot("VerifyAddressPopUp", "Pass", "Verify Address Pop Up displayed");
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*-----Business Physical Address Details------*/
		commonFuntions.screenShot("PhysicalBussinessAddressPageDetails", "Pass", "Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*-----Employer Contact Details------*/
		commonFuntions.screenShot("EmployerContactDetailsPage", "Pass", "Employer Contact Details-Entering Details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");sleep();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("John");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("Terry");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");sleep();
		commonFuntions.screenShot("EmployerContactDetailsPage1", "Pass", "Employer Contact Details:SREG-004");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
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

		/*----------Employer Verify Contact Details----------*/
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*----------Business Acquisition----------*/
		commonFuntions.screenShot("BussinessAquisition", "Pass", "Bussiness Aquisition:SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*-----Change in Legal Entity-----*/
		commonFuntions.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity(SREG-012");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*-------Add Sole Proprietorship Details------*/
		commonFuntions.screenShot("AddSoleProprietorshipDetails", "Pass", "Add Sole Proprietorship Details-Entering Details");
		commonFuntions.enterTextboxContains("SSN", "985768958");
		commonFuntions.enterTextboxContains("First Name", "TestAuto");
		commonFuntions.enterTextboxContains("Last Name", "Automation");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","23263");
		commonFuntions.enterTextboxContains("Residential Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddSoleProprietorshipDetails1", "Pass", "Add Sole Proprietorship Details:SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);
		commonFuntions.screenShot("SoleProprietorshipDetails", "Pass", "Sole Proprietorship Details:SREG-005");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*--------Upload Documents----*/
		AddPage.browserLink.click();
		sleep(3000);
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(3000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents(SREG-683)");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details(SREG-800)");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit");
		sleep(5000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to Review Employer Type WI.....
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
		}catch(Exception e) {}

		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");

		//Resolving Review Employer Type WI.....
		PEOPage.queue.click(); sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","Search with fein number");
		commonFuntions.clickButtonContains("Search");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewEmployerTask","Pass","Review Employer Task After Search");
		commonFuntions.clickOnLink("Review Employer Type");
		sleep(2000); 
		commonFuntions.screenShot("WorkItemDetails", "Pass", "Work Item Details");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewEmployerTypeTaskDetails","Pass","Review Employer Type Task Details");sleep();
		AddPage.commentField.sendKeys("review employer closing");
		commonFuntions.screenShot("ReviewEmployerTypeTaskDetails1","Pass","Review Employer Type Task Details1");
		commonFuntions.clickButtonContains("Submit"); sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("WorkItemCompleted","Pass","Workitem Completed");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI Determine Liability Task.................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		//Resolving WI Determine Liability Task.................
		PEOPage.queue.click();sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "liability");sleep();
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("DetermineLiabilityTask","Fail","Determine Liability Task");
		commonFuntions.clickOnLink("Unable to Determine Liability Task");
		sleep(2000);
		commonFuntions.screenShot("LiabilityTask","Pass","LiabilityTaskDetails"); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Review","Pass","Unable to Determine Liability Task");
		commonFuntions.selectDropdown("Account Status", "Liable");		
		commonFuntions.selectRadio("Contributory");
		commonFuntions.enterTextboxContains("Date covered employment began? ", "1212022");
		commonFuntions.populateListbox("Comment", "testing");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Home");


	}
}
