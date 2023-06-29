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
public class EE_07_003_EmployerType extends TestBase {

	@Test
	public void EE_07_003() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.07.003:Verify employer can submit employer registration for employer "
						+ "type 'Governmental' and legal entity type 'Village' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		//commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep();
		commonFuntions.enterTextboxContains("First Name", "Mithchel");
		commonFuntions.enterTextboxContains("Last Name", "Strak");
		commonFuntions.enterTextboxContains("Job Title", "Sdet");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Governmental");
		sleep();
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The fein  value is ::" +feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Village");
		//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "EAN");
		//		String ernValue = databaseResults.get("EAN");
		String ernValue = prop.getProperty("ErnPresentInDolDtf");
		System.out.println("ErnValue is: " + ernValue);
		test.log(Status.INFO, "Ern::" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterRandomStringLegalName("Legal Name");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterPastDate("What is the date of the first payroll", 180);
		commonFuntions.enterTextboxContains("Estimated or approximate number", "500");
		commonFuntions.enterPastDate("Date covered employment began?", 90);
		commonFuntions.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Reimbursable");
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:(SREG-003)");
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
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		/*-----Employer Contact Details------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("test");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("auto");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_noticeOfPotentialCharges.sendKeys("test2");
		AddPage.lastName_noticeOfPotentialCharges.sendKeys("autro");
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
		sleep(5000);
		//Assigning user to WI Review emp type..................
		loginPage.okPopUpButton.click();
		sleep(2000);
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

		//Assigning user to WI DOL-DTF work item..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		//Resolving DOL-DTF work item................
		PEOPage.queue.click(); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectDropdown("Work Item Description", " DOL-DTF Discrepancy task ");
		sleep();
		//		commonFuntions.enterTextboxContains("FEIN",feinValue);
		//		commonFuntions.searchForworkItem(AddPage.searchByFilter);
		//		commonFuntions.screenShot("DOLDTFDiscrepancytasksearch","Pass","DOL-DTF Discrepancy task search");
		//		commonFuntions.clickOnLink("DOL DTF Discrepancy");
		//		commonFuntions.clickButtonContains("Search");
		sleep();
		commonFuntions.screenShot("DOL/DTFDiscrepancytask","Pass","DOL-DTF Discrepancy task");
		sleep(); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("DOL/DTFDiscrepancytaskPage","Pass","DOL/DTF Discrepancy task Page");
		sleep();
		commonFuntions.forceClearText(PEOPage.clearErnFieldSection);
		commonFuntions.enterRandomStringLegalName("Legal Name of business");
		commonFuntions.selectRadioQuestions("Financing Method", "Reimbursable");
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep();
		AddPage.comment.sendKeys("registration  in progress");
		sleep();
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("DolDtfworkitemCompleted","Pass","DolDtf work item completed");
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
		commonFuntions.enterTextboxContains("Employer Registration Number", "8131412");
		sleep();
		AddPage.commentField.sendKeys("co");
		sleep();
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
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
		commonFuntions.forceClearText(PEOPage.clearErnFieldSection);
		AddPage.commentField.sendKeys("closing");
		sleep();
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.screenShot("ReviewCommentsWorkItemCompleted","Pass","Review Comments Workitem Completed");
		commonFuntions.clickButtonContains("Home");
	}
}
