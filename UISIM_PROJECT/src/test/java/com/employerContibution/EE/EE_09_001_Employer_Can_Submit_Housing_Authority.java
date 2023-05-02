package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_09_001_Employer_Can_Submit_Housing_Authority extends TestBase{
	
	@Test
	public void EE_09_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		test = report
				.createTest("EE.09.002 Verify employer can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Business' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_MANJU.toUpperCase(), COMMON_CONSTANT.EMPLOYER_PASS_MANJU);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep();
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Willam");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Email Address", "test@Test.com");
		sleep();
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-025--------------*/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Indian Tribe ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Business ");
		/*---------------FEIN--------------*/
		Map<String, String> feinOutput = commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd EXCEPT SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea", "FEIN");
		String FEIN = feinOutput.get("FEIN");
		System.out.println(FEIN);
		test.log(Status.INFO, "FEIN : : "+FEIN);
		/*---------------FEIN--------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-003--------------*/
		
		
		
		/*---------------Legal Name--------------*/
		//SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT  IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
		String legalName="GLOBAL CONCRETE CORP";
		/*---------------Legal Name--------------*/
		
		
		
		
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?", 270);
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly owned by a federally recognized Indian Tribe?", "Yes ");
		sleep();
		commonFuntions.enterTextboxContains("Enter the name of the federally recognized Indian Tribe.", "Tester");
		commonFuntions.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Reimbursable");
		commonFuntions.selectRadioQuestions("If Reimbursable, has a copy of the 501c3 exemption documentation been provided?", "No ");
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals working in covered employment", "25");
		commonFuntions.enterPastDate("Date covered employment began? ", 1825);
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-008--------------*/
		
		
		sleep(4000);
		commonFuntions.screenShot("TPRRegister6", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		
		/*---------------SREG-007--------------*/
		commonFuntions.screenShot("Register111", "PASS", "Navigated to SREG-007 page after adding the address");
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-004--------------*/
		
		sleep(3000);
		commonFuntions.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		commonFuntions.enterTextboxContains("Address Line 1 ", "New Address 1");
		commonFuntions.enterTextboxContains("City ", "New York");
		commonFuntions.enterTextboxContains("Zip Code", "34276");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form1);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		sleep();
		commonFuntions.screenShot("TPRRegister8", "Pass", "Entered the address for Business Mailing Address");
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		sleep();
		empPage.location_Of_Book_AddresLine1.sendKeys("Fake Addres 2");
		empPage.location_Of_Book_City.sendKeys("Albany");
		empPage.location_Of_Book_ZipCode.sendKeys("34531");
		commonFuntions.safeJavaScriptClick(empPage.location_Of_Book_County);
		sleep();
		empPage.countyValue_Form1.click();
		commonFuntions.enterTextbox("First Name", "Abhi");
		commonFuntions.enterTextbox("Last Name", "Jan");
		sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Entered the address for Location of Books and Records");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		
		empPage.notice_potential_AddressLine_1.sendKeys("New Address 3");
		empPage.notice_potential_City.sendKeys("New York");
		empPage.notice_potential_Zipcode.sendKeys("34784");
		commonFuntions.safeJavaScriptClick(empPage.notice_potential_county);
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		
		empPage.notice_potential_firstName.sendKeys("Abhi1");
		empPage.notice_potential_LastName.sendKeys("JanNew");
		sleep();
		commonFuntions.enterTextboxContains(" Telephone Number ", "3426453789");
		commonFuntions.screenShot("TPRRegister10", "Pass", "Entered the address for Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-521--------------*/
		sleep();
		commonFuntions.screenShot("TPRRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-800----------------*/
		sleep(5000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButton("Submit ");
		sleep(2500);
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(20000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextbox("FEIN", FEIN);
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		empPage.review_employer_My_Q.click();
		sleep(3000);
		
		/*-----------------WF-091----------------*/
		
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(3000);
		
		/*-----------------EEWl-002----------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		empPage.commentBox_MyQ.sendKeys("test QA ops");
		commonFuntions.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		commonFuntions.screenShot("EmpRegister19", "Pass", "Employer review work item submitted");
		commonFuntions.clickButton("Home ");
		
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(25000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextbox("FEIN", FEIN);
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep(2000);
		empPage.obtain_bond_task_My_Q.click();
		sleep(3000);
		
		/*-----------------WF-091----------------*/
		
		commonFuntions.screenShot("EmpRegister122", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(3000);
		
		/*----------------SREG-803----------------*/
		commonFuntions.screenShot("EmpRegister123", "Pass", "Navigated to SREG-803 page and entering the details");
		commonFuntions.enterPastDate("Liability Date", 60);
		empPage.commentBox_MyQ.sendKeys("test QA ops");
		commonFuntions.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		commonFuntions.screenShot("EmpRegister19", "Pass", "Obtain Bond work item submitted");
		commonFuntions.clickButton("Home ");
		
	}
}
