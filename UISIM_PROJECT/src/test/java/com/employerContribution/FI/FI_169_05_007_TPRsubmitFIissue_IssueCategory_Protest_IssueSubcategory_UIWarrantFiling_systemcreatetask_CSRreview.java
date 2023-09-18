package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_05_007_TPRsubmitFIissue_IssueCategory_Protest_IssueSubcategory_UIWarrantFiling_systemcreatetask_CSRreview
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'UI Warrant Filing' and system create task for CSR review", groups = "Regression")
	public void FI_169_05_007() throws Exception {

		test = report.createTest(
				"FI.169.05.007 - Verify TPR can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'UI Warrant Filing' and system create task for CSR review");
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		FIpage filocators = new FIpage(driver);

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
			System.out.println("Fetched ERN is: " + ernNum + ".");
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
		commonFunctions.selectDropdown("Subcategory", "How do I protest a UI Warrant Filing?");
		sleep();
		commonFunctions.screenShot("Write Message", "Pass", "Dropdown details filled-SM-101");
		commonFunctions.clickOnLinkAnchorTag("click here");
		sleep();
		commonFunctions.switchTab();

		// ---Submit issue - FIS-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Submit issue", "Pass", "Submit issue screen launched-FIS-002");
		commonFunctions.enterTextbox("Warrant File Date", "7/4/2023");
		commonFunctions.enterTextbox("Warrant Amount ($)", "2000000");
		filocators.EnterRemarks.sendKeys(
				"TPR can submit an FI Issue when Issue Category - Protest ,Issue Subcategory - 'How do I protest a UI Warrant Filing?'");

		commonFunctions.selectLink("Document", "Browse");
		sleep(2000);
		commonFunctions.uploadDoc("Sample.docx");
		sleep(2000);

		commonFunctions.screenShot("Submit issue", "Pass", "Submit issue details filled-FIS-002");
		commonFunctions.clickButton("Continue ");

		// ---Submit issue verification---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Submit issue verification", "Pass", "Submit issue verification screen launched");
		commonFunctions.clickButton("Submit ");

		// ---Issue Submission Confirmation-SUC-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Issue Submission Confirmation", "Pass", "Issue Submission Confirmation-SUC-002");
		commonFunctions.clickButton("Home ");
		sleep();

		commonFunctions.screenShot("FI.169.05.007 pass", "Pass", "Test Case got Pass FI.169.05.007");

		/* Test Case Completed */
	}
}