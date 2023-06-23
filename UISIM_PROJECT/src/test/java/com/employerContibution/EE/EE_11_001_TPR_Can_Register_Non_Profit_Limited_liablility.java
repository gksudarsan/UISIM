package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_11_001_TPR_Can_Register_Non_Profit_Limited_liablility extends TestBase{
	
	@Test
	public void EE_09_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		test = report
				.createTest("EE.11.001 -Verify TPR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Limited Liability Company (All Types)' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(4000);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
//		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.menuButtonHomepage);
		sleep(4000);
//		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Employer Registration");
		sleep();
		commonFuntions.clickMenu("Register Employer");
		sleep(4000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Willam");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Email Address", "test@Test.com");
		commonFuntions.enterTextboxContains(" Contact Telephone Number ", "4534567645");
		sleep();
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		/*---------------SREG-025--------------*/
		commonFuntions.screenShot("TPRRegister", "Pass", "Navigated to SREG-025 page");
		test.log(Status.INFO, "Selecting drop down and filling the form");
		commonFuntions.selectDropdown("Employer Type", " Non-Profit ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Liability Company (All Types) ");
		/*---------------FEIN--------------*/
		Map<String, String> feinOutput = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS DESC", "FEIN");
		String feinValue = feinOutput.get("FEIN");
		System.out.println(feinValue);
		test.log(Status.INFO, "FEIN : : "+feinValue);
		
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		
		/*---------------SREG-003--------------*/
		
		commonFuntions.screenShot("TPRRegister3", "Pass", "Navigated to SREG-003 page");
		test.log(Status.INFO, "Selecting drop down and filling the form");
		/*---------------Legal Name--------------*/
		Map<String, String> legalNameValue = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea  ORDER BY UPDATED_TS DESC", "ENTITY_NAME");
//		
		String legalName= legalNameValue.get("ENTITY_NAME");
		/*---------------Legal Name--------------*/

		empPage.legalNameTextBox.clear();
		empPage.legalNameTextBox.sendKeys(legalName+" LLC");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterPastDate("Enter date of first", 450);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_Value_2);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_Value_2024);
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_Yes_radio);
		commonFuntions.enterTextboxContains("Explain services that are performed by people", "Tester");
		commonFuntions.screenShot("TPRRegister5", "Pass", "Filling the form continue....");
		
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_employed_4);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_Value_2);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_employed_4);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_Value_2024);
		commonFuntions.safeJavaScriptClick(empPage.If_Not_Lible_Yes_Radio);
		commonFuntions.safeJavaScriptClick(empPage.DOes_Org_Have_Yes_Radio);
		commonFuntions.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Contributory");
		commonFuntions.selectRadioQuestions("If Reimbursable, has a copy of the 501c3 exemption documentation been provided?", "No ");

		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-008--------------*/
		sleep(4000);
		commonFuntions.screenShot("TPRRegister6", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 Madisen Avenue");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.enterTextboxContains("Number of employees at this location", "98");
		commonFuntions.enterTextboxContains("Name of Government Agency from which you receive funds", "Tester LLC");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Pop- up Not displayed");
		}
		sleep(5000);
		/*---------------SREG-007--------------*/
		commonFuntions.screenShot("Register111", "PASS", "Navigated to SREG-007 page after adding the address");
		commonFuntions.clickButton("Continue ");
		/*---------------SREG-004--------------*/
		
		sleep(6000);
		commonFuntions.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Rooling Street");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "34276");
		commonFuntions.safeJavaScriptClick(empPage.countyDropDown_Form1);
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		sleep();
		commonFuntions.screenShot("TPRRegister8", "Pass", "Entered the address for Business Mailing Address");
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		commonFuntions.enterTextbox("First Name", "Abhi");
		commonFuntions.enterTextbox("Last Name", "Jan");
		sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Entered the address for Location of Books and Records");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		sleep(3000);
		empPage.notice_potential_AddressLine_1.sendKeys("76 Mind Road");
		empPage.notice_potential_City.sendKeys("Albany");
		empPage.notice_potential_Zipcode.sendKeys("45456");
		commonFuntions.safeJavaScriptClick(empPage.notice_potential_county);
		commonFuntions.safeJavaScriptClick(empPage.countyValue_Form1);
		
		empPage.notice_potential_firstName.sendKeys("Abhi1");
		empPage.notice_potential_LastName.sendKeys("JanNew");
		sleep();
		commonFuntions.screenShot("TPRRegister10", "Pass", "Entered the address for Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton2);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		
		/*---------------SREG-521--------------*/
		sleep();
		commonFuntions.screenShot("TPRRegister11", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*---------------SREG-011--------------*/
		commonFuntions.screenShot("TPRRegister12", "Pass", "Navigated to SREG-011 page");
		sleep();
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*--------------------SREG-006----------------*/
		
		commonFuntions.selectRadioQuestions("Type of Member/Managing Member", "Individual");
		sleep();
		Map<String, String>	ssnOutput = commonFuntions.database_SelectQuerySingleColumn("SELECT t10.T10_SSN, t20.T20_FEIN, T10.T10_DATE_1, t10.T10_WAGE_1, T10.T10_DATE_2, t10.T10_WAGE_2,\r\n"
				+ "T10.T10_DATE_3, t10.T10_WAGE_3, T10.T10_DATE_4, t10.T10_WAGE_4, T10.T10_DATE_5,\r\n"
				+ "t10.T10_WAGE_5, T10.T10_DATE_6, t10.T10_WAGE_6, T10.T10_DATE_7, t10.T10_WAGE_7, T10.T10_DATE_8, t10.T10_WAGE_8 FROM\r\n"
				+ "T_TWAGE10 t10 LEFT JOIN T_TWAGET20 t20 ON t10.T10_FEIN = t20.T20_FEIN WHERE t10.T10_WAGE_1> ('2900.00')\r\n"
				+ "AND t10.T10_WAGE_2 > ('2900.00') AND t10.T10_WAGE_3 > ('2900.00') AND t10.T10_WAGE_4 > ('2900.00') AND\r\n"
				+ "t10.T10_WAGE_5 > ('2900.00') AND t10.T10_WAGE_6 > ('2900.00') AND t10.T10_WAGE_7 > ('2900.00') AND t10.T10_WAGE_8 > ('2900.00');", "T10_SSN");
		String ssn = ssnOutput.get("T10_SSN");
		System.out.println(ssn);
		test.log(Status.INFO, "SSN : : "+ssn);
		
		commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("First Name", "Abhi");
		commonFuntions.enterTextboxContains("Last Name", "Jan");
		commonFuntions.selectDropdown("Title", " Member ");
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "32 Edwin Road");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "24954");
		commonFuntions.enterTextboxContains(" Residential Telephone Number ", "2428374672");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(4000);
		
		/*--------------------SREG-005----------------*/
		
		commonFuntions.screenShot("TPRRegister12", "Pass", "Navigated to SREG-006 page and adding a member");
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.add_Member_Managing_Member_Detail_Link);
		sleep(3000);
		
		/*--------------------SREG-006----------------*/
		
		commonFuntions.selectRadioQuestions("Type of Member/Managing Member", "Individual");
		sleep();
		Map<String, String>	ssnOutput2 = commonFuntions.database_SelectQuerySingleColumn("SELECT t10.T10_SSN, t20.T20_FEIN, T10.T10_DATE_1, t10.T10_WAGE_1, T10.T10_DATE_2, t10.T10_WAGE_2,\r\n"
				+ "T10.T10_DATE_3, t10.T10_WAGE_3, T10.T10_DATE_4, t10.T10_WAGE_4, T10.T10_DATE_5,\r\n"
				+ "t10.T10_WAGE_5, T10.T10_DATE_6, t10.T10_WAGE_6, T10.T10_DATE_7, t10.T10_WAGE_7, T10.T10_DATE_8, t10.T10_WAGE_8 FROM\r\n"
				+ "T_TWAGE10 t10 LEFT JOIN T_TWAGET20 t20 ON t10.T10_FEIN = t20.T20_FEIN WHERE t10.T10_WAGE_1> ('2900.00')\r\n"
				+ "AND t10.T10_WAGE_2 > ('2900.00') AND t10.T10_WAGE_3 > ('2900.00') AND t10.T10_WAGE_4 > ('2900.00') AND\r\n"
				+ "t10.T10_WAGE_5 > ('2900.00') AND t10.T10_WAGE_6 > ('2900.00') AND t10.T10_WAGE_7 > ('2900.00') AND t10.T10_WAGE_8 > ('2900.00');", "T10_SSN");
		String ssn2 = ssnOutput2.get("T10_SSN");
		System.out.println(ssn);
		test.log(Status.INFO, "SSN : : "+ssn2);
		
		commonFuntions.enterTextboxContains("SSN", ssn2);
		commonFuntions.enterTextboxContains("First Name", "Abhi2");
		commonFuntions.enterTextboxContains("Last Name", "Jan2");
		commonFuntions.selectDropdown("Title", " Member ");
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "80 Morrison Road");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "24784");
		commonFuntions.enterTextboxContains(" Residential Telephone Number ", "2428359872");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(4000);
		
		/*--------------------SREG-005----------------*/
		
		commonFuntions.screenShot("TPRRegister12", "Pass", "Navigated to SREG-006 page and adding a member");
		sleep(3000);
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		sleep(3000);
		commonFuntions.uploadDoc("Sample");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-800----------------*/
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButton("Submit ");
		sleep(2500);
		commonFuntions.waitForLoadingIconToDisappear();
		/*-----------------SREG-013----------------*/
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-013 page and click on exit");
		commonFuntions.clickButton("Exit ");
		sleep(10000);
		
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(20000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		empPage.review_employer_My_Q.click();
		sleep(3000);
		
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();
		/*-----------------EEWl-002----------------*/
		commonFuntions.selectDropdown("Account Status", " Erroneous ");
		sleep();
		commonFuntions.selectDropdown("Send Status to NYBE", " NYS100IT filed/Required to file NYS100-N ");
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_Value_2);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_Value_2024);
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_Yes_radio);
		sleep();
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_employed_4);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Quater_Value_2);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_employed_4);
		commonFuntions.safeJavaScriptClick(empPage.firstCalender_Year_Value_2022);
		empPage.commentBox_MyQ.sendKeys("Test");
		commonFuntions.clickButton("Submit ");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		/*-----------------SUC-002----------------*/
	
		commonFuntions.validateNextPageNumber("SUC-002");
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to Success page");
		commonFuntions.clickButtonContains("Home ");
//		sleep(2000);
//		commonFuntions.waitForLoadingIconToDisappear();
//		
//		commonFuntions.clickMenu("Inquiry");
//		sleep();
//		commonFuntions.clickMenu("Contribution Inquiry");
//		sleep();
//		commonFuntions.clickMenu("Inquiry Employer Account");
//		/*---------------------SREG-050-----------------*/
//		sleep();
//		commonFuntions.waitForLoadingIconToDisappear();
//		
//		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
//		commonFuntions.clickButton("Continue ");
//		
		
		
	}
}
