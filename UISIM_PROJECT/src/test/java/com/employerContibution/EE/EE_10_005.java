package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_005 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EE_10_005 Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_10_005() throws Exception {

		test = report.createTest(
				"EE_10_005 Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_4.toUpperCase(), COMMON_CONSTANT.TPR_USER_4_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		commonFunction.clickMenu("Menu");
		// commonFuntions.clickMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");

		// --- SREG-001 ---
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Middle Initial", "S");
		commonFunction.enterTextboxContains("Last Name", "Rodriguez");
		commonFunction.selectDropdown("Suffix", " Sr. ");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Telephone Number ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address",
				"randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		commonFunction.screenShot("MenuPage", "Pass", "Details entered on SREG-001 page");
		commonFunction.clickButton("Continue ");

		// --- SREG-025 ---
		commonFunction.screenShot("MenuPage", "Pass", "Details entered on SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		// System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "261035802");
		commonFunction.selectDropdown("Type of Legal Entity", " County ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0719526");
		// System.out.println("The EAN is " + eanValue);
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
		sleep();

		// --- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("TEST DUPE TEST DUP22");
		commonFunction.enterTextboxContains("Other commonly known", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains(" Business Fax Number ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("date of the first payroll", "02/10/2024");
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "347");
		commonFunction.enterTextboxContains("Date covered employment", "10/01/2023");
		commonFunction.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"Yes ");
		sleep();
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "Acme Corp");
		commonFunction.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFunction.enterTextboxContains("Address Line 2 ", "St 9th floor");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10001");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enter the details on Employer Entity Information page and click continue");
		commonFunction.selectRadioQuestions(
				"Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Contributory");
		commonFunction.clickButton("Continue ");

		// --- SREG-008 ---
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enter the details on Employer Entity Information page and click continue");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street ");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {

			empRegPage.uspsBusinessAddressInnerCircle.click();
		}

		commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		sleep(2000);

		// --- SREG-007 ---
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");

		// --- SREG-004 ---
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Location of Books and Records",
				"Same as Primary Business Physical Address");
		empRegPage.enterContactPersonFirstName("Contact Person for Location of Books and Records Address", "Joseph");
		empRegPage.enterContactPersonMiddleName("Contact Person for Location of Books and Records Address", "M");
		empRegPage.enterContactPersonLastName("Contact Person for Location of Books and Records Address", "Morgan");
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enetered Details of Contact Person for Location of Books and Records address in SREG-004 page");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address",
				"Same as Primary Business Physical Address");
		empRegPage.enterContactPersonFirstName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Jonathan");
		empRegPage.enterContactPersonMiddleName("Contact Person for Notice of Potential Charges (LO400) Address", "S");
		empRegPage.enterContactPersonLastName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Cybel");
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enetered Details of Contact Person for Notice of Potential Charges (LO400) address in SREG-004 page");
		commonFunction.clickButton("Continue ");

		try {
			try {
				empRegPage.bmad_Address.click();
			} catch (Exception exception) {
				empRegPage.bmad_AddressInnerCircle.click();
			}
			try {
				empRegPage.lbra_Address.click();
			} catch (Exception exception) {
				empRegPage.lbra_AddressInnerCircle.click();
			}
			try {
				empRegPage.npca_Address.click();
			} catch (Exception exception) {
				empRegPage.npca_AddressInnerCircle.click();
			}
			commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection onSREG-004");
			empRegPage.continueButton_popUp.click();
		} catch (Exception exception) {
		}

		sleep(2000);
		commonFunction.clickButton("Continue ");

		

		// --- SREG 683 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(
				" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.",
				"Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		// --- SREG-800 ---
		sleep(10000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		// --- SREG-043 ---
		sleep(2000);
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		// --- SREG-013 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Home ");
		sleep(2000);

		// --- Home ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
		sleep(2000);

		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(10000);

		commonFunction.screenShot("EmpRegister13", "Pass", "Navigated on Home page");
		commonFunction.clickButton(" Go to My Q ");

		sleep(3000);

		// --- WF-001 ---
		sleep(10000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.enterTextboxContains("Work Item Description Free Text", "Dol");
		commonFunction.clickButton(" Search ");
		commonFunction.clickHyperlink("DOL DTF Discrepancy");
		// --- WF-091 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
		commonFunction.clickButton("Open Work Item ");
		// --- EEWI-005 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-005 page");
		empRegPage.Legal_Name_of_business.sendKeys(" TAX");
		commonFunction.enterTextboxContains("Enter date of first operation in New York State", "7/9/2022");
		commonFunction.selectDropdown("Quarter ", " 3 ");
		commonFunction.selectDropdown("Year ", " 2023 ");
		commonFunction.selectDropdown("Account Status", " Liable ");
		commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
		empRegPage.commentBox_MyQ.sendKeys("for testing");
		commonFunction.clickButton("Submit ");
		// --- SUC-002 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
		commonFunction.clickButton("Home ");
		commonFunction.clickButton(" Go to My Q ");

	}

}