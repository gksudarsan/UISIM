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
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_011;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_009_CSR_Registration extends TestBase {

	@Test
	public void EE_01_009() throws Exception {
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SREG_011 sreg011 = new SREG_011(driver);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = report.createTest(
				"EE.01.009:Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Estate' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		// commonFuntions.clickMenu("Menu");
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
		// Map<String, String> databaseResults = commonFuntions
		// .database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea",
		// "ENTITY_NAME");
		// String legalName = databaseResults.get("ENTITY_NAME");
		// System.out.println("Legal Name is:: " + legalName);
		// test.log(Status.INFO, "Legal Name::" + legalName);

		AddPage.legalNameTextBox.sendKeys(prop.getProperty("MoreThanOneMatchLegalNameInDOLANDOneMatchInDTF")); // Mult match with  DOL and one match with DTF
																												
																												 																				 
																												
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)",
				"AutoTest" + commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "1/1/2022"); //prior 6 Quarter
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "2/1/2022"); //prior 6 Quarter +1 month
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		commonFuntions.selectDropdown("Quarter", "2");
		sleep(2000);
		commonFuntions.selectDropdown("Year", "2022"); //prior 5 Quarter
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
		//commonFuntions.enterTextboxContains("Number of employees at this location", "45");
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
		sreg004.listOfFirstname.get(0).sendKeys("FN");
		sreg004.listOfLastName.get(0).sendKeys("LN");
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

///////////////////
		/*
		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_EMPLOYER_TRANSFER tr ON TEA.EMPLOYER_ACCOUNT_ID = tr.FROM_EMPLOYER_ID");*/
		
		Map<String, String> databaseResults1 = PEOPage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;");
		
		String feinValue2 = databaseResults1.get("Fein");
		String eanValue2 = databaseResults1.get("Ean");
		String legalname2 = databaseResults1.get("legalName");
		System.out.println("The EAN Value is:" + feinValue2);
		test.log(Status.INFO, "Ean::" + feinValue2);
		System.out.println("The EAN Value is:" + eanValue2);
		test.log(Status.INFO, "Ean::" + eanValue2);
		System.out.println("The EAN Value is:" + legalname2);
		test.log(Status.INFO, "Ean::" + legalname2);
		
		//commonFuntions.enterTextboxContains("Employer Registration Number", eanValue2);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		
		//commonFuntions.enterRandomStringLegalName("Legal Name of Business");
		sreg011.legalNameField.sendKeys(legalname2);
		
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		//commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterTextboxContains("Acquisition Date", "1/1/2022");
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
		
		Map<String, String> databaseResults3 = PEOPage.database_SelectQuery2(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;");
		
		String feinValue3 = databaseResults1.get("Fein");
		String eanValue3 = databaseResults1.get("Ean");
		String legalname3 = databaseResults1.get("legalName");
		System.out.println("The EAN Value is:" + feinValue3);
		test.log(Status.INFO, "Ean::" + feinValue3);
		System.out.println("The EAN Value is:" + eanValue3);
		test.log(Status.INFO, "Ean::" + eanValue3);
		System.out.println("The EAN Value is:" + legalname3);
		test.log(Status.INFO, "Ean::" + legalname3);

		//String feinValue3 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		//System.out.println(feinValue3);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue3);
		//sreg011.legalNameField.sendKeys(legalname3);
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "54665");
		commonFuntions.enterPastDate("Acquisition Date",1);
		commonFuntions.enterCurrentDate("Notification date of Transfer");  //same date
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

		Map<String, String> databaseResults4 = PEOPage.database_SelectQuery3(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;");
		
		String feinValue4 = databaseResults1.get("Fein");
		String eanValue4 = databaseResults1.get("Ean");
		String legalname4 = databaseResults1.get("legalName");
		System.out.println("The EAN Value is:" + feinValue4);
		test.log(Status.INFO, "Ean::" + feinValue4);
		System.out.println("The EAN Value is:" + eanValue4);
		test.log(Status.INFO, "Ean::" + eanValue4);
		System.out.println("The EAN Value is:" + legalname4);
		test.log(Status.INFO, "Ean::" + legalname4);

		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue4);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue4);
		sreg011.legalNameField.sendKeys(legalname4);
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterPastDate("Acquisition Date",1);
		commonFuntions.enterCurrentDate("Notification date of Transfer"); //same date
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
		commonFuntions.enterTextboxContains("First Name", "FN");
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.selectDropdown("Title", " Executor ");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.enterTextboxContains(" Residential Telephone Number ", "8457864799");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		//commonFuntions.safeJavaScriptClick(PEOPage.enteredAddress);
		//sleep();
		//commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
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
		commonFuntions.enterTextboxContains("First Name", "FN");
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.selectDropdown("Title", " Executor ");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.enterTextboxContains(" Residential Telephone Number ", "8457864799");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		//commonFuntions.safeJavaScriptClick(PEOPage.enteredAddress);
		//sleep();
		//commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
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
		sleep(5000);

		// Assigning user to WI DOL-DTF Work item..................
		// loginPage.okPopUpButton.click();sleep(2000);
		// commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET
		// USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT
		// PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER
		// BY UPDATED_TS desc)");
		sleep();
		// Resolving DOL-DTF Work item........
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectDropdown("WorkItemDescription", " DOL-DTF Discrepancy task ");
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "dol dtf");
		sleep();
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("DOLDTFDiscrepancytasksearch", "Pass", "DOL-DTF Discrepancy task search");
		commonFuntions.clickOnLink("DOL DTF Discrepancy");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytask", "Pass", "DOL-DTF Discrepancy task");
		sleep();
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytaskPage", "Pass", "DOL/DTF Discrepancy task Page");
		sleep();
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		commonFuntions.selectRadioQuestions("Suppress Correspondence?", " No");
		sleep();
		sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedDolDtf", "Pass", "DolDtf work item completed");
		commonFuntions.clickButtonContains("Home");

		// Assigning user to WI Potential Duplicate..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(2000);

		/*
		 * //Resolving Potential Duplicate................ PEOPage.queue.click();
		 * commonFuntions.waitForLoadingIconToDisappear();
		 * commonFuntions.enterTextboxContains("FEIN",feinValue);
		 * commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		 * commonFuntions.clickButtonContains("Search"); sleep(2000);
		 * commonFuntions.screenShot("ReviewpotentialDuplicatesType",
		 * "Pass","Review potential Duplicates");
		 * commonFuntions.clickOnLink("Review potential Duplicates"); sleep(2000);
		 * commonFuntions.clickButtonContains("Open Work Item"); sleep(2000);
		 * commonFuntions.screenShot("ReviewpotentialDuplicatesWorkitem",
		 * "Pass","Review potential Duplicates Task");
		 * 
		 * sleep(); AddPage.comment.sendKeys("revew potential task"); sleep();
		 * commonFuntions.clickButtonContains("Submit"); sleep(2000);
		 * commonFuntions.screenShot("ReviewpotentialDuplicates Task "
		 * ,"Pass","Review potential Duplicates Task Confirmation");
		 * commonFuntions.clickButtonContains("Home");
		 * 
		 * //Assigning user to WI ResolveIncompleteData..................
		 * commonFuntions.
		 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
		 * +COMMON_CONSTANT.
		 * CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
		 * +feinValue+"' ORDER BY UPDATED_TS desc)"); sleep(2000);
		 */
		// Resolving ResolveIncompleteData................
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicatesType", "Pass", "Review potential Duplicates");
		commonFuntions.clickOnLink("Resolve Incomplete Data");
		sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("Resolve Incomplete DataWorkitem", "Pass", "Resolve Incomplete Data Task");
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		commonFuntions.selectDropdown("Acquired", " All ");
		sleep();
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		sleep();
		// AddPage.comment.sendKeys("Resolve Incomplete Data");
		sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicates Task ", "Pass",
				"Review potential Duplicates Task Confirmation");
		commonFuntions.clickButtonContains("Home");

		// Assigning user to WI Verify Predecessor data
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(2000);

		// 4 Resolving Verify Predecessor data................
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("Verify Predecessor data", "Pass", "Verify Predecessor data Task");
		// commonFuntions.clickOnLink("Predecessor data");
		sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("Verify Predecessor data DataWorkitem", "Pass", "Verify Predecessor data Task");

		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("Verify Predecessor data Task ", "Pass", "Verify Predecessor data Task Confirmation");
		commonFuntions.clickButtonContains("Home");

		// Assigning user to WI VerifyTransferFailedRules
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(2000);

		// 5 Resolving VerifyTransferFailedRules................
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("VerifyTransferFailedRules", "Pass", "VerifyTransferFailedRules Task");
		commonFuntions.clickOnLink("Verify Transfer Failed Rules");
		sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("VerifyTransferFailedRules DataWorkitem", "Pass", "VerifyTransferFailedRules Task");
		sleep(2000);
		commonFuntions.selectDropdown("Decision", " No Transfer ");
		sleep();
		sreg043.EEWI014CommentFeild.sendKeys("VerifyTransferFailedRules");
		commonFuntions.screenShot("VerifyTransferFailedRules DataWorkitem", "Pass", "VerifyTransferFailedRules Task");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("VerifyTransferFailedRules Task ", "Pass",
				"VerifyTransferFailedRules Task Confirmation");

		commonFuntions.clickButtonContains("Home");

		// Assigning user to WI ResolveIncompleteData
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(2000);

		// 6 Resolving ResolveIncompleteData................
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("ResolveIncompleteData", "Pass", "ResolveIncompleteData Task");
		commonFuntions.clickOnLink("Resolve Incomplete Data");
		sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("ResolveIncompleteData DataWorkitem", "Pass", "ResolveIncompleteData Task");
		sleep(2000);

		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		sleep();

		sreg043.EEWI002CommentsField.sendKeys("resolve incomplete Cm");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("ResolveIncompleteData Task ", "Pass", "ResolveIncompleteData Task Confirmation");
		commonFuntions.clickButtonContains("Home");

		// Assigning user to WI VerifyTransferFailedRules
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(2000);

		// 7 Resolving VerifyTransferFailedRules................
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("VerifyTransferFailedRules", "Pass", "VerifyTransferFailedRules Task");
		commonFuntions.clickOnLink("Verify Transfer Failed Rules");
		sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("VerifyTransferFailedRules DataWorkitem", "Pass", "VerifyTransferFailedRules Task");
		sleep(2000);
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		sleep();
		sreg043.EEWI014CommentFeild.sendKeys("VerifyTransferFailedRules");
		commonFuntions.screenShot("VerifyTransferFailedRules DataWorkitem", "Pass", "VerifyTransferFailedRules Task");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("VerifyTransferFailedRules Task ", "Pass",
				"VerifyTransferFailedRules Task Confirmation");

		commonFuntions.clickButtonContains("Home");

		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(2000);

	}
}
