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

		test = report.createTest(
				"EE.02.004 -Verify CSR can submit employer registration for employer type 'Agricultural' and legal entity type 'Guardianship' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButtonContains("Continue ");
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to __ Page");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
//		sleep();
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
		/*-----------------SREG-003----------------*/

		commonFuntions.screenShot("EmpRegister5", "Pass", "Landed on SREG-003 page");

		empPage.legalNameTextBox.sendKeys("FUL STUDIO INC");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Business Email Address", "test@test.com");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "01/12/2023");
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "04/11/2023");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Filling the form");
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_radio);
		commonFuntions.enterTextboxContains("Total number of covered employees", "10");

		commonFuntions.clickButtonContains("Continue ");

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
		PEOPage.otherDetails_New1.sendKeys("other1");
		PEOPage.otherDetails1_New2.sendKeys("other2");

		commonFuntions.clickButtonContains("Continue ");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
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
		PEOPage.otherDetails_New1.sendKeys("other3");
		PEOPage.otherDetails1_New2.sendKeys("other4");
		commonFuntions.clickButtonContains("Continue ");
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}

		/*-----------------SREG-007----------------*/

//		sleep(4000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-007 page");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		empPage.addressLine1_Form1.sendKeys("20 cooper square");
		empPage.city_Form1.sendKeys("NY");
		empPage.zipCode_Form1.sendKeys("32424");
		empPage.countyDropDown_Form1_SREG_004.click();
		empPage.countyValue_Form1.click();

		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");

//		empPage.addresLine1_Form2_SREG_004.sendKeys("20 cooper");

		commonFuntions.clickButtonContains("Continue ");

		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_Cooper);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}

		/*-----------------SREG-521----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-011----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-011 page");
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		/*-----------------Find Predecessor----------------*/

		/*-----------------Find Predecessor----------------*/
		String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue2);

		/*----------------FEIN----------------*/
		Map<String, String> output = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT FEIN  FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd GROUP BY FEIN HAVING COUNT(*)>1 )",
				"EAN");
		String eanValue2 = output.get("EAN");
		System.out.println(eanValue2);
		test.log(Status.INFO, "FEIN : : " + eanValue2);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue2); // 8606734
		commonFuntions.enterTextboxContains("Legal Name of Business", "Other Enterprise");

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square new");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
//		commonFuntions.selectDropdown("County", " Allegancy ");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterTextboxContains("Acquisition Date", "04/01/2023");
		commonFuntions.enterTextboxContains("Notification date of Transfer", "04/02/2023");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-012----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-012----------------*/

		Map<String, String> databaseResults = peoPage
				.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN AND FEIN IS NOT NULL;");
		String feinValue3 = databaseResults.get("Fein");
		String eanValue3 = databaseResults.get("Ean");

		System.out.println("feinValue " + feinValue3);
		System.out.println("ernValue " + eanValue3);

//		sleep(3000);
		commonFuntions.screenShot("EmpRegister15", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		commonFuntions.enterTextboxContains("Prior Federal Employer Identification Number (FEIN)", feinValue3);
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", eanValue3);
		commonFuntions.enterTextboxContains("Date of Legal Entity change", "04/03/2023");
		commonFuntions.enterTextboxContains("Date of Notification", "04/04/2023");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-006----------------*/
		// step-23
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");

		commonFuntions.enterTextboxContains("SSN", "453758964");
		commonFuntions.enterTextboxContains("First Name", "Abhi");
		commonFuntions.enterTextboxContains("Last Name", "Jan");
		commonFuntions.selectDropdown("Title", " Officer ");

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square new 2");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24944");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-005----------------*/
		// step 25
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButtonContains("Continue ");

		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		commonFuntions.uploadDoc("Sample");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-800----------------*/
//		sleep(5000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButtonContains("Continue ");
		/*-----------------SREG-043----------------*/
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.clickButtonContains("Submit ");
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
