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

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_10_005() throws Exception {

		test = report.createTest(
				"Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// GET query
		// FEIN in DOL & not in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.EAN = tedd.ERN WHERE tea.FEIN != tedd.FEIN",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		// EAN in DOL not in DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		commonFunction.clickMenu("menu");
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
		System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.selectDropdown("Type of Legal Entity", " County ");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		System.out.println("The EAN is " + eanValue);
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
		sleep();

		// --- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
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
		commonFunction.selectRadioQuestions(
				"Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Reimbursable");
		sleep();
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "Acme Corp");
		commonFunction.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFunction.enterTextboxContains("Address Line 2 ", "St 9th floor");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10001");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enter the details on Employer Entity Information page and click continue");
		commonFunction.clickButton("Continue ");

		// --- SREG-008 ---
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enter the details on Employer Entity Information page and click continue");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street ");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");

		empRegPage.uspsBusinessAddress.click();
		commonFunction.screenShot("EmpRegister5", "Pass", "USPS Business address selection");
		empRegPage.continueButton_popUp.click();

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

		empRegPage.uspsBmadAddress.click();
		empRegPage.uspsLbraAddress.click();
		empRegPage.uspsNpcaAddress.click();
		commonFunction.screenShot("EmpRegister5", "Pass", "Entered USPS address in SREG-004 page");
		commonFunction.clickButton("Continue ");

		// --- SREG 683 Screen expected ---

		commonFunction.screenShot("FailurePage", "Fail", "Launched to SREG-521 page");
		System.out.println("XXX");

	}

}