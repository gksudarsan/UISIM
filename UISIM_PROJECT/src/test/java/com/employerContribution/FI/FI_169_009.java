package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_009 extends TestBase {

	@Test(priority = 1, description = "FI.169.009 -''Verify CSR can submit an FI Issue by selecting a work item 'Protest Wage Garnishment Task'  (Issue Category - Protest, Issue Subcategory - 'Wage Garnishment')", groups = {
			"Regression" })
	public void FI_169_009() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"FI.169.009 -''Verify CSR can submit an FI Issue by selecting a work item 'Protest Wage Garnishment Task'  (Issue Category - Protest, Issue Subcategory - 'Wage Garnishment')");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("Menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Employer Issues");
		commonFunctions.clickMenu("Employer Issues");
		commonFunctions.ScrollMenu("Create Work Item");
		commonFunctions.clickMenu("Create Work Item");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-001---
		commonFunctions.screenShot("Create Work Item - Enter ERN", "Pass",
				"Create Work Item - Enter ERN (FIS-001)screen launched");
		commonFunctions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunctions.screenShot("Generate Collection Notice - Enter ERN", "Pass", "ERN Entered");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-010---
		commonFunctions.screenShot("Select Work Item", "Pass", "Select Work Item (FIS-010)screen launched");
		commonFunctions.selectDropdown("Select Unit", " Collections ");
		sleep(1000);
		commonFunctions.selectDropdown("Select Work Item", " Protest Wage Garnishment Task ");
		sleep(1000);
		commonFunctions.screenShot("Select Work Item", "Pass", "Details selected on (FIS-010)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-003---
		commonFunctions.screenShot("Protest Wage Garnishment Task", "Pass",
				"Protest Wage Garnishment Task (FIS-003)screen launched");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample.docx");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickButtonContains(" Associate Documents ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---WF-101---
		commonFunctions.screenShot("Search and Associate Documents", "Pass",
				"Search and Associate Documents (WF-101)screen launched");
		commonFunctions.enterTextboxContains("ERN", eanValue);
		commonFunctions.clickButtonContains(" Search ");
		BCL.selectcheckbox_SearchAssociateDocuments.click();
		commonFunctions.screenShot("Search and Associate Documents", "Pass", "Record Selected on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		sleep(2000);

		// ---FIS-003---
		commonFunctions.screenShot("Protest Wage Garnishment Task", "Pass",
				"Protest Wage Garnishment Task (FIS-003)screen launched");
		commonFunctions.selectDropdown("Work Basket", " Default Work Basket ");
		sleep(1000);
		commonFunctions.selectDropdown("Source", " Department Request ");
		sleep(1000);
		commonFunctions.enterTextboxContains("Garnishee Name", "Bismith");
		commonFunctions.enterTextboxContains("Wage Garnishment Issue Date", "6/4/2023");
		BCL.Reasons_AuditProtest.sendKeys("For Testing");
		commonFunctions.screenShot("Audit Protest", "Pass", "Details Entered on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-005---
		commonFunctions.screenShot("Verification - Protest Wage Garnishment Task", "Pass",
				"Verification - Protest Wage Garnishment Task (FIS-005)screen launched");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Work Item Submission - Confirmation", "Pass",
				"Work Item Submission - Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		sleep(2000);

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");

		// Done
	}

}
