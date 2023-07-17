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
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		
		test = report.createTest(
				"EE.03.006 Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");

		
		//-------loGIN------
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLogonPage", "Pass", "Application login successfully");
		
		//--- Menu Click ---
				commonFunction.screenShot("Menu", "Pass", "Menu page");
				commonFunction.clickMenu("menu");
				sleep();
				commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
				sleep();
				commonFunction.screenShot("Menu", "Pass", "Menu - Employer Registration - Register Employer");
				sleep();
				commonFunction.clickMenu("Register Employer");
				sleep(2000);
				//------- SREG-001 ---
				commonFunction.screenShot("EmployerRegistraionPage", "Pass", "Launched at Employer Registration(SREG-001) page");
				sleep();
				/*
				commonFunction.enterTextboxContains("First Name", "AutoTest");
				commonFunction.enterTextboxContains("Last Name", "AutoSanjay");
				commonFunction.enterTextboxContains("Job Title", "AutomationEngineer");
				commonFunction.enterTextboxContains("Contact Telephone Number",Long.toString(commonFunction.createRandomInteger(10000000,99999999))+Long.toString(commonFunction.createRandomInteger(10,99)));
				commonFunction.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFunction.createRandomInteger(10000,99999))+"@gmail.com");
				*/
				commonFunction.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001");
				commonFunction.clickButton("Continue ");
				/*---------------SREG-025--------------*/
				//--------------FEIN
				String fein="161719556";
						//StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
				System.out.println(fein);
				commonFunction.screenShot("General Information", "Pass", "Navigated to SREG-025 page and enter the details");
				sleep(2000);
				commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)","fein");
				sleep(2000);
				commonFunction.selectDropdown("Employer Type", "Governmental");
				sleep(2000);
				commonFunction.selectDropdown("Type of Legal Entity", " Other ");
				sleep(2000);
				//commonFunction.enterTextboxContains("Employer Registration Number","6070074");
				
				commonFunction.enterTextboxContains("If Other, provide the type of Legal Entity.", "Testing");
				sleep(2000);
				commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
				sleep(2000);
				commonFunction.selectDropdown("Source Type", " NYS-100G ");
				sleep(2000);
				commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
				sleep(2000);
				commonFunction.screenShot("General Information", "Pass", "entered the details SREG-025 page ");
				sleep(2000);
				commonFunction.clickButton("Continue ");
				sleep(5000);
				/*---------------SREG-003--------------*/
				String legalName="AGILID INC";
				//+ StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
				/*---------------Legal Name--------------*/
				empPage.legalNameTextBox.sendKeys(legalName);
				commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(1000000,9999999))+Long.toString(commonFunction.createRandomInteger(100,999)));
				sleep(2000);
				commonFunction.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax", 90);
				sleep(2000);
				commonFunction.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly", "No ");
				sleep(2000);
				commonFunction.selectRadioQuestions("Financing Method", "Contributory");
				sleep(2000);
				commonFunction.enterPastDate("Date covered employment began?", 180);
				sleep(2000);
				commonFunction.clickButton("Continue ");
				sleep(5000);
				/*-----------------SREG-008----------------*/
				commonFunction.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entering the details");
				sleep(2000);
				commonFunction.enterTextboxContains("Address Line 1 ","36 East 8th Street" );
				sleep(2000);
				commonFunction.enterTextboxContains("City ", "NewYork");
				sleep(2000);
				commonFunction.enterTextboxContains("Zip Code", "10003");
				sleep(2000);
				commonFunction.selectDropdown("County", " Albany ");
				sleep(2000);
				commonFunction.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entered the details");
				sleep(2000);
				commonFunction.clickButton("Continue ");
				sleep(5000);
				try {
					empPage.uspsBusinessAddress.click();
				} catch (Exception exception) {
					empPage.uspsBusinessAddressInnerCircle.click();
				}
				commonFunction.screenShot("PoP UP", "Pass", "USPS Business address selection on SREG-008");
				empPage.continueButton_popUp.click();
				
				/*-----------------SREG-007----------------*/
				
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("Business Physical Address details ", "Pass", "Navigated to SREG-007 page");
				sleep(2000);
				commonFunction.clickButton("Continue ");
				
				/*-----------------SREG-004----------------*/
				
				sleep(3000);
				commonFunction.screenShot("Emp contact details", "Pass", "Navigated to SREG-004 page and entering the details");
				sleep(2000);
				commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
				sleep(2000);
				empPage.addressLine1_Form1.sendKeys("70 Washington Square South");
				empPage.city_Form1.sendKeys("New York");
				empPage.zipCode_Form1.sendKeys("10012");
				empPage.countyDropDown_Form1.click();
				empPage.countyValue_Form1.click();
				sleep(2000);
				commonFunction.screenShot("Emp contact details", "Pass", "Successfully entered details in SREG-004 page");
				commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
				commonFunction.screenShot("Emp contact details", "Pass", "Selected other for - Location of Books and Records");
				empPage.uspsLbraAddressText.sendKeys("29 Washington Pl,");
				empPage.uspsLbraCityText.sendKeys("New York");
				empPage.uspsLbraZipText.sendKeys("10003");
				empPage.uspsLbraCounty.click();
				commonFunction.selectFromDropdown(" Albany ");
				sleep();
				empPage.uspsLbraFirstNameText.sendKeys("Brown");
				empPage.uspsLbraLastNameText.sendKeys("Bldg");
				sleep(2000);
				commonFunction.screenShot("Emp contact details", "Pass", "Successfully entered details in SREG-004 page");
				sleep(3000);
				commonFunction.clickButton("Continue ");
				
				sleep();
				try {
					empPage.uspsBmadAddressRadio.click();
				} catch(Exception exception) {}
				
				try {
					empPage.uspsLbraAddressRadio.click();
				} catch(Exception exception) {}
				//AddPage.continueButton_popUp.click();
				sleep(2000);
				commonFunction.screenShot("EE01007", "Pass", "Click on appropriate USPS radio on SREG-004 page");
				try {
					empPage.continueButton_popUp.click();
				} catch(Exception exception) {}
				
				
				//-------------SREG 521
				sleep(2000);
				commonFunction.screenShot("Employer Contact Details", "Pass", "entered details SREG 521 page");
				sleep(2000);
				commonFunction.clickButton("Continue ");
				
				
				//-----------------SREG-683----------------
				sleep(3000);
				commonFunction.screenShot("Upload Documents", "Pass", "Navigated to SREG-683 page and uploading the document");
				sleep(4000);
				commonFunction.selectLink("Supporting documents like 501(c)(3) Exemptions, Lessor ", "Browse");
				sleep(2000);
				commonFunction.uploadDoc("Sample.docx");
				Thread.sleep(4000);
				commonFunction.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
				sleep(2000);
				commonFunction.clickButtonContains("Continue");
				
				//-----------------SREG-800----------------
				sleep(5000);
				commonFunction.screenShot("Review Registration Details", "Pass", "Navigated to SREG-800 page");
				sleep(2000);
				commonFunction.clickButton("Continue ");
				//-----------------SREG-043----------------
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("Statement of Acknowledgement", "Pass", "Navigated to SREG-043 page");
				sleep(3000);
				commonFunction.selectCheckbox("I accept");
				sleep(2000);
				commonFunction.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement:SREG-043)");
				sleep(2000);
				commonFunction.clickButtonContains("Submit");
				commonFunction.waitForLoadingIconToDisappear();
				//-----------------SREG-013----------------
				commonFunction.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation:SREG-013)");
				sleep(2000);
				commonFunction.clickButtonContains("Home ");
				sleep(3000);
				
				//-------------- Work Item 1  --------------
				sleep(3000);
				commonFunction.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				
				//--------------WF-001   --------------
				sleep(3000);
				commonFunction.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFunction.clearTextboxContains("Employer Registration Number");
				commonFunction.enterTextboxContains("Work Item Description Free Text","review");
				//commonFunction.enterTextboxContains("FEIN",fein);
				sleep(3000);
			    //commonFunction.screenShot("FEINSearch","Pass","FEIN Search for Task");
			    sleep(3000);
			    commonFunction.clickButtonContains(" Search ");
			    
			    sleep(3000);
			    commonFunction.ScrollMenu("Review Employer Type");
			    sleep(3000);
			    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Review Employer Type'");
			    sleep(3000);
			    commonFunction.clickOnLink("Review Employer Type");
			    
			    //----- --- WF-091 ---
			    commonFunction.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFunction.clickButtonContains("Open Work Item ");
			    
			 // --------EEWI-002---
			    commonFunction.waitForLoadingIconToDisappear();
			    commonFunction.screenShot("Review Employer Type Task Details", "Pass", "Successful launch to EEWI-002) page");
			    sleep(2000);
			    commonFunction.selectDropdown("Account Status", " Future ");
			    sleep(2000);
			//	commonFunction.enterPastDate("Date Covered Employment began? ", 180);
			//	sleep(2000);
			 //   commonFunction.selectRadioQuestions("Financing Method", "Contributory");
			  //  sleep(2000);
			    empPage.commentId.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFunction.screenShot("Review Employer Type Task Details","Pass","Details entered in to EEWI-002) page");
			    sleep(2000);
			    commonFunction.clickButtonContains("Submit ");
			 // --------- SUC-002 ---
			    commonFunction.waitForLoadingIconToDisappear();
			    commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
			    sleep();
				commonFunction.clickButtonContains("Home ");
				sleep();
				commonFunction.waitForLoadingIconToDisappear();
				
				/*-------------- Work Item 2  --------------*/
				sleep(3000);
				commonFunction.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				commonFunction.waitForLoadingIconToDisappear();
				
				/*--------------WF-001   --------------*/
				sleep(3000);
				commonFunction.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFunction.clearTextboxContains("Employer Registration Number");
				commonFunction.enterTextboxContains("Work Item Description Free Text","DOL");
				sleep(3000);
			    commonFunction.screenShot("Work Item Description Free Text","Pass","Search for Task");
			    sleep(3000);
			    commonFunction.clickButtonContains(" Search ");
			    sleep(3000);
			    commonFunction.ScrollMenu("DOL DTF Discrepancy");
			    sleep(3000);
			    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - DOL DTF Discrepancy");
			    sleep(3000);
			    commonFunction.clickOnLink("DOL DTF Discrepancy");
				
			   
			 // --- WF-091 ---
			    commonFunction.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFunction.clickButtonContains("Open Work Item ");
			    
			 // --- EEWI-005---
			    commonFunction.waitForLoadingIconToDisappear();
			    commonFunction.screenShot("DOL/DTF Discrepency Task", "Pass", "Successful launch to EEWI-005 page");
			    sleep(2000);
			    commonFunction.selectDropdown("Account Status", " Liable ");
			    sleep(2000);
			    commonFunction.enterTextbox("Date covered employment began? ", "09/05/2023");
			    
				//commonFunction.enterPastDate("Date covered employment began? ", 270);
				 sleep(2000);
			    commonFunction.selectRadioQuestions("Financing Method", "Contributory");
			    sleep(2000);
			    empPage.comment.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFunction.screenShot("Dol DTF e Task Details","Pass","Details entered in to EEWI-005 page");
			    sleep(2000);
			    commonFunction.clickButtonContains("Submit ");
			    
			 // --- SUC-002 ---
			    commonFunction.waitForLoadingIconToDisappear();
			    commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
			    sleep();
				commonFunction.clickButtonContains("Home ");
				sleep();
				commonFunction.waitForLoadingIconToDisappear();
				
				
				/*-------------- Work Item 3  --------------*/
				sleep(3000);
				commonFunction.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				//commonFunction.waitForLoadingIconToDisappear();
				
				/*--------------WF-001   --------------*/
				sleep(3000);
				commonFunction.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFunction.clearTextboxContains("Employer Registration Number");
				commonFunction.enterTextboxContains("FEIN","fein");
				sleep(3000);
			    commonFunction.screenShot("FEINSearch","Pass","FEIN Search for Task");
			    sleep(3000);
			    commonFunction.clickButtonContains(" Search ");
			    
			    sleep(3000);
			    commonFunction.ScrollMenu("Review Profile Data");
			    sleep(3000);
			    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - Review Profile Data");
			    sleep(3000);
			    commonFunction.clickOnLink("Review Profile Data");
			    
			    // --- WF-091 ---
			    commonFunction.waitForLoadingIconToDisappear();
			    sleep(2000);
			    commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
			    sleep(3000);
			    commonFunction.clickButtonContains("Open Work Item ");
			    
			 // --- EEWI-014---
			    commonFunction.waitForLoadingIconToDisappear();
			    commonFunction.screenShot("Review Profile Data Task", "Pass", "Successful launch to EEWI-014 page");
			    sleep(2000);
				//commonFunction.enterPastDate("Date Covered Employment began? ", 180);
				// sleep(2000);
				 commonFunction.enterPastDate("Liability date – Qtr. Year", 60);
				 sleep(2000);
			    commonFunction.selectRadioQuestions("Financing Method", "Contributory");
			    sleep(2000);
			    commonFunction.selectDropdown("Account Status", " Liable ");
			    sleep(2000);
			    empPage.commentid.sendKeys("Test Reveiw");
			    sleep(2000);
			    commonFunction.screenShot("Review Profile Data Task","Pass","Details entered in to EEWI-014 page");
			    sleep(2000);
			    commonFunction.clickButtonContains("Submit ");
			    
			 // --- SUC-002 ---
			    commonFunction.waitForLoadingIconToDisappear();
			    commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
			    sleep();
				commonFunction.clickButtonContains("Home ");
				sleep();
				commonFunction.waitForLoadingIconToDisappear();
				
              //--------Menu----
				
				commonFunction.clickMenu("menu");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Inquiry");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Contribution Inquiry");
				sleep(2000);
				commonFunction.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Inquiry Employer Account");
				
				//----------SREG 050
				sleep(2000);
				commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully landed on SREG 050 page");
				commonFunction.enterTextboxContains(" FEIN ", "fein");  //
				//commonFunction.enterTextboxContains("Employer Registration Number", "6070139");
				sleep(2000);
				commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully entered deatils and  click on continue");
				commonFunction.clickButton("Continue ");
				sleep(2000);
				
				//----------SREG 051
				commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "Successfully landed on SREG 051 page");
				sleep(2000);
				commonFunction.screenShot("TC:EE_03_006", "Pass", "Successfully Completed	");
				//done by palak
				
	}
}
