package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.FIpage;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_05_002_TPRsubmitFIissue_IssueCategory_ReviewFraudPenaltyProtest_IssueSubcategory_Protest50PercentFraudPenaltyAssessed_systemcreatetask_CSRreview
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit an FI Issue when Issue Category - SUTA Hit Transfer or UIES Transfer , Issue Subcategory - ''SUTA Hit Transfer\" or \"UIES Transfer\" and system create task for CSR review", groups = "Regression")
	public void FI_169_05_002() throws Exception {

		test = report.createTest(
				"FI.169.05.002 : Verify TPR can submit an FI Issue when Issue Category - SUTA Hit Transfer or UIES Transfer , Issue Subcategory - ''SUTA Hit Transfer\" or \"UIES Transfer\" and system create task for CSR review");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FraudInvestigationLocators fiLocators = new FraudInvestigationLocators(driver);
		FIpage filocators	= new FIpage(driver);
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%';", "EAN");
		String ernNum = databaseEanResult.get("EAN");

		if ((ernNum == null) || (ernNum.isEmpty())) {
			System.out.println("ERN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNum + ".");
		}
		
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_3, COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		fiLocators.menu.click();
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		commonFuntions.ScrollMenu("Write Message - Enter ERN");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Secure Messaging -> Write Message - Enter ERN");
		commonFuntions.clickMenu("Write Message - Enter ERN");

		// --- SM-100 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successful launch Write Message - Enter ERN(SM-1000 screen");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum); //5454645
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "ERN entered and clicked on Continue");
		commonFuntions.clickButton("Continue ");

		// --- SM-101 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launched Write Message (SM-101) screen");
		commonFuntions.selectDropdown("Category", " Protest ");
		sleep();
		commonFuntions.selectDropdown("Subcategory", " How do I protest the fifty percent fraud penalty assessed? ");
		sleep();
		commonFuntions.screenShot("FI16905001", "Pass", "Data enetered in SM-101 screen");
		
		commonFuntions.clickOnLinkAnchorTag("click here");
		sleep(3000);
		commonFuntions.switchTab();
		
		commonFuntions.screenShot("FI16905001", "Pass", "Data enetered in SM-101 screen");
		
		// --- FIS-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launched Submit Issue(FIS-002) screen");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Error on blank continue for FIS-002 screen");
		
		filocators.EnterRemarks.sendKeys("Testing");
		
		fiLocators.Quarter_start.click();
		sleep(2000);
		fiLocators.Value_Quarter_1.click();
		fiLocators.Year_start.click();
		sleep(2000);
		fiLocators.Value_Year.click();
		
		fiLocators.Quarter_end.click();
		sleep(2000);
		fiLocators.Value_Quarter_start.click();
		fiLocators.Year_end.click();
		sleep(2000);
		fiLocators.Value_Year.click();
		
		commonFuntions.enterTextbox("Audit Contributions ($)", "10000");
		commonFuntions.enterTextbox("Penalty Amount Protesting ($)", "10000");
		commonFuntions.selectCheckbox("Is this protest a hearing request ?");
		commonFuntions.selectRadioQuestions("Are you also protesting audited assessment?", "No ");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Data entered in FIS-002 screen");
		
		sleep();
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Document entered in FIS-002 screen");
		
		commonFuntions.clickButton("Continue ");

		// ---Submit issue verification---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Submit issue verification screen launched");
		commonFuntions.clickButton("Submit ");

		// ---SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launched Issue Submission COnfirmation(SUC-002) screen");
		commonFuntions.clickButton("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Back to Home Screen");
		
		//--- CSR review Flow Started ---//
		// ---Login---
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+ernNum+"' ORDER BY UPDATED_TS desc)");
	     Thread.sleep(2000);

		// --- Navigation ----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User logged in Successfully");
		commonFuntions.clickButton(" Go to My Q ");
		sleep(20000);

		// --- WF-001 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launch to Individual Work Queue (WF-001) screen");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		commonFuntions.selectDropdown("Work Item Description", " Review Fraud Penalty Protest Task ");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("FI16905001", "Pass", "Data Searched for ERN at WF-001 page");
		
		commonFuntions.ScrollMenu("Review Fraud Penalty Protest Task");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Search result for WI");
		commonFuntions.clickHyperlink("Review Fraud Penalty Protest Task");
		
		// --- WF-091 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launch to Work Item Details (WF-091) screen");
		commonFuntions.clickButtonContains("Open Work Item");
		
		// --- PFP-003 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launch to Review Fraud Penalty Protest(PFP-003) screen");
		commonFuntions.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		commonFuntions.selectRadioQuestions("Abate this Penalty?", "Yes ");
		fiLocators.Entercomments.sendKeys("Test Comments");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Data entered in PFP-003 screen");
		
		sleep();
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Document entered in PFP-007 screen");
		
		commonFuntions.clickButton("Submit ");
		
		// ---SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launched Issue Submission COnfirmation(SUC-002) screen");
		commonFuntions.clickButton("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Back to Home Screen");
		
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");
		
		// --- Navigation ----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User logged in Successfully");
		commonFuntions.clickButton(" Go to My Q ");
		sleep(20000);

		// --- WF-001 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launch to Individual Work Queue (WF-001) screen");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		commonFuntions.enterTextbox("Work Item Description Free Text", "Penalty");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("FI16905001", "Pass", "Data Searched for ERN at WF-001 page");
		
		commonFuntions.ScrollMenu("Review Penalty Abatement Request Task");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Search result for WI");
		commonFuntions.clickHyperlink("Review Penalty Abatement Request Task");
				
		// --- WF-091 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launch to Work Item Details (WF-091) screen");
		commonFuntions.clickButtonContains("Open Work Item");
		
		// --- PFP-007 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launch to Review Penalty Abatement Request(PFP-007) screen");
		commonFuntions.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		commonFuntions.selectRadioQuestions("Select Action", "Approve");
		fiLocators.commentsId.sendKeys("Test Resolutions");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Data entered in PFP-007 screen");
		
		sleep();
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);
		commonFuntions.screenShot("FI16905001", "Pass", "Document entered in PFP-007 screen");
		
		commonFuntions.clickButton("Submit ");
		
		// ---SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully launched Issue Submission COnfirmation(SUC-002) screen");
		commonFuntions.clickButton("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Back to Home Screen");
				
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI16905001", "Pass", "Successfully passed TC FI.169.05.001");
	}
}