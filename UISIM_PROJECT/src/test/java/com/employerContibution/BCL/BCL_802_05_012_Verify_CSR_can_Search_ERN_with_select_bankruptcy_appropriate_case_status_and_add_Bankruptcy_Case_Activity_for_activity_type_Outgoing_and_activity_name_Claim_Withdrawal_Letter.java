package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_05_012_Verify_CSR_can_Search_ERN_with_select_bankruptcy_appropriate_case_status_and_add_Bankruptcy_Case_Activity_for_activity_type_Outgoing_and_activity_name_Claim_Withdrawal_Letter extends TestBase {

	@Test
	public void BCL_802_05_012()throws Exception {
	test = report.createTest("BCL_802_05_012_Verify_CSR_can_Search_ERN_with_select_bankruptcy_appropriate_case_status_and_add_Bankruptcy_Case_Activity_for_activity_type_Outgoing_and_activity_name_Claim_Withdrawal_Letter");

	commonStepDefinitions commonFunction = new commonStepDefinitions();
	EmployerRegisterPage BCL_802_05_004 = new EmployerRegisterPage(driver);
	PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
	
	Map<String, String> databaseEanResult =
commonFunction.database_SelectQuerySingleColumn("SELECT tea.EAN FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE STATUS ='ACTV'",
	"EAN");
	String eanValue = databaseEanResult.get("EAN");
	  System.out.println("The EAN is " + eanValue);
	
	// --- Login ---
			commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			sleep(2000);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
			//---Menu Click---
			commonFunction.clickMenu("menu");
			commonFunction.ScrollMenu("Contribution Collection");
			commonFunction.clickMenu("Contribution Collection");
			commonFunction.clickMenu("Bankruptcy");
			commonFunction.screenShot("Menu", "Pass", "Warrant page is displayed");
			commonFunction.clickMenu("BankruptcyReview/UpdateBankruptcyCaseActivity");
			
	sleep(3000);
	commonFunction.screenShot("COL-474", "Pass", "Review/Update Bankruptcy Case Activity");
	commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
	
	commonFunction.clickButton(" Search ");
	//peoPage.selectRadiobutton.click();
	commonFunction.screenShot("COL-474", "Pass", "Review/Update Bankruptcy Case Activity");
	commonFunction.clickButton("Continue ");
	commonFunction.screenShot("COL-475", "Pass", "View Bankruptcy Case Details/History");
	sleep(2000);
	commonFunction.screenShot("COL-475", "Pass", "View Bankruptcy Case Details/History");
	//peoPage.editLink.click();
	commonFunction.clickButton("Add New Activity ");
	sleep(3000);
	commonFunction.screenShot("COL-402", "Pass", "Add/Edit Activity - Bankruptcy Case Activity");
	sleep(3000);
	commonFunction.clickButton("Continue ");
	sleep(3000);
	commonFunction.screenShot("COL-513", "Pass", "Add/Edit - Activity Verification");
	sleep(3000);
	commonFunction.clickButton("Submit ");
	sleep(3000);
	commonFunction.screenShot("SUC-002", "Pass", "Request to vacate warrent page is displayed");
	sleep(3000);
	commonFunction.clickButton("Home");
	sleep(3000);
	commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");



	}



	}




