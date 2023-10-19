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
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_007_CSR_Agricultural_Joint_Venture extends TestBase {

	@Test
	public void EE_02_007() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_004 sreg004 = new SREG_004(driver);

		test = report.createTest(
				"EE.02.007 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Joint Venture' and work items will be created for CSR to review.");

		// commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(),
		// COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 Page");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");

		sleep();
		/*----------------FEIN----------------*/
		// Map<String, String> output =
		// commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM
		// T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF
		// tedd ) GROUP BY FEIN HAVING COUNT(*)>1", "FEIN");
		// String feinValue = output.get("FEIN");
		String feinValue = "363735912";
		System.out.println(feinValue);
		test.log(Status.INFO, "FEIN : : " + feinValue);
		/*----------------FEIN----------------*/

		commonFuntions.screenShot("EmpRegister3", "Pass", "Filling the form");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Partnership ");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Form filled and click on continue");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-003----------------*/

		commonFuntions.screenShot("EmpRegister5", "Pass", "Landed on SREG-003 page");

		empPage.legalNameTextBox.sendKeys("Random Legal Name LLPN");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
//        commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
//        commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
//        commonFuntions.enterTextboxContains("Business Email Address", "test@test.com");
//        commonFuntions.enterPastDate("Enter date of first operations in New York State", 365);
//        commonFuntions.enterPastDate("What is the date of the first payroll which you withheld", 330);
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?",
				"No ");
//        empPage.firstCalender_Quater.click();
//        empPage.firstCalender_Quater_Value_2.click();
//        empPage.firstCalender_Year.click();
//        empPage.firstCalender_Year_Value_2023.click();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Filling the form");
//        commonFuntions.enterTextboxContains("Total number of covered employees", "20");
		commonFuntions.selectRadioQuestions(
				"If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?",
				"No ");
		sleep();
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-008----------------*/

		sleep(4000);
		commonFuntions.screenShot("EmpRegister7", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "123State");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23432");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.selectDropdown(
				"Indicate your principal activity or farm production that produces the greatest gross sales.",
				" Other ");
		commonFuntions.enterTextbox("If Other, provide details", "Test");
		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			sleep(2000);
			empPage.continueButton_popUp.click();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		/*-----------------SREG-007----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated on SREG-007 page and take screenshot");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-004----------------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep(3000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "123State");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23433");
		commonFuntions.selectDropdown("County", " Albany ");

		commonFuntions.selectRadioQuestions("Location of Books and Records",
				"Same as Primary Business Physical Address");
		commonFuntions.enterTextboxContains(" Telephone Number ", "(243)-254-3456");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		commonFuntions.clickButtonContains("Continue ");
		sreg004.addresslinelist.get(2).sendKeys("123state");
		sreg004.citylist.get(2).sendKeys("New York");
		sreg004.zipCodelist.get(2).sendKeys("34784");
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

		commonFuntions.waitForLoadingIconToDisappear();

		/*-----------------SREG-521----------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated on SREG-521 page and take screenshot");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-011----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-011 page");
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		/*-----------------Find Valid FEIN----------------*/

		Map<String, String> FEINOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT FEIN  FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS", "FEIN");
		String FEIN2 = FEINOutput.get("FEIN");
		System.out.println(FEIN2);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + FEIN2);
		// -----------------Find Valid FEIN----------------

		// -----------------Find Valid ERN----------------
		Map<String, String> ERNOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT EAN  FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CAND' AND EAN!='NULL' ORDER BY UPDATED_TS",
				"EAN");
		String ERN = ERNOutput.get("EAN");
		System.out.println(ERN);
		test.log(Status.INFO, "ERN used on SREG-011 page : : " + ERN);

		/*-----------------Find Valid ERN----------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN2); // FEIN2
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN); // ERN
		commonFuntions.enterTextboxContains("Legal Name of Business", "Other Enterprise");

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square 4");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterPastDate("Acquisition Date", 450);
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Notification date of Transfer");
		commonFuntions.clickButtonContains("Continue ");

		/*
		 * // added new empPage.addAnotherBusinessLink.click();
		 * commonFuntions.selectRadioQuestions(
		 * "Have you acquired the business of another employer liable for New York State Unemployment Insurance?"
		 * , "Yes ");
		 * 
		 * -----------------Find Predecessor---------------- String feinValue2 =
		 * StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))),
		 * 9); System.out.println(feinValue2);
		 * 
		 * ----------------FEIN---------------- Map<String, String> output =
		 * commonFuntions.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CNDP' ORDER BY UPDATED_TS DESC;"
		 * , "EAN"); String eanValue2 = output.get("EAN");
		 * System.out.println(eanValue2); test.log(Status.INFO, "FEIN : : " +
		 * eanValue2); commonFuntions.
		 * enterTextboxContains("Federal Employer Identification Number (FEIN)",
		 * feinValue2);
		 * commonFuntions.enterTextboxContains("Employer Registration Number",
		 * eanValue2); // 8606734
		 * commonFuntions.enterTextboxContains("Legal Name of Business",
		 * "Other Enterprise");
		 * 
		 * commonFuntions.enterTextboxContains("Address Line 1 ",
		 * "20 cooper square new"); commonFuntions.enterTextboxContains("City ", "NY");
		 * commonFuntions.enterTextboxContains("Zip Code", "24986"); //
		 * commonFuntions.selectDropdown("County", " All ");
		 * commonFuntions.screenShot("EmpRegister13", "Pass",
		 * "Entering the form Details"); commonFuntions.
		 * selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		 * commonFuntions.enterTextboxContains("Acquisition Date", "04/01/2023");
		 * commonFuntions.enterTextboxContains("Notification date of Transfer",
		 * "04/02/2023"); commonFuntions.clickButtonContains("Continue ");
		 * 
		 * //
		 * 
		 * -----------------SREG-012---------------- sleep(4000);
		 * commonFuntions.screenShot("EmpRegister14", "Pass",
		 * "Navigated to SREG-012 page and click on continue");
		 * commonFuntions.clickButton("Continue ");
		 */
		
		/*-----------------SREG-012----------------*/
		commonFuntions.screenShot("EmpRegister15", "Pass", "Entering the form Details");
		commonFuntions.clickButton("Continue ");

		/*-----------------SREG-006----------------*/
		/*
		 * 
		 * Map<String, String> ssnOutput =
		 * commonFuntions.database_SelectQuerySingleColumn(
		 * "SELECT t10.T10_SSN, t20.T20_FEIN, T10.T10_DATE_1, t10.T10_WAGE_1, T10.T10_DATE_2, t10.T10_WAGE_2,\r\n"
		 * +
		 * "T10.T10_DATE_3, t10.T10_WAGE_3, T10.T10_DATE_4, t10.T10_WAGE_4, T10.T10_DATE_5,\r\n"
		 * +
		 * "t10.T10_WAGE_5, T10.T10_DATE_6, t10.T10_WAGE_6, T10.T10_DATE_7, t10.T10_WAGE_7, T10.T10_DATE_8, t10.T10_WAGE_8 FROM\r\n"
		 * +
		 * "T_TWAGE10 t10 LEFT JOIN T_TWAGET20 t20 ON t10.T10_FEIN = t20.T20_FEIN WHERE t10.T10_WAGE_1> ('2900.00')\r\n"
		 * +
		 * "AND t10.T10_WAGE_2 > ('2900.00') AND t10.T10_WAGE_3 > ('2900.00') AND t10.T10_WAGE_4 > ('2900.00') AND\r\n"
		 * +
		 * "t10.T10_WAGE_5 > ('2900.00') AND t10.T10_WAGE_6 > ('2900.00') AND t10.T10_WAGE_7 > ('2900.00') AND t10.T10_WAGE_8 > ('2900.00');"
		 * , "T10_SSN"); String ssn = ssnOutput.get("T10_SSN"); System.out.println(ssn);
		 * test.log(Status.INFO, "SSN : : " + ssn);
		 */
		/*----------------------FEIN---------------------*/
		
		
		/*-------------------SREG-713-----------------*/
		
		commonFuntions.clickButtonContains("Continue ");
		
		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_SUBMIT_ISSUE_BENEFIT_CLAIM_PENALTY_ASSESSMENT ORDER BY UPDATED_TS DESC;" , "EAN"); 
		  String ssnValue = databaseResults1.get("CLAIMANT_SSN");
	        System.out.println(ssnValue);
		
		//SREG-006
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Partner/Owner", "Individual");

		commonFuntions.enterTextboxContains("SSN", ssnValue);
		commonFuntions.enterTextboxContains("First Name", "Abhi");
		commonFuntions.enterTextboxContains("Last Name", "Jan");
		commonFuntions.selectDropdown("Title", " Partner ");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square 4");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24954");
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
		commonFuntions.clickButton("Continue ");

		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		commonFuntions.uploadDoc("Sample");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-800----------------*/
		sleep(5000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButtonContains("Submit ");
		sleep(15000);
		/*-----------------SREG-013----------------*/
		commonFuntions.clickButtonContains("Home ");
		Thread.sleep(2000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("Homepage", "Pass", "Homapage page displayed");

		/*-----------------SREG-013----------------*/
		// Step-33
		commonFuntions.screenShot("Business Acquisition", "Pass", "logged In");
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		empPage.review_employer_My_Q.click();
//      sleep(3000);
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(2000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		empRegPage.EEWI002CommentsField.sendKeys("Review em comment");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 34 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);

		test.info("Step: 35 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 36 -- DOL-DTF");
		Thread.sleep(2000);
		// sreg084.dolDTFlink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);

		test.info("Step: 38 -- DOL-DTF");
		commonFuntions.selectRadioQuestions(
				"If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?",
				"No ");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("Account Status", " Liable ");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Suppress Correspondence?", "No ");
		sleep(2000);
		empRegPage.EEWI002CommentsField.sendKeys("Dol DTF Cm");
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

		test.info("Step: 40 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 41 -- ");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);

		test.info("Step: 42 -- ");
		empRegPage.verifyTransferlink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);

		test.info("Step: 43 -- ");
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		Thread.sleep(2000);
		// commonFuntions.selectCheckbox("Transfer Business Rules");
		commonFuntions.enterTextboxContains("Comment", "Ok");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 44 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);

		test.info("Step: 45 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 46 -- ");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);

	}
}