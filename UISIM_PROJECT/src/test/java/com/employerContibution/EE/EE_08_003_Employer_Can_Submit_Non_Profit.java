package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SUC_002;
import com.ui.pages.SREG_084;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_08_003_Employer_Can_Submit_Non_Profit extends TestBase {
	@Test
	public void EE_08_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_084 sreg084 = new SREG_084(driver);

		test = report.createTest(
				"EE.08.003 Verify employer can submit employer registration for employer type 'Non-Profit' and legal entity type 'Unincorporated Association' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		// homePage.menu.click();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();

		// Step-4
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "rider");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Contact Telephone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Email Address", "test2@Test.com");
		sleep();
		commonFuntions.screenShot("EmpRegister11", "Pass", "Entered the details and click on continue button");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// Step-5
		/**************** SREG-025 ************************/
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);

		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Non-Profit ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Unincorporated Association ");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue); // Random Fein
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();

		// Step-6
		/*----------------SREG-003----------------*/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.sreg003_legalNameField.sendKeys("TRANSCOM MEDIA INC"); // LEGAL NAME MULT MATCH in DOl & DTF
		commonFuntions.enterTextboxContains(" Business Phone Number  ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));

		// commonFuntions.enterTextboxContains("Business Email Address",
		// "autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999))
		// + "@gmail.com");

		commonFuntions.enterTextbox("Enter date of first operations in New York State", "07/1/2021");

		sleep(3000);

		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value.click();
		sleep();
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?",
				"No ");

		commonFuntions.screenShot("EmpRegister5", "Pass", "Entering the details");
		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_3.click();
		empPage.What_firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2022.click();

		/*
		 * commonFuntions.safeJavaScriptClick(empPage.If_Not_Lible_Radio);
		 * commonFuntions.safeJavaScriptClick(empPage.DOes_Org_Have_Radio);
		 * commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Radio);
		 */

		commonFuntions.selectRadioQuestions(
				"If you are not liable under the Unemployment Insurance law for nonprofit employment, do you wish to elect voluntary coverage?",
				"No ");
		commonFuntions.selectRadioQuestions(
				"Does this organization have, or have they applied for, a Nonprofit 501 (c)(3) exemption with the Internal Revenue Service?",
				"No ");
		// commonFuntions.selectRadioQuestions("Choose the option you wish to",
		// "Contributory");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-008 page");
		// Step-7
		commonFuntions.enterTextboxContains("Address Line 1 ", "123state");
		commonFuntions.enterTextboxContains("City ", "albany");
		// commonFuntions.selectDropdown("Country", " United States ");
		// commonFuntions.enterTextboxContains("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code", "12012");
		commonFuntions.selectDropdown("County", " Albany ");
		sleep(1000);
		commonFuntions.enterTextboxContains("Number of employees at this location", "87");
		// commonFuntions.enterTextboxContains("Name of Government Agency from which you
		// receive funds", "test");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFuntions.screenShot("verify address", "Pass", "Navigated on SREG-008 page");

		// Step-8
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			sleep(2000);
			empPage.continueButton_popUp.click();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		// Step-9
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Employer Contact Details", "Pass", "SREG-004 screen is displayed");

		// Step-10
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sreg004.addresslinelist.get(0).sendKeys("123state");
		sreg004.citylist.get(0).sendKeys("albany");
		sreg004.zipCodelist.get(0).sendKeys("12012");

		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		sreg004.addresslinelist.get(1).sendKeys("123state");
		sreg004.citylist.get(1).sendKeys("albany");
		sreg004.zipCodelist.get(0).sendKeys("12012");
		sreg004.listOfFirstname.get(0).sendKeys("FN");
		sreg004.listOfLastName.get(0).sendKeys("LN");

		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		sreg004.addresslinelist.get(2).sendKeys("123state");
		sreg004.citylist.get(2).sendKeys("albany");
		sreg004.zipCodelist.get(2).sendKeys("12012");

		sreg004.listOfFirstname.get(1).sendKeys("FN");
		sreg004.listOfLastName.get(1).sendKeys("LN");

		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");

		commonFuntions.selectRadioQuestions("Agent (C/O) address", "Other");
		// sreg004.agadCareOfBtn.sendKeys(commonFuntions.createRandomInteger(10, 99) +
		// "randomCareOf"
		// + commonFuntions.createRandomInteger(10, 99));
		sreg004.addresslinelist.get(3).sendKeys("123state");
		sreg004.citylist.get(3).sendKeys("albany");
		sreg004.zipCodelist.get(3).sendKeys("12012");

		sreg004.listOfFirstname.get(2).sendKeys("FN");
		sreg004.listOfLastName.get(2).sendKeys("LN");

		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// Step-11
		try {
			commonFuntions.selectRadioQuestions("Agad Address", "123");
			commonFuntions.selectRadioQuestions("bmad Address", "123");
			commonFuntions.selectRadioQuestions("lbra Address", "123");
			commonFuntions.selectRadioQuestions("npca Address", "123");
			Thread.sleep(2000);
			sreg004.popUpContinueButton.click();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Employer Verify Contact Details", "Pass", "SREG-011 screen is displayed");

		// Step-12
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");

		/*-----------------Find Valid FEIN----------------*/
		// String FEIN2 = StringUtils.left(String.valueOf((long) (Math.random() *
		// Math.pow(10, 10))), 9);
		Map<String, String> FEINOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;",
				"FEIN");
		String FEIN2 = FEINOutput.get("FEIN");
		System.out.println(FEIN2);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + FEIN2);
		/*-----------------Find Valid FEIN----------------*/

		/*-----------------Find Valid ERN----------------*/
		Map<String, String> ERNOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CANE' AND EAN!='NULL' ORDER BY UPDATED_TS",
				"EAN");
		String ERN = ERNOutput.get("EAN");
		System.out.println(ERN);
		test.log(Status.INFO, "ERN used on SREG-011 page : : " + ERN);
		/*-----------------Find Valid ERN----------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN2);
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		// empPage.legalNameOfBusinessTextBox.sendKeys("Other Enterprisee");

		commonFuntions.enterTextboxContains("Address Line 1 ", "123state");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12012");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterTextboxContains("Acquisition Date", "12/22/2022"); // 2 quaters prior from current quater
		// commonFuntions.enterTextboxContains("Notification date of Transfer",
		// "07/11/2023"); // current date
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// Step-14
		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		empPage.addAnotherAcquisitionLink.click();
		commonFuntions.waitForLoadingIconToDisappear();

		// Step-15
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		commonFuntions.waitForLoadingIconToDisappear();

		// String feinValue3 = StringUtils.left(String.valueOf((long) (Math.random() *
		// Math.pow(10, 10))), 9);
		Map<String, String> FEIN2Output = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;",
				"FEIN");
		String feinValue3 = FEIN2Output.get("FEIN");
		System.out.println(feinValue3);

		/*----------------FEIN----------------*/
		// String eanValue3 = StringUtils.left(String.valueOf((long) (Math.random() *
		// Math.pow(10, 10))), 7);
		// System.out.println(eanValue3);
		// test.log(Status.INFO, "FEIN : : " + eanValue3);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue3);
		// commonFuntions.enterTextboxContains("Employer Registration Number",
		// eanValue3);
		// empPage.legalNameOfBusinessTextBox.sendKeys("Otherr Enterpriseit");

		commonFuntions.enterTextboxContains("Address Line 1 ", "123state");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12012");
//		commonFuntions.selectDropdown("County", " Allegancy ");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterTextboxContains("Acquisition Date", "12/21/2022"); // 2 quaters prior from current quater
		// commonFuntions.enterTextboxContains("Notification date of Transfer",
		// "07/11/2023");
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-012 page and click on continue");

		// Step-17
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and click on continue");

		// Step-18
		// String ssn = StringUtils.left(String.valueOf((long) (Math.random() *
		// Math.pow(10, 10))), 9);
		// System.out.println(ssn);
		Map<String, String> ssnOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT OWNER_TYPE,* FROM T_Employer_partner WHERE FIRST_NAME ='FN' AND LAST_NAME='LN';", "SSN");
		String ssn = ssnOutput.get("SSN");
		System.out.println(ssn);

		sleep(3000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("First Name", "FN");
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.selectDropdown("Title", " Treasurer ");

		commonFuntions.enterTextboxContains("Address Line 1 ", "123state");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12012");
		commonFuntions.enterTextboxContains(" Residential Telephone Number ", "1234567890");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);

		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_123_state);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		// Step-20
		/*-----------------SREG-005----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-683 page and do not upload the document");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------SREG-800----------------*/
		sleep(5000);
		commonFuntions.screenShot("EmpRegister22", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		// Step-23
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		// sreg043.submitterCommentsField.sendKeys("test work");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		// sleep(15000);

		/*-----------------SREG-013----------------*/
		// Step-24
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Homepage", "Pass", "Homapage page displayed");

		/*
		 * commonFuntions.
		 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" +
		 * COMMON_CONSTANT.CSR_USER_1 +
		 * "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
		 * + feinValue + "' ORDER BY UPDATED_TS desc)");
		 */

		test.info("Step: 25 -- Login as a CSR and Navigates to Main Menu -> MyQ");
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.screenShot("Business Acquisition", "Pass", "logged In");
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 26 -- Click on Search button using fein number");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(2000);
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);

		test.info(
				"Step: 27&28 -- Select  \"Review Employer Type Task\"  from the search result by clicking on Work Item Description hyperlink");

		sreg084.reviewemployertypelink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		// commonFuntions.clickOnLink("Review Employer Type");
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");

		test.info("Step: 29 -- Enter/Select \"Review Employer Type Task (EEWI-002)\" screen with below details");

		sleep(2000);

		// empPage.What_firstCalender_Quater.click();
		// empPage.firstCalender_Quater_Value_2.click();
		// empPage.What_firstCalender_Year.click();
		// empPage.firstCalender_Year_Value_2023.click();
		Thread.sleep(1000);

		sreg043.EEWI002CommentsField.sendKeys("testing work item");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 30 -- Click on Exit button on \"Workitem Completed Confirmation - (SUC-002)\" screen.");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		/*
		 * commonFuntions.
		 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" +
		 * COMMON_CONSTANT.CSR_USER_1 +
		 * "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
		 * + feinValue + "' ORDER BY UPDATED_TS desc)");
		 */

		// Step 39 DOL DTF Task
		// commonFuntions.enterTextboxContains("FEIN", feinValue);
		// commonFuntions.clickButtonContains(" Search ");
		// Thread.sleep(2000);

		// Open manually dol task
		// Step-40
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("DOL-DTF Task Details", "Pass", "EEWI-005 screen is visible");

		// Step-41 EEWI-005 Screen

		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_3.click();
		empPage.whatFirstCalender_YearField.sendKeys("2022");

		// empPage.legalNameOfBusinessTextBoxEEWI_005.sendKeys("NEW TRANSCOM MEDIA
		// INC");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep(2000);
		sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 35 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		/*
		 * commonFuntions.
		 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" +
		 * COMMON_CONSTANT.CSR_USER_1 +
		 * "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
		 * + feinValue + "' ORDER BY UPDATED_TS desc)");
		 */

		// Review potential duplicates
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(2000);
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		commonFuntions.clickOnLink("Review potential Duplicates");
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review potential duplicates Task Details", "Pass", "EEWI-006 screen is visible");
		sreg043.EEWI012CommentFeild.sendKeys("testing work");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		// Validate partial transfer failed rules
		// Step-36&37&38
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		//
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(2000);
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		commonFuntions.clickOnLink("Verify Transfer Failed Rules");
		//
		// open work item
		//

		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);

		// Step-39
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		sreg043.EEWI014CommentFeild.sendKeys("testing work");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);

		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		// Step-40
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		/*
		 * commonFuntions.
		 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" +
		 * COMMON_CONSTANT.CSR_USER_1 +
		 * "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
		 * + feinValue + "' ORDER BY UPDATED_TS desc)");
		 */

		// 4.Resolve incomplete data transfer task

	}
}
