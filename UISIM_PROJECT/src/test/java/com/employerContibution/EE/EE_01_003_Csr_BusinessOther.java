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

public class EE_01_003_Csr_BusinessOther extends TestBase{

	@Test()
	public void EE_01_003_csr_registration() throws Exception {

		/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		
		employerManagementLocators eml = new employerManagementLocators();
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

	//	Map<String, String> databaseResults = commonFunction.database_SelectQuerySingleColumn(
		//		 "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
			//	 , "FEIN"); String FEIN = databaseResults.get("FEIN");
				//  System.out.println("FEIN NUMBER = " +FEIN);
				 
				 // Map<String, String> databaseResults1 = commonFunction.database_SelectQuerySingleColumn(
		//				 "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
			//	 , "EAN"); String EAN = databaseResults1.get("EAN");
				//  System.out.println("EAN NUMBER = " +EAN);
				
		
		test = report.createTest("EE_01_003 -  Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'other' and work items will be created for CSR to review.");

		
		
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

		// --- SREG-025 ---//
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)","063231175");
		commonFunction.selectDropdown("Type of Legal Entity", " Other ");
		commonFunction.enterTextboxContains("If Other, provide the type of Legal Entity.", "test");
		commonFunction.enterTextboxContains("Employer Registration Number", "8600537");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-025 page");
		commonFunction.clickButton("Continue ");
		sleep(3000);
		
		//--- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("STRUCTURAL STONES CONCEPTS INC");
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "4/6/2022");
		commonFunction.enterTextboxContains("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from", "4/13/2022");		
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
		commonFunction.enterTextboxContains("Address Line 1 ", "1st");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-007 ---
		commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");

		// --- SREG-004 ---
		commonFunction.screenShot("EE01008", "Warning", "Successful launch to Employer Contact Details(SREG-004) page");
		commonFunction.selectRadio("Same as Primary Business Physical Address");
		commonFunction.selectRadio("Same as Mailing");
		commonFunction.selectRadio("Same as Location of Books and Records");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-521 --- 
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-011 ---
	    commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-011 page");
	    commonFunction.selectRadio("Yes ");
	    commonFunction.enterTextboxContains("Employer Registration Number", "0506625");
	    commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "134021237");
	    empRegPage.Legal_Name_of_Business.sendKeys("OCQULA INC");
	    commonFunction.enterTextboxContains("Address Line 1 ", "1st");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.selectRadio("ALL");
		commonFunction.enterTextboxContains("Acquisition Date", "4/1/2023");
		commonFunction.enterTextboxContains("Notification date of Transfer", "4/13/2023");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-012 ---
	    commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-012 page");
	    commonFunction.clickButton("Continue ");
	    
	    // --- SREG-713 ---
	    commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-713 page");
	    commonFunction.selectRadio("Yes ");
	    commonFunction.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "648901299");
	    commonFunction.enterTextboxContains("Prior Employer Registration Number", "6481299");
	    commonFunction.enterTextboxContains("Date of Legal Entity change", "1/5/2022");
		commonFunction.enterTextboxContains("Date of Notification", "4/4/2023");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
		commonFunction.selectRadio("Individual");
		commonFunction.enterTextboxContains("First Name","XYZ");
		commonFunction.enterTextboxContains("Last Name","xyz");
		commonFunction.enterTextboxContains("Address Line 1 ", "1st");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
				
		// --- SREG-005 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
		commonFunction.clickOnLink("Add Corporate Officer/Owner Details");
		
		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
		commonFunction.selectRadio("Business Entity");	
		commonFunction.enterTextboxContains("Entity Name","abc");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "134021237");
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
		
	}
}