package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_010_Csr_Agriculture_Trust extends TestBase{

	@Test()
	public void EE_02_010() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();	
		employerManagement em =  new employerManagement();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */	
		test = 
				report.createTest("EE_02_010:Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Guardianship' and work items will be created for CSR to review.");
		cf.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");		
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); sleep(2000);
		cf.screenShot("EmployerRegistration", "Pass", "EmployerGeneralInfo");
		cf.clickButtonContains("Continue"); sleep(3000);
		
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC", "FEIN"); 
		String FEIN = databaseResults.get("FEIN");
		System.out.println("Fein Value::" +FEIN);
		test.log(Status.INFO, "Fein::" +FEIN);

		Map<String, String> databaseResults_Ern = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSB'", "EAN"); 
		String ERN = databaseResults_Ern.get("EAN");
		System.out.println("Ern Value:: =" +ERN);
		test.log(Status.INFO, "Ern::" +ERN);

		cf.screenShot("GenInfo", "Pass", "GenInfo-Entering Details");
		cf.selectDropdown("Employer Type", " Agricultural ");
		cf.enterTextboxContains("(FEIN)", FEIN); 
		cf.screenShot("filingInfo","Pass", "Searching with FEIN "); 
		cf.selectDropdown("Type of Legal Entity"," Trust "); 
		cf.enterTextboxContains("Employer Registration Number", ERN);
		cf.selectDropdown("Source", " NYS-100 (paper) ");sleep();
		cf.selectDropdown("Source Type", " NYS-100AG ");sleep();
		cf.screenShot("GenInfo1", "Pass", "General Information");
		cf.clickButtonContains("Continue");
		sleep(3000);

		cf.screenShot("EmployerEntityInfo", "Pass", "Entering Details - Entity Employer Info");
		String legalName = prop.getProperty("MoreThanOneMatchLegalName");
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.populateListbox("Legal Name", legalName);sleep();
		cf.screenShot("EmployerEntityInfo", "Pass", "Employer Entity Information");
		cf.clickButtonContains("Continue");sleep(3000);

		cf.screenShot("AddPrimaryBusinessAddress", "Pass", "AddPrimaryAddress-Enter Details");
		cf.enterTextboxContains("Address Line 1","25 cooper square");
		cf.enterTextboxContains("City","New York");
		cf.enterTextboxContains("Zip Code","10003");
		cf.selectDropdown("Indicate your principal activity or farm production that produces the greatest gross sales.", " Other ");
		cf.enterTextboxContains("If Other, provide details", "abc");
		cf.screenShot("AddPrimaryBusinessAddress1", "Pass", "AddPrimaryAddressBusinessDetails");
		cf.clickButtonContains("Continue");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			cf.screenShot("VerifyAddressPopUp", "Pass", "Verify Address Pop Up displayed");
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*-----Business Physical Address Details------*/
		cf.screenShot("PhysicalBussinessAddressPageDetails", "Pass", "Business Physical Address Details:SREG-007");
		cf.clickButton("Continue ");
		sleep(3000);

		/*-----Employer Contact Details------*/
		cf.screenShot("EmployerContactDetailsPage", "Pass", "Employer Contact Details-Entering Details");
		cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");sleep();
		cf.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");sleep();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("John");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("Terry");
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");sleep();
		cf.screenShot("EmployerContactDetailsPage1", "Pass", "Employer Contact Details:SREG-004");
		cf.clickButton("Continue ");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress1);
			cf.safeJavaScriptClick(AddPage.uspsAddress2);
			cf.safeJavaScriptClick(AddPage.uspsAddress3);sleep();
			cf.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Employer Contact Details Address pop up");
		}
		sleep(2000);

		/*----------Employer Verify Contact Details----------*/
		cf.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		cf.clickButton("Continue ");
		sleep(3000);

		/*----------Business Acquisition----------*/
		cf.screenShot("BussinessAquisition", "Pass", "Bussiness Aquisition:SREG-011");
		cf.clickButton("Continue ");
		sleep(3000);

		/*-----Change in Legal Entity-----*/
		cf.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity(SREG-012");
		cf.clickButton("Continue ");
		sleep(3000);

		/*-------Add Trustee/Owner Details------*/
		cf.screenShot("AddTrustee/OwnerDetails", "Pass", "Add Trustee/Owner Details-Entering Details");
		cf.enterTextboxContains("SSN", "985768958");
		cf.enterTextboxContains("First Name", "TestAuto");
		cf.enterTextboxContains("Last Name", "Automation");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99)+ "Cooper Square");
		cf.enterTextboxContains("City","NY");
		cf.enterTextboxContains("Zip Code","23263");
		cf.enterTextboxContains("Residential Telephone Number",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
		cf.screenShot("AddTrustee/OwnerDetails1", "Pass", "Add Trustee/Owner Details:SREG-006");
		cf.clickButton("Continue ");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);
		cf.screenShot("Trustee/OwnerDetails", "Pass", "Trustee/Owner Details:SREG-005");
		cf.clickButton("Continue ");
		sleep(3000);

		/*--------Upload Documents----*/
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.screenShot("UploadDocuments", "Pass", "Upload Documents(SREG-683)");
		cf.clickButtonContains("Continue");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details(SREG-800)");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.selectCheckbox("I accept");
		cf.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		cf.clickButtonContains("Submit");
		sleep(5000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		cf.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to Review Employer Type WI.....
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
		}catch(Exception e) {}

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");

		//Resolving Review Employer Type WI.....
		PEOPage.queue.click(); sleep();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN",FEIN);
		cf.screenShot("FeinSearch","Pass","Search with fein number");
		cf.clickButtonContains("Search");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ReviewEmployerTask","Pass","Review Employer Task After Search");
		cf.clickOnLink("Review Employer Type");
		sleep(2000); 
		cf.screenShot("WorkItemDetails", "Pass", "Work Item Details");
		cf.clickButtonContains("Open Work Item");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ReviewEmployerTypeTaskDetails","Pass","Review Employer Type Task Details");sleep();
		AddPage.commentField.sendKeys("review employer closing");
		cf.screenShot("ReviewEmployerTypeTaskDetails1","Pass","Review Employer Type Task Details1");
		cf.clickButtonContains("Submit"); sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("WorkItemCompleted","Pass","Workitem Completed");
		cf.clickButtonContains("Home");

		//Assigning user to DOL_DTF WI.....
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		//Resolving DOL_DTF WI.........
		PEOPage.queue.click();sleep();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("Work Item Description Free Text", "dol dtf");sleep();
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("DOLDTFDiscrepancyTask","Pass","DOL DTF Discrepancy Task");
		cf.clickOnLink("DOL DTF Discrepancy");
		cf.screenShot("DOLDTFWorkItemDetails","Pass","DOL DTFWork Item Details");
        sleep(2000); cf.clickButtonContains("Open Work Item");
		sleep(2000);cf.waitForLoadingIconToDisappear();
		cf.screenShot("DOLDTFTaskDetails","Pass","DOL DTF Task ");
		cf.selectDropdown("Quarter", "1");sleep();
		cf.selectDropdown("Year", "2023");sleep();
		cf.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		cf.selectDropdown("*Account Status ", "Liable");
		cf.enterTextboxContains("Comment", "registration in process");
		cf.clickButtonContains("Submit"); sleep(2000);
		cf.screenShot("WorkItemConfirmationScreen","Pass","WorkItemConfirmationScreen");
		cf.clickButtonContains("Home");
		sleep(5000);
		//--------Menu----
				cf.clickMenu("menu");
				cf.ScrollMenu("Inquiry");
				cf.clickMenu("Inquiry");
				cf.ScrollMenu("Inquiry");
				cf.clickMenu("Contribution Inquiry");
				sleep(2000);
				cf.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
				cf.ScrollMenu("Inquiry");
				cf.clickMenu("Inquiry Employer Account");
				//----------SREG 050
				sleep(2000);
				cf.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully landed on SREG 050 page");
				cf.enterTextboxContains(" FEIN ", FEIN);  //
				//cf.enterTextboxContains("Employer Registration Number", EAN);
				sleep(2000);
				cf.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully entered deatils and  click on continue");
				cf.clickButton("Continue ");
				sleep(2000);
				//----------SREG 051
				cf.screenShot("Inquiry Employer Account Information", "Pass", "Successfully landed on SREG 051 page");
				sleep(2000);
				cf.screenShot("TC:EE_02_010", "Pass", "Successfully completed EE_02_010  ");
		
		
		
		
		//Verify Registered employer in Inquiry page
		//em.Inquery_fein(FEIN);
		



	}
}