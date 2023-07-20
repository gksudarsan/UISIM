package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_09_004_CSR_Can_Submit_Indian_Tribe_Other extends TestBase{
	
	@Test
	public void EE_09_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		test = report
				.createTest("EE.09.004 -Verify employer can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Other' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(2000);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Willam");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Contact Telephone Number", "3028054576");
		commonFuntions.enterTextboxContains("Email Address", "test@Test.com");
		sleep();
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		/*---------------SREG-025--------------*/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		
		commonFuntions.selectDropdownUsingSearch("Employer Type", " Indian Tribe ");
		commonFuntions.selectDropdownUsingSearch("Type of Legal Entity", " Other ");
		/*---------------FEIN--------------*/
		String feinValue = "936495581"; // Fein from 09.003 testcase StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		
		/*---------------FEIN--------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "test");
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		/*---------------SREG-003--------------*/
		
		
		
		/*---------------Legal Name--------------*/
//		SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
		String legalName="NORTON LAW GROUP PLLC";
		/*---------------Legal Name--------------*/
		
		
		
		empPage.legalNameTextBox.clear();
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterPastDate("What is the date of the first payroll which", 545);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.are_You_Subsidiary_Yes);
//		commonFuntions.enterTextboxContains("Are you a subdivision, subsidiary or business", "Yes ");
		commonFuntions.enterTextboxContains("Enter the name of the federally recogni", "Test Tribe");
		commonFuntions.selectRadioQuestions("Financing Method","Contributory");
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals working", "73");
		commonFuntions.enterPastDate("Date covered employment began", 365);
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-004--------------*/
		sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains(legalName, feinValue);
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");		
		sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.safeJavaScriptClick(empPage.verifyAddressUspsAddress);
		commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		sleep(2000);
		
		sleep(2000);
		commonFuntions.screenShot("EmpRegister6", "Pass", "Business Physical Address Details");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		commonFuntions.enterTextboxContains("Address Line 1 ", "22 asdas asd");
		commonFuntions.enterTextboxContains("City ", "New York");
		commonFuntions.enterTextboxContains("Zip Code", "45435");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form1);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		sleep(2000);
		commonFuntions.screenShot("TPRRegister8", "Pass", "Entered the address for Business Mailing Address");
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		try {
		
		commonFuntions.enterTextbox("First Name", "Abhi");
		commonFuntions.enterTextbox("Last Name", "Jan");
		sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Entered the address for Location of Books and Records");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		
		empPage.notice_potential_AddressLine_1.sendKeys("25 sres sts");
		empPage.notice_potential_City.sendKeys("New York");
		empPage.notice_potential_Zipcode.sendKeys("45435");
		commonFuntions.safeJavaScriptClick(empPage.notice_potential_county);
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		
		empPage.notice_potential_firstName.sendKeys("Abhi1");
		empPage.notice_potential_LastName.sendKeys("JanNew");
		sleep();
		commonFuntions.enterTextboxContains(" Telephone Number ", "3426453789");
		}catch(Exception e) {}
		commonFuntions.screenShot("TPRRegister10", "Pass", "Entered the address for Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButton("Continue ");
		sleep();
		try {
		sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.safeJavaScriptClick(empPage.verifyAddressUspsAddress);
		commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {}
		
		commonFuntions.screenShot("TPRRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("UploadDocs", "Pass", "Upload Documents");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-800----------------*/
		sleep(5000);
		commonFuntions.screenShot("Review", "Pass", "Review");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("Acknowledgement", "Pass", "Statement Of Acknowledgement");
		commonFuntions.selectCheckbox("I accept");
		//commonFuntions.populateListbox("Submitter Comments may be entered below.", "Auto Test");		
		sleep();
		commonFuntions.clickButton("Submit ");
		sleep(2500);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Success", "Pass", "Success Message");
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(20000);
		//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		
		sleep(5000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	    
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
	    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
	    commonFuntions.clickButtonContains("Search");
	    sleep(2000);
	    commonFuntions.screenShot("Review Employer TYpe","Pass","Review Employer Type");
	    commonFuntions.clickOnLink("Review Employer Type");
	    sleep(2000);
		
		/*-----------------WF-091----------------*/
		
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(3000);
		
		/*-----------------EEWl-002----------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		commonFuntions.enterTextboxContains("Date Covered Employment began?", "06/01/2023");
		commonFuntions.selectRadioQuestions("Financing Method","Contributory");
		empPage.commentBox_MyQ.sendKeys("test QA ops");
		commonFuntions.clickButton("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		/*-----------------SUC-002----------------*/
		commonFuntions.screenShot("EmpRegister19", "Pass", "Employer review work item submitted");
		commonFuntions.clickButton("Home ");
		
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(5000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
	    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
	    commonFuntions.clickButtonContains("Search");
	    sleep(2000);
	    commonFuntions.screenShot("DOLDTF","Pass","DOL DTF TASK");
	    //commonFuntions.clickOnLink("Review Employer Type");
	    sleep(2000);
	    commonFuntions.screenShot("EmpRegister122", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(2000);
		commonFuntions.populateListbox("Comment", "Auto Test");
		sleep(2000);
	    commonFuntions.clickButton("Submit ");
	    sleep(2000);
	    commonFuntions.waitForLoadingIconToDisappear();
	    commonFuntions.screenShot("EmpRegister19", "Pass", "DOL DTF work item submitted");
		commonFuntions.clickButton("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(5000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	    
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
	    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
	    commonFuntions.clickButtonContains("Search");
	    sleep(2000);
	    //commonFuntions.clickOnLink("Review Comments");
	    sleep(2000);
		
	    commonFuntions.screenShot("EmpRegister122", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(2000);
		commonFuntions.screenShot("PotentialDuplicates", "Pass", "Review Potential Duplicates");
		commonFuntions.populateListbox("Comment", "Auto Test");
		
		commonFuntions.clickButton("Submit ");
	    sleep(2000);
	    commonFuntions.waitForLoadingIconToDisappear();
	    commonFuntions.screenShot("EmpRegister19", "Pass", "Review Potential duplicates work item submitted");
		commonFuntions.clickButton("Home ");
	    
		
	}
}
