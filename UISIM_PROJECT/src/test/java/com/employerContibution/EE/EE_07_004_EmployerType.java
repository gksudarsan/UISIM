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
public class EE_07_004_EmployerType extends TestBase {

	@Test
	public void EE_07_004() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.07.004:Verify employer can submit employer registration for employer type 'Governmental' and legal entity type "
						+ "'School District' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		//commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep();
		commonFuntions.enterTextboxContains("First Name", "dev");
		commonFuntions.enterTextboxContains("Last Name", "Autotest");
		commonFuntions.enterTextboxContains("Job Title", "AutomationEngineer");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("Registration_page2", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Governmental");

		//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd)", 
		//				"FEIN");
		//		String feinValue = databaseResults.get("FEIN");
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		//String feinValue = prop.getProperty("FeinPresentInDolDtf");
		System.out.println("FeinValue is: " + feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "School District");

		//		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd)", 
		//				"EAN");
		//		String ernValue = databaseResults1.get("EAN");
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		//String ernValue = prop.getProperty("ErnPresentInDolDtf");
		System.out.println("ErnValue is: " + ernValue);
		test.log(Status.INFO, "Ern::" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Employer Entity Information---------*/
		commonFuntions.forceClearText(AddPage.legalNameTextBox);
		sleep();
		commonFuntions.enterRandomStringLegalName("Legal Name");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterCurrentDate("What is the date of the first payroll");
		commonFuntions.enterTextboxContains("Estimated or approximate number", "764");
		commonFuntions.enterPastDate("Date covered employment began?", 1825);
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information  (SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		/*--------Add Primary Business Physical Address------*/
		try {
			commonFuntions.clickOnLinkAnchorTag("Edit");
		}catch(Exception e) {			
		}
		commonFuntions.enterTextboxContains("Address Line 1",  commonFuntions.createRandomInteger(10,99 )+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		commonFuntions.screenShot("AddPrimaryBussineesdDetails", "Pass", "Add Primary Business Physical Address");
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
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		/*------Employer Contact Details-------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("te");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("ab");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_noticeOfPotentialCharges.sendKeys("ac");
		AddPage.lastName_noticeOfPotentialCharges.sendKeys("xy");
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details (SREG-004)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Upload Documents(SREG-683)------*/
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterRandomStringLegalName("Submitter Comments may be entered below.");
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementOfAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass",
				"Employer Registration Confirmation:SREG-013");
		commonFuntions.clickButtonContains("Home");

		//Assigning user to WI Review emp type..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		
		//Resolving WI Review emp type................
		PEOPage.queue.click(); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","FeinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("IndividualWorkQueueReviewWorkItem","Pass","Individual Work Queue Review");
		driver.navigate().refresh();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
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

		//Assigning user to WI Determine Liability Task.................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(2000);

		//Resolving WI Determine Liability Task.................
		PEOPage.queue.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		commonFuntions.clickButtonContains("Search");
		commonFuntions.screenShot("Search","Pass","Searchbyname");
		sleep(2000);
		commonFuntions.screenShot("DetermineLiabilityTask","Fail","Determine Liability Task");
		commonFuntions.clickOnLink("Unable to Determine Liability Task");
		sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("Review","Pass","Unable to Determine Liability Task");
		commonFuntions.selectDropdown("Account Status", "Liable");		
		commonFuntions.selectRadio("Contributory");
		commonFuntions.enterTextboxContains("Date covered employment began? ", "1212022");
		commonFuntions.populateListbox("Comment", "testing");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Home");


	}
}
