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
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_006_CSR_Can_Register_Agriculture_Limited_Partnership extends TestBase {

	@Test
	public void EE02_006() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		
		test = report.createTest(
				"EE.02.006 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Limited Partnership' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(1000);
		commonFuntions.ScrollMenu("Register Employer");
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickMenu("Register Employer");
		commonFuntions.screenShot("EmpRegister", "Pass", "Navigated to __ Page");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to __ Page");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
		/*----------------FEIN----------------*/
		// Map<String, String> output =
		// commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM
		// T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF
		// tedd ) GROUP BY FEIN HAVING COUNT(*)>1", "FEIN");
		// String feinValue = output.get("FEIN");
		String feinValue = "210000411";  // 363735912
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
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------SREG-003----------------*/

		commonFuntions.screenShot("EmpRegister5", "Pass", "Landed on SREG-003 page");

		empPage.legalNameTextBox.sendKeys("Random" + commonFuntions.createRandomString());
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Business Email Address", "test@test.com");
		commonFuntions.enterPastDate("Enter date of first operations in New York State", 365);
		commonFuntions.enterPastDate("What is the date of the first payroll which you withheld", 330);
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?",
				"No ");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_3.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();
		commonFuntions.enterTextboxContains("Total number of covered employees", "20");
		commonFuntions.selectRadioQuestions(
				"If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?",
				"No ");
		sleep();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Filling the form");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-008----------------*/

		sleep(4000);
		commonFuntions.screenShot("EmpRegister7", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23432");
		commonFuntions.selectDropdown("County", " Albany ");
		empPage.individualPrinciple.click();
		empPage.individualPrinciple_value_other.click();
		commonFuntions.enterTextbox("If Other, provide details", "Test");
		commonFuntions.screenShot("EmpRegister8", "Pass", "Filling the form");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}*/
		
		try {
			commonFuntions.selectRadioQuestions("business Address", "20");
			sleep(2000);
			sreg004.popUpContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(1000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		/*-----------------SREG-007----------------*/
		commonFuntions.screenShot("EmpRegister9", "Pass", "Navigated on SREG-007 page and take screenshot");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-004----------------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep(3000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "123state");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "12012");
		commonFuntions.selectDropdown("County", " Albany ");
		
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister10", "Pass", "Filling the details");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		try {
			commonFuntions.selectRadioQuestions("Agad Address", "123");
			commonFuntions.selectRadioQuestions("bmad Address", "123");
			commonFuntions.selectRadioQuestions("lbra Address", "123");
			commonFuntions.selectRadioQuestions("npca Address", "123");
			Thread.sleep(2000);
			commonFuntions.screenShot("EmpRegister11", "Pass", "Filling the details");
			sreg004.popUpContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		/*-----------------SREG-521----------------*/
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated on SREG-521 page and take screenshot");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-011----------------*/
	    commonFuntions.screenShot("EmpRegister13", "Pass", "Navigated to SREG-011 page");
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		Map<String, String> databaseResults1 = PEOPage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;");
		
		String feinValue2 = databaseResults1.get("Fein");
		String eanValue2 = databaseResults1.get("Ean");
		String ENTITY_NAMEValue3 = databaseResults1.get("legalName");
		System.out.println("The EAN Value is:" + feinValue2);
		test.log(Status.INFO, "Ean::" + feinValue2);
		System.out.println("The EAN Value is:" + eanValue2);
		test.log(Status.INFO, "Ean::" + eanValue2);
		System.out.println("The EAN Value is:" + ENTITY_NAMEValue3);
		test.log(Status.INFO, "Ean::" + ENTITY_NAMEValue3);

		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue2);
		empPage.legalNameOfBusinessTextBox.sendKeys(ENTITY_NAMEValue3);
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterPastDate("Acquisition Date", 7);
		commonFuntions.enterPastDate("Notification date of Transfer", 1);
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square 4");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterPastDate("Acquisition Date", 450);
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Notification date of Transfer");
		commonFuntions.screenShot("EmpRegister14", "Pass", "Filling the details");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		
		try {
			commonFuntions.selectRadioQuestions("business Address", "20");
			sleep(2000);
			sreg004.popUpContinueButton.click();
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to SREG-012 page and click on continue");
		empPage.addAnotherAcquisitionLink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		
		/*-----------------Find Valid FEIN----------------*/
		Map<String, String> FEINOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;", "FEIN");
		String FEIN2 = FEINOutput.get("FEIN");
		System.out.println(FEIN2);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + FEIN2);
		
		/*-----------------Find Valid ENTITY NAME----------------*/
		Map<String, String> ENTITY_NAMEOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;",
				"ENTITY_NAME");
		String ENTITY_NAMEValue = ENTITY_NAMEOutput.get("ENTITY_NAME");
		System.out.println(ENTITY_NAMEValue);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + ENTITY_NAMEValue);

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
		commonFuntions.enterTextboxContains("Legal Name of Business", ENTITY_NAMEValue);

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square 4");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		commonFuntions.enterDateOfCurrentQuaterFirstMonthPlusOneDay("Notification date of Transfer");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Filling the details");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		try {
			commonFuntions.selectRadioQuestions("business Address", "20");
			sleep(2000);
			sreg004.popUpContinueButton.click();
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		/*-----------------SREG-012----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-012 page and click on continue");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-012----------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering the form Details");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-006----------------*/
		
		/*

		Map<String, String> ssnOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT t10.T10_SSN, t20.T20_FEIN, T10.T10_DATE_1, t10.T10_WAGE_1, T10.T10_DATE_2, t10.T10_WAGE_2,\r\n"
						+ "T10.T10_DATE_3, t10.T10_WAGE_3, T10.T10_DATE_4, t10.T10_WAGE_4, T10.T10_DATE_5,\r\n"
						+ "t10.T10_WAGE_5, T10.T10_DATE_6, t10.T10_WAGE_6, T10.T10_DATE_7, t10.T10_WAGE_7, T10.T10_DATE_8, t10.T10_WAGE_8 FROM\r\n"
						+ "T_TWAGE10 t10 LEFT JOIN T_TWAGET20 t20 ON t10.T10_FEIN = t20.T20_FEIN WHERE t10.T10_WAGE_1> ('2900.00')\r\n"
						+ "AND t10.T10_WAGE_2 > ('2900.00') AND t10.T10_WAGE_3 > ('2900.00') AND t10.T10_WAGE_4 > ('2900.00') AND\r\n"
						+ "t10.T10_WAGE_5 > ('2900.00') AND t10.T10_WAGE_6 > ('2900.00') AND t10.T10_WAGE_7 > ('2900.00') AND t10.T10_WAGE_8 > ('2900.00');",
				"T10_SSN");
		String ssn = ssnOutput.get("T10_SSN");
		System.out.println(ssn);
		test.log(Status.INFO, "SSN : : " + ssn);
		*/
		Map<String, String> ssnOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT OWNER_TYPE,* FROM T_Employer_partner WHERE FIRST_NAME ='FN' AND LAST_NAME='LN';", "SSN");
		String ssn = ssnOutput.get("SSN");
		System.out.println(ssn);
		sleep(3000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Partner/Owner", "Individual");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("First Name", "FN"+ commonFuntions.createRandomString());
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.selectDropdown("Title", " Partner ");

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square 4");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24954");
		commonFuntions.screenShot("EmpRegister20", "Pass", "Filling the details");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		try {
			commonFuntions.selectRadioQuestions("entered address", "20");
			sleep(2000);
			sreg004.popUpContinueButton.click();
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		commonFuntions.waitForLoadingIconToDisappear();

		/*-----------------SREG-005----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister22", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		commonFuntions.uploadDoc("Sample");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------SREG-800----------------*/
		sleep(5000);
		commonFuntions.screenShot("EmpRegister23", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister24", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
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
//		sleep(3000);
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(2000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		sreg043.EEWI002CommentsField.sendKeys("Review em comment");
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
		sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		//
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
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
		sreg084.verifyTransferlink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		
		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);
		
		test.info("Step: 43 -- ");
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		Thread.sleep(2000);
		//commonFuntions.selectCheckbox("Transfer Business Rules");
		commonFuntions.enterTextboxContains("Comment", "Ok");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 44 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
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
