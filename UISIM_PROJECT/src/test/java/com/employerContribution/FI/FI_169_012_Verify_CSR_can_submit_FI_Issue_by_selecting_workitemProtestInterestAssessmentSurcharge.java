package com.employerContribution.FI;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_012_Verify_CSR_can_submit_FI_Issue_by_selecting_workitemProtestInterestAssessmentSurcharge
		extends TestBase {
	@Test
	public void FI_169_012() throws Exception {
		test = report.createTest(
				"FI.169.012 -  'Verify CSR can submit an FI Issue by selecting a work item 'Protest Interest Assessment Surcharge '  (Issue Category - Protest, Issue Subcategory - ' Interest Assessment Surcharge (IAS) Protest')");

		commonStepDefinitions cf = new commonStepDefinitions();
		SUC_002 suc002 = new SUC_002(driver);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = new PEOPage(driver);
		EM_005 em005 = new EM_005(driver);

		// Query
		Map<String, String> databaseEanResult1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_SUBMIT_ISSUE_BENEFIT_CLAIM_PENALTY_ASSESSMENT ttsibcpa JOIN T_EMPLOYER_ACCOUNT tea ON TTSIBCPA.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"CLAIMANT_SSN");
		String ssnValue = databaseEanResult1.get("CLAIMANT_SSN");
		System.out.println("The SSN is " + ssnValue);

		Map<String, String> databaseEanResult2 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "EAN");
		String eanValue = databaseEanResult2.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// Menu
		cf.clickMenu("menu");
		sleep(2000);
		cf.screenShot("MenuPage", "Pass", "Launched to Menu");
		cf.ScrollMenu("Employer Issues");
		cf.clickMenu("Employer Issues");
		sleep(1000);
		cf.clickMenu("Create Work Item");
		cf.screenShot("Create Work Item - Enter ERN", "Pass", "Launched to FIS-001");
		cf.enterTextboxContains("Employer Registration Number", "5294535"); // hard-coded
		cf.clickButtonContains("Continue ");

		// FIS-010
		cf.screenShot("Select Work Item", "Pass", "Launched to FIS-010");
		cf.selectDropdown("Select Unit", " Employer Accounts Adjustment ");
		cf.selectDropdown("Select Work Item", " Protest Interest Assessment Surcharge ");
		cf.clickButtonContains("Continue ");

		// FIS-003
		cf.screenShot("Protest Interest Assessment Surcharge", "Pass", "Launched to FIS-003");
		cf.clickButtonContains(" Associate Documents ");

		// WF-101
		cf.screenShot("Search and Associate Documents", "Pass", "Launched to WF-101");
		cf.enterTextboxContains("SSN ", ssnValue);
		cf.clickButtonContains(" Search ");
		cf.selectCheckbox("Select");
		cf.clickButtonContains("Continue ");

		// FIS-003
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Protest Interest Assessment Surcharge", "Pass", "Launched to FIS-003");
		cf.selectDropdown("Work Basket", " Default Work Basket ");
		cf.selectDropdown("Source", " Department Request ");

		cf.enterTextboxContains("Name", "Sam");
		cf.enterTextboxContains("Title", "Miss");
		cf.enterTextboxContains(" Telephone Number ", "(545)-646-5434");
		cf.enterTextboxContains("Mailing Address", "shr@example.com");
		cf.enterTextboxContains("Specific Year Interest Assessment Surcharge being protested", "2021");
		cf.enterTextboxContains("Amount of the Interest Assessment Surcharge being protested", "34,523.45");
		cf.enterTextboxContains(
				"Reason/basis for Interest Assessment Surcharge Protest (must not exceed 2000 characters)",
				"Remark Test");
		empRegPage.browserLink.click();
		Thread.sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.clickButtonContains("Continue ");

		// FIS-005
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Verification - Protest Interest Assessment Surcharge", "Pass", "Launched to FIS-005");
		cf.clickButtonContains("Submit ");

		// SUC-002
		cf.waitForLoadingIconToDisappear();
		cf.screenShot(" Work Item Submission Confirmation", "Pass", "Launched to SUC-002");
		cf.Label("  A Work Item has been successfully created ");

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='5294535' ORDER BY UPDATED_TS desc)");
		Thread.sleep(4000);

		peoPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		cf.enterTextboxContains("Employer Registration Number", "5294535"); //hard-coded
		cf.screenShot("EAN Search", "Pass", "EAN Search");
		cf.clickButtonContains("Search");

		cf.clickOnLink("Protest Interest Assessment Surcharge");
		cf.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		cf.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		cf.screenShot("Protest Interest Assessment Surcharge", "Pass", "PFP-121 screen is visible");

		cf.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		cf.addCommentContains("ok");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();
		sleep(1000);
		cf.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");
		cf.Label("The task details have been successfully saved.");
		suc002.homeButton.click();
		cf.screenShot("Home", "Pass", "Home Page");
		System.out.println("Worked");
	}
}