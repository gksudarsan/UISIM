package com.employerContibution.EE;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

		public class EE_09_002_Verify_employer_can_submit_employer_registration_for_employer_type_Indian_Tribe_and_legal_entity_type_Business_and_work_items_will_be_created_for_CSR_to_review extends TestBase {
			@Test()
			public void EE_09_002_Verify_employer_can_submit_employer_registration_for_employer_type_Indian_Tribe_and_legal_entity_type_Business_and_work_items_will_be_created_for_CSR_to_review () throws Exception {

		
		commonStepDefinitions commonFunction = new commonStepDefinitions();	
		 //String EntityName = prop.getProperty("Entity");
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);


		test = report.createTest("EE_09_002_Verify_employer_can_submit_employer_registration_for_employer_type_Indian_Tribe_and_legal_entity_type_Business_and_work_items_will_be_created_for_CSR_to_review");

		test.log(Status.INFO, "Logging to the application");
		
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");	
		sleep(3000);
		commonFunction.clickMenu("menu"); 
		commonFunction.ScrollMenu("Employer Registration");sleep();
		
	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.clickMenu("Employer Registration");sleep(2000);
	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.clickMenu("Register Employer"); sleep(3000);
	commonFunction.screenShot("Page1", "Pass", "Employer Registration");
	commonFunction.clickButtonContains("Continue"); sleep(6000);
	
	commonFunction.selectDropdown("Employer Type", " Indian Tribe ");
	commonFunction.enterTextboxContains("(FEIN)", "00-0973838"); 
	commonFunction.screenShot("file1","Pass", "Searching with FEIN "); 
	commonFunction.selectDropdown("*Type of Legal Entity"," Business "); 
	commonFunction.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
	commonFunction.selectDropdown("Source Type", " NYS-100 ");sleep(2000);
	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.clickButtonContains("Continue");sleep(2000);

	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.populateListbox("Legal Name", "RD 937");sleep(2000);
	commonFunction.selectRadioQuestions("Financing Method", "Reimbursable");
	commonFunction.screenShot("SREG003", "Pass", "Legal name page displayed");
	commonFunction.clickButtonContains("Continue");sleep(6000);
	
	commonFunction.enterTextboxContains("Address Line 1","345 E 24th St,");sleep(2000);
	commonFunction.enterTextboxContains("Address Line 2","");
   commonFunction.enterTextboxContains("City","New York");sleep(2000);
   commonFunction.enterTextboxContains("Zip Code","10010");sleep(2000);
	commonFunction.screenShot("SREG008", "Pass", "Add primary physical address is displayed");		 
	commonFunction.clickButtonContains("Continue ");sleep(6000);
	
	commonFunction.screenShot("SREG007", "Pass", "Business Physical Address Details is displayed");	
	
	sleep(2000);
	try {
		PEOPage.uspsAdd.click();
		commonFunction.screenShot("UspsAddress","Pass","UspsAddress");
		PEOPage.UspsContinueButton.click();
		Thread.sleep(2000);
	}
	catch(Exception e) {
		System.out.println("usps pop up dispalyed");
	}
	sleep(2000);
	
	commonFunction.clickButtonContains("Continue ");sleep(6000);
	
	commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
	commonFunction.enterTextboxContains("Address Line 1","345 E 24th St,");sleep(2000);
	commonFunction.enterTextboxContains("Address Line 2","");
   commonFunction.enterTextboxContains("City","New York");sleep(2000);
   commonFunction.enterTextboxContains("Zip Code","10010");sleep(2000);
   
 	commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
	commonFunction.enterTextboxContains("Address Line 1","345 E 24th St,");sleep(2000);
	commonFunction.enterTextboxContains("Address Line 2","");
   commonFunction.enterTextboxContains("City","New York");sleep(2000);
   commonFunction.enterTextboxContains("Zip Code","10010");sleep(2000);
   
   commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
   commonFunction.enterTextboxContains("Address Line 1","345 E 24th St,");sleep(2000);
	commonFunction.enterTextboxContains("Address Line 2","");
  commonFunction.enterTextboxContains("City","New York");sleep(2000);
  commonFunction.enterTextboxContains("Zip Code","10010");sleep(2000);
  
  commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");
  
  commonFunction.selectRadioQuestions("Agent (C/O) address", "Other");
  commonFunction.enterTextboxContains("Care Of","itstestng");
  commonFunction.enterTextboxContains("Address Line 1","345 E 24th St,");sleep(2000);
	commonFunction.enterTextboxContains("Address Line 2","");
 commonFunction.enterTextboxContains("City","New York");sleep(2000);
 commonFunction.enterTextboxContains("Zip Code","10010");sleep(2000);
 
	commonFunction.screenShot("SREG004", "Pass", "Employer Contact Details is displayed");	
	commonFunction.clickButtonContains("Continue ");sleep(6000);
	
	
	//commonFunction.selectRadioQuestions("Have you changed legal entity?", "No ");
		/*
		 * commonFunction.screenShot("Change in Legal Entity", "Pass",
		 * "Change in Legal Entity(SREG-713");
		 * 
		 * commonFunction.clickButtonContains("Continue "); sleep(2000);
		 * 
		 * commonFunction.screenShot("SREG-006", "Pass",
		 * "Add Trustee/Owner Details is displayed");
		 * commonFunction.clickButtonContains("Continue "); sleep(6000);
		 * 
		 * commonFunction.screenShot("SREG-005", "Pass",
		 * "Add Trustee/Owner Details is displayed01");
		 * commonFunction.clickButtonContains("Continue ");
		 */
	sleep(6000);
	
	commonFunction.screenShot("Upload Documents", "Pass", "Skip Upload Documents(SREG-683)");
	commonFunction.clickButtonContains("Continue ");
	sleep(5000);
	
	commonFunction.screenShot("SREG-800", "Pass", "Employer Registration is displayed");
	commonFunction.clickButtonContains("Continue ");
	sleep(5000);
	
	commonFunction.selectCheckbox("I accept");
	commonFunction.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
	sleep(2000);
	commonFunction.clickButtonContains("Submit");
	sleep(6000);
	commonFunction.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
	sleep(20000);
	commonFunction.clickButtonContains("Home");
	
	commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='99-9971203' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);
   PEOPage.queue.click(); Thread.sleep(15000);
   //commonFunction.enterTextboxContains("FEIN","99-9901203");
   commonFunction.screenShot("FeinSearch","Pass","feinSearch");
   //commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
	commonFunction.screenShot("review employer task","Pass","review employer task is displayed");
	Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
	Thread.sleep(2000);
	commonFunction.screenShot("EEWI-002","Pass","review emp type  ");
	//commonFunction.selectDropdown("Account Status ", "Liable");
	commonFunction.screenShot("EEWI-002","Pass","review emp type  ");
	//commonFunction.enterTextboxContains("Comment", "registration in process");
	commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
	commonFunction.screenShot("GeneralInfo","Pass","General Information");
	commonFunction.clickButtonContains("Home");
	
	Thread.sleep(15000);
	PEOPage.queue.click(); Thread.sleep(15000);
	   //commonFunction.enterTextboxContains("FEIN","99-9901203");
	   commonFunction.screenShot("FeinSearch","Pass","feinSearch");
	   //commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
		commonFunction.screenShot("Obtain Bond Task","Pass","Obtain Bond Task is displayed");
		Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		commonFunction.screenShot("EEWI-002","Pass","review emp type  ");
		//commonFunction.selectDropdown("*Account Status ", "Liable");
		commonFunction.screenShot("EEWI-002","Pass","review emp type  ");
		//commonFunction.enterTextboxContains("Comment", "registration in process");
		commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
		commonFunction.screenShot("GeneralInfo","Pass","General Information");
		commonFunction.clickButtonContains("Home");
		
		
		Thread.sleep(15000);
		   PEOPage.queue.click(); Thread.sleep(15000);
		   //commonFunction.enterTextboxContains("FEIN","99-9901203");
		   commonFunction.screenShot("FeinSearch","Pass","feinSearch");
		   //commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
			commonFunction.screenShot("Unable to Determine Liability Task","Pass","Unable to Determine Liability Taskis displayed");
			Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
			Thread.sleep(2000);
			commonFunction.screenShot("EEWI-002","Pass","review emp type  ");
			//commonFunction.selectDropdown("*Account Status ", "Liable");
			commonFunction.screenShot("EEWI-002","Pass","review emp type  ");
			//commonFunction.enterTextboxContains("Comment", "registration in process");
			commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
			commonFunction.screenShot("GeneralInfo","Pass","General Information");
			
			commonFunction.clickButtonContains("Home");
	
			commonFunction.clickMenu("menu");
			commonFunction.ScrollMenu("Inquiry");
			commonFunction.clickMenu("Inquiry");
			commonFunction.clickMenu("Contribution Inquiry");
			commonFunction.screenShot("Menu", "Pass", "Inquiry Employer Account");
			sleep(2000);
			commonFunction.screenShot("ERM4", "Pass", "Inquiry Employer Account Profile Changes page is displayed");
			commonFunction.enterTextboxContains("Employer Registration Number", "");
			commonFunction.clickButton("Continue ");
			sleep(5000);
			commonFunction.screenShot("SREG-051", "Pass", "Inquiry Employer Account Information");
			sleep(3000);
			commonFunction.clickButtonContains("Previous ");
			sleep(3000);
			commonFunction.clickButtonContains(" Home ");
			commonFunction.screenShot("Home", "Pass", "Home page is displayed");
	
}
}
