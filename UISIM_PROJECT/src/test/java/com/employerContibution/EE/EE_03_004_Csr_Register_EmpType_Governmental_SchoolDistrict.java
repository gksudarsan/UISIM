package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_03_004_Csr_Register_EmpType_Governmental_SchoolDistrict extends TestBase {

	@Test
	public void EE_03_004() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report
				.createTest("EE.03.004 Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'School District' and work items will be created for CSR to review.");
	
		cf.login("ndsbb3","Brijen@1234567");
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("menu");
	    cf.ScrollMenu("Employer Registration");
		cf.clickMenu("Employer Registration");
		cf.ScrollMenu("Register Employer");
		cf.clickMenu("Register Employer");	
   	    sleep();
				// --- SREG-001 ---
       	cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-001 page");
		cf.clickButton("Continue ");

		// --- SREG-025 ---//
		cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		cf.selectDropdown("Employer Type", " Governmental ");
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)","830629123");
		cf.selectDropdown("Type of Legal Entity", " School District ");
		cf.enterTextboxContains("Employer Registration Number", "6750008");
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		cf.selectDropdown("Source Type", " NYS-100G ");
		cf.screenShot("EmpRegister3", "Pass", "Details entered on SREG-025 page");
		cf.clickButton("Continue ");
		sleep(3000);
				
		// --- SREG-003 --- //
		cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-003 page");
		empPage.legalNameTextBox.sendKeys("THE DOG PALACE");
        cf.enterTextboxContains("Other commonly known", "ABCDERFGH");
		cf.enterTextboxContains(" Business Phone Number  ",
						Long.toString(cf.createRandomInteger(10000000, 99999999))
								+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains(" Business Fax Number ", "9484735838");
		cf.enterTextboxContains("What is the date of the first payroll which you withheld (or","/6/17/2023");
		cf.enterTextboxContains("Estimated or approximate number of individuals working in covered employment", "50");
		cf.enterTextboxContains("Date covered employment began?", "10/4/2023");
		cf.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"No ");
		cf.screenShot("EmpRegister5", "Pass","Enter the details on Employer Entity Information page and click continue");
		cf.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.","Contributory");
		cf.clickButton("Continue ");

		
		// --- SREG-008 ---
		sleep(2000);
		cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		cf.enterTextboxContains("Address Line 1 ", "13th Street");
		cf.enterTextboxContains("City ", "New York");
		cf.enterTextboxContains("Zip Code", "10011");
		sleep();
		cf.selectDropdown("State", " New York ");
		cf.selectDropdown("County", " Albany ");
		
		sleep(2000);
		cf.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		cf.clickButton("Continue ");
		sleep(3000);
		try {
			empPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empPage.uspsBusinessAddressInnerCircle.click();
		}
		
		cf.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-008");
		empPage.continueButton_popUp.click();
		
		
		// --- SREG-007 ---
		sleep(2000);
		cf.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
		cf.clickButton("Continue ");
		
		// --- SREG-004 ---
		
		cf.screenShot("EE01008", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		empPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empPage.uspsBmadCityText.sendKeys("New York");
		empPage.uspsBmadZipText.sendKeys("10003");
		empPage.uspsBmadCounty.click();
		cf.selectFromDropdown(" Albany ");
		sleep(2000);
		cf.selectRadioQuestions("Location of Books and Records", "Other");
		cf.enterTextboxContains("If Other, Provide the Location of Books and Records Address", "LEGACY");
		empPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empPage.uspsBmadCityText.sendKeys("New York");
		empPage.uspsBmadZipText.sendKeys("10003");
		empPage.uspsBmadCounty.click();
		cf.selectFromDropdown(" Albany ");
		cf.enterTextbox("First Name", "abc");
		cf.enterTextbox("Last Name", "abc");
		cf.enterTextbox(" Telephone Number ", "8269375089");
		sleep(2000);
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		cf.enterTextboxContains("If Other, Provide the Location of Books and Records Address", "LEGACY");
		empPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empPage.uspsBmadCityText.sendKeys("New York");
		empPage.uspsBmadZipText.sendKeys("10003");
		empPage.uspsBmadCounty.click();
		cf.selectFromDropdown(" Albany ");
		cf.enterTextbox("First Name", "abc");
		cf.enterTextbox("Last Name", "abc");
		cf.enterTextbox(" Telephone Number ", "8269375089");
		cf.screenShot("EE01008", "Pass", "Successfully entered details in SREG-004 page");
		sleep(2000);
		cf.clickButton("Continue ");
        sleep(2000);
        try {
		try {
			empPage.bmad_Address.click();
		} catch (Exception exception) {
			empPage.bmad_AddressInnerCircle.click();
		}
		try {
			empPage.lbra_Address.click();
		} catch (Exception exception) {
			empPage.lbra_AddressInnerCircle.click();
		}
		try {
			empPage.npca_Address.click();
		} catch (Exception exception) {
			empPage.npca_AddressInnerCircle.click();
		}
		cf.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-004");
		empPage.continueButton_popUp.click();
    	
		sleep(2000);}
		catch (Exception exception) {}
	
     // --- SREG-521 ---

		cf.screenShot("EmpRegister11", "Pass", "Navigated on SREG-521 page");
		cf.clickButton("Continue ");
         sleep(4000);
         
      // --- SREG-683 ---
		
        cf.screenShot("EmpRegister12", "Pass", "Navigated on SREG-683 page and upload document");
		empPage.browserLink.click();
		Thread.sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.clickButton("Continue ");
		sleep(10000);
	
		
		cf.screenShot("EmpRegister13", "Pass", "Navigated on SREG-800");
		cf.clickButton("Continue ");
		
		// --- SREG-043 ---
		
		cf.screenShot("EmpRegister13", "Pass", "Navigated on SREG-043 and Accept");
		cf.selectCheckbox("I accept");
		cf.clickButton("Submit ");
		sleep(3000);
		
		// --- SREG-013 ---
		
		cf.screenShot("EmpRegister13", "Pass", "Navigated on SREG-013");
        cf.clickButton("Home ");
        sleep();
        cf.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
        cf.clickButton(" Go to My Q ");
        
        sleep(3000);
	   
     // --- WF-001 ---
		 sleep(10000);
        cf.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		 cf.enterTextboxContains("FEIN", "830629123");
		 cf.clickButton(" Search ");
	     cf.clickHyperlink("Review Employer Type");
    // --- WF-091 ---
			cf.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
			cf.clickButton("Open Work Item ");
			
    // --- EEWI-002 ---		
			 cf.screenShot("EE01007", "Pass", "Successfully launched to EEWI-002 page");
			 cf.enterTextboxContains("Date covered employment began?", "6/04/2023");
			 empPage.commentBox_MyQ.sendKeys("Testing");
			 cf.clickButton("Submit ");
			 sleep(2000);
	// --- SUC-002 ---
			 cf.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
			 cf.clickButton("Home ");
			 sleep();
		     cf.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
		     cf.clickButton(" Go to My Q ");
		     sleep(3000);
			   
    // --- WF-001 ---

		      cf.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
			  cf.enterTextboxContains("Work Item Description Free Text", "Dol");
			  cf.clickButton(" Search ");
			  cf.clickHyperlink("DOL DTF Discrepancy");
	// --- WF-091 ---
			  
			  cf.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
			  cf.clickButton("Open Work Item ");
			  
    // --- EEWI-005 ---

		      cf.screenShot("EE01007", "Pass", "Successfully launched to EEWI-005 page");
		      cf.selectDropdown("Account Status", " Liable ");
			  cf.selectRadioQuestions("Suppress Correspondence?", "No ");
			  empPage.commentBox_MyQ.sendKeys("for testing");
			  cf.clickButton("Submit ");
	
	// --- SUC-002 ---

		      cf.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
		      cf.clickButton("Home ");
			  sleep();
			  cf.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
			  cf.clickButton(" Go to My Q ");
			  sleep(3000);
				   
	    // --- WF-001 ---

			   cf.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
			   cf.enterTextboxContains("FEIN", "534758697");
			   cf.clickButton(" Search ");
			   cf.clickHyperlink("Verify Agent Rep Task");
			   
		// --- WF-091 ---
				  
			    cf.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
				cf.clickButton("Open Work Item ");
				
		// --- EEWI-012 ---		
				
				cf.screenShot("EE01007", "Pass", "Successfully launched to EEWI-012 page");
				cf.selectDropdown("Account Status", " Liable ");
				cf.clickHyperlink("Select Agent");
				
        // --- SREG-041-p ---		
				
				cf.screenShot("EE01007", "Pass", "Successfully launched to SREG-041-p page");
				cf.enterTextbox("TPR Name", "C & L ACCOUNTING INC");
				cf.selectRadio("Select");
				cf.clickButton("Continue ");
				
        // --- EEWI-012 ---		
				
				cf.screenShot("EE01007", "Pass", "Successfully launched to EEWI-012 page");		
				cf.clickButton("Submit ");
				
        // --- SUC-002 ---		
				
				cf.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");				
				 cf.clickButton("Home ");
				 sleep();
			     cf.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
			     cf.clickMenu("menu");
			     cf.clickMenu("Inquiry");
			     cf.clickMenu("Contribution Inquiry");
			     cf.clickMenu("Inquiry Employer Account");
			     
		// --- SREG-050 ---
			     
			     cf.screenShot("EE01007", "Pass", "Successfully launched to SREG-050 page");  	
			     cf.enterTextbox(" FEIN ", "133385266");
			     cf.clickButton("Continue ");
			     
        // --- SREG-051 ---
			     
			     cf.screenShot("EE01007", "Pass", "Successfully launched to SREG-051 page");	
			     cf.clickButton("Previous ");
		
	    // --- SREG-050 ---
			     
			     cf.screenShot("EE01007", "Pass", "Successfully launched to SREG-050 page");  	
			     cf.clickButton("Home ");
		
	
	}
	
	
}
