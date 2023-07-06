// --------------------------Palak--------------------------

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

public class EE_03_006 extends TestBase {
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void EE_03_006() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		
		test = report.createTest(
				"EE.03.006 Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");

		
		//-------loGIN------
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		commonFuntions.screenShot("ApplicationLogonPage", "Pass", "Application login successfully");
		
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
				//------- SREG-001 ---
				commonFuntions.screenShot("EmployerRegistraionPage", "Pass", "Launched at Employer Registration(SREG-001) page");
				sleep();
				commonFuntions.enterTextboxContains("First Name", "AutoTest");
				commonFuntions.enterTextboxContains("Last Name", "AutoSanjay");
				commonFuntions.enterTextboxContains("Job Title", "AutomationEngineer");
				commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
				commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
				commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001");
				commonFuntions.clickButton("Continue ");
				/*---------------SREG-025--------------*/
				//--------------FEIN
				//String fein=StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
				//System.out.println(fein);
				commonFuntions.screenShot("General Information", "Pass", "Navigated to SREG-025 page and enter the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)","010042354");
				sleep(2000);
				commonFuntions.selectDropdown("Employer Type", "Governmental");
				sleep(2000);
				commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
				commonFuntions.enterTextboxContains("Employer Registration Number","6070074");
				sleep(2000);
				commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "Testing");
				sleep(2000);
				commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
				sleep(2000);
				commonFuntions.selectDropdown("Source Type", " NYS-100G ");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
				sleep(2000);
				commonFuntions.screenShot("General Information", "Pass", "entered the details SREG-025 page ");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				sleep(5000);
				/*---------------SREG-003--------------*/
				String legalName="HE JESUIT BUREAU OF THE PROVINCE OF BUFFALO";
				//+ StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
				/*---------------Legal Name--------------*/
				empPage.legalNameTextBox.sendKeys(legalName);
				commonFuntions.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(100,999)));
				sleep(2000);
				commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax", 90);
				sleep(2000);
				commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly", "No ");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
				sleep(2000);
				commonFuntions.enterPastDate("Date covered employment began?", 180);
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				sleep(5000);
				/*-----------------SREG-008----------------*/
				commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entering the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("Address Line 1 ",StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9)+ " Fake Address" );
				sleep(2000);
				commonFuntions.enterTextboxContains("City ", "NewYork");
				sleep(2000);
				commonFuntions.enterTextboxContains("Zip Code", "75002");
				sleep(2000);
				commonFuntions.selectDropdown("County", " Albany ");
				sleep(2000);
				commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entered the details");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				sleep(5000);
				try {
					//PEOPage.uspsAdd.click();
					commonFuntions.safeJavaScriptClick(PEOPage.uspsAdd);
					//AddPage.continueButton_popUp.click();
					commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
				}catch (Exception e) {
					System.out.println("The popup is displyed");
				}
				
				/*-----------------SREG-007----------------*/
				
				commonFuntions.waitForLoadingIconToDisappear();
				commonFuntions.screenShot("Business Physical Address details ", "Pass", "Navigated to SREG-007 page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				/*-----------------SREG-004----------------*/
				
				sleep(3000);
				commonFuntions.screenShot("Emp contact details", "Pass", "Navigated to SREG-004 page and entering the details");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
				sleep(2000);
				empPage.addressLine1_Form1.sendKeys("Goddard Hall 80");
				empPage.city_Form1.sendKeys("New York");
				empPage.zipCode_Form1.sendKeys("80003");
				empPage.countyDropDown_Form1.click();
				empPage.countyValue_Form1.click();
				commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
				commonFuntions.screenShot("Emp contact details", "Pass", "Selected other for - Location of Books and Records");
				sleep();
				AddPage.firstName_locationOfBooksAndrecords.clear();
				AddPage.firstName_locationOfBooksAndrecords.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Jhon");
				AddPage.lastName_noticeOfPotentialCharges.clear();
				AddPage.lastName_locationOfBooksAndrecords.sendKeys(commonFuntions.createRandomInteger(10, 99)+"AutoTets");
				sleep();
				commonFuntions.screenShot("Employer Contact Details", "Pass", "entered details SREG-004 page");
				sleep();
				commonFuntions.clickButtonContains("Continue");
				sleep(3000);
				try {
					commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton1);
					sleep();
				}catch(Exception e) {
					System.out.println("bmad address selected");
				}
				try {
					commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton2);
					sleep();
				}catch(Exception e) {
					System.out.println("ibra address selected");
				}

				try {
					commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton3);
					sleep();
				}catch(Exception e) {
					System.out.println("npca address selected");
				}
				//AddPage.continueButton_popUp.click();
				
				
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
				sleep(5000);
				commonFuntions.screenShot("Review Registration Details", "Pass", "Navigated to SREG-800 page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				//-----------------SREG-043----------------
				sleep(3000);
				commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "Navigated to SREG-043 page");
				sleep(3000);
				//empPage.commentid.sendKeys(" Okay Agree ");
				//sleep(2000);
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
				
				//-------------- Work Item 1  --------------
				sleep(3000);
				commonFuntions.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				
				//--------------WF-001   --------------
				sleep(3000);
				commonFuntions.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFuntions.clearTextboxContains("Employer Registration Number");
				commonFuntions.enterTextboxContains("Work Item Description Free Text","review");
				//commonFuntions.enterTextboxContains("FEIN",fein);
				sleep(3000);
			    //commonFuntions.screenShot("FEINSearch","Pass","FEIN Search for Task");
			    sleep(3000);
			    commonFuntions.clickButtonContains(" Search ");
			    
			    sleep(3000);
			    commonFuntions.ScrollMenu("Review Employer Type");
			    sleep(3000);
			    commonFuntions.screenShot("WIClick","Pass","Clicked on Work Item - 'Review Employer Type'");
			    sleep(3000);
			    commonFuntions.clickOnLink("Review Employer Type");
			    
			    //----- --- WF-091 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFuntions.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFuntions.clickButtonContains("Open Work Item ");
			    
			 // --------EEWI-002---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "Successful launch to EEWI-002) page");
			    sleep(2000);
			    commonFuntions.selectDropdown("Account Status", " Future ");
			    sleep(2000);
				commonFuntions.enterPastDate("Date Covered Employment began? ", 180);
				sleep(2000);
			    commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
			    sleep(2000);
			    empPage.commentId.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFuntions.screenShot("Review Employer Type Task Details","Pass","Details entered in to EEWI-002) page");
			    sleep(2000);
			    commonFuntions.clickButtonContains("Submit ");
			 // --------- SUC-002 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
			    sleep();
				commonFuntions.clickButtonContains("Home ");
				sleep();
				commonFuntions.waitForLoadingIconToDisappear();
				
				/*-------------- Work Item 2  --------------*/
				sleep(3000);
				commonFuntions.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				commonFuntions.waitForLoadingIconToDisappear();
				
				/*--------------WF-001   --------------*/
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
				
				
				/*-------------- Work Item 3  --------------*/
				sleep(3000);
				commonFuntions.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				//commonFuntions.waitForLoadingIconToDisappear();
				
				/*--------------WF-001   --------------*/
				sleep(3000);
				commonFuntions.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFuntions.clearTextboxContains("Employer Registration Number");
				commonFuntions.enterTextboxContains("FEIN","161719556");
				sleep(3000);
			    commonFuntions.screenShot("FEINSearch","Pass","FEIN Search for Task");
			    sleep(3000);
			    commonFuntions.clickButtonContains(" Search ");
			    
			    sleep(3000);
			    commonFuntions.ScrollMenu("Review Profile Data");
			    sleep(3000);
			    commonFuntions.screenShot("WIClick","Pass","Clicked on Work Item - Review Profile Data");
			    sleep(3000);
			    commonFuntions.clickOnLink("Review Profile Data");
			    
			    // --- WF-091 ---
			    commonFuntions.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFuntions.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFuntions.clickButtonContains("Open Work Item ");
			    
			 // --- EEWI-014---
			    commonFuntions.waitForLoadingIconToDisappear();
			    commonFuntions.screenShot("Review Profile Data Task", "Pass", "Successful launch to EEWI-014 page");
			    sleep(2000);
				//commonFuntions.enterPastDate("Date Covered Employment began? ", 180);
				// sleep(2000);
				 commonFuntions.enterPastDate("Liability date – Qtr. Year", 60);
				 sleep(2000);
			    commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
			    sleep(2000);
			    commonFuntions.selectDropdown("Account Status", " Future ");
			    sleep(2000);
			    empPage.commentid.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFuntions.screenShot("Review Profile Data Task","Pass","Details entered in to EEWI-014 page");
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
				commonFuntions.enterTextboxContains(" FEIN ", "161719556");  //
				//commonFuntions.enterTextboxContains("Employer Registration Number", "6070139");
				sleep(2000);
				commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully entered deatils and  click on continue");
				commonFuntions.clickButton("Continue ");
				sleep(2000);
				
				//----------SREG 051
				commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "Successfully landed on SREG 051 page");
				sleep(2000);
				commonFuntions.screenShot("TC:EE_03_006", "Pass", "Successfully Completed	");
				
	}
}
