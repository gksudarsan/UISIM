package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FraudAndInvestigationPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_10_Verify_CSR_can_submit_an_FI_Issue_by_selecting_a_work_item_Protest_Offset_Intercept_Task_Issue_Category_Protest_Issue_Subcategory_Offset_Intercept
		extends TestBase {

	@Test(priority = 1, description = "FI.169.010 -'Verify_CSR_can_submit_an_FI_Issue_by_selecting_a_work_item_Protest_Offset_Intercept_Task_Issue_Category_Protest_Issue_Subcategory_Offset_Intercept", groups = {
			"Regression" })
	public void FI_169_010() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		BclPage BCL = new BclPage(driver);
		FraudAndInvestigationPage fiPage = new FraudAndInvestigationPage(driver);

		test = report.createTest(
				"FI.169.010 -'Verify_CSR_can_submit_an_FI_Issue_by_selecting_a_work_item_Protest_Offset_Intercept_Task_Issue_Category_Protest_Issue_Subcategory_Offset_Intercept");

		// --------Login-------
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.waitForLoadingIconToDisappear();
		// -------DB---
		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID AND tea.EAN LIKE '9%'",
				"EAN");

		String eanNumber = databaseEanResult.get("EAN");

		if ((eanNumber == null) || eanNumber.isEmpty()) {
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanNumber + ".");
		}

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
		commonFunctions.enterTextboxContains("Employer Registration Number", eanNumber);
		commonFunctions.screenShot("Generate Collection Notice - Enter ERN", "Pass", "ERN Entered");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-010---
		commonFunctions.screenShot("Select Work Item", "Pass", "Select Work Item (FIS-010)screen launched");
		commonFunctions.selectDropdown("Select Unit", " Collections ");
		sleep(1000);
		commonFunctions.selectDropdown("Select Work Item", " Protest Offset Intercept Task ");
		sleep(1000);
		commonFunctions.screenShot("Select Work Item", "Pass", "Details selected on (FIS-010)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-003---
		commonFunctions.screenShot("Protest Offset Intercept Task", "Pass",
				"Protest Offset Intercept Task (FIS-003)screen launched");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample.docx");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickButtonContains(" Associate Documents ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---WF-101---
		commonFunctions.screenShot("Search and Associate Documents", "Pass",
				"Search and Associate Documents (WF-101)screen launched");
		commonFunctions.enterTextboxContains("ERN", eanNumber);
		commonFunctions.clickButtonContains(" Search ");
		BCL.selectcheckbox_SearchAssociateDocuments.click();
		// fiPage.table_checkBox.click();
		commonFunctions.screenShot("Search and Associate Documents", "Pass", "Record Selected on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		sleep(2000);

		// ---FIS-003---
		commonFunctions.screenShot("Protest Offset Intercept Task", "Pass",
				"Protest Offset Intercept Task(FIS-003)screen launched");
		commonFunctions.selectDropdown("Work Basket", " Default Work Basket ");
		sleep(1000);
		commonFunctions.selectDropdown("Source", " Department Request ");
		sleep(1000);
		commonFunctions.enterTextboxContains("Offset/Intercept Source Name", "Bismith");
		commonFunctions.enterTextboxContains("Offset/Intercept Source Date", "6/4/2023");
		// BCL.Reasons_AuditProtest.sendKeys("For Testing");
		commonFunctions.enterTextboxContains("Offset/Intercept Amount", "100000");
		sleep(1000);
		commonFunctions.enterTextboxContains(
				"Reason/basis for Offset/Intercept Protest (must not exceed 2000 characters)", "testing");
		sleep(1000);
		commonFunctions.selectLink("Document", "Browse");
		sleep(2000);
		commonFunctions.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunctions.screenShot("Protest Offset Intercept Task", "Pass", "Details Entered on (FIS-003)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---FIS-005---
		commonFunctions.screenShot("Verification - Protest Offset Intercept Task Task", "Pass",
				"Verification - Protest Offset Intercept Task(FIS-005)screen launched");
		sleep(2000);
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ----SUC 002
		sleep(2000);
		commonFunctions.screenShot("Work item submission confirmation", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		// -----Home
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---Executed & completed by Palak

	}

}
