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
		// DB Query //
		// FEIN - In DOL but not in DTF
		Map<String, String> databaseFeinResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.EAN = tedd.ERN WHERE tea.FEIN != tedd.FEIN",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println(feinValue);

		// ERN - In DOL but not in DTF
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.FEIN = tedd.FEIN WHERE tea.EAN != tedd.ERN AND tea.EAN IS NOT NULL",
				"ERN");
		String eanValue = databaseEanResult.get("ERN");
		System.out.println(eanValue);

		// Legal name not in DOL, multiple in DTF
		Map<String, String> databaseEntityNameResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd GROUP BY LEGAL_NAME HAVING COUNT(*)>1 ) ORDER BY UPDATED_TS DESC",
				"ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);

		// --- Login ---
		cf.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		cf.clickMenu("Menu");
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
		sleep(2000);
		cf.screenShot("EmpRegister3", "Pass", "Details entered on SREG-001 page");
		cf.clickButton("Continue ");

		// --- SREG-025 ---//
		cf.selectDropdown("Employer Type", " Governmental ");
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		cf.selectDropdown("Type of Legal Entity", " Town ");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(3000);
		cf.clickButton("Continue ");
		cf.screenShot("EmpRegister4", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);

		// --- SREG-003 --- //
		empRegPage.legalNameTextBox.sendKeys(legalName);
		cf.enterTextboxContains("Other commonly known", "ABCDERFGH");
		cf.enterTextboxContains(" Business Phone Number  ",
				Long.toString(cf.createRandomInteger(10000000, 99999999))
						+ Long.toString(cf.createRandomInteger(10, 99)));
		sleep(2000);
		cf.enterTextboxContains("What is the date of the first payroll", "10/6/2022");
		cf.enterTextboxContains("Estimated or approximate number", "432");
		cf.enterTextboxContains("Date covered employment began?", "10/4/2023");
		cf.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"No");
		cf.selectRadioQuestions(
				"Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Contributory");
		cf.screenShot("Employer Entity Information", "Pass", "Employer Entity Information  (SREG-003)");
		cf.clickButtonContains("Continue");
		sleep();

		// --- SREG-008 --- //
		cf.screenShot("Expected : SREG-008", "Pass", "Successfully launched to SREG-008");
		cf.enterTextboxContains("Address Line 1 ", "New York Street");
		cf.enterTextboxContains("Address Line 2 ", "Brooklyn");
		cf.enterTextboxContains("City ", "Brooklyn");
		// cf.enterTextboxContains("State", "New York");
		cf.enterTextboxContains("Zip Code", "10001");
		cf.selectDropdown("County", " Albany ");
		cf.screenShot("EmpRegister5", "Pass",
				"Enter the details on Employer Entity Information page and click continue");
		cf.clickButton("Continue ");

		// --- SREG-007 --- //
		cf.screenShot("Business Physical Address Details", "Pass", "Successfully launched to (SREG-007)");
		cf.clickButton("Continue ");

		// --- SREG-004 --- //
		cf.screenShot("Employer Contact Details", "Pass", "Successfully launched to SREG-004");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		cf.enterTextboxContains("Address Line 1 ", "123state");
		cf.enterTextboxContains("Address Line 2 ", "Brooklyn");
		cf.enterTextboxContains("City", "Albany");
		cf.selectDropdown("State", "New York");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.selectDropdown("Country", "United States");
		cf.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		empRegPage.enterContactPersonFirstName("Contact Person for Location of Books and Records Address", "Joseph");
		empRegPage.enterContactPersonMiddleName("Contact Person for Location of Books and Records Address", "M");
		empRegPage.enterContactPersonLastName("Contact Person for Location of Books and Records Address", "Morgan");
		cf.screenShot("EmpRegister6", "Pass",
				"Entered Details of Contact Person for Location of Books and Records address in SREG-004 page");
		// Notice of Potential Charges L0400 Address
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		empRegPage.notice_potential_AddressLine_1.sendKeys("123state");
		empRegPage.notice_potential_City.sendKeys("New York");
		empRegPage.notice_potential_Zipcode.sendKeys("12112");
//		cf.enterTextboxContains("Address Line 1 ", "123state");
//		cf.enterTextboxContains("Address Line 2 ", "Brooklyn");
//		cf.enterTextboxContains("City", "Albany");
//		cf.selectDropdown("State", "New York");
//		cf.enterTextboxContains("Zip Code", "13429");
//		cf.selectDropdown("Country", "United States");
//		cf.selectDropdown("County", "Albany");
		empRegPage.enterContactPersonFirstName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Jonathan");
		empRegPage.enterContactPersonMiddleName("Contact Person for Notice of Potential Charges (LO400) Address", "S");
		empRegPage.enterContactPersonLastName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Cybel");
		cf.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
		cf.screenShot("EmpRegister6", "Pass",
				"Entered Details of Contact Person for Notice of Potential Charges (LO400) address in SREG-004 page");
		cf.clickButton("Continue ");
		sleep(2000);

		try {
			empRegPage.uspsBmadAddressRadio.click();
			empRegPage.uspsLbraAddressRadio.click();
			empRegPage.uspsNpcaAddressRadio.click();
			cf.screenShot("EmpRegister15", "Pass", "Click on appropriate USPS radio on SREG-004 page");
			empRegPage.continueButton_popUp.click();
		} catch (Exception exception) {
			sleep(2000);
		}

		// --- SREG-521 --- //
		cf.screenShot("Employer Verify Contact Details", "Pass", "Successfully launched to SREG-521");
		cf.clickButton("Continue ");

		// --- SREG-683 --- //
		cf.screenShot("Upload Documents", "Pass", "Successfully launched to SREG-683");
		cf.clickButton("Continue ");
		sleep(2000);

		// --- SREG-800 --- //
		cf.screenShot("Review Registration Details", "Pass", "Successfully launched to SREG-800");
		cf.clickButton("Continue ");
		sleep(2000);

		// --- SREG-043 --- //
		cf.screenShot("Statement of Acknowledgement", "Pass", "Successfully launched to SREG-043");
		cf.enterTextboxContains("Submitter Comments may be entered below.", "okay");
		cf.selectCheckbox("I accept");
		sleep();
		cf.screenShot("Submit", "Pass", "Click on submit button");
		cf.clickButtonContains("Submit ");
		sleep(15000);

		// --- SREG-013 --- //
		cf.screenShot("Employer Registration Confirmation", "Pass", "Successfully launched to SREG-013");
		cf.clickButtonContains("Exit ");
		cf.screenShot("Home Page", "Pass", "Successfully exited to Home Page");

		// Logout and Login as CSR
		cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		peoPage.queue.click();
		sleep(15000);
		cf.waitForLoadingIconToDisappear();
		sleep(20000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		cf.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		
		cf.enterTextbox("FEIN", feinValue);
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
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		cf.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextbox("FEIN", feinValue);
		cf.clickButton(" Search ");
		cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
//		cf.clickOnLink("Review Employer Type");
		sleep();
//		cf.safeJavaScriptClick(empPage.obtain_bond_task_My_Q);
		PEOPage.queue.click(); Thread.sleep(15000);
		cf.enterTextboxContains("FEIN",feinValue);
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
		em.Inquery_fein(feinValue);
		test.log(Status.PASS, "Clicked on Home button");		
		/*-----------------SUC-002----------------*/
		
		sleep(10000);
		cf.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page and click on Home");
		cf.clickButton("Home ");
	}
		
		
		

		

	
	}


