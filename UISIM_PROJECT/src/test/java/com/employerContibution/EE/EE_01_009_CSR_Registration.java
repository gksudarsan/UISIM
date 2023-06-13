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
public class EE_01_009_CSR_Registration extends TestBase {

	@Test
	public void EE_01_009() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		test = report.createTest("EE.01.009:Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Estate' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Navigating to Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		commonFuntions.screenShot("EmployerRegistrationDetails", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", " Business ");
		sleep();
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		sleep();
		commonFuntions.selectDropdown("Type of Legal Entity", " Estate ");
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		System.out.println(ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------------------'Employer Entity Information (SREG-003)'--------------*/
		//		Map<String, String> databaseResults = commonFuntions
		//				.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea", "ENTITY_NAME");
		//		String legalName = databaseResults.get("ENTITY_NAME");
		//		System.out.println("Legal Name is:: " + legalName);
		//		test.log(Status.INFO, "Legal Name::" + legalName);

		AddPage.legalNameTextBox.sendKeys(prop.getProperty("MoreThanOneMatchLegalName"));
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)",
				"AutoTest" + commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "10/1/2021");
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "1/1/2018");
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		commonFuntions.selectDropdown("Quarter", "1");
		sleep(2000);
		commonFuntions.selectDropdown("Year", "2023");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?",
				"Yes");
		commonFuntions.enterTextboxContains("Explain services that are performed", "OthersTest");
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----------'Add Primary Business Physical Address (SREG-008)'----------*/

		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		commonFuntions.selectDropdown("Principal Business Activity", "Manufacturing");
		sleep();
		AddPage.productsName.sendKeys("Automation Testing");
		commonFuntions.enterTextboxContains("Percent of Total Sales Value", "50");
		AddPage.rawMaterialName.sendKeys("SteelTest");
		commonFuntions.screenShot("AddPrimaryBussinessPhysicalAddress", "Pass",
				"Add Primary Bussiness Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyAddressPopUp", "Pass", "Verify Address Pop Up displayed");
		commonFuntions.safeJavaScriptClick(PEOPage.uspsAdd);
		sleep();
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass",
				"Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------------'Employer Contact Details (SREG-004)'------------*/

		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records",
				"Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address",
				"Same as Primary Business Physical Address");
		sleep();
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details:SREG-004");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton1);
			sleep();
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton2);
			sleep();
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton3);
			sleep();
			commonFuntions.screenShot("VerifyAddress", "Pass", "Verify Address Pop-Up");
			sleep();
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);

		} catch (Exception e) {
			System.out.println("Address pop up appears om this screen");
		}
		sleep();

		/*---------'Employer Verify Contact Details (SREG-521)'---------*/

		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------"Business Acquisition (SREG-011)---------*/

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes");
		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_EMPLOYER_TRANSFER tr ON TEA.EMPLOYER_ACCOUNT_ID = tr.FROM_EMPLOYER_ID");
		String eanValue = databaseResults1.get("Ean");
		System.out.println("The EAN Value is:" + eanValue);
		test.log(Status.INFO, "Ean::" + eanValue);
		String FEIN = databaseResults1.get("Fein");
		System.out.println("The Fein Value is:" + FEIN);
		test.log(Status.INFO, "Fein::" + FEIN);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFuntions.enterRandomStringLegalName("Legal Name of Business");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		sleep();
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition (SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details (SREG-012)");
		commonFuntions.clickOnLinkAnchorTag(" Add Another Acquisition");
		sleep();
		/*--------"Business Acquisition (SREG-011)---------*/

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "54665");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterFutureDate("Notification date of Transfer", 5);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionAnotherBAD", "Pass",
				"Adding another Business Acquisition (SREG-011)");
		commonFuntions.clickOnLinkAnchorTag(" Add Another Acquisition");
		sleep();
		/*--------"Business Acquisition (SREG-011)---------*/

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFuntions.enterRandomStringLegalName("Legal Name of Business");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterFutureDate("Notification date of Transfer", 10);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionAnother3rdBAD", "Pass",
				"Adding 3rdtime another Business Acquisition (SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity:SREG-713");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Add Executor/Owner Details(SREG-006)---------*/
		String SSN = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(SSN);
		test.log(Status.INFO, "SSN Value::" + SSN);
		commonFuntions.enterTextboxContains("SSN", SSN);
		commonFuntions.enterTextboxContains("First Name", "john");
		commonFuntions.enterTextboxContains("Last Name", "Autotest");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.safeJavaScriptClick(PEOPage.enteredAddress);
		sleep();
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		commonFuntions.screenShot("Executor/OwnerDetails", "Pass", "Executor/Owner Details:SREG-005");
		sleep();
		commonFuntions.clickOnLinkAnchorTag("Add Executor/Owner Details");
		sleep();

		/*--------Add Executor/Owner Details(SREG-006)---------*/
		String SSN1 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(SSN1);
		test.log(Status.INFO, "SSN Value::" + SSN1);
		commonFuntions.enterTextboxContains("SSN", SSN1);
		commonFuntions.enterTextboxContains("First Name", "Micheal");
		commonFuntions.enterTextboxContains("Last Name", "jordan");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.safeJavaScriptClick(PEOPage.enteredAddress);
		sleep();
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		commonFuntions.screenShot("Executor/OwnerDetails_1", "Pass", "Executor/Owner Details_1:SREG-005");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Upload Documents(SREG-683)------*/

		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL.docx");
		sleep(3000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementOfAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass",
				"Employer Registration Confirmation:SREG-013");
		commonFuntions.clickButtonContains("Home");
		sleep(2000);

		//Assigning user to WI DOL-DTF Work item..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		sleep();
		//Resolving DOL-DTF Work item................
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		//cf.selectDropdown("Work Item Description", " DOL-DTF Discrepancy task ");
		//cf.enterTextboxContains("FEIN",FEIN);
		commonFuntions.searchForworkItem(AddPage.searchByFilter);
		sleep(2000);
		commonFuntions.screenShot("DOLDTFDiscrepancytasksearch","Pass","DOL-DTF Discrepancy task search");
		//commonFuntions.clickOnLink("DOL DTF Discrepancy");
		//cf.clickButtonContains("Search");
		//sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytask","Pass","DOL-DTF Discrepancy task");
		sleep(); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytaskPage","Pass","DOL/DTF Discrepancy task Page");
		sleep();
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		AddPage.comment.sendKeys("registration  in progress");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompleted","Pass","DolDtf work item completed");
		sleep();
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI Potential Duplicate..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)"); 
		sleep(2000);

		//Resolving Potential Duplicate................
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicatesType","Pass","Review potential Duplicates");
		commonFuntions.clickOnLink("Review potential Duplicates");
		sleep(2000); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicatesWorkitem","Pass","Review potential Duplicates Task");

		sleep();
		AddPage.comment.sendKeys("revew potential task");
		sleep();
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicates Task ","Pass","Review potential Duplicates Task Confirmation");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI ResolveIncompleteData..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)"); 
		sleep(2000);

		//Resolving ResolveIncompleteData................
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicatesType","Pass","Review potential Duplicates");
		commonFuntions.clickOnLink("Resolve Incomplete Data");
		sleep(2000); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("Resolve Incomplete DataWorkitem","Pass","Resolve Incomplete Data Task");
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		commonFuntions.selectDropdown("Acquired", " All ");
		sleep();
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		sleep();
		AddPage.comment.sendKeys("Resolve Incomplete Data");
		sleep();
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicates Task ","Pass","Review potential Duplicates Task Confirmation");
		commonFuntions.clickButtonContains("Home");



	}
}
