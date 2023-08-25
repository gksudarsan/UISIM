package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FraudAndInvestigationPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_003_Verify_CSr_can_submit_an_FI_Issue_by_selecting_a_work_item_Review_SUTA_Transfer_and_or_SUTA_Penalty_Protest_Task_Issue_Category_SUTA_Hit_Transfer_or_UIES_TransfeR_Issue_Subcategory_SUTA_Hit_TransfeR_or_UIES_Transfer
		extends TestBase {

	@Test
	public void FI_169_003() throws Exception {

		test = report.createTest(
				"FI_169_003 - Verify_CSr_can_submit_an_FI_Issue_by_selecting_a_work_item_Review_SUTA_Transfer_and_or_SUTA_Penalty_Protest_Task_Issue_Category_SUTA_Hit_Transfer_or_UIES_TransfeR_Issue_Subcategory_SUTA_Hit_TransfeR_or_UIES_Transfer");

		FraudAndInvestigationPage fiPage = new FraudAndInvestigationPage(driver);
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);

		// --------Login-------
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.waitForLoadingIconToDisappear();
		// -------DB---
		Map<String, String> databaseResults = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"EAN");

		String eanNumber = databaseResults.get("EAN");

		if ((eanNumber == null) || eanNumber.isEmpty()) {
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanNumber + ".");
		}

		// --------Menu---------
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Employer Issues");
		commonFunction.clickMenu("Employer Issues");
		sleep(1000);
		commonFunction.screenShot("menu", "Pass", "selected option");
		commonFunction.clickMenu("Create Work Item");
		sleep(2000);
		

		// ----FIS_001
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Create Work Item", "Pass", "Successfully launched to FIS_001 page");
		// commonFunction.enterTextboxContains("Employer Registration Number",
		// "4730215");
		commonFunction.enterTextboxContains("Employer Registration Number", eanNumber);
		sleep(2000);
		commonFunction.screenShot("Create Work Item", "Pass", "Entered valid ERN on FIS_001 page");
		commonFunction.clickButtonContains("Continue ");

		// ----FIS_010
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Select Work Item", "Pass", "Successfully launched to FIS_010 page");
		commonFunction.selectDropdown("Select Unit", " L&D Fraud ");
		sleep(2000);
		commonFunction.selectDropdown("Select Work Item", " Review SUTA Transfer and/or SUTA Penalty Protest Task ");
		sleep(2000);
		commonFunction.screenShot("Select Work Item", "Pass", "Entered Information on FIS_001 page");
		commonFunction.clickButtonContains("Continue ");
		// ----FIS_003
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Select Work Item", "Pass", "Successfully launched to FIS_003 page");
		sleep(2000);
		commonFunction.selectLink("Document", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.clickButtonContains(" Associate Documents ");
		// ----WF-101
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Search and Associate Documents", "Pass", "Successfully launched to WF-101 page");
		sleep(2000);
		commonFunction.enterTextboxContains("ERN", eanNumber);
		sleep(2000);
		commonFunction.screenShot("Search and Associate Documents", "Pass", "Entered valid ERN on WF-101 page");
		commonFunction.clickButtonContains(" Search ");
		commonFunction.waitForLoadingIconToDisappear();
		fiPage.dataTableId_select_0_checkbox.click();
		commonFunction.screenShot("Search and Associate Documents", "Pass", "Selected checkbox on WF-101 page");
		sleep(2000);
		//commonFunction.clickButtonContains(" Add Additional Employer ");
			commonFunction.clickButtonContains("Continue ");
		// ----FIS_003
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("Select Work Item", "Pass", "Successfully launched to FIS_003 page");
				sleep(2000);
				commonFunction.clickButtonContains(" Add Additional Employer ");
			  // ----FIS_004
		commonFunction.waitForLoadingIconToDisappear();
			  commonFunction.screenShot("Search Employer", "Pass",
			  "Successfully launched to FIS_004 page");
			  sleep(2000);
			  commonFunction.enterTextboxContains("Employer Registration Number",eanNumber); 
			  sleep(2000); 
			  commonFunction.screenShot("Search Employer", "Pass",
			  "Entered valid ERN on FIS_004 page");
			  commonFunction.clickButtonContains(" Search ");
			  
				commonFunction.waitForLoadingIconToDisappear();
//				fiPage.select_employer.click();
				commonFunction.screenShot("Search Employer", "Pass", "Selected radia on FIS_004 page");
				sleep(2000);
				commonFunction.clickButtonContains("Continue ");
			 
		// ----FIS_003
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Select Work Item", "Pass", "Successfully launched to FIS_003 page");
		sleep(2000);
		commonFunction.selectDropdown("Work Basket", "Default Work Basket");
		sleep(2000);
		commonFunction.selectDropdown("Source", " Correspondence/ Email/ Efax ");
		sleep(2000);

		commonFunction.enterTextboxContains("Mailing Address",
				"randomBusinessMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		
		commonFunction.enterTextboxContains("SUTA Dumping Penalty Amount Protesting", "100000");
		sleep(1000);
		commonFunction.enterTextboxContains("Remarks/Reasons for submitting Issue", "testing");
		sleep(1000);
		commonFunction.selectCheckbox("Is this protest a hearing request?");

		commonFunction.selectLink("Document", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.enterTextboxContains("Received Date", "09/06/2023");
		sleep(1000);
		commonFunction.clickButtonContains("Continue ");
		// ----FIS_005
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Verification - Review SUTA Transfer and/or SUTA Penalty Protest Task", "Pass",
				"Successfully launched to FIS_005 page");
		sleep(2000);
		commonFunction.clickButtonContains("Submit ");
		commonFunction.waitForLoadingIconToDisappear();

		// ----SUC 002
		sleep(2000);
		commonFunction.screenShot("Work item submission confirmation", "Pass", "Successfully landed on SUC 002");
		sleep(2000);

		commonFunction.clickButtonContains("Home ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		commonFunction.waitForLoadingIconToDisappear();

		// --------WorkItems--------

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
		commonFunction.enterTextboxContains("Work Item Description Free Text", "Review SUTA Transfer Protest");
		sleep(3000);
		commonFunction.screenShot("Work Item Description Free Text", "Pass", "Search for Task");
		sleep(3000);
		commonFunction.clickButtonContains(" Search ");
		sleep(3000);
		commonFunction.ScrollMenu("Review SUTA Transfer Protest");
		sleep(3000);
		commonFunction.screenShot("WIClick", "Pass", "Clicked on Work Item - Review Fraud Penalty Protest Task");
		sleep(3000);
		commonFunction.clickOnLink("Review SUTA Transfer Protest");

		// --- WF-091 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
		sleep(3000);
		commonFunction.clickButtonContains("Open Work Item ");

		// --- PFP-003---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Review SUTA Transfer Protest", "Pass", "Successful launch to PFP-003 page");
		sleep(2000);
		 commonFunction.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		 commonFunction.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		 commonFunction.selectRadioQuestions("Do you want to create a ‘Field Audit Request task?", "No ");
		fiPage.commentsId.sendKeys("Test Reveiw");
		sleep(2000);
		commonFunction.screenShot("Review SUTA Transfer Protest", "Pass", "Details entered in to PFP-003 page");
		sleep(2000);
		//commonFunction.clickOnLinkAnchorTag(" Inquiry Employer Account Information ");
		commonFunction.clickButtonContains("Submit ");

		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Task Completed.", "Pass", "Successful launch (SUC-002) page");
		sleep();
		commonFunction.clickButtonContains("Home ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();

		commonFunction.screenShot("TC:  FI_169_003", "Pass", "test completed  ");
		// ---Executed & completed by Palak
	}
}
