package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_004 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EE.10.004 - Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'School District' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_10_004() throws Exception {

		test = report.createTest(
				"EE.10.004 - Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'School District' and work items will be created for CSR to review.");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		
		       // --- Login ---
				commonFunction.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
				commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
				
				// ---Menu Click---
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.clickMenu("menu");
				// commonFuntions.clickMenu("Employer Registration");
				commonFunction.clickMenu("Employer Registration");
				commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
				commonFunction.clickMenu("Register Employer");
				// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
				sleep(2000);
				commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
				
				// --- SREG-001 ---
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.enterTextboxContains("First Name", "Antonio");
				commonFunction.enterTextboxContains("Middle Initial", "S");
				commonFunction.enterTextboxContains("Last Name", "Rodriguez");
				commonFunction.selectDropdown("Suffix", " Sr. ");
				sleep(2000);
				commonFunction.screenShot("EmpRegister2", "Pass", "Details entered on SREG-001 page");
				commonFunction.enterTextboxContains("Job Title", "Tester");
				commonFunction.enterTextboxContains(" Contact Telephone Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
				commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
				commonFunction.enterTextboxContains("Email Address", "randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
				sleep(2000);
				commonFunction.screenShot("EmpRegister3", "Pass", "Details entered on SREG-001 page");
				commonFunction.clickButton("Continue ");

				// --- SREG-025 ---
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("MenuPage", "Pass", "Details entered on SREG-025 page");
				commonFunction.selectDropdown("Employer Type", " Governmental ");
				commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "959975907"); 
				commonFunction.selectDropdown("Type of Legal Entity", " School District ");
				commonFunction.enterTextboxContains("Employer Registration Number", "2501424"); //4543352
				sleep(2000);
				commonFunction.screenShot("EmpRegister4", "Pass", "Details entered and click on CONTINUE button");
				commonFunction.clickButton("Continue ");
				
				// --- SREG-003 ---
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("EmpRegister5", "Pass", "Launched Employer Entity Information(SREG-003) page");
				empRegPage.legalNameTextBox.sendKeys("THE H-ADJUSTED TAX"); //ColorEseence122
				//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
				//commonFunction.enterTextboxContains("Other commonly known", "S Corp");
				commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
				sleep(2000);
				commonFunction.screenShot("EmpRegister6", "Pass", "Details entered in SREG-003 page");
				commonFunction.enterTextboxContains("date of the first payroll", "09/01/2020");
				commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "453");
				commonFunction.enterTextboxContains("Date covered employment began?", "05/07/2021");
				commonFunction.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Reimbursable");
				sleep(2000);
				commonFunction.screenShot("EmpRegister7", "Pass", "Details entered in SREG-003 page and click Continue");
				commonFunction.clickButton("Continue ");
				sleep();
				
				// --- SREG-008 ---
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("EmpRegister8", "Pass", "Sucessfully launched to SREG-008 page");
				commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
				commonFunction.enterTextboxContains("City ", "New York");
				commonFunction.enterTextboxContains("Zip Code", "10011");
				sleep();
				commonFunction.selectDropdown("State", " New York ");
				commonFunction.selectDropdown("County", " Albany ");
				sleep(2000);
				commonFunction.screenShot("EmpRegister9", "Pass", "Enter the details on SREG-008 and click continue");
				commonFunction.clickButton("Continue ");
				
				try {
					empRegPage.uspsBusinessAddress.click();
				} catch (Exception exception) {
					empRegPage.uspsBusinessAddressInnerCircle.click();
				}
				
				commonFunction.screenShot("EmpRegister10", "Pass", "USPS Business address selection on SREG-008");
				empRegPage.continueButton_popUp.click();
				
				// --- SREG-007 ---
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("EmpRegister11", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
				commonFunction.clickButton("Continue ");
				// --- SREG-004 ---
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("EmpRegister12", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
				commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
				empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
				empRegPage.uspsBmadCityText.sendKeys("New York");
				empRegPage.uspsBmadZipText.sendKeys("10003");
				empRegPage.uspsBmadCounty.click();
				commonFunction.selectFromDropdown(" Albany ");
				sleep(2000);
				commonFunction.screenShot("EmpRegister13", "Pass", "Successfully entered details in SREG-004 page");
				
				commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
				empRegPage.uspsLbraAddressText.sendKeys("Affinia Manhattan Hotel");
				empRegPage.uspsLbraCityText.sendKeys("New York");
				empRegPage.uspsLbraZipText.sendKeys("10001");
				empRegPage.uspsLbraCounty.click();
				commonFunction.selectFromDropdown(" Albany ");
				empRegPage.uspsLbraFirstNameText.sendKeys("Thomas");
				empRegPage.uspsLbraLastNameText.sendKeys("Shelby");
				sleep(2000);
				commonFunction.screenShot("EmpRegister14", "Pass", "Successfully entered details in SREG-004 page");
				
				commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
				empRegPage.uspsNpcaAddressText.sendKeys("55 East 10th Street");
				empRegPage.uspsNpcaCityText.sendKeys("New York");
				empRegPage.uspsNpcaZipText.sendKeys("10003");
				empRegPage.uspsNpcaCounty.click();
				commonFunction.selectFromDropdown(" Albany ");
				empRegPage.uspsNpcaFirstNameText.sendKeys("Sydney");
				empRegPage.uspsNpcaLastNameText.sendKeys("Sheldon");
				sleep(2000);
				commonFunction.screenShot("EmpRegister15", "Pass", "Successfully entered details in SREG-004 page");
				commonFunction.clickButton("Continue ");
				
				sleep(2000);
				empRegPage.uspsBmadAddressRadio.click();
				empRegPage.uspsNpcaAddressRadio.click();
				commonFunction.screenShot("EmpRegister15", "Pass", "Click on appropriate USPS radio on SREG-004 page");
				empRegPage.continueButton_popUp.click();
				
				try {
					commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
					commonFunction.clickButton("Continue ");
				} catch(Exception exception) {
					sleep(2000);
				}
				// SREG-011 expected, SREG-521 coming. 
				sleep(2000);
				commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-521 page");
				commonFunction.clickButton("Continue ");
				
				// --- SREG 683 ---
				sleep(2000);
				commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
				sleep();
				commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
		 		sleep(2000);
		 		commonFunction.uploadDoc("Sample.docx");
		 		sleep(2000);
		 		commonFunction.screenShot("EE01007", "Pass", "Sample document uploaded");
				commonFunction.clickButton("Continue ");
		        // --- SREG-800 ---
				sleep(10000);
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
				commonFunction.clickButton("Continue ");
	            // --- SREG-043 ---
				sleep(2000);
				 empRegPage.Submitter_Comments_may_be_entered_below.sendKeys("Testing");
				commonFunction.selectCheckbox("I accept");
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
				commonFunction.clickButton("Submit ");
				sleep(10000); 
				
				// --- SREG-013 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
				commonFunction.clickButton("Home ");
				sleep(2000);

				// --- SREG-013 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
				sleep(20000);
				

				commonFunction.login("ndsbb3","Brijen@1234567");
				commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
				 sleep(10000);
				 
				commonFunction.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
				commonFunction.clickButton(" Go to My Q ");
			      sleep(3000);
				   
			     // --- WF-001 ---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
				 commonFunction.enterTextboxContains("FEIN", "240785592");
				 commonFunction.clickButton(" Search ");
				 commonFunction.clickHyperlink("Review Employer Type");
			     // --- WF-091 ---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
				 commonFunction.clickButton("Open Work Item ");
						
			     // --- EEWI-002 ---		
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-002 page");
				 commonFunction.enterTextboxContains("Date covered employment began?", "6/04/2023");
				 commonFunction.selectRadio("Contributory");
				 empRegPage.commentBox_MyQ.sendKeys("Testing");
				 commonFunction.clickButton("Submit ");
				 sleep(2000);
						 
				// --- SUC-002 ---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
				 commonFunction.clickButton("Home ");
				 sleep();
						 
				 // ---Home---
				 commonFunction.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
				 commonFunction.clickButton(" Go to My Q ");
				 sleep(3000);
						 
				 // --- WF-001 ---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
				 commonFunction.enterTextboxContains("Work Item Description Free Text", "dol");
				 commonFunction.clickButton(" Search ");
				 commonFunction.clickHyperlink("DOL DTF Discrepancy");
				  
				 // --- WF-091 ---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
				 commonFunction.clickButton("Open Work Item ");
					
				 // --- EEWI-005 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-005 page");	
				empRegPage.Legal_Name_of_business.sendKeys(" TAX");
				commonFunction.selectRadioQuestions("Financing Method", "Contributory");
				commonFunction.selectDropdown("Account Status", " Liable ");
				commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
				empRegPage.commentBox_MyQ.sendKeys("for testing");
				commonFunction.clickButton("Submit ");
						
				// --- SUC-002 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
				commonFunction.clickButton("Home ");
				sleep();
							 
				// ---Home---
				commonFunction.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
				commonFunction.clickButton(" Go to My Q ");
				sleep(3000);
							 
				// --- WF-001 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
				commonFunction.enterTextboxContains("FEIN", "240785592");
				commonFunction.clickButton(" Search ");
				commonFunction.clickHyperlink("Review Comments");
				
				// --- WF-091 ---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
				 commonFunction.clickButton("Open Work Item ");
				 
				//---Review Comments---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched Review Comments workitem");
				 commonFunction.selectRadioQuestions("Financing Method", "Contributory");
				 commonFunction.selectDropdown("Account Status", " Future ");
				 commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
				 empRegPage.commentBox_MyQ.sendKeys("for testing");
				 commonFunction.clickButton("Submit ");
				 
				 // --- SUC-002 ---
				 commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
				 commonFunction.clickButton("Home ");
				 sleep();
								 
				// ---Home---
				commonFunction.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
				commonFunction.clickButton(" Go to My Q ");
				sleep(3000);
							 
				// --- WF-001 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
				commonFunction.enterTextboxContains("FEIN", "240785592");
				commonFunction.clickButton(" Search ");
				commonFunction.clickHyperlink("Review Profile Data");
				
				// --- WF-091 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
				commonFunction.clickButton("Open Work Item ");
				 
				// --- EEWI-014 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-014 page");
				commonFunction.enterTextboxContains("Liability date – Qtr. Year","01/01/2022");
				commonFunction.selectDropdown("Account Status", " Future ");
				commonFunction.selectCheckbox("New ERN required ?");
				commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "959975907");
				empRegPage.commentBox_MyQ.sendKeys("for testing");
				commonFunction.clickButton("Submit ");
				sleep();
				      
				// --- SUC-002 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
				commonFunction.clickButton("Home ");
				sleep();
				commonFunction.clickMenu("menu");
				commonFunction.screenShot("Menu", "Pass", "ClickMenu");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Inquiry");
				commonFunction.ScrollMenu("Contribution Inquiry");
				commonFunction.clickMenu("Contribution Inquiry");
				commonFunction.ScrollMenu("Inquiry Employer Account");
				commonFunction.clickMenu("Inquiry Employer Account");
				sleep();
							
				//---SREG-050---
				commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Inquiry Employer Account - Enter ERN (SREG-050)screen launched");
				commonFunction.enterTextboxContains("Employer Registration Number" ,"2501424");
				commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "ERN Entered");
				commonFunction.clickButtonContains("Continue ");
				sleep(2000);
							
				//---SREG-051---
				commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "Inquiry Employer Account Information (SREG-051)screen Launched");
				commonFunction.clickButtonContains("Previous ");
				sleep(2000);
						
				//---SREG-050---
				commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Inquiry Employer Account - Enter ERN (SREG-050)screen launched");
				commonFunction.clickButton("Home ");
				commonFunction.screenShot("Home Page", "Pass", "Home Page Launched");
				sleep(2000);
						        
				// DONE	 
						 
	}
}
