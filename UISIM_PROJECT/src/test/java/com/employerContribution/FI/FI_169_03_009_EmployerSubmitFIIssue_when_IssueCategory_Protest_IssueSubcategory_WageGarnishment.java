package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FIpage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_03_009_EmployerSubmitFIIssue_when_IssueCategory_Protest_IssueSubcategory_WageGarnishment
		extends TestBase {

	@Test(priority = 1, description = "Verify Employer can submit an FI Issue when (Issue Category - Protest, Issue Subcategory - 'Wage Garnishment'", groups = {
			"Regression" })
	public void FI_169_03_009() throws Exception {

		test = report.createTest(
				"FI.169.03.009 - Verify Employer can submit an FI Issue when (Issue Category - Protest, Issue Subcategory - 'Wage Garnishment'");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		FIpage filocators = new FIpage(driver);

		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
		String ernValue = databaseEanResult.get("EAN");
		
		System.out.println("ERN is: " + ernValue + ".");

		if ((ernValue == null) || (ernValue.isEmpty())) {
			System.out.println("ERN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched ERN is: " + ernValue + ".");
		}

		// ---Login---
		commonFunctions.login(COMMON_CONSTANT.EMPLOYER_USER_8, COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		test.log(Status.PASS, "Login with Employer is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");sleep();
		commonFunctions.ScrollMenu("Write Message");
		commonFunctions.screenShot("Menu", "Pass", "Menu Write Message");
		commonFunctions.clickMenu("Write Message");

		// ---Write Message-SM-101---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Write Message", "Pass", "Write Message page launched SM-101");
		commonFunctions.selectDropdown("Category", " Protest ");sleep();
		commonFunctions.selectDropdown("Subcategory", "How do I protest a Wage Garnishment?");sleep();
		commonFunctions.screenShot("Write Message", "Pass", "Category-Subcategory selected");
		commonFunctions.clickOnLinkAnchorTag("click here");sleep();
		commonFunctions.switchTab();sleep();

		// ---Submit Issue-FIS-002---//
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Submit Issue", "Pass", "Submit Issue page Successfully launched FIS-002");
		commonFunctions.enterTextbox("Garnishee Name", "Bill Britt");
		commonFunctions.enterTextbox("Garnishee Address", "12,state street NewYork State USA");
		commonFunctions.enterTextbox(" Garnishee Telephone Number ", "4565645674");
		commonFunctions.enterTextbox(" Garnishee Fax Number ", "8765745765");
		commonFunctions.enterCurrentDate("Wage Garnishment Issue Date");
		commonFunctions.enterTextbox("Wage Garnishment Amount ($)", "1200000");
		filocators.Reason_reasonBasisWageGarnishmentProtest.sendKeys("EmployerSubmitFIIssue when IssueCategory Protest IssueSubcategory WageGarnishment");
		
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Recipient_Worklist.xls");
		commonFunctions.waitForLoadingIconToDisappear();
		
		commonFunctions.screenShot("Submit Issue", "Pass", "Submit Issue details filled FIS-002");
		commonFunctions.clickButtonContains("Continue ");

		// ---Submit Issue Verification---//
		commonFunctions.waitForLoadingIconToDisappear();sleep(6000);
		commonFunctions.screenShot("Submit Issue Verification", "Pass", "Submit Issue Verification launched");
		commonFunctions.clickButtonContains("Submit ");

		// ---Issue Submission Confirmation-SUC-002---//
		commonFunctions.waitForLoadingIconToDisappear();sleep(6000);
		commonFunctions.screenShot("Issue Submission Confirmation", "Pass","Issue Submission Confirmation done-SUC-002");
		commonFunctions.clickButtonContains("Home ");sleep(6000);
		commonFunctions.screenShot("Home Scrren", "Pass","Test Case FI_169_03_009 got pass");
		
		//---Test Case Completed---//
	}

}
