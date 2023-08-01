package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_411_003_1_Verify_CSR_ableToUpdateAccountStatusSuspendedOutofBusinessandSendaClosureLetter
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EM.411.003.1.Verify CSR is able to update account status of employer account 'Suspended Out of Business' and Send a Closure Letter (IA31.6)", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void EM_411_003_1() throws Exception {

		test = report.createTest(
				"EM.411.003.1.Verify CSR is able to update account status of employer account 'Suspended Out of Business' and Send a Closure Letter (IA31.6)");
		
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		/*Map<String, String> ernOutput = cf.database_SelectQuerySingleColumn(
		"SELECT SUCCESSOR_ERN FROM T_EMPLOYER_TRANSFER_SUCCESSOR tets ORDER BY UPDATED_TS", "SUCCESSOR_ERN");
		String ernValue = ernOutput.get("SUCCESSOR_ERN");

		test.log(Status.INFO, "ERN : " + ernValue);
		System.out.println("EAN is :" + ernValue);*/
		
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Employer Account Maintenance");
		cf.clickMenu("Employer Account Maintenance");
		cf.clickMenu("Maintain Account Status");

		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Maintain Account Status - Enter ERN", "Pass", "Navigated to SREG-434");
		cf.enterTextboxContains("Employer Registration Number", "0463916"); //0463916
		cf.screenShot("Maintain Account Status", "Pass", "Entered ERN in SREG-435");
		cf.clickButton("Continue ");
		

		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Update Account Status", "Pass", "Navigated to SREG-435");
		cf.selectDropdown("Status of Employer Account", " Suspended Out of Business ");
		empPage.beginningLiableDateQuarter.click();
		sleep(1500);
		empPage.firstCalender_Quater_Value_2.click();
		empPage.yearDropDown.click();
		sleep(1500);
		empPage.yearValue.click();
		empPage.suspensionDateQuarter.click();
		sleep(1500);
		empPage.firstCalender_Quater_Value_2.click();
		empPage.yearDropDown2.click();
		sleep(1500);
		empPage.yearValue.click();

		cf.screenShot("Update Account Status", "Pass", "Filled details for Quarter and Year");
		cf.enterTextboxContains("Last Date of Payroll", "6/20/2023");
		cf.selectDropdown("Cross Referenced Reasons", "Changeover");
		cf.addComment("Test");
		cf.selectRadioQuestions("Send a Closure Letter (IA31.6)?", "Yes ");
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		cf.clickButton("Submit ");
		cf.screenShot("Account Close/Cancel Confirmation", "Pass", "Navigated to SUC-002");
		cf.validateTextIsDisplayed(
				"Employer Registration Number 04-63916 has been closed successfully effective 2/2023");
		cf.clickButton("Home ");
		sleep();
		cf.screenShot("Home Page", "Pass", "Navigated to Home");
		
		sleep(2000);
		cf.screenShot("Home Page", "Pass", "TC EM.411.003 passed successfully. :)");

	}
}
