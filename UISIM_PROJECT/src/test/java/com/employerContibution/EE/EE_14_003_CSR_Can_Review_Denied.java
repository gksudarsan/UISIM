package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_14_003_CSR_Can_Review_Denied extends TestBase{
	
	@Test
	public void EE_14_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report
				.createTest("Verify CSR is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100G sent to NYBE.");
		
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		sleep();
		commonFuntions.clickButton("Continue ");
		
	/*---------------SREG-025--------------*/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Indian Tribe ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Business ");
		/*---------------FEIN--------------*/
		Map<String, String> FeinOutput =  commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ", "FEIN");
		String feinValue = FeinOutput.get("FEIN");
		System.out.println("FEIN 1 : : " +feinValue);
		test.log(Status.INFO, "FEIN 1 : : " +feinValue);
		/*---------------FEIN--------------*/
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.enterTextboxContains("Source", " NYS-100 (paper) ");
		commonFuntions.enterTextboxContains("Source Type", " NYS-100IT ");
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-003--------------*/
		
		
		
		/*---------------Legal Name--------------*/
		//SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT  IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
		String legalName="Random String FEIN Value";
		/*---------------Legal Name--------------*/
		
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?", 365);
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly", "Yes ");
		sleep();
		commonFuntions.enterPastDate("Date covered employment began? ", 180);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-008----------------*/
		commonFuntions.screenShot("EmpRegister5", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-007----------------*/
		
		sleep(4000);
		
		commonFuntions.screenShot("EmpRegister6", "Pass", "Navigated to SREG-007 page");
		commonFuntions.clickButton("Continue ");
		

		/*-----------------SREG-004----------------*/
		
		sleep(3000);
		commonFuntions.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Selected Same as Same as Primary Business Physical Address for Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-521----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-683 page and uploading the document");
		commonFuntions.safeJavaScriptClick(empPage.browserLink);
		commonFuntions.uploadDoc("Sample");
		sleep(4000);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-800----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-800 page");
		test.log(Status.INFO, "Click on edit Employer Registration");
		commonFuntions.safeJavaScriptClick(empPage.employer_Register_Edit_Button);
		
		/*-----------------SREG-001----------------*/
		
		commonFuntions.selectRadioQuestions("Suppress Correspondence?", "Yes ");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-800----------------*/
		commonFuntions.safeJavaScriptClick(empPage.Contact_Detail_Edit_Button);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-004 page and update the address");
		commonFuntions.clickButton("Continue ");
		
		commonFuntions.safeJavaScriptClick(empPage.general_Info_Edit_Button);
		Map<String, String> FeinOutput2 =  commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ORDER BY UPDATED_TS", "FEIN");
		String feinValueNew = FeinOutput2.get("FEIN");
		System.out.println("FEIN 1 : : " +feinValueNew);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValueNew);
		
	}
}
