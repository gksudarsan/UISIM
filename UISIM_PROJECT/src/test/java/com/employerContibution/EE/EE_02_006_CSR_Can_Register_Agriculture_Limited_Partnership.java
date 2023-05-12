package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_006_CSR_Can_Register_Agriculture_Limited_Partnership extends TestBase{
	
	@Test
	public void EE_02_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);

		
		test = report
				.createTest("EE.02.006 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Limited Partnership' and work items will be created for CSR to review.");
		
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to __ Page");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
		sleep();
		
		sleep();
		/*----------------FEIN----------------*/
		Map<String, String> output = commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN  FROM T_EMPLOYER_ACCOUNT tea  WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ) GROUP BY FEIN HAVING COUNT(*)>1", "FEIN");
		String feinValue = output.get("FEIN");
		System.out.println(feinValue);
		test.log(Status.INFO, "FEIN : : "+ feinValue );
		/*----------------FEIN----------------*/
		
		commonFuntions.screenShot("EmpRegister3", "Pass", "Filling the form");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Partnership ");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Form filled and click on continue");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-003----------------*/
		
		commonFuntions.screenShot("EmpRegister5", "Pass", "Landed on SREG-003 page");
		
		empPage.legalNameTextBox.sendKeys("Random Legal Name LLP");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Other Test");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Business Email Address", "test@test.com");
		commonFuntions.enterPastDate("Enter date of first operations in New York State", 365);
		commonFuntions.enterPastDate("What is the date of the first payroll which you withheld", 330);
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_2.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2023.click();
		commonFuntions.screenShot("EmpRegister6", "Pass", "Filling the form");
		commonFuntions.enterTextboxContains("Total number of covered employees", "20");
		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "No ");
		sleep();
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-008----------------*/

		sleep(4000);
		commonFuntions.screenShot("EmpRegister7", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23432");
		commonFuntions.selectDropdown("County", " Albany ");
		empPage.individualPrinciple.click();
		empPage.individualPrinciple_value_other.click();
		commonFuntions.enterTextbox("If Other, provide details", "Test");
		
		commonFuntions.clickButton("Continue ");
		sleep(3000);
//		try {
//			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
//			sleep();
//			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
//		}catch(Exception e ) {
//			System.out.println("Pop up not displayed");
//		}
		
		/*-----------------SREG-007----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated on SREG-007 page and take screenshot");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-004----------------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep(3000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address 2");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23433");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		/*-----------------SREG-521----------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated on SREG-521 page and take screenshot");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-011----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-011 page");
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		/*-----------------Find Valid FEIN----------------*/
		Map<String, String>	FEINOutput = commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN  FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS", "FEIN");
		String FEIN2 = FEINOutput.get("FEIN");
		System.out.println(FEIN2);
		test.log(Status.INFO, "FEIN used on SREG-011 page : : "+FEIN2);
		/*-----------------Find Valid FEIN----------------*/
		
		/*-----------------Find Valid ERN----------------*/
		Map<String, String>	ERNOutput = commonFuntions.database_SelectQuerySingleColumn("SELECT EAN  FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='CAND' AND EAN!='NULL' ORDER BY UPDATED_TS", "EAN");
		String ERN = ERNOutput.get("EAN");
		System.out.println(ERN);
		test.log(Status.INFO, "ERN used on SREG-011 page : : "+ERN);
		/*-----------------Find Valid ERN----------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN2);
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		commonFuntions.enterTextboxContains("Legal Name of Business", "Other Enterprise");
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address 4");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFuntions.enterPastDate("Acquisition Date", 450);
		commonFuntions.enterDateOfCurrentQuaterFirstMonth("Notification date of Transfer");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-012----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-012----------------*/
		commonFuntions.screenShot("EmpRegister15", "Pass", "Entering the form Details");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-006----------------*/
		sleep(3000);
		
		Map<String, String>	ssnOutput = commonFuntions.database_SelectQuerySingleColumn("SELECT t10.T10_SSN, t20.T20_FEIN, T10.T10_DATE_1, t10.T10_WAGE_1, T10.T10_DATE_2, t10.T10_WAGE_2,\r\n"
				+ "T10.T10_DATE_3, t10.T10_WAGE_3, T10.T10_DATE_4, t10.T10_WAGE_4, T10.T10_DATE_5,\r\n"
				+ "t10.T10_WAGE_5, T10.T10_DATE_6, t10.T10_WAGE_6, T10.T10_DATE_7, t10.T10_WAGE_7, T10.T10_DATE_8, t10.T10_WAGE_8 FROM\r\n"
				+ "T_TWAGE10 t10 LEFT JOIN T_TWAGET20 t20 ON t10.T10_FEIN = t20.T20_FEIN WHERE t10.T10_WAGE_1> ('2900.00')\r\n"
				+ "AND t10.T10_WAGE_2 > ('2900.00') AND t10.T10_WAGE_3 > ('2900.00') AND t10.T10_WAGE_4 > ('2900.00') AND\r\n"
				+ "t10.T10_WAGE_5 > ('2900.00') AND t10.T10_WAGE_6 > ('2900.00') AND t10.T10_WAGE_7 > ('2900.00') AND t10.T10_WAGE_8 > ('2900.00');", "T10_SSN");
		String ssn = ssnOutput.get("T10_SSN");
		System.out.println(ssn);
		test.log(Status.INFO, "SSN : : "+ssn);
		/*----------------------FEIN---------------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		commonFuntions.selectRadioQuestions("Type of Partner/Owner", "Individual");
		
		commonFuntions.enterTextboxContains("SSN", ssn);
		commonFuntions.enterTextboxContains("First Name", "Abhi");
		commonFuntions.enterTextboxContains("Last Name", "Jan");
		commonFuntions.selectDropdown("Title", " Partner ");
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address 6");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24954");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-005----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-005 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		commonFuntions.uploadDoc("Sample");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-800----------------*/
		sleep(5000);
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButton("Continue ");
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButton("Submit ");
		sleep(15000);
		/*-----------------SREG-013----------------*/
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-013 success page and click on exit");
		commonFuntions.clickButton("Exit ");
		
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
//		commonFuntions.clickOnLink("Review Employer Type");
//		commonFuntions.safeJavaScriptClick(empPage.review_employer_My_Q);
		empPage.review_employer_My_Q.click();
		sleep(3000);
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(2000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		
		empPage.commentBox_MyQ.sendKeys("Random Queue");
		sleep();
		commonFuntions.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page and click on Home");
		commonFuntions.clickButton("Home ");
		
		
	}
}
