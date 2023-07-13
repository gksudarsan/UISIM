package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_08_002_Employer_Can_Submit_Non_Profit extends TestBase {

	@Test
	public void EE_08_002() throws Exception {

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);

		test = report.createTest(
				"EE.08.001-Verify employer can submit employer registration for employer type 'Non-Profit' and legal entity type 'Limited Liability Company (All Types)' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		// homePage.menu.click();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);

		// Step-4
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Willam");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Contact Telephone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Email Address", "test@Test.com");
		sleep();
		commonFuntions.screenShot("EmpRegister11", "Pass", "Entered the details and click on continue button");
		commonFuntions.clickButtonContains("Continue ");

		/**************** SREG-025 ************************/
		// Step-5
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Non-Profit ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		String feinValue = "231943113";
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);

		/*----------------SREG-003----------------*/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("SUNSET BAY BEACH"); // LEGAL NAME MULT MATCH  
		// commonFuntions.enterTextboxContains(" Business Phone Number ", "6732111111");
		commonFuntions.enterTextboxContains(" Business Phone Number  ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");

		commonFuntions.enterTextbox("Enter date of first operations in New York State", "9/20/2021");

		empPage.firstCalender_Quater.click();
		// empPage.firstCalender_Quater_Value.click();
		empPage.firstCalender_Quater_Value_2.click();

		empPage.firstCalender_Year.click();
		// empPage.firstCalender_Year_Value.click();
		empPage.firstCalender_Year_Value_2022.click();

		sleep();
		commonFuntions.screenShot("EmpRegister5", "Pass", "Entering the details");

		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?",
				"No ");
		sleep();

		commonFuntions.selectRadioQuestions(
				"If you are not liable under the Unemployment Insurance law for nonprofit employment, do you wish to elect voluntary coverage?",
				"Yes ");
		commonFuntions.selectRadioQuestions(
				"Does this organization have, or have they applied for, a Nonprofit 501 (c)(3) exemption with the Internal Revenue Service?",
				"Yes ");
		commonFuntions.selectRadioQuestions(
				"Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Reimbursable");
		commonFuntions.selectRadioQuestions(
				"If Reimbursable, has a copy of the 501c3 exemption documentation been provided?", "No ");

		///
		commonFuntions.screenShot("EmpRegister6", "Pass", "Entered he details and click continue");
		commonFuntions.clickButtonContains("Continue ");
		sleep();

		// SREG-008
		// Step-11
		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFuntions.enterTextboxContains("City ", "albany");
		// commonFuntions.selectDropdown("Country", " United States ");
		// commonFuntions.enterTextboxContains("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		commonFuntions.selectDropdown("County", " Albany ");
		sleep(1000);
		commonFuntions.enterTextboxContains("Number of employees at this location", "58");
		commonFuntions.enterTextboxContains("Name of Government Agency from which you receive funds", "test");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		// step-14
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			sleep(2000);
			empPage.continueButton_popUp.click();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		/*
		 * // step-15 sreg008.addAnothersreg007.click(); Thread.sleep(2000);
		 * 
		 * // step-16 commonFuntions.enterTextboxContains("Address Line 1 ",
		 * "29 W 35th 2nd"); commonFuntions.enterTextboxContains("City ", "albanys"); //
		 * commonFuntions.selectDropdown("Country", " United States "); //
		 * commonFuntions.enterTextboxContains("State", " New York ");
		 * commonFuntions.enterTextboxContains("Zip Code", "12210");
		 * commonFuntions.selectDropdown("County", " Albany "); sleep(1000);
		 * commonFuntions.enterTextboxContains("Number of employees at this location",
		 * "200"); commonFuntions.
		 * enterTextboxContains("Name of Government Agency from which you receive funds"
		 * , "test"); commonFuntions.enterTextboxContains("Program Name", "testone");
		 * commonFuntions.
		 * selectDropdown("Principle purpose for which you are organized and operate.",
		 * " Other "); commonFuntions.enterTextboxContains("If Other, provide details",
		 * "testtwo"); commonFuntions.clickButtonContains("Continue "); sleep(2000);
		 * 
		 * try { sreg008.firstradiobuttonVerifyAddPopup.click(); sleep(2000);
		 * empPage.continueButton_popUp.click(); sleep(2000);
		 * commonFuntions.screenShot("Business Physical Address Details", "Pass",
		 * "SREG-007 screen is displayed"); } catch (Exception e) {
		 * System.out.println("pop up not appeared"); }
		 */
		// step-19
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Employer Contact Details", "Pass", "SREG-004 screen is displayed");

		// step-20
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sreg004.addresslinelist.get(0).sendKeys("29 W 35th");
		sreg004.citylist.get(0).sendKeys("albany");
		sreg004.zipCodelist.get(0).sendKeys("12210");

		commonFuntions.selectRadioQuestions("Location of Books and Records",
				"Same as Primary Business Physical Address");
		sreg004.listOfFirstname.get(0).sendKeys("FN");
		sreg004.listOfLastName.get(0).sendKeys("LN");

		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		sreg004.addresslinelist.get(2).sendKeys("29 W 35th");
		sreg004.citylist.get(2).sendKeys("albany");
		sreg004.zipCodelist.get(2).sendKeys("12210");

		sreg004.listOfFirstname.get(1).sendKeys("FN");
		sreg004.listOfLastName.get(1).sendKeys("LN");

		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
		/*
		 * commonFuntions.selectRadioQuestions("Agent (C/O) address", "Other");
		 * sreg004.agadCareOfBtn.sendKeys("Grass");
		 * sreg004.addresslinelist.get(3).sendKeys("29 W 35th 3rd");
		 * sreg004.citylist.get(3).sendKeys("albany");
		 * sreg004.zipCodelist.get(3).sendKeys("12210");
		 * 
		 * sreg004.listOfFirstname.get(2).sendKeys("FN");
		 * sreg004.listOfLastName.get(2).sendKeys("LN");
		 */
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Employer Verify Contact Details", "Pass", "SREG-521 screen is displayed");

		// SREG-521
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "SREG-011 screen is displayed");

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		/*-----------------Find Valid FEIN----------------*/
		String FEIN2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(FEIN2);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + FEIN2);
		/*-----------------Find Valid FEIN----------------*/

		/*-----------------Find Valid ERN----------------*/
		Map<String, String> ERNOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT EAN  FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CAND' AND EAN!='NULL' ORDER BY UPDATED_TS",
				"EAN");
		String ERN = ERNOutput.get("EAN");
		System.out.println(ERN);
		test.log(Status.INFO, "ERN used on SREG-011 page : : " + ERN);
		/*-----------------Find Valid ERN----------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN2);
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		empPage.legalNameOfBusinessTextBox.sendKeys("Other Enterprise"); 

		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterTextboxContains("Acquisition Date", "09/15/2022");
		commonFuntions.enterTextboxContains("Notification date of Transfer", "07/10/2023");
		commonFuntions.clickButtonContains("Continue ");

		// Step-13
		empPage.addAnotherAcquisitionLink.click();

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");

		String feinValue3 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue3);

		/*----------------FEIN----------------*/
		String eanValue3 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		System.out.println(eanValue3);
		test.log(Status.INFO, "FEIN : : " + eanValue3);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue3);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue3);
		empPage.legalNameOfBusinessTextBox.sendKeys("Otherr Enterprise"); 

		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12210");
//		commonFuntions.selectDropdown("County", " Allegancy ");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterTextboxContains("Acquisition Date", "09/15/2022");
		commonFuntions.enterTextboxContains("Notification date of Transfer", "07/10/2023");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-012----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		commonFuntions.clickButton("Continue ");

		// Step-17
		/*-----------------SREG-006----------------*/
		String ssn = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(ssn);
		sleep(3000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");

		commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("First Name", "FN");
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.selectDropdown("Title", " Treasurer ");

		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		commonFuntions.enterTextboxContains(" Residential Telephone Number ", "1234567890");
		commonFuntions.clickButtonContains("Continue ");

		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}

		commonFuntions.waitForLoadingIconToDisappear();

		/*-----------------SREG-005----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and do not upload the document");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-800----------------*/
		sleep(5000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		//sreg043.submitterCommentsField.sendKeys("test work");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButtonContains("Submit ");
		sleep(15000);
		/*-----------------SREG-013----------------*/
		// Step-23
		commonFuntions.clickButtonContains("Home ");
		Thread.sleep(2000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("Homepage", "Pass", "Homapage page displayed");

		test.info("Step: 24&25 -- Login as a CSR and Navigates to Main Menu -> MyQ");
		// commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(),
		// COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("Business Acquisition", "Pass", "logged In");
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 34 -- Click on Search button using fein number");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);

		test.info(
				"Step: 35 -- Select  \"Review Employer Type Task\"  from the search result by clicking on Work Item Description hyperlink");
		// commonFunction.selectTableWithoutId("Work Item Description", 1, 1,
		// "Individual Work Queue");
		// sreg084.reviewemployertypelink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");

		test.info("Step: 36 -- Enter/Select \"Review Employer Type Task (EEWI-002)\" screen with below details");
		commonFuntions.selectDropdown("Account Status", " Future ");
		sleep(2000);

		// empPage.What_firstCalender_Quater.click();
		// empPage.firstCalender_Quater_Value_2.click();
		// empPage.What_firstCalender_Year.click();
		// empPage.firstCalender_Year_Value_2023.click();
		Thread.sleep(1000);

		sreg043.EEWI002CommentsField.sendKeys("testing work item");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 37 -- Click on Exit button on \"Workitem Completed Confirmation - (SUC-002)\" screen.");
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

		test.info("Step: 38-- CSR Navigates to Main Menu -> MyQ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

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

		// empPage.What_firstCalender_Quater.click();
		// empPage.firstCalender_Quater_Value_2.click();
		// empPage.whatFirstCalender_YearField.sendKeys("2023");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep(2000);
		sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 48 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");

		Thread.sleep(2000);

		// Step- 35 &36 & 37
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		//
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		//
		// open work item
		//
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);

		// Step-38
		commonFuntions.screenShot("Potential Duplicate Employer Task", "Pass", "EEWI-012 screen is visible");
		sreg043.EEWI002CommentsField.sendKeys("testing work item");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 39 -- ");
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

		Thread.sleep(2000);

		// Normalize Address
		// Step 40 & 41 & 42
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		//
		// commonFuntions.enterTextboxContains("FEIN", feinValue);
		// commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		//
		// open work item
		//
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Normalize", "Pass", "EEWI-011 screen is visible");

		test.info("Step: 43 & 44 & 45 -- ");
		sreg043.EEWI011EditButtonList.get(0).click();
		Thread.sleep(2000);
		commonFuntions.screenShot("Normalize Address", "Pass", "EEWI-011 Popup Window screen is visible");
		commonFuntions.ScrollMenu(" NORMALIZE ");
		sreg043.EEWI011NormalizeBtn.click();
		commonFuntions.screenShot("Normalize Address", "Pass", "EEWI-011 screen is visible");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 46 -- ");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");

		Thread.sleep(2000);
		test.info("Step: 47& 48 & 49 -- ");
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		 commonFuntions.enterTextboxContains("FEIN", feinValue);
		 commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		//
		// sreg084.verifyTransferlink.click();
		//
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		
		//Step-50
		commonFuntions.screenShot("Validate Total/Partial Transfer Failed Rules Task", "Pass", "EEWI-014 screen is visible");

		sreg043.EEWI014CommentFeild.sendKeys("testing work");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 51 -- ");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");

		Thread.sleep(2000);
		
		test.info("Step: 52& 53 & 54 -- ");
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		 commonFuntions.enterTextboxContains("FEIN", feinValue);
		 commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		//
		// sreg084.verifyTransferlink.click();
		//
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		
		//Step-55
		commonFuntions.screenShot("Validate Total/Partial Transfer Failed Rules Task", "Pass", "EEWI-014 screen is visible");

		sreg043.EEWI014CommentFeild.sendKeys("testing workk");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 56 -- ");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		test.info("Step: 57 -- ");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		sleep(2000);
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "SREG-050 screen is displayed");
		sleep(2000);

		//Step: 58&59&60&61 
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		
		sleep(2000);
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "SREG-051 screen is displayed");
		
	}
}
