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

public class EE_01_001_Csr_Registration_EmpType_Business_Corporation extends TestBase{

	@Test()
	public void EE_01_001_csr_registration() throws Exception {

		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		test = report.createTest("EE_01_001 -  Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Corporation (All types)' and work items will be created for CSR to review");

		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Employer Registration");
	    commonFunction.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Register Employer");
		commonFunction.clickMenu("Register Employer");	
   	    sleep();
		
   	    // --- SREG-001 ---
   	    commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-001 page");
   	    commonFunction.clickButton("Continue ");

		// --- SREG-025 ---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)","");
		commonFunction.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", "");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-025 page");
		commonFunction.clickButton("Continue ");
		sleep(3000);
				
		//--- SREG-025 ---
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.selectDropdown("Employer Type", " Business ");	
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)","815318333");
		commonFunction.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		commonFunction.enterTextboxContains("Employer Registration Number","5426933");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100G ");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "Yes ");
		commonFunction.enterTextboxContains("Enter the prior Employer Registration Number.","5426933");
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Message 'Previous NYS Employer Registration Number cannot be the same as the Employer Registration Number submitted on this application' on SREG-025 page");
		sleep();
		
		//--- SREG-025 ---
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.selectDropdown("Employer Type", " Business ");	
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)","815318333");
		commonFunction.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		commonFunction.enterTextboxContains("Employer Registration Number","5426933");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100G ");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered on SREG-025 page");
		commonFunction.clickButton("Continue ");
		sleep();
				
		//--- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-003 page");
		empRegPage.legalNameTextBox.sendKeys("Laksh Private Limited");
		commonFunction.enterTextboxContains("Other commonly known", "New Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFunction.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFunction.enterTextboxContains("Business Email Address", "shubhanshisahu@labor.ny.gov");
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "1/1/1800");
        commonFunction.enterTextboxContains("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from", "7/5/1800");		
        commonFunction.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
        commonFunction.selectDropdown("Quarter ", " 1 ");
        commonFunction.selectDropdown("Year ", " 2022 ");
        commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
        commonFunction.enterTextboxContains("Total number of covered employees", "450");
        commonFunction.selectRadioQuestions("Are you registering to remit withholding tax only?", "No ");
        commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
        commonFunction.clickButton("Continue ");
        sleep();
        
        //---SREG-008---
        commonFunction.screenShot("EmpRegister4", "Pass", "Sucessfully launched to SREG-008 page page");
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
		commonFunction.enterTextboxContains("Legal Name of Business", "Laksh Private Limited");
		commonFunction.clickButton(" Search ");
		commonFunction.clickOnLink("LAKSH PRIVATE LIMITED");
		
		//---SREG-001---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		//---SREG-025---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		commonFunction.clickButton("Continue ");
        
		//--- SREG-003 ---
      	commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-008 ---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		//---SREG-004---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-004 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-004 page");
		commonFunction.clickButtonContains("Previous ");
		sleep(2000);
		
		//---SREG-008---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 2 ", "13th Street");
		commonFunction.selectDropdown("Principal Business Activity at this location in New York State"," Manufacturing ");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.screenShot("EE01008", "Pass", "Message for fill details to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("Address Line 2 ", "s");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.enterTextboxContains("Number of employees at this location","100");
		commonFunction.selectDropdown("Principal Business Activity at this location in New York State"," Other ");
		empRegPage.Type_of_Establishment.sendKeys("Testing");
		empRegPage.Principal_Product_Sold_or_Service_Rendered.sendKeys("Testing");
		empRegPage.Percent_of_Total_Revenue.sendKeys("70%");
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  If Other, provide details");
		commonFunction.enterTextboxContains("If Other, provide details","Marketing");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
		}
		
		commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		sleep(2000);
		
		// --- SREG-007 ---
		commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLink(" Add Another Business Location ");
		

		//---SREG-008---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "vatican city");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("County", " Egypt ");
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.enterTextboxContains("Number of employees at this location","45");
		commonFunction.selectDropdown("Principal Business Activity at this location in New York State"," Manufacturing ");
		commonFunction.enterTextboxContains("If Other, provide details","Marketing");
		empRegPage.Principal_Products_Produced.sendKeys("Testing");
		empRegPage.Principal_Raw_Materials_Used.sendKeys("Testing");
		commonFunction.enterTextboxContains("Percent of Total Sales Value","90%");
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep(2000);
				
		// --- SREG-007 ---
		commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLink("Edit");
		sleep();
		
		//---SREG-008---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.clickButton("Continue ");
        sleep();		
		
        // --- SREG-007 ---
     	commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
     	commonFunction.clickOnLink("Delete");
     	commonFunction.screenShot("EE01008", "Warning","Pop up launch for Confirmation!Do you want to delete this location/address details?");
     	commonFunction.clickButton(" No ");
     	sleep(2000);
     	commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLink("Delete");
		commonFunction.screenShot("EE01008", "Warning","Pop up launch for Confirmation!Do you want to delete this location/address details?");
		commonFunction.clickButton(" Yes ");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-004 ---
		commonFunction.screenShot("EE01008", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-003 page");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		commonFunction.enterTextbox("First Name", "abc");
		commonFunction.enterTextbox("Last Name", "abc");
		commonFunction.enterTextbox(" Telephone Number ", "8269375089");
		sleep(2000);
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		commonFunction.enterTextbox("First Name", "abc");
		commonFunction.enterTextbox("Last Name", "abc");
		commonFunction.enterTextbox(" Telephone Number ", "8269375089");
		commonFunction.screenShot("EE01008", "Pass", "Successfully entered details in SREG-004 page");
		sleep(2000);
		commonFunction.clickButton("Continue ");
        sleep(2000);
	
		try {
			empRegPage.bmad_Address.click();
		} catch (Exception exception) {
			empRegPage.bmad_AddressInnerCircle.click();
		}
		try {
			empRegPage.lbra_Address.click();
		} catch (Exception exception) {
			empRegPage.lbra_AddressInnerCircle.click();
		}
		try {
			empRegPage.npca_Address.click();
		} catch (Exception exception) {
			empRegPage.npca_AddressInnerCircle.click();
		}
		commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-004");
		empRegPage.continueButton_popUp.click();
		sleep(2000);
		
	// SREG-011 expected, SREG-521 coming. 
		
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-011 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-011 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-713 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-713 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
		commonFunction.clickButton("Continue ");
		
		// --- SREG 683 ---
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
		commonFunction.clickButton("Previous ");
		
		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
		commonFunction.selectRadio("Individual");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.screenShot("EE01008", "Warning", "Message for enter Required field SREG-006 page");
		commonFunction.selectRadio("Individual");
		commonFunction.enterTextboxContains("First Name","XYZ");
		commonFunction.enterTextboxContains("Last Name","xyz");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-005 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
		commonFunction.clickOnLink("Add Member/Managing Member Details");
		
		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
		commonFunction.selectRadio("Individual");
		commonFunction.enterTextboxContains("First Name","XYZ");
		commonFunction.enterTextboxContains("Last Name","xyz");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-005 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG 683 ---
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Previous ");
		sleep(10000);
		
		// --- SREG-800 ---				
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);

	   // --- SREG-043 ---
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
	   
		// --- SREG-013 ---
	}
}