package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class EE_06_003_Household_Domestic_Estate extends TestBase{

	@Test()
	public void EE_01_004_csr_registration() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();	
		String feinValue1 =StringUtils.left( String.valueOf((long)
				(Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
				FEIN = feinValue2 + feinValue1 ; 
				System.out.println("FEIN NUMBER = " +FEIN);

				//Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				//		"SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea  WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ) GROUP BY FEIN HAVING COUNT(*)>1 " , "FEIN"); 
				//	String FEIN = databaseResults1.get("FEIN");
				//System.out.println("FEIN NUMBER  = " +FEIN);
				String EntityName = prop.getProperty("Entity");
				employerManagement em =  new employerManagement();
				EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
				PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
				AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
				test = report.createTest("EE.06.003:Verify CSR can submit employer registration for employer type 'Household/Domestic' and legal entity type 'Estate' and work items will be created for CSR to review.");

				cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
				cf.screenShot("ApplicationLogin", "Pass", "Login is successful");	
				sleep(2000);
				cf.waitForLoadingIconToDisappear();
				cf.clickMenu("Menu"); sleep();
				cf.ScrollMenu("Employer Registration");sleep();
				cf.screenShot("Menu", "Pass", "Employer Registration");
				cf.clickMenu("Employer Registration");sleep(2000);
				cf.screenShot("Menu", "Pass", "Employer Registration");
				cf.clickMenu("Register Employer"); sleep(2000);
				cf.clickButtonContains("Continue"); sleep(2000);

				cf.selectDropdown("Employer Type", "  Household/Domestic ");
				cf.enterTextboxContains("(FEIN)", FEIN); 
				cf.screenShot("file1","Pass", "Searching with FEIN "); 
				cf.selectDropdown("Type of Legal Entity"," Estate "); 
				cf.selectDropdown("Source", " NYS-100 (paper) ");sleep();
				cf.selectDropdown("Source Type", " NYS-100 ");sleep();
				cf.screenShot("Menu", "Pass", "Employer Registration");
				cf.clickButtonContains("Continue");
				sleep(2000);

				cf.screenShot("Menu", "Pass", "Employer Registration");
				/*---Household/Domestic Employers(SREG-003)------*/
				cf.enterRandomStringLegalName("Legal Name");
				cf.selectDropdown("Quarter", "4");
				sleep();
				cf.selectDropdown("Year", "2022");
				sleep();
				cf.selectRadioQuestions("Will you withhold New York State Income Tax from these employees?", "No");
				cf.clickButtonContains("Continue");
				sleep(2000);
				/*---Add Primary Business Physical Address(SREG-008)---*/
				cf.enterTextboxContains("Address Line 1","223 Madison ave");
				cf.enterTextboxContains("City","albany");
				cf.enterTextboxContains("Zip Code","12203");
				cf.selectDropdown("County", " Albany ");
				sleep();
				cf.clickButtonContains("Continue");
				sleep(2000);
				try {
					cf.safeJavaScriptClick(AddPage.uspsAddress);
					sleep();
					AddPage.continueButton_popUp.click();
				}catch(Exception e) {
					System.out.println("Business Physical Address Details");
				}
				sleep(2000);
				cf.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details");
				cf.clickButtonContains("Continue");
				sleep(2000);
				
				/*-----Employer Contact Details(SREG-004)----*/
				cf.selectRadioQuestions("Business Mailing Address", "Other");
				sleep();
				//cf.selectRadioQuestions("Location of Books and Records", "Same as");
				//empPage.addressLine1_Form2.sendKeys("street32");
				//sleep();
				//empPage.city_Form2.sendKeys("Albany");
				//sleep();
				//empPage.zipCode_Form2.sendKeys("45678");
				//sleep();
				//empPage.countyDropDown_Form2.click();
				//empPage.countyValue_Form2.click();
				AddPage.addressLine1_Form1.sendKeys("224 Madison ave");
				sleep();
				AddPage.city_Form1.sendKeys("albany");
				sleep();
				AddPage.zipCode_Form1.sendKeys("12233");
				sleep();
				AddPage.countyDropdown1.click();
				sleep();
				AddPage.countyValue1.click();
				cf.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details");
				cf.clickButtonContains("Continue");
				sleep(2000);
				try {
					cf.safeJavaScriptClick(AddPage.uspsAddress);
					sleep();
					AddPage.continueButton_popUp.click();
				}catch(Exception e) {
					System.out.println("Employer Contact Details");
				}
				sleep(2000);
				cf.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Contact Details:SREG-521");
				cf.clickButtonContains("Continue");
				sleep(2000);
				
				/*----- Change in legal entity-------*/
				cf.screenShot("NavigatingToChangeInLegalEntity", "Pass", "Change in legal entity");
				sleep();
				cf.selectRadioQuestions("Have you changed legal entity?", "Yes ");
				sleep();
				cf.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "525262525");
				cf.enterTextboxContains("Prior Employer Registration Number", "0000640");
				cf.enterPastDate("Date of Legal Entity change", 270);
				cf.enterCurrentDate("Date of Notification");
				cf.screenShot("ChangeinLegalEntity", "Pass", "Change in legal entity filled data");
				cf.clickButtonContains("Continue");
				sleep(2000);
				
				/*----  Add Executor/Owner Details  -------*/
				cf.screenShot("Addexecutor", "Pass", "Add Executor Details without details");
				cf.enterTextboxContains("First Name", "Test");
				cf.enterTextboxContains("Last Name", "AutoTest");
				cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(100,999)+ "Madison ave");
				cf.enterTextboxContains("City","albany");
				cf.enterTextboxContains("Zip Code","12203");
				cf.screenShot("AddExecutor/OwnerDetails", "Pass", "Add Executor/Owner Details(SREG-006)");
				cf.clickButtonContains("Continue");
				sleep(2000);
				try {
					cf.safeJavaScriptClick(AddPage.uspsAddress);
					sleep();
					cf.screenShot("uspsAddress", "Pass", "Usps Address PopUp");
					AddPage.continueButton_popUp.click();
				}catch(Exception e) {
					System.out.println("Executor/Owner Details");
				}
				sleep(2000);	
				cf.screenShot("Executor/Owner Details", "Pass", "Executor/Owner Details(SREG-005)");
				cf.clickButtonContains("Continue");
				sleep(2000);
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

				cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

				//Resolving 1 WI................
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

				//cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

				//Resolving 2ND WI ................
//				PEOPage.queue.click();
//				sleep();
//				cf.waitForLoadingIconToDisappear();
//				cf.enterTextboxContains("FEIN",FEIN);
//				cf.screenShot("FeinSearch","Pass","feinSearch");
//				cf.clickButtonContains("Search"); 
//				sleep(2000);
//				cf.screenShot("DOL DTF Discrepancy","Pass","emp type");
//				cf.clickOnLink("DOL DTF Discrepancy");
//				sleep(2000); 
//				cf.clickButtonContains("Open Work Item");
//				sleep(2000);
//				cf.screenShot("","Pass","DOL DTF ");
//				cf.selectDropdown("Quarter", "1");sleep();
//				cf.selectDropdown("Year", "2023");sleep();
//				cf.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
//				cf.selectDropdown("*Account Status ", "Liable");
//				cf.enterTextboxContains("Comment", "registration in process");
//				cf.clickButtonContains("Submit"); 
//				sleep(2000);
//				cf.screenShot("GeneralInfo","Pass","General Information");
//				cf.clickButtonContains("Home");

				//Verify Registered employer in Inquery page 	...........
				//em.Inquery_fein(FEIN);
				//test.log(Status.PASS, "Clicked on Home button");




	}
}