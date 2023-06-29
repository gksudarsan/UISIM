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

public class EE_06_002_Household_Domestic_Partnership extends TestBase{

	@Test()
	public void EE_06_002_csr_registration() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		test = report.createTest("EE.06.002:Verify CSR can submit employer registration for employer type 'Household/Domestic' and legal entity type 'Partnership' and work items will be created for CSR to review.");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		//cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); sleep();
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*-------General Information (SREG-025)-------*/
		cf.selectDropdown("Employer Type", "  Household/Domestic ");
		//		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
		//				"SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea  WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ) GROUP BY FEIN HAVING COUNT(*)>1 " , "FEIN"); 
		//		String FEIN = databaseResults1.get("FEIN");
		String feinValue = prop.getProperty("FeinPresentInDolNotInDtf");
		System.out.println("Fein Number is:: = " +feinValue);
		test.log(Status.INFO, "FEIN VALUE::" + feinValue);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);  
		cf.selectDropdown("*Type of Legal Entity"," Sole Proprietorship (Individual) "); 
		//cf.enterTextboxContains("Employer Registration Number", value);
		cf.selectDropdown("Source", " NYS-100 (paper) ");sleep();
		cf.selectDropdown("Source Type", " NYS-100 ");sleep();
		cf.screenShot("GeneralInformationPage", "Pass", "General Information:SREG-025");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*----Household/Domestic Employers------*/
		String legalName = prop.getProperty("LegalFoundInDtfNotInDol");
		AddPage.legalNameTextBox.sendKeys(legalName);
		cf.selectDropdown("Quarter ", " 2 ");sleep();
		cf.selectDropdown("Year ", " 2022 ");sleep();
		cf.selectRadioQuestions("Will you withhold New York State Income Tax from these employees?", "Yes ");
		cf.screenShot("Household/DomesticEmployers", "Pass", "Household/Domestic Employers");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*----Add Primary Business Physical Address----*/
		cf.enterTextboxContains("Address Line 1","23 Cooper Square");
		cf.enterTextboxContains("City","New York");
		cf.enterTextboxContains("Zip Code","10003");
		cf.selectDropdown("County", " Albany ");
		cf.screenShot("AddPrimaryBusinessPhysicalAddress", "Pass", "Add Primary Business Physical Address");
		cf.clickButtonContains("Continue");
		sleep(2000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*--Business Physical Address Details---*/
		cf.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*---Employer Contact Details----*/
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		AddPage.addressLine1_Form1.sendKeys("43 cooper sqaure");
		AddPage.city_Form1.sendKeys("NY");
		AddPage.zipCode_Form1.sendKeys("10045");
		AddPage.countyDropDown_Form1.click();sleep();
		AddPage.countyValue1.click();
		cf.selectRadioQuestions("Location of Books and Records", "Other");
		AddPage.addressLine1_Form2.sendKeys("56 cooper sqaure");
		AddPage.city_Form2.sendKeys("NY");
		AddPage.zipCode_Form2.sendKeys("10065");
		AddPage.countyDropDown_Form2.click();sleep();
		AddPage.countyValue2.click();sleep();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("jony");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("mac");
		cf.enterTextboxContains(" Telephone Number ", "5544435678");
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		cf.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details:SREG-004");
		cf.clickButtonContains("Continue");
		sleep(2000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress1);
			cf.safeJavaScriptClick(AddPage.uspsAddress2);
			cf.safeJavaScriptClick(AddPage.uspsAddress3);
			cf.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Employer Contact Details Address pop up");
		}
		sleep(2000);
		cf.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*---Change in Legal Entity------*/
		cf.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		cf.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "260274280");
		cf.enterTextboxContains("Prior Employer Registration Number", "0463870");
		cf.enterCurrentDate("Date of Legal Entity change");
		cf.enterCurrentDate("Date of Notification");
		cf.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*----Add Sole Proprietorship Details------*/
		cf.enterTextboxContains("SSN", Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
		cf.enterTextboxContains("First Name", "abc");
		cf.enterTextboxContains("Last Name", "tony");
		cf.enterTextboxContains("Address Line 1","23 Cooper Square");
		cf.enterTextboxContains("City","New York");
		cf.enterTextboxContains("Zip Code","10003");
		cf.screenShot("AddSoleProprietorshipDetails", "Pass", "Add Sole Proprietorship Details");
		cf.clickButtonContains("Continue");
		sleep(2000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);
		cf.screenShot("SoleProprietorshipDetails", "Pass", "Add Sole Proprietorship Details");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*---Upload Documents-----*/
		cf.selectLink("Supporting documents like", "Browse");
		sleep(3000);
		cf.screenShot("UploadDocuments", "Pass", "Upload Documents(SREG-683)");
		cf.clickButtonContains("Continue");
		sleep(5000);
		cf.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details(SREG-800)");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.selectCheckbox("I accept");
		cf.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		cf.clickButtonContains("Home ");
		sleep(5000);
		//Assigning user to WI Review emp type............
		loginPage.okPopUpButton.click();
		sleep(2000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

		//Resolving 1 WI................
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN",feinValue);
		cf.screenShot("FeinSearch","Pass","FeinSearch");
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("IndividualWorkQueueReviewWorkItemQueue","Pass","Individual Work Queue Review");
		sleep(2000);
		cf.clickOnLinkAnchorTag("Review Employer Type");
		sleep(2000); 
		cf.screenShot("WorkItemDetails","Pass","Work Item Details");
		cf.clickButtonContains("Open Work Item");
		sleep(2000);
		cf.screenShot("ReviewEmployerTypeTaskDetails","Pass","Review Employer Type Task Details");
		cf.enterFutureDate("Date Covered Employment began? ", 10);
		AddPage.commentField.sendKeys("registration  in progress");
		cf.clickButtonContains("Submit"); 
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ReviewWorkItemCompleted","Pass","Review Workitem Completed");
		cf.clickButtonContains("Home");
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

		//Resolving 2ND WI ................
		PEOPage.queue.click();
		sleep(15000);
		cf.enterTextboxContains("FEIN",feinValue);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search"); Thread.sleep(2000);
		cf.screenShot("DOL DTF Discrepancy","Pass","emp type");
		cf.clickOnLink("DOL DTF Discrepancy");

		Thread.sleep(2000); cf.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		cf.screenShot("","Pass","DOL DTF ");
		cf.selectDropdown("Quarter", "1");sleep();
		cf.selectDropdown("Year", "2023");sleep();
		cf.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		cf.selectDropdown("*Account Status ", "Liable");
		cf.enterTextboxContains("Comment", "registration in process");
		cf.clickButtonContains("Submit"); Thread.sleep(2000);
		cf.screenShot("GeneralInfo","Pass","General Information");
		cf.clickButtonContains("Home");

		//Verify Registered employer in Inquery page 	...........
		//em.Inquery_fein(FEIN);
		test.log(Status.PASS, "Clicked on Home button");




	}
}