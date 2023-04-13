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
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagementLocators eml = new employerManagementLocators();
		
		employerManagement em =  new employerManagement();
		test = report.createTest("EE_01_002 -  Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Corporation (All types)' and work items will be created for CSR to review.\r\n"
				+ "");
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				 "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
				 , "FEIN"); String FEIN = databaseResults.get("FEIN");
				  System.out.println("FEIN NUMBER = " +FEIN);
				 
				  Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				 "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
				 , "EAN"); String EAN = databaseResults1.get("EAN");
				  System.out.println("EAN NUMBER = " +EAN);
		
		
		System.out.println("FEIN NUMBER = " +FEIN);
		
		

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

		cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); sleep(3000);
		cf.clickButtonContains("Continue"); sleep(2000);

		cf.selectDropdown("Employer Type", " Business ");
		cf.enterTextboxContains("(FEIN)", "546237282"); 
		cf.screenShot("file1","Pass", "Searching with FEIN "); 
		cf.selectDropdown("*Type of Legal Entity"," Partnership "); 
		cf.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
		cf.selectDropdown("Source Type", " NYS-100 ");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");sleep(2000);

		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.populateListbox("Legal Name", "testing registration process");sleep(2000);
		cf.clickButtonContains("Continue");sleep();

		cf.enterTextboxContains("Address Line 1","7th Street 40 E 7th St");sleep(2000);
		cf.enterTextboxContains("Address Line 2","");
		cf.enterTextboxContains("City","New York");sleep(2000);
		cf.enterTextboxContains("Zip Code","10003");sleep(2000);
		
		//cf.safeJavaScriptClick(empPage.uspsAddressRadio);
		sleep();
		cf.screenShot("", "Pass", "Employer Registration");
		cf.clickButtonContains("Finish Later");sleep(2000);
		cf.clickButtonContains("Yes");sleep(2000);
		cf.clickButtonContains("Home");sleep(2000);

		//-- entering data in incomplete registration.............

		cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Incomplete Registration"); sleep(3000);

		cf.enterTextbox("FEIN", FEIN);sleep();
		cf.clickButtonContains("Search");sleep(2000);
		cf.clickOnLink("");sleep(2000);
		cf.clickButtonContains("Continue");sleep(2000);

		cf.clickButtonContains("Continue");sleep(2000);

		cf.clickButtonContains("Continue");sleep(2000);

		//cf.clickButtonContains("Continue");
		cf.screenShot("", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");sleep();sleep(2000);

		cf.selectRadio("Same as Primary Business Physical Address");
		eml.selectradio_locationofbooks().click();sleep(2000);
		eml.selectradio_noticeofpotentialcharges().click();sleep(2000);
		cf.clickButtonContains("Continue"); sleep(2000);
		cf.clickButtonContains("Continue");sleep(2000);
		
		
		cf.screenShot("Bussiness Aquisition", "Pass", "Bussiness Aquisition(SREG-011)");
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "No ");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("Change in Legal Entity", "Pass", "Change in Legal Entity(SREG-012");
		cf.selectRadioQuestions("Have you changed legal entity?", "No ");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		cf.enterTextboxContains("First Name", "Test");
		cf.enterTextboxContains("Last Name", "AutoTest");
		cf.enterTextboxContains("Address Line 1", "Ave"+ cf.createRandomInteger(10,99));
		cf.enterTextboxContains("City","NY");
		cf.enterTextboxContains("Zip Code","13429");
		cf.screenShot("Add Corporate Officer/Owner", "Pass", "Add Corporate Officer/Owner(SREG-006)");
		cf.clickButtonContains("Continue");
		sleep(2000);
		try {
			PEOPage.uspsAdd.click();
			cf.screenShot("UspsAddress","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("usps pop up dispalyed");
		}
		sleep(2000);
		cf.screenShot("Corporate Officer/Owner Details", "Pass", "Corporate Officer/Owner Details(SREG-005))");
		cf.clickButtonContains("Continue");
		sleep(2000);
	    cf.selectLink("Proof of NYS Workers", "Browse");
	    sleep(3000);
	    cf.screenShot("Upload Documents", "Pass", "Upload Documents(SREG-683)");
		cf.clickButtonContains("Continue");
		sleep(5000);
		cf.screenShot("Review Registration Details", "Pass", "Review Registration Details(SREG-800)");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.selectCheckbox("I accept");
		cf.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		cf.clickButtonContains("Exit");

		//Assigning user to WI Review emp type..................
				cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);
			
				//Resolving WI Review emp type................
				PEOPage.queue.click(); Thread.sleep(15000);
				cf.enterTextboxContains("FEIN",FEIN);
				cf.screenShot("FeinSearch","Pass","feinSearch");
				cf.clickButtonContains("Search"); Thread.sleep(2000);
				cf.screenShot("Review emp type","Pass","emp type");
				cf.clickOnLink("Review potential Duplicates");

				Thread.sleep(2000); cf.clickButtonContains("Open Work Item");
				Thread.sleep(2000);
				cf.screenShot("Review","Pass","Review Employer Type Task Details");
				cf.enterTextboxContains("Comment", "registration in process");
				cf.clickButtonContains("Submit"); Thread.sleep(2000);
				cf.screenShot("GeneralInfo","Pass","General Information");
				cf.clickButtonContains("Home");
				
				cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);
				
				//Resolving WI Review emp type................
				PEOPage.queue.click(); Thread.sleep(15000);
				cf.enterTextboxContains("FEIN",FEIN);
				cf.screenShot("FeinSearch","Pass","feinSearch");
				cf.clickButtonContains("Search"); Thread.sleep(2000);
				cf.screenShot("Review emp type","Pass","emp type");
				cf.clickOnLink("Verify Agent Rep Task");sleep();
				
				//failed got system failure.......................

				/*
				 * Thread.sleep(2000); cf.clickButtonContains("Open Work Item");
				 * Thread.sleep(2000);
				 * cf.screenShot("Review","Pass","Review Employer Type Task Details");
				 * cf.enterTextboxContains("Comment", "registration in process");
				 * cf.clickButtonContains("Submit"); Thread.sleep(2000);
				 * cf.screenShot("GeneralInfo","Pass","General Information");
				 * cf.clickButtonContains("Home");
				 */
				
				em.Inquery_fein(FEIN);
				
				
				
		test.log(Status.PASS, "Clicked on Home button");
		



	}
}