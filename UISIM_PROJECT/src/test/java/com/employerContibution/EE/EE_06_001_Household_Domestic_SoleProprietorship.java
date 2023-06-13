package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_06_001_Household_Domestic_SoleProprietorship extends TestBase{

	@Test()
	public void EE_06_001_csr_registration() throws Exception {

<<<<<<< HEAD
		commonStepDefinitions cf = new commonStepDefinitions();	
=======
		commonStepDefinitions cf = new commonStepDefinitions();	/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea  WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ) GROUP BY FEIN HAVING COUNT(*)>1 " , "FEIN"); 
		String FEIN = databaseResults1.get("FEIN");
		System.out.println("FEIN = " +FEIN);
		test.log(Status.INFO, "FEIN : : "+FEIN);
		//String EntityName = prop.getProperty("Entity");
>>>>>>> refs/heads/master_13062023
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
//		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
//				"SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea  WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ) GROUP BY FEIN HAVING COUNT(*)>1 " , "FEIN"); 
//		String FEIN = databaseResults1.get("FEIN");
//		System.out.println("FEIN = " +FEIN);
		
		String FEIN = prop.getProperty("SingleFoundonDOLandMultipleFoundInDT_FFEIN");
		test = report.createTest("EE.06.001:Verify CSR can submit employer registration for employer type 'Household/Domestic' and legal entity type 'Sole Proprietorship (Individual)' and work items will be created for CSR to review.");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
<<<<<<< HEAD
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu"); 
		sleep();
		cf.ScrollMenu("Employer Registration");
=======
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");		
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");sleep();
>>>>>>> refs/heads/master_13062023
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");
		sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); 
		sleep();
		cf.clickButtonContains("Continue"); 
		sleep(2000);
		
		/*----------------General Information(SREG-025)-----------------*/
		
		cf.selectDropdown("Employer Type", " Household/Domestic ");
		cf.enterTextboxContains("(FEIN)", FEIN); 
		cf.screenShot("file1","Pass", "Searching with FEIN "); 
		cf.selectDropdown("*Type of Legal Entity"," Sole Proprietorship (Individual) "); 
		cf.selectDropdown("Source", " NYS-100 (paper) ");
	    sleep();
		cf.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		cf.screenShot("NavigatingToGeneralInformation", "Pass", "General Information");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*----------------Household/Domestic Employers (SREG-003)-----------------*/
		
		cf.enterRandomStringLegalName("Legal Name");
		cf.selectDropdown("Quarter", "4");
		sleep();
		cf.selectDropdown("Year", "2022");
		sleep();
		cf.selectRadioQuestions("Will you withhold New York State Income Tax from these employees?", "Yes");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*----------------Add Primary Business Physical Address(SREG-008)--------*/
		cf.enterTextboxContains("Address Line 1","234 Snider Road");
		cf.enterTextboxContains("City","albany");
		cf.enterTextboxContains("Zip Code","53243");
		cf.selectDropdown("County", " Albany ");
		sleep();
		cf.screenShot("AddPrimaryBusinessPhysicalAddress", "Pass", "Add Primary Business Physical Address");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details");
		cf.clickButtonContains("Continue");
		sleep(2000);
        
		/*----------------Employer Contact Details(SREG-004)--------*/
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		AddPage.addressLine1_Form1.sendKeys("325 Test Road");
		sleep();
		AddPage.city_Form1.sendKeys("albany");
		sleep();
		AddPage.zipCode_Form1.sendKeys("34568");
		sleep();
		AddPage.countyDropdown1.click();
		sleep();
		AddPage.countyValue1.click();
		sleep();
		cf.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		cf.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*-----------Employer Verify Contact Details(SREG-521)-------*/

		//cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		//eml.selectradio_locationofbooks().click();sleep(2000);
		//eml.selectradio_noticeofpotentialcharges().click();sleep(2000);
		cf.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		cf.clickButtonContains("Continue"); 
		sleep(2000);
		
		/*----- Change in legal entity-------*/
		cf.screenShot("NavigatingToChangeInLegalEntity", "Pass", "Change in legal entity");
		sleep();
		cf.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		sleep();
		cf.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "643523422");
		cf.enterTextboxContains("Prior Employer Registration Number", "9300009");
		cf.enterPastDate("Date of Legal Entity change", 180);
		cf.enterCurrentDate("Date of Notification");
		cf.screenShot("ChangeinLegalEntity", "Pass", "Change in legal entity filled data");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*-----Add Sole Proprietorship Details(SREG-006)-----*/
		cf.screenShot("AddSoleProprietorshipDetails", "Pass", "Add Sole Proprietorship Details without details");
		cf.enterTextboxContains("First Name", "Tjhon");
		cf.enterTextboxContains("Last Name", "abc");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(100,999)+ "Test Ave");
		cf.enterTextboxContains("City","albany");
		cf.enterTextboxContains("Zip Code","12203");
		cf.screenShot("AddSoleProprietorshipDetailsFilled", "Pass", "Add Sole Proprietorship Details(SREG-006)");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("Sole Proprietorship Details", "Pass", "Sole Proprietorship Details(SREG-005)");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*--------Upload Documents(SREG-683)-------*/
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
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");

		//Resolving 1 WI................
		PEOPage.queue.click(); 
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		//cf.selectDropdown("Work Item Description", " DOL-DTF Discrepancy task ");
		//cf.enterTextboxContains("FEIN",FEIN);
		cf.searchForworkItem(AddPage.searchByFilter);
		sleep(2000);
		cf.screenShot("DOLDTFDiscrepancytasksearch","Pass","DOL-DTF Discrepancy task search");
		cf.clickOnLink("DOL DTF Discrepancy");
		//cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("DOL/DTFDiscrepancytask","Pass","DOL-DTF Discrepancy task");
		sleep(); 
		cf.clickButtonContains("Open Work Item");
	    sleep(2000);
		cf.screenShot("DOL/DTFDiscrepancytaskPage","Pass","DOL/DTF Discrepancy task Page");
		sleep();
		cf.selectDropdown("Account Status", " Liable ");
		sleep();
		AddPage.comment.sendKeys("registration  in progress");
		sleep();
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("workitemCompleted","Pass","DolDtf work item completed");
		cf.clickButtonContains("Home");

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		sleep(2000);

		//Resolving 2ND WI ................
		PEOPage.queue.click();
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN",FEIN);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("VerifypredecessorData","Pass","Verify predecessor Data");
		cf.clickOnLink("VerifypredecessorData");
		sleep(2000); 
		cf.clickButtonContains("Open Work Item");
		sleep(2000);
		cf.screenShot("","Pass","Open Work Item");
		sleep();
		cf.forceClearText(AddPage.previousFein);
		sleep();
		cf.selectDropdown("Decision", " No Transfer ");
		sleep();
		AddPage.comment.sendKeys("registration  in progress");
		sleep();
		cf.clickButtonContains("Submit"); 
		sleep(2000);
		cf.screenShot("WorkItemCompleted","Pass","Verify predecessor Data Task");
		cf.clickButtonContains("Home");
		
		//Verify Registered employer in Inquery page 	...........
		//em.Inquery_fein(FEIN);
		//test.log(Status.PASS, "Clicked on Home button");


	}
}