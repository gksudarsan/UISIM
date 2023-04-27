package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_002 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Town' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_10_002() throws Exception {

		test = report.createTest(
				"Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Town' and work items will be created for CSR to review.");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// DB Query //
		// FEIN - In DOL but not in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.EAN = tedd.ERN WHERE tea.FEIN != tedd.FEIN",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println(feinValue);

		// ERN - In DOL but not in DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.FEIN = tedd.FEIN WHERE tea.EAN != tedd.ERN AND tea.EAN IS NOT NULL",
				"ERN");
		String eanValue = databaseEanResult.get("ERN");
		System.out.println(eanValue);

		// Legal name not in DOL, multiple in DTF
		Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd GROUP BY LEGAL_NAME HAVING COUNT(*)>1 ) ORDER BY UPDATED_TS DESC",
				"ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		sleep(2000);
		commonFunction.clickMenu("Menu");
		// commonFuntions.clickMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(2000);
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");

		// --- SREG-001 ---
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Middle Initial", "S");
		commonFunction.enterTextboxContains("Last Name", "Rodriguez");
		commonFunction.selectDropdown("Suffix", " Sr. ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister2", "Pass", "Details entered on SREG-001 page");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Telephone Number ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address",
				"randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered on SREG-001 page");
		commonFunction.clickButton("Continue ");

		// --- SREG-025 ---//
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.selectDropdown("Type of Legal Entity", " Town ");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(3000);
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister4", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);

		// --- SREG-003 --- //
		empRegPage.legalNameTextBox.sendKeys(legalName);
		commonFunction.enterTextboxContains("Other commonly known", "ABCDERFGH");
		commonFunction.enterTextboxContains(" Business Phone Number  ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		sleep(2000);
		commonFunction.enterTextboxContains("What is the date of the first payroll", "10/6/2022");
		commonFunction.enterTextboxContains("Estimated or approximate number", "432");
		commonFunction.enterTextboxContains("Date covered employment began?", "10/4/2023");
		commonFunction.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"No");
		commonFunction.selectRadioQuestions(
				"Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Contributory");
		commonFunction.screenShot("Employer Entity Information", "Pass", "Employer Entity Information  (SREG-003)");
		commonFunction.clickButtonContains("Continue");
		sleep();

		// --- SREG-008 --- //
		commonFunction.screenShot("Expected : SREG-008", "Pass", "Successfully launched to SREG-008");
		commonFunction.enterTextboxContains("Address Line 1 ", "New York Street");
		commonFunction.enterTextboxContains("Address Line 2 ", "Brooklyn");
		commonFunction.enterTextboxContains("City ", "Brooklyn");
		// commonFunction.enterTextboxContains("State", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10001");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enter the details on Employer Entity Information page and click continue");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// --- SREG-007 --- //
		commonFunction.screenShot("Business Physical Address Details", "Pass", "Successfully launched to (SREG-007)");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// --- SREG-004 --- //
		commonFunction.screenShot("Employer Contact Details", "Pass", "Successfully launched to SREG-004");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		commonFunction.enterTextboxContains("Address Line 1 ", "123state");
		commonFunction.enterTextboxContains("Address Line 2 ", "Brooklyn");
		commonFunction.enterTextboxContains("City", "Albany");
		commonFunction.selectDropdown("State", "New York");
		commonFunction.enterTextboxContains("Zip Code", "13429");
		commonFunction.selectDropdown("Country", "United States");
		commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		empRegPage.enterContactPersonFirstName("Contact Person for Location of Books and Records Address", "Joseph");
		empRegPage.enterContactPersonMiddleName("Contact Person for Location of Books and Records Address", "M");
		empRegPage.enterContactPersonLastName("Contact Person for Location of Books and Records Address", "Morgan");
		commonFunction.screenShot("EmpRegister6", "Pass",
				"Entered Details of Contact Person for Location of Books and Records address in SREG-004 page");
		// Notice of Potential Charges L0400 Address
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		empRegPage.notice_potential_AddressLine_1.sendKeys("123state");
		empRegPage.notice_potential_City.sendKeys("New York");
		empRegPage.notice_potential_Zipcode.sendKeys("12112");
//		commonFunction.enterTextboxContains("Address Line 1 ", "123state");
//		commonFunction.enterTextboxContains("Address Line 2 ", "Brooklyn");
//		commonFunction.enterTextboxContains("City", "Albany");
//		commonFunction.selectDropdown("State", "New York");
//		commonFunction.enterTextboxContains("Zip Code", "13429");
//		commonFunction.selectDropdown("Country", "United States");
//		commonFunction.selectDropdown("County", "Albany");
		empRegPage.enterContactPersonFirstName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Jonathan");
		empRegPage.enterContactPersonMiddleName("Contact Person for Notice of Potential Charges (LO400) Address", "S");
		empRegPage.enterContactPersonLastName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Cybel");
		commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
		commonFunction.screenShot("EmpRegister6", "Pass",
				"Entered Details of Contact Person for Notice of Potential Charges (LO400) address in SREG-004 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		
		empRegPage.uspsBmadAddressRadio.click();
		empRegPage.uspsLbraAddressRadio.click();
		//empRegPage.uspsNpcaAddressRadio.click();
		commonFunction.screenShot("EmpRegister15", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		empRegPage.continueButton_popUp.click();
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {
			sleep(2000);
		}

		// --- SREG-521 --- //
		commonFunction.screenShot("Employer Verify Contact Details", "Pass", "Successfully launched to SREG-521");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// --- SREG-683 --- //
		commonFunction.screenShot("Upload Documents", "Pass", "Successfully launched to SREG-683");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// --- SREG-800 --- //
		commonFunction.screenShot("Review Registration Details", "Pass", "Successfully launched to SREG-800");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// --- SREG-043 --- //
		commonFunction.screenShot("Statement of Acknowledgement", "Pass", "Successfully launched to SREG-043");
		commonFunction.selectCheckbox("I accept");
		sleep();
		commonFunction.screenShot("Submit", "Pass", "Click on submit button");
		commonFunction.clickButtonContains("Submit ");
		sleep(15000);

		// --- SREG-013 --- //
		commonFunction.screenShot("Employer Registration Confirmation", "Pass", "Successfully launched to SREG-013");
		commonFunction.clickButtonContains("Exit ");
		commonFunction.screenShot("Home Page", "Pass", "Successfully exited to Home Page");

		// Logout and Login as CSR
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		peoPage.queue.click();
		sleep(15000);
		
		// Blocked after step 16, Work Item not getting created
		commonFunction.screenShot("Home Page", "Fail", "Blocked after step 16, Work Item not getting created");

	
	}

}
