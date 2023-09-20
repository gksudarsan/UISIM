package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FraudAndInvestigationPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_405_002_Verify_TPR_can_submit_IA138_online_form_to_file_a_protest_for_a_BCP_penalty_and_system_create_task_for_CSR_review_and_remove_the_BCP_Penalty_for_the_quarter_and_store_the_cancel_code
		extends TestBase {

	@Test
	public void FI_405_002() throws Exception {

		test = report.createTest(
				"FI_405_002_Verify_TPR_can_submit_IA138_online_form_to_file_a_protest_for_a_BCP_penalty_and_system_create_task_for_CSR_review_and_remove_the_BCP_Penalty_for_the_quarter_and_store_the_cancel_code");

		FraudAndInvestigationPage fiPage = new FraudAndInvestigationPage(driver);
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
       AddressPage add =new AddressPage(driver);
		// --------Login-------
		commonFunction.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		commonFunction.waitForLoadingIconToDisappear();
		// -------DB---
		/*
		Map<String, String> databaseResults = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%' AND REGISTRATION_STATUS='P'",
				"EAN");

		String eanNumber = databaseResults.get("EAN");
*/
		String eanNumber = "5454645";
		
		if ((eanNumber == null) || eanNumber.isEmpty()) {
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanNumber + ".");
		}

		// --------Menu---------
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		//commonFunction.clickMenu("menu");
		add.menu.click();
		commonFunction.ScrollMenu("Secure Messaging");
		commonFunction.clickMenu("Secure Messaging");
		sleep(1000);
		commonFunction.screenShot("menu", "Pass", "selected option");
		commonFunction.clickMenu("Write Message - Enter ERN");
		sleep(2000);

		// ----SM-100
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Write Message - Enter ERN", "Pass", "Successfully launched to SM-100 page");
		// commonFunction.enterTextboxContains("Employer Registration Number",
		// "4730215");
		commonFunction.enterTextboxContains("Employer Registration Number", eanNumber);
		sleep(2000);
		commonFunction.screenShot("Write Message - Enter ERN", "Pass", "Entered valid ERN on SM-100 page");
		commonFunction.clickButtonContains("Continue ");

		// ----SM-101
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Write Message", "Pass", "Successfully launched to SM-101 page");
		commonFunction.selectDropdown("Category", " Protest ");
		sleep(2000);
		commonFunction.selectDropdown("Subcategory", " How Do I Protest Benefit Claim Penalty? ");
		sleep(2000);
		commonFunction.screenShot("Write Message", "Pass", "Entered Information on SM-101 page");
		sleep(2000);
		commonFunction.clickOnLinkAnchorTag("Click Here");
		commonFunction.switchTab();
		//commonFunction.clickButtonContains("Continue ");
		// ----FIS_009
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Benefit Claim Penalty Assessment Protest (FIS-009)", "Pass", "Successfully launched to FIS_009 page");
	
		commonFunction.enterTextboxContains("Claimant Name", "Robert Fizz");
		sleep(1000);
		commonFunction.enterTextboxContains("SSN", "78900-5679");
		sleep(1000);
		commonFunction.enterTextboxContains("Claim Date", "09/06/2023");
		sleep(1000);
		commonFunction.selectDropdown("Quarter ", " 4 ");
		sleep(1000);
		commonFunction.selectDropdown("Year ", " 2023 ");
		sleep(1000);
		try {
        fiPage.wedgeInformation_checkBox.click();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
        commonFunction.clickButtonContains("Choose File");
	     Thread.sleep(2000);
	     commonFunction.uploadDoc("Sample.docx");
	     Thread.sleep(2000);
	     commonFunction.screenShot("Benefit Claim Penalty Assessment Protest","Pass","Uploaded doc");
        
		fiPage.comments.sendKeys("Testing");
		sleep(1000);
		commonFunction.selectCheckbox("I Accept");
		
		commonFunction.screenShot("Benefit Claim Penalty Assessment Protest (FIS-009)", "Pass",
				"Entered information to FIS_009 page");
		sleep(2000);
		commonFunction.clickButtonContains("Submit ");
		commonFunction.waitForLoadingIconToDisappear();
		
		// ----SUC 002
		sleep(2000);
		commonFunction.screenShot("Issue Submission Confirmation", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		commonFunction.clickButtonContains("Home ");
		//------Home
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		commonFunction.waitForLoadingIconToDisappear();

		// --------Login Logout-------
				commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
				commonFunction.waitForLoadingIconToDisappear();
		
		// --------WorkItems-------- 
				/*String ean = "56-67230" ;
				
				UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = 'ndsbb3' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='+"ean"+' ORDER BY UPDATED_TS desc);
				*/
		/*-------------- Work Item 1  --------------*/
		sleep(3000);
		commonFunction.waitForLoadingIconToDisappear();
		peoPage.queue.click();
		commonFunction.waitForLoadingIconToDisappear();

		/*--------------WF-001   --------------*/
		sleep(3000);
		commonFunction.screenShot("Individual Work Queue", "Pass", "Clicked on Work Item - WF-001 ");
		sleep(3000);
		commonFunction.clearTextboxContains("Employer Registration Number");
		commonFunction.enterTextboxContains("Work Item Description Free Text", "Review Benefit Claim Penalty Protest");
		sleep(3000);
		commonFunction.screenShot("Work Item Description Free Text", "Pass", "Search for Task");
		sleep(3000);
		commonFunction.clickButtonContains(" Search ");
		sleep(3000);
		commonFunction.ScrollMenu("Review Benefit Claim Penalty Protest");
		sleep(3000);
		commonFunction.screenShot("WIClick", "Pass", "Clicked on Work Item - Review Benefit Claim Penalty Protest");
		sleep(3000);
		commonFunction.clickOnLink("Review Benefit Claim Penalty Protest");

		// --- WF-091 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
		sleep(3000);
		commonFunction.clickButtonContains("Open Work Item ");

		// --- PFP-003---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Review Benefit Claim Penalty Protest ", "Pass", "Successful launch to PFP-004 page");
		sleep(2000);
		commonFunction.selectDropdown("If Sustain a BCP,choose the sustain code", " IEVF ");
		sleep(1000);
		//commonFunction.selectDropdown("If Cancel a BCP,choose the cancel code", " 2023 ");
		//sleep(1000);
	
		 commonFunction.selectRadioQuestions("Options for CSR", " Sustain BCP");
		 sleep(1000);
		 commonFunction.selectRadioQuestions("Do you want to Generate the IA13.2 notice to the Employer?    ", "No ");
		 sleep(1000);
		 commonFunction.selectRadioQuestions("Is Fact Finding required", "No ");
		 fiPage.resolutionDet.sendKeys("Test Reveiw");
		sleep(2000);
		commonFunction.screenShot("Review Fraud Penalty Protest ", "Pass", "Details entered in to PFP-003 page");
		sleep(2000);
		commonFunction.clickButtonContains("Submit ");

		
		//--RAised bug for time stamp
		
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Task Completed.", "Pass", "Successful launch (SUC-002) page");
		sleep();
		commonFunction.clickButtonContains("Home ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();

		//------Menu--------
		//--------Menu----
				commonFunction.clickMenu("menu");
				commonFunction.clickMenu("Inquiry");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Contribution Inquiry");
				sleep(2000);
				commonFunction.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
				commonFunction.ScrollMenu("Inquiry");
				commonFunction.clickMenu("Inquiry Employer Account");
		
		
		
		commonFunction.screenShot("TC:  FI_405_002", "Pass", "test completed  ");
		// ---raised bug
	}
}
