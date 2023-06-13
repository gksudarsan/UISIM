package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_07_006_Emp_Register_EmpType_Governmental_Other extends TestBase {

	@Test
	public void EE_07_006() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = report.createTest(" EE_07_006 Verify employer can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");
		

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_5.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_5_PASSWORD);
		sleep(3000);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(3000);
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "FEIN");
		String FEIN = databaseResults.get("FEIN");
		System.out.println("FEIN NUMBER = " +FEIN);

		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "EAN");
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN NUMBER = " +EAN);
		
		//--- Menu Click ---
		commonFuntions.screenShot("Menu", "Pass", "Menu page");
		commonFuntions.clickMenu("Menu");
				sleep();
				commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
				sleep();
				commonFuntions.screenShot("Menu", "Pass", "Menu - Employer Registration - Register Employer");
				sleep();
				commonFuntions.clickMenu("Register Employer");
				sleep(2000);
		
		
				//-----------SREG-001 ---
				commonFuntions.screenShot("EmployerRegistraionPage", "Pass", "Launched at Employer Registration(SREG-001) page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
		
				//---------------SREG-025--------------*/
				
				commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
				sleep(2000);
				commonFuntions.selectDropdown("Employer Type", " Governmental ");
				sleep(2000);
				commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
				sleep(2000);
				commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "Test");
				sleep(2000);
				commonFuntions.screenShot("General Information", "Pass", "entered the details SREG-025 page ");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				/*---------------SREG-003--------------*/
				//---------------Legal Name--------------
				//SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT  IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
				String legalName="Random String FEIN Value";
				
				empPage.legalNameTextBox.sendKeys(legalName);
				commonFuntions.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(100,999)));
				sleep(2000);
				//commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?",);
				commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax", 180);
				sleep(2000);
				commonFuntions.enterTextboxContains("Estimated or approximate number of individuals working in covered employment", "56");
				//commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly", "Yes ");
				//sleep(2000);
				//commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
				sleep(2000);
				commonFuntions.enterPastDate("Date covered employment began? ", 180);
				sleep(2000);
				commonFuntions.selectRadioQuestions("Is your entity a legally established component or subdivision of another entity", "No ");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance ", "Contributory");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				/*-----------------SREG-008----------------*/
				commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entering the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("Address Line 1 ", "123HEC Zone");
				sleep(2000);
				commonFuntions.enterTextboxContains("City ", "NY");
				sleep(2000);
				commonFuntions.enterTextboxContains("Zip Code", "10002");
				sleep(2000);
				commonFuntions.selectDropdown("County", " Albany ");
				sleep(2000);
				commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entered the details");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				/*-----------------SREG-007----------------*/
				
				sleep(4000);
				
				commonFuntions.screenShot("EmpRegister6", "Pass", "Navigated to SREG-007 page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				

				
				
		
				/*
		
	
		cf.screenShot("EmpRegister1", "Pass", " Employer Register page");
		cf.clickButton("Continue ");
		sleep(3000);
		cf.screenShot("EmpRegister2", "Pass", "Emp Page");
		cf.selectDropdown("Employer Type", " Governmental ");
		sleep();

		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		cf.selectDropdown("Type of Legal Entity", " Other ");
		cf.enterTextbox("If Other, provide the type of Legal Entity.", "oshsh");
		cf.enterTextboxContains("Employer Registration Number", "");
		sleep(3000);
		cf.clickButton("Continue ");
		cf.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		//----------------SREG-003----------------
		cf.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("NO Entity Found");
		//cf.enterTextboxContains("Other commonly known", "HJGHHFH");
		cf.enterTextboxContains(" Business Phone Number  ", "3564777565");
		cf.enterTextboxContains(" Business Fax Number ", "9484735838");
		cf.enterTextboxContains("What is the date of the first payroll", "03032022");
		cf.enterTextboxContains("Estimated or approximate number of individuals", "20");
		cf.enterTextboxContains("Date covered employment began?", "03032022");
		cf.safeJavaScriptClick(empPage.iSyourEntityQuestion_No);
		sleep();
		cf.screenShot("EmpRegister5", "Pass", "Enter the details on SREG-003 page and click continue");
		//cf.enterTextboxContains("If Yes, enter Legal Name of Entity", "abc");
		//cf.enterTextboxContains("Address Line 1 ", "7th Street 40");
		//cf.enterTextboxContains("City ", "New York");
		//cf.enterTextboxContains("Zip Code", "44673");
		//cf.selectDropdown("County", " New York ");
		cf.clickButton("Continue ");


		sleep(4000);
		cf.screenShot("EmpRegister6", "Pass", "Navigated on SREG-008 page and entering the address");
		cf.enterTextboxContains("Address Line 1 ", "23 Plaza");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34737");
		cf.clickButton("Continue ");


		cf.screenShot("EmpRegister7", "Pass", "Navigated on SREG-007 and click continue");
		sleep(4000);
		cf.clickButton("Continue ");


		sleep(4000);
		cf.screenShot("EmpRegister8", "Pass", "Navigated on SREG-008 and enter Both the address ");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		empPage.addressLine1_Form1.sendKeys("Goddard Hall 80");
		empPage.city_Form1.sendKeys("New York");
		empPage.zipCode_Form1.sendKeys("10003");
		empPage.countyDropDown_Form1.click();
		empPage.countyValue_Form1.click();

		cf.selectRadioQuestions("Location of Books and Records", "Other");
		cf.screenShot("EmpRegister9", "Pass", "Selected other for - Location of Books and Records");
		sleep(2000);
		empPage.addressLine1_Form2.sendKeys("40 Park View");
		sleep();
		empPage.city_Form2.sendKeys("Albany");
		sleep();
		empPage.zipCode_Form2.sendKeys("28287");
		sleep();
		empPage.countyDropDown_Form2.click();
		empPage.countyValue_Form2.click();
		cf.clickButton("Continue ");
		sleep(4000);

		cf.safeJavaScriptClick(empPage.uspsAddressRadio);
		sleep();
		cf.screenShot("EmpRegister10", "Pass", "Pop Up displayed");
		cf.safeJavaScriptClick(empPage.continueButton_popUp);



		sleep(4000);
		cf.screenShot("EmpRegister11", "Pass", "Navigated on SREG-521 page");
		cf.clickButton("Continue ");


		sleep(4000);
		cf.screenShot("EmpRegister12", "Pass", "Navigated on SREG-683 page and upload document");
		empPage.browserLink.click();
		Thread.sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.clickButton("Continue ");


		sleep(10000);
		cf.screenShot("EmpRegister13", "Pass", "Navigated on SREG-800 and Accept");
		cf.clickButton("Continue ");
		cf.selectCheckbox("I accept");
		sleep();
		cf.screenShot("EmpRegister14", "Pass", "Click on submit button");
		cf.clickButtonContains("Submit ");
		sleep(15000);


		cf.screenShot("EmpRegister15", "Pass", "Navigated to SREG-521 page and click on exit");
		cf.clickButtonContains("Exit ");


		sleep(4000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		cf.screenShot("EmpRegister16", "Pass", "Navigated to Home Page and click on My-Q");
		PEOPage.queue.click();
		sleep(25000);


		sleep();
		cf.screenShot("EmpRegister17", "Pass", "Navigated to WF-001 page and open the work Item");
		cf.enterTextboxContains("FEIN", FEIN);
		sleep();
		cf.clickButtonContains(" Search ");
		sleep(4000);
		cf.clickOnLink("Review Employer Type");
		sleep(5000);
		cf.clickButtonContains("Open Work Item ");


		sleep(3000);
		cf.screenShot("EmpRegister18", "Pass",
				"Navigated to EEWI page by opening the Work Item and add a comment and click submit");
		empPage.commentBox_MyQ.sendKeys("Employer Tupe Gov");
		sleep(3000);
		cf.clickButtonContains("Submit ");


		sleep(3000);
		cf.screenShot("EmpRegister19", "Pass",
				"Sucessfully clicked on Submit button and navigated to SUC-002 Page");
		cf.clickButtonContains("Home ");
*/
		
		
	}
}
