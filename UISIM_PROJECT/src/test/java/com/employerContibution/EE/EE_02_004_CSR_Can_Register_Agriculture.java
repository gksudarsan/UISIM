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
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_004_CSR_Can_Register_Agriculture extends TestBase {

	@Test
	public void EE_02_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_004 sreg004 = new SREG_004(driver);

		test = report.createTest(
				"EE.02.004 -Verify CSR can submit employer registration for employer type 'Agricultural' and legal entity type 'Guardianship' and work items will be created for CSR to review.");

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

		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		/*----------------Enter Query logic here----------------*/
		String ERN = "0449897";
		/*----------------Enter Query logic here----------------*/
		commonFuntions.screenShot("EmpRegister3", "Pass", "Filling the form");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		commonFuntions.selectDropdown("Type of Legal Entity", " Guardianship  Internal User Only ");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");

//		sleep(3000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Form filled and click on continue");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		/*-----------------SREG-003----------------*/

		commonFuntions.screenShot("EmpRegister5", "Pass", "Landed on SREG-003 page");

		empPage.legalNameTextBox.sendKeys("WT ACH CREDIT 1");
		//commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains("Ext", "091");
		
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Business Email Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "06/12/2022");
		//commonFuntions.enterTextboxContains("What is the date of the first payroll", "04/11/2023");
		commonFuntions.enterPastDate("What is the date of the first payroll", 7);
		
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_3.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Filling the form");
		//commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_radio);
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		commonFuntions.enterTextboxContains("Total number of covered employees", "10");

		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);

		/*-----------------SREG-008----------------*/

//		sleep(4000);
		commonFuntions.screenShot("EmpRegister7", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23432");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.enterTextboxContains("Number of employees at this location", "10");
		empPage.individualPrinciple.click();
		empPage.individualPrinciple_value_other.click();
//		commonFuntions.selectDropdown("Indicate your principal activity or", " Other ");
		//PEOPage.otherDetails_New1.sendKeys("other1");
		PEOPage.otherDetails1_New2.sendKeys("other2");

		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
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

		/*
		 * try { sreg008.firstradiobuttonVerifyAddPopup.click(); sleep(2000);
		 * empPage.continueButton_popUp.click(); sleep(2000);
		 * commonFuntions.screenShot("Business Physical Address Details", "Pass",
		 * "SREG-007 screen is displayed"); } catch (Exception e) {
		 * System.out.println("pop up not appeared"); }
		 */

		/*-----------------SREG-007----------------*/
		test.info("Step: 12 --");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister8", "Pass", "Navigated to SREG-007 page");
		empPage.addAnotherBusinessLink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		/*-----------------SREG-008----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister9", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.selectDropdown("County", " Allegany ");
		commonFuntions.safeJavaScriptClick(empPage.individualPrinciple);
		commonFuntions.safeJavaScriptClick(empPage.individualPrinciple_value_other);
		commonFuntions.enterTextboxContains("Number of employees at this location", "23");

//		commonFuntions.selectDropdown("Indicate your principal activity or", " Other ");
		//PEOPage.otherDetails_New1.sendKeys("other3");
		PEOPage.otherDetails1_New2.sendKeys("other4");
		commonFuntions.clickButtonContains("Continue ");
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
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		/*-----------------SREG-007----------------*/

//		sleep(4000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-007 page");
		
		/*-----------------SREG-004----------------*/
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


		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		try {
			commonFuntions.selectRadioQuestions("Agad Address", "123");
			commonFuntions.selectRadioQuestions("bmad Address", "123");
			commonFuntions.selectRadioQuestions("lbra Address", "123");
			commonFuntions.selectRadioQuestions("npca Address", "123");
			Thread.sleep(2000);
			sreg004.popUpContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		/*-----------------SREG-521----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-011----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-011 page");
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------Find Predecessor----------------*/
		/*-----------------Find Valid FEIN----------------*/
		Map<String, String> FEINOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;",
				"FEIN");
		String feinValue2 = FEINOutput.get("FEIN");
		System.out.println(feinValue2);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + feinValue2);

		/*-----------------Find Valid ENTITY NAME----------------*/
		Map<String, String> ENTITY_NAMEOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;",
				"ENTITY_NAME");
		String ENTITY_NAMEValue = ENTITY_NAMEOutput.get("ENTITY_NAME");
		System.out.println(ENTITY_NAMEValue);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + ENTITY_NAMEValue);
		
		/*-----------------Find Valid ERN----------------*/
		//ERN having account status suspended return mail
		Map<String, String> ERNOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSM' ORDER BY UPDATED_TS DESC;", "EAN");
		String eanValue2 = ERNOutput.get("EAN");
		System.out.println(ERN);
		test.log(Status.INFO, "ERN used on SREG-011 page : : " + eanValue2);
		
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue2); // 8606734
		//commonFuntions.enterTextboxContains("Legal Name of Business", ENTITY_NAMEValue);
		empPage.legalNameOfBusinessTextBox.sendKeys(ENTITY_NAMEValue);

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
//		commonFuntions.selectDropdown("County", " Allegancy ");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		//commonFuntions.enterTextboxContains("Acquisition Date", "04/01/2023");
		commonFuntions.enterPastDate("Acquisition Date", 10);
		//commonFuntions.enterTextboxContains("Notification date of Transfer", "04/02/2023");
		commonFuntions.enterPastDate("Notification date of Transfer", 1);
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
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		empPage.addAnotherAcquisitionLink.click();
		commonFuntions.waitForLoadingIconToDisappear();
		
		//
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		Map<String, String> databaseResults1 = PEOPage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;");
		
		String feinValue3 = databaseResults1.get("Fein");
		String EANnValue3 = databaseResults1.get("Ean");
		String ENTITY_NAMEValue3 = databaseResults1.get("legalName");
		System.out.println("The EAN Value is:" + feinValue3);
		test.log(Status.INFO, "Ean::" + feinValue3);
		System.out.println("The EAN Value is:" + EANnValue3);
		test.log(Status.INFO, "Ean::" + EANnValue3);
		System.out.println("The EAN Value is:" + ENTITY_NAMEValue3);
		test.log(Status.INFO, "Ean::" + ENTITY_NAMEValue3);

		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue3);
		commonFuntions.enterTextboxContains("Employer Registration Number", EANnValue3);
		empPage.legalNameOfBusinessTextBox.sendKeys(ENTITY_NAMEValue3);
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterPastDate("Acquisition Date", 7);
		commonFuntions.enterPastDate("Notification date of Transfer", 1);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);




		/*-----------------SREG-012----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-012----------------*/

		Map<String, String> databaseResults = peoPage
				.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN AND FEIN IS NOT NULL;");
		String feinValue4 = databaseResults.get("Fein");
		String eanValue4 = databaseResults.get("Ean");

		System.out.println("feinValue " + feinValue4);
		System.out.println("ernValue " + eanValue4);

//		sleep(3000);
		commonFuntions.screenShot("EmpRegister15", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.enterTextboxContains("Prior Federal Employer Identification Number (FEIN)", feinValue4);
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", eanValue4);
		commonFuntions.enterPastDate("Date of Legal Entity change", 7);
		commonFuntions.enterPastDate("Date of Notification", 1);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-006----------------*/
		// step-23
//		sleep(3000);
		Map<String, String> ssnOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT OWNER_TYPE,* FROM T_Employer_partner WHERE FIRST_NAME ='FN' AND LAST_NAME='LN';", "SSN");
		String ssn = ssnOutput.get("SSN");
		System.out.println(ssn);

		
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("First Name","FN"+ commonFuntions.createRandomString());
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.selectDropdown("Title", " Treasurer ");

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square 6");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24954");
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

		/*-----------------SREG-005----------------*/
		// step 25
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		commonFuntions.uploadDoc("Sample");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------SREG-800----------------*/
//		sleep(5000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------SREG-043----------------*/
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
//		sleep(15000);
		/*-----------------SREG-013----------------*/
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-013 success page and click on home");
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

		// Step-34
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);

		// Step-35
		sreg084.reviewemployertypelink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);
		sreg043.EEWI002CommentsField.sendKeys("Review em comment");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 37 -- ");
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

		test.info("Step: 38 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 39 -- DOL-DTF");
		Thread.sleep(2000);
		// sreg084.dolDTFlink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);

		test.info("Step: 41&42 -- DOL-DTF");
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
		
		test.info("Step: 43 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 44 -- ");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);
		
		test.info("Step: 45 -- ");
		sreg084.verifyTransferlink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);
		
		test.info("Step: 46 -- ");
		commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		Thread.sleep(2000);
		commonFuntions.selectCheckbox("Transfer Business Rules");
		commonFuntions.enterTextboxContains("Comment", "Ok");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 47 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
		
		test.info("Step: 48 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		
		test.info("Step: 49 -- ");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		Thread.sleep(2000);

	}
}
