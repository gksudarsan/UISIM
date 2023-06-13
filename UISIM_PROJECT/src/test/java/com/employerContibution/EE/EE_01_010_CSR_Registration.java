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
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep();
		commonFuntions.screenShot("EmployerRegistrationDetails", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", " Business ");
		//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
		//				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd ORDER BY UPDATED_TS DESC", "FEIN");
		//		String feinValue = databaseResults.get("FEIN");

		//		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		//		System.out.println("FeinValue is: " + feinValue);
		//		test.log(Status.INFO, "FeinValue::" + feinValue);
		String Fein = prop.getProperty("FEINfoundbutprovidedERNisincorrect_Fein");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)",Fein);
		test.log(Status.INFO, "FEIN VALUE::" + Fein);
		commonFuntions.selectDropdown("Type of Legal Entity", " Trust ");
		//		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		//		System.out.println(ernValue);
		//		test.log(Status.INFO, "ErnValue::" + ernValue);

		commonFuntions.enterTextboxContains("Employer Registration Number", prop.getProperty("FEINfoundbutprovidedERNisincorrect_Ern"));
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----Employer Entity Information(SREG-003)--*/
		//		Map<String, String> databaseResults2 = commonFuntions.database_SelectQuerySingleColumn(
		//				"SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC", "ENTITY_NAME");
		//		String legalName = databaseResults2.get("ENTITY_NAME");
		//		System.out.println("Legal Name is:" + legalName);
		//		test.log(Status.INFO, "LegalName::" + legalName);

		AddPage.legalNameTextBox.sendKeys(prop.getProperty("MoreThanOneMatchLegalName"));
		commonFuntions.enterTextboxContains("Trade Name", "TestAutoCompany");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "5/1/2023");
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "5/1/2023");
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		commonFuntions.selectDropdown("Quarter", "2");
		sleep();
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
		AddPage.rawMaterialName.sendKeys("SteelTest");
		commonFuntions.screenShot("AddPrimaryBussinessPhysicalAddress", "Pass",
				"Add Primary Bussiness Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyAddressPopUp", "Pass", "Verify Address Pop Up displayed");
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep();
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------------'Employer Contact Details (SREG-004)'------------*/

		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details:SREG-004");
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

		/*---------'Employer Verify Contact Details (SREG-521)'---------*/

		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------"Business Acquisition (SREG-011)---------*/

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes");
		Map<String, String> databaseResults = commonFuntions.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea");
		String eanValue = databaseResults.get("Ean");
		System.out.println("The EAN Value is:" + eanValue);
		test.log(Status.INFO, "Ean::" + eanValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		// commonFuntions.enterTextboxContains("Federal Employer Identification Number
		// (FEIN)", FEIN);
		commonFuntions.enterRandomStringLegalName("Legal Name of Business");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition (SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details (SREG-012)");
		commonFuntions.clickOnLinkAnchorTag(" Add Another Acquisition");
		sleep();

		/*--------"Business Acquisition (SREG-011)---------*/
		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC");
		String FEIN = databaseResults1.get("Fein");
		System.out.println("The Fein Value is:" + FEIN);
		test.log(Status.INFO, "Fein::" + FEIN);
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?","Yes");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);

		// enter ERN having account status as CANCELLED DUPLICATE
		Map<String, String> databaseResults2 = commonFuntions.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CAND'");
		String eanValue1 = databaseResults2.get("Ean");
		System.out.println("The EAN Value is:" + eanValue1);
		test.log(Status.INFO, "Ean::" + eanValue1);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue1); 
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "54665");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "All");
		commonFuntions.enterCurrentDate("Acquisition Date");
		commonFuntions.enterFutureDate("Notification date of Transfer", 5);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BusinessAcquisitionAnotherBAD", "Pass", "Adding another Business Acquisition (SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----------------"Change in Legal Entity(SREG-713"------------*/ 
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes");
		commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", FEIN);
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", eanValue);
		commonFuntions.enterCurrentDate("Date of Notification");
		commonFuntions.screenShot("ChangeInLegalEntity", "Pass", "Change in legal entity(SREG-713)");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*---------'Add Trustee/Ownwer Details(SREG-006)'-------*/
		String SSN = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(SSN);
		test.log(Status.INFO, "SSN Value::" + SSN);
		commonFuntions.enterTextboxContains("SSN", SSN);
		commonFuntions.enterTextboxContains("First Name", "Automation");
		commonFuntions.enterTextboxContains("Last Name", "Test");
		commonFuntions.screenShot("AddTrustee/OwnerDetails", "Pass", "Add Trustee/Owner Details(SREG-006)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----Trustee/Owner Details-----*/
		commonFuntions.screenShot("Trustee/OwnerDetails", "Pass", "Trustee/Owner Details(SREG-005)");
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
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+Fein+"' ORDER BY UPDATED_TS desc)");
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
		commonFuntions.selectRadioQuestions("Suppress Correspondence?", "No");
		AddPage.comment.sendKeys("registration  in progress");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedDolDtf","Pass","DolDtf work item completed");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI Potential Duplicate..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+Fein+"' ORDER BY UPDATED_TS desc)"); 
		sleep(2000);

		//Resolving Potential Duplicate................
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",Fein);
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
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+Fein+"' ORDER BY UPDATED_TS desc)"); 
		sleep(2000);

		//Resolving ResolveIncompleteData................
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",Fein);
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
