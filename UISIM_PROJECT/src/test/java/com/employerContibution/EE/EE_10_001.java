package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_003;
import com.ui.pages.SREG_084;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_043;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_001 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_10_001() throws Exception {

		test = report.createTest(
				"Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review.");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_003 sreg003 = new SREG_003(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		//
		String feinValuemanual = "133653406";

		//
		// GET query
		// FEIN in DOL & not in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.EAN = tedd.ERN WHERE tea.FEIN != tedd.FEIN",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println("The FIEN is " + feinValue);
		// EAN in DOL & DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		commonFunction.clickMenu("Menu");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("HomePage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		sleep(2000);

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

		// --- SREG-025 ---
		sleep(2000);
		commonFunction.screenShot("MenuPage", "Pass", "Details entered on SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		// commonFunction.enterTextboxContains("Federal Employer Identification Number
		// (FEIN)", feinValue); // 897397325
		
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)",feinValuemanual);
		commonFunction.selectDropdown("Type of Legal Entity", " City ");
		// commonFunction.enterTextboxContains("Employer Registration Number",
		// eanValue); // 4543352
		commonFunction.enterTextboxContains("Employer Registration Number", "0506562");
		sleep(2000);
		commonFunction.screenShot("EmpRegister4", "Pass", "Details entered and click on CONTINUE button");
		commonFunction.clickButton("Continue ");
		sleep(4000);

		// --- SREG-003 ---
		/*
		 * sleep(2000); commonFunction.screenShot("EmpRegister5", "Pass",
		 * "Launched Employer Entity Information(SREG-003) page");
		 * empRegPage.legalNameTextBox.sendKeys("ColorEseence122");
		 * //empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
		 * //commonFunction.enterTextboxContains("Other commonly known", "S Corp");
		 * commonFunction.enterTextboxContains(" Business Phone Number  ",
		 * Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) +
		 * Long.toString(commonFunction.createRandomInteger(10, 99))); sleep(2000);
		 * commonFunction.screenShot("EmpRegister6", "Pass",
		 * "Details entered in SREG-003 page");
		 * commonFunction.enterTextboxContains("date of the first payroll",
		 * "10/12/2021"); commonFunction.
		 * enterTextboxContains("Estimated or approximate number of individuals",
		 * "745"); commonFunction.enterTextboxContains("Date covered employment",
		 * "05/07/2022"); sleep(2000);
		 * 
		 * commonFunction.selectRadioQuestions(
		 * "Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?"
		 * , "Yes "); sleep();
		 * 
		 * commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity",
		 * "Acme Corp"); commonFunction.enterTextboxContains("Address Line 1 ",
		 * "29 W 35th"); commonFunction.enterTextboxContains("Address Line 2 ",
		 * "St 9th floor"); commonFunction.enterTextboxContains("City ", "New York");
		 * commonFunction.enterTextboxContains("Zip Code", "10001");
		 * commonFunction.selectDropdown("County", " Albany ");
		 * commonFunction.screenShot("EmpRegister8", "Pass",
		 * "Enter the details on Employer Entity Information page and click continue");
		 * commonFunction.clickButton("Continue ");
		 * 
		 * //step 8 failed at MC sleep(); commonFunction.screenShot("EmpRegister7",
		 * "Fail", "Failed in MC");
		 */

		test.info(
				"Step: 6 -- Do not Enter/ Select on 'Employer Entity Information  (SREG-003)' screen and Click \"Continue\" button.");
		commonFunction.clickButton("Continue ");
		commonFunction.errorLabel(" Required");
		commonFunction.screenShot("Employer Entity Information", "Pass", "Required Error ");
		sleep(2000);

		test.info("Step: 7 --  Required fields verification");
		empRegPage.legalNameTextBox.sendKeys("ColorEseence122");
		commonFunction.enterTextboxContains(" Business Phone Number  ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		sleep(2000);
		commonFunction.enterTextboxContains("date of the first payroll", "10/12/2021");
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "745");
		commonFunction.enterTextboxContains("Date covered employment", "05/07/2022");
		sleep(2000);

		commonFunction.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"Yes ");
		sleep(1000);
		commonFunction.clickButton("Continue ");
		commonFunction.errorLabel(" Required");
		commonFunction.screenShot("Employer Entity Information", "Pass", "Required Error: Legal Name ");

		test.info("Step: 8 --  Required fields verification");
		commonFunction.enterTextboxContains("Other commonly known", "ColorEseence122");
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "ColorEseence122");
		commonFunction.clickButton("Continue ");
		commonFunction.errorLabel(" Other Name cannot be the same as Legal Name of business");
		commonFunction.screenShot("Employer Entity Information", "Pass",
				"Required Error: Other Name cannot be the same as Legal Name of business");

		//
		Thread.sleep(2000);
		commonFunction.forceClearText(sreg003.othercommonNameField);
		Thread.sleep(2000);
		commonFunction.forceClearText(sreg003.legalEntitIfField);
		//

		test.info("Step: 9 --  Date of first payroll withheld is invalid");
		// error not occuring

		test.info("Step: 10 --  move to SREG-008");
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "Acme Corp");
		commonFunction.clickButton("Continue ");
		Thread.sleep(4000);
		commonFunction.screenShot("Add Primary Business Physical Address", "Pass", "Page SREG-008 is visible");

		Assert.assertTrue(sreg084.helpButton.isDisplayed());
		Assert.assertTrue(sreg084.previousButton.isDisplayed());
		Assert.assertTrue(sreg084.continueButton.isDisplayed());
		Assert.assertTrue(sreg008.finishLaterButton.isDisplayed());

		test.info(
				"Step: 11 --  Click on Finish Later button on 'Add Primary Business Physical Address (SREG-008)' screen");
		sreg008.finishLaterButton.click();
		Thread.sleep(2000);
		sreg008.YesButton.click();

		Thread.sleep(3000);
		commonFunction.screenShot("Home Screen", "Pass", "Home screen is visible");

		test.info("Step: 12 --  Navigate to Home Page>Employer Registration> Incomplete Registration");
		commonFunction.clickMenu("Menu");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("HomePage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Incomplete Registration");
		sleep(2000);
		commonFunction.screenShot("Search for Finish Later Applications", "Pass", "SREG-101 is visble");

		test.info("Step: 13 --  Enter details on \"Search for Finish Later Applications\" screen");
		commonFunction.enterTextboxContains("FEIN", "121121121");
		commonFunction.clickButton(" Search ");
		commonFunction.screenShot("Search for Finish Later Applications", "Pass", "No records found");
		sleep(4000);
		test.info("Step: 14 --  Enter details on \"Search for Finish Later Applications\" screen");
		commonFunction.forceClearText(sreg084.feinField);
		sleep(2000);
		commonFunction.enterTextboxContains("FEIN", feinValuemanual);
		commonFunction.clickButton(" Search ");
		sleep(2000);
		commonFunction.screenShot("Search for Finish Later Applications", "Pass", "table is visible");

		test.info("Step: 15 --  Click on Legal Name of Business hyperlink");
		sreg003.lnobWithTextFirst.click(); //ColorEseence122
		sleep(4000);
		commonFunction.screenShot("Employer Registration", "Pass", "SREG-001 is visble");

		test.info("Step: 16 --  Click on Legal Name of Business hyperlink");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("General Information", "Pass", "SREG-025 is visble");

		test.info("Step: 17 --  Verify 'General Information (SREG-025)' screen with below information");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("Employer Entity Information", "Pass", "SREG-003 is visble");

		test.info("Step: 18 --  Verify 'General Information (SREG-003)' screen with below information");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("Employer Entity Information", "Pass", "SREG-008 is visible");

		test.info("Step: 19 --  check required error in Add Primary Business Physical Address page");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.errorLabel(" Required");
		commonFunction.screenShot("Add Primary Business Physical Address", "Pass", "Required Error ");

		test.info("Step: 20 --  check required error in Add Primary Business Physical Address page");
		commonFunction.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFunction.enterTextboxContains("Address Line 2 ", "St 9th floor");
		//commonFunction.selectDropdown("Country", " United States ");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10001");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("Add Primary Business Physical Address", "Pass", "Verify address popup is displayed");

		test.info("Step: 21 --  Select appropriate radio button on Pop up message");
		sreg008.firstradiobuttonVerifyAddPopup.click();
		sleep(2000);
		empRegPage.continueButton_popUp.click();
		commonFunction.screenShot("Business Physical Address Details", "Pass", "SREG-007 page displayed");

		test.info("Step: 22 --  Click \"Continue\" button on \"Business Physical Address Details (SREG-007)\" screen");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.screenShot("Business Physical Address Details", "Pass", "SREG-004 page is displayed");

		test.info("Step: 23 --  Enter/Select \"Employer Contact Details (SREG-004)\" screen with below details");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Location of Books and Records",
				"Same as Primary Business Physical Address");
		//commonFunction.enterTextboxContains("First Name", "Antonio");
		sreg004.listOfFirstname.get(0).sendKeys("matt");
		sreg004.listOfLastName.get(0).sendKeys("hade");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address",
				"Same as Location of Books and Records");
		commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
		commonFunction.clickButton("Continue ");

		sleep(2000);
		commonFunction.selectRadioQuestions("bmad Address", "29");
		commonFunction.selectRadioQuestions("lbra Address", "29");
		commonFunction.selectRadioQuestions("npca Address", "29");

		sreg004.popUpContinueButton.click();
		sleep(2000);
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {
			sleep(2000);
		}
		
		sleep(2000);
		
		commonFunction.screenShot("Employer Verify Contact Details", "Pass", "SREG-521 page displayed");

		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("Upload Documents", "Pass", "SREG-683 page displayed");

		test.info("Step: 24 --  Click \"Upload\" button on 'Upload Documents (SREG-683)' screen");
		sreg084.browseLink.click();
		Thread.sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		Thread.sleep(2000);
		commonFunction.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Upload Document Section");
		Assert.assertTrue(sreg084.uploadDocSec.getText().contains("Uploaded Documents"));

		test.info("Step: 25 --  Click on Continue button on 'Upload Documents (SREG-683)' screen");
		commonFunction.clickButton("Continue ");
		Thread.sleep(2000);
		commonFunction.screenShot("Review Registration Details", "Pass", "SREG-800 page displayed");

		test.info("Step: 26 --  Click Continue button on \"Review Registration Details (SREG-800)\" screen");
		commonFunction.clickButton("Continue ");
		Thread.sleep(2000);
		commonFunction.screenShot("Statement of Acknowledgement", "Pass", "SREG-043 page displayed");

		test.info("Step: 27 --  Enter/Select the information in 'Statement' of Acknowledgement (SREG-043)' screen ");
		sreg043.submitterCommentsField.sendKeys("test");
		commonFunction.selectCheckbox("I accept");
		commonFunction.clickButton("Continue ");
		Thread.sleep(2000);
		commonFunction.screenShot("Employer Registration Confirmation", "Pass", "SREG-013 page displayed");
		
		test.info("Step: 28 -- Navigate to homepage");
		commonFunction.clickButton("Home ");
		Thread.sleep(2000);
		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValuemanual+"' ORDER BY UPDATED_TS desc)");
		commonFunction.screenShot("Homepage", "Pass", "Homapage page displayed");
		
		test.info("Step: 29&30 -- Login as a CSR and Navigates to Main Menu -> MyQ");
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("Business Acquisition", "Pass", "logged In");
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFunction.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 31 -- Click on Search button using fein number");
		commonFunction.enterTextboxContains("FEIN", feinValuemanual);
		commonFunction.clickButtonContains(" Search ");
		Thread.sleep(2000);
		
		test.info("Step: 32 -- Select  \"Review Employer Type Task\"  from the search result by clicking on Work Item Description hyperlink");
		//commonFunction.selectTableWithoutId("Work Item Description", 1, 1, "Individual Work Queue");
		sreg084.reviewemployertypelink.click();
		commonFunction.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		
		commonFunction.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFunction.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");

		test.info("Step: 33 -- Enter/Select \"Review Employer Type Task (EEWI-002)\" screen with below details");  
		commonFunction.enterTextboxContains("Date Covered Employment began? ", "05/07/2022");
		sleep(2000);
		sreg043.EEWI002CommentsField.sendKeys("testing");
		commonFunction.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFunction.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 34 -- Click on Exit button on \"Workitem Completed Confirmation - (SUC-002)\" screen.");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFunction.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		test.info("Step: 35-- CSR Navigates to Main Menu -> MyQ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFunction.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 36-- search with fein number");
		//commonFunction.enterTextboxContains("FEIN", feinValuemanual);
		//commonFunction.clickButtonContains(" Search ");
		//Thread.sleep(2000);
		
		//click manually on work item "REVIEW POTENTIAL DUPLICATES"
		test.info("Step: 37-- Select \"Potential Duplicate Employer Task\"  link  from search result by clicking on under Work Item Description");
		commonFunction.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		
		commonFunction.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFunction.screenShot("Review Employer Type Task Details", "Pass", "EEWI-012 screen is visible");
		
		//EEWI-012
		commonFunction.selectDropdown("Quarter ", " 2 ");
		Thread.sleep(2000);
		commonFunction.selectDropdown("Year ", " 2022 ");
		sreg043.EEWI002CommentsField.sendKeys("testing");
		commonFunction.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFunction.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 40");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.ReviewpotentialDuplicatesSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFunction.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		test.info("Step: 41-- CSR Navigates to Main Menu -> MyQ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFunction.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 42-- search with fein number");
		//commonFunction.enterTextboxContains("FEIN", feinValuemanual);
		//commonFunction.clickButtonContains(" Search ");
		//Thread.sleep(2000);
		
		//click manually on work item "REVIEW Comments Task"
		test.info("Step: 43-- ");
		
		
	}

}
