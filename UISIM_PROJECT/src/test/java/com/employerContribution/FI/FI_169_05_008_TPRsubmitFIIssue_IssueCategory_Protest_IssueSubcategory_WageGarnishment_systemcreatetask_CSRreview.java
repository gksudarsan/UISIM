package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_05_008_TPRsubmitFIIssue_IssueCategory_Protest_IssueSubcategory_WageGarnishment_systemcreatetask_CSRreview
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit an FI Issue when (Issue Category - Protest, Issue Subcategory - 'Wage Garnishment' and system create task for CSR review ", groups = "Regression")
	public void FI_169_05_008() throws Exception {

		test = report.createTest(
				"FI.169.05.008 - Verify TPR can submit an FI Issue when (Issue Category - Protest, Issue Subcategory - 'Wage Garnishment' and system create task for CSR review ");
		
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		FIpage filocators	= new FIpage(driver);
		
		Map<String, String> databaseEanResult = commonFunctions
				.database_SelectQuerySingleColumn("SELECT * FROM t_employer WHERE EMPLOYER_ID IN (\r\n"
						+ "SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION WHERE \r\n"
						+ "THIRD_PARTY_CDS_VENDOR_ID = (SELECT THIRD_PARTY_AGENT_ID FROM T_TPR_USER ttu WHERE USER_ID = 'tpruser121')\r\n"
						+ "AND ASSOCIATION_STATUS = 'ACTIVE'\r\n" + ");", "EAN");
		String ernNum = databaseEanResult.get("EAN");

		if ((ernNum == null) || (ernNum.isEmpty())) {
			System.out.println("ERN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernNum + ".");
			System.out.println("fetched ERN is: " + ernNum + ".");
		}

		// ---Login---
		commonFunctions.login(COMMON_CONSTANT.TPR_USER_1, COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("HomePage", "Pass", "HomePageLoaded");
		commonFunctions.clickMenu("Menu");
		sleep(2000);
		commonFunctions.clickMenu("Secure Messaging");
		sleep();
		commonFunctions.screenShot("Menu", "Pass", "Click on Write Message - Enter ERN");
		commonFunctions.clickMenu("Write Message - Enter ERN");

		// ---Write Message - Enter ERN-SM-100---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Write Message - Enter ERN", "Pass", "Successful launch Write Message SM-100");
		commonFunctions.enterTextbox("Employer Registration Number", ernNum);
		sleep();
		commonFunctions.screenShot("Write Message - Enter ERN", "Pass", "ERN Entered");
		commonFunctions.clickButton("Continue ");

		// ---Write Message-SM-101---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Write Message", "Pass", "Write Message page launched-SM-101");
		commonFunctions.selectDropdown("Category", "Protest");
		sleep();
		commonFunctions.selectDropdown("Subcategory", "How do I protest a Wage Garnishment?");
		sleep();
		commonFunctions.screenShot("Write Message", "Pass", "Dropdown details filled-SM-101");
		commonFunctions.clickOnLinkAnchorTag("click here");
		sleep();
		commonFunctions.switchTab();

		// ---Submit issue - FIS-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Submit Issue", "Pass", "Submit Issue page Successfully launched FIS-002");
		commonFunctions.enterTextbox("Garnishee Name", "Bill Britt");
		commonFunctions.enterTextbox("Garnishee Address", "12,state street NewYork State USA");
		commonFunctions.enterTextbox(" Garnishee Telephone Number ", "4565645674");
		commonFunctions.enterTextbox(" Garnishee Fax Number ", "8765745765");
		commonFunctions.enterCurrentDate("Wage Garnishment Issue Date");
		commonFunctions.enterTextbox("Wage Garnishment Amount ($)", "1200000");
		filocators.Reason_reasonBasisWageGarnishmentProtest.sendKeys("TPRSubmitFIIssue when IssueCategory Protest IssueSubcategory WageGarnishment");
		
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Recipient_Worklist.xls");
		commonFunctions.waitForLoadingIconToDisappear();
		
		commonFunctions.screenShot("Submit Issue", "Pass", "Submit Issue details filled FIS-002");
		commonFunctions.clickButtonContains("Continue ");

		// ---Submit issue verification---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Submit issue verification", "Pass", "Submit issue verification screen launched");
		commonFunctions.clickButton("Submit ");

		// ---Issue Submission Confirmation-SUC-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Issue Submission Confirmation", "Pass", "Issue Submission Confirmation-SUC-002");
		commonFunctions.clickButton("Home ");
		sleep();

		commonFunctions.screenShot("FI.169.05.008 pass", "Pass", "Test Case got Pass FI.169.05.008");

		/* Test Case Completed */
	}
}