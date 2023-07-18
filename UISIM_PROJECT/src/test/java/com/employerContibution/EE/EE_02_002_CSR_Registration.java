package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class EE_02_002_CSR_Registration extends TestBase{

	@Test
	public void EE_02_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.02.002:Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Partnership' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");sleep();
		commonFuntions.screenShot("OpenRegisterEmployerPage", "Pass", "Navigate to Register Employer");
		commonFuntions.clickMenu("Register Employer");sleep();
		commonFuntions.screenShot("EmployerRegistration1", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);

		/*------General Information (SREG-025)-----*/
		commonFuntions.screenShot("GenInfo", "Pass", "General Information:SREG-025");
		commonFuntions.selectDropdown("Employer Type", "Agricultural");
		//Generating fein..
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The Fein Value is:"+ feinValue);
		test.log(Status.INFO, "FEIN VALUE::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Partnership ");
		//Generating ern..
		//Map<String, String> databaseResults =commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "EAN");
		//String ernValue = databaseResults.get("EAN");
		String ernValue = prop.getProperty("ErnPresentInDolDtf");
		System.out.println("EanValue is: "+ ernValue );
		test.log(Status.INFO, "ERN VALUE::" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");sleep();
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);

		//Generating Legal Name..
		/*---------- SREG-003--------------------*/
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd ORDER BY UPDATED_TS DESC",
				"LEGAL_NAME");
		String legalName = databaseResults.get("LEGAL_NAME");
		System.out.println("LegalName is: "+ legalName );
		commonFuntions.screenShot("EmployerEntityInfo", "Pass", "Employer Entity Information:SREG-003)");
		AddPage.legalNameTextBox.sendKeys(legalName);
		test.log(Status.INFO, "Legal Name:" + legalName);
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "AutoTest"+commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("Business Phone Number", Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "6/1/2021");
		commonFuntions.selectDropdown("Quarter", "4");sleep();
		commonFuntions.selectDropdown("Year", "2023");sleep();
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "Yes");
		commonFuntions.enterTextboxContains("Explain services that are performed", "AutomationTest");
		commonFuntions.enterTextboxContains("Total number of covered employees", "500");
		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);

		/*-------------SREG-008-------------------*/
		commonFuntions.screenShot("AddPrimaryBusinessDetails", "Pass", "Entering Address Details");
		commonFuntions.enterTextboxContains("Address Line 1",  commonFuntions.createRandomInteger(10,99 )+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code",commonFuntions.createRandomInteger(100, 999)+"23");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		AddPage.selectDropdown.click();sleep();
		AddPage.selectOtherDropDown.click();sleep();
		commonFuntions.enterTextboxContains("Specify Type", "CSRTest23");
		commonFuntions.enterTextboxContains("If Other, provide details", "testing");
		commonFuntions.screenShot("AddPrimaryBussinessAddresDeatils", "Pass", "Add Primary Bussiness Details:SREG-008");
		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*-----Business Physical Address Details------*/
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*-----Employer Contact Details------*/
		commonFuntions.screenShot("EmployerVerifyContact", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		AddPage.addressLine1_Form1.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Cooper Square");
		AddPage.city_Form1.sendKeys("NY");
		AddPage.zipCode_Form1.sendKeys(commonFuntions.createRandomInteger(10, 99)+"678");
		AddPage.countyDropdown1.click();sleep();
		AddPage.countyValue1.click();
		commonFuntions.screenShot("EmployerVerifyContact1", "Pass", "Business Mailing Address");

		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		AddPage.addressLine1_Form2.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Cooper Square");
		AddPage.city_Form2.sendKeys("NY");
		AddPage.zipCode_Form2.sendKeys(commonFuntions.createRandomInteger(10, 99)+"345");
		AddPage.countyDropdown2.click();sleep();
		AddPage.countyValue2.click();sleep();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("Test");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("AbtoTest");
		commonFuntions.screenShot("EmployerVerifyContact2", "Pass", "Location of Books and Records");

		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFuntions.screenShot("EmployerContactDetails3", "Pass", "Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButtonContains("Continue");
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

		/*-------Add Partnership Details------*/
		commonFuntions.screenShot("AddPartnership", "Pass", "Add Partnership Details");
		commonFuntions.selectRadioQuestions("Type of Partner/Owner", "Individual");sleep();
		commonFuntions.enterTextboxContains("First Name", "Sanjahjk");
		commonFuntions.enterTextboxContains("Last Name", "Singh");
		commonFuntions.enterTextboxContains("Address Line 1", "Ave"+ commonFuntions.createRandomInteger(10,99));
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","98798");
		commonFuntions.screenShot("AddPartnershipDetails", "Pass", "Add Partnership Details: SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*----Partner Details-----*/
		commonFuntions.screenShot("PartnerDetails", "Pass", "Partner details: SREG-005");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

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
		commonFuntions.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
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
		//commonFuntions.enterFutureDate("Date Covered Employment began? ", 10);
		AddPage.commentField.sendKeys("review employer closing");
		commonFuntions.screenShot("ReviewEmployerTypeTaskDetails1","Pass","Review Employer Type Task Details1");
		commonFuntions.clickButtonContains("Submit"); sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("WorkItemCompleted","Pass","Workitem Completed");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI DOL-DTF Work item.........
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		//Resolving DOL-DTF Work item........
		PEOPage.queue.click(); sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "dol dtf");sleep();
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("DOLDTFDiscrepancytasksearch","Pass","DOL-DTF Discrepancy task search");
		commonFuntions.clickOnLink("DOL DTF Discrepancy");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytask","Pass","DOL-DTF Discrepancy task"); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("DOL/DTFDiscrepancytaskPage","Pass","DOL/DTF Discrepancy task Page");
		commonFuntions.selectDropdown("Account Status", " Liable ");sleep();
		AddPage.comment.sendKeys("doldtf closing");
		commonFuntions.clickButtonContains("Submit");sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedDolDtf","Pass","DolDtf work item completed");
		commonFuntions.clickButtonContains("Home");

		//Navigating to Inquiry Employer Account 
		AddPage.menu.click();sleep();
		commonFuntions.clickMenu("Inquiry");sleep();
		commonFuntions.clickMenu("Contribution Inquiry");sleep();
		commonFuntions.screenShot("Inquiry", "Pass", "Navigating to Inquiry Employer Account ");
		commonFuntions.clickMenu("Inquiry Employer Account");sleep();
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		commonFuntions.screenShot("InquiryEmployerAccount", "Pass", "Inquiry Employer Account");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.screenShot("InquiryEmployerAccountInformation", "Pass", "Inquiry Employer Account Information");

	}
}