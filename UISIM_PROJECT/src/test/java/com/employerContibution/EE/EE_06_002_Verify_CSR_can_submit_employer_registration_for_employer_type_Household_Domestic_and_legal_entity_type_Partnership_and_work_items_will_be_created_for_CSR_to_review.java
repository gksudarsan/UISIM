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

public class EE_06_002_Verify_CSR_can_submit_employer_registration_for_employer_type_Household_Domestic_and_legal_entity_type_Partnership_and_work_items_will_be_created_for_CSR_to_review extends TestBase {
	@Test()
	public void EE_06_002_Verify_CSR_can_submit_employer_registration_for_employer_type_Household_Domestic_and_legal_entity_type_Partnership_and_work_items_will_be_created_for_CSR_to_review() throws Exception {
		// TODO Auto-generated constructor stub
		commonStepDefinitions commonFunction = new commonStepDefinitions();	
		 //String EntityName = prop.getProperty("Entity");
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		
		test = report.createTest("EE_06_002_Verify_CSR_can_submit_employer_registration_for_employer_type_Household_Domestic_and_legal_entity_type_Partnership_and_work_items_will_be_created_for_CSR_to_review");

		test.log(Status.INFO, "Logging to the application");
		
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");		
		commonFunction.clickMenu("menu"); 
		commonFunction.ScrollMenu("Employer Registration");sleep();
		
	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.clickMenu("Employer Registration");sleep(2000);
	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.clickMenu("Register Employer"); sleep(3000);
	commonFunction.screenShot("Page1", "Pass", "Employer Registration");
	commonFunction.clickButtonContains("Continue"); sleep(6000);
	
	commonFunction.selectDropdown("Employer Type", " Household/Domestic ");
	commonFunction.enterTextboxContains("(FEIN)", "95-9970908"); 
	commonFunction.screenShot("file1","Pass", "Searching with FEIN "); 
	commonFunction.selectDropdown("*Type of Legal Entity"," Trust "); 
	commonFunction.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
	commonFunction.selectDropdown("Source Type", " NYS-100 ");sleep(2000);
	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.clickButtonContains("Continue");sleep(2000);

	commonFunction.populateListbox("Legal Name", "RD 920");sleep(2000);
	commonFunction.screenShot("Menu", "Pass", "Employer Registration");
	commonFunction.selectRadioQuestions("Will you withhold New York State Income Tax from these employees?", "No ");
	commonFunction.screenShot("SREG003", "Pass", "Legal name page displayed");
	commonFunction.clickButtonContains("Continue");sleep(6000);
	
	commonFunction.enterTextboxContains("Address Line 1","7th Street 40 E 7th St");sleep(2000);
	commonFunction.enterTextboxContains("Address Line 2","");
    commonFunction.enterTextboxContains("City","New York");sleep(2000);
    commonFunction.enterTextboxContains("Zip Code","10003");sleep(2000);
	commonFunction.screenShot("SREG008", "Pass", "Add primary physical address is displayed");		 
	commonFunction.clickButtonContains("Continue ");sleep(6000);
	
	commonFunction.screenShot("SREG007", "Pass", "Business Physical Address Details is displayed");	
	
	commonFunction.clickButtonContains("Continue ");sleep(6000);
	
	commonFunction.selectRadio("Same as Primary Business Physical Address");sleep();
	commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
	commonFunction.screenShot("SREG004", "Pass", "Employer Contact Details is displayed");	
	commonFunction.clickButtonContains("Continue ");sleep(6000);
	
	commonFunction.screenShot("SREG-521", "Pass", "Employer Verify Contact Details is displayed");	
	commonFunction.clickButtonContains("Continue ");sleep(6000);
	
	commonFunction.selectRadioQuestions("Have you changed legal entity?", "Yes ");
	commonFunction.screenShot("Change in Legal Entity", "Pass", "Change in Legal Entity(SREG-713");
	commonFunction.clickButtonContains("Continue");
	sleep(2000);
	commonFunction.screenShot("Add executor", "Pass", "");
	commonFunction.enterTextboxContains("First Name", "Test");
	commonFunction.enterTextboxContains("Last Name", "AutoTest");
	commonFunction.enterTextboxContains("Address Line 1", "Ave"+ commonFunction.createRandomInteger(10,99));
	commonFunction.enterTextboxContains("City","NY");
	commonFunction.enterTextboxContains("Zip Code","13429");
	commonFunction.screenShot("Add Corporate Officer/Owner", "Pass", "Add Corporate Officer/Owner(SREG-006)");
	commonFunction.clickButtonContains("Continue ");
	sleep(5000);
	commonFunction.screenShot("SREG-005", "Pass", "Sole Proprietorship Details");
	commonFunction.clickButtonContains("Continue ");
	sleep(5000);
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
	sleep(20000);
	commonFunction.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
	sleep(2000);
	commonFunction.clickButtonContains("Home");
	
	commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='99-9971203' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);
    PEOPage.queue.click(); Thread.sleep(15000);
    //commonFunction.enterTextboxContains("FEIN","99-9901203");
    commonFunction.screenShot("FeinSearch","Pass","feinSearch");
   // commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
	commonFunction.screenShot("DOL DTF TASK","Pass","DOL DTF TASK is displayed");
	Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
	Thread.sleep(2000);
	commonFunction.screenShot("Page1","Pass","DOL DTF TASK");
	//commonFunction.selectDropdown("*Account Status ", "Liable");
	commonFunction.screenShot("Page2","Pass","DOL DTF TASK");
	//commonFunction.selectDropdown("*Account Status ", "Liable");
	//commonFunction.enterTextboxContains("Comment", "registration in process");
	commonFunction.clickButtonContains("Submit "); Thread.sleep(10000);
	commonFunction.screenShot("GeneralInfo","Pass","General Information");
	commonFunction.clickButtonContains("Home");
	
	
	 PEOPage.queue.click(); Thread.sleep(15000);
	    //commonFunction.enterTextboxContains("FEIN","99-9901203");
	    commonFunction.screenShot("FeinSearch","Pass","feinSearch");
	    //commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
		commonFunction.screenShot("Validate total failed rules","Pass","Validate total failed rules");
		Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		commonFunction.screenShot("SCR1","Pass","Validate total failed rules");
		//commonFunction.selectDropdown("*Account Status ", "Liable");
		commonFunction.screenShot("SCR12","Pass","Validate total failed rules ");
		//commonFunction.selectDropdown("*Account Status ", "Liable");
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
