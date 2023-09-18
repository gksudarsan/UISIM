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
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_07_002_EmployerType extends TestBase{

	@Test
	public void EE_07_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.07.002:Verify employer can submit employer registration for employer type 'Governmental' and legal entity type 'Town' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");sleep();
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");sleep();

		/*----Employer Registration (SREG-001)-----*/
		commonFuntions.enterTextboxContains("First Name", "Sanjay");
		commonFuntions.enterTextboxContains("Last Name", "TestAuto");
		commonFuntions.enterTextboxContains("Job Title", "AutomationEngineer");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*---General Information (SREG-025)-----*/
		commonFuntions.selectDropdown("Employer Type", "Governmental");sleep();
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The fein  value is ::" +feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Town");
		String ernValue = prop.getProperty("ErnPresentInDolButNotInDtf");
		System.out.println("The fein  value is ::" +ernValue);
		test.log(Status.INFO, "Fein::" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*---'Employer Entity Information  (SREG-003)----*/
		String legalName = prop.getProperty("MoreThanOneMatchLegalName");
		AddPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterPastDate("What is the date of the first payroll", 365);
		commonFuntions.enterTextboxContains("Estimated or approximate number", "475");
		commonFuntions.enterPastDate("Date covered employment began?", 270);
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information  (SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*---Add Primary Business Physical Address (SREG-008---*/
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10,99 )+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.selectDropdown("County", "Albany");sleep();
		commonFuntions.screenShot("AddPrimaryBusinessPhysicalAddress", "Pass", "Add Primary Business Physical Address");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
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
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		AddPage.addressLine1_Form1.sendKeys("23 cooper square");
		AddPage.city_Form1.sendKeys("ny");
		AddPage.zipCode_Form1.sendKeys("25653");
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		AddPage.addressLine1_Form2.sendKeys("24 cooper square");
		AddPage.city_Form2.sendKeys("ny");
		AddPage.zipCode_Form2.sendKeys("45797");
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("abc");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("test");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");sleep();
		commonFuntions.selectRadioQuestions("Agent (C/O) address", "Same as Location of Books and Records");
		//commonFuntions.enterTextboxContains("Care Of", "LEGACY");
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details (SREG-004)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress4);sleep();
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress1);sleep();
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress2);sleep();
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress3);sleep();
			commonFuntions.screenShot("VerifyAddress", "Pass", "Verify Address Pop-Up");
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Employer Contact Details Addres Pop Up");
		}
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		//commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		//commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Upload Documents(SREG-683)------*/
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
		
		//Assigning user to WI Review employer type..................
		try {
		loginPage.okPopUpButton.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		}catch(Exception e) {}
		
		commonFuntions.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(2000);
		
		//Resolving WI Review employer type................
		PEOPage.queue.click(); 
		sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","FeinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(3000);
		commonFuntions.screenShot("IndividualWorkQueueReviewWorkItemQueue","Pass","Individual Work Queue Review");
		sleep(3000);
		commonFuntions.clickOnLinkAnchorTag("Review Employer Type");
		sleep(2000); 
		commonFuntions.screenShot("WorkItemDetails","Pass","Work Item Details");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("ReviewEmployerTypeTaskDetails","Pass","Review Employer Type Task Details");
		commonFuntions.selectDropdown("Account Status", " Future ");
		commonFuntions.enterFutureDate("Date Covered Employment began? ", 10);
		AddPage.commentField.sendKeys("registration  in progress");
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewWorkItemCompleted","Pass","Review Workitem Completed");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI DOL-DTF Work item..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep();
		//Resolving DOL-DTF Work item................
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		//cf.selectDropdown("Work Item Description", " DOL-DTF Discrepancy task ");
		//cf.enterTextboxContains("FEIN",FEIN);
		//commonFuntions.searchForworkItem(AddPage.searchByFilter);
		sleep(2000);
		commonFuntions.screenShot("DOLDTFDiscrepancytasksearch","Pass","DOL-DTF Discrepancy task search");
		//commonFuntions.clickOnLink("DOL DTF Discrepancy");
		//cf.clickButtonContains("Search");
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
		sleep(5000);

		//Assigning user Review potential Duplicates..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep();
		//Review potential Duplicates...............
		PEOPage.queue.click(); 
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectDropdown("WorkItemDescription", " Review potential Duplicates ");
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("ReviewpotentialDuplicates","Pass","Review potential Duplicates");
		commonFuntions.clickOnLink("Review potential Duplicates");
		sleep(2000);
		commonFuntions.screenShot("WorkItemDetailsReviewPotential","Pass","Work Item Details");sleep(); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("PotentialDuplicateEmployerTask","Pass","Potential Duplicate Employer Task");sleep();
		commonFuntions.selectDropdown("Quarter ", "2");sleep();
		commonFuntions.selectDropdown("Year ", "2023");sleep();
		AddPage.comment.sendKeys("closing");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
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
