package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_03_001_csr_registration_emptype_business extends TestBase{

	@Test()
	public void EE_03_001_csr_registration() throws Exception {


		String feinValue1 =StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		//String feinValue2 =  "9999"  ;
		//String feinValue = feinValue2 + feinValue1 ;  
		System.out.println("FEIN NUMBER = " +feinValue1);
		employerManagementLocators eml = new employerManagementLocators();
		commonStepDefinitions cf = new commonStepDefinitions();
		employerManagement em =  new employerManagement();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		//Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
		//		"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOGR'", "FEIN");
		//String FEIN = databaseResults.get("FEIN");
		test = report.createTest("EE_03_001 -  Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review. ");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep(2000);
        cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");
		sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); 
		sleep();
		cf.clickButtonContains("Continue");
		sleep(2000);
		
        /*------General Information(SREG-025)-------*/
		
		cf.selectDropdown("Employer Type", " Governmental ");
		cf.enterTextboxContains("(FEIN)", feinValue1); 
		cf.screenShot("file1","Pass", "Searching with FEIN "); 
		cf.selectDropdown("*Type of Legal Entity"," City "); 
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		cf.selectDropdown("Source Type", " NYS-100G ");
		cf.screenShot("GeneralInformation", "Pass", "General Information:sreg-025");
		cf.clickButtonContains("Continue");
        sleep(2000);
        
        /*----Employer Entity Information(SREG-003)-------*/
        
		cf.enterRandomStringLegalName("Legal Name");
		cf.enterTextboxContains("Other commonly known name of entity", "ABCCOMTETS");
		cf.enterTextboxContains(" Business Phone Number  ",
				Long.toString(cf.createRandomInteger(10000000, 99999999))
						+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains(" Business Fax Number ",
				Long.toString(cf.createRandomInteger(10000000, 99999999))
						+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterPastDate("Date covered employment began?", 365);
		cf.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information(SREG-003)");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*----Add Primary Business Physical Address(SREG-008)-------*/
		
		cf.enterTextboxContains("Address Line 1","243 Madison ave");
		cf.enterTextboxContains("City","NY");
		cf.enterTextboxContains("Zip Code","10025");
		cf.clickButtonContains("Continue");
		sleep(2000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			sleep();
			AddPage.continueButton_popUp.click();
		}catch(Exception e) {
			System.out.println("Business Physical Address Details");
		}
		sleep();
		/*----Add Primary Business Physical Address----*/
		cf.screenShot("AddPrimaryBusinesPhysicalAddress", "Pass", "Add Primary Busines Physical Address");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*---Employer Contact Details---*/
		cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		cf.screenShot("EmployerContactDetais", "Pass", "Employer Contact Details");
		cf.clickButtonContains("Continue");
		sleep(2000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			sleep();
			AddPage.continueButton_popUp.click();
		}catch(Exception e) {
			System.out.println("Employer Contact Details");
		}
		sleep();
		cf.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*--------Upload Documents----*/
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("TESTINGEL");
		sleep(3000);
		cf.screenShot("UploadDocuments", "Pass", "Upload Documents(SREG-683)");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details(SREG-800)");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.selectCheckbox("I accept");
		cf.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		cf.clickButtonContains("Submit");
		sleep(5000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		cf.clickButtonContains("Home");
		
		//Assigning user to WI Review emp type..................
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue1+"' ORDER BY UPDATED_TS desc)"); 
		sleep(2000);
	
		//Resolving WI Review emp type................
		PEOPage.queue.click(); 
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN",feinValue1);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("Review emp type","Pass","emp type");
		cf.clickOnLink("Review Employer Type");
		sleep(); 
		cf.clickButtonContains("Open Work Item");
		sleep(2000);
		cf.screenShot("Review","Pass","Review Employer Type Task Details");
		cf.enterFutureDate("Date Covered Employment began? ", 10);
		sleep();
		AddPage.commentField.sendKeys("registration  in progress");
		sleep();
		cf.clickButtonContains("Submit"); 
		sleep(2000);
		cf.screenShot("GeneralInfo","Pass","General Information");
		cf.clickButtonContains("Home");

		//Assigning user to WI Determine Liability Task.................
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue1+"' ORDER BY UPDATED_TS desc)");
	    sleep(2000);

		//Resolving WI Determine Liability Task.................
		PEOPage.queue.click();
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN",feinValue1);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search");
		cf.screenShot("Search","Pass","Searchbyname");
	    sleep(2000);
		cf.screenShot("Determine Liability Task","Fail","Determine Liability Task");
		cf.clickOnLink("Unable to Determine Liability Task");
		sleep(2000);
		cf.clickButtonContains("Open Work Item");
		sleep(2000);
		cf.screenShot("Review","Pass","Unable to Determine Liability Task");
		cf.selectDropdown("Account Status", "Liable");		
		cf.selectRadio("Contributory");
		cf.enterTextboxContains("Date covered employment began? ", "1212022");
		cf.populateListbox("Comment", "testing");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickButtonContains("Home");

		//Verify Registered employer in Inquery page 	...........
		em.Inquery_fein(feinValue1);
		test.log(Status.PASS, "Clicked on Home button");




	}
}