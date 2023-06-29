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
import com.ui.pages.createRandomString;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_12_002_TPR_UserType extends TestBase{

	@Test
	public void EE_12_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.12.002:Verify TPR can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Business' and work items will be created for CSR to review.");
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
		commonFuntions.enterTextboxContains("First Name", "TestAuto");
		commonFuntions.enterTextboxContains("Last Name", "DJ");
		commonFuntions.enterTextboxContains("Job Title", "TPRTest");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("EmployerRegistrationDetailsPage", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Indian Tribe");
		String feinValue = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The FEIN Value is:"+ feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Business");

		//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea join T_EMPLOYER_DOL_DTF ted ON tea.EAN = ted.ERN WHERE tea.ACCOUNT_STATUS = 'SUSD'", 
		//				"EAN");
		//		String ernValue = databaseResults.get("EAN");

		String ernValue = prop.getProperty("ErnInDolDtfAccoutStatus_SUSD");
		System.out.println("The EAN Value is:"+ ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterRandomStringLegalName("Legal Name");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterPastDate("What is the date of the first payroll", 365);
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly owned by a federally recognized Indian Tribe?", "Yes");
		commonFuntions.enterTextboxContains("Enter the name of the federally recognized Indian Tribe.", "TPRUSER");
		commonFuntions.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Contributory");
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals", "653");
		commonFuntions.enterPastDate("Date covered employment began?", 275);
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*------SREG-008:Add Primary Business Physical Address-----*/

		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+"Cooper Square");
		commonFuntions.enterTextboxContains("City", "New York");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"67");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.screenShot("AddPrimaryBusinessPhysicalAddress", "Pass", "Add Primary Business Physical Address:SREG-008");
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
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		AddPage.addressLine1_Form1.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Cooper Sqaure");
		AddPage.city_Form1.sendKeys("NY");
		AddPage.zipCode_Form1.sendKeys("37634");
		AddPage.countyDropDown_Form1.click();sleep();
		AddPage.countyValue1.click();sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("john");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("Tery");sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		AddPage.addressLine1_Form3.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Cooper Sqaure");
		AddPage.city_Form3.sendKeys("NY");
		AddPage.zipCode_Form3.sendKeys("76545");
		AddPage.countyDropDown_Form3.click();sleep();
		AddPage.countyValue3.click();sleep();
		AddPage.firstName_noticeOfPotentialCharges.sendKeys("Hens");
		AddPage.lastName_noticeOfPotentialCharges.sendKeys("Micheal");
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details:SREG-004");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress1);
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress2);
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress3);
			commonFuntions.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Employer Contact Details Address pop up");
		}
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*---Employer Verify Contact Details:SREG-521---*/
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
		commonFuntions.enterRandomStringLegalName("Submitter Comments may be entered below.");
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
		sleep(2000);
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
		//sleep(2000);
		//commonFuntions.screenShot("DOL/DTFDiscrepancytask","Pass","DOL-DTF Discrepancy task");
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

		//Assigning user to WI review comments type..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)"); 
		//Resolving WI review comments type...............
		PEOPage.queue.click(); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("Review comments emp type","Pass","emp type");
		commonFuntions.clickOnLink("Review Comments");
		sleep(2000); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("ReviewComments","Pass","Review Comments");
		AddPage.commentField.sendKeys("review comments");
		commonFuntions.clickButtonContains("Submit"); 
		sleep(3000);
		commonFuntions.screenShot("ReviewCommentsWorkItemCompleted","Pass","Review Comments Workitem Completed");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI review profile data type..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)"); 

		//Resolving WI review profile data type...............
		PEOPage.queue.click(); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("Reviewprofile","Pass","emp type");
		commonFuntions.clickOnLink("Review Profile Data");
		sleep(2000); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("ReviewProfileDataType","Pass","Review Profile Data Type");
		sleep();
		commonFuntions.forceClearText(PEOPage.clearErnFieldSection);sleep();
		commonFuntions.enterFutureDate("Liability date – Qtr. Year", 10);sleep();
		AddPage.comments.sendKeys("closing");sleep();
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.screenShot("ReviewCommentsWorkItemCompleted","Pass","Review Comments Workitem Completed");
		commonFuntions.clickButtonContains("Home");
	}
}
