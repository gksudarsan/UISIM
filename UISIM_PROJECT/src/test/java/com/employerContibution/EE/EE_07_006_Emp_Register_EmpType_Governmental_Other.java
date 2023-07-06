package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_07_006_Emp_Register_EmpType_Governmental_Other extends TestBase {

	@Test
	public void EE_07_006() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report.createTest(" EE_07_006 Verify employer can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");
		
		//commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		sleep(3000);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(3000);
		
		/*Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "FEIN");
		String FEIN = databaseResults.get("FEIN");
		System.out.println("FEIN NUMBER = " +FEIN); 


		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) AND EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "EAN");
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN NUMBER = " +EAN); */
		
		//--- Menu Click ---
		        commonFuntions.screenShot("Menu", "Pass", "Menu page");
		        commonFuntions.clickMenu("menu");
				sleep();
				commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
				sleep();
				commonFuntions.screenShot("Menu", "Pass", "Menu - Employer Registration - Register Employer");
				sleep();
				commonFuntions.clickMenu("Register Employer");
				sleep(2000);
		
				//-----------SREG-001 ---
				commonFuntions.screenShot("EmployerRegistraionPage", "Pass", "Launched at Employer Registration(SREG-001) page");
				sleep(2000);
				commonFuntions.enterTextboxContains("First Name", "AutoTest");
				commonFuntions.enterTextboxContains("Last Name", "AutoSanjay");
				commonFuntions.enterTextboxContains("Job Title", "AutomationEngineer");
				commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
				commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
				sleep(2000);
				commonFuntions.screenShot("Registration_page2", "Pass", "Employer Registration:SREG-001");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
		
				//---------------SREG-025--------------
				
				commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)","010042354"); //010042354
				sleep(2000);
				commonFuntions.selectDropdown("Employer Type", " Governmental ");
				sleep(2000);
				commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
				sleep(2000);
				commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "Test");
				sleep(2000);
				//commonFuntions.enterTextboxContains("Employer Registration Number","4607757");
				//sleep(2000);
				commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
				sleep(2000);
				commonFuntions.selectDropdown("Source Type", " NYS-100G ");
				sleep(2000);
				commonFuntions.screenShot("General Information", "Pass", "entered the details SREG-025 page ");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				//---------------SREG-003--------------
				//---------------Legal Name--------------
				//SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT  IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
				String legalName="Automation Employed Testing  " + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
				sleep(2000);
				empPage.legalNameTextBox.sendKeys(legalName);
				sleep(2000);
				commonFuntions.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(100,999)));
				sleep(2000);
				commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax", 180);
				sleep(2000);
				commonFuntions.enterTextboxContains("Estimated or approximate number of individuals working in covered employment", "56");
				sleep(2000);
				commonFuntions.enterPastDate("Date covered employment began?", 270);
				sleep(2000);
				commonFuntions.selectRadioQuestions("Is your entity a legally established component or subdivision of another entity", "No ");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance ", "Contributory");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				//-----------------SREG-008----------------
				try {
				commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entering the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("Address Line 1 ", "123HEC Zone");
				sleep(2000);
				commonFuntions.enterTextboxContains("City ", "NY");
				sleep(2000);
				commonFuntions.enterTextboxContains("Zip Code", "10002");
				sleep(2000);
				commonFuntions.selectDropdown("County", " Albany ");
				sleep(2000);
				commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entered the details");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				}
				catch(Exception e) {}
				
				//-----------------SREG-007----------------
				
				sleep(2000);
				commonFuntions.screenShot("EmpRegister6", "Pass", "Navigated to SREG-007 page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				//-----------------SREG-004----------------
				commonFuntions.screenShot("Employer Contact Details", "Pass", "Navigated to SREG-004 page");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
				sleep();
				commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
				sleep();
				AddPage.firstName_locationOfBooksAndrecords.clear();
				AddPage.firstName_locationOfBooksAndrecords.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Jhon");
				AddPage.lastName_noticeOfPotentialCharges.clear();
				AddPage.lastName_locationOfBooksAndrecords.sendKeys(commonFuntions.createRandomInteger(10, 99)+"AutoTets");
				commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
				sleep();
				AddPage.firstName_noticeOfPotentialCharges.clear();
				AddPage.firstName_noticeOfPotentialCharges.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Jhiny");
				AddPage.lastName_noticeOfPotentialCharges.clear();
				AddPage.lastName_noticeOfPotentialCharges.sendKeys(commonFuntions.createRandomInteger(10, 99)+"TestAuto");
				sleep();
				commonFuntions.screenShot("Employer Contact Details", "Pass", "entered details SREG-004 page");
				sleep();
				commonFuntions.clickButtonContains("Continue");
				
				
				//-------------SREG 521
				sleep(2000);
				commonFuntions.screenShot("Employer Contact Details", "Pass", "entered details SREG 521 page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				
				//-----------------SREG-683----------------
				sleep(3000);
				commonFuntions.screenShot("Upload Documents", "Pass", "Navigated to SREG-683 page and uploading the document");
				sleep(4000);
				commonFuntions.selectLink("Supporting documents like 501(c)(3) Exemptions, Lessor ", "Browse");
				sleep(2000);
				commonFuntions.uploadDoc("Sample.docx");
				Thread.sleep(4000);
				commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
				sleep(2000);
				commonFuntions.clickButtonContains("Continue");
				
				//-----------------SREG-800----------------
				sleep(3000);
				commonFuntions.screenShot("Review Registration Details", "Pass", "Navigated to SREG-800 page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				//-----------------SREG-043----------------
				sleep(3000);
				commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "Navigated to SREG-043 page");
				sleep(3000);
				//empPage.commentid.sendKeys(" Okay Agree ");
				sleep(2000);
				commonFuntions.selectCheckbox("I accept");
				sleep(2000);
				commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement:SREG-043)");
				sleep(2000);
				commonFuntions.clickButtonContains("Submit");
				sleep(8000);
				//-----------------SREG-013----------------
				commonFuntions.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation:SREG-013)");
				sleep(2000);
				commonFuntions.clickButtonContains("Home ");
				sleep(3000);
				
				//----FEIN used 010042354
				
				//-----------------Logout & login ----------------
				
				commonFuntions.waitForLoadingIconToDisappear();
				commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
				sleep(3000);
				commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
				
				
				//-------------- Work Item 1  --------------
				sleep(3000);
				commonFuntions.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				commonFuntions.waitForLoadingIconToDisappear();
				
				//--------------WF-001   --------------
				sleep(3000);
				commonFuntions.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFuntions.clearTextboxContains("Employer Registration Number");
				commonFuntions.enterTextboxContains("FEIN","010042354");
				sleep(3000);
			    commonFuntions.screenShot("FEINSearch","Pass","FEIN Search for Task");
			    sleep(3000);
			    commonFuntions.clickButtonContains(" Search ");
			    
			    sleep(3000);
			    commonFuntions.ScrollMenu("Review Employer Type");
			    sleep(3000);
			    commonFuntions.screenShot("WIClick","Pass","Clicked on Work Item - 'Review Employer Type'");
			    sleep(3000);
			    commonFuntions.clickOnLink("Review Employer Type");
			    
			    // --- WF-091 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFuntions.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFuntions.clickButtonContains("Open Work Item ");
			    
			 // --- EEWI-002---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "Successful launch to EEWI-002) page");
			    sleep(2000);
			    commonFuntions.selectDropdown("Account Status", " Future ");
			    sleep(2000);
				commonFuntions.enterPastDate("Date Covered Employment began? ", 170);
				 sleep(2000);
			    commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
			    sleep(2000);
			    empPage.commentId.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFuntions.screenShot("Review Employer Type Task Details","Pass","Details entered in to EEWI-002) page");
			    sleep(2000);
			    commonFuntions.clickButtonContains("Submit ");
			   
			 // --- SUC-002 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
			    sleep();
				commonFuntions.clickButtonContains("Home ");
				sleep();
				commonFuntions.waitForLoadingIconToDisappear();
				
				//-------------- Work Item 2  --------------*/
				sleep(3000);
				commonFuntions.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				commonFuntions.waitForLoadingIconToDisappear();
				
				//--------------WF-001   --------------*/
				sleep(3000);
				commonFuntions.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFuntions.clearTextboxContains("Employer Registration Number");
				commonFuntions.enterTextboxContains("Work Item Description Free Text","DOL");
				sleep(3000);
			    commonFuntions.screenShot("Work Item Description Free Text","Pass","Search for Task");
			    sleep(3000);
			    commonFuntions.clickButtonContains(" Search ");
			    sleep(3000);
			    commonFuntions.ScrollMenu("DOL DTF Discrepancy");
			    sleep(3000);
			    commonFuntions.screenShot("WIClick","Pass","Clicked on Work Item - DOL DTF Discrepancy");
			    sleep(3000);
			    commonFuntions.clickOnLink("DOL DTF Discrepancy");
				
			   
			 // --- WF-091 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFuntions.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFuntions.clickButtonContains("Open Work Item ");
			    
			 // --- EEWI-005---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("DOL/DTF Discrepency Task", "Pass", "Successful launch to EEWI-005 page");
			    sleep(2000);
			    commonFuntions.selectDropdown("Account Status", " Liable ");
			    sleep(2000);
			    commonFuntions.enterTextbox("Date covered employment began? ", "09/05/2023");
			    
				//commonFuntions.enterPastDate("Date covered employment began? ", 270);
				 sleep(2000);
			    commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
			    sleep(2000);
			    empPage.comment.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFuntions.screenShot("Dol DTF e Task Details","Pass","Details entered in to EEWI-005 page");
			    sleep(2000);
			    commonFuntions.clickButtonContains("Submit ");
			 // --- SUC-002 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
			    sleep();
				commonFuntions.clickButtonContains("Home ");
				sleep();
				commonFuntions.waitForLoadingIconToDisappear();
			    
			    //-------------- Work Item 3  --------------*/
				sleep(3000);
				commonFuntions.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				commonFuntions.waitForLoadingIconToDisappear();
				
				//--------------WF-001   --------------*/
				sleep(3000);
				commonFuntions.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFuntions.clearTextboxContains("Employer Registration Number");
				commonFuntions.enterTextboxContains("FEIN","010042354");
				sleep(3000);
			    commonFuntions.screenShot("FEINSearch","Pass","FEIN Search for Task");
			    sleep(3000);
			    commonFuntions.clickButtonContains(" Search ");
			    
			    sleep(3000);
			    commonFuntions.ScrollMenu("Review Comments");
			    sleep(3000);
			    commonFuntions.screenShot("WIClick","Pass","Clicked on Work Item - Review Comments");
			    sleep(3000);
			    commonFuntions.clickOnLink("Review Comments");
			    
			    // --- WF-091 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFuntions.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFuntions.clickButtonContains("Open Work Item ");
			    
			 // --- EEWI-002---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "Successful launch to EEWI-002) page");
			    sleep(2000);
			    commonFuntions.selectDropdown("Account Status", " Future ");
			    sleep(2000);
				//commonFuntions.enterPastDate("Date Covered Employment began? ", 170);
			    commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
			    sleep(2000);
			    empPage.commentId.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFuntions.screenShot("Review Employer Type Task Details","Pass","Details entered in to EEWI-002) page");
			    sleep(2000);
			    commonFuntions.clickButtonContains("Submit ");
			   
			 // --- SUC-002 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
			    sleep();
				commonFuntions.clickButtonContains("Home ");
				sleep();
				commonFuntions.waitForLoadingIconToDisappear();
				
				
				//--------Menu----
				
				commonFuntions.clickMenu("menu");
				commonFuntions.ScrollMenu("Inquiry");
				commonFuntions.clickMenu("Inquiry");
				commonFuntions.ScrollMenu("Inquiry");
				commonFuntions.clickMenu("Contribution Inquiry");
				sleep(2000);
				commonFuntions.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
				commonFuntions.ScrollMenu("Inquiry");
				commonFuntions.clickMenu("Inquiry Employer Account");
				
				//----------SREG 050
				sleep(2000);
				commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully landed on SREG 050 page");
				commonFuntions.enterTextboxContains(" FEIN ", "010042354");  //
				//commonFuntions.enterTextboxContains("Employer Registration Number", "6070139");
				sleep(2000);
				commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully entered deatils and  click on continue");
				commonFuntions.clickButton("Continue ");
				sleep(2000);
				
				//----------EM 052
				commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Successfully landed on SREG 052 page");
				sleep(2000);
				commonFuntions.screenShot("TC:EE_07_006", "Pass", "Successfully Completed TC");
	}
}