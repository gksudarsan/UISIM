package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_05_003_TPRsubmitFIissue_IssueCategory_Audit_IssueSubcategory_AuditProtest_systemcreatetask_CSRreview
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit an FI Issue whenIssue Category - Audit ,Issue Subcategory - 'Audit Protest'  and system create task for CSR review", groups = "Regression")
	public void FI_169_05_003() throws Exception {

		test = report.createTest(
				"FI_169_05_003_Verify TPR can submit an FI Issue whenIssue Category - Audit ,Issue Subcategory - 'Audit Protest'  and system create task for CSR review");
		//String ernNum = "5454645";
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIpage filocators	= new FIpage(driver);
		
		/*Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM t_employer WHERE EMPLOYER_ID IN (\r\n"
				+ "SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION WHERE \r\n"
				+ "THIRD_PARTY_CDS_VENDOR_ID = (SELECT THIRD_PARTY_AGENT_ID FROM T_TPR_USER ttu WHERE USER_ID = 'TPRUser01')\r\n"
				+ "AND ASSOCIATION_STATUS = 'ACTIVE'\r\n"
				+ ");", "EAN");*/
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM t_employer WHERE EMPLOYER_ID IN (SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION WHERE THIRD_PARTY_CDS_VENDOR_ID = '299')", "EAN");
		String ernNum = databaseEanResult.get("EAN");

		if ((ernNum == null) || (ernNum.isEmpty())) {
			System.out.println("ERN Value is null");
			test.log(Status.FAIL,"ERN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNum + ".");
		}
		System.out.println("DB connected successfully and fetched ERN is: " + ernNum + ".");

		// ---Login---TPRUser01
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_6, COMMON_CONSTANT.TPR_USER_6_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.clickMenu("Secure Messaging");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Write Message - Enter ERN");
		commonFuntions.clickMenu("Write Message - Enter ERN");

		// ---Write Message - Enter ERN-SM-100---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Write Message - Enter ERN", "Pass", "Successful launch Write Message SM-100");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		sleep();
		commonFuntions.screenShot("Write Message - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButton("Continue ");

		// ---Write Message-SM-101---//
		commonFuntions.waitForLoadingIconToDisappear();
		//commonFuntions.Label("SM-101");
		commonFuntions.screenShot("Write Message", "Pass", "Write Message page launched-SM-101");
		commonFuntions.selectDropdown("Category", "Protest");
		sleep();
		commonFuntions.selectDropdown("Subcategory", "How do I protest Audit Findings?");
		commonFuntions.screenShot("Write Message", "Pass", "Dropdown details filled-SM-101");
		commonFuntions.clickOnLinkAnchorTag("click here");
		sleep(5000);
		commonFuntions.switchTab();
		
		sleep();
		// ---Submit issue - FIS-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Submit issue", "Pass", "Submit issue screen launched-FIS-002");
		filocators.EnterRemarks.sendKeys("TPR can submit an FI Issue when Issue Category - Protest ,Issue Subcategory - 'How do I protest Audit Findings?'");
		
		filocators.Quarter_start.click();
		filocators.Value_Quarter_start.click();
		filocators.Year_start.click();
		filocators.Value_Year.click();
		filocators.Quarter_end.click();
		filocators.Value_Quarter_end.click();
		filocators.Year_end.click();
		filocators.Value_Year.click();
		
		commonFuntions.enterTextbox("Amount Protesting ($)", "10000");
		commonFuntions.selectCheckbox("Were Books and records previously provided?");
		commonFuntions.selectCheckbox("If 50% penalty was assessed are you protesting 50% fraud penalty?");
		commonFuntions.selectCheckbox("Is this protest a hearing request ?");
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFuntions.screenShot("Select Work Item", "Pass", "Selected the required details-FIS-002");
		commonFuntions.clickButton("Continue ");
		
		// ---Submit issue verification---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Submit issue verification", "Pass", "Submit issue verification screen launched");
		commonFuntions.clickButton("Submit ");

		// ---Issue Submission Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Issue Submission COnfirmation", "Pass", "Issue Submission COnfirmation-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("Redirected to Home Screen", "Pass", "Back to Home Screen");
		
		//--- CSR review Flow Started ---//
		// ---Login---
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.LnDSpecialist_User, COMMON_CONSTANT.LnDSpecialist_User_Pwd);
		test.log(Status.PASS, "Login with CSR-LnDSpecialist is successful");
		
		 commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+ernNum+"' ORDER BY UPDATED_TS desc)");
	     Thread.sleep(2000);

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CSR User Logged In", "Pass", "CSR User logged in Successfully");
		commonFuntions.clickButton(" Go to My Q ");
		sleep(10000);

		// ---Individual Work Queue-WF-001---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Individual Work Queue launched-WF-001");
		commonFuntions.enterTextbox("Employer Registration Number", ernNum);
		commonFuntions.selectDropdown("Work Item Description", "Audit Protest Task");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Data Searched for ERN-WF-001");
		commonFuntions.clickHyperlink("Audit Protest Task");

		// ---Work Item Details AUDIT PROTEST TASK-WF-091---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("AUDIT PROTEST TASK-WF-091", "Pass", "AUDIT PROTEST TASK page launched-WF-091");
		commonFuntions.clickButton("Open Work Item ");

		// ---Audit Protest Task-PFP-020---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("AUDIT PROTEST TASK-PFP-0201", "Pass", "AUDIT PROTEST TASK page launched-PFP-020");
		commonFuntions.selectRadioQuestions("Do you want to reroute this task to another work basket/User?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to create a Field Audit Request?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		commonFuntions.selectRadioQuestions("Is this Employer Protesting 50% Fraud Penalty?", "Yes ");
		commonFuntions.selectRadioQuestions("Do you want to close this work item with no action taken?", "Yes ");

		filocators.Entercomments.sendKeys("Audit Protest Task-PFP-020");
		
		commonFuntions.selectLink("Document", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);
		
		commonFuntions.screenShot("AUDIT PROTEST TASK-PFP-0201", "Pass", "APT page details filled-PFP-020");
		commonFuntions.clickButton("Submit ");

		// ---Task Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Task Confirmation", "Pass", "Task Confirmation page launched Successfully-SUC-002");
		commonFuntions.clickButton("Home ");
		sleep();
		commonFuntions.screenShot("FI.169.05.003 pass", "Pass", "Test Case got Pass FI.169.05.003");
	}
}