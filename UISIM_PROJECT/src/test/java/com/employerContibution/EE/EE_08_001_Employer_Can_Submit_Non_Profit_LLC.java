package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.SREG_004;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_08_001_Employer_Can_Submit_Non_Profit_LLC extends TestBase {

	@Test
	public void EE_08_001() throws Exception {

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
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Liability Company ");
		String feinValue = "231943113"; // 401101502
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);

		/*----------------SREG-003----------------*/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("Random Test OP LLC");
		// commonFuntions.enterTextboxContains(" Business Phone Number ", "6732111111");
		commonFuntions.enterTextboxContains(" Business Phone Number  ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");

		commonFuntions.enterTextbox("Enter date of first operations in New York State", "8/18/2022");

		empPage.firstCalender_Quater.click();
		// empPage.firstCalender_Quater_Value.click();
		empPage.firstCalender_Quater_Value_1.click();

		empPage.firstCalender_Year.click();
		// empPage.firstCalender_Year_Value.click();
		empPage.firstCalender_Year_Value_2023.click();

		sleep();
		commonFuntions.screenShot("EmpRegister5", "Pass", "Entering the details");

		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_Yes_radio);
		sleep();
		commonFuntions.enterTextboxContains("Explain services that are", "Test value");
		sleep();
		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		empPage.What_firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();

		/*
		 * commonFuntions.safeJavaScriptClick(empPage.If_Not_Lible_Yes_Radio);
		 * commonFuntions.safeJavaScriptClick(empPage.DOes_Org_Have_Yes_Radio);
		 * commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		 * sleep(); commonFuntions.safeJavaScriptClick(empPage.Is_Reimbursable_Radio);
		 * sleep();
		 */

		//
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
				"If Reimbursable, has a copy of the 501c3 exemption documentation been provided?", "Yes ");

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
		commonFuntions.enterTextboxContains("Number of employees at this location", "100");
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

		// step-15
		sreg008.addAnothersreg007.click();
		Thread.sleep(2000);

		// step-16
		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFuntions.enterTextboxContains("City ", "albanys");
		// commonFuntions.selectDropdown("Country", " United States ");
		// commonFuntions.enterTextboxContains("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		commonFuntions.selectDropdown("County", " Albany ");
		sleep(1000);
		commonFuntions.enterTextboxContains("Number of employees at this location", "200");
		commonFuntions.enterTextboxContains("Name of Government Agency from which you receive funds", "test");
		commonFuntions.enterTextboxContains("Program Name", "testone");
		commonFuntions.selectDropdown("Principle purpose for which you are organized and operate.", " Other ");
		commonFuntions.enterTextboxContains("If Other, provide details", "testtwo");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			sleep(2000);
			empPage.continueButton_popUp.click();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		// step-19
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Employer Contact Details", "Pass", "SREG-004 screen is displayed");

		// step-20
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sreg004.addresslinelist.get(0).sendKeys("29 W 35th");
		sreg004.citylist.get(0).sendKeys("albany");
		sreg004.zipCodelist.get(0).sendKeys("12210");

		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		sreg004.listOfFirstname.get(0).sendKeys("FN");
		sreg004.listOfLastName.get(0).sendKeys("LN");

		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		sreg004.addresslinelist.get(2).sendKeys("29 W 35th");
		sreg004.citylist.get(2).sendKeys("albany");
		sreg004.zipCodelist.get(2).sendKeys("12210");

		sreg004.listOfFirstname.get(1).sendKeys("FN");
		sreg004.listOfLastName.get(1).sendKeys("LN");

		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");
		commonFuntions.selectRadioQuestions("Agent (C/O) address", "Other");
		sreg004.agadCareOfBtn.sendKeys("Grass");
		sreg004.addresslinelist.get(3).sendKeys("29 W 35th");
		sreg004.citylist.get(3).sendKeys("albany");
		sreg004.zipCodelist.get(3).sendKeys("12210");

		sreg004.listOfFirstname.get(2).sendKeys("FN");
		sreg004.listOfLastName.get(2).sendKeys("LN");

		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Employer Verify Contact Details", "Pass", "SREG-521 screen is displayed");

		// SREG-521
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "SREG-011 screen is displayed");

		// Step-22
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Add Member/Managing Member", "Pass", "SREG-006 screen is displayed");

		// Step-23
		commonFuntions.selectRadioQuestions("Type of Member/Managing Member", "Business Entity");
		commonFuntions.enterTextboxContains("Entity Name", "WALDMAN NEW INC");
		String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue2);
		commonFuntions.enterTextboxContains("Federal Identification Number (FEIN)", feinValue2);
		commonFuntions.selectDropdown("Title", " Managing Member ");

		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFuntions.enterTextboxContains("City ", "albany");
		// commonFuntions.selectDropdown("Country", " United States ");
		// commonFuntions.enterTextboxContains("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code", "12210");

		commonFuntions.enterTextboxContains(" Contact Number ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));

		commonFuntions.clickButtonContains("Continue ");
		try {
			commonFuntions.selectRadioQuestions(" Address", "29");
			Thread.sleep(2000);
			sreg004.popUpContinueButton.click();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		sleep(2000);
		commonFuntions.screenShot("Member/Managing Member Details", "Pass", "SREG-005 screen is displayed");
		//
		sreg004.addMemberLinkSreg005.click();
		commonFuntions.selectRadioQuestions("Type of Member/Managing Member", "Business Entity");
		commonFuntions.enterTextboxContains("Entity Name", "WALDMAN NEWW INC");
		String feinValue3 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue2);
		commonFuntions.enterTextboxContains("Federal Identification Number (FEIN)", feinValue3);
		commonFuntions.selectDropdown("Title", " Managing Member ");

		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFuntions.enterTextboxContains("City ", "albany");
		// commonFuntions.selectDropdown("Country", " United States ");
		// commonFuntions.enterTextboxContains("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code", "12210");

		commonFuntions.enterTextboxContains(" Contact Number ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));

		commonFuntions.clickButtonContains("Continue ");
		try {
			commonFuntions.selectRadioQuestions(" Address", "29");
			Thread.sleep(2000);
			sreg004.popUpContinueButton.click();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		//
		sleep(2000);
		commonFuntions.screenShot("Member/Managing Member Details", "Pass", "SREG-005 screen is displayed");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		// Step-26
		/*
		sreg084.browseLink.click();
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(2000);
		*/
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass",
				"Upload Document Section");
		Assert.assertTrue(sreg084.uploadDocSec.getText().contains("Uploaded Documents"));

		// Step-27
		commonFuntions.clickButton("Continue ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Registration Details", "Pass", "SREG-800 page displayed");

		// Step-28
		commonFuntions.clickButton("Continue ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "SREG-043 page displayed");

		// Step-29
		sreg043.submitterCommentsField.sendKeys("test work");
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Employer Registration Confirmation", "Pass", "SREG-013 page displayed");

		// step-30&31
		commonFuntions.clickButton("Home ");
		Thread.sleep(2000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("Homepage", "Pass", "Homapage page displayed");

		test.info("Step: 29-33 -- Login as a CSR and Navigates to Main Menu -> MyQ");
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
		// commonFuntions.enterTextboxContains("Date Covered Employment began? ",
		// "05/07/2022");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
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
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");

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
		
		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		empPage.whatFirstCalender_YearField.sendKeys("2023");

		commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep(2000);
		sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm ");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 48 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		/*commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
				*/
		Thread.sleep(2000);

		// Verify Agent reptask
		//
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		//
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		//
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-005 screen is visible");
        //
		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		empPage.whatFirstCalender_YearField.sendKeys("2023");
		
		commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		//
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
		//

		//Review comments task
		test.info("Step: 50 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 51 -- ");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		
		//sreg084.reviewCommentslink.click();

		// Step-52
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-005 screen is visible");

		// Step-53
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_1.click();
		empPage.firstCalender_YearField.sendKeys("2023");
		sleep();
		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		empPage.whatFirstCalender_YearField.sendKeys("2023");

		commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 54 -- ");
		Thread.sleep(2000);
	//	Assert.assertTrue(suc002.screenIdText.isDisplayed());
	//	Assert.assertTrue(suc002.reviewCommentsTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);

		test.info("Step: 55 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 55 -- ");
		// commonFuntions.enterTextboxContains("FEIN", feinValue);
		// commonFuntions.clickButtonContains(" Search ");
		// Thread.sleep(2000);

		// open manually Step-56 & 57

		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-016 screen is visible");

		// Step-58
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		// Step-59
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		test.info("Step: 46 -- ");
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

		// Step61
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "SREG-051 screen is displayed");

	}
}
