package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
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
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_02_005_CSR_Can_Register_Agriculture_Limited_liability_Partnership extends TestBase {

	@Test
	public void EE_02_005() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		employerManagement empmanagementPage = new employerManagement(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_008 sreg008 = new SREG_008(driver);

		test = report.createTest(
				"EE.02.005 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Limited Liability Partnership' and work items will be created for CSR to review.");
		sleep(2000);
		commonFuntions.login(COMMON_CONSTANT.REGISTRATION_SPECIALIST.toUpperCase(), COMMON_CONSTANT.REGISTRATION_SPECIALIST_PASSWORD);
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
		sleep();

		/*----------------FEIN----------------*/
		// Map<String, String> output =
		// commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM
		// T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF
		// tedd GROUP BY FEIN HAVING COUNT(*)>1 )", "FEIN");
		// String feinValue = output.get("FEIN");
		String feinValue = "363735912";
		System.out.println(feinValue);
		test.log(Status.INFO, "FEIN : : " + feinValue);
		/*----------------FEIN----------------*/

		commonFuntions.screenShot("EmpRegister3", "Pass", "Filling the form");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Liability Partnership ");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Form filled and click on continue");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);

		/*-----------------SREG-003----------------*/

		commonFuntions.screenShot("EmpRegister5", "Pass", "Landed on SREG-003 page");

		empPage.legalNameTextBox.sendKeys("Random" + commonFuntions.createRandomString()+ "LLP");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Business Email Address", "test2@test.com");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Enter date of first operations in New York State");
//		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "01122023");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_4.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Filling the form");
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
		empPage.individualPrinciple.click();
		empPage.individualPrinciple_value_other.click();
		commonFuntions.enterTextbox("If Other, provide details", "Test");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		try {
			commonFuntions.selectRadioQuestions("business Address", "20");
			sleep(2000);
			sreg004.popUpContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		/*-----------------SREG-007----------------*/
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
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

		// commonFuntions.safeJavaScriptClick(empPage.location_Of_Book_County);
		// commonFuntions.safeJavaScriptClick(empPage.albany_County_Value);

		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");
		commonFuntions.selectRadioQuestions("Agent (C/O) address", "Other");

		Map<String, String> CARE_OF_Output = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_CONTACT WHERE CARE_OF IS NOT NULL AND CARE_OF != 'LEGACY' ORDER BY UPDATED_TS DESC;",
				"CARE_OF");
		String careOfValue = CARE_OF_Output.get("CARE_OF");
		System.out.println(careOfValue);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + careOfValue);

		// sreg004.agadCareOfBtn.sendKeys(commonFuntions.createRandomInteger(10, 99) +
		// "randomCareOf"
		// + commonFuntions.createRandomInteger(10, 99));

		//sreg004.agadCareOfBtn.sendKeys(careOfValue); //Required as per the new changes in TC

		sreg004.addresslinelist.get(3).sendKeys("123state");
		sreg004.citylist.get(3).sendKeys("albany");
		sreg004.zipCodelist.get(3).sendKeys("12012");
		sreg004.listOfFirstname.get(2).sendKeys("FN");
		sreg004.listOfLastName.get(2).sendKeys("LN");

		//commonFuntions.safeJavaScriptClick(empPage.agent_CO_County);
		//.safeJavaScriptClick(empPage.albany_County_Value);
//		sleep(4000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		sleep(4000);
		try {
			commonFuntions.selectRadioQuestions("Agad Address", "123");
			commonFuntions.selectRadioQuestions("bmad Address", "123");
			commonFuntions.selectRadioQuestions("lbra Address", "123");
			commonFuntions.selectRadioQuestions("npca Address", "123");
			Thread.sleep(2000);
			sreg004.popUpContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(1000);
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		/*-----------------SREG-521----------------*/
		commonFuntions.screenShot("EmpRegister", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-011----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-011 page");
		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		/*-----------------Find Valid FEIN----------------*/
		// Map<String, String> FEINOutput =
		// commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM
		// T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS", "FEIN");
		// String FEIN2 = FEINOutput.get("FEIN");
		// String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() *
		// Math.pow(10, 10))), 9);
		// System.out.println(feinValue2);
		// test.log(Status.INFO, "FEIN used on SREG-011 page : : " + feinValue2);
		Map<String, String> FEINOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;",
				"FEIN");
		String feinValue2 = FEINOutput.get("FEIN");
		System.out.println(feinValue2);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + feinValue2);

		Map<String, String> ENTITY_NAMEOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS ASC;",
				"ENTITY_NAME");
		String ENTITY_NAMEValue = ENTITY_NAMEOutput.get("ENTITY_NAME");
		System.out.println(ENTITY_NAMEValue);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : " + ENTITY_NAMEValue);

		/*-----------------Find Valid FEIN----------------*/
		/*-----------------Find Valid ERN----------------*/
		Map<String, String> ERNOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSC' ORDER BY UPDATED_TS DESC;", "EAN");
		String ERN = ERNOutput.get("EAN");
		System.out.println(ERN);
		test.log(Status.INFO, "ERN used on SREG-011 page : : " + ERN);
		/*-----------------Find Valid ERN----------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		//commonFuntions.enterTextboxContains("Legal Name of Business", ENTITY_NAMEValue);
		empPage.legalNameOfBusinessTextBox.sendKeys(ENTITY_NAMEValue);

		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square 4");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
//		commonFuntions.enterTextboxContains("Acquisition Date", "04012023");
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		commonFuntions.enterDateOfCurrentQuaterFirstMonthPlusOneDay("Notification date of Transfer");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		// Step-17
		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		empPage.addAnotherAcquisitionLink.click();
		commonFuntions.waitForLoadingIconToDisappear();

		//

		commonFuntions.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		commonFuntions.waitForLoadingIconToDisappear();

		Map<String, String> FEIN2Output3 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;",
				"FEIN");
		String feinValue3 = FEIN2Output3.get("FEIN");
		System.out.println(feinValue3);

		Map<String, String> EANOutput3 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;",
				"EAN");
		String EANnValue3 = EANOutput3.get("EAN");
		System.out.println(feinValue3);

		Map<String, String> ENTITY_NAME_Output3 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;",
				"ENTITY_NAME");
		String ENTITY_NAMEValue3 = ENTITY_NAME_Output3.get("ENTITY_NAME");
		System.out.println(ENTITY_NAMEValue3);

		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue3);
		commonFuntions.enterTextboxContains("Employer Registration Number", EANnValue3);
		empPage.legalNameOfBusinessTextBox.sendKeys(ENTITY_NAMEValue3);
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFuntions.enterFutureDate("Acquisition Date", 7);
		commonFuntions.enterFutureDate("Notification date of Transfer", 14);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		//

		/*-----------------SREG-012----------------*/

		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-012----------------*/
		/*-----------------Prior FEIN----------------*/
		Map<String, String> priorFEINOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT NULL ORDER BY UPDATED_TS ASC;", "FEIN");
		String priorFein = priorFEINOutput.get("FEIN");
		System.out.println(priorFein);
		test.log(Status.INFO, "Prior FEIN : : " + priorFein);
		/*-----------------Prior FEIN----------------*/
		// SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='LIAB' AND
		// REGISTRATION_STATUS ='C' ORDER BY UPDATED_TS DESC;
		/*-----------------Prior FEIN----------------*/
		Map<String, String> priorERNOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT NULL ORDER BY UPDATED_TS ASC;", "EAN");
		String priorEan = priorERNOutput.get("EAN");
		System.out.println(priorEan);
		test.log(Status.INFO, "Prior ERN : : " + priorEan);
		/*-----------------Prior FEIN----------------*/

//		sleep(3000);
		commonFuntions.screenShot("EmpRegister15", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.enterTextboxContains("Prior Federal Employer Identification Number (FEIN)", priorFein);
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", priorEan);
		commonFuntions.enterDateOfCurrentQuaterFirstMonthPlusOneDay("Date of Legal Entity change");
//		commonFuntions.enterTextboxContains("Date of Legal Entity change", "04032023");
		commonFuntions.enterPastDate("Date of Notification",7);
//		commonFuntions.enterTextboxContains("Date of Notification", "04042023");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-006----------------*/
//		sleep(3000);

		/*
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
		/*----------------------SSN---------------------*/
		Map<String, String> ssnValue = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_Employer_partner ORDER BY UPDATED_TS DESC;",
				"SSN");
		String ssn = ssnValue.get("SSN");
		System.out.println(ssn);
		
		Map<String, String> lastNameValue = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_Employer_partner ORDER BY UPDATED_TS DESC;",
				"LAST_NAME");
		String lastName = lastNameValue.get("LAST_NAME");
		System.out.println(lastName);

		sleep(3000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Partner/Owner", "Individual");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		// commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("First Name","FN"+commonFuntions.createRandomString());
		commonFuntions.enterTextboxContains("Last Name",lastName);
		commonFuntions.selectDropdown("Title", " Partner ");

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

		commonFuntions.waitForLoadingIconToDisappear();


		/*-----------------SREG-005----------------*/
//		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		/*-----------------SREG-683----------------*/
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		commonFuntions.uploadDoc("Sample");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		/*-----------------SREG-800----------------*/
		sleep(5000);
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
		commonFuntions.screenShot("Employer Registration Confirmation", "Pass", "SREG-013 page displayed");
		sleep(2000);
		/*-----------------SREG-013----------------*/
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		/*commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
				*/
		commonFuntions.screenShot("Homepage", "Pass", "Homapage page displayed");

		/*-----------------SREG-013----------------*/
		// Step-33
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		empPage.review_employer_My_Q.click();
		commonFuntions.waitForLoadingIconToDisappear();
//		sleep(3000);
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		sreg043.EEWI002CommentsField.sendKeys("Review em comment");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 34 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		/*commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
		*/

		test.info("Step: 35 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 36 -- DOL-DTF");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(2000);
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "DOL");sleep();
		//PEOPage.workItemDescriptionFieldWF01.sendKeys("DOL");
		commonFuntions.clickButton(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.clickOnLinkfirstItem("DOL/DTF Discrepancy Task");
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);

		test.info("Step: 38 -- DOL-DTF");
		String feinValueRandom = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValueRandom);
		commonFuntions.forceClearTextWithElement("Federal Employer Identification Number (FEIN)");
		sleep(2000);
		empmanagementPage.feinEEWI005.sendKeys(feinValueRandom);
		
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
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		//
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		/*commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
		*/

		test.info("Step: 40 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 41 -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("FEIN", feinValueRandom);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);

		test.info("Step: 42 -- ");
		//sreg084.verifyTransferlink.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Potential duplicate task", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);

		test.info("Step: 43 -- ");
		//commonFuntions.selectDropdown("Decision", " Continue with Transfer ");
		Thread.sleep(2000);
		//commonFuntions.selectCheckbox("Transfer Business Rules");
		//commonFuntions.enterTextboxContains("Comment", "Ok");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step: 44 -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		/*commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
		*/

		test.info("Step: 45 -- ");
		Thread.sleep(2000);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(3000);
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		test.info("Step: 46 -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("FEIN", feinValueRandom);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		
		//open work item
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Validate Partial Transfer Failed Rules Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step:  -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		test.info("Step:  -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("FEIN", feinValueRandom);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		
		//open work item
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		commonFuntions.screenShot("Create Transfer Letter Task Details", "Pass", "EEWI-002 screen is visible");
		Thread.sleep(2000);
		Thread.sleep(2000);
		commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		test.info("Step:  -- ");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		

	}
}
