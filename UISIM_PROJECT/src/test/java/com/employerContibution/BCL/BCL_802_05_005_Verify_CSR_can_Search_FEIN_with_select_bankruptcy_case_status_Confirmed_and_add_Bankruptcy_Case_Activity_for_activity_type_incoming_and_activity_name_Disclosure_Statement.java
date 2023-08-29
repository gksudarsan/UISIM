package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Test
public class BCL_802_05_005_Verify_CSR_can_Search_FEIN_with_select_bankruptcy_case_status_Confirmed_and_add_Bankruptcy_Case_Activity_for_activity_type_incoming_and_activity_name_Disclosure_Statement extends TestBase {

	public void BCL_802_05_005()throws Exception {
	test = report.createTest("BCL_802_05_005_Verify_CSR_can_Search_FEIN_with_select_bankruptcy_case_status_Confirmed_and_add_Bankruptcy_Case_Activity_for_activity_type_incoming_and_activity_name_Disclosure_Statement");

	commonStepDefinitions commonFunction = new commonStepDefinitions();
	EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
	PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
	
	// --- Login ---
			commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			sleep(2000);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
			//---Menu Click---
			commonFunction.clickMenu("menu");
			commonFunction.ScrollMenu("Contribution Collection");
			commonFunction.clickMenu("Contribution Collection");
			commonFunction.clickMenu("Warrant");
			commonFunction.screenShot("Menu", "Pass", "Warrant page is displayed");
			commonFunction.clickMenu("Request Warrant");
			
	sleep(3000);
	commonFunction.screenShot("COL-570", "Pass", "Process Partial Transfer page is displayed");
	commonFunction.enterTextboxContains("Employer Registration Number", "0464364");
	commonFunction.screenShot("COL-570", "Pass", "Process Partial Transfer page is displayed");
	commonFunction.clickButton("Continue ");
	commonFunction.screenShot("COL-571", "Pass", "List of warrent is displayed");
	sleep(2000);
	peoPage.selectRadiobutton.click();
	//peoPage.textBox.sendKeys("ok");
	//peoPage.dateEnter.sendKeys("8/2/2023");


	commonFunction.screenShot("COL-571", "Pass", "List of warrent is displayed");
	commonFunction.clickButton("Submit ");
	sleep(3000);
	commonFunction.screenShot("SUC-002", "Pass", "Request to vacate warrent page is displayed");
	sleep(3000);
	commonFunction.clickButton("Previous ");
	commonFunction.clickButton("Home");
	sleep(3000);
	commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");



	}



	}




