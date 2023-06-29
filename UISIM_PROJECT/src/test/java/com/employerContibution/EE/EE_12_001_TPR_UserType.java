package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.createRandomString;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_12_001_TPR_UserType extends TestBase{

	@Test
	public void EE_12_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.12.001:Verify TPR can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Housing Authority' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		//commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep();
		commonFuntions.enterTextboxContains("First Name", "AJ");
		commonFuntions.enterTextboxContains("Last Name", "Test");
		commonFuntions.enterTextboxContains("Job Title", "TprUserTest");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("EmployerRegistrationDetailsPage", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Indian Tribe");
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The FEIN Value is:"+ feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Housing Authority");
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		System.out.println("The ERN Value is:"+ ernValue);
		test.log(Status.INFO, "Ern::" + ernValue);
		//commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("RequiredMessageValidation", "Pass", "RequiredError:SREG-003");
		AddPage.requiredError_genInfo();
		sleep();
		String entityName = commonFuntions.enterRandomStringLegalName("Legal Name");
		System.out.println(entityName);
		test.log(Status.INFO, "legalName::" + entityName);
		commonFuntions.enterTextboxContains("Other commonly known name of entity", entityName);
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterPastDate("What is the date of the first payroll", 720);
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly owned by a federally recognized Indian Tribe?", "Yes");
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals", "62");
		commonFuntions.enterPastDate("Date covered employment began?", 540);
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.screenShot("RequiredMessageValidation1", "Pass", "RequiredErrorMessage:SREG-003");
		commonFuntions.errorLabel(" Required ");
		sleep();
		commonFuntions.enterTextboxContains("Enter the name of the federally recognized Indian Tribe.", "TPRUSER");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("SameLegalNameErrorMessage", "Pass", "SameLegalNameMessagwError:SREG-003");
		commonFuntions.errorLabel(" Other Name cannot be the same as Legal Name of business");
		sleep();
		commonFuntions.enterRandomStringLegalName("Legal Name");
		commonFuntions.enterTextboxContains("Other commonly known name of entity", "TESTTPRUSER");
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly owned by a federally recognized Indian Tribe?", "Yes");
		commonFuntions.enterPastDate("What is the date of the first payroll", 365);
		commonFuntions.enterPastDate("Date covered employment began?", 180);
		commonFuntions.selectRadioQuestions("Financing Method", "Reimbursable");
		sleep();
		commonFuntions.screenShot("EmployerEntityInformationPage", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------------SREG-008:Add Primary Business Physical Address ----*/
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("AddPrimaryBusinessPhysicalAddressErrorMessage", "Pass", "Bussiness Physical Address Required Error Message:SREG-008");
		AddPage.requiredError_genInfo();
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+"Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.selectDropdown("State", "--SELECT--");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"23");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("StateSelectionErrorMessage", "Pass", "State Selection Error Message:SREG-008");
		commonFuntions.errorLabel("Required");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+"Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.selectDropdown("State", "New York");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"23");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Bussiness Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("Tom");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("Tert");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			AddPage.adderessRadioButton1.click();
			AddPage.adderessRadioButton2.click();
			AddPage.adderessRadioButton3.click();
			commonFuntions.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Employer Contact Details Information");
		}
		sleep(2000);

		/*--Employer Verify Contact Details--SREG-521---*/
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Upload Documents----*/
		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(2000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents(SREG-683)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details(SREG-800)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit");
		sleep(5000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to WI Review emp type..................
		loginPage.okPopUpButton.click();
		sleep(2000);
		commonFuntions.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");

		//Resolving WI Review emp type................
		PEOPage.queue.click(); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","FeinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("IndividualWorkQueueReviewWorkItemQueue","Pass","Individual Work Queue Review");
		commonFuntions.clickOnLinkAnchorTag("Review Employer Type");
		sleep(2000); 
		commonFuntions.screenShot("WorkItemDetails","Pass","Work Item Details");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("ReviewEmployerTypeTaskDetails","Pass","Review Employer Type Task Details");
		commonFuntions.enterFutureDate("Date Covered Employment began? ", 10);
		AddPage.commentField.sendKeys("registration  in progress");
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewWorkItemCompleted","Pass","Review Workitem Completed");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI Obtain Bond Task..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		//Resolving WI Obtain Bond Task................
		PEOPage.queue.click(); 
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","FeinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(3000);
		commonFuntions.screenShot("IndividualWorkQueueObtainBond","Pass","Individual Work Queue Obtain Bond");
		commonFuntions.clickOnLinkAnchorTag("Obtain Bond Task");
		sleep(2000); 
		commonFuntions.screenShot("WorkItemDetailsObtainBond","Pass","Work Item Details Obtain Bond");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.enterFutureDate("Liability Date", 10);
		sleep(2000);
		commonFuntions.enterFutureDate("Date covered employment began? ", 10);
		sleep(2000);
		AddPage.commentField.sendKeys("obtain bond task closing");
		commonFuntions.screenShot("ObtainBondTask","Pass","Obtain Bond Task");
		commonFuntions.clickButtonContains("Submit"); 
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewWorkItemObtainBondTask","Pass","Review Workitem Obtain Bond Task");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI unable to determine liability Work item..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep();
		//Resolving DOL-DTF Work item................non expected WI
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectDropdown("WorkItemDescription", "Unable to determine liability task");
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "liability");sleep();
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("UnabletoDetermineLiabilityTask","Pass","Unable to Determine Liability Task search");
		commonFuntions.clickOnLink("DOL DTF Discrepancy");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytask","Pass","DOL-DTF Discrepancy task");
		sleep(); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytaskPage","Pass","DOL/DTF Discrepancy task Page");
		sleep();
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		AddPage.comment.sendKeys("doldtf");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("workitemCompletedDolDtf","Pass","DolDtf work item completed");
		commonFuntions.clickButtonContains("Home");

	}
}
