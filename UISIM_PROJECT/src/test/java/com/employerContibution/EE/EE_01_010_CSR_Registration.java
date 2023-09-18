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
public class EE_01_010_CSR_Registration extends TestBase {

	@Test
	public void EE_01_010() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = report.createTest(
				"EE.01.010:Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Trust' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		// commonFuntions.clickMenu("Menu");
		AddPage.menu.click();sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");sleep();
		commonFuntions.screenShot("EmployerRegistrationDetails", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", " Business ");

		// Map<String, String> databaseResults =
		// commonFuntions.database_SelectQuerySingleColumn(
		// "SELECT * FROM T_EMPLOYER_DOL_DTF tedd ORDER BY UPDATED_TS DESC", "FEIN");
		// String feinValue = databaseResults.get("FEIN");

		// String feinValue = StringUtils.left(String.valueOf((long) (Math.random() *
		// Math.pow(10, 10))), 9);
		// System.out.println("FeinValue is: " + feinValue);
		// test.log(Status.INFO, "FeinValue::" + feinValue);

		String feinValue = prop.getProperty("FeinNotPresentInDolButInDtf");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		test.log(Status.INFO, "FEIN VALUE::" + feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Trust ");
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		System.out.println(ernValue);
		test.log(Status.INFO, "ErnValue::" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");sleep();
		commonFuntions.selectDropdown("Source Type", " NYS-100 ");sleep();
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----Employer Entity Information(SREG-003)--*/
		// Map<String, String> databaseResults2 =
		// commonFuntions.database_SelectQuerySingleColumn(
		// "SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC",
		// "ENTITY_NAME");
		// String legalName = databaseResults2.get("ENTITY_NAME");
		// System.out.println("Legal Name is:" + legalName);
		// test.log(Status.INFO, "LegalName::" + legalName);

		
		//Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
		//		"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE REGISTRATION_STATUS = 'C' AND ACCOUNT_STATUS='LIAB' ORDER BY UPDATED_TS DESC",
		//		"ENTITY_NAME");
		//String legalName = databaseResults.get("ENTITY_NAME");
		
		//AddPage.legalNameTextBox.sendKeys(legalName);
		AddPage.legalNameTextBox.sendKeys(prop.getProperty("MoreThanOneMatchLegalName"));
		commonFuntions.enterTextboxContains("Trade Name", "TESTING TRADING COMPANY");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Enter date of first operations in New York State");
		commonFuntions.enterDateOfCurrentQuaterFirstMonthPlusOneDay("What is the date of the first payroll");
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		commonFuntions.selectDropdown("Quarter", "3");
		sleep();
		commonFuntions.selectDropdown("Year", "2023");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?",
				"Yes");
		commonFuntions.enterTextboxContains("Explain services that are performed", "OthersTest");
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----------'Add Primary Business Physical Address (SREG-008)'----------*/

		commonFuntions.enterTextboxContains("Address Line 1", "13TH STREET");
		commonFuntions.enterTextboxContains("City", "NEW YORK");
		commonFuntions.enterTextboxContains("Zip Code", "10011");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		commonFuntions.selectDropdown("Principal Business Activity at this location in New York State",
				" Manufacturing ");
		sleep(2000);
		AddPage.productsName.sendKeys("Automation Testing");
		AddPage.rawMaterialName.sendKeys("SteelTest");
		commonFuntions.enterTextboxContains("Percent of Total Sales Value", "45");
		commonFuntions.screenShot("AddPrimaryBussinessPhysicalAddress", "Pass",
				"Add Primary Bussiness Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			AddPage.uspsAddress.click();
			sleep();
			commonFuntions.screenShot("VerifyAddressPopUp", "Pass", "Verify Address Pop Up displayed");
			AddPage.continueButton_popUp.click();	
		} catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
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
			AddPage.adderessRadioButton1.click();
			AddPage.adderessRadioButton2.click();
			AddPage.adderessRadioButton3.click();
			commonFuntions.screenShot("VerifyAddress", "Pass", "Verify Address Pop-Up");
			AddPage.continueButton_popUp.click();
		} catch (Exception e) {
			System.out.println("Employer Contact Details Addres Pop Up");
		}
		sleep(2000);

		/*---------'Employer Verify Contact Details (SREG-521)'---------*/

		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------"Business Acquisition (SREG-011)---------*/

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes");
		// Map<String, String> databaseResults_EAN =
		// commonFuntions.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea");
		// String eanValue = databaseResults_EAN.get("Ean");
		// System.out.println("The EAN Value is:" + eanValue);
		// test.log(Status.INFO, "Ean::" + eanValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", "0463815");
		// commonFuntions.enterTextboxContains("Federal Employer Identification Number
		// (FEIN)", FEIN);
		AddPage.legalNameOfBussiness.sendKeys("MERCY HOSPITAL");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Test Address");
		commonFuntions.enterTextboxContains("City", "NEW YORK");
		commonFuntions.enterTextboxContains("Zip Code", "10023");
		commonFuntions.selectDropdown("Country", " Algeria ");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		commonFuntions.enterDateOfCurrentQuaterFirstMonthPlusOneDay("Notification date of Transfer");
		sleep();
		// commonFuntions.selectRadioQuestions("Did you acquire all or part of the
		// business?", "ALL");
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition (SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details (SREG-012)");
		commonFuntions.clickOnLinkAnchorTag(" Add Another Acquisition");
		sleep();

		/*--------"Business Acquisition (SREG-011)---------*/
		// Map<String, String> databaseResults1 =
		// commonFuntions.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea");
		// String FEIN = databaseResults1.get("Fein");
		// System.out.println("The Fein Value is:" + FEIN);
		// test.log(Status.INFO, "Fein::" + FEIN);
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "074359262");

		// enter ERN having account status as CANCELLED DUPLICATE
		// Map<String, String> databaseResults2 =
		// commonFuntions.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea
		// WHERE ACCOUNT_STATUS='CAND'");
		// String eanValue1 = databaseResults2.get("Ean");
		// System.out.println("The EAN Value is:" + eanValue1);
		// test.log(Status.INFO, "Ean::" + eanValue1);
		commonFuntions.enterTextboxContains("Employer Registration Number", "0889638");
		AddPage.legalNameOfBussiness.sendKeys("D & T GROUP INC");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Madison Ave");
		commonFuntions.enterTextboxContains("City", "NEW YORK");
		commonFuntions.enterTextboxContains("Zip Code", "10012");
		sleep();
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		commonFuntions.enterDateOfCurrentQuaterFirstMonthPlusOneDay("Notification date of Transfer");
		commonFuntions.screenShot("BusinessAcquisition1", "Pass", "Business Acquisition (SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionAnotherBAD", "Pass",
				"Adding another Business Acquisition (SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----------------"Change in Legal Entity(SREG-713)"------------*/
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes");
		commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "949161533");
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", "0467102");
		// commonFuntions.enterPastDate("Date of Legal Entity change", 30);
		commonFuntions.enterCurrentDate("Date of Notification");
		commonFuntions.screenShot("ChangeInLegalEntity", "Pass", "Change in legal entity(SREG-713)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*---------'Add Trustee/Ownwer Details(SREG-006)'-------*/
		// String SSN = StringUtils.left(String.valueOf((long) (Math.random() *
		// Math.pow(10, 10))), 9);
		// System.out.println(SSN);
		// test.log(Status.INFO, "SSN Value::" + SSN);
		commonFuntions.enterTextboxContains("SSN", "580352169");
		commonFuntions.enterTextboxContains("First Name", "Abc");
		commonFuntions.enterTextboxContains("Last Name", "Test");
		commonFuntions.screenShot("AddTrusteeOwnerDetails", "Pass", "Add Trustee Owner Details(SREG-006)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----Trustee/Owner Details-----*/
		commonFuntions.screenShot("TrusteeOwnerDetails", "Pass", "Trustee Owner Details(SREG-005)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Upload Documents(SREG-683)------*/

		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("Sample");
		sleep(3000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.screenShot("StatementOfAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass",
				"Employer Registration Confirmation:SREG-013");
		commonFuntions.clickButtonContains("Home");
		sleep(60000);

		// Assigning user to WI DOL-DTF Work item......
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
		} catch (Exception e) {

		}

		// Running query to get PROCESS_DETAIL_ID
		Map<String, String> databaseResults_processDetailId= commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_WFA_PROCESS_DETAIL WHERE PROCESS_NAME='DOLDTFDiscrepancy' ORDER BY UPDATED_TS DESC", "PROCESS_DETAIL_ID");
		String processDetailId = databaseResults_processDetailId.get("PROCESS_DETAIL_ID");
		System.out.println("FeinValue is: " + processDetailId);
		test.log(Status.INFO, "FeinValue::" + processDetailId);
		sleep(15000);
		// Resolving DOL-DTF Work item........
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "dol dtf");sleep();
		commonFuntions.clickButtonContains("Search");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("DOLDTFDiscrepancytasksearch", "Pass", "DOL-DTF Discrepancy task search");
		commonFuntions.clickOnLink("DOL DTF Discrepancy");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("WorkItemDetails", "Pass", "Work Item Details");
		sleep();
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("DOLDTFDiscrepancyTask", "Pass", "DOL DTF Discrepancy task Page");
		sleep();
		commonFuntions.forceClearText(PEOPage.clearErnFieldSection);
		commonFuntions.forceClearText(PEOPage.clearFeinFieldSection);
		String newFeinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(newFeinValue);
		test.log(Status.INFO, "ErnValue::" + newFeinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", newFeinValue);
		sleep();
		commonFuntions.enterRandomStringLegalName("Legal Name of business");
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		commonFuntions.selectRadioQuestions("Suppress Correspondence?", "No ");
		AddPage.comment.sendKeys("doldtf task");
		commonFuntions.screenShot("DOLDTFDiscrepancytaskPage1", "Pass", "DOL DTF Discrepancy task Page");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompleted", "Pass", "Work Item Completed.");
		commonFuntions.clickButtonContains("Home");
		sleep(60000);

		// Assigning user to WI Resolve Incomplete......
		// commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET
		// USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT
		// PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+newFeinValue+"'
		// ORDER BY UPDATED_TS desc)");
		sleep(5000);
		// Resolving WI Resolve Incomplete.........
		PEOPage.queue.click();
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("IndividualWorkQueue", "Pass", "IndividualWorkQueue");
		commonFuntions.enterTextboxContains("FEIN", newFeinValue);
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("FeinSearchResolveIncomplete", "Pass", "fein Search Resolve Incomplete");
		sleep();
		commonFuntions.clickOnLink("Resolve Incomplete Data");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ResolveIncompleteData", "Pass", "Resolve Incomplete Data");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ResolveIncompleteDataTransferTask", "Pass", "Resolve Incomplete Data Transfer Task");
		sleep();
		// commonFuntions.enterTextboxContains("Employer Registration Number",
		// "5665159");
		AddPage.getPredecessorErn.sendKeys("5665159");
		// commonFuntions.enterTextboxContains(" Federal Employer Identification Number
		// (FEIN) ", "001232195");
		AddPage.getPredecessorFein.sendKeys("001232195");
		commonFuntions.enterPastDate("Date Changed", 10);
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		commonFuntions.selectDropdown("Decision", " No Transfer ");
		sleep();
		AddPage.comment.sendKeys("ResolveIncomplete Task");
		sleep();
		commonFuntions.screenShot("ReviewpotentialDuplicates Task ", "Pass",
				"Review potential Duplicates Task Confirmation");
		commonFuntions.clickButtonContains("Submit");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedResolveIncomplteTask", "Pass",
				"Resolve Incomplete work item completed1");
		commonFuntions.clickButtonContains("Home");
		sleep(60000);

		// Assigning user to WI Validate Total Transfer Failed Rules Task...
		// commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET
		// USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT
		// PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER
		// BY UPDATED_TS desc)");
		sleep(5000);

		// Resolving WI Validate Total Transfer Failed Rules Task...
		PEOPage.queue.click();
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("IndividualWorkQueue1", "Pass", "IndividualWorkQueue1");
		commonFuntions.enterTextboxContains("FEIN", newFeinValue);
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("FeinSearchVerifyTransferFailedRules", "Pass",
				"fein Search VerifyTransferFailedRules");
		sleep();
		commonFuntions.clickOnLink("Verify Transfer Failed Rules");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("VerifyTransferFailedRules", "Pass", "Verify Transfer Failed Rules");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("VerifyTransferFailedRulesTask", "Pass", "Verify Transfer Failed Rules Task");
		sleep();
		// AddPage.getPredecessorErn.sendKeys("5665159");
		// AddPage.getPredecessorFein.sendKeys("001232195");
		commonFuntions.selectDropdown("Decision", " No Transfer ");
		sleep();
		AddPage.commentBox.sendKeys("verify transfer failed task");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedVerifyTransfer", "Pass", "Verify Transfer work item completed1");
		commonFuntions.clickButtonContains("Home");
		sleep(60000);

		// Assigning user to WI Resolve Incomplete 2......
		sleep(5000);
		// Resolving WI Resolve Incomplete 2.........
		PEOPage.queue.click();
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("IndividualWorkQueue2", "Pass", "IndividualWorkQueue2");
		commonFuntions.enterTextboxContains("FEIN", newFeinValue);
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("FeinSearchResolveIncomplete2", "Pass", "fein Search Resolve Incomplete2");
		sleep();
		commonFuntions.clickOnLink("Resolve Incomplete Data");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ResolveIncompleteData2", "Pass", "Resolve Incomplete Data2");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ResolveIncompleteDataTransferTask2", "Pass",
				"Resolve Incomplete Data Transfer Task2");
		sleep();
		// commonFuntions.enterTextboxContains("Employer Registration Number",
		// "5665159");
		// AddPage.getPredecessorErn.sendKeys("5665159");
		// commonFuntions.enterTextboxContains(" Federal Employer Identification Number
		// (FEIN) ", "001232195");
		// AddPage.getPredecessorFein.sendKeys("001232195");
		commonFuntions.enterPastDate("Date Changed", 10);
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		commonFuntions.selectDropdown("Decision", " No Transfer ");
		sleep();
		AddPage.comment.sendKeys("ResolveIncomplete Task 2");
		sleep();
		commonFuntions.screenShot("ReviewpotentialDuplicatesTask2 ", "Pass",
				"Review potential Duplicates Task Confirmation2");
		commonFuntions.clickButtonContains("Submit");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedResolveIncomplteTask2", "Pass",
				"Resolve Incomplete work item completed 2");
		commonFuntions.clickButtonContains("Home");
		sleep(60000);

		// Assigning user to WI Review Employment...
		sleep(5000);
		// Resolving WI Review Employment...
		PEOPage.queue.click();
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("IndividualWorkQueue3", "Pass", "IndividualWorkQueue3");
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "Review Employment");
		sleep();
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("Review Employment", "Pass", "Review Employment search keyword");
		sleep();
		commonFuntions.clickOnLink("Review Employment");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewEmploymentWIOpening", "Pass", "Review Employment WI Opening");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Review Employment Task", "Pass", "Review Employment Task");
		sleep();
		AddPage.commentField.sendKeys("closing");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedVerifyTransfer", "Pass", "Verify Transfer work item completed1");
		commonFuntions.clickButtonContains("Home");
		sleep(60000);
	}
}
