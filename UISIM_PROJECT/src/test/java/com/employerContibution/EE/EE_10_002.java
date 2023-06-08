package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
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
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		
		
		//
		String feinValuemanual = "133653406";

		//

		// DB Query //
		// FEIN - In DOL but not in DTF
		/*Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
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
*/
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
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValuemanual);
		commonFunction.selectDropdown("Type of Legal Entity", " Town ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0506562");
		sleep(3000);
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister4", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);

		// --- SREG-003 --- //
		//empRegPage.legalNameTextBox.sendKeys(legalName);
		empRegPage.legalNameTextBox.sendKeys("ColorEseence122");
		
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

		commonFunction.selectRadioQuestions("bmad Address", "123");
		commonFunction.selectRadioQuestions("lbra Address", "123");
		
		//empRegPage.uspsBmadAddressRadio.click();
		//empRegPage.uspsLbraAddressRadio.click();
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
		sleep(3000);
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
		sleep(10000);

		// --- SREG-013 --- //
		commonFunction.screenShot("Employer Registration Confirmation", "Pass", "Successfully launched to SREG-013");
		commonFunction.clickButton("Home ");
		commonFunction.screenShot("Homepage", "Pass", "Homapage page displayed");
		
		////
		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValuemanual+"' ORDER BY UPDATED_TS desc)");
		////
		
		// Logout and Login as CSR
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("Business Acquisition", "Pass", "logged In");
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFunction.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		commonFunction.enterTextboxContains("FEIN", feinValuemanual);
		commonFunction.clickButtonContains(" Search ");
		Thread.sleep(2000);
		
		sreg084.reviewemployertypelink.click();
		commonFunction.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		
		commonFunction.clickButtonContains("Open Work Item ");
		Thread.sleep(4000);
		commonFunction.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		
		test.info("Step: 18 -- Enter/Select \"Review Employer Type Task (EEWI-002)\" screen with below details");  
		commonFunction.enterTextboxContains("Date Covered Employment began? ", "05/07/2022");
		sleep(2000);
		sreg043.EEWI002CommentsField.sendKeys("testing");
		commonFunction.clickButtonContains("Submit ");
		commonFunction.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 19 -- Click on Exit button on \"Workitem Completed Confirmation - (SUC-002)\" screen.");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFunction.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		test.info("Step: 20-- CSR Navigates to Main Menu -> MyQ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(7000);
		commonFunction.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 21-- CSR Enter/Select the information in \"Individual Work Queue - (WF-001)\" screen");
		//commonFunction.enterTextboxContains("FEIN", feinValuemanual);
		//commonFunction.clickButtonContains(" Search ");
		//Thread.sleep(2000);
		
		test.info("Step: 22-- Select  DOL-DTF Discrepancy Task  from the search result by clicking on Work Item Description hyperlink");
		//click manually
		
		test.info("Step: 23-- Enter/Select  \"DOL-DTF Discrepancy Task (EEWI-005)\" screen with below details");
		commonFunction.clickButtonContains("Open Work Item ");
		Thread.sleep(5000);
		sreg043.EEWI002CommentsField.sendKeys("testing");
		commonFunction.clickButtonContains("Submit ");
		Thread.sleep(4000);
		commonFunction.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 24 -- Click on Exit button on \"Workitem Completed Confirmation - (SUC-002)\" screen.");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.DOLDTFDiscrepancySuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFunction.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		test.info("Step: 25 -- Navigate to Main Menu > Inquiry > Contribution Inquiry > Inquiry Employer Account");
		sleep(4000);
		commonFunction.clickMenu("Menu");
		commonFunction.clickMenu("Inquiry");
		commonFunction.clickMenu("Contribution Inquiry");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Main Menu > Inquiry > Contribution Inquiry > Inquiry Employer Account");
		commonFunction.clickMenu("Inquiry Employer Account");
		
		sleep(2000);
		commonFunction.screenShot("EmpRegister1", "Pass", "SREG-050 Inquiry Employer Account");
		
		test.info("Step: 26 -- Enter/Select details on Inquiry Employer Account – Enter ERN (SREG-050) screen ");
		commonFunction.enterTextboxContains(" FEIN ", feinValuemanual);
		commonFunction.clickButtonContains("Continue ");
		
		commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "SREG-051 page is dislayed");
		
		test.info("Step: 27 -- Enter/Select details on Inquiry Employer Account – Enter ERN (SREG-050) screen ");
		//28
		commonFunction.clickButtonContains("Previous ");
		Thread.sleep(2000);
		commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "(SREG-050) screen is dislayed");
		commonFunction.clickButtonContains(" Home ");
		Thread.sleep(2000);
		commonFunction.screenShot("Home", "Pass", "Home Screen screen is dislayed");
		
	}

}
