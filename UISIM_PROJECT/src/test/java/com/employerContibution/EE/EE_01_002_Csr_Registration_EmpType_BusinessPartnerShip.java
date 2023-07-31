package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_01_002_Csr_Registration_EmpType_BusinessPartnerShip extends TestBase{

	@Test()
	public void EE_01_002_csr_registration() throws Exception {
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagementLocators eml = new employerManagementLocators();
		employerManagement em =  new employerManagement();
		test = report.createTest("EE.01.002 -  Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Partnership' and work items will be created for CSR to review.");
		
		commonFunction.login("NDBAS4", "Anay@123456789");
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Employer Registration");
	    commonFunction.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Register Employer");
		commonFunction.clickMenu("Register Employer");	
   	    sleep();
		
   	// --- SREG-001 ---
   			commonFunction.enterTextboxContains("First Name", "Antonio");
   			commonFunction.enterTextboxContains("Middle Initial", "S");
   			commonFunction.enterTextboxContains("Last Name", "Rodriguez");
   			commonFunction.selectDropdown("Suffix", " Sr. ");
   			commonFunction.enterTextboxContains("Job Title", "Tester");
   			commonFunction.enterTextboxContains(" Contact Telephone Number ",
   					Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
   							+ Long.toString(commonFunction.createRandomInteger(10, 99)));
   			commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
   			commonFunction.enterTextboxContains("Email Address",
   					"randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
   			commonFunction.screenShot("MenuPage", "Pass", "Details entered on SREG-001 page");
   			commonFunction.clickButton("Continue ");


		// --- SREG-025 ---//
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)","120042355");
		commonFunction.selectDropdown("Type of Legal Entity", " Partnership ");
		commonFunction.enterTextboxContains("Employer Registration Number", "1289179");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-025 page");
		commonFunction.clickButton("Continue ");
		sleep(3000);
		
		//--- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("ONE CASE TWO");
		commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Tester");
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "4/15/2023");
		commonFunction.enterTextboxContains("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from", "3/20/2023");		
		commonFunction.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
		commonFunction.selectDropdown("Quarter ", " 2 ");
		commonFunction.selectDropdown("Year ", " 2023 ");
		commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		commonFunction.enterTextboxContains("Total number of covered employees", "10");
		commonFunction.selectRadioQuestions("Are you registering to remit withholding tax only?", "No ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
		commonFunction.clickButton("Continue ");
		sleep();
		
		//---SREG-008---
				commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
				commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
				commonFunction.enterTextboxContains("City ", "New York");
				commonFunction.enterTextboxContains("Zip Code", "10011");
				sleep();
				commonFunction.selectDropdown("State", " New York ");
				commonFunction.selectDropdown("County", " Albany ");
				sleep(2000);
				commonFunction.enterTextboxContains("Number of employees at this location","0");
				commonFunction.selectDropdown("Principal Business Activity at this location in New York State"," Manufacturing ");
				empRegPage.Principal_Products_Produced.sendKeys("Test engaged in manufacturing");
				commonFunction.enterTextboxContains("Percent of Total Sales Value","47");
				empRegPage.Principal_Raw_Materials_Used.sendKeys("Test row material used");
				commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
				commonFunction.clickButton("Continue ");
				sleep(2000);
				try {
					empRegPage.uspsBusinessAddress.click();
				} catch (Exception exception) {
					
					empRegPage.uspsBusinessAddressInnerCircle.click();
				}
				
				commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-008");
				empRegPage.continueButton_popUp.click();
				sleep(2000);
				
				// --- SREG-007 ---
				commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
				commonFunction.clickButton(" Finish Later ");
				commonFunction.clickButton(" Yes ");
				sleep();
				
				//---Home---
				commonFunction.clickMenu("menu");
				commonFunction.ScrollMenu("Employer Registration");
			    commonFunction.clickMenu("Employer Registration");
				commonFunction.ScrollMenu("Incomplete Registration");
				commonFunction.clickMenu("Incomplete Registration");
				//---SREG-101---
				commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-101 page");
				commonFunction.enterTextboxContains("Legal Name of Business", "ONE CASE TWO");
				commonFunction.clickButton(" Search ");
				commonFunction.clickOnLink("ONE CASE TWO");
				
				//---SREG-001---
				commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-001 page");
				commonFunction.clickButton("Continue ");
				
				//---SREG-025---
				commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
				commonFunction.clickButton("Continue ");
				
				//---SREG-003---
				commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-003 page");
				commonFunction.clickButton("Continue ");
								
				// --- SREG-007 ---
				commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
				commonFunction.clickButton("Continue ");
				
				// --- SREG-004 ---
				commonFunction.screenShot("EE01008", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
				commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
				empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
				empRegPage.uspsBmadCityText.sendKeys("New York");
				empRegPage.uspsBmadZipText.sendKeys("10003");
				empRegPage.uspsBmadCounty.click();
				commonFunction.selectFromDropdown(" Albany ");
				sleep(2000);
				commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
				commonFunction.enterTextbox("Care Of","LEGACY");
				empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
				empRegPage.uspsBmadCityText.sendKeys("New York");
				empRegPage.uspsBmadZipText.sendKeys("10003");
				empRegPage.uspsBmadCounty.click();
				commonFunction.selectFromDropdown(" Albany ");
				sleep(2000);
				commonFunction.enterTextbox("First Name", "abc");
				commonFunction.enterTextbox("Last Name", "abc");
				commonFunction.enterTextbox(" Telephone Number ", "8269375089");
				sleep(2000);
				commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
				commonFunction.enterTextbox("First Name", "abc");
				commonFunction.enterTextbox("Last Name", "abc");
				commonFunction.enterTextbox(" Telephone Number ", "8269375089");
				commonFunction.screenShot("EE01008", "Pass", "Successfully entered details in SREG-004 page");
				sleep(2000);
				commonFunction.clickButton("Continue ");
		        sleep(2000);
			
		//		try {
		//			empRegPage.bmad_Address.click();
		//		} catch (Exception exception) {
		//			empRegPage.bmad_AddressInnerCircle.click();
		//		}
		//		try {
		//			empRegPage.lbra_Address.click();
		//		} catch (Exception exception) {
		//			empRegPage.lbra_AddressInnerCircle.click();
		//		}
		//		try {
		//			empRegPage.npca_Address.click();
		//		} catch (Exception exception) {
		//			empRegPage.npca_AddressInnerCircle.click();
		//		}
		//		commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-004");
//				empRegPage.continueButton_popUp.click();


				sleep(2000);
				
		
		
		// --- SREG-521 --- 
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-011 ---
	    commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-011 page");
	    commonFunction.selectRadio("Yes ");
	    commonFunction.enterTextboxContains("Employer Registration Number", "4678999");
	    commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "134021237");
	    empRegPage.Legal_Name_of_Business.sendKeys("SUNSHINE MEDICAL PC   ");
	    commonFunction.enterTextboxContains("Address Line 1 ", "Test");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		//commonFunction.selectRadio("ALL");
		commonFunction.enterTextboxContains("Acquisition Date", "4/1/2023");
		commonFunction.enterTextboxContains("Notification date of Transfer", "64/21/2013");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-012 ---
	    commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-012 page");
	    commonFunction.clickButton("Continue ");
	    
	    // --- SREG-713 ---
	    commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-713 page");
	    commonFunction.selectRadio("Yes ");
	    commonFunction.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "111746409");
	    commonFunction.enterTextboxContains("Prior Employer Registration Number", "6481299");
	    commonFunction.enterTextboxContains("Date of Legal Entity change", "1/5/2022");
		commonFunction.enterTextboxContains("Date of Notification", "6/21/2023");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
		commonFunction.selectRadio("Individual");
		commonFunction.enterTextboxContains("SSN","099820363");
		commonFunction.enterTextboxContains("First Name","JESSE");
		commonFunction.enterTextboxContains("Last Name","GOLDSTEIN");
		commonFunction.enterTextboxContains("Address Line 1 ", "1st");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
				
		// --- SREG-005 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
		commonFunction.clickOnLink("Add Partner Details");
		
		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
		commonFunction.selectRadio("Individual");
		commonFunction.enterTextboxContains("SSN","190560722");
		commonFunction.enterTextboxContains("Entity Name","DAVID");
		commonFunction.enterTextboxContains("Last Name","GOLDSTEIN");
		commonFunction.enterTextboxContains("Address Line 1 ", "1st");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-005 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-683 ---
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		sleep(10000);
		
		// --- SREG-800 ---
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-800");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-043 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.selectCheckbox("I accept");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-013 page");
		commonFunction.clickButton("Home ");
						
		// --- Home ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
		commonFunction.clickButton(" Go to My Q ");
				
		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.enterTextboxContains("Work Item Description Free Text", "Dol");
		commonFunction.clickButton(" Search ");
		commonFunction.clickHyperlink("DOL DTF Discrepancy");
		       
		// --- WF-091 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
		commonFunction.clickButton("Open Work Item ");
			  
		// --- EEWI-005 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-005 page");
		commonFunction.selectDropdown("Account Status", " Liable ");
		commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
		empRegPage.commentBox_MyQ.sendKeys("for testing");
		commonFunction.clickButton("Submit ");
		
		// --- Home ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
		commonFunction.clickButton(" Go to My Q ");
		
		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.enterTextboxContains("FEIN", "363735912");
		commonFunction.clickButton(" Search ");
		commonFunction.clickOnLink("Review potential Duplicates");
		
		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButtonContains("Open Work Item ");
		
		//---EEWI-012---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButton("Submit ");
		commonFunction.screenShot("EE01007", "Pass", "Message 'Required field'on WF-001 page");
		commonFunction.clearTextboxContains("Legal Name Of Business");
		commonFunction.enterTextboxContains("Legal Name Of Business","AUTO TESTING LEGAL NAME 843303756");
		commonFunction.enterTextboxContains("First date of operation", "7/5/1940");
		commonFunction.selectDropdown("Quarter ", " 4 "); 
		commonFunction.selectDropdown("Year ", " 1940 "); 
		commonFunction.selectDropdown("Account Status", " Liable ");
		empRegPage.commentBox_MyQ.sendKeys("for testing");
		commonFunction.clickButton("Submit ");
		commonFunction.screenShot("EE01007", "Pass", "Popup 'Verify Date'on WF-001 page");
		commonFunction.clickButton(" Yes ");
		
		
		
		//---SUC-002---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
		commonFunction.clickButton("Home ");
				
		// --- Home ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
		commonFunction.clickButton(" Go to My Q ");
				
		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.enterTextboxContains("FEIN", "363735912");
		commonFunction.clickButton(" Search ");
		commonFunction.clickOnLink("Review Employer Type");
		
		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButtonContains("Open Work Item ");
		
		//---EEWI-012---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButton("Submit ");
		commonFunction.screenShot("EE01007", "Pass", "Message 'Required field'on WF-001 page");
  
		//Blocked at Step 70
	
	}

}

