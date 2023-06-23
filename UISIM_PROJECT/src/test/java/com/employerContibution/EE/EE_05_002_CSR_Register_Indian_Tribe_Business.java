package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_05_002_CSR_Register_Indian_Tribe_Business extends TestBase {
	
	@Test
	public void EE_05_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"EE.05.002 - Verify CSR can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Business' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		sleep();
		commonFuntions.screenShot("EmpRegister11", "Pass", "Entered the details and click on continue button");
		commonFuntions.clickButton("Continue ");
		
		/**************** SREG-025 ************************/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Indian Tribe ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Housing Authority ");
		/*--------------------FEIN----------------------*/
//		Map<String, String> feinValueOutput =  commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "FEIN");
//		String feinValue = feinValueOutput.get("FEIN");
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		
//		String feinValue = "061683609";

		System.out.println("FEIN : : "+feinValue);
		test.log(Status.INFO, "FEIN : : "+ feinValue);
		/*--------------------FEIN----------------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
//		sleep(3000);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100IT ");
		commonFuntions.clickButton("Continue ");
//		sleep(3000);
		
		/*-----------------SREG-003----------------*/
		
		/*---------------Legal Name--------------*/
//		Map<String, String> legalNameValue = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "ENTITY_NAME");
//		
//		String legalName= legalNameValue.get("ENTITY_NAME");
		
		String legalName = "preety "+StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 4) + " Inc";
		
		/*---------------Legal Name--------------*/
		
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Required text displayed if user do not enter Legal Name");
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		sleep();
		commonFuntions.clickButton("Continue ");
//		sleep(3000);
		
		/*-----------------SREG-008----------------*/
		commonFuntions.screenShot("EmpRegister5", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1 ", "16 AVE STREET");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10452");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		try {
			sleep(2000);
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-007----------------*/
		
		sleep(4000);
		
		commonFuntions.screenShot("EmpRegister6", "Pass", "Navigated to SREG-007 page");
		commonFuntions.clickButton("Continue ");
		
		
		/*-----------------SREG-004----------------*/
		
//		sleep(3000);
		commonFuntions.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		commonFuntions.enterTextboxContains("Address Line 1 ", "2 River RD");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10455");
		commonFuntions.selectDropdown("County", " Albany ");
		
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		sleep();
		empPage.location_Of_Book_AddresLine1.sendKeys("30 AVE STreet");
		empPage.location_Of_Book_City.sendKeys("NY");
		empPage.location_Of_Book_ZipCode.sendKeys("34526");
		commonFuntions.safeJavaScriptClick(empPage.location_Of_Book_County);
		commonFuntions.safeJavaScriptClick(empPage.albany_County_Value);
		
		
		commonFuntions.screenShot("TPRRegister8", "Pass", "Selected Same as Mailing for Location of Books and Records");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Mailing");
		sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Selected Same as Location of Books and Records for Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		try {
			sleep(2000);
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.uspsCommonButton2);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-521----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-683 page and uploading the document");
		commonFuntions.safeJavaScriptClick(empPage.browserLink);
		commonFuntions.uploadDoc("Sample");
		sleep(4000);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-800----------------*/
//		sleep(3000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-800 page");
		commonFuntions.safeJavaScriptClick(empPage.employer_entity_Edit_Button);
		sleep(2000);
		commonFuntions.forceClearText(empPage.legalNameTextBox);
		sleep();
		empPage.legalNameTextBox.sendKeys("EAGLE EYE ANTIQUES, INC");
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals working in covered employment", "4");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
//		commonFuntions.safeJavaScriptClick(empPage.business_physical_Edit_Button);
		commonFuntions.clickButton("Continue ");
		
		
		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister13", "Pass", "Navigated to SREG-043 page and click on I accept and submit");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButton("Submit ");
//		sleep(15000);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		/*-----------------SREG-013----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-013 page and click on exit");
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
		sleep(3000);
//		commonFuntions.clickOnLink("Review Employer Type");
//		commonFuntions.safeJavaScriptClick(empPage.review_employer_My_Q);
		empPage.review_employer_My_Q.click();
		sleep(3000);
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(2000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		commonFuntions.enterCurrentDate("Date Covered Employment began? ");
		empPage.commentBox_MyQ.sendKeys("Random Queue");
		sleep();
		commonFuntions.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page and click on Home");
		commonFuntions.clickButton("Home ");
		
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(20000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on Obtain Bond task");
//		commonFuntions.clickOnLink("Review Employer Type");
		sleep(3000);
//		commonFuntions.safeJavaScriptClick(empPage.obtain_bond_task_My_Q);
		empPage.obtain_bond_task_My_Q.click();
		sleep(4000);
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		empPage.commentBox_MyQ.sendKeys("Random Queue");
		commonFuntions.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		
		sleep(10000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page and click on Home");
		commonFuntions.clickButton("Home ");
	}
}
