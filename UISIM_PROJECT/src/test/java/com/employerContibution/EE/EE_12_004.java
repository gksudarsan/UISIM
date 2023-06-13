package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_12_004 extends TestBase {

	@Test( description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Town' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_12_004() throws Exception {
		employerManagement em =  new employerManagement();
		test = report.createTest(
				"erify TPR can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Other' and work items will be created for CSR to review.");

		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		

		
		// --- Login ---
		cf.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		cf.clickMenu("menu");
		// cf.clickMenu("Employer Registration");
		cf.clickMenu("Employer Registration");
		cf.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		cf.clickMenu("Register Employer");
		// cf.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(2000);
		cf.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");

		// --- SREG-001 ---
		cf.enterTextboxContains("First Name", "Antonio");
		cf.enterTextboxContains("Middle Initial", "S");
		cf.enterTextboxContains("Last Name", "Rodriguez");
		cf.selectDropdown("Suffix", " Sr. ");
		sleep(2000);
		cf.screenShot("EmpRegister2", "Pass", "Details entered on SREG-001 page");
		cf.enterTextboxContains("Job Title", "Tester");
		cf.enterTextboxContains(" Contact Telephone Number ",
				Long.toString(cf.createRandomInteger(10000000, 99999999))
						+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("Ext", Long.toString(cf.createRandomInteger(100, 999)));
		cf.enterTextboxContains("Email Address",
				"randomMail" + Long.toString(cf.createRandomInteger(1000, 9999)) + "@gmail.com");
		cf.screenShot("EmpRegister3", "Pass", "Details entered on SREG-001 page");
		cf.clickButton("Continue ");

		// --- SREG-025 ---//
		cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		cf.selectDropdown("Employer Type", " Indian Tribe ");
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(9,99)));
		cf.selectDropdown("Type of Legal Entity", " Other ");
		cf.enterTextbox("If Other, provide the type of Legal Entity.", "Agriculture");
		cf.enterTextboxContains("Employer Registration Number", "4517766");
		cf.screenShot("EmpRegister3", "Pass", "Details entered on SREG-025 page");
		cf.clickButton("Continue ");
		sleep(3000);
		// --- SREG-003 --- //
		cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-003 page");
		empPage.legalNameTextBox.sendKeys("shubh enterprises");

		cf.enterTextboxContains("Other commonly known", "ABCDERFGH");
		cf.enterTextboxContains(" Business Phone Number  ",
				Long.toString(cf.createRandomInteger(10000000, 99999999))
						+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?","17/03/2023");
		cf.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"Yes ");
		cf.enterTextbox("Enter the name of the federally recognized Indian Tribe.", "Absentee shawnee");
		cf.selectRadioQuestions(
				"Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Contributory");
		cf.enterTextboxContains("Estimated or approximate number", "432");
		cf.enterTextboxContains("Date covered employment began?", "10/4/2023");
		cf.screenShot("Employer Entity Information", "Pass", "Details entered on Employer Entity Information  (SREG-003)");
		cf.clickButtonContains("Continue");
		sleep();

		// --- SREG-008 ---
				sleep(2000);
				cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
				cf.enterTextboxContains("Address Line 1 ", "13th Street");
				cf.enterTextboxContains("City ", "New York");
				cf.enterTextboxContains("Zip Code", "10011");
				sleep();
				cf.selectDropdown("State", " New York ");
				cf.selectDropdown("County", " Albany ");
				sleep(2000);
				cf.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
				cf.clickButton("Continue ");
				
				sleep(2000);
				try {
					empRegPage.uspsBusinessAddress.click();
				} catch (Exception exception) {
					empRegPage.uspsBusinessAddressInnerCircle.click();
				}
				
				cf.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-008");
				empRegPage.continueButton_popUp.click();
				
				// --- SREG-007 ---
				sleep(2000);
				cf.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
				cf.clickButton("Continue ");
				
				// --- SREG-004 ---
				sleep(2000);
				cf.screenShot("EE01008", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
				//cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
				cf.selectRadioQuestions("Business Mailing Address", "Other");
				empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
				empRegPage.uspsBmadCityText.sendKeys("New York");
				empRegPage.uspsBmadZipText.sendKeys("10003");
				empRegPage.uspsBmadCounty.click();
				cf.selectFromDropdown(" Albany ");
				sleep(2000);
				cf.selectRadio("Same as Mailing");
				cf.enterTextbox("First Name", "abc");
				cf.enterTextbox("Last Name", "abc");
				sleep();
				cf.selectRadio("Same as Mailing");
				cf.enterTextbox("First Name", "abc");
				cf.enterTextbox("Last Name", "abc");
				cf.screenShot("EE01008", "Pass", "Successfully entered details in SREG-004 page");
				sleep(2000);
				cf.clickButton("Continue ");
				
							
				// --- SREG 683 ---
						sleep(2000);
						cf.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
						sleep();
						cf.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
				 		sleep(2000);
				 		cf.uploadDoc("Sample.docx");
				 		sleep(2000);
				 		cf.screenShot("EE01007", "Pass", "Sample document uploaded");
						cf.clickButton("Continue ");
				// --- SREG-800 ---
						sleep(10000);
						cf.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
						cf.clickButton("Continue ");
			   // --- SREG-043 ---
						sleep(2000);
						cf.selectCheckbox("I accept");
						cf.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
						cf.clickButton("Submit ");
			   // --- SREG-013 ---
						sleep(10000);
						cf.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
						cf.clickButton("Home ");
				
			  
		// Logout and Login as CSR
		cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		peoPage.queue.click();
		sleep(15000);
		cf.waitForLoadingIconToDisappear();
		sleep(20000);
	//	cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		cf.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		
		//cf.enterTextbox("FEIN", feinValue);
		cf.clickButton(" Search ");
		cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
//		cf.clickOnLink("Review Employer Type");
//		cf.safeJavaScriptClick(empPage.review_employer_My_Q);
		empPage.review_employer_My_Q.click();
		sleep(3000);
		/*-----------------WF-091----------------*/
		cf.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		cf.clickButton("Open Work Item ");
		sleep(2000);
		//employerManagement em =  new employerManagement();
		cf.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		empPage.commentBox_MyQ.sendKeys("Random Queue");
		sleep();
		cf.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		
		sleep(4000);
		cf.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page and click on Home");
		cf.clickButton("Home ");
		
		/*-----------------Home Page----------------*/
		cf.waitForLoadingIconToDisappear();
		sleep(20000);
		//cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		cf.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
	//	cf.enterTextbox("FEIN", feinValue);
		cf.clickButton(" Search ");
		cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
//		cf.clickOnLink("Review Employer Type");
		sleep();
//		cf.safeJavaScriptClick(empPage.obtain_bond_task_My_Q);
		PEOPage.queue.click(); Thread.sleep(15000);
//		cf.enterTextboxContains("FEIN",feinValue);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search"); Thread.sleep(2000);
		cf.screenShot("DOL DTF Discrepancy","Pass","emp type");
		cf.clickOnLink("DOL DTF Discrepancy");

		Thread.sleep(2000); cf.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		cf.screenShot("","Pass","DOL DTF ");
		cf.selectDropdown("Quarter", "1");sleep();
		cf.selectDropdown("Year", "2023");sleep();
		cf.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		cf.selectDropdown("*Account Status ", "Liable");
		cf.enterTextboxContains("Comment", "registration in process");
		cf.clickButtonContains("Submit"); Thread.sleep(2000);
		cf.screenShot("GeneralInfo","Pass","General Information");
		cf.clickButtonContains("Home");

		//Verify Registered employer in Inquery page 	...........
	//	em.Inquery_fein(feinValue);
		test.log(Status.PASS, "Clicked on Home button");		
		/*-----------------SUC-002----------------*/
		
		sleep(10000);
		cf.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page and click on Home");
		cf.clickButton("Home ");
	}
		
		
		

		

	
	}


